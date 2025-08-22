package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class SonarSingleValueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SonarSingleValueExample() {
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

        public Criteria andSingleTargetIdIsNull() {
            addCriterion("single_target_id is null");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdIsNotNull() {
            addCriterion("single_target_id is not null");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdEqualTo(String value) {
            addCriterion("single_target_id =", value, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdNotEqualTo(String value) {
            addCriterion("single_target_id <>", value, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdGreaterThan(String value) {
            addCriterion("single_target_id >", value, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdGreaterThanOrEqualTo(String value) {
            addCriterion("single_target_id >=", value, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdLessThan(String value) {
            addCriterion("single_target_id <", value, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdLessThanOrEqualTo(String value) {
            addCriterion("single_target_id <=", value, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdLike(String value) {
            addCriterion("single_target_id like", value, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdNotLike(String value) {
            addCriterion("single_target_id not like", value, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdIn(List<String> values) {
            addCriterion("single_target_id in", values, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdNotIn(List<String> values) {
            addCriterion("single_target_id not in", values, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdBetween(String value1, String value2) {
            addCriterion("single_target_id between", value1, value2, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andSingleTargetIdNotBetween(String value1, String value2) {
            addCriterion("single_target_id not between", value1, value2, "singleTargetId");
            return (Criteria) this;
        }

        public Criteria andDTsIsNull() {
            addCriterion("d_ts is null");
            return (Criteria) this;
        }

        public Criteria andDTsIsNotNull() {
            addCriterion("d_ts is not null");
            return (Criteria) this;
        }

        public Criteria andDTsEqualTo(Double value) {
            addCriterion("d_ts =", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsNotEqualTo(Double value) {
            addCriterion("d_ts <>", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsGreaterThan(Double value) {
            addCriterion("d_ts >", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsGreaterThanOrEqualTo(Double value) {
            addCriterion("d_ts >=", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsLessThan(Double value) {
            addCriterion("d_ts <", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsLessThanOrEqualTo(Double value) {
            addCriterion("d_ts <=", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsIn(List<Double> values) {
            addCriterion("d_ts in", values, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsNotIn(List<Double> values) {
            addCriterion("d_ts not in", values, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsBetween(Double value1, Double value2) {
            addCriterion("d_ts between", value1, value2, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsNotBetween(Double value1, Double value2) {
            addCriterion("d_ts not between", value1, value2, "dTs");
            return (Criteria) this;
        }

        public Criteria andDRangeIsNull() {
            addCriterion("d_range is null");
            return (Criteria) this;
        }

        public Criteria andDRangeIsNotNull() {
            addCriterion("d_range is not null");
            return (Criteria) this;
        }

        public Criteria andDRangeEqualTo(Double value) {
            addCriterion("d_range =", value, "dRange");
            return (Criteria) this;
        }

        public Criteria andDRangeNotEqualTo(Double value) {
            addCriterion("d_range <>", value, "dRange");
            return (Criteria) this;
        }

        public Criteria andDRangeGreaterThan(Double value) {
            addCriterion("d_range >", value, "dRange");
            return (Criteria) this;
        }

        public Criteria andDRangeGreaterThanOrEqualTo(Double value) {
            addCriterion("d_range >=", value, "dRange");
            return (Criteria) this;
        }

        public Criteria andDRangeLessThan(Double value) {
            addCriterion("d_range <", value, "dRange");
            return (Criteria) this;
        }

        public Criteria andDRangeLessThanOrEqualTo(Double value) {
            addCriterion("d_range <=", value, "dRange");
            return (Criteria) this;
        }

        public Criteria andDRangeIn(List<Double> values) {
            addCriterion("d_range in", values, "dRange");
            return (Criteria) this;
        }

        public Criteria andDRangeNotIn(List<Double> values) {
            addCriterion("d_range not in", values, "dRange");
            return (Criteria) this;
        }

        public Criteria andDRangeBetween(Double value1, Double value2) {
            addCriterion("d_range between", value1, value2, "dRange");
            return (Criteria) this;
        }

        public Criteria andDRangeNotBetween(Double value1, Double value2) {
            addCriterion("d_range not between", value1, value2, "dRange");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeIsNull() {
            addCriterion("n_bio_type_code is null");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeIsNotNull() {
            addCriterion("n_bio_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeEqualTo(Integer value) {
            addCriterion("n_bio_type_code =", value, "nBioTypeCode");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeNotEqualTo(Integer value) {
            addCriterion("n_bio_type_code <>", value, "nBioTypeCode");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeGreaterThan(Integer value) {
            addCriterion("n_bio_type_code >", value, "nBioTypeCode");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("n_bio_type_code >=", value, "nBioTypeCode");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeLessThan(Integer value) {
            addCriterion("n_bio_type_code <", value, "nBioTypeCode");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeLessThanOrEqualTo(Integer value) {
            addCriterion("n_bio_type_code <=", value, "nBioTypeCode");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeIn(List<Integer> values) {
            addCriterion("n_bio_type_code in", values, "nBioTypeCode");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeNotIn(List<Integer> values) {
            addCriterion("n_bio_type_code not in", values, "nBioTypeCode");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeBetween(Integer value1, Integer value2) {
            addCriterion("n_bio_type_code between", value1, value2, "nBioTypeCode");
            return (Criteria) this;
        }

        public Criteria andNBioTypeCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("n_bio_type_code not between", value1, value2, "nBioTypeCode");
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