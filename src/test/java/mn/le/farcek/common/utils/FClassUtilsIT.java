/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.le.farcek.common.utils;

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
public class FClassUtilsIT {
    
    public FClassUtilsIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of instanceOf method, of class FClassUtils.
     */
    @Test
    public void testInstanceOf() {
       
    }

    /**
     * Test of fromString method, of class FClassUtils.
     */
    @Test
    public void testFromString() {
       
    }

    /**
     * Test of getNestedClass method, of class FClassUtils.
     */
    @Test
    public void testGetNestedClass() {
        System.out.println("getNestedClass");
        Class cls = TestClass.class;
        Class[] expResult = null;
        Class[] result = FClassUtils.getNestedClass(cls);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    public static class TestClass{
        public class A{
            
        }
        public class B{
            
        }
        public static class c{
            
        }
        public interface In{
            
        }
        public enum EM{
            
        }
        
    }
    
}
