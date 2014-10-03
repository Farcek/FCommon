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
public class FBooleanUtils {

    public static boolean isTrue(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Boolean && (value.equals(true) || value.equals(Boolean.TRUE))) {
            return true;
        }
        if (value instanceof String) {
            String v = (String) value;
            if (v.equalsIgnoreCase("true") || v.equalsIgnoreCase("yes") || v.equalsIgnoreCase("ok") || v.equalsIgnoreCase("1")) {
                return true;
            }
        }
        if (value instanceof Number) {
            Number n = (Number) value;
            if (n.longValue() == 1l) {
                return true;
            }
        }
        return false;
    }
}
