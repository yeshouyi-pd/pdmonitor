package com.pd.server.secondary.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DlbDataSecExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DlbDataSecExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIccidIsNull() {
            addCriterion("ICCID is null");
            return (Criteria) this;
        }

        public Criteria andIccidIsNotNull() {
            addCriterion("ICCID is not null");
            return (Criteria) this;
        }

        public Criteria andIccidEqualTo(String value) {
            addCriterion("ICCID =", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidNotEqualTo(String value) {
            addCriterion("ICCID <>", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidGreaterThan(String value) {
            addCriterion("ICCID >", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidGreaterThanOrEqualTo(String value) {
            addCriterion("ICCID >=", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidLessThan(String value) {
            addCriterion("ICCID <", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidLessThanOrEqualTo(String value) {
            addCriterion("ICCID <=", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidLike(String value) {
            addCriterion("ICCID like", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidNotLike(String value) {
            addCriterion("ICCID not like", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidIn(List<String> values) {
            addCriterion("ICCID in", values, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidNotIn(List<String> values) {
            addCriterion("ICCID not in", values, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidBetween(String value1, String value2) {
            addCriterion("ICCID between", value1, value2, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidNotBetween(String value1, String value2) {
            addCriterion("ICCID not between", value1, value2, "iccid");
            return (Criteria) this;
        }

        public Criteria andIpportIsNull() {
            addCriterion("IPPort is null");
            return (Criteria) this;
        }

        public Criteria andIpportIsNotNull() {
            addCriterion("IPPort is not null");
            return (Criteria) this;
        }

        public Criteria andIpportEqualTo(String value) {
            addCriterion("IPPort =", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportNotEqualTo(String value) {
            addCriterion("IPPort <>", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportGreaterThan(String value) {
            addCriterion("IPPort >", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportGreaterThanOrEqualTo(String value) {
            addCriterion("IPPort >=", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportLessThan(String value) {
            addCriterion("IPPort <", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportLessThanOrEqualTo(String value) {
            addCriterion("IPPort <=", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportLike(String value) {
            addCriterion("IPPort like", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportNotLike(String value) {
            addCriterion("IPPort not like", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportIn(List<String> values) {
            addCriterion("IPPort in", values, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportNotIn(List<String> values) {
            addCriterion("IPPort not in", values, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportBetween(String value1, String value2) {
            addCriterion("IPPort between", value1, value2, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportNotBetween(String value1, String value2) {
            addCriterion("IPPort not between", value1, value2, "ipport");
            return (Criteria) this;
        }

        public Criteria andWdIsNull() {
            addCriterion("wd is null");
            return (Criteria) this;
        }

        public Criteria andWdIsNotNull() {
            addCriterion("wd is not null");
            return (Criteria) this;
        }

        public Criteria andWdEqualTo(String value) {
            addCriterion("wd =", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotEqualTo(String value) {
            addCriterion("wd <>", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdGreaterThan(String value) {
            addCriterion("wd >", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdGreaterThanOrEqualTo(String value) {
            addCriterion("wd >=", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLessThan(String value) {
            addCriterion("wd <", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLessThanOrEqualTo(String value) {
            addCriterion("wd <=", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLike(String value) {
            addCriterion("wd like", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotLike(String value) {
            addCriterion("wd not like", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdIn(List<String> values) {
            addCriterion("wd in", values, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotIn(List<String> values) {
            addCriterion("wd not in", values, "wd");
            return (Criteria) this;
        }

        public Criteria andWdBetween(String value1, String value2) {
            addCriterion("wd between", value1, value2, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotBetween(String value1, String value2) {
            addCriterion("wd not between", value1, value2, "wd");
            return (Criteria) this;
        }

        public Criteria andSjIsNull() {
            addCriterion("sj is null");
            return (Criteria) this;
        }

        public Criteria andSjIsNotNull() {
            addCriterion("sj is not null");
            return (Criteria) this;
        }

        public Criteria andSjEqualTo(String value) {
            addCriterion("sj =", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjNotEqualTo(String value) {
            addCriterion("sj <>", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjGreaterThan(String value) {
            addCriterion("sj >", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjGreaterThanOrEqualTo(String value) {
            addCriterion("sj >=", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjLessThan(String value) {
            addCriterion("sj <", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjLessThanOrEqualTo(String value) {
            addCriterion("sj <=", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjLike(String value) {
            addCriterion("sj like", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjNotLike(String value) {
            addCriterion("sj not like", value, "sj");
            return (Criteria) this;
        }

        public Criteria andSjIn(List<String> values) {
            addCriterion("sj in", values, "sj");
            return (Criteria) this;
        }

        public Criteria andSjNotIn(List<String> values) {
            addCriterion("sj not in", values, "sj");
            return (Criteria) this;
        }

        public Criteria andSjBetween(String value1, String value2) {
            addCriterion("sj between", value1, value2, "sj");
            return (Criteria) this;
        }

        public Criteria andSjNotBetween(String value1, String value2) {
            addCriterion("sj not between", value1, value2, "sj");
            return (Criteria) this;
        }

        public Criteria andCdztIsNull() {
            addCriterion("cdzt is null");
            return (Criteria) this;
        }

        public Criteria andCdztIsNotNull() {
            addCriterion("cdzt is not null");
            return (Criteria) this;
        }

        public Criteria andCdztEqualTo(String value) {
            addCriterion("cdzt =", value, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztNotEqualTo(String value) {
            addCriterion("cdzt <>", value, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztGreaterThan(String value) {
            addCriterion("cdzt >", value, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztGreaterThanOrEqualTo(String value) {
            addCriterion("cdzt >=", value, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztLessThan(String value) {
            addCriterion("cdzt <", value, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztLessThanOrEqualTo(String value) {
            addCriterion("cdzt <=", value, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztLike(String value) {
            addCriterion("cdzt like", value, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztNotLike(String value) {
            addCriterion("cdzt not like", value, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztIn(List<String> values) {
            addCriterion("cdzt in", values, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztNotIn(List<String> values) {
            addCriterion("cdzt not in", values, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztBetween(String value1, String value2) {
            addCriterion("cdzt between", value1, value2, "cdzt");
            return (Criteria) this;
        }

        public Criteria andCdztNotBetween(String value1, String value2) {
            addCriterion("cdzt not between", value1, value2, "cdzt");
            return (Criteria) this;
        }

        public Criteria andSjdateIsNull() {
            addCriterion("sjdate is null");
            return (Criteria) this;
        }

        public Criteria andSjdateIsNotNull() {
            addCriterion("sjdate is not null");
            return (Criteria) this;
        }

        public Criteria andSjdateEqualTo(Date value) {
            addCriterion("sjdate =", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateNotEqualTo(Date value) {
            addCriterion("sjdate <>", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateGreaterThan(Date value) {
            addCriterion("sjdate >", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateGreaterThanOrEqualTo(Date value) {
            addCriterion("sjdate >=", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateGreaterThanOrEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(sjdate,'"+type+"') >=", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateLessThan(Date value) {
            addCriterion("sjdate <", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateLessThanOrEqualTo(Date value) {
            addCriterion("sjdate <=", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateLessThanOrEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(sjdate,'"+type+"') <=", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateIn(List<Date> values) {
            addCriterion("sjdate in", values, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateNotIn(List<Date> values) {
            addCriterion("sjdate not in", values, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateBetween(Date value1, Date value2) {
            addCriterion("sjdate between", value1, value2, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateNotBetween(Date value1, Date value2) {
            addCriterion("sjdate not between", value1, value2, "sjdate");
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