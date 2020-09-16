package com.markloy.code_community.pojo;

public class CountCommentRecord {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column count_comment_record.id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column count_comment_record.user_id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column count_comment_record.question_id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    private Long questionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column count_comment_record.comment_id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    private Long commentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column count_comment_record.comment_like_check
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    private Integer commentLikeCheck;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column count_comment_record.gmt_create
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    private Long gmtCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column count_comment_record.id
     *
     * @return the value of count_comment_record.id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column count_comment_record.id
     *
     * @param id the value for count_comment_record.id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column count_comment_record.user_id
     *
     * @return the value of count_comment_record.user_id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column count_comment_record.user_id
     *
     * @param userId the value for count_comment_record.user_id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column count_comment_record.question_id
     *
     * @return the value of count_comment_record.question_id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column count_comment_record.question_id
     *
     * @param questionId the value for count_comment_record.question_id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column count_comment_record.comment_id
     *
     * @return the value of count_comment_record.comment_id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column count_comment_record.comment_id
     *
     * @param commentId the value for count_comment_record.comment_id
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column count_comment_record.comment_like_check
     *
     * @return the value of count_comment_record.comment_like_check
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public Integer getCommentLikeCheck() {
        return commentLikeCheck;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column count_comment_record.comment_like_check
     *
     * @param commentLikeCheck the value for count_comment_record.comment_like_check
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public void setCommentLikeCheck(Integer commentLikeCheck) {
        this.commentLikeCheck = commentLikeCheck;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column count_comment_record.gmt_create
     *
     * @return the value of count_comment_record.gmt_create
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column count_comment_record.gmt_create
     *
     * @param gmtCreate the value for count_comment_record.gmt_create
     *
     * @mbg.generated Tue Sep 15 21:13:48 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}