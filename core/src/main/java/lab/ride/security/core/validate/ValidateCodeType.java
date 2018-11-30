package lab.ride.security.core.validate;

import lab.ride.security.core.properties.SecurityConstants;

/**
 * @author cwz
 * @date 2018/11/30
 */
public enum ValidateCodeType {
    IMAGE {
        @Override
        public String getParamNameOnValidate(){
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    },

    SMS {
        @Override
        public String getParamNameOnValidate(){
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();

}
