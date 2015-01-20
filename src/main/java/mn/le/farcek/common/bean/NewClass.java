/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.le.farcek.common.bean;

import java.util.Arrays;

/**
 *
 * @author Farcek
 */
public class NewClass {
    public static void main(String[] args) {
        BeanProperty[] findAllProperys = BeanPropertyFactory.findAllProperys(Test.class);
        
        System.out.println("findAllProperys="+Arrays.toString(findAllProperys));
        
        for (BeanProperty findAllPropery : findAllProperys) {
            System.out.println("-------------------------");
            System.out.println("field:"+findAllPropery.getField());
            System.out.println("get:"+findAllPropery.getGetter());
            System.out.println("set:"+findAllPropery.getSetter());
        }
        
        
        
        //---------------------------
        int n=600,m=5;
        int l=0;
        for(int i=3;i<n;i+=3){            
            System.out.print(i);
            l++;// l+=1;
            
            if(managernantai(l)){
                //..
            }
            
        }
    }
    
    public static boolean managernantai(int k) {
        //// ..
        
        return true;
    }
    
    
    class BB{
        protected float d;
    }
    
    class Test extends BB{
        
        private String s;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
        
        
        public int getA(){
            return 0;
        }

        public float getD() {
            return d;
        }
        
        
        
    }
}
