package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class VehLogoutExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehLogoutExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andXhIsNull() {
            addCriterion("xh is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("xh is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(String value) {
            addCriterion("xh =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(String value) {
            addCriterion("xh <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(String value) {
            addCriterion("xh >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(String value) {
            addCriterion("xh >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(String value) {
            addCriterion("xh <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(String value) {
            addCriterion("xh <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLike(String value) {
            addCriterion("xh like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotLike(String value) {
            addCriterion("xh not like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<String> values) {
            addCriterion("xh in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<String> values) {
            addCriterion("xh not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(String value1, String value2) {
            addCriterion("xh between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(String value1, String value2) {
            addCriterion("xh not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andHpzlIsNull() {
            addCriterion("hpzl is null");
            return (Criteria) this;
        }

        public Criteria andHpzlIsNotNull() {
            addCriterion("hpzl is not null");
            return (Criteria) this;
        }

        public Criteria andHpzlEqualTo(String value) {
            addCriterion("hpzl =", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotEqualTo(String value) {
            addCriterion("hpzl <>", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlGreaterThan(String value) {
            addCriterion("hpzl >", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlGreaterThanOrEqualTo(String value) {
            addCriterion("hpzl >=", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlLessThan(String value) {
            addCriterion("hpzl <", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlLessThanOrEqualTo(String value) {
            addCriterion("hpzl <=", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlLike(String value) {
            addCriterion("hpzl like", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotLike(String value) {
            addCriterion("hpzl not like", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlIn(List<String> values) {
            addCriterion("hpzl in", values, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotIn(List<String> values) {
            addCriterion("hpzl not in", values, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlBetween(String value1, String value2) {
            addCriterion("hpzl between", value1, value2, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotBetween(String value1, String value2) {
            addCriterion("hpzl not between", value1, value2, "hpzl");
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

        public Criteria andZxyyIsNull() {
            addCriterion("zxyy is null");
            return (Criteria) this;
        }

        public Criteria andZxyyIsNotNull() {
            addCriterion("zxyy is not null");
            return (Criteria) this;
        }

        public Criteria andZxyyEqualTo(String value) {
            addCriterion("zxyy =", value, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyNotEqualTo(String value) {
            addCriterion("zxyy <>", value, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyGreaterThan(String value) {
            addCriterion("zxyy >", value, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyGreaterThanOrEqualTo(String value) {
            addCriterion("zxyy >=", value, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyLessThan(String value) {
            addCriterion("zxyy <", value, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyLessThanOrEqualTo(String value) {
            addCriterion("zxyy <=", value, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyLike(String value) {
            addCriterion("zxyy like", value, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyNotLike(String value) {
            addCriterion("zxyy not like", value, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyIn(List<String> values) {
            addCriterion("zxyy in", values, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyNotIn(List<String> values) {
            addCriterion("zxyy not in", values, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyBetween(String value1, String value2) {
            addCriterion("zxyy between", value1, value2, "zxyy");
            return (Criteria) this;
        }

        public Criteria andZxyyNotBetween(String value1, String value2) {
            addCriterion("zxyy not between", value1, value2, "zxyy");
            return (Criteria) this;
        }

        public Criteria andHsqymcIsNull() {
            addCriterion("hsqymc is null");
            return (Criteria) this;
        }

        public Criteria andHsqymcIsNotNull() {
            addCriterion("hsqymc is not null");
            return (Criteria) this;
        }

        public Criteria andHsqymcEqualTo(String value) {
            addCriterion("hsqymc =", value, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcNotEqualTo(String value) {
            addCriterion("hsqymc <>", value, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcGreaterThan(String value) {
            addCriterion("hsqymc >", value, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcGreaterThanOrEqualTo(String value) {
            addCriterion("hsqymc >=", value, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcLessThan(String value) {
            addCriterion("hsqymc <", value, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcLessThanOrEqualTo(String value) {
            addCriterion("hsqymc <=", value, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcLike(String value) {
            addCriterion("hsqymc like", value, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcNotLike(String value) {
            addCriterion("hsqymc not like", value, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcIn(List<String> values) {
            addCriterion("hsqymc in", values, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcNotIn(List<String> values) {
            addCriterion("hsqymc not in", values, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcBetween(String value1, String value2) {
            addCriterion("hsqymc between", value1, value2, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andHsqymcNotBetween(String value1, String value2) {
            addCriterion("hsqymc not between", value1, value2, "hsqymc");
            return (Criteria) this;
        }

        public Criteria andZxrqIsNull() {
            addCriterion("zxrq is null");
            return (Criteria) this;
        }

        public Criteria andZxrqIsNotNull() {
            addCriterion("zxrq is not null");
            return (Criteria) this;
        }

        public Criteria andZxrqEqualTo(Date value) {
            addCriterionForJDBCDate("zxrq =", value, "zxrq");
            return (Criteria) this;
        }

        public Criteria andZxrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("zxrq <>", value, "zxrq");
            return (Criteria) this;
        }

        public Criteria andZxrqGreaterThan(Date value) {
            addCriterionForJDBCDate("zxrq >", value, "zxrq");
            return (Criteria) this;
        }

        public Criteria andZxrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("zxrq >=", value, "zxrq");
            return (Criteria) this;
        }

        public Criteria andZxrqLessThan(Date value) {
            addCriterionForJDBCDate("zxrq <", value, "zxrq");
            return (Criteria) this;
        }

        public Criteria andZxrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("zxrq <=", value, "zxrq");
            return (Criteria) this;
        }

        public Criteria andZxrqIn(List<Date> values) {
            addCriterionForJDBCDate("zxrq in", values, "zxrq");
            return (Criteria) this;
        }

        public Criteria andZxrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("zxrq not in", values, "zxrq");
            return (Criteria) this;
        }

        public Criteria andZxrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("zxrq between", value1, value2, "zxrq");
            return (Criteria) this;
        }

        public Criteria andZxrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("zxrq not between", value1, value2, "zxrq");
            return (Criteria) this;
        }

        public Criteria andHshpIsNull() {
            addCriterion("hshp is null");
            return (Criteria) this;
        }

        public Criteria andHshpIsNotNull() {
            addCriterion("hshp is not null");
            return (Criteria) this;
        }

        public Criteria andHshpEqualTo(String value) {
            addCriterion("hshp =", value, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpNotEqualTo(String value) {
            addCriterion("hshp <>", value, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpGreaterThan(String value) {
            addCriterion("hshp >", value, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpGreaterThanOrEqualTo(String value) {
            addCriterion("hshp >=", value, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpLessThan(String value) {
            addCriterion("hshp <", value, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpLessThanOrEqualTo(String value) {
            addCriterion("hshp <=", value, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpLike(String value) {
            addCriterion("hshp like", value, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpNotLike(String value) {
            addCriterion("hshp not like", value, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpIn(List<String> values) {
            addCriterion("hshp in", values, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpNotIn(List<String> values) {
            addCriterion("hshp not in", values, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpBetween(String value1, String value2) {
            addCriterion("hshp between", value1, value2, "hshp");
            return (Criteria) this;
        }

        public Criteria andHshpNotBetween(String value1, String value2) {
            addCriterion("hshp not between", value1, value2, "hshp");
            return (Criteria) this;
        }

        public Criteria andHsxszIsNull() {
            addCriterion("hsxsz is null");
            return (Criteria) this;
        }

        public Criteria andHsxszIsNotNull() {
            addCriterion("hsxsz is not null");
            return (Criteria) this;
        }

        public Criteria andHsxszEqualTo(String value) {
            addCriterion("hsxsz =", value, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszNotEqualTo(String value) {
            addCriterion("hsxsz <>", value, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszGreaterThan(String value) {
            addCriterion("hsxsz >", value, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszGreaterThanOrEqualTo(String value) {
            addCriterion("hsxsz >=", value, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszLessThan(String value) {
            addCriterion("hsxsz <", value, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszLessThanOrEqualTo(String value) {
            addCriterion("hsxsz <=", value, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszLike(String value) {
            addCriterion("hsxsz like", value, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszNotLike(String value) {
            addCriterion("hsxsz not like", value, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszIn(List<String> values) {
            addCriterion("hsxsz in", values, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszNotIn(List<String> values) {
            addCriterion("hsxsz not in", values, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszBetween(String value1, String value2) {
            addCriterion("hsxsz between", value1, value2, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsxszNotBetween(String value1, String value2) {
            addCriterion("hsxsz not between", value1, value2, "hsxsz");
            return (Criteria) this;
        }

        public Criteria andHsdjzsIsNull() {
            addCriterion("hsdjzs is null");
            return (Criteria) this;
        }

        public Criteria andHsdjzsIsNotNull() {
            addCriterion("hsdjzs is not null");
            return (Criteria) this;
        }

        public Criteria andHsdjzsEqualTo(String value) {
            addCriterion("hsdjzs =", value, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsNotEqualTo(String value) {
            addCriterion("hsdjzs <>", value, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsGreaterThan(String value) {
            addCriterion("hsdjzs >", value, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsGreaterThanOrEqualTo(String value) {
            addCriterion("hsdjzs >=", value, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsLessThan(String value) {
            addCriterion("hsdjzs <", value, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsLessThanOrEqualTo(String value) {
            addCriterion("hsdjzs <=", value, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsLike(String value) {
            addCriterion("hsdjzs like", value, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsNotLike(String value) {
            addCriterion("hsdjzs not like", value, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsIn(List<String> values) {
            addCriterion("hsdjzs in", values, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsNotIn(List<String> values) {
            addCriterion("hsdjzs not in", values, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsBetween(String value1, String value2) {
            addCriterion("hsdjzs between", value1, value2, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andHsdjzsNotBetween(String value1, String value2) {
            addCriterion("hsdjzs not between", value1, value2, "hsdjzs");
            return (Criteria) this;
        }

        public Criteria andCfjgIsNull() {
            addCriterion("cfjg is null");
            return (Criteria) this;
        }

        public Criteria andCfjgIsNotNull() {
            addCriterion("cfjg is not null");
            return (Criteria) this;
        }

        public Criteria andCfjgEqualTo(String value) {
            addCriterion("cfjg =", value, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgNotEqualTo(String value) {
            addCriterion("cfjg <>", value, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgGreaterThan(String value) {
            addCriterion("cfjg >", value, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgGreaterThanOrEqualTo(String value) {
            addCriterion("cfjg >=", value, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgLessThan(String value) {
            addCriterion("cfjg <", value, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgLessThanOrEqualTo(String value) {
            addCriterion("cfjg <=", value, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgLike(String value) {
            addCriterion("cfjg like", value, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgNotLike(String value) {
            addCriterion("cfjg not like", value, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgIn(List<String> values) {
            addCriterion("cfjg in", values, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgNotIn(List<String> values) {
            addCriterion("cfjg not in", values, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgBetween(String value1, String value2) {
            addCriterion("cfjg between", value1, value2, "cfjg");
            return (Criteria) this;
        }

        public Criteria andCfjgNotBetween(String value1, String value2) {
            addCriterion("cfjg not between", value1, value2, "cfjg");
            return (Criteria) this;
        }

        public Criteria andJdsbhIsNull() {
            addCriterion("jdsbh is null");
            return (Criteria) this;
        }

        public Criteria andJdsbhIsNotNull() {
            addCriterion("jdsbh is not null");
            return (Criteria) this;
        }

        public Criteria andJdsbhEqualTo(String value) {
            addCriterion("jdsbh =", value, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhNotEqualTo(String value) {
            addCriterion("jdsbh <>", value, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhGreaterThan(String value) {
            addCriterion("jdsbh >", value, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhGreaterThanOrEqualTo(String value) {
            addCriterion("jdsbh >=", value, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhLessThan(String value) {
            addCriterion("jdsbh <", value, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhLessThanOrEqualTo(String value) {
            addCriterion("jdsbh <=", value, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhLike(String value) {
            addCriterion("jdsbh like", value, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhNotLike(String value) {
            addCriterion("jdsbh not like", value, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhIn(List<String> values) {
            addCriterion("jdsbh in", values, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhNotIn(List<String> values) {
            addCriterion("jdsbh not in", values, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhBetween(String value1, String value2) {
            addCriterion("jdsbh between", value1, value2, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andJdsbhNotBetween(String value1, String value2) {
            addCriterion("jdsbh not between", value1, value2, "jdsbh");
            return (Criteria) this;
        }

        public Criteria andCfsjIsNull() {
            addCriterion("cfsj is null");
            return (Criteria) this;
        }

        public Criteria andCfsjIsNotNull() {
            addCriterion("cfsj is not null");
            return (Criteria) this;
        }

        public Criteria andCfsjEqualTo(Date value) {
            addCriterion("cfsj =", value, "cfsj");
            return (Criteria) this;
        }

        public Criteria andCfsjNotEqualTo(Date value) {
            addCriterion("cfsj <>", value, "cfsj");
            return (Criteria) this;
        }

        public Criteria andCfsjGreaterThan(Date value) {
            addCriterion("cfsj >", value, "cfsj");
            return (Criteria) this;
        }

        public Criteria andCfsjGreaterThanOrEqualTo(Date value) {
            addCriterion("cfsj >=", value, "cfsj");
            return (Criteria) this;
        }

        public Criteria andCfsjLessThan(Date value) {
            addCriterion("cfsj <", value, "cfsj");
            return (Criteria) this;
        }

        public Criteria andCfsjLessThanOrEqualTo(Date value) {
            addCriterion("cfsj <=", value, "cfsj");
            return (Criteria) this;
        }

        public Criteria andCfsjIn(List<Date> values) {
            addCriterion("cfsj in", values, "cfsj");
            return (Criteria) this;
        }

        public Criteria andCfsjNotIn(List<Date> values) {
            addCriterion("cfsj not in", values, "cfsj");
            return (Criteria) this;
        }

        public Criteria andCfsjBetween(Date value1, Date value2) {
            addCriterion("cfsj between", value1, value2, "cfsj");
            return (Criteria) this;
        }

        public Criteria andCfsjNotBetween(Date value1, Date value2) {
            addCriterion("cfsj not between", value1, value2, "cfsj");
            return (Criteria) this;
        }

        public Criteria andJxmjIsNull() {
            addCriterion("jxmj is null");
            return (Criteria) this;
        }

        public Criteria andJxmjIsNotNull() {
            addCriterion("jxmj is not null");
            return (Criteria) this;
        }

        public Criteria andJxmjEqualTo(String value) {
            addCriterion("jxmj =", value, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjNotEqualTo(String value) {
            addCriterion("jxmj <>", value, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjGreaterThan(String value) {
            addCriterion("jxmj >", value, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjGreaterThanOrEqualTo(String value) {
            addCriterion("jxmj >=", value, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjLessThan(String value) {
            addCriterion("jxmj <", value, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjLessThanOrEqualTo(String value) {
            addCriterion("jxmj <=", value, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjLike(String value) {
            addCriterion("jxmj like", value, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjNotLike(String value) {
            addCriterion("jxmj not like", value, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjIn(List<String> values) {
            addCriterion("jxmj in", values, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjNotIn(List<String> values) {
            addCriterion("jxmj not in", values, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjBetween(String value1, String value2) {
            addCriterion("jxmj between", value1, value2, "jxmj");
            return (Criteria) this;
        }

        public Criteria andJxmjNotBetween(String value1, String value2) {
            addCriterion("jxmj not between", value1, value2, "jxmj");
            return (Criteria) this;
        }

        public Criteria andZxjgIsNull() {
            addCriterion("zxjg is null");
            return (Criteria) this;
        }

        public Criteria andZxjgIsNotNull() {
            addCriterion("zxjg is not null");
            return (Criteria) this;
        }

        public Criteria andZxjgEqualTo(String value) {
            addCriterion("zxjg =", value, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgNotEqualTo(String value) {
            addCriterion("zxjg <>", value, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgGreaterThan(String value) {
            addCriterion("zxjg >", value, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgGreaterThanOrEqualTo(String value) {
            addCriterion("zxjg >=", value, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgLessThan(String value) {
            addCriterion("zxjg <", value, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgLessThanOrEqualTo(String value) {
            addCriterion("zxjg <=", value, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgLike(String value) {
            addCriterion("zxjg like", value, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgNotLike(String value) {
            addCriterion("zxjg not like", value, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgIn(List<String> values) {
            addCriterion("zxjg in", values, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgNotIn(List<String> values) {
            addCriterion("zxjg not in", values, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgBetween(String value1, String value2) {
            addCriterion("zxjg between", value1, value2, "zxjg");
            return (Criteria) this;
        }

        public Criteria andZxjgNotBetween(String value1, String value2) {
            addCriterion("zxjg not between", value1, value2, "zxjg");
            return (Criteria) this;
        }

        public Criteria andJbrIsNull() {
            addCriterion("jbr is null");
            return (Criteria) this;
        }

        public Criteria andJbrIsNotNull() {
            addCriterion("jbr is not null");
            return (Criteria) this;
        }

        public Criteria andJbrEqualTo(String value) {
            addCriterion("jbr =", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotEqualTo(String value) {
            addCriterion("jbr <>", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrGreaterThan(String value) {
            addCriterion("jbr >", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrGreaterThanOrEqualTo(String value) {
            addCriterion("jbr >=", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrLessThan(String value) {
            addCriterion("jbr <", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrLessThanOrEqualTo(String value) {
            addCriterion("jbr <=", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrLike(String value) {
            addCriterion("jbr like", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotLike(String value) {
            addCriterion("jbr not like", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrIn(List<String> values) {
            addCriterion("jbr in", values, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotIn(List<String> values) {
            addCriterion("jbr not in", values, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrBetween(String value1, String value2) {
            addCriterion("jbr between", value1, value2, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotBetween(String value1, String value2) {
            addCriterion("jbr not between", value1, value2, "jbr");
            return (Criteria) this;
        }

        public Criteria andGlbmIsNull() {
            addCriterion("glbm is null");
            return (Criteria) this;
        }

        public Criteria andGlbmIsNotNull() {
            addCriterion("glbm is not null");
            return (Criteria) this;
        }

        public Criteria andGlbmEqualTo(String value) {
            addCriterion("glbm =", value, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmNotEqualTo(String value) {
            addCriterion("glbm <>", value, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmGreaterThan(String value) {
            addCriterion("glbm >", value, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmGreaterThanOrEqualTo(String value) {
            addCriterion("glbm >=", value, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmLessThan(String value) {
            addCriterion("glbm <", value, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmLessThanOrEqualTo(String value) {
            addCriterion("glbm <=", value, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmLike(String value) {
            addCriterion("glbm like", value, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmNotLike(String value) {
            addCriterion("glbm not like", value, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmIn(List<String> values) {
            addCriterion("glbm in", values, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmNotIn(List<String> values) {
            addCriterion("glbm not in", values, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmBetween(String value1, String value2) {
            addCriterion("glbm between", value1, value2, "glbm");
            return (Criteria) this;
        }

        public Criteria andGlbmNotBetween(String value1, String value2) {
            addCriterion("glbm not between", value1, value2, "glbm");
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

        public Criteria andClpp1IsNull() {
            addCriterion("clpp1 is null");
            return (Criteria) this;
        }

        public Criteria andClpp1IsNotNull() {
            addCriterion("clpp1 is not null");
            return (Criteria) this;
        }

        public Criteria andClpp1EqualTo(String value) {
            addCriterion("clpp1 =", value, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1NotEqualTo(String value) {
            addCriterion("clpp1 <>", value, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1GreaterThan(String value) {
            addCriterion("clpp1 >", value, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1GreaterThanOrEqualTo(String value) {
            addCriterion("clpp1 >=", value, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1LessThan(String value) {
            addCriterion("clpp1 <", value, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1LessThanOrEqualTo(String value) {
            addCriterion("clpp1 <=", value, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1Like(String value) {
            addCriterion("clpp1 like", value, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1NotLike(String value) {
            addCriterion("clpp1 not like", value, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1In(List<String> values) {
            addCriterion("clpp1 in", values, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1NotIn(List<String> values) {
            addCriterion("clpp1 not in", values, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1Between(String value1, String value2) {
            addCriterion("clpp1 between", value1, value2, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClpp1NotBetween(String value1, String value2) {
            addCriterion("clpp1 not between", value1, value2, "clpp1");
            return (Criteria) this;
        }

        public Criteria andClxhIsNull() {
            addCriterion("clxh is null");
            return (Criteria) this;
        }

        public Criteria andClxhIsNotNull() {
            addCriterion("clxh is not null");
            return (Criteria) this;
        }

        public Criteria andClxhEqualTo(String value) {
            addCriterion("clxh =", value, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhNotEqualTo(String value) {
            addCriterion("clxh <>", value, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhGreaterThan(String value) {
            addCriterion("clxh >", value, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhGreaterThanOrEqualTo(String value) {
            addCriterion("clxh >=", value, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhLessThan(String value) {
            addCriterion("clxh <", value, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhLessThanOrEqualTo(String value) {
            addCriterion("clxh <=", value, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhLike(String value) {
            addCriterion("clxh like", value, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhNotLike(String value) {
            addCriterion("clxh not like", value, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhIn(List<String> values) {
            addCriterion("clxh in", values, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhNotIn(List<String> values) {
            addCriterion("clxh not in", values, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhBetween(String value1, String value2) {
            addCriterion("clxh between", value1, value2, "clxh");
            return (Criteria) this;
        }

        public Criteria andClxhNotBetween(String value1, String value2) {
            addCriterion("clxh not between", value1, value2, "clxh");
            return (Criteria) this;
        }

        public Criteria andSyrIsNull() {
            addCriterion("syr is null");
            return (Criteria) this;
        }

        public Criteria andSyrIsNotNull() {
            addCriterion("syr is not null");
            return (Criteria) this;
        }

        public Criteria andSyrEqualTo(String value) {
            addCriterion("syr =", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotEqualTo(String value) {
            addCriterion("syr <>", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrGreaterThan(String value) {
            addCriterion("syr >", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrGreaterThanOrEqualTo(String value) {
            addCriterion("syr >=", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrLessThan(String value) {
            addCriterion("syr <", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrLessThanOrEqualTo(String value) {
            addCriterion("syr <=", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrLike(String value) {
            addCriterion("syr like", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotLike(String value) {
            addCriterion("syr not like", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrIn(List<String> values) {
            addCriterion("syr in", values, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotIn(List<String> values) {
            addCriterion("syr not in", values, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrBetween(String value1, String value2) {
            addCriterion("syr between", value1, value2, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotBetween(String value1, String value2) {
            addCriterion("syr not between", value1, value2, "syr");
            return (Criteria) this;
        }

        public Criteria andClsbdhIsNull() {
            addCriterion("clsbdh is null");
            return (Criteria) this;
        }

        public Criteria andClsbdhIsNotNull() {
            addCriterion("clsbdh is not null");
            return (Criteria) this;
        }

        public Criteria andClsbdhEqualTo(String value) {
            addCriterion("clsbdh =", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotEqualTo(String value) {
            addCriterion("clsbdh <>", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhGreaterThan(String value) {
            addCriterion("clsbdh >", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhGreaterThanOrEqualTo(String value) {
            addCriterion("clsbdh >=", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhLessThan(String value) {
            addCriterion("clsbdh <", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhLessThanOrEqualTo(String value) {
            addCriterion("clsbdh <=", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhLike(String value) {
            addCriterion("clsbdh like", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotLike(String value) {
            addCriterion("clsbdh not like", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhIn(List<String> values) {
            addCriterion("clsbdh in", values, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotIn(List<String> values) {
            addCriterion("clsbdh not in", values, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhBetween(String value1, String value2) {
            addCriterion("clsbdh between", value1, value2, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotBetween(String value1, String value2) {
            addCriterion("clsbdh not between", value1, value2, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andBfrqIsNull() {
            addCriterion("bfrq is null");
            return (Criteria) this;
        }

        public Criteria andBfrqIsNotNull() {
            addCriterion("bfrq is not null");
            return (Criteria) this;
        }

        public Criteria andBfrqEqualTo(Date value) {
            addCriterionForJDBCDate("bfrq =", value, "bfrq");
            return (Criteria) this;
        }

        public Criteria andBfrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("bfrq <>", value, "bfrq");
            return (Criteria) this;
        }

        public Criteria andBfrqGreaterThan(Date value) {
            addCriterionForJDBCDate("bfrq >", value, "bfrq");
            return (Criteria) this;
        }

        public Criteria andBfrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bfrq >=", value, "bfrq");
            return (Criteria) this;
        }

        public Criteria andBfrqLessThan(Date value) {
            addCriterionForJDBCDate("bfrq <", value, "bfrq");
            return (Criteria) this;
        }

        public Criteria andBfrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bfrq <=", value, "bfrq");
            return (Criteria) this;
        }

        public Criteria andBfrqIn(List<Date> values) {
            addCriterionForJDBCDate("bfrq in", values, "bfrq");
            return (Criteria) this;
        }

        public Criteria andBfrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("bfrq not in", values, "bfrq");
            return (Criteria) this;
        }

        public Criteria andBfrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bfrq between", value1, value2, "bfrq");
            return (Criteria) this;
        }

        public Criteria andBfrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bfrq not between", value1, value2, "bfrq");
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

        public Criteria andShsmIsNull() {
            addCriterion("shsm is null");
            return (Criteria) this;
        }

        public Criteria andShsmIsNotNull() {
            addCriterion("shsm is not null");
            return (Criteria) this;
        }

        public Criteria andShsmEqualTo(String value) {
            addCriterion("shsm =", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmNotEqualTo(String value) {
            addCriterion("shsm <>", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmGreaterThan(String value) {
            addCriterion("shsm >", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmGreaterThanOrEqualTo(String value) {
            addCriterion("shsm >=", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmLessThan(String value) {
            addCriterion("shsm <", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmLessThanOrEqualTo(String value) {
            addCriterion("shsm <=", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmLike(String value) {
            addCriterion("shsm like", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmNotLike(String value) {
            addCriterion("shsm not like", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmIn(List<String> values) {
            addCriterion("shsm in", values, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmNotIn(List<String> values) {
            addCriterion("shsm not in", values, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmBetween(String value1, String value2) {
            addCriterion("shsm between", value1, value2, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmNotBetween(String value1, String value2) {
            addCriterion("shsm not between", value1, value2, "shsm");
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