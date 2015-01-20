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
package mn.le.farcek.common.utils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import mn.le.farcek.common.exeptions.TypeCastException;

/**
 *
 * @author Farcek
 */
public class FClassUtils {

    public static boolean instanceOf(Class<?> parant, Class<?> chield) {
        if (parant == null)
            throw new NullPointerException("parant is null");

        if (chield == null)
            throw new NullPointerException("chield is null");
        if (parant.equals(chield))
            return true;

        return parant.isAssignableFrom(chield);
    }
    

    public static boolean isStaticMethod(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }

    public static boolean isPublicMethod(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }

    public static boolean isBooleanClass(Class cls) {
        return boolean.class.equals(cls) | Boolean.class.equals(cls);
    }

    public static boolean isNumberClass(Class cls) {
        return instanceOf(Number.class, cls);
    }

    public static boolean isIntClass(Class cls) {
        return int.class.equals(cls) | Integer.class.equals(cls);
    }

    public static boolean isFloatClass(Class cls) {
        return float.class.equals(cls) | Float.class.equals(cls);
    }

    public static boolean isDoubleClass(Class cls) {
        return double.class.equals(cls) | Double.class.equals(cls);
    }

    public static boolean isLongClass(Class cls) {
        return long.class.equals(cls) | Long.class.equals(cls);
    }

    public static <T> T fromString(Class<T> typeClass, String value) {
        if (typeClass == null)
            throw new NullPointerException("typeClass is null");
        if (value == null)
            return null;

        if (String.class.equals(typeClass))
            return (T) value;

        if (Integer.class.equals(typeClass) || int.class.equals(typeClass))
            try {
                return (T) Integer.valueOf(value);
            } catch (NumberFormatException e) {
                return null;
            }
        if (Float.class.equals(typeClass) || float.class.equals(typeClass))
            try {
                return (T) Float.valueOf(value);
            } catch (NumberFormatException e) {
                return null;
            }
        if (Double.class.equals(typeClass) || double.class.equals(typeClass))
            try {
                return (T) Double.valueOf(value);
            } catch (NumberFormatException e) {
                return null;
            }
        if (Short.class.equals(typeClass) || short.class.equals(typeClass))
            try {
                return (T) Short.valueOf(value);
            } catch (NumberFormatException e) {
                return null;
            }
        if (Byte.class.equals(typeClass) || byte.class.equals(typeClass))
            try {
                return (T) Byte.valueOf(value);
            } catch (NumberFormatException e) {
                return null;
            }
        if (Long.class.equals(typeClass) || long.class.equals(typeClass))
            try {
                return (T) Long.valueOf(value);
            } catch (NumberFormatException e) {
                return null;
            }
        if (Character.class.equals(typeClass) || char.class.equals(typeClass)) {
            if (value.isEmpty())
                return null;
            return (T) new Character(value.charAt(0));
        }

        if (Boolean.class.equals(typeClass) || boolean.class.equals(typeClass))
            return (T) Boolean.valueOf(value);

        if (typeClass.isEnum()) {
            if (value.isEmpty())
                return null;
            try {
                return (T) Enum.valueOf((Class) typeClass, value);
            } catch (IllegalArgumentException e) {
                throw new TypeCastException(String.format("can not convert; `%s` string to `%s`. %s", value, typeClass, e.getMessage()));
            }
        }
        throw new TypeCastException(String.format("can not convert; `%s` string to `%s` ", value, typeClass));
    }

    public static Class[] getNestedClass(Class cls) {
        Class[] s = cls.getDeclaredClasses();

        System.out.println("s=" + Arrays.toString(s));
        s = cls.getClasses();

        System.out.println("s=" + Arrays.toString(s));
        return null;
    }

    public static String getAllMessage(Throwable throwable) {
        Throwable cause = throwable;
        StringBuilder sb = new StringBuilder();
        while (cause != null) {
            sb.append(cause.getMessage());
            cause = cause.getCause();
        }

        return sb.toString();
    }

}
