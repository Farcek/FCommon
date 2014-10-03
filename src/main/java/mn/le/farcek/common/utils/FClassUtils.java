/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.le.farcek.common.utils;

/**
 *
 * @author Farcek
 */
public class FClassUtils {

    public static boolean instanceOf(Class<?> parant, Class<?> chield) {
        if (parant == null) {
            throw new NullPointerException("parant is null");
        }

        if (chield == null) {
            throw new NullPointerException("chield is null");
        }
        if (parant.equals(chield)) {
            return true;
        }

        return parant.isAssignableFrom(chield);
    }

    public static <T> T fromString(Class<T> typeClass, String value) {
        if (String.class.equals(typeClass)) {
            return (T) value;
        }
        if (Integer.class.equals(typeClass) || int.class.equals(typeClass)) {
            if (FStringUtils.isEmpty(value)) {
                return (T) Integer.valueOf(0);
            }
            return (T) Integer.valueOf(value);
        }
        if (Float.class.equals(typeClass) || float.class.equals(typeClass)) {
            if (FStringUtils.isEmpty(value)) {
                return (T) Float.valueOf(0f);
            }
            return (T) Float.valueOf(value);
        }
        if (Double.class.equals(typeClass) || double.class.equals(typeClass)) {
            if (FStringUtils.isEmpty(value)) {
                return (T) Double.valueOf(0d);
            }
            return (T) Double.valueOf(value);
        }
        if (Short.class.equals(typeClass) || short.class.equals(typeClass)) {
            if (FStringUtils.isEmpty(value)) {
                return (T) Short.valueOf("0");
            }
            return (T) Short.valueOf(value);
        }
        if (Byte.class.equals(typeClass) || byte.class.equals(typeClass)) {
            if (FStringUtils.isEmpty(value)) {
                return (T) Byte.valueOf("0");
            }
            return (T) Byte.valueOf(value);
        }
        if (Long.class.equals(typeClass) || long.class.equals(typeClass)) {
            if (FStringUtils.isEmpty(value)) {
                return (T) Long.valueOf("0");
            }
            return (T) Long.valueOf(value);
        }
        if (Character.class.equals(typeClass) || char.class.equals(typeClass)) {
            if (FStringUtils.isEmpty(value)) {
                return (T) new Character('\0');
            }
            return (T) Character.valueOf(value.charAt(0));
        }

        if (Boolean.class.equals(typeClass) || boolean.class.equals(typeClass)) {
            if (FStringUtils.isEmpty(value)) {
                return (T) Boolean.FALSE;
            }
            return (T) Boolean.valueOf(value);
        }

        throw new UnsupportedOperationException(String.format("can not convert; `%s` to `%s` ", value, typeClass));
    }
}
