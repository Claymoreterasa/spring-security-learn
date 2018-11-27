package lab.ride.exception;

/**
 * @author cwz
 * @date 2018/11/26
 */
public class UserNotExistException extends RuntimeException {
    private String id;

    public UserNotExistException(String id){
        super("user not existed");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
