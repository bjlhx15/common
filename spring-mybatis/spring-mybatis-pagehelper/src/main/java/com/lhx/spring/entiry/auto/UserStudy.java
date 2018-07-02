package com.lhx.spring.entiry.auto;

/**
 * user_study
 * @author Administrator 2018-07-02 23:54:56
 */
public class UserStudy {
    /**
     * 主键
     * This field corresponds to the database column user_study.s_id
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    private Integer sId;

    /**
     * 姓名
     * This field corresponds to the database column user_study.user_name
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    private String userName;

    /**
     * 性别
     * This field corresponds to the database column user_study.s_age
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    private String sAge;

    /**
     * 主键
     * @return s_id 主键
     */
    public Integer getsId() {
        return sId;
    }

    /**
     * 主键
     * @param sId 主键
     */
    public void setsId(Integer sId) {
        this.sId = sId;
    }

    /**
     * 姓名
     * @return user_name 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 姓名
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 性别
     * @return s_age 性别
     */
    public String getsAge() {
        return sAge;
    }

    /**
     * 性别
     * @param sAge 性别
     */
    public void setsAge(String sAge) {
        this.sAge = sAge == null ? null : sAge.trim();
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
        UserStudy other = (UserStudy) that;
        return (this.getsId() == null ? other.getsId() == null : this.getsId().equals(other.getsId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getsAge() == null ? other.getsAge() == null : this.getsAge().equals(other.getsAge()));
    }

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getsId() == null) ? 0 : getsId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getsAge() == null) ? 0 : getsAge().hashCode());
        return result;
    }
}