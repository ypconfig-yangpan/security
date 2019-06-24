package com.cancer.security.core.validate.code.image;

import com.cancer.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code
 * @Description:  图形验证码
 * @date 2018/7/214:43
 */
public class ImageCode extends ValidateCode{
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int exprieIn) {
        super(code,exprieIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime exprieTime) {
        super(code,exprieTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
