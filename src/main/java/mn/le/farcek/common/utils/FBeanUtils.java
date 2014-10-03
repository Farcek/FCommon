/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.le.farcek.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Farcek
 */
public class FBeanUtils {

    public static Object get(Object object, String property) {
        if (object == null) {
            return null;
        }
        Class c = object.getClass();
        String mName = FStringUtils.firstUpper(property);
        try {
            Method m = c.getMethod("get" + mName);
            return m.invoke(object);
        } catch (NoSuchMethodException | SecurityException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }

        try {
            Method m = c.getMethod("is" + mName);
            return m.invoke(object);
        } catch (NoSuchMethodException | SecurityException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String getString(Object object, String property) {
        Object v = get(object, property);
        return v == null ? "" : v.toString();
    }
}
