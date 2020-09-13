package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.UserTag;
import com.markloy.code_community.pojo.UserTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    long countByExample(UserTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    int deleteByExample(UserTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    int insert(UserTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    int insertSelective(UserTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    List<UserTag> selectByExampleWithRowbounds(UserTagExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    List<UserTag> selectByExample(UserTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    UserTag selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserTag record, @Param("example") UserTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    int updateByExample(@Param("record") UserTag record, @Param("example") UserTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    int updateByPrimaryKeySelective(UserTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_TAG
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    int updateByPrimaryKey(UserTag record);
}