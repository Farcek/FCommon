package mn.le.farcek.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FCollectionUtils {

    public static <T> boolean has(T[] arrays, T value) {
        if (arrays == null) {
            return false;
        }
        if (value == null) {
            return false;
        }
        for (T s : arrays) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean notEmpty(T[] arrays) {
        if (arrays == null) {
            return false;
        }

        return arrays.length > 0;
    }

    public static <T> boolean isEmpty(T[] arrays) {
        if (arrays == null) {
            return true;
        }
        return arrays.length == 0;
    }

    public static String toString(Collection collection, String sperator) {
        if (collection == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object o : collection) {
            if (i > 0) {
                sb.append(sperator);
            }
            sb.append(o);
            i++;
        }
        return sb.toString();
    }

    public static <T> String toString(T[] collection, String sperator) {
        if (collection == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object o : collection) {
            if (i > 0) {
                sb.append(sperator);
            }
            sb.append(o);
            i++;
        }
        return sb.toString();
    }

    public static String toString(Collection collection, StringConvertor convertor, String sperator) {
        if (collection == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object o : collection) {
            if (i > 0) {
                sb.append(sperator);
            }
            sb.append(convertor.toString(o));
            i++;
        }
        return sb.toString();
    }

    public static <T> int find(final T[] data, final T value) {
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                if (value == null) {
                    if (data[i] == null) {
                        return i;
                    }
                } else {
                    if (value.equals(data[i])) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public interface StringConvertor {

        public String toString(Object o);
    }

    public static <T> Collection<T> build(T... values) {
        Collection<T> cc = new ArrayList<>();
        cc.addAll(Arrays.asList(values));

        return cc;
    }

    public static <T> Set<T> buildSet(T... el) {
        Set<T> r = new HashSet<>();
        r.addAll(Arrays.asList(el));
        return r;
    }

    public static <T, K> T firstValue(Map<K, T> map) {
        if (map.isEmpty()) {
            return null;
        }
        return map.entrySet().iterator().next().getValue();
    }

    public static String join(Collection list, String sperator) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object it : list) {
            if (i++ > 0) {
                sb.append(sperator);
            }
            sb.append(it);
        }
        return sb.toString();
    }
    
    public static String join(Object[] list, String sperator) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object it : list) {
            if (i++ > 0) {
                sb.append(sperator);
            }
            sb.append(it);
        }
        return sb.toString();
    }
    
    public static String joinValues(Map list, String sperator) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object it : list.values()) {
            if (i++ > 0) {
                sb.append(sperator);
            }
            sb.append(it);
        }
        return sb.toString();
    }
    
    public static String joinKeys(Map list, String sperator) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object it : list.keySet()) {
            if (i++ > 0) {
                sb.append(sperator);
            }
            sb.append(it);
        }
        return sb.toString();
    }
}
