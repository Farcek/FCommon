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

import java.util.Collection;
import java.util.Random;

public class FStringUtils {

    public static boolean has(String srcString, String value) {
        return has(srcString, ";", value);
    }

    public static boolean has(String srcString, String splitor, String value) {
        if (srcString == null) {
            return false;
        }
        if (splitor == null) {
            return false;
        }
        return has(srcString.split(splitor), value);
    }

    public static boolean has(String[] srcString, String value) {
        return FCollectionUtils.has(srcString, value);
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().isEmpty();
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    public static String firstUpper(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String firstLower(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    public static String toString(Collection collection, String sperator) {
        return FCollectionUtils.toString(collection, sperator);
    }

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String N = "0123456789";

    public static String RandomAlpha(int len) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static String RandomNumber(int len) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        sb.append(N.charAt(1 + rnd.nextInt(N.length() - 1)));
        for (int i = 0; i < len - 1; i++) {
            sb.append(N.charAt(rnd.nextInt(N.length())));
        }
        return sb.toString();
    }

}
