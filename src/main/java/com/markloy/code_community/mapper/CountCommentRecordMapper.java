package com.markloy.code_community.mapper;

import com.markloy.code_community.pojo.CountCommentRecord;
import com.markloy.code_community.pojo.CountCommentRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CountCommentRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    long countByExample(CountCommentRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int deleteByExample(CountCommentRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int insert(CountCommentRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int insertSelective(CountCommentRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    List<CountCommentRecord> selectByExampleWithRowbounds(CountCommentRecordExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    List<CountCommentRecord> selectByExample(CountCommentRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    CountCommentRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int updateByExampleSelective(@Param("record") CountCommentRecord record, @Param("example") CountCommentRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int updateByExample(@Param("record") CountCommentRecord record, @Param("example") CountCommentRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int updateByPrimaryKeySelective(CountCommentRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_comment_record
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    int updateByPrimaryKey(CountCommentRecord record);
}