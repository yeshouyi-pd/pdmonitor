package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class VehPassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehPassExample() {
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

        public Criteria andSqrIsNull() {
            addCriterion("sqr is null");
            return (Criteria) this;
        }

        public Criteria andSqrIsNotNull() {
            addCriterion("sqr is not null");
            return (Criteria) this;
        }

        public Criteria andSqrEqualTo(String value) {
            addCriterion("sqr =", value, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrNotEqualTo(String value) {
            addCriterion("sqr <>", value, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrGreaterThan(String value) {
            addCriterion("sqr >", value, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrGreaterThanOrEqualTo(String value) {
            addCriterion("sqr >=", value, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrLessThan(String value) {
            addCriterion("sqr <", value, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrLessThanOrEqualTo(String value) {
            addCriterion("sqr <=", value, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrLike(String value) {
            addCriterion("sqr like", value, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrNotLike(String value) {
            addCriterion("sqr not like", value, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrIn(List<String> values) {
            addCriterion("sqr in", values, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrNotIn(List<String> values) {
            addCriterion("sqr not in", values, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrBetween(String value1, String value2) {
            addCriterion("sqr between", value1, value2, "sqr");
            return (Criteria) this;
        }

        public Criteria andSqrNotBetween(String value1, String value2) {
            addCriterion("sqr not between", value1, value2, "sqr");
            return (Criteria) this;
        }

        public Criteria andTxzlxIsNull() {
            addCriterion("txzlx is null");
            return (Criteria) this;
        }

        public Criteria andTxzlxIsNotNull() {
            addCriterion("txzlx is not null");
            return (Criteria) this;
        }

        public Criteria andTxzlxEqualTo(String value) {
            addCriterion("txzlx =", value, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxNotEqualTo(String value) {
            addCriterion("txzlx <>", value, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxGreaterThan(String value) {
            addCriterion("txzlx >", value, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxGreaterThanOrEqualTo(String value) {
            addCriterion("txzlx >=", value, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxLessThan(String value) {
            addCriterion("txzlx <", value, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxLessThanOrEqualTo(String value) {
            addCriterion("txzlx <=", value, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxLike(String value) {
            addCriterion("txzlx like", value, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxNotLike(String value) {
            addCriterion("txzlx not like", value, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxIn(List<String> values) {
            addCriterion("txzlx in", values, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxNotIn(List<String> values) {
            addCriterion("txzlx not in", values, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxBetween(String value1, String value2) {
            addCriterion("txzlx between", value1, value2, "txzlx");
            return (Criteria) this;
        }

        public Criteria andTxzlxNotBetween(String value1, String value2) {
            addCriterion("txzlx not between", value1, value2, "txzlx");
            return (Criteria) this;
        }

        public Criteria andCfdIsNull() {
            addCriterion("cfd is null");
            return (Criteria) this;
        }

        public Criteria andCfdIsNotNull() {
            addCriterion("cfd is not null");
            return (Criteria) this;
        }

        public Criteria andCfdEqualTo(String value) {
            addCriterion("cfd =", value, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdNotEqualTo(String value) {
            addCriterion("cfd <>", value, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdGreaterThan(String value) {
            addCriterion("cfd >", value, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdGreaterThanOrEqualTo(String value) {
            addCriterion("cfd >=", value, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdLessThan(String value) {
            addCriterion("cfd <", value, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdLessThanOrEqualTo(String value) {
            addCriterion("cfd <=", value, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdLike(String value) {
            addCriterion("cfd like", value, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdNotLike(String value) {
            addCriterion("cfd not like", value, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdIn(List<String> values) {
            addCriterion("cfd in", values, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdNotIn(List<String> values) {
            addCriterion("cfd not in", values, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdBetween(String value1, String value2) {
            addCriterion("cfd between", value1, value2, "cfd");
            return (Criteria) this;
        }

        public Criteria andCfdNotBetween(String value1, String value2) {
            addCriterion("cfd not between", value1, value2, "cfd");
            return (Criteria) this;
        }

        public Criteria andMddIsNull() {
            addCriterion("mdd is null");
            return (Criteria) this;
        }

        public Criteria andMddIsNotNull() {
            addCriterion("mdd is not null");
            return (Criteria) this;
        }

        public Criteria andMddEqualTo(String value) {
            addCriterion("mdd =", value, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddNotEqualTo(String value) {
            addCriterion("mdd <>", value, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddGreaterThan(String value) {
            addCriterion("mdd >", value, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddGreaterThanOrEqualTo(String value) {
            addCriterion("mdd >=", value, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddLessThan(String value) {
            addCriterion("mdd <", value, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddLessThanOrEqualTo(String value) {
            addCriterion("mdd <=", value, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddLike(String value) {
            addCriterion("mdd like", value, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddNotLike(String value) {
            addCriterion("mdd not like", value, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddIn(List<String> values) {
            addCriterion("mdd in", values, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddNotIn(List<String> values) {
            addCriterion("mdd not in", values, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddBetween(String value1, String value2) {
            addCriterion("mdd between", value1, value2, "mdd");
            return (Criteria) this;
        }

        public Criteria andMddNotBetween(String value1, String value2) {
            addCriterion("mdd not between", value1, value2, "mdd");
            return (Criteria) this;
        }

        public Criteria andYxqkssjIsNull() {
            addCriterion("yxqkssj is null");
            return (Criteria) this;
        }

        public Criteria andYxqkssjIsNotNull() {
            addCriterion("yxqkssj is not null");
            return (Criteria) this;
        }

        public Criteria andYxqkssjEqualTo(Date value) {
            addCriterionForJDBCDate("yxqkssj =", value, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqkssjNotEqualTo(Date value) {
            addCriterionForJDBCDate("yxqkssj <>", value, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqkssjGreaterThan(Date value) {
            addCriterionForJDBCDate("yxqkssj >", value, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqkssjGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yxqkssj >=", value, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqkssjLessThan(Date value) {
            addCriterionForJDBCDate("yxqkssj <", value, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqkssjLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yxqkssj <=", value, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqkssjIn(List<Date> values) {
            addCriterionForJDBCDate("yxqkssj in", values, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqkssjNotIn(List<Date> values) {
            addCriterionForJDBCDate("yxqkssj not in", values, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqkssjBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yxqkssj between", value1, value2, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqkssjNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yxqkssj not between", value1, value2, "yxqkssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjIsNull() {
            addCriterion("yxqjssj is null");
            return (Criteria) this;
        }

        public Criteria andYxqjssjIsNotNull() {
            addCriterion("yxqjssj is not null");
            return (Criteria) this;
        }

        public Criteria andYxqjssjEqualTo(Date value) {
            addCriterionForJDBCDate("yxqjssj =", value, "yxqjssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjNotEqualTo(Date value) {
            addCriterionForJDBCDate("yxqjssj <>", value, "yxqjssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjGreaterThan(Date value) {
            addCriterionForJDBCDate("yxqjssj >", value, "yxqjssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yxqjssj >=", value, "yxqjssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjLessThan(Date value) {
            addCriterionForJDBCDate("yxqjssj <", value, "yxqjssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yxqjssj <=", value, "yxqjssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjIn(List<Date> values) {
            addCriterionForJDBCDate("yxqjssj in", values, "yxqjssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjNotIn(List<Date> values) {
            addCriterionForJDBCDate("yxqjssj not in", values, "yxqjssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yxqjssj between", value1, value2, "yxqjssj");
            return (Criteria) this;
        }

        public Criteria andYxqjssjNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yxqjssj not between", value1, value2, "yxqjssj");
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

        public Criteria andSqsmIsNull() {
            addCriterion("sqsm is null");
            return (Criteria) this;
        }

        public Criteria andSqsmIsNotNull() {
            addCriterion("sqsm is not null");
            return (Criteria) this;
        }

        public Criteria andSqsmEqualTo(String value) {
            addCriterion("sqsm =", value, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmNotEqualTo(String value) {
            addCriterion("sqsm <>", value, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmGreaterThan(String value) {
            addCriterion("sqsm >", value, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmGreaterThanOrEqualTo(String value) {
            addCriterion("sqsm >=", value, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmLessThan(String value) {
            addCriterion("sqsm <", value, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmLessThanOrEqualTo(String value) {
            addCriterion("sqsm <=", value, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmLike(String value) {
            addCriterion("sqsm like", value, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmNotLike(String value) {
            addCriterion("sqsm not like", value, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmIn(List<String> values) {
            addCriterion("sqsm in", values, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmNotIn(List<String> values) {
            addCriterion("sqsm not in", values, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmBetween(String value1, String value2) {
            addCriterion("sqsm between", value1, value2, "sqsm");
            return (Criteria) this;
        }

        public Criteria andSqsmNotBetween(String value1, String value2) {
            addCriterion("sqsm not between", value1, value2, "sqsm");
            return (Criteria) this;
        }

        public Criteria andTxxlIsNull() {
            addCriterion("txxl is null");
            return (Criteria) this;
        }

        public Criteria andTxxlIsNotNull() {
            addCriterion("txxl is not null");
            return (Criteria) this;
        }

        public Criteria andTxxlEqualTo(String value) {
            addCriterion("txxl =", value, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlNotEqualTo(String value) {
            addCriterion("txxl <>", value, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlGreaterThan(String value) {
            addCriterion("txxl >", value, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlGreaterThanOrEqualTo(String value) {
            addCriterion("txxl >=", value, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlLessThan(String value) {
            addCriterion("txxl <", value, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlLessThanOrEqualTo(String value) {
            addCriterion("txxl <=", value, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlLike(String value) {
            addCriterion("txxl like", value, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlNotLike(String value) {
            addCriterion("txxl not like", value, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlIn(List<String> values) {
            addCriterion("txxl in", values, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlNotIn(List<String> values) {
            addCriterion("txxl not in", values, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlBetween(String value1, String value2) {
            addCriterion("txxl between", value1, value2, "txxl");
            return (Criteria) this;
        }

        public Criteria andTxxlNotBetween(String value1, String value2) {
            addCriterion("txxl not between", value1, value2, "txxl");
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

        public Criteria andShyxqkssjIsNull() {
            addCriterion("shyxqkssj is null");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjIsNotNull() {
            addCriterion("shyxqkssj is not null");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjEqualTo(Date value) {
            addCriterionForJDBCDate("shyxqkssj =", value, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjNotEqualTo(Date value) {
            addCriterionForJDBCDate("shyxqkssj <>", value, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjGreaterThan(Date value) {
            addCriterionForJDBCDate("shyxqkssj >", value, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("shyxqkssj >=", value, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjLessThan(Date value) {
            addCriterionForJDBCDate("shyxqkssj <", value, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("shyxqkssj <=", value, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjIn(List<Date> values) {
            addCriterionForJDBCDate("shyxqkssj in", values, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjNotIn(List<Date> values) {
            addCriterionForJDBCDate("shyxqkssj not in", values, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("shyxqkssj between", value1, value2, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqkssjNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("shyxqkssj not between", value1, value2, "shyxqkssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjIsNull() {
            addCriterion("shyxqjssj is null");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjIsNotNull() {
            addCriterion("shyxqjssj is not null");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjEqualTo(Date value) {
            addCriterionForJDBCDate("shyxqjssj =", value, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjNotEqualTo(Date value) {
            addCriterionForJDBCDate("shyxqjssj <>", value, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjGreaterThan(Date value) {
            addCriterionForJDBCDate("shyxqjssj >", value, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("shyxqjssj >=", value, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjLessThan(Date value) {
            addCriterionForJDBCDate("shyxqjssj <", value, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("shyxqjssj <=", value, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjIn(List<Date> values) {
            addCriterionForJDBCDate("shyxqjssj in", values, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjNotIn(List<Date> values) {
            addCriterionForJDBCDate("shyxqjssj not in", values, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("shyxqjssj between", value1, value2, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andShyxqjssjNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("shyxqjssj not between", value1, value2, "shyxqjssj");
            return (Criteria) this;
        }

        public Criteria andTxzbhIsNull() {
            addCriterion("txzbh is null");
            return (Criteria) this;
        }

        public Criteria andTxzbhIsNotNull() {
            addCriterion("txzbh is not null");
            return (Criteria) this;
        }

        public Criteria andTxzbhEqualTo(String value) {
            addCriterion("txzbh =", value, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhNotEqualTo(String value) {
            addCriterion("txzbh <>", value, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhGreaterThan(String value) {
            addCriterion("txzbh >", value, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhGreaterThanOrEqualTo(String value) {
            addCriterion("txzbh >=", value, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhLessThan(String value) {
            addCriterion("txzbh <", value, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhLessThanOrEqualTo(String value) {
            addCriterion("txzbh <=", value, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhLike(String value) {
            addCriterion("txzbh like", value, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhNotLike(String value) {
            addCriterion("txzbh not like", value, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhIn(List<String> values) {
            addCriterion("txzbh in", values, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhNotIn(List<String> values) {
            addCriterion("txzbh not in", values, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhBetween(String value1, String value2) {
            addCriterion("txzbh between", value1, value2, "txzbh");
            return (Criteria) this;
        }

        public Criteria andTxzbhNotBetween(String value1, String value2) {
            addCriterion("txzbh not between", value1, value2, "txzbh");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d')  <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion(" DATE_FORMAT(create_time,'%Y-%m-%d') >=", value, "createTime");
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