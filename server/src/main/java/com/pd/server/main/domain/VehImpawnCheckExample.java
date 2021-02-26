package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehImpawnCheckExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehImpawnCheckExample() {
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
        public VehImpawnCheckExample.Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion(" DATE_FORMAT(create_time,'%Y-%m-%d') >=", value, "createTime");
            return (VehImpawnCheckExample.Criteria) this;
        }
        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }
        public VehImpawnCheckExample.Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d')  <=", value, "createTime");
            return (VehImpawnCheckExample.Criteria) this;
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

        public Criteria andZhthIsNull() {
            addCriterion("zhth is null");
            return (Criteria) this;
        }

        public Criteria andZhthIsNotNull() {
            addCriterion("zhth is not null");
            return (Criteria) this;
        }

        public Criteria andZhthEqualTo(String value) {
            addCriterion("zhth =", value, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthNotEqualTo(String value) {
            addCriterion("zhth <>", value, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthGreaterThan(String value) {
            addCriterion("zhth >", value, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthGreaterThanOrEqualTo(String value) {
            addCriterion("zhth >=", value, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthLessThan(String value) {
            addCriterion("zhth <", value, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthLessThanOrEqualTo(String value) {
            addCriterion("zhth <=", value, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthLike(String value) {
            addCriterion("zhth like", value, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthNotLike(String value) {
            addCriterion("zhth not like", value, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthIn(List<String> values) {
            addCriterion("zhth in", values, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthNotIn(List<String> values) {
            addCriterion("zhth not in", values, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthBetween(String value1, String value2) {
            addCriterion("zhth between", value1, value2, "zhth");
            return (Criteria) this;
        }

        public Criteria andZhthNotBetween(String value1, String value2) {
            addCriterion("zhth not between", value1, value2, "zhth");
            return (Criteria) this;
        }

        public Criteria andDyhthIsNull() {
            addCriterion("dyhth is null");
            return (Criteria) this;
        }

        public Criteria andDyhthIsNotNull() {
            addCriterion("dyhth is not null");
            return (Criteria) this;
        }

        public Criteria andDyhthEqualTo(String value) {
            addCriterion("dyhth =", value, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthNotEqualTo(String value) {
            addCriterion("dyhth <>", value, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthGreaterThan(String value) {
            addCriterion("dyhth >", value, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthGreaterThanOrEqualTo(String value) {
            addCriterion("dyhth >=", value, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthLessThan(String value) {
            addCriterion("dyhth <", value, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthLessThanOrEqualTo(String value) {
            addCriterion("dyhth <=", value, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthLike(String value) {
            addCriterion("dyhth like", value, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthNotLike(String value) {
            addCriterion("dyhth not like", value, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthIn(List<String> values) {
            addCriterion("dyhth in", values, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthNotIn(List<String> values) {
            addCriterion("dyhth not in", values, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthBetween(String value1, String value2) {
            addCriterion("dyhth between", value1, value2, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyhthNotBetween(String value1, String value2) {
            addCriterion("dyhth not between", value1, value2, "dyhth");
            return (Criteria) this;
        }

        public Criteria andDyjeIsNull() {
            addCriterion("dyje is null");
            return (Criteria) this;
        }

        public Criteria andDyjeIsNotNull() {
            addCriterion("dyje is not null");
            return (Criteria) this;
        }

        public Criteria andDyjeEqualTo(Double value) {
            addCriterion("dyje =", value, "dyje");
            return (Criteria) this;
        }

        public Criteria andDyjeNotEqualTo(Double value) {
            addCriterion("dyje <>", value, "dyje");
            return (Criteria) this;
        }

        public Criteria andDyjeGreaterThan(Double value) {
            addCriterion("dyje >", value, "dyje");
            return (Criteria) this;
        }

        public Criteria andDyjeGreaterThanOrEqualTo(Double value) {
            addCriterion("dyje >=", value, "dyje");
            return (Criteria) this;
        }

        public Criteria andDyjeLessThan(Double value) {
            addCriterion("dyje <", value, "dyje");
            return (Criteria) this;
        }

        public Criteria andDyjeLessThanOrEqualTo(Double value) {
            addCriterion("dyje <=", value, "dyje");
            return (Criteria) this;
        }

        public Criteria andDyjeIn(List<Double> values) {
            addCriterion("dyje in", values, "dyje");
            return (Criteria) this;
        }

        public Criteria andDyjeNotIn(List<Double> values) {
            addCriterion("dyje not in", values, "dyje");
            return (Criteria) this;
        }

        public Criteria andDyjeBetween(Double value1, Double value2) {
            addCriterion("dyje between", value1, value2, "dyje");
            return (Criteria) this;
        }

        public Criteria andDyjeNotBetween(Double value1, Double value2) {
            addCriterion("dyje not between", value1, value2, "dyje");
            return (Criteria) this;
        }

        public Criteria andDydjrqIsNull() {
            addCriterion("dydjrq is null");
            return (Criteria) this;
        }

        public Criteria andDydjrqIsNotNull() {
            addCriterion("dydjrq is not null");
            return (Criteria) this;
        }

        public Criteria andDydjrqEqualTo(Date value) {
            addCriterion("dydjrq =", value, "dydjrq");
            return (Criteria) this;
        }

        public Criteria andDydjrqNotEqualTo(Date value) {
            addCriterion("dydjrq <>", value, "dydjrq");
            return (Criteria) this;
        }

        public Criteria andDydjrqGreaterThan(Date value) {
            addCriterion("dydjrq >", value, "dydjrq");
            return (Criteria) this;
        }

        public Criteria andDydjrqGreaterThanOrEqualTo(Date value) {
            addCriterion("dydjrq >=", value, "dydjrq");
            return (Criteria) this;
        }

        public Criteria andDydjrqLessThan(Date value) {
            addCriterion("dydjrq <", value, "dydjrq");
            return (Criteria) this;
        }

        public Criteria andDydjrqLessThanOrEqualTo(Date value) {
            addCriterion("dydjrq <=", value, "dydjrq");
            return (Criteria) this;
        }

        public Criteria andDydjrqIn(List<Date> values) {
            addCriterion("dydjrq in", values, "dydjrq");
            return (Criteria) this;
        }

        public Criteria andDydjrqNotIn(List<Date> values) {
            addCriterion("dydjrq not in", values, "dydjrq");
            return (Criteria) this;
        }

        public Criteria andDydjrqBetween(Date value1, Date value2) {
            addCriterion("dydjrq between", value1, value2, "dydjrq");
            return (Criteria) this;
        }

        public Criteria andDydjrqNotBetween(Date value1, Date value2) {
            addCriterion("dydjrq not between", value1, value2, "dydjrq");
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

        public Criteria andSfzmmcIsNull() {
            addCriterion("sfzmmc is null");
            return (Criteria) this;
        }

        public Criteria andSfzmmcIsNotNull() {
            addCriterion("sfzmmc is not null");
            return (Criteria) this;
        }

        public Criteria andSfzmmcEqualTo(String value) {
            addCriterion("sfzmmc =", value, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcNotEqualTo(String value) {
            addCriterion("sfzmmc <>", value, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcGreaterThan(String value) {
            addCriterion("sfzmmc >", value, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcGreaterThanOrEqualTo(String value) {
            addCriterion("sfzmmc >=", value, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcLessThan(String value) {
            addCriterion("sfzmmc <", value, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcLessThanOrEqualTo(String value) {
            addCriterion("sfzmmc <=", value, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcLike(String value) {
            addCriterion("sfzmmc like", value, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcNotLike(String value) {
            addCriterion("sfzmmc not like", value, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcIn(List<String> values) {
            addCriterion("sfzmmc in", values, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcNotIn(List<String> values) {
            addCriterion("sfzmmc not in", values, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcBetween(String value1, String value2) {
            addCriterion("sfzmmc between", value1, value2, "sfzmmc");
            return (Criteria) this;
        }

        public Criteria andSfzmmcNotBetween(String value1, String value2) {
            addCriterion("sfzmmc not between", value1, value2, "sfzmmc");
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

        public Criteria andXzqhIsNull() {
            addCriterion("xzqh is null");
            return (Criteria) this;
        }

        public Criteria andXzqhIsNotNull() {
            addCriterion("xzqh is not null");
            return (Criteria) this;
        }

        public Criteria andXzqhEqualTo(String value) {
            addCriterion("xzqh =", value, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhNotEqualTo(String value) {
            addCriterion("xzqh <>", value, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhGreaterThan(String value) {
            addCriterion("xzqh >", value, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhGreaterThanOrEqualTo(String value) {
            addCriterion("xzqh >=", value, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhLessThan(String value) {
            addCriterion("xzqh <", value, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhLessThanOrEqualTo(String value) {
            addCriterion("xzqh <=", value, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhLike(String value) {
            addCriterion("xzqh like", value, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhNotLike(String value) {
            addCriterion("xzqh not like", value, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhIn(List<String> values) {
            addCriterion("xzqh in", values, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhNotIn(List<String> values) {
            addCriterion("xzqh not in", values, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhBetween(String value1, String value2) {
            addCriterion("xzqh between", value1, value2, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXzqhNotBetween(String value1, String value2) {
            addCriterion("xzqh not between", value1, value2, "xzqh");
            return (Criteria) this;
        }

        public Criteria andXxdzIsNull() {
            addCriterion("xxdz is null");
            return (Criteria) this;
        }

        public Criteria andXxdzIsNotNull() {
            addCriterion("xxdz is not null");
            return (Criteria) this;
        }

        public Criteria andXxdzEqualTo(String value) {
            addCriterion("xxdz =", value, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzNotEqualTo(String value) {
            addCriterion("xxdz <>", value, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzGreaterThan(String value) {
            addCriterion("xxdz >", value, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzGreaterThanOrEqualTo(String value) {
            addCriterion("xxdz >=", value, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzLessThan(String value) {
            addCriterion("xxdz <", value, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzLessThanOrEqualTo(String value) {
            addCriterion("xxdz <=", value, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzLike(String value) {
            addCriterion("xxdz like", value, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzNotLike(String value) {
            addCriterion("xxdz not like", value, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzIn(List<String> values) {
            addCriterion("xxdz in", values, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzNotIn(List<String> values) {
            addCriterion("xxdz not in", values, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzBetween(String value1, String value2) {
            addCriterion("xxdz between", value1, value2, "xxdz");
            return (Criteria) this;
        }

        public Criteria andXxdzNotBetween(String value1, String value2) {
            addCriterion("xxdz not between", value1, value2, "xxdz");
            return (Criteria) this;
        }

        public Criteria andYzbmIsNull() {
            addCriterion("yzbm is null");
            return (Criteria) this;
        }

        public Criteria andYzbmIsNotNull() {
            addCriterion("yzbm is not null");
            return (Criteria) this;
        }

        public Criteria andYzbmEqualTo(String value) {
            addCriterion("yzbm =", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmNotEqualTo(String value) {
            addCriterion("yzbm <>", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmGreaterThan(String value) {
            addCriterion("yzbm >", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmGreaterThanOrEqualTo(String value) {
            addCriterion("yzbm >=", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmLessThan(String value) {
            addCriterion("yzbm <", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmLessThanOrEqualTo(String value) {
            addCriterion("yzbm <=", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmLike(String value) {
            addCriterion("yzbm like", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmNotLike(String value) {
            addCriterion("yzbm not like", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmIn(List<String> values) {
            addCriterion("yzbm in", values, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmNotIn(List<String> values) {
            addCriterion("yzbm not in", values, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmBetween(String value1, String value2) {
            addCriterion("yzbm between", value1, value2, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmNotBetween(String value1, String value2) {
            addCriterion("yzbm not between", value1, value2, "yzbm");
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

        public Criteria andJcrqIsNull() {
            addCriterion("jcrq is null");
            return (Criteria) this;
        }

        public Criteria andJcrqIsNotNull() {
            addCriterion("jcrq is not null");
            return (Criteria) this;
        }

        public Criteria andJcrqEqualTo(Date value) {
            addCriterion("jcrq =", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqNotEqualTo(Date value) {
            addCriterion("jcrq <>", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqGreaterThan(Date value) {
            addCriterion("jcrq >", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqGreaterThanOrEqualTo(Date value) {
            addCriterion("jcrq >=", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqLessThan(Date value) {
            addCriterion("jcrq <", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqLessThanOrEqualTo(Date value) {
            addCriterion("jcrq <=", value, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqIn(List<Date> values) {
            addCriterion("jcrq in", values, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqNotIn(List<Date> values) {
            addCriterion("jcrq not in", values, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqBetween(Date value1, Date value2) {
            addCriterion("jcrq between", value1, value2, "jcrq");
            return (Criteria) this;
        }

        public Criteria andJcrqNotBetween(Date value1, Date value2) {
            addCriterion("jcrq not between", value1, value2, "jcrq");
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

        public Criteria andXgzlIsNull() {
            addCriterion("xgzl is null");
            return (Criteria) this;
        }

        public Criteria andXgzlIsNotNull() {
            addCriterion("xgzl is not null");
            return (Criteria) this;
        }

        public Criteria andXgzlEqualTo(String value) {
            addCriterion("xgzl =", value, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlNotEqualTo(String value) {
            addCriterion("xgzl <>", value, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlGreaterThan(String value) {
            addCriterion("xgzl >", value, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlGreaterThanOrEqualTo(String value) {
            addCriterion("xgzl >=", value, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlLessThan(String value) {
            addCriterion("xgzl <", value, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlLessThanOrEqualTo(String value) {
            addCriterion("xgzl <=", value, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlLike(String value) {
            addCriterion("xgzl like", value, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlNotLike(String value) {
            addCriterion("xgzl not like", value, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlIn(List<String> values) {
            addCriterion("xgzl in", values, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlNotIn(List<String> values) {
            addCriterion("xgzl not in", values, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlBetween(String value1, String value2) {
            addCriterion("xgzl between", value1, value2, "xgzl");
            return (Criteria) this;
        }

        public Criteria andXgzlNotBetween(String value1, String value2) {
            addCriterion("xgzl not between", value1, value2, "xgzl");
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