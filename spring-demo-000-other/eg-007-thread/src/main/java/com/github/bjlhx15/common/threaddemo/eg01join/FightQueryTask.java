package com.github.bjlhx15.common.threaddemo.eg01join;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author lihongxu6
 * @version 1.0
 * @className FightQueryTask
 * @description TODO
 * @date 2021-02-18 09:46
 */
public class FightQueryTask extends Thread implements FightQuery {
    private final String origin;
    private final String destination;
    private final List<String> fightList = new ArrayList<String>();

    public FightQueryTask(String airline, String origin, String destination) {
        super("[" + airline + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public List<String> get() {
        return this.fightList;
    }

    @Override
    public void run() {
        System.out.println(getName() + "-query " + origin + " to " + destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.fightList.add(getName() + "-" + randomVal);
            System.out.println(new Date()+":The fight" + getName() + " list query successful.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
