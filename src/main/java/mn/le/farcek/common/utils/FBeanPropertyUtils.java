/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.le.farcek.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import mn.le.farcek.common.bean.BeanProperty;

/**
 *
 * @author Farcek
 */
public class FBeanPropertyUtils {

    public static boolean checkCollectionType(BeanProperty property, Class<?> type) {
        if (FClassUtils.instanceOf(Collection.class, property.getType())) {
            Type genericType = property.getField().getGenericType();
            if (genericType instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericType;
                for (Type atp : pt.getActualTypeArguments()) {
                    return FClassUtils.instanceOf(type, (Class<?>) atp);
                }
            }
        }
        return false;
    }
}
