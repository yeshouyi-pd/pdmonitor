package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class FinlessPorpoiseTraverseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinlessPorpoiseTraverseExample() {
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

        public Criteria andXmbhmcIsNull() {
            addCriterion("xmbhmc is null");
            return (Criteria) this;
        }

        public Criteria andXmbhmcIsNotNull() {
            addCriterion("xmbhmc is not null");
            return (Criteria) this;
        }

        public Criteria andXmbhmcEqualTo(String value) {
            addCriterion("xmbhmc =", value, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcNotEqualTo(String value) {
            addCriterion("xmbhmc <>", value, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcGreaterThan(String value) {
            addCriterion("xmbhmc >", value, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcGreaterThanOrEqualTo(String value) {
            addCriterion("xmbhmc >=", value, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcLessThan(String value) {
            addCriterion("xmbhmc <", value, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcLessThanOrEqualTo(String value) {
            addCriterion("xmbhmc <=", value, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcLike(String value) {
            addCriterion("xmbhmc like", value, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcNotLike(String value) {
            addCriterion("xmbhmc not like", value, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcIn(List<String> values) {
            addCriterion("xmbhmc in", values, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcNotIn(List<String> values) {
            addCriterion("xmbhmc not in", values, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcBetween(String value1, String value2) {
            addCriterion("xmbhmc between", value1, value2, "xmbhmc");
            return (Criteria) this;
        }

        public Criteria andXmbhmcNotBetween(String value1, String value2) {
            addCriterion("xmbhmc not between", value1, value2, "xmbhmc");
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

        public Criteria andCjsjEqualTo(String value) {
            addCriterion("cjsj =", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotEqualTo(String value) {
            addCriterion("cjsj <>", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThan(String value) {
            addCriterion("cjsj >", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThanOrEqualTo(String value) {
            addCriterion("cjsj >=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThan(String value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(String value) {
            addCriterion("cjsj <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLike(String value) {
            addCriterion("cjsj like", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotLike(String value) {
            addCriterion("cjsj not like", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjIn(List<String> values) {
            addCriterion("cjsj in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotIn(List<String> values) {
            addCriterion("cjsj not in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjBetween(String value1, String value2) {
            addCriterion("cjsj between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotBetween(String value1, String value2) {
            addCriterion("cjsj not between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andTpljIsNull() {
            addCriterion("tplj is null");
            return (Criteria) this;
        }

        public Criteria andTpljIsNotNull() {
            addCriterion("tplj is not null");
            return (Criteria) this;
        }

        public Criteria andTpljEqualTo(String value) {
            addCriterion("tplj =", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljNotEqualTo(String value) {
            addCriterion("tplj <>", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljGreaterThan(String value) {
            addCriterion("tplj >", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljGreaterThanOrEqualTo(String value) {
            addCriterion("tplj >=", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljLessThan(String value) {
            addCriterion("tplj <", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljLessThanOrEqualTo(String value) {
            addCriterion("tplj <=", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljLike(String value) {
            addCriterion("tplj like", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljNotLike(String value) {
            addCriterion("tplj not like", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljIn(List<String> values) {
            addCriterion("tplj in", values, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljNotIn(List<String> values) {
            addCriterion("tplj not in", values, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljBetween(String value1, String value2) {
            addCriterion("tplj between", value1, value2, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljNotBetween(String value1, String value2) {
            addCriterion("tplj not between", value1, value2, "tplj");
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