/*
 * Copyright (C) 2014 Farcek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mn.le.farcek.common.bean;

import java.beans.Expression;
import java.beans.Statement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mn.le.farcek.common.utils.FClassUtils;
import mn.le.farcek.common.utils.FCollectionUtils;
import mn.le.farcek.common.utils.FStringUtils;

/**
 *
 * @author Farcek
 */
public class BeanPropertyFactory {

    public static BeanProperty[] findAllProperys(Class<?> beanClass) {
        return findAllProperys(beanClass, Object.class);
    }

    public static BeanProperty[] findAllProperys(Class<?> beanClass, Class<?> stopClass) {

        Class<?> cls = beanClass;
        Map<String, BeanProperty> beanPropertys = new HashMap<>();
        while (cls != null && !cls.equals(stopClass)) {
            for (Field f : beanClass.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                String propertyName = f.getName();
                registerProperty(beanPropertys, beanClass, propertyName);
            }
            cls = cls.getSuperclass();
        }

        List<Method> methods = new ArrayList<>();
        cls = beanClass;
        // get all methods
        while (cls != null && !cls.equals(stopClass)) {
            for (Method m : beanClass.getDeclaredMethods()) {
                if (Modifier.isStatic(m.getModifiers())) {
                    continue;
                }
                methods.add(m);
            }
            cls = cls.getSuperclass();
        }

        // find get methods
        for (Method m : methods) {
            String name = m.getName();
            if (name.startsWith("get") && FCollectionUtils.isEmpty(m.getParameterTypes())) {
                String propertyName = FStringUtils.firstLower(name.substring(3));
                registerProperty(beanPropertys, beanClass, propertyName);
            }
        }

        // find is methods
        for (Method m : methods) {
            String name = m.getName();
            if (name.startsWith("is") && FCollectionUtils.isEmpty(m.getParameterTypes()) && FClassUtils.isBooleanClass(m.getReturnType())) {
                String propertyName = FStringUtils.firstLower(name.substring(2));
                registerProperty(beanPropertys, beanClass, propertyName);
            }
        }

        // find has methods
        for (Method m : methods) {
            String name = m.getName();
            if (name.startsWith("has") && FCollectionUtils.isEmpty(m.getParameterTypes()) && FClassUtils.isBooleanClass(m.getReturnType())) {
                String propertyName = FStringUtils.firstLower(name.substring(3));
                registerProperty(beanPropertys, beanClass, propertyName);
            }
        }

//        // find raw methods
//        for (Method m : methods) {
//            String name = m.getName();
//            if (!name.startsWith("get") && !name.startsWith("is") && !name.startsWith("has")
//                    && FCollectionUtils.isEmpty(m.getParameterTypes()) && !m.getReturnType().equals(Void.TYPE)) {
//                registerProperty(beanPropertys, beanClass, name);
//            }
//        }

        return beanPropertys.values().toArray(new BeanProperty[]{});

    }

    private static void registerProperty(Map<String, BeanProperty> beanPropertys, Class<?> beanClass, String propertyName) {
        if (beanPropertys.containsKey(propertyName)) {
            return;
        }
        try {
            BeanProperty property = factoryProperty(beanClass, propertyName);
            beanPropertys.put(propertyName, property);
        } catch (NoSuchPropertyException ex) {
        }
    }

    @SuppressWarnings("null")
    public static BeanProperty factoryProperty(Class<?> beanClass, String propertyName) throws NoSuchPropertyException {

        if (propertyName == null) {
            throw new NullPointerException();
        }
        Field field = null;
        Class<?> type;

        {
            Class<?> cls = beanClass;
            do {
                try {
                    field = cls.getDeclaredField(propertyName);
                } catch (NoSuchFieldException | SecurityException ex) {
                }

                if (field != null && Modifier.isStatic(field.getModifiers())) {
                    field = null;
                }
                cls = cls.getSuperclass();
            } while (field == null && cls != null);

        }

        Method getter = getGetterMethod(beanClass, propertyName);

        if (field == null && getter == null) {
            throw new NoSuchPropertyException(String.format("`%s` no such property `%s` in Class", propertyName, beanClass));
        }

        if (getter == null && !Modifier.isPublic(field.getModifiers())) {
            throw new NoSuchPropertyException(String.format("`%s` no such property `%s` in Class", propertyName, beanClass));
        }

        type = field != null ? field.getType() : getter.getReturnType();
        Method setter = getSetterMethod(beanClass, type, propertyName);
        return new BeanProperty(beanClass, propertyName, type, field, getter, setter);

    }

    private static Method getSetterMethod(Class<?> beanClass, Class<?> type, String propertyName) {
        Method setter;
        String name = FStringUtils.firstUpper(propertyName);
        try {
            setter = beanClass.getMethod("set" + name, new Class<?>[]{type});
            if (FClassUtils.isStaticMethod(setter) == false) {
                return setter;
            }
        } catch (NoSuchMethodException | SecurityException ex) {
        }

        if (FClassUtils.isBooleanClass(type)) {
            try {
                setter = beanClass.getMethod("flag" + name, new Class<?>[]{type});
                if (FClassUtils.isStaticMethod(setter) == false) {
                    return setter;
                }
            } catch (NoSuchMethodException | SecurityException ex) {
            }
        }

        try {
            setter = beanClass.getMethod(propertyName, new Class<?>[]{type});
            if (FClassUtils.isStaticMethod(setter) == false) {
                return setter;
            }
        } catch (NoSuchMethodException | SecurityException ex) {
        }

        return null;
    }

    private static Method getGetterMethod(Class<?> beanClass, String propertyName) {
        Method getter;
        String name = FStringUtils.firstUpper(propertyName);
        try {
            getter = beanClass.getMethod("get" + name);
            if (FClassUtils.isStaticMethod(getter) == false) {
                return getter;
            }
        } catch (NoSuchMethodException | SecurityException ex) {
        }

        try {
            getter = beanClass.getMethod("is" + name);
            if (FClassUtils.isBooleanClass(getter.getReturnType()) && FClassUtils.isStaticMethod(getter) == false) {
                return getter;
            }
        } catch (NoSuchMethodException | SecurityException ex) {
        }

        try {
            getter = beanClass.getMethod("has" + name);
            if (FClassUtils.isBooleanClass(getter.getReturnType()) && FClassUtils.isStaticMethod(getter) == false) {
                return getter;
            }
        } catch (NoSuchMethodException | SecurityException ex) {
        }

        try {
            getter = beanClass.getMethod(propertyName);
            if (FClassUtils.isStaticMethod(getter) == false) {
                return getter;
            }
        } catch (NoSuchMethodException | SecurityException ex) {
        }

        return null;
    }
}
