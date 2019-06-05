package com.lhx.spring.mapper.auto;

import com.lhx.spring.entiry.auto.User;

public interface UserMapper {
    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int insert(User record);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int insertSelective(User record);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    User selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbg.generated 2018-07-03 22:13:48
     */
    int updateByPrimaryKey(User record);
}