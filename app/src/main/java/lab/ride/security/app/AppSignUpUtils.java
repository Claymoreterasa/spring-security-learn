package lab.ride.security.app;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cwz
 * @date 2018/12/04
 */
@Component
public class AppSignUpUtils {
    private ConcurrentHashMap<String, ConnectionData> users = new ConcurrentHashMap<>();

    @Autowired
    private UsersConnectionRepository connectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    public void saveConnectionData(WebRequest request, ConnectionData connectionData){
        users.put(getKey(request), connectionData);
    }

    public void doPostSignUp(WebRequest request, String userId){
        String key = getKey(request);
        if(users.get(key) == null){
            throw new AppSecurityException("无法找到用户信息");
        }
        ConnectionData connectionData = (ConnectionData) users.get(key);
        Connection connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);
        connectionRepository.createConnectionRepository(userId).addConnection(connection);
        users.remove(key);
    }

    private String getKey(WebRequest request){
        String deviceId = request.getHeader("deviceId");
        if(StringUtils.isBlank(deviceId)){
            throw new AppSecurityException("设备id参数不能为空");
        }
        return deviceId;
    }
}
