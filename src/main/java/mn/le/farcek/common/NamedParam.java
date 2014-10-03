/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.le.farcek.common;

import java.util.Objects;

/**
 *
 * @author Farcek
 */
public class NamedParam {

    private String name;
    private Object value;

    public NamedParam(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public NamedParam(String name) {
        this.name = name;
    }

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
