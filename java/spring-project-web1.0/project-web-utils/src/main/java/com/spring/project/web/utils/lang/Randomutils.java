/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.utils.lang;


import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-08-26 15:46
 */
public class Randomutils extends RandomUtils {

    public static double nextDouble(final double startInclusive, final double endInclusive, int scale) {
        double d = RandomUtils.nextDouble(startInclusive, endInclusive);
        BigDecimal bg = new BigDecimal(d);
        d = bg.setScale(scale).doubleValue();
        return d;
        //double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}