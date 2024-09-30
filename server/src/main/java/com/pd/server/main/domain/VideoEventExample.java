package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class VideoEventExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VideoEventExample() {
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

        public Criteria andKssjIsNull() {
            addCriterion("kssj is null");
            return (Criteria) this;
        }

        public Criteria andKssjIsNotNull() {
            addCriterion("kssj is not null");
            return (Criteria) this;
        }

        public Criteria andKssjEqualTo(String value) {
            addCriterion("kssj =", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotEqualTo(String value) {
            addCriterion("kssj <>", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjGreaterThan(String value) {
            addCriterion("kssj >", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjGreaterThanOrEqualTo(String value) {
            addCriterion("kssj >=", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjLessThan(String value) {
            addCriterion("kssj <", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjLessThanOrEqualTo(String value) {
            addCriterion("kssj <=", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjLike(String value) {
            addCriterion("kssj like", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotLike(String value) {
            addCriterion("kssj not like", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjIn(List<String> values) {
            addCriterion("kssj in", values, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotIn(List<String> values) {
            addCriterion("kssj not in", values, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjBetween(String value1, String value2) {
            addCriterion("kssj between", value1, value2, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotBetween(String value1, String value2) {
            addCriterion("kssj not between", value1, value2, "kssj");
            return (Criteria) this;
        }

        public Criteria andJssjIsNull() {
            addCriterion("jssj is null");
            return (Criteria) this;
        }

        public Criteria andJssjIsNotNull() {
            addCriterion("jssj is not null");
            return (Criteria) this;
        }

        public Criteria andJssjEqualTo(String value) {
            addCriterion("jssj =", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotEqualTo(String value) {
            addCriterion("jssj <>", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjGreaterThan(String value) {
            addCriterion("jssj >", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjGreaterThanOrEqualTo(String value) {
            addCriterion("jssj >=", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjLessThan(String value) {
            addCriterion("jssj <", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjLessThanOrEqualTo(String value) {
            addCriterion("jssj <=", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjLike(String value) {
            addCriterion("jssj like", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotLike(String value) {
            addCriterion("jssj not like", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjIn(List<String> values) {
            addCriterion("jssj in", values, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotIn(List<String> values) {
            addCriterion("jssj not in", values, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjBetween(String value1, String value2) {
            addCriterion("jssj between", value1, value2, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotBetween(String value1, String value2) {
            addCriterion("jssj not between", value1, value2, "jssj");
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

        public Criteria andWjljIsNull() {
            addCriterion("wjlj is null");
            return (Criteria) this;
        }

        public Criteria andWjljIsNotNull() {
            addCriterion("wjlj is not null");
            return (Criteria) this;
        }

        public Criteria andWjljEqualTo(String value) {
            addCriterion("wjlj =", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljNotEqualTo(String value) {
            addCriterion("wjlj <>", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljGreaterThan(String value) {
            addCriterion("wjlj >", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljGreaterThanOrEqualTo(String value) {
            addCriterion("wjlj >=", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljLessThan(String value) {
            addCriterion("wjlj <", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljLessThanOrEqualTo(String value) {
            addCriterion("wjlj <=", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljLike(String value) {
            addCriterion("wjlj like", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljNotLike(String value) {
            addCriterion("wjlj not like", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljIn(List<String> values) {
            addCriterion("wjlj in", values, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljNotIn(List<String> values) {
            addCriterion("wjlj not in", values, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljBetween(String value1, String value2) {
            addCriterion("wjlj between", value1, value2, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljNotBetween(String value1, String value2) {
            addCriterion("wjlj not between", value1, value2, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjmcIsNull() {
            addCriterion("wjmc is null");
            return (Criteria) this;
        }

        public Criteria andWjmcIsNotNull() {
            addCriterion("wjmc is not null");
            return (Criteria) this;
        }

        public Criteria andWjmcEqualTo(String value) {
            addCriterion("wjmc =", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotEqualTo(String value) {
            addCriterion("wjmc <>", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcGreaterThan(String value) {
            addCriterion("wjmc >", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcGreaterThanOrEqualTo(String value) {
            addCriterion("wjmc >=", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcLessThan(String value) {
            addCriterion("wjmc <", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcLessThanOrEqualTo(String value) {
            addCriterion("wjmc <=", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcLike(String value) {
            addCriterion("wjmc like", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotLike(String value) {
            addCriterion("wjmc not like", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcIn(List<String> values) {
            addCriterion("wjmc in", values, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotIn(List<String> values) {
            addCriterion("wjmc not in", values, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcBetween(String value1, String value2) {
            addCriterion("wjmc between", value1, value2, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotBetween(String value1, String value2) {
            addCriterion("wjmc not between", value1, value2, "wjmc");
            return (Criteria) this;
        }

        public Criteria andSfyspIsNull() {
            addCriterion("sfysp is null");
            return (Criteria) this;
        }

        public Criteria andSfyspIsNotNull() {
            addCriterion("sfysp is not null");
            return (Criteria) this;
        }

        public Criteria andSfyspEqualTo(Integer value) {
            addCriterion("sfysp =", value, "sfysp");
            return (Criteria) this;
        }

        public Criteria andSfyspNotEqualTo(Integer value) {
            addCriterion("sfysp <>", value, "sfysp");
            return (Criteria) this;
        }

        public Criteria andSfyspGreaterThan(Integer value) {
            addCriterion("sfysp >", value, "sfysp");
            return (Criteria) this;
        }

        public Criteria andSfyspGreaterThanOrEqualTo(Integer value) {
            addCriterion("sfysp >=", value, "sfysp");
            return (Criteria) this;
        }

        public Criteria andSfyspLessThan(Integer value) {
            addCriterion("sfysp <", value, "sfysp");
            return (Criteria) this;
        }

        public Criteria andSfyspLessThanOrEqualTo(Integer value) {
            addCriterion("sfysp <=", value, "sfysp");
            return (Criteria) this;
        }

        public Criteria andSfyspIn(List<Integer> values) {
            addCriterion("sfysp in", values, "sfysp");
            return (Criteria) this;
        }

        public Criteria andSfyspNotIn(List<Integer> values) {
            addCriterion("sfysp not in", values, "sfysp");
            return (Criteria) this;
        }

        public Criteria andSfyspBetween(Integer value1, Integer value2) {
            addCriterion("sfysp between", value1, value2, "sfysp");
            return (Criteria) this;
        }

        public Criteria andSfyspNotBetween(Integer value1, Integer value2) {
            addCriterion("sfysp not between", value1, value2, "sfysp");
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

        public Criteria andSxtipEqualTo(String value) {
            addCriterion("sxtip =", value, "sxtip");
            return (Criteria) this;
        }

        public Criteria andYfGreaterThanOrEqualTo(String value) {
            addCriterion("yf >=", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfLessThanOrEqualTo(String value) {
            addCriterion("yf <=", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfEqualTo(String value) {
            addCriterion("yf =", value, "yf");
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