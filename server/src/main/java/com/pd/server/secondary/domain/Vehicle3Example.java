package com.pd.server.secondary.domain;

import java.util.ArrayList;
import java.util.List;

public class Vehicle3Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Vehicle3Example() {
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

        public Criteria andHpzlIsNull() {
            addCriterion("HPZL is null");
            return (Criteria) this;
        }

        public Criteria andHpzlIsNotNull() {
            addCriterion("HPZL is not null");
            return (Criteria) this;
        }

        public Criteria andHpzlEqualTo(String value) {
            addCriterion("HPZL =", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotEqualTo(String value) {
            addCriterion("HPZL <>", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlGreaterThan(String value) {
            addCriterion("HPZL >", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlGreaterThanOrEqualTo(String value) {
            addCriterion("HPZL >=", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlLessThan(String value) {
            addCriterion("HPZL <", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlLessThanOrEqualTo(String value) {
            addCriterion("HPZL <=", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlLike(String value) {
            addCriterion("HPZL like", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotLike(String value) {
            addCriterion("HPZL not like", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlIn(List<String> values) {
            addCriterion("HPZL in", values, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotIn(List<String> values) {
            addCriterion("HPZL not in", values, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlBetween(String value1, String value2) {
            addCriterion("HPZL between", value1, value2, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotBetween(String value1, String value2) {
            addCriterion("HPZL not between", value1, value2, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHphmIsNull() {
            addCriterion("HPHM is null");
            return (Criteria) this;
        }

        public Criteria andHphmIsNotNull() {
            addCriterion("HPHM is not null");
            return (Criteria) this;
        }

        public Criteria andHphmEqualTo(String value) {
            addCriterion("HPHM =", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotEqualTo(String value) {
            addCriterion("HPHM <>", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmGreaterThan(String value) {
            addCriterion("HPHM >", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmGreaterThanOrEqualTo(String value) {
            addCriterion("HPHM >=", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmLessThan(String value) {
            addCriterion("HPHM <", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmLessThanOrEqualTo(String value) {
            addCriterion("HPHM <=", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmLike(String value) {
            addCriterion("HPHM like", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotLike(String value) {
            addCriterion("HPHM not like", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmIn(List<String> values) {
            addCriterion("HPHM in", values, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotIn(List<String> values) {
            addCriterion("HPHM not in", values, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmBetween(String value1, String value2) {
            addCriterion("HPHM between", value1, value2, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotBetween(String value1, String value2) {
            addCriterion("HPHM not between", value1, value2, "hphm");
            return (Criteria) this;
        }

        public Criteria andSyrIsNull() {
            addCriterion("SYR is null");
            return (Criteria) this;
        }

        public Criteria andSyrIsNotNull() {
            addCriterion("SYR is not null");
            return (Criteria) this;
        }

        public Criteria andSyrEqualTo(String value) {
            addCriterion("SYR =", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotEqualTo(String value) {
            addCriterion("SYR <>", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrGreaterThan(String value) {
            addCriterion("SYR >", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrGreaterThanOrEqualTo(String value) {
            addCriterion("SYR >=", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrLessThan(String value) {
            addCriterion("SYR <", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrLessThanOrEqualTo(String value) {
            addCriterion("SYR <=", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrLike(String value) {
            addCriterion("SYR like", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotLike(String value) {
            addCriterion("SYR not like", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrIn(List<String> values) {
            addCriterion("SYR in", values, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotIn(List<String> values) {
            addCriterion("SYR not in", values, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrBetween(String value1, String value2) {
            addCriterion("SYR between", value1, value2, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotBetween(String value1, String value2) {
            addCriterion("SYR not between", value1, value2, "syr");
            return (Criteria) this;
        }

        public Criteria andClsbdhIsNull() {
            addCriterion("CLSBDH is null");
            return (Criteria) this;
        }

        public Criteria andClsbdhIsNotNull() {
            addCriterion("CLSBDH is not null");
            return (Criteria) this;
        }

        public Criteria andClsbdhEqualTo(String value) {
            addCriterion("CLSBDH =", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotEqualTo(String value) {
            addCriterion("CLSBDH <>", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhGreaterThan(String value) {
            addCriterion("CLSBDH >", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhGreaterThanOrEqualTo(String value) {
            addCriterion("CLSBDH >=", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhLessThan(String value) {
            addCriterion("CLSBDH <", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhLessThanOrEqualTo(String value) {
            addCriterion("CLSBDH <=", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhLike(String value) {
            addCriterion("CLSBDH like", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotLike(String value) {
            addCriterion("CLSBDH not like", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhIn(List<String> values) {
            addCriterion("CLSBDH in", values, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotIn(List<String> values) {
            addCriterion("CLSBDH not in", values, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhBetween(String value1, String value2) {
            addCriterion("CLSBDH between", value1, value2, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotBetween(String value1, String value2) {
            addCriterion("CLSBDH not between", value1, value2, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andFdjhIsNull() {
            addCriterion("FDJH is null");
            return (Criteria) this;
        }

        public Criteria andFdjhIsNotNull() {
            addCriterion("FDJH is not null");
            return (Criteria) this;
        }

        public Criteria andFdjhEqualTo(String value) {
            addCriterion("FDJH =", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhNotEqualTo(String value) {
            addCriterion("FDJH <>", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhGreaterThan(String value) {
            addCriterion("FDJH >", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhGreaterThanOrEqualTo(String value) {
            addCriterion("FDJH >=", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhLessThan(String value) {
            addCriterion("FDJH <", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhLessThanOrEqualTo(String value) {
            addCriterion("FDJH <=", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhLike(String value) {
            addCriterion("FDJH like", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhNotLike(String value) {
            addCriterion("FDJH not like", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhIn(List<String> values) {
            addCriterion("FDJH in", values, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhNotIn(List<String> values) {
            addCriterion("FDJH not in", values, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhBetween(String value1, String value2) {
            addCriterion("FDJH between", value1, value2, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhNotBetween(String value1, String value2) {
            addCriterion("FDJH not between", value1, value2, "fdjh");
            return (Criteria) this;
        }

        public Criteria andCcdjrqIsNull() {
            addCriterion("CCDJRQ is null");
            return (Criteria) this;
        }

        public Criteria andCcdjrqIsNotNull() {
            addCriterion("CCDJRQ is not null");
            return (Criteria) this;
        }

        public Criteria andCcdjrqEqualTo(String value) {
            addCriterion("CCDJRQ =", value, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqNotEqualTo(String value) {
            addCriterion("CCDJRQ <>", value, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqGreaterThan(String value) {
            addCriterion("CCDJRQ >", value, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqGreaterThanOrEqualTo(String value) {
            addCriterion("CCDJRQ >=", value, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqLessThan(String value) {
            addCriterion("CCDJRQ <", value, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqLessThanOrEqualTo(String value) {
            addCriterion("CCDJRQ <=", value, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqLike(String value) {
            addCriterion("CCDJRQ like", value, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqNotLike(String value) {
            addCriterion("CCDJRQ not like", value, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqIn(List<String> values) {
            addCriterion("CCDJRQ in", values, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqNotIn(List<String> values) {
            addCriterion("CCDJRQ not in", values, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqBetween(String value1, String value2) {
            addCriterion("CCDJRQ between", value1, value2, "ccdjrq");
            return (Criteria) this;
        }

        public Criteria andCcdjrqNotBetween(String value1, String value2) {
            addCriterion("CCDJRQ not between", value1, value2, "ccdjrq");
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