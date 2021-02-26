package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class VehInsuranceinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehInsuranceinfoExample() {
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

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion(" DATE_FORMAT(create_time,'%Y-%m-%d') >=", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d')  <=", value, "createTime");
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

        public Criteria andBxpzhIsNull() {
            addCriterion("bxpzh is null");
            return (Criteria) this;
        }

        public Criteria andBxpzhIsNotNull() {
            addCriterion("bxpzh is not null");
            return (Criteria) this;
        }

        public Criteria andBxpzhEqualTo(String value) {
            addCriterion("bxpzh =", value, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhNotEqualTo(String value) {
            addCriterion("bxpzh <>", value, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhGreaterThan(String value) {
            addCriterion("bxpzh >", value, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhGreaterThanOrEqualTo(String value) {
            addCriterion("bxpzh >=", value, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhLessThan(String value) {
            addCriterion("bxpzh <", value, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhLessThanOrEqualTo(String value) {
            addCriterion("bxpzh <=", value, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhLike(String value) {
            addCriterion("bxpzh like", value, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhNotLike(String value) {
            addCriterion("bxpzh not like", value, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhIn(List<String> values) {
            addCriterion("bxpzh in", values, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhNotIn(List<String> values) {
            addCriterion("bxpzh not in", values, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhBetween(String value1, String value2) {
            addCriterion("bxpzh between", value1, value2, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxpzhNotBetween(String value1, String value2) {
            addCriterion("bxpzh not between", value1, value2, "bxpzh");
            return (Criteria) this;
        }

        public Criteria andBxjeIsNull() {
            addCriterion("bxje is null");
            return (Criteria) this;
        }

        public Criteria andBxjeIsNotNull() {
            addCriterion("bxje is not null");
            return (Criteria) this;
        }

        public Criteria andBxjeEqualTo(String value) {
            addCriterion("bxje =", value, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeNotEqualTo(String value) {
            addCriterion("bxje <>", value, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeGreaterThan(String value) {
            addCriterion("bxje >", value, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeGreaterThanOrEqualTo(String value) {
            addCriterion("bxje >=", value, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeLessThan(String value) {
            addCriterion("bxje <", value, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeLessThanOrEqualTo(String value) {
            addCriterion("bxje <=", value, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeLike(String value) {
            addCriterion("bxje like", value, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeNotLike(String value) {
            addCriterion("bxje not like", value, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeIn(List<String> values) {
            addCriterion("bxje in", values, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeNotIn(List<String> values) {
            addCriterion("bxje not in", values, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeBetween(String value1, String value2) {
            addCriterion("bxje between", value1, value2, "bxje");
            return (Criteria) this;
        }

        public Criteria andBxjeNotBetween(String value1, String value2) {
            addCriterion("bxje not between", value1, value2, "bxje");
            return (Criteria) this;
        }

        public Criteria andSxrqIsNull() {
            addCriterion("sxrq is null");
            return (Criteria) this;
        }

        public Criteria andSxrqIsNotNull() {
            addCriterion("sxrq is not null");
            return (Criteria) this;
        }

        public Criteria andSxrqEqualTo(Date value) {
            addCriterionForJDBCDate("sxrq =", value, "sxrq");
            return (Criteria) this;
        }

        public Criteria andSxrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("sxrq <>", value, "sxrq");
            return (Criteria) this;
        }

        public Criteria andSxrqGreaterThan(Date value) {
            addCriterionForJDBCDate("sxrq >", value, "sxrq");
            return (Criteria) this;
        }

        public Criteria andSxrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sxrq >=", value, "sxrq");
            return (Criteria) this;
        }

        public Criteria andSxrqLessThan(Date value) {
            addCriterionForJDBCDate("sxrq <", value, "sxrq");
            return (Criteria) this;
        }

        public Criteria andSxrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sxrq <=", value, "sxrq");
            return (Criteria) this;
        }

        public Criteria andSxrqIn(List<Date> values) {
            addCriterionForJDBCDate("sxrq in", values, "sxrq");
            return (Criteria) this;
        }

        public Criteria andSxrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("sxrq not in", values, "sxrq");
            return (Criteria) this;
        }

        public Criteria andSxrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sxrq between", value1, value2, "sxrq");
            return (Criteria) this;
        }

        public Criteria andSxrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sxrq not between", value1, value2, "sxrq");
            return (Criteria) this;
        }

        public Criteria andZzrqIsNull() {
            addCriterion("zzrq is null");
            return (Criteria) this;
        }

        public Criteria andZzrqIsNotNull() {
            addCriterion("zzrq is not null");
            return (Criteria) this;
        }

        public Criteria andZzrqEqualTo(Date value) {
            addCriterionForJDBCDate("zzrq =", value, "zzrq");
            return (Criteria) this;
        }

        public Criteria andZzrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("zzrq <>", value, "zzrq");
            return (Criteria) this;
        }

        public Criteria andZzrqGreaterThan(Date value) {
            addCriterionForJDBCDate("zzrq >", value, "zzrq");
            return (Criteria) this;
        }

        public Criteria andZzrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("zzrq >=", value, "zzrq");
            return (Criteria) this;
        }

        public Criteria andZzrqLessThan(Date value) {
            addCriterionForJDBCDate("zzrq <", value, "zzrq");
            return (Criteria) this;
        }

        public Criteria andZzrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("zzrq <=", value, "zzrq");
            return (Criteria) this;
        }

        public Criteria andZzrqIn(List<Date> values) {
            addCriterionForJDBCDate("zzrq in", values, "zzrq");
            return (Criteria) this;
        }

        public Criteria andZzrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("zzrq not in", values, "zzrq");
            return (Criteria) this;
        }

        public Criteria andZzrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("zzrq between", value1, value2, "zzrq");
            return (Criteria) this;
        }

        public Criteria andZzrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("zzrq not between", value1, value2, "zzrq");
            return (Criteria) this;
        }

        public Criteria andBbxrIsNull() {
            addCriterion("bbxr is null");
            return (Criteria) this;
        }

        public Criteria andBbxrIsNotNull() {
            addCriterion("bbxr is not null");
            return (Criteria) this;
        }

        public Criteria andBbxrEqualTo(String value) {
            addCriterion("bbxr =", value, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrNotEqualTo(String value) {
            addCriterion("bbxr <>", value, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrGreaterThan(String value) {
            addCriterion("bbxr >", value, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrGreaterThanOrEqualTo(String value) {
            addCriterion("bbxr >=", value, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrLessThan(String value) {
            addCriterion("bbxr <", value, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrLessThanOrEqualTo(String value) {
            addCriterion("bbxr <=", value, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrLike(String value) {
            addCriterion("bbxr like", value, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrNotLike(String value) {
            addCriterion("bbxr not like", value, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrIn(List<String> values) {
            addCriterion("bbxr in", values, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrNotIn(List<String> values) {
            addCriterion("bbxr not in", values, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrBetween(String value1, String value2) {
            addCriterion("bbxr between", value1, value2, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrNotBetween(String value1, String value2) {
            addCriterion("bbxr not between", value1, value2, "bbxr");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmIsNull() {
            addCriterion("bbxrsfzmhm is null");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmIsNotNull() {
            addCriterion("bbxrsfzmhm is not null");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmEqualTo(String value) {
            addCriterion("bbxrsfzmhm =", value, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmNotEqualTo(String value) {
            addCriterion("bbxrsfzmhm <>", value, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmGreaterThan(String value) {
            addCriterion("bbxrsfzmhm >", value, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmGreaterThanOrEqualTo(String value) {
            addCriterion("bbxrsfzmhm >=", value, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmLessThan(String value) {
            addCriterion("bbxrsfzmhm <", value, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmLessThanOrEqualTo(String value) {
            addCriterion("bbxrsfzmhm <=", value, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmLike(String value) {
            addCriterion("bbxrsfzmhm like", value, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmNotLike(String value) {
            addCriterion("bbxrsfzmhm not like", value, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmIn(List<String> values) {
            addCriterion("bbxrsfzmhm in", values, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmNotIn(List<String> values) {
            addCriterion("bbxrsfzmhm not in", values, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmBetween(String value1, String value2) {
            addCriterion("bbxrsfzmhm between", value1, value2, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andBbxrsfzmhmNotBetween(String value1, String value2) {
            addCriterion("bbxrsfzmhm not between", value1, value2, "bbxrsfzmhm");
            return (Criteria) this;
        }

        public Criteria andJqxtbztIsNull() {
            addCriterion("jqxtbzt is null");
            return (Criteria) this;
        }

        public Criteria andJqxtbztIsNotNull() {
            addCriterion("jqxtbzt is not null");
            return (Criteria) this;
        }

        public Criteria andJqxtbztEqualTo(String value) {
            addCriterion("jqxtbzt =", value, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztNotEqualTo(String value) {
            addCriterion("jqxtbzt <>", value, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztGreaterThan(String value) {
            addCriterion("jqxtbzt >", value, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztGreaterThanOrEqualTo(String value) {
            addCriterion("jqxtbzt >=", value, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztLessThan(String value) {
            addCriterion("jqxtbzt <", value, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztLessThanOrEqualTo(String value) {
            addCriterion("jqxtbzt <=", value, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztLike(String value) {
            addCriterion("jqxtbzt like", value, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztNotLike(String value) {
            addCriterion("jqxtbzt not like", value, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztIn(List<String> values) {
            addCriterion("jqxtbzt in", values, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztNotIn(List<String> values) {
            addCriterion("jqxtbzt not in", values, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztBetween(String value1, String value2) {
            addCriterion("jqxtbzt between", value1, value2, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andJqxtbztNotBetween(String value1, String value2) {
            addCriterion("jqxtbzt not between", value1, value2, "jqxtbzt");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkIsNull() {
            addCriterion("ccsnsqk is null");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkIsNotNull() {
            addCriterion("ccsnsqk is not null");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkEqualTo(String value) {
            addCriterion("ccsnsqk =", value, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkNotEqualTo(String value) {
            addCriterion("ccsnsqk <>", value, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkGreaterThan(String value) {
            addCriterion("ccsnsqk >", value, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkGreaterThanOrEqualTo(String value) {
            addCriterion("ccsnsqk >=", value, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkLessThan(String value) {
            addCriterion("ccsnsqk <", value, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkLessThanOrEqualTo(String value) {
            addCriterion("ccsnsqk <=", value, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkLike(String value) {
            addCriterion("ccsnsqk like", value, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkNotLike(String value) {
            addCriterion("ccsnsqk not like", value, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkIn(List<String> values) {
            addCriterion("ccsnsqk in", values, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkNotIn(List<String> values) {
            addCriterion("ccsnsqk not in", values, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkBetween(String value1, String value2) {
            addCriterion("ccsnsqk between", value1, value2, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsqkNotBetween(String value1, String value2) {
            addCriterion("ccsnsqk not between", value1, value2, "ccsnsqk");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeIsNull() {
            addCriterion("ccsnsje is null");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeIsNotNull() {
            addCriterion("ccsnsje is not null");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeEqualTo(String value) {
            addCriterion("ccsnsje =", value, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeNotEqualTo(String value) {
            addCriterion("ccsnsje <>", value, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeGreaterThan(String value) {
            addCriterion("ccsnsje >", value, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeGreaterThanOrEqualTo(String value) {
            addCriterion("ccsnsje >=", value, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeLessThan(String value) {
            addCriterion("ccsnsje <", value, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeLessThanOrEqualTo(String value) {
            addCriterion("ccsnsje <=", value, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeLike(String value) {
            addCriterion("ccsnsje like", value, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeNotLike(String value) {
            addCriterion("ccsnsje not like", value, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeIn(List<String> values) {
            addCriterion("ccsnsje in", values, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeNotIn(List<String> values) {
            addCriterion("ccsnsje not in", values, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeBetween(String value1, String value2) {
            addCriterion("ccsnsje between", value1, value2, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andCcsnsjeNotBetween(String value1, String value2) {
            addCriterion("ccsnsje not between", value1, value2, "ccsnsje");
            return (Criteria) this;
        }

        public Criteria andWspzhmIsNull() {
            addCriterion("wspzhm is null");
            return (Criteria) this;
        }

        public Criteria andWspzhmIsNotNull() {
            addCriterion("wspzhm is not null");
            return (Criteria) this;
        }

        public Criteria andWspzhmEqualTo(String value) {
            addCriterion("wspzhm =", value, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmNotEqualTo(String value) {
            addCriterion("wspzhm <>", value, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmGreaterThan(String value) {
            addCriterion("wspzhm >", value, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmGreaterThanOrEqualTo(String value) {
            addCriterion("wspzhm >=", value, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmLessThan(String value) {
            addCriterion("wspzhm <", value, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmLessThanOrEqualTo(String value) {
            addCriterion("wspzhm <=", value, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmLike(String value) {
            addCriterion("wspzhm like", value, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmNotLike(String value) {
            addCriterion("wspzhm not like", value, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmIn(List<String> values) {
            addCriterion("wspzhm in", values, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmNotIn(List<String> values) {
            addCriterion("wspzhm not in", values, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmBetween(String value1, String value2) {
            addCriterion("wspzhm between", value1, value2, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andWspzhmNotBetween(String value1, String value2) {
            addCriterion("wspzhm not between", value1, value2, "wspzhm");
            return (Criteria) this;
        }

        public Criteria andNsrsbhIsNull() {
            addCriterion("nsrsbh is null");
            return (Criteria) this;
        }

        public Criteria andNsrsbhIsNotNull() {
            addCriterion("nsrsbh is not null");
            return (Criteria) this;
        }

        public Criteria andNsrsbhEqualTo(String value) {
            addCriterion("nsrsbh =", value, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhNotEqualTo(String value) {
            addCriterion("nsrsbh <>", value, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhGreaterThan(String value) {
            addCriterion("nsrsbh >", value, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhGreaterThanOrEqualTo(String value) {
            addCriterion("nsrsbh >=", value, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhLessThan(String value) {
            addCriterion("nsrsbh <", value, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhLessThanOrEqualTo(String value) {
            addCriterion("nsrsbh <=", value, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhLike(String value) {
            addCriterion("nsrsbh like", value, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhNotLike(String value) {
            addCriterion("nsrsbh not like", value, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhIn(List<String> values) {
            addCriterion("nsrsbh in", values, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhNotIn(List<String> values) {
            addCriterion("nsrsbh not in", values, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhBetween(String value1, String value2) {
            addCriterion("nsrsbh between", value1, value2, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andNsrsbhNotBetween(String value1, String value2) {
            addCriterion("nsrsbh not between", value1, value2, "nsrsbh");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqIsNull() {
            addCriterion("ccsnsrq is null");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqIsNotNull() {
            addCriterion("ccsnsrq is not null");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnsrq =", value, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnsrq <>", value, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqGreaterThan(Date value) {
            addCriterionForJDBCDate("ccsnsrq >", value, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnsrq >=", value, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqLessThan(Date value) {
            addCriterionForJDBCDate("ccsnsrq <", value, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnsrq <=", value, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqIn(List<Date> values) {
            addCriterionForJDBCDate("ccsnsrq in", values, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("ccsnsrq not in", values, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ccsnsrq between", value1, value2, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ccsnsrq not between", value1, value2, "ccsnsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqIsNull() {
            addCriterion("ccsnsqsrq is null");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqIsNotNull() {
            addCriterion("ccsnsqsrq is not null");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnsqsrq =", value, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnsqsrq <>", value, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqGreaterThan(Date value) {
            addCriterionForJDBCDate("ccsnsqsrq >", value, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnsqsrq >=", value, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqLessThan(Date value) {
            addCriterionForJDBCDate("ccsnsqsrq <", value, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnsqsrq <=", value, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqIn(List<Date> values) {
            addCriterionForJDBCDate("ccsnsqsrq in", values, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("ccsnsqsrq not in", values, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ccsnsqsrq between", value1, value2, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnsqsrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ccsnsqsrq not between", value1, value2, "ccsnsqsrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqIsNull() {
            addCriterion("ccsnszzrq is null");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqIsNotNull() {
            addCriterion("ccsnszzrq is not null");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnszzrq =", value, "ccsnszzrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnszzrq <>", value, "ccsnszzrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqGreaterThan(Date value) {
            addCriterionForJDBCDate("ccsnszzrq >", value, "ccsnszzrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnszzrq >=", value, "ccsnszzrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqLessThan(Date value) {
            addCriterionForJDBCDate("ccsnszzrq <", value, "ccsnszzrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ccsnszzrq <=", value, "ccsnszzrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqIn(List<Date> values) {
            addCriterionForJDBCDate("ccsnszzrq in", values, "ccsnszzrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("ccsnszzrq not in", values, "ccsnszzrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ccsnszzrq between", value1, value2, "ccsnszzrq");
            return (Criteria) this;
        }

        public Criteria andCcsnszzrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ccsnszzrq not between", value1, value2, "ccsnszzrq");
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