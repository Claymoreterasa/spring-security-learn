package lab.ride.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author cwz
 * @date 2018/11/26
 */
@RestController
public class AsyncController {
    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder holder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        logger.info("主线程开始");
//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                logger.info("副线程开始");
//                Thread.sleep(1000);
//                logger.info("副线程返回");
//                return "success";
//            }
//        };

        String orderNum = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNum);
        DeferredResult<String> result = new DeferredResult<>();
        holder.getMap().put(orderNum, result);
        logger.info("主线程结束");
        return result;
    }
}
