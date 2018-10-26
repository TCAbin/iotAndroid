package cn.com.gree.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Abin
 * @date 2018/8/7 14:20
 * json 格式化数据
 */
public class JsonUtil {

    private JsonUtil(){}


    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        init();
    }

    /**
     * @author Abin
     * @date 2018/8/7 11:03
     * 初始化
     */
    private static void init(){
        // 总是把所有字段序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 不对空数据进行校验
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        // 默认日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * @author Abin
     * @date 2018/8/7 11:43
     * 转换类型
     */
    public static <T> T coverValue(Object object, Class<T> clazz){
        T obj = null;
        try {
            obj = objectMapper.convertValue(object,clazz);
        }catch (IllegalArgumentException e){
            System.out.println("解析错误。" + e.getMessage());
        }
        return obj;
    }

    /**
     * @author Abin
     * @date 2018/8/7 11:04
     * 将对象序列化为字符串
     */
    public static <T> String objectToString(T object){
        String obj = null;
        try {
            obj = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.out.println("解析错误。" + e.getMessage());
        }
        return obj;
    }


    /**
     * @author Abin
     * @date 2018/8/7 11:06
     * 字符串序列化为对象
     */
    public static <T> T StringToObject(String json, Class<T> clazz){
        T obj = null;
        try {
            obj = objectMapper.readValue(json,clazz);
        } catch (IOException e) {
            System.out.println("解析错误。" + e.getMessage());
        }
        return obj;
    }

    /**
     * @author Abin
     * @date 2018/8/7 11:07
     * 字符流序列化为对象
     */
    public static <T> T streamToObject(InputStream in, Class<T> clazz){
        T obj = null;
        try {
            obj = objectMapper.readValue(in,clazz);
        } catch (IOException e) {
            System.out.println("解析错误。" + e.getMessage());
        }
        return obj;
    }

    /**
     * @author Abin
     * @date 2018/8/7 11:09
     * 字符串序列化为List对象
     */
    public static <T> List<T> stringToListObject(String json, Class<T> clazz){
        List<T> obj = null;
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class,clazz);
        try {
            obj = objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            System.out.println("解析错误。" + e.getMessage());
        }
        return obj;
    }

    /**
     * @author Abin
     * @date 2018/8/7 11:09
     * 字符流序列化为List对象
     */
    public static <T> List<T> streamToListObject(InputStream in, Class<T> clazz){
        List<T> obj = null;
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class,clazz);
        try {
            obj = objectMapper.readValue(in, javaType);
        } catch (IOException e) {
            System.out.println("解析错误。" + e.getMessage());
        }
        return obj;
    }



}
