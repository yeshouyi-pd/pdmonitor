package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TurbidityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TurbidityExample() {
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

        public Criteria andDateTimeIsNull() {
            addCriterion("date_time is null");
            return (Criteria) this;
        }

        public Criteria andDateTimeIsNotNull() {
            addCriterion("date_time is not null");
            return (Criteria) this;
        }

        public Criteria andDateTimeEqualTo(Date value) {
            addCriterion("date_time =", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotEqualTo(Date value) {
            addCriterion("date_time <>", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeGreaterThan(Date value) {
            addCriterion("date_time >", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("date_time >=", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeGreaterThanOrEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(date_time,'"+type+"') >=", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeLessThan(Date value) {
            addCriterion("date_time <", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("date_time <=", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeLessThanOrEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(date_time,'"+type+"') <=", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeIn(List<Date> values) {
            addCriterion("date_time in", values, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotIn(List<Date> values) {
            addCriterion("date_time not in", values, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeBetween(Date value1, Date value2) {
            addCriterion("date_time between", value1, value2, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("date_time not between", value1, value2, "dateTime");
            return (Criteria) this;
        }

        public Criteria andTurbidityHIsNull() {
            addCriterion("turbidity_h is null");
            return (Criteria) this;
        }

        public Criteria andTurbidityHIsNotNull() {
            addCriterion("turbidity_h is not null");
            return (Criteria) this;
        }

        public Criteria andTurbidityHEqualTo(String value) {
            addCriterion("turbidity_h =", value, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHNotEqualTo(String value) {
            addCriterion("turbidity_h <>", value, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHGreaterThan(String value) {
            addCriterion("turbidity_h >", value, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHGreaterThanOrEqualTo(String value) {
            addCriterion("turbidity_h >=", value, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHLessThan(String value) {
            addCriterion("turbidity_h <", value, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHLessThanOrEqualTo(String value) {
            addCriterion("turbidity_h <=", value, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHLike(String value) {
            addCriterion("turbidity_h like", value, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHNotLike(String value) {
            addCriterion("turbidity_h not like", value, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHIn(List<String> values) {
            addCriterion("turbidity_h in", values, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHNotIn(List<String> values) {
            addCriterion("turbidity_h not in", values, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHBetween(String value1, String value2) {
            addCriterion("turbidity_h between", value1, value2, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTurbidityHNotBetween(String value1, String value2) {
            addCriterion("turbidity_h not between", value1, value2, "turbidityH");
            return (Criteria) this;
        }

        public Criteria andTuribidityLIsNull() {
            addCriterion("turibidity_l is null");
            return (Criteria) this;
        }

        public Criteria andTuribidityLIsNotNull() {
            addCriterion("turibidity_l is not null");
            return (Criteria) this;
        }

        public Criteria andTuribidityLEqualTo(String value) {
            addCriterion("turibidity_l =", value, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLNotEqualTo(String value) {
            addCriterion("turibidity_l <>", value, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLGreaterThan(String value) {
            addCriterion("turibidity_l >", value, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLGreaterThanOrEqualTo(String value) {
            addCriterion("turibidity_l >=", value, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLLessThan(String value) {
            addCriterion("turibidity_l <", value, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLLessThanOrEqualTo(String value) {
            addCriterion("turibidity_l <=", value, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLLike(String value) {
            addCriterion("turibidity_l like", value, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLNotLike(String value) {
            addCriterion("turibidity_l not like", value, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLIn(List<String> values) {
            addCriterion("turibidity_l in", values, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLNotIn(List<String> values) {
            addCriterion("turibidity_l not in", values, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLBetween(String value1, String value2) {
            addCriterion("turibidity_l between", value1, value2, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andTuribidityLNotBetween(String value1, String value2) {
            addCriterion("turibidity_l not between", value1, value2, "turibidityL");
            return (Criteria) this;
        }

        public Criteria andDepthIsNull() {
            addCriterion("`depth` is null");
            return (Criteria) this;
        }

        public Criteria andDepthIsNotNull() {
            addCriterion("`depth` is not null");
            return (Criteria) this;
        }

        public Criteria andDepthEqualTo(String value) {
            addCriterion("`depth` =", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotEqualTo(String value) {
            addCriterion("`depth` <>", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThan(String value) {
            addCriterion("`depth` >", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThanOrEqualTo(String value) {
            addCriterion("`depth` >=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThan(String value) {
            addCriterion("`depth` <", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThanOrEqualTo(String value) {
            addCriterion("`depth` <=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLike(String value) {
            addCriterion("`depth` like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotLike(String value) {
            addCriterion("`depth` not like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthIn(List<String> values) {
            addCriterion("`depth` in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotIn(List<String> values) {
            addCriterion("`depth` not in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthBetween(String value1, String value2) {
            addCriterion("`depth` between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotBetween(String value1, String value2) {
            addCriterion("`depth` not between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNull() {
            addCriterion("temperature is null");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNotNull() {
            addCriterion("temperature is not null");
            return (Criteria) this;
        }

        public Criteria andTemperatureEqualTo(String value) {
            addCriterion("temperature =", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotEqualTo(String value) {
            addCriterion("temperature <>", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThan(String value) {
            addCriterion("temperature >", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("temperature >=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThan(String value) {
            addCriterion("temperature <", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThanOrEqualTo(String value) {
            addCriterion("temperature <=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLike(String value) {
            addCriterion("temperature like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotLike(String value) {
            addCriterion("temperature not like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureIn(List<String> values) {
            addCriterion("temperature in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotIn(List<String> values) {
            addCriterion("temperature not in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureBetween(String value1, String value2) {
            addCriterion("temperature between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotBetween(String value1, String value2) {
            addCriterion("temperature not between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andConductivityIsNull() {
            addCriterion("conductivity is null");
            return (Criteria) this;
        }

        public Criteria andConductivityIsNotNull() {
            addCriterion("conductivity is not null");
            return (Criteria) this;
        }

        public Criteria andConductivityEqualTo(String value) {
            addCriterion("conductivity =", value, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityNotEqualTo(String value) {
            addCriterion("conductivity <>", value, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityGreaterThan(String value) {
            addCriterion("conductivity >", value, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityGreaterThanOrEqualTo(String value) {
            addCriterion("conductivity >=", value, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityLessThan(String value) {
            addCriterion("conductivity <", value, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityLessThanOrEqualTo(String value) {
            addCriterion("conductivity <=", value, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityLike(String value) {
            addCriterion("conductivity like", value, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityNotLike(String value) {
            addCriterion("conductivity not like", value, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityIn(List<String> values) {
            addCriterion("conductivity in", values, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityNotIn(List<String> values) {
            addCriterion("conductivity not in", values, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityBetween(String value1, String value2) {
            addCriterion("conductivity between", value1, value2, "conductivity");
            return (Criteria) this;
        }

        public Criteria andConductivityNotBetween(String value1, String value2) {
            addCriterion("conductivity not between", value1, value2, "conductivity");
            return (Criteria) this;
        }

        public Criteria andSalinityIsNull() {
            addCriterion("salinity is null");
            return (Criteria) this;
        }

        public Criteria andSalinityIsNotNull() {
            addCriterion("salinity is not null");
            return (Criteria) this;
        }

        public Criteria andSalinityEqualTo(String value) {
            addCriterion("salinity =", value, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityNotEqualTo(String value) {
            addCriterion("salinity <>", value, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityGreaterThan(String value) {
            addCriterion("salinity >", value, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityGreaterThanOrEqualTo(String value) {
            addCriterion("salinity >=", value, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityLessThan(String value) {
            addCriterion("salinity <", value, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityLessThanOrEqualTo(String value) {
            addCriterion("salinity <=", value, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityLike(String value) {
            addCriterion("salinity like", value, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityNotLike(String value) {
            addCriterion("salinity not like", value, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityIn(List<String> values) {
            addCriterion("salinity in", values, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityNotIn(List<String> values) {
            addCriterion("salinity not in", values, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityBetween(String value1, String value2) {
            addCriterion("salinity between", value1, value2, "salinity");
            return (Criteria) this;
        }

        public Criteria andSalinityNotBetween(String value1, String value2) {
            addCriterion("salinity not between", value1, value2, "salinity");
            return (Criteria) this;
        }

        public Criteria andBatVoltIsNull() {
            addCriterion("bat_volt is null");
            return (Criteria) this;
        }

        public Criteria andBatVoltIsNotNull() {
            addCriterion("bat_volt is not null");
            return (Criteria) this;
        }

        public Criteria andBatVoltEqualTo(String value) {
            addCriterion("bat_volt =", value, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltNotEqualTo(String value) {
            addCriterion("bat_volt <>", value, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltGreaterThan(String value) {
            addCriterion("bat_volt >", value, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltGreaterThanOrEqualTo(String value) {
            addCriterion("bat_volt >=", value, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltLessThan(String value) {
            addCriterion("bat_volt <", value, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltLessThanOrEqualTo(String value) {
            addCriterion("bat_volt <=", value, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltLike(String value) {
            addCriterion("bat_volt like", value, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltNotLike(String value) {
            addCriterion("bat_volt not like", value, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltIn(List<String> values) {
            addCriterion("bat_volt in", values, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltNotIn(List<String> values) {
            addCriterion("bat_volt not in", values, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltBetween(String value1, String value2) {
            addCriterion("bat_volt between", value1, value2, "batVolt");
            return (Criteria) this;
        }

        public Criteria andBatVoltNotBetween(String value1, String value2) {
            addCriterion("bat_volt not between", value1, value2, "batVolt");
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