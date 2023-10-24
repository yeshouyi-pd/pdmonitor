package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterQualityNewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WaterQualityNewExample() {
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

        public Criteria andCjsjIsNull() {
            addCriterion("cjsj is null");
            return (Criteria) this;
        }

        public Criteria andCjsjIsNotNull() {
            addCriterion("cjsj is not null");
            return (Criteria) this;
        }

        public Criteria andCjsjEqualTo(Date value) {
            addCriterion("cjsj =", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(cjsj,'"+type+"') =", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotEqualTo(Date value) {
            addCriterion("cjsj <>", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThan(Date value) {
            addCriterion("cjsj >", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThanOrEqualTo(Date value) {
            addCriterion("cjsj >=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThanOrEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(cjsj,'"+type+"') >=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThan(Date value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(Date value) {
            addCriterion("cjsj <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(cjsj,'"+type+"') <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjIn(List<Date> values) {
            addCriterion("cjsj in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotIn(List<Date> values) {
            addCriterion("cjsj not in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjBetween(Date value1, Date value2) {
            addCriterion("cjsj between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotBetween(Date value1, Date value2) {
            addCriterion("cjsj not between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andSbbhIsNull() {
            addCriterion("sbbh is null");
            return (Criteria) this;
        }

        public Criteria andSbbhIsNotNull() {
            addCriterion("sbbh is not null");
            return (Criteria) this;
        }

        public Criteria andSbbhEqualTo(String value) {
            addCriterion("sbbh =", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotEqualTo(String value) {
            addCriterion("sbbh <>", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhGreaterThan(String value) {
            addCriterion("sbbh >", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhGreaterThanOrEqualTo(String value) {
            addCriterion("sbbh >=", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhLessThan(String value) {
            addCriterion("sbbh <", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhLessThanOrEqualTo(String value) {
            addCriterion("sbbh <=", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhLike(String value) {
            addCriterion("sbbh like", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotLike(String value) {
            addCriterion("sbbh not like", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhIn(List<String> values) {
            addCriterion("sbbh in", values, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotIn(List<String> values) {
            addCriterion("sbbh not in", values, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhBetween(String value1, String value2) {
            addCriterion("sbbh between", value1, value2, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotBetween(String value1, String value2) {
            addCriterion("sbbh not between", value1, value2, "sbbh");
            return (Criteria) this;
        }

        public Criteria andOxidativeIsNull() {
            addCriterion("oxidative is null");
            return (Criteria) this;
        }

        public Criteria andOxidativeIsNotNull() {
            addCriterion("oxidative is not null");
            return (Criteria) this;
        }

        public Criteria andOxidativeEqualTo(String value) {
            addCriterion("oxidative =", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeNotEqualTo(String value) {
            addCriterion("oxidative <>", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeGreaterThan(String value) {
            addCriterion("oxidative >", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeGreaterThanOrEqualTo(String value) {
            addCriterion("oxidative >=", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeLessThan(String value) {
            addCriterion("oxidative <", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeLessThanOrEqualTo(String value) {
            addCriterion("oxidative <=", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeLike(String value) {
            addCriterion("oxidative like", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeNotLike(String value) {
            addCriterion("oxidative not like", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeIn(List<String> values) {
            addCriterion("oxidative in", values, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeNotIn(List<String> values) {
            addCriterion("oxidative not in", values, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeBetween(String value1, String value2) {
            addCriterion("oxidative between", value1, value2, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeNotBetween(String value1, String value2) {
            addCriterion("oxidative not between", value1, value2, "oxidative");
            return (Criteria) this;
        }

        public Criteria andChlorophyllIsNull() {
            addCriterion("chlorophyll is null");
            return (Criteria) this;
        }

        public Criteria andChlorophyllIsNotNull() {
            addCriterion("chlorophyll is not null");
            return (Criteria) this;
        }

        public Criteria andChlorophyllEqualTo(String value) {
            addCriterion("chlorophyll =", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllNotEqualTo(String value) {
            addCriterion("chlorophyll <>", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllGreaterThan(String value) {
            addCriterion("chlorophyll >", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllGreaterThanOrEqualTo(String value) {
            addCriterion("chlorophyll >=", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllLessThan(String value) {
            addCriterion("chlorophyll <", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllLessThanOrEqualTo(String value) {
            addCriterion("chlorophyll <=", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllLike(String value) {
            addCriterion("chlorophyll like", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllNotLike(String value) {
            addCriterion("chlorophyll not like", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllIn(List<String> values) {
            addCriterion("chlorophyll in", values, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllNotIn(List<String> values) {
            addCriterion("chlorophyll not in", values, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllBetween(String value1, String value2) {
            addCriterion("chlorophyll between", value1, value2, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllNotBetween(String value1, String value2) {
            addCriterion("chlorophyll not between", value1, value2, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andPhIsNull() {
            addCriterion("ph is null");
            return (Criteria) this;
        }

        public Criteria andPhIsNotNull() {
            addCriterion("ph is not null");
            return (Criteria) this;
        }

        public Criteria andPhEqualTo(String value) {
            addCriterion("ph =", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotEqualTo(String value) {
            addCriterion("ph <>", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhGreaterThan(String value) {
            addCriterion("ph >", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhGreaterThanOrEqualTo(String value) {
            addCriterion("ph >=", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhLessThan(String value) {
            addCriterion("ph <", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhLessThanOrEqualTo(String value) {
            addCriterion("ph <=", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhLike(String value) {
            addCriterion("ph like", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotLike(String value) {
            addCriterion("ph not like", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhIn(List<String> values) {
            addCriterion("ph in", values, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotIn(List<String> values) {
            addCriterion("ph not in", values, "ph");
            return (Criteria) this;
        }

        public Criteria andPhBetween(String value1, String value2) {
            addCriterion("ph between", value1, value2, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotBetween(String value1, String value2) {
            addCriterion("ph not between", value1, value2, "ph");
            return (Criteria) this;
        }

        public Criteria andAdIsNull() {
            addCriterion("ad is null");
            return (Criteria) this;
        }

        public Criteria andAdIsNotNull() {
            addCriterion("ad is not null");
            return (Criteria) this;
        }

        public Criteria andAdEqualTo(String value) {
            addCriterion("ad =", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdNotEqualTo(String value) {
            addCriterion("ad <>", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdGreaterThan(String value) {
            addCriterion("ad >", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdGreaterThanOrEqualTo(String value) {
            addCriterion("ad >=", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdLessThan(String value) {
            addCriterion("ad <", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdLessThanOrEqualTo(String value) {
            addCriterion("ad <=", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdLike(String value) {
            addCriterion("ad like", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdNotLike(String value) {
            addCriterion("ad not like", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdIn(List<String> values) {
            addCriterion("ad in", values, "ad");
            return (Criteria) this;
        }

        public Criteria andAdNotIn(List<String> values) {
            addCriterion("ad not in", values, "ad");
            return (Criteria) this;
        }

        public Criteria andAdBetween(String value1, String value2) {
            addCriterion("ad between", value1, value2, "ad");
            return (Criteria) this;
        }

        public Criteria andAdNotBetween(String value1, String value2) {
            addCriterion("ad not between", value1, value2, "ad");
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