package lab.ride.security.core.properties;

import lab.ride.security.core.validate.image.ImageCodeProperties;
import lab.ride.security.core.validate.sms.SmsCodeProperties;

/**
 * @author cwz
 * @date 2018/11/28
 */
public class ValidateCodeProperties {
   private ImageCodeProperties image = new ImageCodeProperties();

   private SmsCodeProperties sms = new SmsCodeProperties();

   public SmsCodeProperties getSms() {
      return sms;
   }

   public void setSms(SmsCodeProperties sms) {
      this.sms = sms;
   }

   public ImageCodeProperties getImage() {
      return image;
   }

   public void setImage(ImageCodeProperties image) {
      this.image = image;
   }
}
