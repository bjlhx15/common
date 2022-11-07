package com.github.bjlhx15.common.loadbalance;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Server {
    private String path;
    private int weight;
    private int currentWeight;
}
