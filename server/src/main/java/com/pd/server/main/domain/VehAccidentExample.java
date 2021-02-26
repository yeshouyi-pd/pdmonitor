package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehAccidentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehAccidentExample() {
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

        public Criteria andSgbhIsNull() {
            addCriterion("sgbh is null");
            return (Criteria) this;
        }

        public Criteria andSgbhIsNotNull() {
            addCriterion("sgbh is not null");
            return (Criteria) this;
        }

        public Criteria andSgbhEqualTo(String value) {
            addCriterion("sgbh =", value, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhNotEqualTo(String value) {
            addCriterion("sgbh <>", value, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhGreaterThan(String value) {
            addCriterion("sgbh >", value, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhGreaterThanOrEqualTo(String value) {
            addCriterion("sgbh >=", value, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhLessThan(String value) {
            addCriterion("sgbh <", value, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhLessThanOrEqualTo(String value) {
            addCriterion("sgbh <=", value, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhLike(String value) {
            addCriterion("sgbh like", value, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhNotLike(String value) {
            addCriterion("sgbh not like", value, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhIn(List<String> values) {
            addCriterion("sgbh in", values, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhNotIn(List<String> values) {
            addCriterion("sgbh not in", values, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhBetween(String value1, String value2) {
            addCriterion("sgbh between", value1, value2, "sgbh");
            return (Criteria) this;
        }

        public Criteria andSgbhNotBetween(String value1, String value2) {
            addCriterion("sgbh not between", value1, value2, "sgbh");
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

        public Criteria andSgfssjIsNull() {
            addCriterion("sgfssj is null");
            return (Criteria) this;
        }

        public Criteria andSgfssjIsNotNull() {
            addCriterion("sgfssj is not null");
            return (Criteria) this;
        }

        public Criteria andSgfssjEqualTo(String value) {
            addCriterion("sgfssj =", value, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjNotEqualTo(String value) {
            addCriterion("sgfssj <>", value, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjGreaterThan(String value) {
            addCriterion("sgfssj >", value, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjGreaterThanOrEqualTo(String value) {
            addCriterion("sgfssj >=", value, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjLessThan(String value) {
            addCriterion("sgfssj <", value, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjLessThanOrEqualTo(String value) {
            addCriterion("sgfssj <=", value, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjLike(String value) {
            addCriterion("sgfssj like", value, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjNotLike(String value) {
            addCriterion("sgfssj not like", value, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjIn(List<String> values) {
            addCriterion("sgfssj in", values, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjNotIn(List<String> values) {
            addCriterion("sgfssj not in", values, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjBetween(String value1, String value2) {
            addCriterion("sgfssj between", value1, value2, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgfssjNotBetween(String value1, String value2) {
            addCriterion("sgfssj not between", value1, value2, "sgfssj");
            return (Criteria) this;
        }

        public Criteria andSgddIsNull() {
            addCriterion("sgdd is null");
            return (Criteria) this;
        }

        public Criteria andSgddIsNotNull() {
            addCriterion("sgdd is not null");
            return (Criteria) this;
        }

        public Criteria andSgddEqualTo(String value) {
            addCriterion("sgdd =", value, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddNotEqualTo(String value) {
            addCriterion("sgdd <>", value, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddGreaterThan(String value) {
            addCriterion("sgdd >", value, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddGreaterThanOrEqualTo(String value) {
            addCriterion("sgdd >=", value, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddLessThan(String value) {
            addCriterion("sgdd <", value, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddLessThanOrEqualTo(String value) {
            addCriterion("sgdd <=", value, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddLike(String value) {
            addCriterion("sgdd like", value, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddNotLike(String value) {
            addCriterion("sgdd not like", value, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddIn(List<String> values) {
            addCriterion("sgdd in", values, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddNotIn(List<String> values) {
            addCriterion("sgdd not in", values, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddBetween(String value1, String value2) {
            addCriterion("sgdd between", value1, value2, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgddNotBetween(String value1, String value2) {
            addCriterion("sgdd not between", value1, value2, "sgdd");
            return (Criteria) this;
        }

        public Criteria andSgqxIsNull() {
            addCriterion("sgqx is null");
            return (Criteria) this;
        }

        public Criteria andSgqxIsNotNull() {
            addCriterion("sgqx is not null");
            return (Criteria) this;
        }

        public Criteria andSgqxEqualTo(String value) {
            addCriterion("sgqx =", value, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxNotEqualTo(String value) {
            addCriterion("sgqx <>", value, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxGreaterThan(String value) {
            addCriterion("sgqx >", value, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxGreaterThanOrEqualTo(String value) {
            addCriterion("sgqx >=", value, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxLessThan(String value) {
            addCriterion("sgqx <", value, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxLessThanOrEqualTo(String value) {
            addCriterion("sgqx <=", value, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxLike(String value) {
            addCriterion("sgqx like", value, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxNotLike(String value) {
            addCriterion("sgqx not like", value, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxIn(List<String> values) {
            addCriterion("sgqx in", values, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxNotIn(List<String> values) {
            addCriterion("sgqx not in", values, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxBetween(String value1, String value2) {
            addCriterion("sgqx between", value1, value2, "sgqx");
            return (Criteria) this;
        }

        public Criteria andSgqxNotBetween(String value1, String value2) {
            addCriterion("sgqx not between", value1, value2, "sgqx");
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

        public Criteria andDzzbIsNull() {
            addCriterion("dzzb is null");
            return (Criteria) this;
        }

        public Criteria andDzzbIsNotNull() {
            addCriterion("dzzb is not null");
            return (Criteria) this;
        }

        public Criteria andDzzbEqualTo(String value) {
            addCriterion("dzzb =", value, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbNotEqualTo(String value) {
            addCriterion("dzzb <>", value, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbGreaterThan(String value) {
            addCriterion("dzzb >", value, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbGreaterThanOrEqualTo(String value) {
            addCriterion("dzzb >=", value, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbLessThan(String value) {
            addCriterion("dzzb <", value, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbLessThanOrEqualTo(String value) {
            addCriterion("dzzb <=", value, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbLike(String value) {
            addCriterion("dzzb like", value, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbNotLike(String value) {
            addCriterion("dzzb not like", value, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbIn(List<String> values) {
            addCriterion("dzzb in", values, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbNotIn(List<String> values) {
            addCriterion("dzzb not in", values, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbBetween(String value1, String value2) {
            addCriterion("dzzb between", value1, value2, "dzzb");
            return (Criteria) this;
        }

        public Criteria andDzzbNotBetween(String value1, String value2) {
            addCriterion("dzzb not between", value1, value2, "dzzb");
            return (Criteria) this;
        }

        public Criteria andSfgjIsNull() {
            addCriterion("sfgj is null");
            return (Criteria) this;
        }

        public Criteria andSfgjIsNotNull() {
            addCriterion("sfgj is not null");
            return (Criteria) this;
        }

        public Criteria andSfgjEqualTo(String value) {
            addCriterion("sfgj =", value, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjNotEqualTo(String value) {
            addCriterion("sfgj <>", value, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjGreaterThan(String value) {
            addCriterion("sfgj >", value, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjGreaterThanOrEqualTo(String value) {
            addCriterion("sfgj >=", value, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjLessThan(String value) {
            addCriterion("sfgj <", value, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjLessThanOrEqualTo(String value) {
            addCriterion("sfgj <=", value, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjLike(String value) {
            addCriterion("sfgj like", value, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjNotLike(String value) {
            addCriterion("sfgj not like", value, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjIn(List<String> values) {
            addCriterion("sfgj in", values, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjNotIn(List<String> values) {
            addCriterion("sfgj not in", values, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjBetween(String value1, String value2) {
            addCriterion("sfgj between", value1, value2, "sfgj");
            return (Criteria) this;
        }

        public Criteria andSfgjNotBetween(String value1, String value2) {
            addCriterion("sfgj not between", value1, value2, "sfgj");
            return (Criteria) this;
        }

        public Criteria andTqIsNull() {
            addCriterion("tq is null");
            return (Criteria) this;
        }

        public Criteria andTqIsNotNull() {
            addCriterion("tq is not null");
            return (Criteria) this;
        }

        public Criteria andTqEqualTo(String value) {
            addCriterion("tq =", value, "tq");
            return (Criteria) this;
        }

        public Criteria andTqNotEqualTo(String value) {
            addCriterion("tq <>", value, "tq");
            return (Criteria) this;
        }

        public Criteria andTqGreaterThan(String value) {
            addCriterion("tq >", value, "tq");
            return (Criteria) this;
        }

        public Criteria andTqGreaterThanOrEqualTo(String value) {
            addCriterion("tq >=", value, "tq");
            return (Criteria) this;
        }

        public Criteria andTqLessThan(String value) {
            addCriterion("tq <", value, "tq");
            return (Criteria) this;
        }

        public Criteria andTqLessThanOrEqualTo(String value) {
            addCriterion("tq <=", value, "tq");
            return (Criteria) this;
        }

        public Criteria andTqLike(String value) {
            addCriterion("tq like", value, "tq");
            return (Criteria) this;
        }

        public Criteria andTqNotLike(String value) {
            addCriterion("tq not like", value, "tq");
            return (Criteria) this;
        }

        public Criteria andTqIn(List<String> values) {
            addCriterion("tq in", values, "tq");
            return (Criteria) this;
        }

        public Criteria andTqNotIn(List<String> values) {
            addCriterion("tq not in", values, "tq");
            return (Criteria) this;
        }

        public Criteria andTqBetween(String value1, String value2) {
            addCriterion("tq between", value1, value2, "tq");
            return (Criteria) this;
        }

        public Criteria andTqNotBetween(String value1, String value2) {
            addCriterion("tq not between", value1, value2, "tq");
            return (Criteria) this;
        }

        public Criteria andYhdhIsNull() {
            addCriterion("yhdh is null");
            return (Criteria) this;
        }

        public Criteria andYhdhIsNotNull() {
            addCriterion("yhdh is not null");
            return (Criteria) this;
        }

        public Criteria andYhdhEqualTo(String value) {
            addCriterion("yhdh =", value, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhNotEqualTo(String value) {
            addCriterion("yhdh <>", value, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhGreaterThan(String value) {
            addCriterion("yhdh >", value, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhGreaterThanOrEqualTo(String value) {
            addCriterion("yhdh >=", value, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhLessThan(String value) {
            addCriterion("yhdh <", value, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhLessThanOrEqualTo(String value) {
            addCriterion("yhdh <=", value, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhLike(String value) {
            addCriterion("yhdh like", value, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhNotLike(String value) {
            addCriterion("yhdh not like", value, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhIn(List<String> values) {
            addCriterion("yhdh in", values, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhNotIn(List<String> values) {
            addCriterion("yhdh not in", values, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhBetween(String value1, String value2) {
            addCriterion("yhdh between", value1, value2, "yhdh");
            return (Criteria) this;
        }

        public Criteria andYhdhNotBetween(String value1, String value2) {
            addCriterion("yhdh not between", value1, value2, "yhdh");
            return (Criteria) this;
        }

        public Criteria andClfsIsNull() {
            addCriterion("clfs is null");
            return (Criteria) this;
        }

        public Criteria andClfsIsNotNull() {
            addCriterion("clfs is not null");
            return (Criteria) this;
        }

        public Criteria andClfsEqualTo(String value) {
            addCriterion("clfs =", value, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsNotEqualTo(String value) {
            addCriterion("clfs <>", value, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsGreaterThan(String value) {
            addCriterion("clfs >", value, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsGreaterThanOrEqualTo(String value) {
            addCriterion("clfs >=", value, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsLessThan(String value) {
            addCriterion("clfs <", value, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsLessThanOrEqualTo(String value) {
            addCriterion("clfs <=", value, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsLike(String value) {
            addCriterion("clfs like", value, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsNotLike(String value) {
            addCriterion("clfs not like", value, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsIn(List<String> values) {
            addCriterion("clfs in", values, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsNotIn(List<String> values) {
            addCriterion("clfs not in", values, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsBetween(String value1, String value2) {
            addCriterion("clfs between", value1, value2, "clfs");
            return (Criteria) this;
        }

        public Criteria andClfsNotBetween(String value1, String value2) {
            addCriterion("clfs not between", value1, value2, "clfs");
            return (Criteria) this;
        }

        public Criteria andDsrsmIsNull() {
            addCriterion("dsrsm is null");
            return (Criteria) this;
        }

        public Criteria andDsrsmIsNotNull() {
            addCriterion("dsrsm is not null");
            return (Criteria) this;
        }

        public Criteria andDsrsmEqualTo(String value) {
            addCriterion("dsrsm =", value, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmNotEqualTo(String value) {
            addCriterion("dsrsm <>", value, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmGreaterThan(String value) {
            addCriterion("dsrsm >", value, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmGreaterThanOrEqualTo(String value) {
            addCriterion("dsrsm >=", value, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmLessThan(String value) {
            addCriterion("dsrsm <", value, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmLessThanOrEqualTo(String value) {
            addCriterion("dsrsm <=", value, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmLike(String value) {
            addCriterion("dsrsm like", value, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmNotLike(String value) {
            addCriterion("dsrsm not like", value, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmIn(List<String> values) {
            addCriterion("dsrsm in", values, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmNotIn(List<String> values) {
            addCriterion("dsrsm not in", values, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmBetween(String value1, String value2) {
            addCriterion("dsrsm between", value1, value2, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrsmNotBetween(String value1, String value2) {
            addCriterion("dsrsm not between", value1, value2, "dsrsm");
            return (Criteria) this;
        }

        public Criteria andDsrss1IsNull() {
            addCriterion("dsrss1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrss1IsNotNull() {
            addCriterion("dsrss1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrss1EqualTo(String value) {
            addCriterion("dsrss1 =", value, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1NotEqualTo(String value) {
            addCriterion("dsrss1 <>", value, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1GreaterThan(String value) {
            addCriterion("dsrss1 >", value, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrss1 >=", value, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1LessThan(String value) {
            addCriterion("dsrss1 <", value, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1LessThanOrEqualTo(String value) {
            addCriterion("dsrss1 <=", value, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1Like(String value) {
            addCriterion("dsrss1 like", value, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1NotLike(String value) {
            addCriterion("dsrss1 not like", value, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1In(List<String> values) {
            addCriterion("dsrss1 in", values, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1NotIn(List<String> values) {
            addCriterion("dsrss1 not in", values, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1Between(String value1, String value2) {
            addCriterion("dsrss1 between", value1, value2, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrss1NotBetween(String value1, String value2) {
            addCriterion("dsrss1 not between", value1, value2, "dsrss1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1IsNull() {
            addCriterion("dsrxm1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrxm1IsNotNull() {
            addCriterion("dsrxm1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrxm1EqualTo(String value) {
            addCriterion("dsrxm1 =", value, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1NotEqualTo(String value) {
            addCriterion("dsrxm1 <>", value, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1GreaterThan(String value) {
            addCriterion("dsrxm1 >", value, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrxm1 >=", value, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1LessThan(String value) {
            addCriterion("dsrxm1 <", value, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1LessThanOrEqualTo(String value) {
            addCriterion("dsrxm1 <=", value, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1Like(String value) {
            addCriterion("dsrxm1 like", value, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1NotLike(String value) {
            addCriterion("dsrxm1 not like", value, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1In(List<String> values) {
            addCriterion("dsrxm1 in", values, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1NotIn(List<String> values) {
            addCriterion("dsrxm1 not in", values, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1Between(String value1, String value2) {
            addCriterion("dsrxm1 between", value1, value2, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrxm1NotBetween(String value1, String value2) {
            addCriterion("dsrxm1 not between", value1, value2, "dsrxm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1IsNull() {
            addCriterion("dsrsfzmhm1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1IsNotNull() {
            addCriterion("dsrsfzmhm1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1EqualTo(String value) {
            addCriterion("dsrsfzmhm1 =", value, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1NotEqualTo(String value) {
            addCriterion("dsrsfzmhm1 <>", value, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1GreaterThan(String value) {
            addCriterion("dsrsfzmhm1 >", value, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrsfzmhm1 >=", value, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1LessThan(String value) {
            addCriterion("dsrsfzmhm1 <", value, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1LessThanOrEqualTo(String value) {
            addCriterion("dsrsfzmhm1 <=", value, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1Like(String value) {
            addCriterion("dsrsfzmhm1 like", value, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1NotLike(String value) {
            addCriterion("dsrsfzmhm1 not like", value, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1In(List<String> values) {
            addCriterion("dsrsfzmhm1 in", values, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1NotIn(List<String> values) {
            addCriterion("dsrsfzmhm1 not in", values, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1Between(String value1, String value2) {
            addCriterion("dsrsfzmhm1 between", value1, value2, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm1NotBetween(String value1, String value2) {
            addCriterion("dsrsfzmhm1 not between", value1, value2, "dsrsfzmhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1IsNull() {
            addCriterion("dsrsjhm1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1IsNotNull() {
            addCriterion("dsrsjhm1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1EqualTo(String value) {
            addCriterion("dsrsjhm1 =", value, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1NotEqualTo(String value) {
            addCriterion("dsrsjhm1 <>", value, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1GreaterThan(String value) {
            addCriterion("dsrsjhm1 >", value, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrsjhm1 >=", value, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1LessThan(String value) {
            addCriterion("dsrsjhm1 <", value, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1LessThanOrEqualTo(String value) {
            addCriterion("dsrsjhm1 <=", value, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1Like(String value) {
            addCriterion("dsrsjhm1 like", value, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1NotLike(String value) {
            addCriterion("dsrsjhm1 not like", value, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1In(List<String> values) {
            addCriterion("dsrsjhm1 in", values, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1NotIn(List<String> values) {
            addCriterion("dsrsjhm1 not in", values, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1Between(String value1, String value2) {
            addCriterion("dsrsjhm1 between", value1, value2, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm1NotBetween(String value1, String value2) {
            addCriterion("dsrsjhm1 not between", value1, value2, "dsrsjhm1");
            return (Criteria) this;
        }

        public Criteria andHpzl1IsNull() {
            addCriterion("hpzl1 is null");
            return (Criteria) this;
        }

        public Criteria andHpzl1IsNotNull() {
            addCriterion("hpzl1 is not null");
            return (Criteria) this;
        }

        public Criteria andHpzl1EqualTo(String value) {
            addCriterion("hpzl1 =", value, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1NotEqualTo(String value) {
            addCriterion("hpzl1 <>", value, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1GreaterThan(String value) {
            addCriterion("hpzl1 >", value, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1GreaterThanOrEqualTo(String value) {
            addCriterion("hpzl1 >=", value, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1LessThan(String value) {
            addCriterion("hpzl1 <", value, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1LessThanOrEqualTo(String value) {
            addCriterion("hpzl1 <=", value, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1Like(String value) {
            addCriterion("hpzl1 like", value, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1NotLike(String value) {
            addCriterion("hpzl1 not like", value, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1In(List<String> values) {
            addCriterion("hpzl1 in", values, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1NotIn(List<String> values) {
            addCriterion("hpzl1 not in", values, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1Between(String value1, String value2) {
            addCriterion("hpzl1 between", value1, value2, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHpzl1NotBetween(String value1, String value2) {
            addCriterion("hpzl1 not between", value1, value2, "hpzl1");
            return (Criteria) this;
        }

        public Criteria andHphm1IsNull() {
            addCriterion("hphm1 is null");
            return (Criteria) this;
        }

        public Criteria andHphm1IsNotNull() {
            addCriterion("hphm1 is not null");
            return (Criteria) this;
        }

        public Criteria andHphm1EqualTo(String value) {
            addCriterion("hphm1 =", value, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1NotEqualTo(String value) {
            addCriterion("hphm1 <>", value, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1GreaterThan(String value) {
            addCriterion("hphm1 >", value, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1GreaterThanOrEqualTo(String value) {
            addCriterion("hphm1 >=", value, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1LessThan(String value) {
            addCriterion("hphm1 <", value, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1LessThanOrEqualTo(String value) {
            addCriterion("hphm1 <=", value, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1Like(String value) {
            addCriterion("hphm1 like", value, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1NotLike(String value) {
            addCriterion("hphm1 not like", value, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1In(List<String> values) {
            addCriterion("hphm1 in", values, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1NotIn(List<String> values) {
            addCriterion("hphm1 not in", values, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1Between(String value1, String value2) {
            addCriterion("hphm1 between", value1, value2, "hphm1");
            return (Criteria) this;
        }

        public Criteria andHphm1NotBetween(String value1, String value2) {
            addCriterion("hphm1 not between", value1, value2, "hphm1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1IsNull() {
            addCriterion("dsrjtfs1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1IsNotNull() {
            addCriterion("dsrjtfs1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1EqualTo(String value) {
            addCriterion("dsrjtfs1 =", value, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1NotEqualTo(String value) {
            addCriterion("dsrjtfs1 <>", value, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1GreaterThan(String value) {
            addCriterion("dsrjtfs1 >", value, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrjtfs1 >=", value, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1LessThan(String value) {
            addCriterion("dsrjtfs1 <", value, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1LessThanOrEqualTo(String value) {
            addCriterion("dsrjtfs1 <=", value, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1Like(String value) {
            addCriterion("dsrjtfs1 like", value, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1NotLike(String value) {
            addCriterion("dsrjtfs1 not like", value, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1In(List<String> values) {
            addCriterion("dsrjtfs1 in", values, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1NotIn(List<String> values) {
            addCriterion("dsrjtfs1 not in", values, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1Between(String value1, String value2) {
            addCriterion("dsrjtfs1 between", value1, value2, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs1NotBetween(String value1, String value2) {
            addCriterion("dsrjtfs1 not between", value1, value2, "dsrjtfs1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1IsNull() {
            addCriterion("dsrcsbw1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1IsNotNull() {
            addCriterion("dsrcsbw1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1EqualTo(String value) {
            addCriterion("dsrcsbw1 =", value, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1NotEqualTo(String value) {
            addCriterion("dsrcsbw1 <>", value, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1GreaterThan(String value) {
            addCriterion("dsrcsbw1 >", value, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrcsbw1 >=", value, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1LessThan(String value) {
            addCriterion("dsrcsbw1 <", value, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1LessThanOrEqualTo(String value) {
            addCriterion("dsrcsbw1 <=", value, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1Like(String value) {
            addCriterion("dsrcsbw1 like", value, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1NotLike(String value) {
            addCriterion("dsrcsbw1 not like", value, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1In(List<String> values) {
            addCriterion("dsrcsbw1 in", values, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1NotIn(List<String> values) {
            addCriterion("dsrcsbw1 not in", values, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1Between(String value1, String value2) {
            addCriterion("dsrcsbw1 between", value1, value2, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw1NotBetween(String value1, String value2) {
            addCriterion("dsrcsbw1 not between", value1, value2, "dsrcsbw1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1IsNull() {
            addCriterion("dsrbsgzr1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1IsNotNull() {
            addCriterion("dsrbsgzr1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1EqualTo(String value) {
            addCriterion("dsrbsgzr1 =", value, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1NotEqualTo(String value) {
            addCriterion("dsrbsgzr1 <>", value, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1GreaterThan(String value) {
            addCriterion("dsrbsgzr1 >", value, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrbsgzr1 >=", value, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1LessThan(String value) {
            addCriterion("dsrbsgzr1 <", value, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1LessThanOrEqualTo(String value) {
            addCriterion("dsrbsgzr1 <=", value, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1Like(String value) {
            addCriterion("dsrbsgzr1 like", value, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1NotLike(String value) {
            addCriterion("dsrbsgzr1 not like", value, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1In(List<String> values) {
            addCriterion("dsrbsgzr1 in", values, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1NotIn(List<String> values) {
            addCriterion("dsrbsgzr1 not in", values, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1Between(String value1, String value2) {
            addCriterion("dsrbsgzr1 between", value1, value2, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr1NotBetween(String value1, String value2) {
            addCriterion("dsrbsgzr1 not between", value1, value2, "dsrbsgzr1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1IsNull() {
            addCriterion("dsrwfxw1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1IsNotNull() {
            addCriterion("dsrwfxw1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1EqualTo(String value) {
            addCriterion("dsrwfxw1 =", value, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1NotEqualTo(String value) {
            addCriterion("dsrwfxw1 <>", value, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1GreaterThan(String value) {
            addCriterion("dsrwfxw1 >", value, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrwfxw1 >=", value, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1LessThan(String value) {
            addCriterion("dsrwfxw1 <", value, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1LessThanOrEqualTo(String value) {
            addCriterion("dsrwfxw1 <=", value, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1Like(String value) {
            addCriterion("dsrwfxw1 like", value, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1NotLike(String value) {
            addCriterion("dsrwfxw1 not like", value, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1In(List<String> values) {
            addCriterion("dsrwfxw1 in", values, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1NotIn(List<String> values) {
            addCriterion("dsrwfxw1 not in", values, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1Between(String value1, String value2) {
            addCriterion("dsrwfxw1 between", value1, value2, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw1NotBetween(String value1, String value2) {
            addCriterion("dsrwfxw1 not between", value1, value2, "dsrwfxw1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1IsNull() {
            addCriterion("dsrwftk1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1IsNotNull() {
            addCriterion("dsrwftk1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1EqualTo(String value) {
            addCriterion("dsrwftk1 =", value, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1NotEqualTo(String value) {
            addCriterion("dsrwftk1 <>", value, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1GreaterThan(String value) {
            addCriterion("dsrwftk1 >", value, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrwftk1 >=", value, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1LessThan(String value) {
            addCriterion("dsrwftk1 <", value, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1LessThanOrEqualTo(String value) {
            addCriterion("dsrwftk1 <=", value, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1Like(String value) {
            addCriterion("dsrwftk1 like", value, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1NotLike(String value) {
            addCriterion("dsrwftk1 not like", value, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1In(List<String> values) {
            addCriterion("dsrwftk1 in", values, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1NotIn(List<String> values) {
            addCriterion("dsrwftk1 not in", values, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1Between(String value1, String value2) {
            addCriterion("dsrwftk1 between", value1, value2, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrwftk1NotBetween(String value1, String value2) {
            addCriterion("dsrwftk1 not between", value1, value2, "dsrwftk1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1IsNull() {
            addCriterion("dsrbxgs1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1IsNotNull() {
            addCriterion("dsrbxgs1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1EqualTo(String value) {
            addCriterion("dsrbxgs1 =", value, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1NotEqualTo(String value) {
            addCriterion("dsrbxgs1 <>", value, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1GreaterThan(String value) {
            addCriterion("dsrbxgs1 >", value, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrbxgs1 >=", value, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1LessThan(String value) {
            addCriterion("dsrbxgs1 <", value, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1LessThanOrEqualTo(String value) {
            addCriterion("dsrbxgs1 <=", value, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1Like(String value) {
            addCriterion("dsrbxgs1 like", value, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1NotLike(String value) {
            addCriterion("dsrbxgs1 not like", value, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1In(List<String> values) {
            addCriterion("dsrbxgs1 in", values, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1NotIn(List<String> values) {
            addCriterion("dsrbxgs1 not in", values, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1Between(String value1, String value2) {
            addCriterion("dsrbxgs1 between", value1, value2, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs1NotBetween(String value1, String value2) {
            addCriterion("dsrbxgs1 not between", value1, value2, "dsrbxgs1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1IsNull() {
            addCriterion("dsrbxqz1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1IsNotNull() {
            addCriterion("dsrbxqz1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1EqualTo(String value) {
            addCriterion("dsrbxqz1 =", value, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1NotEqualTo(String value) {
            addCriterion("dsrbxqz1 <>", value, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1GreaterThan(String value) {
            addCriterion("dsrbxqz1 >", value, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrbxqz1 >=", value, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1LessThan(String value) {
            addCriterion("dsrbxqz1 <", value, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1LessThanOrEqualTo(String value) {
            addCriterion("dsrbxqz1 <=", value, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1Like(String value) {
            addCriterion("dsrbxqz1 like", value, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1NotLike(String value) {
            addCriterion("dsrbxqz1 not like", value, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1In(List<String> values) {
            addCriterion("dsrbxqz1 in", values, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1NotIn(List<String> values) {
            addCriterion("dsrbxqz1 not in", values, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1Between(String value1, String value2) {
            addCriterion("dsrbxqz1 between", value1, value2, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz1NotBetween(String value1, String value2) {
            addCriterion("dsrbxqz1 not between", value1, value2, "dsrbxqz1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1IsNull() {
            addCriterion("dsrbxpzh1 is null");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1IsNotNull() {
            addCriterion("dsrbxpzh1 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1EqualTo(String value) {
            addCriterion("dsrbxpzh1 =", value, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1NotEqualTo(String value) {
            addCriterion("dsrbxpzh1 <>", value, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1GreaterThan(String value) {
            addCriterion("dsrbxpzh1 >", value, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1GreaterThanOrEqualTo(String value) {
            addCriterion("dsrbxpzh1 >=", value, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1LessThan(String value) {
            addCriterion("dsrbxpzh1 <", value, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1LessThanOrEqualTo(String value) {
            addCriterion("dsrbxpzh1 <=", value, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1Like(String value) {
            addCriterion("dsrbxpzh1 like", value, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1NotLike(String value) {
            addCriterion("dsrbxpzh1 not like", value, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1In(List<String> values) {
            addCriterion("dsrbxpzh1 in", values, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1NotIn(List<String> values) {
            addCriterion("dsrbxpzh1 not in", values, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1Between(String value1, String value2) {
            addCriterion("dsrbxpzh1 between", value1, value2, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh1NotBetween(String value1, String value2) {
            addCriterion("dsrbxpzh1 not between", value1, value2, "dsrbxpzh1");
            return (Criteria) this;
        }

        public Criteria andDsrss2IsNull() {
            addCriterion("dsrss2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrss2IsNotNull() {
            addCriterion("dsrss2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrss2EqualTo(String value) {
            addCriterion("dsrss2 =", value, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2NotEqualTo(String value) {
            addCriterion("dsrss2 <>", value, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2GreaterThan(String value) {
            addCriterion("dsrss2 >", value, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrss2 >=", value, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2LessThan(String value) {
            addCriterion("dsrss2 <", value, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2LessThanOrEqualTo(String value) {
            addCriterion("dsrss2 <=", value, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2Like(String value) {
            addCriterion("dsrss2 like", value, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2NotLike(String value) {
            addCriterion("dsrss2 not like", value, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2In(List<String> values) {
            addCriterion("dsrss2 in", values, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2NotIn(List<String> values) {
            addCriterion("dsrss2 not in", values, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2Between(String value1, String value2) {
            addCriterion("dsrss2 between", value1, value2, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrss2NotBetween(String value1, String value2) {
            addCriterion("dsrss2 not between", value1, value2, "dsrss2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2IsNull() {
            addCriterion("dsrxm2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrxm2IsNotNull() {
            addCriterion("dsrxm2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrxm2EqualTo(String value) {
            addCriterion("dsrxm2 =", value, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2NotEqualTo(String value) {
            addCriterion("dsrxm2 <>", value, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2GreaterThan(String value) {
            addCriterion("dsrxm2 >", value, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrxm2 >=", value, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2LessThan(String value) {
            addCriterion("dsrxm2 <", value, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2LessThanOrEqualTo(String value) {
            addCriterion("dsrxm2 <=", value, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2Like(String value) {
            addCriterion("dsrxm2 like", value, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2NotLike(String value) {
            addCriterion("dsrxm2 not like", value, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2In(List<String> values) {
            addCriterion("dsrxm2 in", values, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2NotIn(List<String> values) {
            addCriterion("dsrxm2 not in", values, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2Between(String value1, String value2) {
            addCriterion("dsrxm2 between", value1, value2, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrxm2NotBetween(String value1, String value2) {
            addCriterion("dsrxm2 not between", value1, value2, "dsrxm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2IsNull() {
            addCriterion("dsrsfzmhm2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2IsNotNull() {
            addCriterion("dsrsfzmhm2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2EqualTo(String value) {
            addCriterion("dsrsfzmhm2 =", value, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2NotEqualTo(String value) {
            addCriterion("dsrsfzmhm2 <>", value, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2GreaterThan(String value) {
            addCriterion("dsrsfzmhm2 >", value, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrsfzmhm2 >=", value, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2LessThan(String value) {
            addCriterion("dsrsfzmhm2 <", value, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2LessThanOrEqualTo(String value) {
            addCriterion("dsrsfzmhm2 <=", value, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2Like(String value) {
            addCriterion("dsrsfzmhm2 like", value, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2NotLike(String value) {
            addCriterion("dsrsfzmhm2 not like", value, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2In(List<String> values) {
            addCriterion("dsrsfzmhm2 in", values, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2NotIn(List<String> values) {
            addCriterion("dsrsfzmhm2 not in", values, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2Between(String value1, String value2) {
            addCriterion("dsrsfzmhm2 between", value1, value2, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsfzmhm2NotBetween(String value1, String value2) {
            addCriterion("dsrsfzmhm2 not between", value1, value2, "dsrsfzmhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2IsNull() {
            addCriterion("dsrsjhm2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2IsNotNull() {
            addCriterion("dsrsjhm2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2EqualTo(String value) {
            addCriterion("dsrsjhm2 =", value, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2NotEqualTo(String value) {
            addCriterion("dsrsjhm2 <>", value, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2GreaterThan(String value) {
            addCriterion("dsrsjhm2 >", value, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrsjhm2 >=", value, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2LessThan(String value) {
            addCriterion("dsrsjhm2 <", value, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2LessThanOrEqualTo(String value) {
            addCriterion("dsrsjhm2 <=", value, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2Like(String value) {
            addCriterion("dsrsjhm2 like", value, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2NotLike(String value) {
            addCriterion("dsrsjhm2 not like", value, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2In(List<String> values) {
            addCriterion("dsrsjhm2 in", values, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2NotIn(List<String> values) {
            addCriterion("dsrsjhm2 not in", values, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2Between(String value1, String value2) {
            addCriterion("dsrsjhm2 between", value1, value2, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andDsrsjhm2NotBetween(String value1, String value2) {
            addCriterion("dsrsjhm2 not between", value1, value2, "dsrsjhm2");
            return (Criteria) this;
        }

        public Criteria andHpzl2IsNull() {
            addCriterion("hpzl2 is null");
            return (Criteria) this;
        }

        public Criteria andHpzl2IsNotNull() {
            addCriterion("hpzl2 is not null");
            return (Criteria) this;
        }

        public Criteria andHpzl2EqualTo(String value) {
            addCriterion("hpzl2 =", value, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2NotEqualTo(String value) {
            addCriterion("hpzl2 <>", value, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2GreaterThan(String value) {
            addCriterion("hpzl2 >", value, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2GreaterThanOrEqualTo(String value) {
            addCriterion("hpzl2 >=", value, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2LessThan(String value) {
            addCriterion("hpzl2 <", value, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2LessThanOrEqualTo(String value) {
            addCriterion("hpzl2 <=", value, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2Like(String value) {
            addCriterion("hpzl2 like", value, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2NotLike(String value) {
            addCriterion("hpzl2 not like", value, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2In(List<String> values) {
            addCriterion("hpzl2 in", values, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2NotIn(List<String> values) {
            addCriterion("hpzl2 not in", values, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2Between(String value1, String value2) {
            addCriterion("hpzl2 between", value1, value2, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHpzl2NotBetween(String value1, String value2) {
            addCriterion("hpzl2 not between", value1, value2, "hpzl2");
            return (Criteria) this;
        }

        public Criteria andHphm2IsNull() {
            addCriterion("hphm2 is null");
            return (Criteria) this;
        }

        public Criteria andHphm2IsNotNull() {
            addCriterion("hphm2 is not null");
            return (Criteria) this;
        }

        public Criteria andHphm2EqualTo(String value) {
            addCriterion("hphm2 =", value, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2NotEqualTo(String value) {
            addCriterion("hphm2 <>", value, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2GreaterThan(String value) {
            addCriterion("hphm2 >", value, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2GreaterThanOrEqualTo(String value) {
            addCriterion("hphm2 >=", value, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2LessThan(String value) {
            addCriterion("hphm2 <", value, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2LessThanOrEqualTo(String value) {
            addCriterion("hphm2 <=", value, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2Like(String value) {
            addCriterion("hphm2 like", value, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2NotLike(String value) {
            addCriterion("hphm2 not like", value, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2In(List<String> values) {
            addCriterion("hphm2 in", values, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2NotIn(List<String> values) {
            addCriterion("hphm2 not in", values, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2Between(String value1, String value2) {
            addCriterion("hphm2 between", value1, value2, "hphm2");
            return (Criteria) this;
        }

        public Criteria andHphm2NotBetween(String value1, String value2) {
            addCriterion("hphm2 not between", value1, value2, "hphm2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2IsNull() {
            addCriterion("dsrjtfs2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2IsNotNull() {
            addCriterion("dsrjtfs2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2EqualTo(String value) {
            addCriterion("dsrjtfs2 =", value, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2NotEqualTo(String value) {
            addCriterion("dsrjtfs2 <>", value, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2GreaterThan(String value) {
            addCriterion("dsrjtfs2 >", value, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrjtfs2 >=", value, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2LessThan(String value) {
            addCriterion("dsrjtfs2 <", value, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2LessThanOrEqualTo(String value) {
            addCriterion("dsrjtfs2 <=", value, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2Like(String value) {
            addCriterion("dsrjtfs2 like", value, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2NotLike(String value) {
            addCriterion("dsrjtfs2 not like", value, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2In(List<String> values) {
            addCriterion("dsrjtfs2 in", values, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2NotIn(List<String> values) {
            addCriterion("dsrjtfs2 not in", values, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2Between(String value1, String value2) {
            addCriterion("dsrjtfs2 between", value1, value2, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrjtfs2NotBetween(String value1, String value2) {
            addCriterion("dsrjtfs2 not between", value1, value2, "dsrjtfs2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2IsNull() {
            addCriterion("dsrcsbw2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2IsNotNull() {
            addCriterion("dsrcsbw2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2EqualTo(String value) {
            addCriterion("dsrcsbw2 =", value, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2NotEqualTo(String value) {
            addCriterion("dsrcsbw2 <>", value, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2GreaterThan(String value) {
            addCriterion("dsrcsbw2 >", value, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrcsbw2 >=", value, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2LessThan(String value) {
            addCriterion("dsrcsbw2 <", value, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2LessThanOrEqualTo(String value) {
            addCriterion("dsrcsbw2 <=", value, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2Like(String value) {
            addCriterion("dsrcsbw2 like", value, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2NotLike(String value) {
            addCriterion("dsrcsbw2 not like", value, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2In(List<String> values) {
            addCriterion("dsrcsbw2 in", values, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2NotIn(List<String> values) {
            addCriterion("dsrcsbw2 not in", values, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2Between(String value1, String value2) {
            addCriterion("dsrcsbw2 between", value1, value2, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrcsbw2NotBetween(String value1, String value2) {
            addCriterion("dsrcsbw2 not between", value1, value2, "dsrcsbw2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2IsNull() {
            addCriterion("dsrbsgzr2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2IsNotNull() {
            addCriterion("dsrbsgzr2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2EqualTo(String value) {
            addCriterion("dsrbsgzr2 =", value, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2NotEqualTo(String value) {
            addCriterion("dsrbsgzr2 <>", value, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2GreaterThan(String value) {
            addCriterion("dsrbsgzr2 >", value, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrbsgzr2 >=", value, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2LessThan(String value) {
            addCriterion("dsrbsgzr2 <", value, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2LessThanOrEqualTo(String value) {
            addCriterion("dsrbsgzr2 <=", value, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2Like(String value) {
            addCriterion("dsrbsgzr2 like", value, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2NotLike(String value) {
            addCriterion("dsrbsgzr2 not like", value, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2In(List<String> values) {
            addCriterion("dsrbsgzr2 in", values, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2NotIn(List<String> values) {
            addCriterion("dsrbsgzr2 not in", values, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2Between(String value1, String value2) {
            addCriterion("dsrbsgzr2 between", value1, value2, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrbsgzr2NotBetween(String value1, String value2) {
            addCriterion("dsrbsgzr2 not between", value1, value2, "dsrbsgzr2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2IsNull() {
            addCriterion("dsrwfxw2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2IsNotNull() {
            addCriterion("dsrwfxw2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2EqualTo(String value) {
            addCriterion("dsrwfxw2 =", value, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2NotEqualTo(String value) {
            addCriterion("dsrwfxw2 <>", value, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2GreaterThan(String value) {
            addCriterion("dsrwfxw2 >", value, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrwfxw2 >=", value, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2LessThan(String value) {
            addCriterion("dsrwfxw2 <", value, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2LessThanOrEqualTo(String value) {
            addCriterion("dsrwfxw2 <=", value, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2Like(String value) {
            addCriterion("dsrwfxw2 like", value, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2NotLike(String value) {
            addCriterion("dsrwfxw2 not like", value, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2In(List<String> values) {
            addCriterion("dsrwfxw2 in", values, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2NotIn(List<String> values) {
            addCriterion("dsrwfxw2 not in", values, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2Between(String value1, String value2) {
            addCriterion("dsrwfxw2 between", value1, value2, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwfxw2NotBetween(String value1, String value2) {
            addCriterion("dsrwfxw2 not between", value1, value2, "dsrwfxw2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2IsNull() {
            addCriterion("dsrwftk2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2IsNotNull() {
            addCriterion("dsrwftk2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2EqualTo(String value) {
            addCriterion("dsrwftk2 =", value, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2NotEqualTo(String value) {
            addCriterion("dsrwftk2 <>", value, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2GreaterThan(String value) {
            addCriterion("dsrwftk2 >", value, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrwftk2 >=", value, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2LessThan(String value) {
            addCriterion("dsrwftk2 <", value, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2LessThanOrEqualTo(String value) {
            addCriterion("dsrwftk2 <=", value, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2Like(String value) {
            addCriterion("dsrwftk2 like", value, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2NotLike(String value) {
            addCriterion("dsrwftk2 not like", value, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2In(List<String> values) {
            addCriterion("dsrwftk2 in", values, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2NotIn(List<String> values) {
            addCriterion("dsrwftk2 not in", values, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2Between(String value1, String value2) {
            addCriterion("dsrwftk2 between", value1, value2, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrwftk2NotBetween(String value1, String value2) {
            addCriterion("dsrwftk2 not between", value1, value2, "dsrwftk2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2IsNull() {
            addCriterion("dsrbxgs2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2IsNotNull() {
            addCriterion("dsrbxgs2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2EqualTo(String value) {
            addCriterion("dsrbxgs2 =", value, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2NotEqualTo(String value) {
            addCriterion("dsrbxgs2 <>", value, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2GreaterThan(String value) {
            addCriterion("dsrbxgs2 >", value, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrbxgs2 >=", value, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2LessThan(String value) {
            addCriterion("dsrbxgs2 <", value, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2LessThanOrEqualTo(String value) {
            addCriterion("dsrbxgs2 <=", value, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2Like(String value) {
            addCriterion("dsrbxgs2 like", value, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2NotLike(String value) {
            addCriterion("dsrbxgs2 not like", value, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2In(List<String> values) {
            addCriterion("dsrbxgs2 in", values, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2NotIn(List<String> values) {
            addCriterion("dsrbxgs2 not in", values, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2Between(String value1, String value2) {
            addCriterion("dsrbxgs2 between", value1, value2, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxgs2NotBetween(String value1, String value2) {
            addCriterion("dsrbxgs2 not between", value1, value2, "dsrbxgs2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2IsNull() {
            addCriterion("dsrbxqz2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2IsNotNull() {
            addCriterion("dsrbxqz2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2EqualTo(String value) {
            addCriterion("dsrbxqz2 =", value, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2NotEqualTo(String value) {
            addCriterion("dsrbxqz2 <>", value, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2GreaterThan(String value) {
            addCriterion("dsrbxqz2 >", value, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrbxqz2 >=", value, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2LessThan(String value) {
            addCriterion("dsrbxqz2 <", value, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2LessThanOrEqualTo(String value) {
            addCriterion("dsrbxqz2 <=", value, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2Like(String value) {
            addCriterion("dsrbxqz2 like", value, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2NotLike(String value) {
            addCriterion("dsrbxqz2 not like", value, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2In(List<String> values) {
            addCriterion("dsrbxqz2 in", values, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2NotIn(List<String> values) {
            addCriterion("dsrbxqz2 not in", values, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2Between(String value1, String value2) {
            addCriterion("dsrbxqz2 between", value1, value2, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxqz2NotBetween(String value1, String value2) {
            addCriterion("dsrbxqz2 not between", value1, value2, "dsrbxqz2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2IsNull() {
            addCriterion("dsrbxpzh2 is null");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2IsNotNull() {
            addCriterion("dsrbxpzh2 is not null");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2EqualTo(String value) {
            addCriterion("dsrbxpzh2 =", value, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2NotEqualTo(String value) {
            addCriterion("dsrbxpzh2 <>", value, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2GreaterThan(String value) {
            addCriterion("dsrbxpzh2 >", value, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2GreaterThanOrEqualTo(String value) {
            addCriterion("dsrbxpzh2 >=", value, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2LessThan(String value) {
            addCriterion("dsrbxpzh2 <", value, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2LessThanOrEqualTo(String value) {
            addCriterion("dsrbxpzh2 <=", value, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2Like(String value) {
            addCriterion("dsrbxpzh2 like", value, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2NotLike(String value) {
            addCriterion("dsrbxpzh2 not like", value, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2In(List<String> values) {
            addCriterion("dsrbxpzh2 in", values, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2NotIn(List<String> values) {
            addCriterion("dsrbxpzh2 not in", values, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2Between(String value1, String value2) {
            addCriterion("dsrbxpzh2 between", value1, value2, "dsrbxpzh2");
            return (Criteria) this;
        }

        public Criteria andDsrbxpzh2NotBetween(String value1, String value2) {
            addCriterion("dsrbxpzh2 not between", value1, value2, "dsrbxpzh2");
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

        public Criteria andDzmjIsNull() {
            addCriterion("dzmj is null");
            return (Criteria) this;
        }

        public Criteria andDzmjIsNotNull() {
            addCriterion("dzmj is not null");
            return (Criteria) this;
        }

        public Criteria andDzmjEqualTo(String value) {
            addCriterion("dzmj =", value, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjNotEqualTo(String value) {
            addCriterion("dzmj <>", value, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjGreaterThan(String value) {
            addCriterion("dzmj >", value, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjGreaterThanOrEqualTo(String value) {
            addCriterion("dzmj >=", value, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjLessThan(String value) {
            addCriterion("dzmj <", value, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjLessThanOrEqualTo(String value) {
            addCriterion("dzmj <=", value, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjLike(String value) {
            addCriterion("dzmj like", value, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjNotLike(String value) {
            addCriterion("dzmj not like", value, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjIn(List<String> values) {
            addCriterion("dzmj in", values, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjNotIn(List<String> values) {
            addCriterion("dzmj not in", values, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjBetween(String value1, String value2) {
            addCriterion("dzmj between", value1, value2, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzmjNotBetween(String value1, String value2) {
            addCriterion("dzmj not between", value1, value2, "dzmj");
            return (Criteria) this;
        }

        public Criteria andDzsjIsNull() {
            addCriterion("dzsj is null");
            return (Criteria) this;
        }

        public Criteria andDzsjIsNotNull() {
            addCriterion("dzsj is not null");
            return (Criteria) this;
        }

        public Criteria andDzsjEqualTo(Date value) {
            addCriterion("dzsj =", value, "dzsj");
            return (Criteria) this;
        }

        public Criteria andDzsjNotEqualTo(Date value) {
            addCriterion("dzsj <>", value, "dzsj");
            return (Criteria) this;
        }

        public Criteria andDzsjGreaterThan(Date value) {
            addCriterion("dzsj >", value, "dzsj");
            return (Criteria) this;
        }

        public Criteria andDzsjGreaterThanOrEqualTo(Date value) {
            addCriterion("dzsj >=", value, "dzsj");
            return (Criteria) this;
        }

        public Criteria andDzsjLessThan(Date value) {
            addCriterion("dzsj <", value, "dzsj");
            return (Criteria) this;
        }

        public Criteria andDzsjLessThanOrEqualTo(Date value) {
            addCriterion("dzsj <=", value, "dzsj");
            return (Criteria) this;
        }

        public Criteria andDzsjIn(List<Date> values) {
            addCriterion("dzsj in", values, "dzsj");
            return (Criteria) this;
        }

        public Criteria andDzsjNotIn(List<Date> values) {
            addCriterion("dzsj not in", values, "dzsj");
            return (Criteria) this;
        }

        public Criteria andDzsjBetween(Date value1, Date value2) {
            addCriterion("dzsj between", value1, value2, "dzsj");
            return (Criteria) this;
        }

        public Criteria andDzsjNotBetween(Date value1, Date value2) {
            addCriterion("dzsj not between", value1, value2, "dzsj");
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