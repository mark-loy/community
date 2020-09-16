package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.CountRecord;
import com.markloy.code_community.pojo.CountRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CountRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    long countByExample(CountRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int deleteByExample(CountRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int insert(CountRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int insertSelective(CountRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    List<CountRecord> selectByExampleWithRowbounds(CountRecordExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    List<CountRecord> selectByExample(CountRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    CountRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int updateByExampleSelective(@Param("record") CountRecord record, @Param("example") CountRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int updateByExample(@Param("record") CountRecord record, @Param("example") CountRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int updateByPrimaryKeySelective(CountRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int updateByPrimaryKey(CountRecord record);
}