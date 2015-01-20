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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

import java.util.Set;
import mn.le.farcek.common.objects.FNamedObject;

/**
 *
 * @author Farcek
 */
public final class BeanProperty implements FNamedObject {

    private final Class<?> beanClass;
    private final String name;
    private final Class<?> type;
    private final Field field;
    private final Method getter;
    private final Method setter;

    BeanProperty(Class<?> beanClass, String name, Class<?> type, Field field, Method getter, Method setter) {
        this.beanClass = beanClass;
        this.name = name;
        this.type = type;
        this.field = field;
        this.getter = getter;
        this.setter = setter;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public Field getField() {
        return field;
    }

    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }

    public boolean isWritable() {
        return setter != null || field != null;
    }

    public Object getValue(Object bean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (getter != null) {
            return getter.invoke(bean);
        }
        if (field != null) {
            return field.get(bean);
        }
        throw new IllegalArgumentException();
    }

    public void setValue(Object bean, Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (setter != null) {
            setter.invoke(bean, new Object[]{value});
            return;
        }
        if (field != null) {
            field.set(bean, value);
            return;
        }
        throw new IllegalArgumentException();
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotation) {
        T ann = null;
        if (field != null) {
            ann = field.getAnnotation(annotation);
        }
        if (ann == null && getter != null) {
            ann = getter.getAnnotation(annotation);
        }
        if (ann == null && setter != null) {
            ann = setter.getAnnotation(annotation);
        }
        return ann;
    }

    public Annotation[] getAnnotations() {
        Set<Annotation> anns = new HashSet<>();

        if (setter != null) {
            anns.addAll(Arrays.asList(setter.getAnnotations()));
        }
        if (getter != null) {
            anns.addAll(Arrays.asList(getter.getAnnotations()));
        }
        if (field != null) {
            anns.addAll(Arrays.asList(field.getAnnotations()));
        }
        return anns.toArray(new Annotation[]{});
    }

    @Override
    public String toString() {
        return getName() + ":" + getType().getName();
    }

}
