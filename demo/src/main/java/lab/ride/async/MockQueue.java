package lab.ride.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author cwz
 * @date 2018/11/26
 */
@Component
public class MockQueue {
    private String placeOrder;

    private String completeOrder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(() -> {
            logger.info("接到下单请求");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.placeOrder = placeOrder;
            this.completeOrder = placeOrder;
            logger.info("下单请求处理完毕");
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
