package com.markloy.code_community.pojo;

public class UserTag {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_TAG.ID
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_TAG.TAG_NAME
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    private String tagName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_TAG.TYPE
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_TAG.USER_ID
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_TAG.GMT_CREATE
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_TAG.GMT_MODIFIED
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    private Long gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_TAG.ID
     *
     * @return the value of USER_TAG.ID
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_TAG.ID
     *
     * @param id the value for USER_TAG.ID
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_TAG.TAG_NAME
     *
     * @return the value of USER_TAG.TAG_NAME
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_TAG.TAG_NAME
     *
     * @param tagName the value for USER_TAG.TAG_NAME
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_TAG.TYPE
     *
     * @return the value of USER_TAG.TYPE
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_TAG.TYPE
     *
     * @param type the value for USER_TAG.TYPE
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_TAG.USER_ID
     *
     * @return the value of USER_TAG.USER_ID
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_TAG.USER_ID
     *
     * @param userId the value for USER_TAG.USER_ID
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_TAG.GMT_CREATE
     *
     * @return the value of USER_TAG.GMT_CREATE
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_TAG.GMT_CREATE
     *
     * @param gmtCreate the value for USER_TAG.GMT_CREATE
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_TAG.GMT_MODIFIED
     *
     * @return the value of USER_TAG.GMT_MODIFIED
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_TAG.GMT_MODIFIED
     *
     * @param gmtModified the value for USER_TAG.GMT_MODIFIED
     *
     * @mbg.generated Sat Sep 12 15:59:19 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}