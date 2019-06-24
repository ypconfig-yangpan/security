package com.cancer.security.core.validate.code.image;

import com.cancer.security.core.validate.code.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.validate.code.image
 * @Description:
 * @date 2018/7/414:43
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    /**
     * 发送图片校验码
     *
     * @param request
     * @param imageCode
     * @throws Exception
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        BufferedImage image = imageCode.getImage();
        ServletOutputStream outputStream = request.getResponse().getOutputStream();
        ImageIO.write(image, "JPEG", outputStream);

    }
}
