package com.pd.server.main.dto;

        import java.util.Date;
        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonFormat;
        import com.pd.server.main.domain.DrvExam;

public class DrvExamlogDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 姓名
    */
    private String xm;

    /**
    * 身份证明号码
    */
    private String sfzmhm;

    /**
    * 手机号
    */
    private String sjh;

    /**
    * 处罚编号
    */
    private String cfbh;

    /**
    * 答题科目
    */
    private String dtkm;

    /**
    * 开始时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date kssj;

    /**
    * 结束时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date jssj;

    /**
    * 所得分数
    */
    private Integer sdfs;

    /**
    * 是否及格1及格0不及格
    */
    private String sfjg;

    /**
    * 微信openid
    */
    private String wxid;

    private List<Object> answerList;

    private List<String> tmIdList;

    private List<Object> questionList;

    private Integer correctCount;

    private Integer errorCount;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getXm() {
    return xm;
    }

    public void setXm(String xm) {
    this.xm = xm;
    }

    public String getSfzmhm() {
    return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
    this.sfzmhm = sfzmhm;
    }

    public String getSjh() {
    return sjh;
    }

    public void setSjh(String sjh) {
    this.sjh = sjh;
    }

    public String getCfbh() {
    return cfbh;
    }

    public void setCfbh(String cfbh) {
    this.cfbh = cfbh;
    }

    public String getDtkm() {
    return dtkm;
    }

    public void setDtkm(String dtkm) {
    this.dtkm = dtkm;
    }

    public Date getKssj() {
    return kssj;
    }

    public void setKssj(Date kssj) {
    this.kssj = kssj;
    }

    public Date getJssj() {
    return jssj;
    }

    public void setJssj(Date jssj) {
    this.jssj = jssj;
    }

    public Integer getSdfs() {
    return sdfs;
    }

    public void setSdfs(Integer sdfs) {
    this.sdfs = sdfs;
    }

    public String getSfjg() {
    return sfjg;
    }

    public void setSfjg(String sfjg) {
    this.sfjg = sfjg;
    }

    public String getWxid() {
    return wxid;
    }

    public void setWxid(String wxid) {
    this.wxid = wxid;
    }

    public List<Object> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Object> answerList) {
        this.answerList = answerList;
    }

    public List<String> getTmIdList() {
        return tmIdList;
    }

    public void setTmIdList(List<String> tmIdList) {
        this.tmIdList = tmIdList;
    }

    public List<Object> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Object> questionList) {
        this.questionList = questionList;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    @Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", xm=").append(xm);
    sb.append(", sfzmhm=").append(sfzmhm);
    sb.append(", sjh=").append(sjh);
    sb.append(", cfbh=").append(cfbh);
    sb.append(", dtkm=").append(dtkm);
    sb.append(", kssj=").append(kssj);
    sb.append(", jssj=").append(jssj);
    sb.append(", sdfs=").append(sdfs);
    sb.append(", sfjg=").append(sfjg);
    sb.append(", wxid=").append(wxid);
sb.append("]");
return sb.toString();
}

}