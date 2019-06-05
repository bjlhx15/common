package com.lhx.spring.mapper.auto;

import com.lhx.spring.entiry.auto.UserStudy;
import com.lhx.spring.entiry.auto.UserStudyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserStudyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_study
     *
     * @mbg.generated
     */
    long countByExample(UserStudyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_study
     *
     * @mbg.generated
     */
    int deleteByExample(UserStudyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_study
     *
     * @mbg.generated
     */
    int insert(UserStudy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_study
     *
     * @mbg.generated
     */
    int insertSelective(UserStudy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_study
     *
     * @mbg.generated
     */
    List<UserStudy> selectByExample(UserStudyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_study
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") UserStudy record, @Param("example") UserStudyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_study
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") UserStudy record, @Param("example") UserStudyExample example);
}