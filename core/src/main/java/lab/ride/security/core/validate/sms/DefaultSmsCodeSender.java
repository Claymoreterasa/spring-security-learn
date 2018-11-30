package lab.ride.security.core.validate.sms;

/**
 * @author cwz
 * @date 2018/11/29
 */
public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String code) {
        System.out.println("向 " + mobile +" 发送 " + code);
    }
}
