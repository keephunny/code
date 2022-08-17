package com.test.pack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-06-07 20:20
 *
 * @author
 */
public class PackUtil {

    private final Logger logger = LoggerFactory.getLogger(PackUtil.class);

    public int add(Integer a, Integer b) {
        System.out.println(a + "------" + b);
        logger.info("add:{} {}", a, b);

        try {
            float f = 0 / 1f;
        } catch (Exception e) {
            logger.error("ee", e);
        }

        System.exit(0);
        return a + b;
    }
}
