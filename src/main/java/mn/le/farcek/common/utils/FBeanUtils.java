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
