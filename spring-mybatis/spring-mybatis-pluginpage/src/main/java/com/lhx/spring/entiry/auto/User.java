package com.lhx.spring.entiry.auto;

/**
 * users
 * @author Administrator 2018-07-02 23:54:56
 */
public class User {
    /**
     * 主键
     * This field corresponds to the database column users.id
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    private Integer id;

    /**
     * 名称
     * This field corresponds to the database column users.name
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    private String name;

    /**
     * 地址
     * This field corresponds to the database column users.address
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    private String address;

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 地址
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()));
    }

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        return result;
    }
}