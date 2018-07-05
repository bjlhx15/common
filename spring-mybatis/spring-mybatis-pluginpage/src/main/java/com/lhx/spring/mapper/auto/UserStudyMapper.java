package com.lhx.spring.mapper.auto;

import com.lhx.spring.entiry.auto.UserStudy;

public interface UserStudyMapper {
    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int deleteByPrimaryKey(Integer sId);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int insert(UserStudy record);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int insertSelective(UserStudy record);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    UserStudy selectByPrimaryKey(Integer sId);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int updateByPrimaryKeySelective(UserStudy record);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int updateByPrimaryKey(UserStudy record);
}