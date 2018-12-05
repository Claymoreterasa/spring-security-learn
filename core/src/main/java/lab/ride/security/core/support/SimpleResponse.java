package lab.ride.security.core.support;

/**
 * @author cwz
 * @date 2018/11/27
 */
public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
