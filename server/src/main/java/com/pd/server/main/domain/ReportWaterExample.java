package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportWaterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReportWaterExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSfzmhmIsNull() {
            addCriterion("sfzmhm is null");
            return (Criteria) this;
        }

        public Criteria andSfzmhmIsNotNull() {
            addCriterion("sfzmhm is not null");
            return (Criteria) this;
        }

        public Criteria andSfzmhmEqualTo(String value) {
            addCriterion("sfzmhm =", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotEqualTo(String value) {
            addCriterion("sfzmhm <>", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmGreaterThan(String value) {
            addCriterion("sfzmhm >", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmGreaterThanOrEqualTo(String value) {
            addCriterion("sfzmhm >=", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmLessThan(String value) {
            addCriterion("sfzmhm <", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmLessThanOrEqualTo(String value) {
            addCriterion("sfzmhm <=", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmLike(String value) {
            addCriterion("sfzmhm like", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotLike(String value) {
            addCriterion("sfzmhm not like", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmIn(List<String> values) {
            addCriterion("sfzmhm in", values, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotIn(List<String> values) {
            addCriterion("sfzmhm not in", values, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmBetween(String value1, String value2) {
            addCriterion("sfzmhm between", value1, value2, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotBetween(String value1, String value2) {
            addCriterion("sfzmhm not between", value1, value2, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion(" DATE_FORMAT(create_time,'%Y-%m-%d') >=", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d')  <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeIsNull() {
            addCriterion("sys_org_code is null");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeIsNotNull() {
            addCriterion("sys_org_code is not null");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeEqualTo(String value) {
            addCriterion("sys_org_code =", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotEqualTo(String value) {
            addCriterion("sys_org_code <>", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeGreaterThan(String value) {
            addCriterion("sys_org_code >", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_org_code >=", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeLessThan(String value) {
            addCriterion("sys_org_code <", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("sys_org_code <=", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeLike(String value) {
            addCriterion("sys_org_code like", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotLike(String value) {
            addCriterion("sys_org_code not like", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeIn(List<String> values) {
            addCriterion("sys_org_code in", values, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotIn(List<String> values) {
            addCriterion("sys_org_code not in", values, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeBetween(String value1, String value2) {
            addCriterion("sys_org_code between", value1, value2, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotBetween(String value1, String value2) {
            addCriterion("sys_org_code not between", value1, value2, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andZsddIsNull() {
            addCriterion("zsdd is null");
            return (Criteria) this;
        }

        public Criteria andZsddIsNotNull() {
            addCriterion("zsdd is not null");
            return (Criteria) this;
        }

        public Criteria andZsddEqualTo(String value) {
            addCriterion("zsdd =", value, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddNotEqualTo(String value) {
            addCriterion("zsdd <>", value, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddGreaterThan(String value) {
            addCriterion("zsdd >", value, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddGreaterThanOrEqualTo(String value) {
            addCriterion("zsdd >=", value, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddLessThan(String value) {
            addCriterion("zsdd <", value, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddLessThanOrEqualTo(String value) {
            addCriterion("zsdd <=", value, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddLike(String value) {
            addCriterion("zsdd like", value, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddNotLike(String value) {
            addCriterion("zsdd not like", value, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddIn(List<String> values) {
            addCriterion("zsdd in", values, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddNotIn(List<String> values) {
            addCriterion("zsdd not in", values, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddBetween(String value1, String value2) {
            addCriterion("zsdd between", value1, value2, "zsdd");
            return (Criteria) this;
        }

        public Criteria andZsddNotBetween(String value1, String value2) {
            addCriterion("zsdd not between", value1, value2, "zsdd");
            return (Criteria) this;
        }

        public Criteria andGpsIsNull() {
            addCriterion("gps is null");
            return (Criteria) this;
        }

        public Criteria andGpsIsNotNull() {
            addCriterion("gps is not null");
            return (Criteria) this;
        }

        public Criteria andGpsEqualTo(String value) {
            addCriterion("gps =", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotEqualTo(String value) {
            addCriterion("gps <>", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsGreaterThan(String value) {
            addCriterion("gps >", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsGreaterThanOrEqualTo(String value) {
            addCriterion("gps >=", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsLessThan(String value) {
            addCriterion("gps <", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsLessThanOrEqualTo(String value) {
            addCriterion("gps <=", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsLike(String value) {
            addCriterion("gps like", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotLike(String value) {
            addCriterion("gps not like", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsIn(List<String> values) {
            addCriterion("gps in", values, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotIn(List<String> values) {
            addCriterion("gps not in", values, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsBetween(String value1, String value2) {
            addCriterion("gps between", value1, value2, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotBetween(String value1, String value2) {
            addCriterion("gps not between", value1, value2, "gps");
            return (Criteria) this;
        }

        public Criteria andZsddxxIsNull() {
            addCriterion("zsddxx is null");
            return (Criteria) this;
        }

        public Criteria andZsddxxIsNotNull() {
            addCriterion("zsddxx is not null");
            return (Criteria) this;
        }

        public Criteria andZsddxxEqualTo(String value) {
            addCriterion("zsddxx =", value, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxNotEqualTo(String value) {
            addCriterion("zsddxx <>", value, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxGreaterThan(String value) {
            addCriterion("zsddxx >", value, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxGreaterThanOrEqualTo(String value) {
            addCriterion("zsddxx >=", value, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxLessThan(String value) {
            addCriterion("zsddxx <", value, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxLessThanOrEqualTo(String value) {
            addCriterion("zsddxx <=", value, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxLike(String value) {
            addCriterion("zsddxx like", value, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxNotLike(String value) {
            addCriterion("zsddxx not like", value, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxIn(List<String> values) {
            addCriterion("zsddxx in", values, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxNotIn(List<String> values) {
            addCriterion("zsddxx not in", values, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxBetween(String value1, String value2) {
            addCriterion("zsddxx between", value1, value2, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andZsddxxNotBetween(String value1, String value2) {
            addCriterion("zsddxx not between", value1, value2, "zsddxx");
            return (Criteria) this;
        }

        public Criteria andSbrIsNull() {
            addCriterion("sbr is null");
            return (Criteria) this;
        }

        public Criteria andSbrIsNotNull() {
            addCriterion("sbr is not null");
            return (Criteria) this;
        }

        public Criteria andSbrEqualTo(String value) {
            addCriterion("sbr =", value, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrNotEqualTo(String value) {
            addCriterion("sbr <>", value, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrGreaterThan(String value) {
            addCriterion("sbr >", value, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrGreaterThanOrEqualTo(String value) {
            addCriterion("sbr >=", value, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrLessThan(String value) {
            addCriterion("sbr <", value, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrLessThanOrEqualTo(String value) {
            addCriterion("sbr <=", value, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrLike(String value) {
            addCriterion("sbr like", value, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrNotLike(String value) {
            addCriterion("sbr not like", value, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrIn(List<String> values) {
            addCriterion("sbr in", values, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrNotIn(List<String> values) {
            addCriterion("sbr not in", values, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrBetween(String value1, String value2) {
            addCriterion("sbr between", value1, value2, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrNotBetween(String value1, String value2) {
            addCriterion("sbr not between", value1, value2, "sbr");
            return (Criteria) this;
        }

        public Criteria andSbrsjhIsNull() {
            addCriterion("sbrsjh is null");
            return (Criteria) this;
        }

        public Criteria andSbrsjhIsNotNull() {
            addCriterion("sbrsjh is not null");
            return (Criteria) this;
        }

        public Criteria andSbrsjhEqualTo(String value) {
            addCriterion("sbrsjh =", value, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhNotEqualTo(String value) {
            addCriterion("sbrsjh <>", value, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhGreaterThan(String value) {
            addCriterion("sbrsjh >", value, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhGreaterThanOrEqualTo(String value) {
            addCriterion("sbrsjh >=", value, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhLessThan(String value) {
            addCriterion("sbrsjh <", value, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhLessThanOrEqualTo(String value) {
            addCriterion("sbrsjh <=", value, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhLike(String value) {
            addCriterion("sbrsjh like", value, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhNotLike(String value) {
            addCriterion("sbrsjh not like", value, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhIn(List<String> values) {
            addCriterion("sbrsjh in", values, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhNotIn(List<String> values) {
            addCriterion("sbrsjh not in", values, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhBetween(String value1, String value2) {
            addCriterion("sbrsjh between", value1, value2, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbrsjhNotBetween(String value1, String value2) {
            addCriterion("sbrsjh not between", value1, value2, "sbrsjh");
            return (Criteria) this;
        }

        public Criteria andSbsmIsNull() {
            addCriterion("sbsm is null");
            return (Criteria) this;
        }

        public Criteria andSbsmIsNotNull() {
            addCriterion("sbsm is not null");
            return (Criteria) this;
        }

        public Criteria andSbsmEqualTo(String value) {
            addCriterion("sbsm =", value, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmNotEqualTo(String value) {
            addCriterion("sbsm <>", value, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmGreaterThan(String value) {
            addCriterion("sbsm >", value, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmGreaterThanOrEqualTo(String value) {
            addCriterion("sbsm >=", value, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmLessThan(String value) {
            addCriterion("sbsm <", value, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmLessThanOrEqualTo(String value) {
            addCriterion("sbsm <=", value, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmLike(String value) {
            addCriterion("sbsm like", value, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmNotLike(String value) {
            addCriterion("sbsm not like", value, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmIn(List<String> values) {
            addCriterion("sbsm in", values, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmNotIn(List<String> values) {
            addCriterion("sbsm not in", values, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmBetween(String value1, String value2) {
            addCriterion("sbsm between", value1, value2, "sbsm");
            return (Criteria) this;
        }

        public Criteria andSbsmNotBetween(String value1, String value2) {
            addCriterion("sbsm not between", value1, value2, "sbsm");
            return (Criteria) this;
        }

        public Criteria andShrIsNull() {
            addCriterion("shr is null");
            return (Criteria) this;
        }

        public Criteria andShrIsNotNull() {
            addCriterion("shr is not null");
            return (Criteria) this;
        }

        public Criteria andShrEqualTo(String value) {
            addCriterion("shr =", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrNotEqualTo(String value) {
            addCriterion("shr <>", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrGreaterThan(String value) {
            addCriterion("shr >", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrGreaterThanOrEqualTo(String value) {
            addCriterion("shr >=", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrLessThan(String value) {
            addCriterion("shr <", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrLessThanOrEqualTo(String value) {
            addCriterion("shr <=", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrLike(String value) {
            addCriterion("shr like", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrNotLike(String value) {
            addCriterion("shr not like", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrIn(List<String> values) {
            addCriterion("shr in", values, "shr");
            return (Criteria) this;
        }

        public Criteria andShrNotIn(List<String> values) {
            addCriterion("shr not in", values, "shr");
            return (Criteria) this;
        }

        public Criteria andShrBetween(String value1, String value2) {
            addCriterion("shr between", value1, value2, "shr");
            return (Criteria) this;
        }

        public Criteria andShrNotBetween(String value1, String value2) {
            addCriterion("shr not between", value1, value2, "shr");
            return (Criteria) this;
        }

        public Criteria andShsjIsNull() {
            addCriterion("shsj is null");
            return (Criteria) this;
        }

        public Criteria andShsjIsNotNull() {
            addCriterion("shsj is not null");
            return (Criteria) this;
        }

        public Criteria andShsjEqualTo(Date value) {
            addCriterion("shsj =", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjNotEqualTo(Date value) {
            addCriterion("shsj <>", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjGreaterThan(Date value) {
            addCriterion("shsj >", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjGreaterThanOrEqualTo(Date value) {
            addCriterion("shsj >=", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjLessThan(Date value) {
            addCriterion("shsj <", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjLessThanOrEqualTo(Date value) {
            addCriterion("shsj <=", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjIn(List<Date> values) {
            addCriterion("shsj in", values, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjNotIn(List<Date> values) {
            addCriterion("shsj not in", values, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjBetween(Date value1, Date value2) {
            addCriterion("shsj between", value1, value2, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjNotBetween(Date value1, Date value2) {
            addCriterion("shsj not between", value1, value2, "shsj");
            return (Criteria) this;
        }

        public Criteria andClyjIsNull() {
            addCriterion("clyj is null");
            return (Criteria) this;
        }

        public Criteria andClyjIsNotNull() {
            addCriterion("clyj is not null");
            return (Criteria) this;
        }

        public Criteria andClyjEqualTo(String value) {
            addCriterion("clyj =", value, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjNotEqualTo(String value) {
            addCriterion("clyj <>", value, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjGreaterThan(String value) {
            addCriterion("clyj >", value, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjGreaterThanOrEqualTo(String value) {
            addCriterion("clyj >=", value, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjLessThan(String value) {
            addCriterion("clyj <", value, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjLessThanOrEqualTo(String value) {
            addCriterion("clyj <=", value, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjLike(String value) {
            addCriterion("clyj like", value, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjNotLike(String value) {
            addCriterion("clyj not like", value, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjIn(List<String> values) {
            addCriterion("clyj in", values, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjNotIn(List<String> values) {
            addCriterion("clyj not in", values, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjBetween(String value1, String value2) {
            addCriterion("clyj between", value1, value2, "clyj");
            return (Criteria) this;
        }

        public Criteria andClyjNotBetween(String value1, String value2) {
            addCriterion("clyj not between", value1, value2, "clyj");
            return (Criteria) this;
        }

        public Criteria andZtIsNull() {
            addCriterion("zt is null");
            return (Criteria) this;
        }

        public Criteria andZtIsNotNull() {
            addCriterion("zt is not null");
            return (Criteria) this;
        }

        public Criteria andZtEqualTo(String value) {
            addCriterion("zt =", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotEqualTo(String value) {
            addCriterion("zt <>", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThan(String value) {
            addCriterion("zt >", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThanOrEqualTo(String value) {
            addCriterion("zt >=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThan(String value) {
            addCriterion("zt <", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThanOrEqualTo(String value) {
            addCriterion("zt <=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLike(String value) {
            addCriterion("zt like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotLike(String value) {
            addCriterion("zt not like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtIn(List<String> values) {
            addCriterion("zt in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotIn(List<String> values) {
            addCriterion("zt not in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtBetween(String value1, String value2) {
            addCriterion("zt between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotBetween(String value1, String value2) {
            addCriterion("zt not between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andBzIsNull() {
            addCriterion("bz is null");
            return (Criteria) this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("bz is not null");
            return (Criteria) this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("bz =", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("bz <>", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("bz >", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("bz >=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("bz <", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("bz <=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLike(String value) {
            addCriterion("bz like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotLike(String value) {
            addCriterion("bz not like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzIn(List<String> values) {
            addCriterion("bz in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotIn(List<String> values) {
            addCriterion("bz not in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("bz between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("bz not between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andLshIsNull() {
            addCriterion("lsh is null");
            return (Criteria) this;
        }

        public Criteria andLshIsNotNull() {
            addCriterion("lsh is not null");
            return (Criteria) this;
        }

        public Criteria andLshEqualTo(String value) {
            addCriterion("lsh =", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshNotEqualTo(String value) {
            addCriterion("lsh <>", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshGreaterThan(String value) {
            addCriterion("lsh >", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshGreaterThanOrEqualTo(String value) {
            addCriterion("lsh >=", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshLessThan(String value) {
            addCriterion("lsh <", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshLessThanOrEqualTo(String value) {
            addCriterion("lsh <=", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshLike(String value) {
            addCriterion("lsh like", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshNotLike(String value) {
            addCriterion("lsh not like", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshIn(List<String> values) {
            addCriterion("lsh in", values, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshNotIn(List<String> values) {
            addCriterion("lsh not in", values, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshBetween(String value1, String value2) {
            addCriterion("lsh between", value1, value2, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshNotBetween(String value1, String value2) {
            addCriterion("lsh not between", value1, value2, "lsh");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}