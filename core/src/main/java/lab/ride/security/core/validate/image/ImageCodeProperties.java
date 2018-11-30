package lab.ride.security.core.validate.image;

import lab.ride.security.core.validate.sms.SmsCodeProperties;

/**
 * @author cwz
 * @date 2018/11/28
 */
public class ImageCodeProperties extends SmsCodeProperties {
    private int width = 67;
    private int height = 23;

    public ImageCodeProperties(){
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
