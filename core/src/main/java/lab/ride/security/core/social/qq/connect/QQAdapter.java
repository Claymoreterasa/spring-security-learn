package lab.ride.security.core.social.qq.connect;

import lab.ride.security.core.social.qq.api.QQ;
import lab.ride.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author cwz
 * @date 2018/12/02
 */
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {

        QQUserInfo userInfo = api.getUserInfo();

        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setDisplayName(userInfo.getNickname());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());

    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
