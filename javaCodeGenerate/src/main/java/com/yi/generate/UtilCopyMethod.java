package com.yi.generate;


import com.yi.bean.YiewBean;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class UtilCopyMethod {


    public static void main(String[] args) throws Exception, IllegalAccessException {

        Field[] declaredFields = YiewBean.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            if (declaredField.getType() == String.class) {
                System.out.println(" if (from."+name+" != null) {");
                System.out.println("  to."+name+"=from."+name+";");
                System.out.println(" }");

            }
            if (declaredField.getType() == int.class) {


                System.out.println(" if (from."+name+" != 0) {");
                System.out.println("  to."+name+"=from."+name+";");
                System.out.println(" }");
            }
            if (declaredField.getType() == boolean.class) {


                System.out.println(" if (from."+name+") {");
                System.out.println("  to."+name+"=from."+name+";");
                System.out.println("}");

            }
            if (declaredField.getType() == ArrayList.class) {


//                System.out.println(" if (yiew."+name+" != null ) {");
//                System.out.println("  yiew1."+name+"=yiew."+name+";");
//                System.out.println("}");
            }

        }





    }


}
