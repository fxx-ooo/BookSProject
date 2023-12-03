package com.fxx.books.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RequestParameterUtils {
    public static  <T> T getRequestParameterForReflect(HttpServletRequest req, Class<T> cls) throws Exception {
        T t = cls.newInstance();
        Map<String, String[]> parameterMap = req.getParameterMap();
        Field[] declaredFields = cls.getDeclaredFields();
        if(declaredFields!=null&&declaredFields.length>0){
            for (Field declaredField : declaredFields) {
                String[] values = parameterMap.get(declaredField.getName());
                if (values==null||values.length==0){
                    continue;
                }
                //如果不表单中的值为空，那么也不用处理
                if (values[0]==null||"".equals(values[0])){
                    continue;
                }
                if (declaredField.getType()==String[].class){
                    //判断是否是数组
                    declaredField.setAccessible(true);//放开访问权限
                    try {
                        declaredField.set(t,values);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declaredField.setAccessible(false);
                    continue;
                }
                if (declaredField.getType()==Integer.class){
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(t,Integer.parseInt(values[0]));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declaredField.setAccessible(false);
                    continue;
                }else if (declaredField.getType()== BigDecimal.class){
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(t,new BigDecimal(values[0]));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declaredField.setAccessible(false);
                    continue;
                }else if (declaredField.getType()== Date.class){
                    setField(t, declaredField, values[0]);
                    continue;
                }
                //普通数据类型
                //判断是否是数组
                declaredField.setAccessible(true);//放开访问权限
                try {
                    declaredField.set(t,values[0]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                declaredField.setAccessible(false);
                continue;

            }

        }
        return t;
    }

    private static <T> void setField(T t, Field declaredField, String value) throws ParseException {
        declaredField.setAccessible(true);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        //Date parse = format.parse(values[0]);
        try {
            declaredField.set(t,format.parse(value));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        declaredField.setAccessible(false);
    }
}
