package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehLicelostExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehLicelostExample() {
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

        public Criteria andLqdzIsNull() {
            addCriterion("lqdz is null");
            return (Criteria) this;
        }

        public Criteria andLqdzIsNotNull() {
            addCriterion("lqdz is not null");
            return (Criteria) this;
        }

        public Criteria andLqdzEqualTo(String value) {
            addCriterion("lqdz =", value, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzNotEqualTo(String value) {
            addCriterion("lqdz <>", value, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzGreaterThan(String value) {
            addCriterion("lqdz >", value, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzGreaterThanOrEqualTo(String value) {
            addCriterion("lqdz >=", value, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzLessThan(String value) {
            addCriterion("lqdz <", value, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzLessThanOrEqualTo(String value) {
            addCriterion("lqdz <=", value, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzLike(String value) {
            addCriterion("lqdz like", value, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzNotLike(String value) {
            addCriterion("lqdz not like", value, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzIn(List<String> values) {
            addCriterion("lqdz in", values, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzNotIn(List<String> values) {
            addCriterion("lqdz not in", values, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzBetween(String value1, String value2) {
            addCriterion("lqdz between", value1, value2, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdzNotBetween(String value1, String value2) {
            addCriterion("lqdz not between", value1, value2, "lqdz");
            return (Criteria) this;
        }

        public Criteria andLqdhIsNull() {
            addCriterion("lqdh is null");
            return (Criteria) this;
        }

        public Criteria andLqdhIsNotNull() {
            addCriterion("lqdh is not null");
            return (Criteria) this;
        }

        public Criteria andLqdhEqualTo(String value) {
            addCriterion("lqdh =", value, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhNotEqualTo(String value) {
            addCriterion("lqdh <>", value, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhGreaterThan(String value) {
            addCriterion("lqdh >", value, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhGreaterThanOrEqualTo(String value) {
            addCriterion("lqdh >=", value, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhLessThan(String value) {
            addCriterion("lqdh <", value, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhLessThanOrEqualTo(String value) {
            addCriterion("lqdh <=", value, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhLike(String value) {
            addCriterion("lqdh like", value, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhNotLike(String value) {
            addCriterion("lqdh not like", value, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhIn(List<String> values) {
            addCriterion("lqdh in", values, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhNotIn(List<String> values) {
            addCriterion("lqdh not in", values, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhBetween(String value1, String value2) {
            addCriterion("lqdh between", value1, value2, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqdhNotBetween(String value1, String value2) {
            addCriterion("lqdh not between", value1, value2, "lqdh");
            return (Criteria) this;
        }

        public Criteria andLqrxmIsNull() {
            addCriterion("lqrxm is null");
            return (Criteria) this;
        }

        public Criteria andLqrxmIsNotNull() {
            addCriterion("lqrxm is not null");
            return (Criteria) this;
        }

        public Criteria andLqrxmEqualTo(String value) {
            addCriterion("lqrxm =", value, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmNotEqualTo(String value) {
            addCriterion("lqrxm <>", value, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmGreaterThan(String value) {
            addCriterion("lqrxm >", value, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmGreaterThanOrEqualTo(String value) {
            addCriterion("lqrxm >=", value, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmLessThan(String value) {
            addCriterion("lqrxm <", value, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmLessThanOrEqualTo(String value) {
            addCriterion("lqrxm <=", value, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmLike(String value) {
            addCriterion("lqrxm like", value, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmNotLike(String value) {
            addCriterion("lqrxm not like", value, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmIn(List<String> values) {
            addCriterion("lqrxm in", values, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmNotIn(List<String> values) {
            addCriterion("lqrxm not in", values, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmBetween(String value1, String value2) {
            addCriterion("lqrxm between", value1, value2, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrxmNotBetween(String value1, String value2) {
            addCriterion("lqrxm not between", value1, value2, "lqrxm");
            return (Criteria) this;
        }

        public Criteria andLqrdhIsNull() {
            addCriterion("lqrdh is null");
            return (Criteria) this;
        }

        public Criteria andLqrdhIsNotNull() {
            addCriterion("lqrdh is not null");
            return (Criteria) this;
        }

        public Criteria andLqrdhEqualTo(String value) {
            addCriterion("lqrdh =", value, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhNotEqualTo(String value) {
            addCriterion("lqrdh <>", value, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhGreaterThan(String value) {
            addCriterion("lqrdh >", value, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhGreaterThanOrEqualTo(String value) {
            addCriterion("lqrdh >=", value, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhLessThan(String value) {
            addCriterion("lqrdh <", value, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhLessThanOrEqualTo(String value) {
            addCriterion("lqrdh <=", value, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhLike(String value) {
            addCriterion("lqrdh like", value, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhNotLike(String value) {
            addCriterion("lqrdh not like", value, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhIn(List<String> values) {
            addCriterion("lqrdh in", values, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhNotIn(List<String> values) {
            addCriterion("lqrdh not in", values, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhBetween(String value1, String value2) {
            addCriterion("lqrdh between", value1, value2, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrdhNotBetween(String value1, String value2) {
            addCriterion("lqrdh not between", value1, value2, "lqrdh");
            return (Criteria) this;
        }

        public Criteria andLqrqIsNull() {
            addCriterion("lqrq is null");
            return (Criteria) this;
        }

        public Criteria andLqrqIsNotNull() {
            addCriterion("lqrq is not null");
            return (Criteria) this;
        }

        public Criteria andLqrqEqualTo(Date value) {
            addCriterion("lqrq =", value, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqNotEqualTo(Date value) {
            addCriterion("lqrq <>", value, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqGreaterThan(Date value) {
            addCriterion("lqrq >", value, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqGreaterThanOrEqualTo(Date value) {
            addCriterion("lqrq >=", value, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqGreaterThanOrEqualTo(String value) {
            addCriterion(" DATE_FORMAT(lqrq,'%Y-%m-%d') >=", value, "lqrq");
            return (Criteria) this;
        }
        public Criteria andLqrqLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(lqrq,'%Y-%m-%d')  <=", value, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqLessThan(Date value) {
            addCriterion("lqrq <", value, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqLessThanOrEqualTo(Date value) {
            addCriterion("lqrq <=", value, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqIn(List<Date> values) {
            addCriterion("lqrq in", values, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqNotIn(List<Date> values) {
            addCriterion("lqrq not in", values, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqBetween(Date value1, Date value2) {
            addCriterion("lqrq between", value1, value2, "lqrq");
            return (Criteria) this;
        }

        public Criteria andLqrqNotBetween(Date value1, Date value2) {
            addCriterion("lqrq not between", value1, value2, "lqrq");
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

        public Criteria andLqsmIsNull() {
            addCriterion("lqsm is null");
            return (Criteria) this;
        }

        public Criteria andLqsmIsNotNull() {
            addCriterion("lqsm is not null");
            return (Criteria) this;
        }

        public Criteria andLqsmEqualTo(String value) {
            addCriterion("lqsm =", value, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmNotEqualTo(String value) {
            addCriterion("lqsm <>", value, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmGreaterThan(String value) {
            addCriterion("lqsm >", value, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmGreaterThanOrEqualTo(String value) {
            addCriterion("lqsm >=", value, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmLessThan(String value) {
            addCriterion("lqsm <", value, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmLessThanOrEqualTo(String value) {
            addCriterion("lqsm <=", value, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmLike(String value) {
            addCriterion("lqsm like", value, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmNotLike(String value) {
            addCriterion("lqsm not like", value, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmIn(List<String> values) {
            addCriterion("lqsm in", values, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmNotIn(List<String> values) {
            addCriterion("lqsm not in", values, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmBetween(String value1, String value2) {
            addCriterion("lqsm between", value1, value2, "lqsm");
            return (Criteria) this;
        }

        public Criteria andLqsmNotBetween(String value1, String value2) {
            addCriterion("lqsm not between", value1, value2, "lqsm");
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