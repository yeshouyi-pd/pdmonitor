package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehScrewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehScrewExample() {
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

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
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
        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d')  <=", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d') <=", value, "createTime");
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

        public Criteria andHphmIsNull() {
            addCriterion("hphm is null");
            return (Criteria) this;
        }

        public Criteria andHphmIsNotNull() {
            addCriterion("hphm is not null");
            return (Criteria) this;
        }

        public Criteria andHphmEqualTo(String value) {
            addCriterion("hphm =", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotEqualTo(String value) {
            addCriterion("hphm <>", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmGreaterThan(String value) {
            addCriterion("hphm >", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmGreaterThanOrEqualTo(String value) {
            addCriterion("hphm >=", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmLessThan(String value) {
            addCriterion("hphm <", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmLessThanOrEqualTo(String value) {
            addCriterion("hphm <=", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmLike(String value) {
            addCriterion("hphm like", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotLike(String value) {
            addCriterion("hphm not like", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmIn(List<String> values) {
            addCriterion("hphm in", values, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotIn(List<String> values) {
            addCriterion("hphm not in", values, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmBetween(String value1, String value2) {
            addCriterion("hphm between", value1, value2, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotBetween(String value1, String value2) {
            addCriterion("hphm not between", value1, value2, "hphm");
            return (Criteria) this;
        }

        public Criteria andSqyyIsNull() {
            addCriterion("sqyy is null");
            return (Criteria) this;
        }

        public Criteria andSqyyIsNotNull() {
            addCriterion("sqyy is not null");
            return (Criteria) this;
        }

        public Criteria andSqyyEqualTo(String value) {
            addCriterion("sqyy =", value, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyNotEqualTo(String value) {
            addCriterion("sqyy <>", value, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyGreaterThan(String value) {
            addCriterion("sqyy >", value, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyGreaterThanOrEqualTo(String value) {
            addCriterion("sqyy >=", value, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyLessThan(String value) {
            addCriterion("sqyy <", value, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyLessThanOrEqualTo(String value) {
            addCriterion("sqyy <=", value, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyLike(String value) {
            addCriterion("sqyy like", value, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyNotLike(String value) {
            addCriterion("sqyy not like", value, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyIn(List<String> values) {
            addCriterion("sqyy in", values, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyNotIn(List<String> values) {
            addCriterion("sqyy not in", values, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyBetween(String value1, String value2) {
            addCriterion("sqyy between", value1, value2, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSqyyNotBetween(String value1, String value2) {
            addCriterion("sqyy not between", value1, value2, "sqyy");
            return (Criteria) this;
        }

        public Criteria andSlslIsNull() {
            addCriterion("slsl is null");
            return (Criteria) this;
        }

        public Criteria andSlslIsNotNull() {
            addCriterion("slsl is not null");
            return (Criteria) this;
        }

        public Criteria andSlslEqualTo(String value) {
            addCriterion("slsl =", value, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslNotEqualTo(String value) {
            addCriterion("slsl <>", value, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslGreaterThan(String value) {
            addCriterion("slsl >", value, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslGreaterThanOrEqualTo(String value) {
            addCriterion("slsl >=", value, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslLessThan(String value) {
            addCriterion("slsl <", value, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslLessThanOrEqualTo(String value) {
            addCriterion("slsl <=", value, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslLike(String value) {
            addCriterion("slsl like", value, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslNotLike(String value) {
            addCriterion("slsl not like", value, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslIn(List<String> values) {
            addCriterion("slsl in", values, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslNotIn(List<String> values) {
            addCriterion("slsl not in", values, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslBetween(String value1, String value2) {
            addCriterion("slsl between", value1, value2, "slsl");
            return (Criteria) this;
        }

        public Criteria andSlslNotBetween(String value1, String value2) {
            addCriterion("slsl not between", value1, value2, "slsl");
            return (Criteria) this;
        }

        public Criteria andSjrIsNull() {
            addCriterion("sjr is null");
            return (Criteria) this;
        }

        public Criteria andSjrIsNotNull() {
            addCriterion("sjr is not null");
            return (Criteria) this;
        }

        public Criteria andSjrEqualTo(String value) {
            addCriterion("sjr =", value, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrNotEqualTo(String value) {
            addCriterion("sjr <>", value, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrGreaterThan(String value) {
            addCriterion("sjr >", value, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrGreaterThanOrEqualTo(String value) {
            addCriterion("sjr >=", value, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrLessThan(String value) {
            addCriterion("sjr <", value, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrLessThanOrEqualTo(String value) {
            addCriterion("sjr <=", value, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrLike(String value) {
            addCriterion("sjr like", value, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrNotLike(String value) {
            addCriterion("sjr not like", value, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrIn(List<String> values) {
            addCriterion("sjr in", values, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrNotIn(List<String> values) {
            addCriterion("sjr not in", values, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrBetween(String value1, String value2) {
            addCriterion("sjr between", value1, value2, "sjr");
            return (Criteria) this;
        }

        public Criteria andSjrNotBetween(String value1, String value2) {
            addCriterion("sjr not between", value1, value2, "sjr");
            return (Criteria) this;
        }

        public Criteria andLxdhIsNull() {
            addCriterion("lxdh is null");
            return (Criteria) this;
        }

        public Criteria andLxdhIsNotNull() {
            addCriterion("lxdh is not null");
            return (Criteria) this;
        }

        public Criteria andLxdhEqualTo(String value) {
            addCriterion("lxdh =", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhNotEqualTo(String value) {
            addCriterion("lxdh <>", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhGreaterThan(String value) {
            addCriterion("lxdh >", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhGreaterThanOrEqualTo(String value) {
            addCriterion("lxdh >=", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhLessThan(String value) {
            addCriterion("lxdh <", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhLessThanOrEqualTo(String value) {
            addCriterion("lxdh <=", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhLike(String value) {
            addCriterion("lxdh like", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhNotLike(String value) {
            addCriterion("lxdh not like", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhIn(List<String> values) {
            addCriterion("lxdh in", values, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhNotIn(List<String> values) {
            addCriterion("lxdh not in", values, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhBetween(String value1, String value2) {
            addCriterion("lxdh between", value1, value2, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhNotBetween(String value1, String value2) {
            addCriterion("lxdh not between", value1, value2, "lxdh");
            return (Criteria) this;
        }

        public Criteria andSfIsNull() {
            addCriterion("sf is null");
            return (Criteria) this;
        }

        public Criteria andSfIsNotNull() {
            addCriterion("sf is not null");
            return (Criteria) this;
        }

        public Criteria andSfEqualTo(String value) {
            addCriterion("sf =", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotEqualTo(String value) {
            addCriterion("sf <>", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfGreaterThan(String value) {
            addCriterion("sf >", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfGreaterThanOrEqualTo(String value) {
            addCriterion("sf >=", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLessThan(String value) {
            addCriterion("sf <", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLessThanOrEqualTo(String value) {
            addCriterion("sf <=", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLike(String value) {
            addCriterion("sf like", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotLike(String value) {
            addCriterion("sf not like", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfIn(List<String> values) {
            addCriterion("sf in", values, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotIn(List<String> values) {
            addCriterion("sf not in", values, "sf");
            return (Criteria) this;
        }

        public Criteria andSfBetween(String value1, String value2) {
            addCriterion("sf between", value1, value2, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotBetween(String value1, String value2) {
            addCriterion("sf not between", value1, value2, "sf");
            return (Criteria) this;
        }

        public Criteria andCsIsNull() {
            addCriterion("cs is null");
            return (Criteria) this;
        }

        public Criteria andCsIsNotNull() {
            addCriterion("cs is not null");
            return (Criteria) this;
        }

        public Criteria andCsEqualTo(String value) {
            addCriterion("cs =", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotEqualTo(String value) {
            addCriterion("cs <>", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThan(String value) {
            addCriterion("cs >", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThanOrEqualTo(String value) {
            addCriterion("cs >=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThan(String value) {
            addCriterion("cs <", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThanOrEqualTo(String value) {
            addCriterion("cs <=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLike(String value) {
            addCriterion("cs like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotLike(String value) {
            addCriterion("cs not like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsIn(List<String> values) {
            addCriterion("cs in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotIn(List<String> values) {
            addCriterion("cs not in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsBetween(String value1, String value2) {
            addCriterion("cs between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotBetween(String value1, String value2) {
            addCriterion("cs not between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andQxIsNull() {
            addCriterion("qx is null");
            return (Criteria) this;
        }

        public Criteria andQxIsNotNull() {
            addCriterion("qx is not null");
            return (Criteria) this;
        }

        public Criteria andQxEqualTo(String value) {
            addCriterion("qx =", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxNotEqualTo(String value) {
            addCriterion("qx <>", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxGreaterThan(String value) {
            addCriterion("qx >", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxGreaterThanOrEqualTo(String value) {
            addCriterion("qx >=", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxLessThan(String value) {
            addCriterion("qx <", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxLessThanOrEqualTo(String value) {
            addCriterion("qx <=", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxLike(String value) {
            addCriterion("qx like", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxNotLike(String value) {
            addCriterion("qx not like", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxIn(List<String> values) {
            addCriterion("qx in", values, "qx");
            return (Criteria) this;
        }

        public Criteria andQxNotIn(List<String> values) {
            addCriterion("qx not in", values, "qx");
            return (Criteria) this;
        }

        public Criteria andQxBetween(String value1, String value2) {
            addCriterion("qx between", value1, value2, "qx");
            return (Criteria) this;
        }

        public Criteria andQxNotBetween(String value1, String value2) {
            addCriterion("qx not between", value1, value2, "qx");
            return (Criteria) this;
        }

        public Criteria andJddzIsNull() {
            addCriterion("jddz is null");
            return (Criteria) this;
        }

        public Criteria andJddzIsNotNull() {
            addCriterion("jddz is not null");
            return (Criteria) this;
        }

        public Criteria andJddzEqualTo(String value) {
            addCriterion("jddz =", value, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzNotEqualTo(String value) {
            addCriterion("jddz <>", value, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzGreaterThan(String value) {
            addCriterion("jddz >", value, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzGreaterThanOrEqualTo(String value) {
            addCriterion("jddz >=", value, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzLessThan(String value) {
            addCriterion("jddz <", value, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzLessThanOrEqualTo(String value) {
            addCriterion("jddz <=", value, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzLike(String value) {
            addCriterion("jddz like", value, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzNotLike(String value) {
            addCriterion("jddz not like", value, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzIn(List<String> values) {
            addCriterion("jddz in", values, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzNotIn(List<String> values) {
            addCriterion("jddz not in", values, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzBetween(String value1, String value2) {
            addCriterion("jddz between", value1, value2, "jddz");
            return (Criteria) this;
        }

        public Criteria andJddzNotBetween(String value1, String value2) {
            addCriterion("jddz not between", value1, value2, "jddz");
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

        public Criteria andShyyIsNull() {
            addCriterion("shyy is null");
            return (Criteria) this;
        }

        public Criteria andShyyIsNotNull() {
            addCriterion("shyy is not null");
            return (Criteria) this;
        }

        public Criteria andShyyEqualTo(String value) {
            addCriterion("shyy =", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyNotEqualTo(String value) {
            addCriterion("shyy <>", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyGreaterThan(String value) {
            addCriterion("shyy >", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyGreaterThanOrEqualTo(String value) {
            addCriterion("shyy >=", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyLessThan(String value) {
            addCriterion("shyy <", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyLessThanOrEqualTo(String value) {
            addCriterion("shyy <=", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyLike(String value) {
            addCriterion("shyy like", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyNotLike(String value) {
            addCriterion("shyy not like", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyIn(List<String> values) {
            addCriterion("shyy in", values, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyNotIn(List<String> values) {
            addCriterion("shyy not in", values, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyBetween(String value1, String value2) {
            addCriterion("shyy between", value1, value2, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyNotBetween(String value1, String value2) {
            addCriterion("shyy not between", value1, value2, "shyy");
            return (Criteria) this;
        }

        public Criteria andYwmcIsNull() {
            addCriterion("ywmc is null");
            return (Criteria) this;
        }

        public Criteria andYwmcIsNotNull() {
            addCriterion("ywmc is not null");
            return (Criteria) this;
        }

        public Criteria andYwmcEqualTo(String value) {
            addCriterion("ywmc =", value, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcNotEqualTo(String value) {
            addCriterion("ywmc <>", value, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcGreaterThan(String value) {
            addCriterion("ywmc >", value, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcGreaterThanOrEqualTo(String value) {
            addCriterion("ywmc >=", value, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcLessThan(String value) {
            addCriterion("ywmc <", value, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcLessThanOrEqualTo(String value) {
            addCriterion("ywmc <=", value, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcLike(String value) {
            addCriterion("ywmc like", value, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcNotLike(String value) {
            addCriterion("ywmc not like", value, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcIn(List<String> values) {
            addCriterion("ywmc in", values, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcNotIn(List<String> values) {
            addCriterion("ywmc not in", values, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcBetween(String value1, String value2) {
            addCriterion("ywmc between", value1, value2, "ywmc");
            return (Criteria) this;
        }

        public Criteria andYwmcNotBetween(String value1, String value2) {
            addCriterion("ywmc not between", value1, value2, "ywmc");
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

        public Criteria andSfbrIsNull() {
            addCriterion("sfbr is null");
            return (Criteria) this;
        }

        public Criteria andSfbrIsNotNull() {
            addCriterion("sfbr is not null");
            return (Criteria) this;
        }

        public Criteria andSfbrEqualTo(String value) {
            addCriterion("sfbr =", value, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrNotEqualTo(String value) {
            addCriterion("sfbr <>", value, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrGreaterThan(String value) {
            addCriterion("sfbr >", value, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrGreaterThanOrEqualTo(String value) {
            addCriterion("sfbr >=", value, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrLessThan(String value) {
            addCriterion("sfbr <", value, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrLessThanOrEqualTo(String value) {
            addCriterion("sfbr <=", value, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrLike(String value) {
            addCriterion("sfbr like", value, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrNotLike(String value) {
            addCriterion("sfbr not like", value, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrIn(List<String> values) {
            addCriterion("sfbr in", values, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrNotIn(List<String> values) {
            addCriterion("sfbr not in", values, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrBetween(String value1, String value2) {
            addCriterion("sfbr between", value1, value2, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSfbrNotBetween(String value1, String value2) {
            addCriterion("sfbr not between", value1, value2, "sfbr");
            return (Criteria) this;
        }

        public Criteria andSllbIsNull() {
            addCriterion("sllb is null");
            return (Criteria) this;
        }

        public Criteria andSllbIsNotNull() {
            addCriterion("sllb is not null");
            return (Criteria) this;
        }

        public Criteria andSllbEqualTo(String value) {
            addCriterion("sllb =", value, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbNotEqualTo(String value) {
            addCriterion("sllb <>", value, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbGreaterThan(String value) {
            addCriterion("sllb >", value, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbGreaterThanOrEqualTo(String value) {
            addCriterion("sllb >=", value, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbLessThan(String value) {
            addCriterion("sllb <", value, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbLessThanOrEqualTo(String value) {
            addCriterion("sllb <=", value, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbLike(String value) {
            addCriterion("sllb like", value, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbNotLike(String value) {
            addCriterion("sllb not like", value, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbIn(List<String> values) {
            addCriterion("sllb in", values, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbNotIn(List<String> values) {
            addCriterion("sllb not in", values, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbBetween(String value1, String value2) {
            addCriterion("sllb between", value1, value2, "sllb");
            return (Criteria) this;
        }

        public Criteria andSllbNotBetween(String value1, String value2) {
            addCriterion("sllb not between", value1, value2, "sllb");
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