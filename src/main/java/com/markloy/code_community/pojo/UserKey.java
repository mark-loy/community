package com.markloy.code_community.pojo;

public class UserKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.Host
     *
     * @mbg.generated Mon Sep 14 19:24:22 CST 2020
     */
    private String host;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.User
     *
     * @mbg.generated Mon Sep 14 19:24:22 CST 2020
     */
    private String user;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.Host
     *
     * @return the value of user.Host
     *
     * @mbg.generated Mon Sep 14 19:24:22 CST 2020
     */
    public String getHost() {
        return host;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.Host
     *
     * @param host the value for user.Host
     *
     * @mbg.generated Mon Sep 14 19:24:22 CST 2020
     */
    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.User
     *
     * @return the value of user.User
     *
     * @mbg.generated Mon Sep 14 19:24:22 CST 2020
     */
    public String getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.User
     *
     * @param user the value for user.User
     *
     * @mbg.generated Mon Sep 14 19:24:22 CST 2020
     */
    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }
}