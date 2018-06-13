package me.dakbutfly.spank_api;

import me.dakbutfly.spark_api.dto.ApiCallerDto;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRouter {

    static <T> T getInstance(Class<T> t) {
        T instance = null;
        try {
            Constructor<T> constructor = t.getConstructor();
            instance = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

    @Test
    public void reflicationTest() {
        Map map =  new HashMap();
        final String url = "www.naver.com";
        final String method = "GET";

        map.put("url", url);
        map.put("method", method);

        Class<ApiCallerDto> tClass = ApiCallerDto.class;
        ApiCallerDto instance = getInstance(tClass);

        Field[] fields = tClass.getDeclaredFields();
        List<Field> fieldsList = Arrays.asList(fields);
        System.out.println(fields.length);
        System.out.println(fieldsList.size());
        fieldsList.forEach((field) -> {
            String fieldName = field.getName();
            Class<?> type = field.getType();
            try {
                System.out.println(fieldName);
                field.setAccessible(true);
                Object value = map.get(fieldName);
                if (value == null) return;

                field.set(instance, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        });

        assert url.equals(instance.getUrl());
        assert method.equals(instance.getMethod());
    }
}
