/*
 * Copyright (c) 2020.
 */
package com.project.utils.lang;

import java.math.BigDecimal;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-09 20:34
 */
public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    /**
     * 获取指定范围和指定小数位的随机数
     *
     * @param startInclusive 最小值
     * @param endInclusive   最大值
     * @param scale          小数位
     * @return 返回值
     */
    public static double nextDouble(final double startInclusive, final double endInclusive, int scale) {
        double d = org.apache.commons.lang3.RandomUtils.nextDouble(startInclusive, endInclusive);
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }
}
