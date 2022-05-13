package com.demo.test.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-03 16:44
 *
 * @author
 */
public class TestNetty72 {
    private final static Logger logger = LoggerFactory.getLogger(TestNetty72.class);

    public static void main(String[] args) throws Exception {
        testJdkFuture();
    }


    private static void testJdkFuture() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                logger.info("计算");
                Thread.sleep(1000);
                return 50;
            }
        });

        logger.info("等待结果");
        //主线程通过future 来获取结果
        logger.info("结果:{}",future.get());
    }
}
