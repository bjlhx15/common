package com.lhx.spring.mapper.auto;

import com.lhx.spring.entiry.auto.User;
import com.lhx.spring.entiry.auto.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    long countByExample(UserExample example);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int deleteByExample(UserExample example);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int insert(User record);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int insertSelective(User record);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    List<User> selectByExample(UserExample example);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    User selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbg.generated 2018-07-02 23:54:56
     */
    int updateByPrimaryKey(User record);
}