package com.lhx.spring.entiry.auto;

public class UserStudy {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_study.s_id
     *
     * @mbg.generated
     */
    private Integer sId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_study.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_study.s_age
     *
     * @mbg.generated
     */
    private String sAge;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_study.s_id
     *
     * @return the value of user_study.s_id
     *
     * @mbg.generated
     */
    public Integer getsId() {
        return sId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_study.s_id
     *
     * @param sId the value for user_study.s_id
     *
     * @mbg.generated
     */
    public void setsId(Integer sId) {
        this.sId = sId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_study.user_name
     *
     * @return the value of user_study.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_study.user_name
     *
     * @param userName the value for user_study.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_study.s_age
     *
     * @return the value of user_study.s_age
     *
     * @mbg.generated
     */
    public String getsAge() {
        return sAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_study.s_age
     *
     * @param sAge the value for user_study.s_age
     *
     * @mbg.generated
     */
    public void setsAge(String sAge) {
        this.sAge = sAge == null ? null : sAge.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_study
     *
     * @mbg.generated
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
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_study
     *
     * @mbg.generated
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