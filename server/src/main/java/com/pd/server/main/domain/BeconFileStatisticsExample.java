package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class BeconFileStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BeconFileStatisticsExample() {
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

        public Criteria andXbidIsNull() {
            addCriterion("xbid is null");
            return (Criteria) this;
        }

        public Criteria andXbidIsNotNull() {
            addCriterion("xbid is not null");
            return (Criteria) this;
        }

        public Criteria andXbidEqualTo(String value) {
            addCriterion("xbid =", value, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidNotEqualTo(String value) {
            addCriterion("xbid <>", value, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidGreaterThan(String value) {
            addCriterion("xbid >", value, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidGreaterThanOrEqualTo(String value) {
            addCriterion("xbid >=", value, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidLessThan(String value) {
            addCriterion("xbid <", value, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidLessThanOrEqualTo(String value) {
            addCriterion("xbid <=", value, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidLike(String value) {
            addCriterion("xbid like", value, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidNotLike(String value) {
            addCriterion("xbid not like", value, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidIn(List<String> values) {
            addCriterion("xbid in", values, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidNotIn(List<String> values) {
            addCriterion("xbid not in", values, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidBetween(String value1, String value2) {
            addCriterion("xbid between", value1, value2, "xbid");
            return (Criteria) this;
        }

        public Criteria andXbidNotBetween(String value1, String value2) {
            addCriterion("xbid not between", value1, value2, "xbid");
            return (Criteria) this;
        }

        public Criteria andSbbhsIsNull() {
            addCriterion("sbbhs is null");
            return (Criteria) this;
        }

        public Criteria andSbbhsIsNotNull() {
            addCriterion("sbbhs is not null");
            return (Criteria) this;
        }

        public Criteria andSbbhsEqualTo(String value) {
            addCriterion("sbbhs =", value, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsNotEqualTo(String value) {
            addCriterion("sbbhs <>", value, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsGreaterThan(String value) {
            addCriterion("sbbhs >", value, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsGreaterThanOrEqualTo(String value) {
            addCriterion("sbbhs >=", value, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsLessThan(String value) {
            addCriterion("sbbhs <", value, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsLessThanOrEqualTo(String value) {
            addCriterion("sbbhs <=", value, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsLike(String value) {
            addCriterion("sbbhs like", value, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsNotLike(String value) {
            addCriterion("sbbhs not like", value, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsIn(List<String> values) {
            addCriterion("sbbhs in", values, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsNotIn(List<String> values) {
            addCriterion("sbbhs not in", values, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsBetween(String value1, String value2) {
            addCriterion("sbbhs between", value1, value2, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbbhsNotBetween(String value1, String value2) {
            addCriterion("sbbhs not between", value1, value2, "sbbhs");
            return (Criteria) this;
        }

        public Criteria andSbmcsIsNull() {
            addCriterion("sbmcs is null");
            return (Criteria) this;
        }

        public Criteria andSbmcsIsNotNull() {
            addCriterion("sbmcs is not null");
            return (Criteria) this;
        }

        public Criteria andSbmcsEqualTo(String value) {
            addCriterion("sbmcs =", value, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsNotEqualTo(String value) {
            addCriterion("sbmcs <>", value, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsGreaterThan(String value) {
            addCriterion("sbmcs >", value, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsGreaterThanOrEqualTo(String value) {
            addCriterion("sbmcs >=", value, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsLessThan(String value) {
            addCriterion("sbmcs <", value, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsLessThanOrEqualTo(String value) {
            addCriterion("sbmcs <=", value, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsLike(String value) {
            addCriterion("sbmcs like", value, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsNotLike(String value) {
            addCriterion("sbmcs not like", value, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsIn(List<String> values) {
            addCriterion("sbmcs in", values, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsNotIn(List<String> values) {
            addCriterion("sbmcs not in", values, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsBetween(String value1, String value2) {
            addCriterion("sbmcs between", value1, value2, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andSbmcsNotBetween(String value1, String value2) {
            addCriterion("sbmcs not between", value1, value2, "sbmcs");
            return (Criteria) this;
        }

        public Criteria andRqIsNull() {
            addCriterion("rq is null");
            return (Criteria) this;
        }

        public Criteria andRqIsNotNull() {
            addCriterion("rq is not null");
            return (Criteria) this;
        }

        public Criteria andRqEqualTo(String value) {
            addCriterion("rq =", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotEqualTo(String value) {
            addCriterion("rq <>", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqGreaterThan(String value) {
            addCriterion("rq >", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqGreaterThanOrEqualTo(String value) {
            addCriterion("rq >=", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqLessThan(String value) {
            addCriterion("rq <", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqLessThanOrEqualTo(String value) {
            addCriterion("rq <=", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqLike(String value) {
            addCriterion("rq like", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotLike(String value) {
            addCriterion("rq not like", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqIn(List<String> values) {
            addCriterion("rq in", values, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotIn(List<String> values) {
            addCriterion("rq not in", values, "rq");
            return (Criteria) this;
        }

        public Criteria andRqBetween(String value1, String value2) {
            addCriterion("rq between", value1, value2, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotBetween(String value1, String value2) {
            addCriterion("rq not between", value1, value2, "rq");
            return (Criteria) this;
        }

        public Criteria andCjsjsIsNull() {
            addCriterion("cjsjs is null");
            return (Criteria) this;
        }

        public Criteria andCjsjsIsNotNull() {
            addCriterion("cjsjs is not null");
            return (Criteria) this;
        }

        public Criteria andCjsjsEqualTo(String value) {
            addCriterion("cjsjs =", value, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsNotEqualTo(String value) {
            addCriterion("cjsjs <>", value, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsGreaterThan(String value) {
            addCriterion("cjsjs >", value, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsGreaterThanOrEqualTo(String value) {
            addCriterion("cjsjs >=", value, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsLessThan(String value) {
            addCriterion("cjsjs <", value, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsLessThanOrEqualTo(String value) {
            addCriterion("cjsjs <=", value, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsLike(String value) {
            addCriterion("cjsjs like", value, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsNotLike(String value) {
            addCriterion("cjsjs not like", value, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsIn(List<String> values) {
            addCriterion("cjsjs in", values, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsNotIn(List<String> values) {
            addCriterion("cjsjs not in", values, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsBetween(String value1, String value2) {
            addCriterion("cjsjs between", value1, value2, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andCjsjsNotBetween(String value1, String value2) {
            addCriterion("cjsjs not between", value1, value2, "cjsjs");
            return (Criteria) this;
        }

        public Criteria andGpsIsNull() {
            addCriterion("gps is null");
            return (Criteria) this;
        }

        public Criteria andGpsIsNotNull() {
            addCriterion("gps is not null");
            return (Criteria) this;
        }

        public Criteria andGpsEqualTo(String value) {
            addCriterion("gps =", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotEqualTo(String value) {
            addCriterion("gps <>", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsGreaterThan(String value) {
            addCriterion("gps >", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsGreaterThanOrEqualTo(String value) {
            addCriterion("gps >=", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsLessThan(String value) {
            addCriterion("gps <", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsLessThanOrEqualTo(String value) {
            addCriterion("gps <=", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsLike(String value) {
            addCriterion("gps like", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotLike(String value) {
            addCriterion("gps not like", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsIn(List<String> values) {
            addCriterion("gps in", values, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotIn(List<String> values) {
            addCriterion("gps not in", values, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsBetween(String value1, String value2) {
            addCriterion("gps between", value1, value2, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotBetween(String value1, String value2) {
            addCriterion("gps not between", value1, value2, "gps");
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

        public Criteria andSmIsNull() {
            addCriterion("sm is null");
            return (Criteria) this;
        }

        public Criteria andSmIsNotNull() {
            addCriterion("sm is not null");
            return (Criteria) this;
        }

        public Criteria andSmEqualTo(String value) {
            addCriterion("sm =", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotEqualTo(String value) {
            addCriterion("sm <>", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmGreaterThan(String value) {
            addCriterion("sm >", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmGreaterThanOrEqualTo(String value) {
            addCriterion("sm >=", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLessThan(String value) {
            addCriterion("sm <", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLessThanOrEqualTo(String value) {
            addCriterion("sm <=", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLike(String value) {
            addCriterion("sm like", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotLike(String value) {
            addCriterion("sm not like", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmIn(List<String> values) {
            addCriterion("sm in", values, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotIn(List<String> values) {
            addCriterion("sm not in", values, "sm");
            return (Criteria) this;
        }

        public Criteria andSmBetween(String value1, String value2) {
            addCriterion("sm between", value1, value2, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotBetween(String value1, String value2) {
            addCriterion("sm not between", value1, value2, "sm");
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