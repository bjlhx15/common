package com.github.bjlhx15.common.test.other;

import sun.management.HotspotRuntimeMBean;
import sun.management.ManagementFactoryHelper;

public class StopTheWorldSout {
    private static HotspotRuntimeMBean mbean = (HotspotRuntimeMBean) ManagementFactoryHelper.getHotspotRuntimeMBean();

    public static void main(String[] args) {
        long count = mbean.getSafepointCount();
        long time = mbean.getTotalSafepointTime();
        long syncTime = mbean.getSafepointSyncTime();
    }
}
