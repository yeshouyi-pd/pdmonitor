package com.pd.server.secondary.domain;

import java.util.ArrayList;
import java.util.List;

public class Drivinglicense3Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Drivinglicense3Example() {
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

        public Criteria andSfzmhmIsNull() {
            addCriterion("SFZMHM is null");
            return (Criteria) this;
        }

        public Criteria andSfzmhmIsNotNull() {
            addCriterion("SFZMHM is not null");
            return (Criteria) this;
        }

        public Criteria andSfzmhmEqualTo(String value) {
            addCriterion("SFZMHM =", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotEqualTo(String value) {
            addCriterion("SFZMHM <>", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmGreaterThan(String value) {
            addCriterion("SFZMHM >", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmGreaterThanOrEqualTo(String value) {
            addCriterion("SFZMHM >=", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmLessThan(String value) {
            addCriterion("SFZMHM <", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmLessThanOrEqualTo(String value) {
            addCriterion("SFZMHM <=", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmLike(String value) {
            addCriterion("SFZMHM like", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotLike(String value) {
            addCriterion("SFZMHM not like", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmIn(List<String> values) {
            addCriterion("SFZMHM in", values, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotIn(List<String> values) {
            addCriterion("SFZMHM not in", values, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmBetween(String value1, String value2) {
            addCriterion("SFZMHM between", value1, value2, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotBetween(String value1, String value2) {
            addCriterion("SFZMHM not between", value1, value2, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andXmIsNull() {
            addCriterion("XM is null");
            return (Criteria) this;
        }

        public Criteria andXmIsNotNull() {
            addCriterion("XM is not null");
            return (Criteria) this;
        }

        public Criteria andXmEqualTo(String value) {
            addCriterion("XM =", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotEqualTo(String value) {
            addCriterion("XM <>", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmGreaterThan(String value) {
            addCriterion("XM >", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmGreaterThanOrEqualTo(String value) {
            addCriterion("XM >=", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmLessThan(String value) {
            addCriterion("XM <", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmLessThanOrEqualTo(String value) {
            addCriterion("XM <=", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmLike(String value) {
            addCriterion("XM like", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotLike(String value) {
            addCriterion("XM not like", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmIn(List<String> values) {
            addCriterion("XM in", values, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotIn(List<String> values) {
            addCriterion("XM not in", values, "xm");
            return (Criteria) this;
        }

        public Criteria andXmBetween(String value1, String value2) {
            addCriterion("XM between", value1, value2, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotBetween(String value1, String value2) {
            addCriterion("XM not between", value1, value2, "xm");
            return (Criteria) this;
        }

        public Criteria andZtIsNull() {
            addCriterion("ZT is null");
            return (Criteria) this;
        }

        public Criteria andZtIsNotNull() {
            addCriterion("ZT is not null");
            return (Criteria) this;
        }

        public Criteria andZtEqualTo(String value) {
            addCriterion("ZT =", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotEqualTo(String value) {
            addCriterion("ZT <>", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThan(String value) {
            addCriterion("ZT >", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThanOrEqualTo(String value) {
            addCriterion("ZT >=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThan(String value) {
            addCriterion("ZT <", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThanOrEqualTo(String value) {
            addCriterion("ZT <=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLike(String value) {
            addCriterion("ZT like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotLike(String value) {
            addCriterion("ZT not like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtIn(List<String> values) {
            addCriterion("ZT in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotIn(List<String> values) {
            addCriterion("ZT not in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtBetween(String value1, String value2) {
            addCriterion("ZT between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotBetween(String value1, String value2) {
            addCriterion("ZT not between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andCclzrqIsNull() {
            addCriterion("CCLZRQ is null");
            return (Criteria) this;
        }

        public Criteria andCclzrqIsNotNull() {
            addCriterion("CCLZRQ is not null");
            return (Criteria) this;
        }

        public Criteria andCclzrqEqualTo(String value) {
            addCriterion("CCLZRQ =", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqNotEqualTo(String value) {
            addCriterion("CCLZRQ <>", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqGreaterThan(String value) {
            addCriterion("CCLZRQ >", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqGreaterThanOrEqualTo(String value) {
            addCriterion("CCLZRQ >=", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqLessThan(String value) {
            addCriterion("CCLZRQ <", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqLessThanOrEqualTo(String value) {
            addCriterion("CCLZRQ <=", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqLike(String value) {
            addCriterion("CCLZRQ like", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqNotLike(String value) {
            addCriterion("CCLZRQ not like", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqIn(List<String> values) {
            addCriterion("CCLZRQ in", values, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqNotIn(List<String> values) {
            addCriterion("CCLZRQ not in", values, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqBetween(String value1, String value2) {
            addCriterion("CCLZRQ between", value1, value2, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqNotBetween(String value1, String value2) {
            addCriterion("CCLZRQ not between", value1, value2, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andYxqsIsNull() {
            addCriterion("YXQS is null");
            return (Criteria) this;
        }

        public Criteria andYxqsIsNotNull() {
            addCriterion("YXQS is not null");
            return (Criteria) this;
        }

        public Criteria andYxqsEqualTo(String value) {
            addCriterion("YXQS =", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsNotEqualTo(String value) {
            addCriterion("YXQS <>", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsGreaterThan(String value) {
            addCriterion("YXQS >", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsGreaterThanOrEqualTo(String value) {
            addCriterion("YXQS >=", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsLessThan(String value) {
            addCriterion("YXQS <", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsLessThanOrEqualTo(String value) {
            addCriterion("YXQS <=", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsLike(String value) {
            addCriterion("YXQS like", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsNotLike(String value) {
            addCriterion("YXQS not like", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsIn(List<String> values) {
            addCriterion("YXQS in", values, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsNotIn(List<String> values) {
            addCriterion("YXQS not in", values, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsBetween(String value1, String value2) {
            addCriterion("YXQS between", value1, value2, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsNotBetween(String value1, String value2) {
            addCriterion("YXQS not between", value1, value2, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqzIsNull() {
            addCriterion("YXQZ is null");
            return (Criteria) this;
        }

        public Criteria andYxqzIsNotNull() {
            addCriterion("YXQZ is not null");
            return (Criteria) this;
        }

        public Criteria andYxqzEqualTo(String value) {
            addCriterion("YXQZ =", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzNotEqualTo(String value) {
            addCriterion("YXQZ <>", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzGreaterThan(String value) {
            addCriterion("YXQZ >", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzGreaterThanOrEqualTo(String value) {
            addCriterion("YXQZ >=", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzLessThan(String value) {
            addCriterion("YXQZ <", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzLessThanOrEqualTo(String value) {
            addCriterion("YXQZ <=", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzLike(String value) {
            addCriterion("YXQZ like", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzNotLike(String value) {
            addCriterion("YXQZ not like", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzIn(List<String> values) {
            addCriterion("YXQZ in", values, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzNotIn(List<String> values) {
            addCriterion("YXQZ not in", values, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzBetween(String value1, String value2) {
            addCriterion("YXQZ between", value1, value2, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzNotBetween(String value1, String value2) {
            addCriterion("YXQZ not between", value1, value2, "yxqz");
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