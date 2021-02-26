package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class VehParkLotStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehParkLotStatisticsExample() {
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

        public Criteria andDdchpIsNull() {
            addCriterion("ddchp is null");
            return (Criteria) this;
        }

        public Criteria andDdchpIsNotNull() {
            addCriterion("ddchp is not null");
            return (Criteria) this;
        }

        public Criteria andDdchpEqualTo(Integer value) {
            addCriterion("ddchp =", value, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdchpNotEqualTo(Integer value) {
            addCriterion("ddchp <>", value, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdchpGreaterThan(Integer value) {
            addCriterion("ddchp >", value, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdchpGreaterThanOrEqualTo(Integer value) {
            addCriterion("ddchp >=", value, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdchpLessThan(Integer value) {
            addCriterion("ddchp <", value, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdchpLessThanOrEqualTo(Integer value) {
            addCriterion("ddchp <=", value, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdchpIn(List<Integer> values) {
            addCriterion("ddchp in", values, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdchpNotIn(List<Integer> values) {
            addCriterion("ddchp not in", values, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdchpBetween(Integer value1, Integer value2) {
            addCriterion("ddchp between", value1, value2, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdchpNotBetween(Integer value1, Integer value2) {
            addCriterion("ddchp not between", value1, value2, "ddchp");
            return (Criteria) this;
        }

        public Criteria andDdcnpIsNull() {
            addCriterion("ddcnp is null");
            return (Criteria) this;
        }

        public Criteria andDdcnpIsNotNull() {
            addCriterion("ddcnp is not null");
            return (Criteria) this;
        }

        public Criteria andDdcnpEqualTo(Integer value) {
            addCriterion("ddcnp =", value, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcnpNotEqualTo(Integer value) {
            addCriterion("ddcnp <>", value, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcnpGreaterThan(Integer value) {
            addCriterion("ddcnp >", value, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcnpGreaterThanOrEqualTo(Integer value) {
            addCriterion("ddcnp >=", value, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcnpLessThan(Integer value) {
            addCriterion("ddcnp <", value, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcnpLessThanOrEqualTo(Integer value) {
            addCriterion("ddcnp <=", value, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcnpIn(List<Integer> values) {
            addCriterion("ddcnp in", values, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcnpNotIn(List<Integer> values) {
            addCriterion("ddcnp not in", values, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcnpBetween(Integer value1, Integer value2) {
            addCriterion("ddcnp between", value1, value2, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcnpNotBetween(Integer value1, Integer value2) {
            addCriterion("ddcnp not between", value1, value2, "ddcnp");
            return (Criteria) this;
        }

        public Criteria andDdcwpIsNull() {
            addCriterion("ddcwp is null");
            return (Criteria) this;
        }

        public Criteria andDdcwpIsNotNull() {
            addCriterion("ddcwp is not null");
            return (Criteria) this;
        }

        public Criteria andDdcwpEqualTo(Integer value) {
            addCriterion("ddcwp =", value, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andDdcwpNotEqualTo(Integer value) {
            addCriterion("ddcwp <>", value, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andDdcwpGreaterThan(Integer value) {
            addCriterion("ddcwp >", value, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andDdcwpGreaterThanOrEqualTo(Integer value) {
            addCriterion("ddcwp >=", value, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andDdcwpLessThan(Integer value) {
            addCriterion("ddcwp <", value, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andDdcwpLessThanOrEqualTo(Integer value) {
            addCriterion("ddcwp <=", value, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andDdcwpIn(List<Integer> values) {
            addCriterion("ddcwp in", values, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andDdcwpNotIn(List<Integer> values) {
            addCriterion("ddcwp not in", values, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andDdcwpBetween(Integer value1, Integer value2) {
            addCriterion("ddcwp between", value1, value2, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andDdcwpNotBetween(Integer value1, Integer value2) {
            addCriterion("ddcwp not between", value1, value2, "ddcwp");
            return (Criteria) this;
        }

        public Criteria andMtcypIsNull() {
            addCriterion("mtcyp is null");
            return (Criteria) this;
        }

        public Criteria andMtcypIsNotNull() {
            addCriterion("mtcyp is not null");
            return (Criteria) this;
        }

        public Criteria andMtcypEqualTo(Integer value) {
            addCriterion("mtcyp =", value, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcypNotEqualTo(Integer value) {
            addCriterion("mtcyp <>", value, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcypGreaterThan(Integer value) {
            addCriterion("mtcyp >", value, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcypGreaterThanOrEqualTo(Integer value) {
            addCriterion("mtcyp >=", value, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcypLessThan(Integer value) {
            addCriterion("mtcyp <", value, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcypLessThanOrEqualTo(Integer value) {
            addCriterion("mtcyp <=", value, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcypIn(List<Integer> values) {
            addCriterion("mtcyp in", values, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcypNotIn(List<Integer> values) {
            addCriterion("mtcyp not in", values, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcypBetween(Integer value1, Integer value2) {
            addCriterion("mtcyp between", value1, value2, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcypNotBetween(Integer value1, Integer value2) {
            addCriterion("mtcyp not between", value1, value2, "mtcyp");
            return (Criteria) this;
        }

        public Criteria andMtcwpIsNull() {
            addCriterion("mtcwp is null");
            return (Criteria) this;
        }

        public Criteria andMtcwpIsNotNull() {
            addCriterion("mtcwp is not null");
            return (Criteria) this;
        }

        public Criteria andMtcwpEqualTo(Integer value) {
            addCriterion("mtcwp =", value, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andMtcwpNotEqualTo(Integer value) {
            addCriterion("mtcwp <>", value, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andMtcwpGreaterThan(Integer value) {
            addCriterion("mtcwp >", value, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andMtcwpGreaterThanOrEqualTo(Integer value) {
            addCriterion("mtcwp >=", value, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andMtcwpLessThan(Integer value) {
            addCriterion("mtcwp <", value, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andMtcwpLessThanOrEqualTo(Integer value) {
            addCriterion("mtcwp <=", value, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andMtcwpIn(List<Integer> values) {
            addCriterion("mtcwp in", values, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andMtcwpNotIn(List<Integer> values) {
            addCriterion("mtcwp not in", values, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andMtcwpBetween(Integer value1, Integer value2) {
            addCriterion("mtcwp between", value1, value2, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andMtcwpNotBetween(Integer value1, Integer value2) {
            addCriterion("mtcwp not between", value1, value2, "mtcwp");
            return (Criteria) this;
        }

        public Criteria andSlcIsNull() {
            addCriterion("slc is null");
            return (Criteria) this;
        }

        public Criteria andSlcIsNotNull() {
            addCriterion("slc is not null");
            return (Criteria) this;
        }

        public Criteria andSlcEqualTo(Integer value) {
            addCriterion("slc =", value, "slc");
            return (Criteria) this;
        }

        public Criteria andSlcNotEqualTo(Integer value) {
            addCriterion("slc <>", value, "slc");
            return (Criteria) this;
        }

        public Criteria andSlcGreaterThan(Integer value) {
            addCriterion("slc >", value, "slc");
            return (Criteria) this;
        }

        public Criteria andSlcGreaterThanOrEqualTo(Integer value) {
            addCriterion("slc >=", value, "slc");
            return (Criteria) this;
        }

        public Criteria andSlcLessThan(Integer value) {
            addCriterion("slc <", value, "slc");
            return (Criteria) this;
        }

        public Criteria andSlcLessThanOrEqualTo(Integer value) {
            addCriterion("slc <=", value, "slc");
            return (Criteria) this;
        }

        public Criteria andSlcIn(List<Integer> values) {
            addCriterion("slc in", values, "slc");
            return (Criteria) this;
        }

        public Criteria andSlcNotIn(List<Integer> values) {
            addCriterion("slc not in", values, "slc");
            return (Criteria) this;
        }

        public Criteria andSlcBetween(Integer value1, Integer value2) {
            addCriterion("slc between", value1, value2, "slc");
            return (Criteria) this;
        }

        public Criteria andSlcNotBetween(Integer value1, Integer value2) {
            addCriterion("slc not between", value1, value2, "slc");
            return (Criteria) this;
        }

        public Criteria andQcIsNull() {
            addCriterion("qc is null");
            return (Criteria) this;
        }

        public Criteria andQcIsNotNull() {
            addCriterion("qc is not null");
            return (Criteria) this;
        }

        public Criteria andQcEqualTo(Integer value) {
            addCriterion("qc =", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcNotEqualTo(Integer value) {
            addCriterion("qc <>", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcGreaterThan(Integer value) {
            addCriterion("qc >", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcGreaterThanOrEqualTo(Integer value) {
            addCriterion("qc >=", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcLessThan(Integer value) {
            addCriterion("qc <", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcLessThanOrEqualTo(Integer value) {
            addCriterion("qc <=", value, "qc");
            return (Criteria) this;
        }

        public Criteria andQcIn(List<Integer> values) {
            addCriterion("qc in", values, "qc");
            return (Criteria) this;
        }

        public Criteria andQcNotIn(List<Integer> values) {
            addCriterion("qc not in", values, "qc");
            return (Criteria) this;
        }

        public Criteria andQcBetween(Integer value1, Integer value2) {
            addCriterion("qc between", value1, value2, "qc");
            return (Criteria) this;
        }

        public Criteria andQcNotBetween(Integer value1, Integer value2) {
            addCriterion("qc not between", value1, value2, "qc");
            return (Criteria) this;
        }

        public Criteria andNycIsNull() {
            addCriterion("nyc is null");
            return (Criteria) this;
        }

        public Criteria andNycIsNotNull() {
            addCriterion("nyc is not null");
            return (Criteria) this;
        }

        public Criteria andNycEqualTo(Integer value) {
            addCriterion("nyc =", value, "nyc");
            return (Criteria) this;
        }

        public Criteria andNycNotEqualTo(Integer value) {
            addCriterion("nyc <>", value, "nyc");
            return (Criteria) this;
        }

        public Criteria andNycGreaterThan(Integer value) {
            addCriterion("nyc >", value, "nyc");
            return (Criteria) this;
        }

        public Criteria andNycGreaterThanOrEqualTo(Integer value) {
            addCriterion("nyc >=", value, "nyc");
            return (Criteria) this;
        }

        public Criteria andNycLessThan(Integer value) {
            addCriterion("nyc <", value, "nyc");
            return (Criteria) this;
        }

        public Criteria andNycLessThanOrEqualTo(Integer value) {
            addCriterion("nyc <=", value, "nyc");
            return (Criteria) this;
        }

        public Criteria andNycIn(List<Integer> values) {
            addCriterion("nyc in", values, "nyc");
            return (Criteria) this;
        }

        public Criteria andNycNotIn(List<Integer> values) {
            addCriterion("nyc not in", values, "nyc");
            return (Criteria) this;
        }

        public Criteria andNycBetween(Integer value1, Integer value2) {
            addCriterion("nyc between", value1, value2, "nyc");
            return (Criteria) this;
        }

        public Criteria andNycNotBetween(Integer value1, Integer value2) {
            addCriterion("nyc not between", value1, value2, "nyc");
            return (Criteria) this;
        }

        public Criteria andQtIsNull() {
            addCriterion("qt is null");
            return (Criteria) this;
        }

        public Criteria andQtIsNotNull() {
            addCriterion("qt is not null");
            return (Criteria) this;
        }

        public Criteria andQtEqualTo(Integer value) {
            addCriterion("qt =", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtNotEqualTo(Integer value) {
            addCriterion("qt <>", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtGreaterThan(Integer value) {
            addCriterion("qt >", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtGreaterThanOrEqualTo(Integer value) {
            addCriterion("qt >=", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtLessThan(Integer value) {
            addCriterion("qt <", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtLessThanOrEqualTo(Integer value) {
            addCriterion("qt <=", value, "qt");
            return (Criteria) this;
        }

        public Criteria andQtIn(List<Integer> values) {
            addCriterion("qt in", values, "qt");
            return (Criteria) this;
        }

        public Criteria andQtNotIn(List<Integer> values) {
            addCriterion("qt not in", values, "qt");
            return (Criteria) this;
        }

        public Criteria andQtBetween(Integer value1, Integer value2) {
            addCriterion("qt between", value1, value2, "qt");
            return (Criteria) this;
        }

        public Criteria andQtNotBetween(Integer value1, Integer value2) {
            addCriterion("qt not between", value1, value2, "qt");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
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