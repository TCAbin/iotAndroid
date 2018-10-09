package cn.com.gree.entity.Do;

import io.swagger.annotations.ApiModel;

@ApiModel
public class UpdateDeviceDo {

    private String barCode;
    private String area;
    private String password;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
