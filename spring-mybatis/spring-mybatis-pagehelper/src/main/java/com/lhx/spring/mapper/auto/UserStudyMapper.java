package com.lhx.spring.mapper.auto;

import com.lhx.spring.entiry.auto.UserStudy;

public interface UserStudyMapper {
    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int deleteByPrimaryKey(Integer sId);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int insert(UserStudy record);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int insertSelective(UserStudy record);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    UserStudy selectByPrimaryKey(Integer sId);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int updateByPrimaryKeySelective(UserStudy record);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int updateByPrimaryKey(UserStudy record);
}