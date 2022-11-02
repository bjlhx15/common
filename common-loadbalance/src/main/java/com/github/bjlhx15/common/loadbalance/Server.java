package com.github.bjlhx15.common.loadbalance;

public class Server {
    private String serverId;
    private String doamin;
    private int weight;

    public Server(String serverId, String doamin) {
        this.serverId = serverId;
        this.doamin = doamin;
    }

    public Server(String serverId, String doamin, int weight) {
        this.serverId = serverId;
        this.doamin = doamin;
        this.weight = weight;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getDoamin() {
        return doamin;
    }

    public void setDoamin(String doamin) {
        this.doamin = doamin;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
