package com.github.bjlhx15.common.other.xml.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author lihongxu6
 * @version 1.0
 * @className Person
 * @description TODO
 * @since 2020-08-07 14:36
 */
@XStreamAlias("Person")
public class Person {
    @XStreamAlias("order")
    private Order order;

    public static class Order {
        @XStreamAlias("Name")
        private String name;
        @XStreamAlias("Mobile")
        private String mobile;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}