package com.pd.server.main.dto;



public class SectionPageDto  extends  PageDto{


    private String courseId;

    /**
    * 大章|chapter.id
    */
    private String chapterId;



    public String getCourseId() {
    return courseId;
    }

    public void setCourseId(String courseId) {
    this.courseId = courseId;
    }

    public String getChapterId() {
    return chapterId;
    }

    public void setChapterId(String chapterId) {
    this.chapterId = chapterId;
    }




}