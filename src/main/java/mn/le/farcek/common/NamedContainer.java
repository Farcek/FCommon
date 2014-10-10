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
package mn.le.farcek.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mn.le.farcek.common.objects.FNamedObject;

/**
 *
 * @author Farcek
 * @param <T>
 */
public class NamedContainer<T extends FNamedObject> extends HashMap<String, T> implements Iterable<T> {

    public NamedContainer<T> set(T namedObject) {
        put(namedObject.getName(), namedObject);
        return this;
    }

    public NamedContainer<T> set(Map<String, ? extends T> params) {
        if (params != null) {
            putAll(params);
        }
        return this;
    }

    public NamedContainer<T> add(T namedObject) {
        if (namedObject != null) {
            put(namedObject.getName(), namedObject);
        }
        return this;
    }

    public NamedContainer<T> add(Map<String, ? extends T> params) {
        putAll(params);
        return this;
    }

    public T get(String name) {
        return super.get(name);
    }

    @Override
    public Iterator<T> iterator() {
        return super.values().iterator();
    }

    public Map<String, T> toMap() {
        return this;
    }
}
