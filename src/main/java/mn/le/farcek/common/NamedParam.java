/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import java.util.Objects;
import mn.le.farcek.common.objects.FNamedObject;

/**
 *
 * @author Farcek
 */
public class NamedParam implements FNamedObject{

    private String name;
    private Object value;

    public NamedParam(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public NamedParam(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public NamedParam setName(String name) {
        this.name = name;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public NamedParam setValue(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return String.format("`%s` = '%s'", name, value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NamedParam other = (NamedParam) obj;
        return Objects.equals(this.name, other.name);
    }

}
