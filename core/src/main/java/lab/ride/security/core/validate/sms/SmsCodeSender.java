package lab.ride.security.core.validate.sms;

/**
 * @author cwz
 * @date 2018/11/29
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
