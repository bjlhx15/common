package com.github.bjlhx15.common.loadbalance;

/**
 * @author lihongxu
 * @version 1.0
 * @className ILoadBalance
 * @description 负载选择接口
 * @since 2022/11/7 9:59 下午
 */
public interface ILoadBalance {
    Server select(final LoadBalanceContext context);
}
