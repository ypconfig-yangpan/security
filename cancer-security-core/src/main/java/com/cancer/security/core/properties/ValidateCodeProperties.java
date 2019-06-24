package com.cancer.security.core.properties;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.properties
 * @Description:
 * @date 2018/7/217:30
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    private  SMSCodeProperties sms = new SMSCodeProperties();


    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SMSCodeProperties getSms() {
        return sms;
    }

    public void setSms(SMSCodeProperties sms) {
        this.sms = sms;
    }
}
