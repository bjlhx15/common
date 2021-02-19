package com.github.bjlhx15.common.threaddemo.eg01join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lihongxu6
 * @version 1.0
 * @className FightQueryExample
 * @description TODO
 * @date 2021-02-18 09:55
 */
public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    private static List<String> search(String origin, String dest) {
        final List<String> result = new ArrayList<>();
        List<FightQueryTask> tasks = fightCompany.stream().map(p -> createSearchTask(p, origin, dest))
                .collect(Collectors.toList());
        tasks.forEach(p -> p.start());
//        每个线程启动join方法
        tasks.forEach(p -> {
            try {
                p.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        结果汇总
        tasks.stream().map(p -> p.get()).forEach(p -> result.addAll(p));
        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String original, String dest) {
        return new FightQueryTask(fight, original, dest);
    }

    public static void main(String[] args) {
        System.out.println(new Date());
        List<String> search = search("SH", "BJ");
        System.out.println("==============result===============");
        search.forEach(p -> System.out.println(p));
    }
}
