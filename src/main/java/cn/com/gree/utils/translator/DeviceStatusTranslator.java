package cn.com.gree.utils.translator;

public class DeviceStatusTranslator {

    /**
     * @author Abin
     * @date 2018/8/7 14:16
     * 状态码转为中文
     */
    public static String intToChinese(Integer statusCode){
        switch (statusCode){
            case 1 : return "在线";
            case 2 : return "离线";
            case 3 : return "INBOX";
            case 4 : return "异常";
            default : return "未知";
        }
    }

}
