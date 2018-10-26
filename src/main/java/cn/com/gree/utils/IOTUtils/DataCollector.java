package cn.com.gree.utils.IOTUtils;

import cn.com.gree.utils.IOTUtils.utils.HttpsUtil;
import cn.com.gree.utils.IOTUtils.utils.StreamClosedHttpResponse;
import cn.com.gree.utils.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Abin
 * @date 2018/8/7 14:25
 * 数据获取接口，从华为平台处获取所需要的数据
 * 该数据属于原始数据，整合处理获取对应设备ID的温湿度、PM、UM值
 */
public class DataCollector {

    /** https校验 */
    private static HttpsUtil httpsUtil = null;

    /**
     * @author Abin
     * @date 2018/8/7 14:27
     * 初始化https的验证
     */
    static {
        verifyHttps();
    }

    /**
     * @author Abin
     * @date 2018/8/7 15:58
     * Https函数
     */
    private static void verifyHttps(){
        httpsUtil = new HttpsUtil();
        try {
            httpsUtil.initSSLConfigForTwoWay();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author 260145
     * @date 2018/6/27 16:16
     * getToken，获取访问华为接口的token
     */
    @SuppressWarnings("unchecked")
    public static String getToken(){
        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        String urlLogin = Constant.APP_AUTH;

        try {
            Map<String, String> paramLogin = new HashMap<>();
            paramLogin.put("appId", appId); // 对应项目Id
            paramLogin.put("secret", secret); // 密钥
            StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

            Map<String, String> data = new HashMap<>();
            data = JsonUtil.StringToObject(responseLogin.getContent(), data.getClass());
            return data.get("accessToken");
        } catch (Exception e) {
            verifyHttps();
            System.out.println("获取token失败。" + e.getMessage());
        }
        return null;
    }


    /**
     * @author 260145
     * @date 2018/6/26 13:39
     * 调用远程接口,或许所需的数据
     */
    @SuppressWarnings("unchecked")
    public static Map<String,String> getRemoteData(String deviceId,String accessToken) throws Exception {
        String appId = Constant.APPID;
        String urlQueryDeviceData = Constant.DEVICE_DATA + "/" + deviceId + "?appId=" + appId;

        Map<String, String> paramQueryDeviceData = new HashMap<>();
        paramQueryDeviceData.put("appId", appId);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse bodyQueryDeviceData = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceData,
                paramQueryDeviceData, header);

        Map<String, String> data = new HashMap<>();
        data = JsonUtil.StringToObject(bodyQueryDeviceData.getContent(), data.getClass());

        Map deviceData = JsonUtil.coverValue(JsonUtil.coverValue(JsonUtil.coverValue(data.get("services"),List.class).get(0),Map.class).get("data"),Map.class);
        String eventTime = String.valueOf(JsonUtil.coverValue(JsonUtil.coverValue(data.get("services"),List.class).get(0),Map.class).get("eventTime"));
        String name = (String) JsonUtil.coverValue(data.get("deviceInfo"),Map.class).get("name");
        String status = (String) JsonUtil.coverValue(data.get("deviceInfo"),Map.class).get("status");

        data.clear();
        data.put("name",name);
        data.put("eventTime",eventTime);
        data.put("temperature",coverTemperature(String.valueOf(deviceData.get("temperature"))));
        data.put("humidity", String.valueOf(deviceData.get("humidity")));
        data.put("PM1_0", String.valueOf(deviceData.get("pm1_0")));
        data.put("PM2_5", String.valueOf(deviceData.get("pm2_5")));
        data.put("PM10", String.valueOf(deviceData.get("pm10")));
        data.put("UM0_3", String.valueOf(deviceData.get("num_0_3")));
        data.put("UM0_5", String.valueOf(deviceData.get("num_0_5")));
        data.put("UM1_0", String.valueOf(deviceData.get("num_1_0")));
        data.put("UM2_5", String.valueOf(deviceData.get("num_2_5")));
        data.put("UM5", String.valueOf(deviceData.get("num_5_0")));
        data.put("UM10", String.valueOf(deviceData.get("num_10")));

        /* 设备状态 */
        switch (status){
            case "ONLINE" : {
                data.put("status","1");
                break;
            }
            case "OFFLINE" : {
                data.put("status","2");
                break;
            }
            case "INBOX" : {
                data.put("status","3");
                break;
            }
            case "ABNORMAL" : {
                data.put("status","4");
                break;
            }
            default : {
                data.put("status","5");
                break;
            }
        }
        return data;
    }


    /**
     * @author Abin
     * @date 2018/10/26 14:29
     * 由于华为保存的不是摄氏度，需要转换
     */
    private static String coverTemperature(String temperature){
        Integer tem = Integer.valueOf(temperature);
        String str = String.valueOf((tem - 400) * 0.1);
        return str.substring(0,str.indexOf(".") + 2);
    }
}
