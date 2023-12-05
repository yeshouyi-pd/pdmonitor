package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class ForecastNumExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ForecastNumExample() {
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

        public Criteria andCxcsIsNull() {
            addCriterion("cxcs is null");
            return (Criteria) this;
        }

        public Criteria andCxcsIsNotNull() {
            addCriterion("cxcs is not null");
            return (Criteria) this;
        }

        public Criteria andCxcsEqualTo(String value) {
            addCriterion("cxcs =", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsNotEqualTo(String value) {
            addCriterion("cxcs <>", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsGreaterThan(String value) {
            addCriterion("cxcs >", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsGreaterThanOrEqualTo(String value) {
            addCriterion("cxcs >=", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsLessThan(String value) {
            addCriterion("cxcs <", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsLessThanOrEqualTo(String value) {
            addCriterion("cxcs <=", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsLike(String value) {
            addCriterion("cxcs like", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsNotLike(String value) {
            addCriterion("cxcs not like", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsIn(List<String> values) {
            addCriterion("cxcs in", values, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsNotIn(List<String> values) {
            addCriterion("cxcs not in", values, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsBetween(String value1, String value2) {
            addCriterion("cxcs between", value1, value2, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsNotBetween(String value1, String value2) {
            addCriterion("cxcs not between", value1, value2, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxsjIsNull() {
            addCriterion("cxsj is null");
            return (Criteria) this;
        }

        public Criteria andCxsjIsNotNull() {
            addCriterion("cxsj is not null");
            return (Criteria) this;
        }

        public Criteria andCxsjEqualTo(String value) {
            addCriterion("cxsj =", value, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjNotEqualTo(String value) {
            addCriterion("cxsj <>", value, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjGreaterThan(String value) {
            addCriterion("cxsj >", value, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjGreaterThanOrEqualTo(String value) {
            addCriterion("cxsj >=", value, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjLessThan(String value) {
            addCriterion("cxsj <", value, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjLessThanOrEqualTo(String value) {
            addCriterion("cxsj <=", value, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjLike(String value) {
            addCriterion("cxsj like", value, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjNotLike(String value) {
            addCriterion("cxsj not like", value, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjIn(List<String> values) {
            addCriterion("cxsj in", values, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjNotIn(List<String> values) {
            addCriterion("cxsj not in", values, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjBetween(String value1, String value2) {
            addCriterion("cxsj between", value1, value2, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxsjNotBetween(String value1, String value2) {
            addCriterion("cxsj not between", value1, value2, "cxsj");
            return (Criteria) this;
        }

        public Criteria andCxrqIsNull() {
            addCriterion("cxrq is null");
            return (Criteria) this;
        }

        public Criteria andCxrqIsNotNull() {
            addCriterion("cxrq is not null");
            return (Criteria) this;
        }

        public Criteria andCxrqEqualTo(String value) {
            addCriterion("cxrq =", value, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqNotEqualTo(String value) {
            addCriterion("cxrq <>", value, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqGreaterThan(String value) {
            addCriterion("cxrq >", value, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqGreaterThanOrEqualTo(String value) {
            addCriterion("cxrq >=", value, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqLessThan(String value) {
            addCriterion("cxrq <", value, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqLessThanOrEqualTo(String value) {
            addCriterion("cxrq <=", value, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqLike(String value) {
            addCriterion("cxrq like", value, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqNotLike(String value) {
            addCriterion("cxrq not like", value, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqIn(List<String> values) {
            addCriterion("cxrq in", values, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqNotIn(List<String> values) {
            addCriterion("cxrq not in", values, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqBetween(String value1, String value2) {
            addCriterion("cxrq between", value1, value2, "cxrq");
            return (Criteria) this;
        }

        public Criteria andCxrqNotBetween(String value1, String value2) {
            addCriterion("cxrq not between", value1, value2, "cxrq");
            return (Criteria) this;
        }

        public Criteria andDepcodeIsNull() {
            addCriterion("depcode is null");
            return (Criteria) this;
        }

        public Criteria andDepcodeIsNotNull() {
            addCriterion("depcode is not null");
            return (Criteria) this;
        }

        public Criteria andDepcodeEqualTo(String value) {
            addCriterion("depcode =", value, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeNotEqualTo(String value) {
            addCriterion("depcode <>", value, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeGreaterThan(String value) {
            addCriterion("depcode >", value, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeGreaterThanOrEqualTo(String value) {
            addCriterion("depcode >=", value, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeLessThan(String value) {
            addCriterion("depcode <", value, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeLessThanOrEqualTo(String value) {
            addCriterion("depcode <=", value, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeLike(String value) {
            addCriterion("depcode like", value, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeNotLike(String value) {
            addCriterion("depcode not like", value, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeIn(List<String> values) {
            addCriterion("depcode in", values, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeNotIn(List<String> values) {
            addCriterion("depcode not in", values, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeBetween(String value1, String value2) {
            addCriterion("depcode between", value1, value2, "depcode");
            return (Criteria) this;
        }

        public Criteria andDepcodeNotBetween(String value1, String value2) {
            addCriterion("depcode not between", value1, value2, "depcode");
            return (Criteria) this;
        }

        public Criteria andXmbhIsNull() {
            addCriterion("xmbh is null");
            return (Criteria) this;
        }

        public Criteria andXmbhIsNotNull() {
            addCriterion("xmbh is not null");
            return (Criteria) this;
        }

        public Criteria andXmbhEqualTo(String value) {
            addCriterion("xmbh =", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhNotEqualTo(String value) {
            addCriterion("xmbh <>", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhGreaterThan(String value) {
            addCriterion("xmbh >", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhGreaterThanOrEqualTo(String value) {
            addCriterion("xmbh >=", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhLessThan(String value) {
            addCriterion("xmbh <", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhLessThanOrEqualTo(String value) {
            addCriterion("xmbh <=", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhLike(String value) {
            addCriterion("xmbh like", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhNotLike(String value) {
            addCriterion("xmbh not like", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhIn(List<String> values) {
            addCriterion("xmbh in", values, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhNotIn(List<String> values) {
            addCriterion("xmbh not in", values, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhBetween(String value1, String value2) {
            addCriterion("xmbh between", value1, value2, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhNotBetween(String value1, String value2) {
            addCriterion("xmbh not between", value1, value2, "xmbh");
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