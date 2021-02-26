package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebchatFileinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebchatFileinfoExample() {
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

        public Criteria andZpzlIsNull() {
            addCriterion("zpzl is null");
            return (Criteria) this;
        }

        public Criteria andZpzlIsNotNull() {
            addCriterion("zpzl is not null");
            return (Criteria) this;
        }

        public Criteria andZpzlEqualTo(String value) {
            addCriterion("zpzl =", value, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlNotEqualTo(String value) {
            addCriterion("zpzl <>", value, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlGreaterThan(String value) {
            addCriterion("zpzl >", value, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlGreaterThanOrEqualTo(String value) {
            addCriterion("zpzl >=", value, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlLessThan(String value) {
            addCriterion("zpzl <", value, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlLessThanOrEqualTo(String value) {
            addCriterion("zpzl <=", value, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlLike(String value) {
            addCriterion("zpzl like", value, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlNotLike(String value) {
            addCriterion("zpzl not like", value, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlIn(List<String> values) {
            addCriterion("zpzl in", values, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlNotIn(List<String> values) {
            addCriterion("zpzl not in", values, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlBetween(String value1, String value2) {
            addCriterion("zpzl between", value1, value2, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpzlNotBetween(String value1, String value2) {
            addCriterion("zpzl not between", value1, value2, "zpzl");
            return (Criteria) this;
        }

        public Criteria andZpljIsNull() {
            addCriterion("zplj is null");
            return (Criteria) this;
        }

        public Criteria andZpljIsNotNull() {
            addCriterion("zplj is not null");
            return (Criteria) this;
        }

        public Criteria andZpljEqualTo(String value) {
            addCriterion("zplj =", value, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljNotEqualTo(String value) {
            addCriterion("zplj <>", value, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljGreaterThan(String value) {
            addCriterion("zplj >", value, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljGreaterThanOrEqualTo(String value) {
            addCriterion("zplj >=", value, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljLessThan(String value) {
            addCriterion("zplj <", value, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljLessThanOrEqualTo(String value) {
            addCriterion("zplj <=", value, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljLike(String value) {
            addCriterion("zplj like", value, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljNotLike(String value) {
            addCriterion("zplj not like", value, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljIn(List<String> values) {
            addCriterion("zplj in", values, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljNotIn(List<String> values) {
            addCriterion("zplj not in", values, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljBetween(String value1, String value2) {
            addCriterion("zplj between", value1, value2, "zplj");
            return (Criteria) this;
        }

        public Criteria andZpljNotBetween(String value1, String value2) {
            addCriterion("zplj not between", value1, value2, "zplj");
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

        public Criteria andCjsjLessThan(Date value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(Date value) {
            addCriterion("cjsj <=", value, "cjsj");
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

        public Criteria andZplxIsNull() {
            addCriterion("zplx is null");
            return (Criteria) this;
        }

        public Criteria andZplxIsNotNull() {
            addCriterion("zplx is not null");
            return (Criteria) this;
        }

        public Criteria andZplxEqualTo(String value) {
            addCriterion("zplx =", value, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxNotEqualTo(String value) {
            addCriterion("zplx <>", value, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxGreaterThan(String value) {
            addCriterion("zplx >", value, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxGreaterThanOrEqualTo(String value) {
            addCriterion("zplx >=", value, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxLessThan(String value) {
            addCriterion("zplx <", value, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxLessThanOrEqualTo(String value) {
            addCriterion("zplx <=", value, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxLike(String value) {
            addCriterion("zplx like", value, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxNotLike(String value) {
            addCriterion("zplx not like", value, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxIn(List<String> values) {
            addCriterion("zplx in", values, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxNotIn(List<String> values) {
            addCriterion("zplx not in", values, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxBetween(String value1, String value2) {
            addCriterion("zplx between", value1, value2, "zplx");
            return (Criteria) this;
        }

        public Criteria andZplxNotBetween(String value1, String value2) {
            addCriterion("zplx not between", value1, value2, "zplx");
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