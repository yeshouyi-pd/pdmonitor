package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class SonarParameterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SonarParameterExample() {
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

        public Criteria andDeviceCodeIsNull() {
            addCriterion("device_code is null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIsNotNull() {
            addCriterion("device_code is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeEqualTo(String value) {
            addCriterion("device_code =", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotEqualTo(String value) {
            addCriterion("device_code <>", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThan(String value) {
            addCriterion("device_code >", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("device_code >=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThan(String value) {
            addCriterion("device_code <", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThanOrEqualTo(String value) {
            addCriterion("device_code <=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLike(String value) {
            addCriterion("device_code like", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotLike(String value) {
            addCriterion("device_code not like", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIn(List<String> values) {
            addCriterion("device_code in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotIn(List<String> values) {
            addCriterion("device_code not in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeBetween(String value1, String value2) {
            addCriterion("device_code between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotBetween(String value1, String value2) {
            addCriterion("device_code not between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andAvrMaxIsNull() {
            addCriterion("avr_max is null");
            return (Criteria) this;
        }

        public Criteria andAvrMaxIsNotNull() {
            addCriterion("avr_max is not null");
            return (Criteria) this;
        }

        public Criteria andAvrMaxEqualTo(Double value) {
            addCriterion("avr_max =", value, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMaxNotEqualTo(Double value) {
            addCriterion("avr_max <>", value, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMaxGreaterThan(Double value) {
            addCriterion("avr_max >", value, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMaxGreaterThanOrEqualTo(Double value) {
            addCriterion("avr_max >=", value, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMaxLessThan(Double value) {
            addCriterion("avr_max <", value, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMaxLessThanOrEqualTo(Double value) {
            addCriterion("avr_max <=", value, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMaxIn(List<Double> values) {
            addCriterion("avr_max in", values, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMaxNotIn(List<Double> values) {
            addCriterion("avr_max not in", values, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMaxBetween(Double value1, Double value2) {
            addCriterion("avr_max between", value1, value2, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMaxNotBetween(Double value1, Double value2) {
            addCriterion("avr_max not between", value1, value2, "avrMax");
            return (Criteria) this;
        }

        public Criteria andAvrMinIsNull() {
            addCriterion("avr_min is null");
            return (Criteria) this;
        }

        public Criteria andAvrMinIsNotNull() {
            addCriterion("avr_min is not null");
            return (Criteria) this;
        }

        public Criteria andAvrMinEqualTo(Double value) {
            addCriterion("avr_min =", value, "avrMin");
            return (Criteria) this;
        }

        public Criteria andAvrMinNotEqualTo(Double value) {
            addCriterion("avr_min <>", value, "avrMin");
            return (Criteria) this;
        }

        public Criteria andAvrMinGreaterThan(Double value) {
            addCriterion("avr_min >", value, "avrMin");
            return (Criteria) this;
        }

        public Criteria andAvrMinGreaterThanOrEqualTo(Double value) {
            addCriterion("avr_min >=", value, "avrMin");
            return (Criteria) this;
        }

        public Criteria andAvrMinLessThan(Double value) {
            addCriterion("avr_min <", value, "avrMin");
            return (Criteria) this;
        }

        public Criteria andAvrMinLessThanOrEqualTo(Double value) {
            addCriterion("avr_min <=", value, "avrMin");
            return (Criteria) this;
        }

        public Criteria andAvrMinIn(List<Double> values) {
            addCriterion("avr_min in", values, "avrMin");
            return (Criteria) this;
        }

        public Criteria andAvrMinNotIn(List<Double> values) {
            addCriterion("avr_min not in", values, "avrMin");
            return (Criteria) this;
        }

        public Criteria andAvrMinBetween(Double value1, Double value2) {
            addCriterion("avr_min between", value1, value2, "avrMin");
            return (Criteria) this;
        }

        public Criteria andAvrMinNotBetween(Double value1, Double value2) {
            addCriterion("avr_min not between", value1, value2, "avrMin");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaIsNull() {
            addCriterion("cross_section_area is null");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaIsNotNull() {
            addCriterion("cross_section_area is not null");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaEqualTo(Double value) {
            addCriterion("cross_section_area =", value, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaNotEqualTo(Double value) {
            addCriterion("cross_section_area <>", value, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaGreaterThan(Double value) {
            addCriterion("cross_section_area >", value, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaGreaterThanOrEqualTo(Double value) {
            addCriterion("cross_section_area >=", value, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaLessThan(Double value) {
            addCriterion("cross_section_area <", value, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaLessThanOrEqualTo(Double value) {
            addCriterion("cross_section_area <=", value, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaIn(List<Double> values) {
            addCriterion("cross_section_area in", values, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaNotIn(List<Double> values) {
            addCriterion("cross_section_area not in", values, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaBetween(Double value1, Double value2) {
            addCriterion("cross_section_area between", value1, value2, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andCrossSectionAreaNotBetween(Double value1, Double value2) {
            addCriterion("cross_section_area not between", value1, value2, "crossSectionArea");
            return (Criteria) this;
        }

        public Criteria andMapParaValueIsNull() {
            addCriterion("map_para_value is null");
            return (Criteria) this;
        }

        public Criteria andMapParaValueIsNotNull() {
            addCriterion("map_para_value is not null");
            return (Criteria) this;
        }

        public Criteria andMapParaValueEqualTo(String value) {
            addCriterion("map_para_value =", value, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueNotEqualTo(String value) {
            addCriterion("map_para_value <>", value, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueGreaterThan(String value) {
            addCriterion("map_para_value >", value, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueGreaterThanOrEqualTo(String value) {
            addCriterion("map_para_value >=", value, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueLessThan(String value) {
            addCriterion("map_para_value <", value, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueLessThanOrEqualTo(String value) {
            addCriterion("map_para_value <=", value, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueLike(String value) {
            addCriterion("map_para_value like", value, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueNotLike(String value) {
            addCriterion("map_para_value not like", value, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueIn(List<String> values) {
            addCriterion("map_para_value in", values, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueNotIn(List<String> values) {
            addCriterion("map_para_value not in", values, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueBetween(String value1, String value2) {
            addCriterion("map_para_value between", value1, value2, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andMapParaValueNotBetween(String value1, String value2) {
            addCriterion("map_para_value not between", value1, value2, "mapParaValue");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeIsNull() {
            addCriterion("weight_or_flux_acc_time is null");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeIsNotNull() {
            addCriterion("weight_or_flux_acc_time is not null");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeEqualTo(Integer value) {
            addCriterion("weight_or_flux_acc_time =", value, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeNotEqualTo(Integer value) {
            addCriterion("weight_or_flux_acc_time <>", value, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeGreaterThan(Integer value) {
            addCriterion("weight_or_flux_acc_time >", value, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight_or_flux_acc_time >=", value, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeLessThan(Integer value) {
            addCriterion("weight_or_flux_acc_time <", value, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeLessThanOrEqualTo(Integer value) {
            addCriterion("weight_or_flux_acc_time <=", value, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeIn(List<Integer> values) {
            addCriterion("weight_or_flux_acc_time in", values, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeNotIn(List<Integer> values) {
            addCriterion("weight_or_flux_acc_time not in", values, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeBetween(Integer value1, Integer value2) {
            addCriterion("weight_or_flux_acc_time between", value1, value2, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andWeightOrFluxAccTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("weight_or_flux_acc_time not between", value1, value2, "weightOrFluxAccTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeIsNull() {
            addCriterion("change_time is null");
            return (Criteria) this;
        }

        public Criteria andChangeTimeIsNotNull() {
            addCriterion("change_time is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTimeEqualTo(String value) {
            addCriterion("change_time =", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotEqualTo(String value) {
            addCriterion("change_time <>", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeGreaterThan(String value) {
            addCriterion("change_time >", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("change_time >=", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeLessThan(String value) {
            addCriterion("change_time <", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeLessThanOrEqualTo(String value) {
            addCriterion("change_time <=", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeLike(String value) {
            addCriterion("change_time like", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotLike(String value) {
            addCriterion("change_time not like", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeIn(List<String> values) {
            addCriterion("change_time in", values, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotIn(List<String> values) {
            addCriterion("change_time not in", values, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeBetween(String value1, String value2) {
            addCriterion("change_time between", value1, value2, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotBetween(String value1, String value2) {
            addCriterion("change_time not between", value1, value2, "changeTime");
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