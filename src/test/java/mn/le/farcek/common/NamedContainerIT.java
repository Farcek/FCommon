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
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Farcek
 */
public class NamedContainerIT {

    public NamedContainerIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    NamedContainer<NamedParam> instance;

    @Before
    public void setUp() {
        instance = new NamedContainer<>();

        instance.set(new NamedParam("a", 1));
        instance.set(new NamedParam("b", 2));
        instance.set(new NamedParam("b", 3));
        instance.set(new NamedParam("c", 4));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class NamedContainer.
     */
    @Test
    public void testAdd() {
        System.out.println("add");

        assertEquals(instance.size(), 3);
        assertEquals(instance.get("a").getValue(), 1);
        assertEquals(instance.get("b").getValue(), 3);
        assertEquals(instance.get("c").getValue(), 4);

    }

    @Test
    public void testFor() {
        System.out.println("for");

        boolean b=false;
        for (NamedParam it : instance) {            
            b= true;
        }
        
        assertEquals(b, true);

    }

}
