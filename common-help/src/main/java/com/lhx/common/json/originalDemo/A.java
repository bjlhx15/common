package com.lhx.common.json.originalDemo;

import java.io.Serializable;

public class A implements Serializable {

    private static final long serialVersionUID = -9065884233612105955L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
