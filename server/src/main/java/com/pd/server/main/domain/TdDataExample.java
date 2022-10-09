package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TdDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TdDataExample() {
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

        public Criteria andTdlxIsNull() {
            addCriterion("tdlx is null");
            return (Criteria) this;
        }

        public Criteria andTdlxIsNotNull() {
            addCriterion("tdlx is not null");
            return (Criteria) this;
        }

        public Criteria andTdlxEqualTo(String value) {
            addCriterion("tdlx =", value, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxNotEqualTo(String value) {
            addCriterion("tdlx <>", value, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxGreaterThan(String value) {
            addCriterion("tdlx >", value, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxGreaterThanOrEqualTo(String value) {
            addCriterion("tdlx >=", value, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxLessThan(String value) {
            addCriterion("tdlx <", value, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxLessThanOrEqualTo(String value) {
            addCriterion("tdlx <=", value, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxLike(String value) {
            addCriterion("tdlx like", value, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxNotLike(String value) {
            addCriterion("tdlx not like", value, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxIn(List<String> values) {
            addCriterion("tdlx in", values, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxNotIn(List<String> values) {
            addCriterion("tdlx not in", values, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxBetween(String value1, String value2) {
            addCriterion("tdlx between", value1, value2, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdlxNotBetween(String value1, String value2) {
            addCriterion("tdlx not between", value1, value2, "tdlx");
            return (Criteria) this;
        }

        public Criteria andTdhIsNull() {
            addCriterion("tdh is null");
            return (Criteria) this;
        }

        public Criteria andTdhIsNotNull() {
            addCriterion("tdh is not null");
            return (Criteria) this;
        }

        public Criteria andTdhEqualTo(String value) {
            addCriterion("tdh =", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotEqualTo(String value) {
            addCriterion("tdh <>", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhGreaterThan(String value) {
            addCriterion("tdh >", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhGreaterThanOrEqualTo(String value) {
            addCriterion("tdh >=", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhLessThan(String value) {
            addCriterion("tdh <", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhLessThanOrEqualTo(String value) {
            addCriterion("tdh <=", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhLike(String value) {
            addCriterion("tdh like", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotLike(String value) {
            addCriterion("tdh not like", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhIn(List<String> values) {
            addCriterion("tdh in", values, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotIn(List<String> values) {
            addCriterion("tdh not in", values, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhBetween(String value1, String value2) {
            addCriterion("tdh between", value1, value2, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotBetween(String value1, String value2) {
            addCriterion("tdh not between", value1, value2, "tdh");
            return (Criteria) this;
        }

        public Criteria andDyIsNull() {
            addCriterion("dy is null");
            return (Criteria) this;
        }

        public Criteria andDyIsNotNull() {
            addCriterion("dy is not null");
            return (Criteria) this;
        }

        public Criteria andDyEqualTo(String value) {
            addCriterion("dy =", value, "dy");
            return (Criteria) this;
        }

        public Criteria andDyNotEqualTo(String value) {
            addCriterion("dy <>", value, "dy");
            return (Criteria) this;
        }

        public Criteria andDyGreaterThan(String value) {
            addCriterion("dy >", value, "dy");
            return (Criteria) this;
        }

        public Criteria andDyGreaterThanOrEqualTo(String value) {
            addCriterion("dy >=", value, "dy");
            return (Criteria) this;
        }

        public Criteria andDyLessThan(String value) {
            addCriterion("dy <", value, "dy");
            return (Criteria) this;
        }

        public Criteria andDyLessThanOrEqualTo(String value) {
            addCriterion("dy <=", value, "dy");
            return (Criteria) this;
        }

        public Criteria andDyLike(String value) {
            addCriterion("dy like", value, "dy");
            return (Criteria) this;
        }

        public Criteria andDyNotLike(String value) {
            addCriterion("dy not like", value, "dy");
            return (Criteria) this;
        }

        public Criteria andDyIn(List<String> values) {
            addCriterion("dy in", values, "dy");
            return (Criteria) this;
        }

        public Criteria andDyNotIn(List<String> values) {
            addCriterion("dy not in", values, "dy");
            return (Criteria) this;
        }

        public Criteria andDyBetween(String value1, String value2) {
            addCriterion("dy between", value1, value2, "dy");
            return (Criteria) this;
        }

        public Criteria andDyNotBetween(String value1, String value2) {
            addCriterion("dy not between", value1, value2, "dy");
            return (Criteria) this;
        }

        public Criteria andDlIsNull() {
            addCriterion("dl is null");
            return (Criteria) this;
        }

        public Criteria andDlIsNotNull() {
            addCriterion("dl is not null");
            return (Criteria) this;
        }

        public Criteria andDlEqualTo(String value) {
            addCriterion("dl =", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlNotEqualTo(String value) {
            addCriterion("dl <>", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlGreaterThan(String value) {
            addCriterion("dl >", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlGreaterThanOrEqualTo(String value) {
            addCriterion("dl >=", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlLessThan(String value) {
            addCriterion("dl <", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlLessThanOrEqualTo(String value) {
            addCriterion("dl <=", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlLike(String value) {
            addCriterion("dl like", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlNotLike(String value) {
            addCriterion("dl not like", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlIn(List<String> values) {
            addCriterion("dl in", values, "dl");
            return (Criteria) this;
        }

        public Criteria andDlNotIn(List<String> values) {
            addCriterion("dl not in", values, "dl");
            return (Criteria) this;
        }

        public Criteria andDlBetween(String value1, String value2) {
            addCriterion("dl between", value1, value2, "dl");
            return (Criteria) this;
        }

        public Criteria andDlNotBetween(String value1, String value2) {
            addCriterion("dl not between", value1, value2, "dl");
            return (Criteria) this;
        }

        public Criteria andTdztIsNull() {
            addCriterion("tdzt is null");
            return (Criteria) this;
        }

        public Criteria andTdztIsNotNull() {
            addCriterion("tdzt is not null");
            return (Criteria) this;
        }

        public Criteria andTdztEqualTo(String value) {
            addCriterion("tdzt =", value, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztNotEqualTo(String value) {
            addCriterion("tdzt <>", value, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztGreaterThan(String value) {
            addCriterion("tdzt >", value, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztGreaterThanOrEqualTo(String value) {
            addCriterion("tdzt >=", value, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztLessThan(String value) {
            addCriterion("tdzt <", value, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztLessThanOrEqualTo(String value) {
            addCriterion("tdzt <=", value, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztLike(String value) {
            addCriterion("tdzt like", value, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztNotLike(String value) {
            addCriterion("tdzt not like", value, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztIn(List<String> values) {
            addCriterion("tdzt in", values, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztNotIn(List<String> values) {
            addCriterion("tdzt not in", values, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztBetween(String value1, String value2) {
            addCriterion("tdzt between", value1, value2, "tdzt");
            return (Criteria) this;
        }

        public Criteria andTdztNotBetween(String value1, String value2) {
            addCriterion("tdzt not between", value1, value2, "tdzt");
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