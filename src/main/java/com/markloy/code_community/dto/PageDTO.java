package com.markloy.code_community.dto;

import jdk.nashorn.internal.ir.IfNode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    //当前页
    private int currentPage;
    //总页数
    private int totalPage;
    //分页数字按钮数
    private List<Integer> pageCount = new ArrayList<>();
    //下一页按钮
    private boolean showNext;
    //上一页按钮
    private boolean showPrevious;
    //第一页按钮
    private boolean showFirst;
    //最后一页按钮
    private boolean showEnd;
    //当前页的数据
    private List<QuestionDTO> questionDTO = new ArrayList<>();


    public void computer(Integer currentPage, Integer totalCount, Integer size) {
        int total = totalCount % size;
        //计算总页数
        if (total == 0) {
            this.totalPage = totalCount / size;
        } else {
            this.totalPage = totalCount / size + 1;
        }

        this.currentPage = currentPage;
        //控制输入的页数，不能小于1，或大于总页数
        if (currentPage < 1) {
            this.currentPage = 1;
        }

        if (currentPage > this.totalPage) {
            this.currentPage = this.totalPage;
        }

        //判断是否显示上一页
        this.showPrevious = this.currentPage > 1;

        //判断是否显示下一页
        this.showNext = this.currentPage != totalPage;

        pageCount.add(this.currentPage);
        //往pageCount中添加数字按钮
        for (int i=1; i <= 3 ; i++) {

            if (this.currentPage + i <= totalPage) {
                pageCount.add(this.currentPage + i);
            }

            if (this.currentPage - i >= 1) {
                pageCount.add(0,this.currentPage - i);
            }
        }

        //查看数字按钮中是否包含首页
        this.showFirst = ! pageCount.contains(1);

        //查看数字按钮中是否包含尾页
        this.showEnd = ! pageCount.contains(totalPage);
    }



}
