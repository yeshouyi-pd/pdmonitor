package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeteorologicalDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MeteorologicalDataExample() {
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

        public Criteria andSpeedIsNull() {
            addCriterion("speed is null");
            return (Criteria) this;
        }

        public Criteria andSpeedIsNotNull() {
            addCriterion("speed is not null");
            return (Criteria) this;
        }

        public Criteria andSpeedEqualTo(Double value) {
            addCriterion("speed =", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotEqualTo(Double value) {
            addCriterion("speed <>", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedGreaterThan(Double value) {
            addCriterion("speed >", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedGreaterThanOrEqualTo(Double value) {
            addCriterion("speed >=", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedLessThan(Double value) {
            addCriterion("speed <", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedLessThanOrEqualTo(Double value) {
            addCriterion("speed <=", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedIn(List<Double> values) {
            addCriterion("speed in", values, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotIn(List<Double> values) {
            addCriterion("speed not in", values, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedBetween(Double value1, Double value2) {
            addCriterion("speed between", value1, value2, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotBetween(Double value1, Double value2) {
            addCriterion("speed not between", value1, value2, "speed");
            return (Criteria) this;
        }

        public Criteria andWinddirectionIsNull() {
            addCriterion("winddirection is null");
            return (Criteria) this;
        }

        public Criteria andWinddirectionIsNotNull() {
            addCriterion("winddirection is not null");
            return (Criteria) this;
        }

        public Criteria andWinddirectionEqualTo(String value) {
            addCriterion("winddirection =", value, "winddirection");
            return (Criteria) this;
        }

        public Criteria andWinddirectionNotEqualTo(String value) {
            addCriterion("winddirection <>", value, "winddirection");
            return (Criteria) this;
        }

        public Criteria andWinddirectionGreaterThan(String value) {
            addCriterion("winddirection >", value, "winddirection");
            return (Criteria) this;
        }

        public Criteria andWinddirectionGreaterThanOrEqualTo(String value) {
            addCriterion("winddirection >=", value, "winddirection");
            return (Criteria) this;
        }

        public Criteria andWinddirectionLessThan(String value) {
            addCriterion("winddirection <", value, "winddirection");
            return (Criteria) this;
        }

        public Criteria andWinddirectionLessThanOrEqualTo(String value) {
            addCriterion("winddirection <=", value, "winddirection");
            return (Criteria) this;
        }

        public Criteria andWinddirectionIn(List<String> values) {
            addCriterion("winddirection in", values, "winddirection");
            return (Criteria) this;
        }

        public Criteria andWinddirectionNotIn(List<String> values) {
            addCriterion("winddirection not in", values, "winddirection");
            return (Criteria) this;
        }

        public Criteria andWinddirectionBetween(String value1, String value2) {
            addCriterion("winddirection between", value1, value2, "winddirection");
            return (Criteria) this;
        }

        public Criteria andWinddirectionNotBetween(String value1, String value2) {
            addCriterion("winddirection not between", value1, value2, "winddirection");
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

        public Criteria andTemperatureEqualTo(Double value) {
            addCriterion("temperature =", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotEqualTo(Double value) {
            addCriterion("temperature <>", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThan(Double value) {
            addCriterion("temperature >", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThanOrEqualTo(Double value) {
            addCriterion("temperature >=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThan(Double value) {
            addCriterion("temperature <", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThanOrEqualTo(Double value) {
            addCriterion("temperature <=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureIn(List<Double> values) {
            addCriterion("temperature in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotIn(List<Double> values) {
            addCriterion("temperature not in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureBetween(Double value1, Double value2) {
            addCriterion("temperature between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotBetween(Double value1, Double value2) {
            addCriterion("temperature not between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andHumidityIsNull() {
            addCriterion("humidity is null");
            return (Criteria) this;
        }

        public Criteria andHumidityIsNotNull() {
            addCriterion("humidity is not null");
            return (Criteria) this;
        }

        public Criteria andHumidityEqualTo(Double value) {
            addCriterion("humidity =", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotEqualTo(Double value) {
            addCriterion("humidity <>", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityGreaterThan(Double value) {
            addCriterion("humidity >", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityGreaterThanOrEqualTo(Double value) {
            addCriterion("humidity >=", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLessThan(Double value) {
            addCriterion("humidity <", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLessThanOrEqualTo(Double value) {
            addCriterion("humidity <=", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityIn(List<Double> values) {
            addCriterion("humidity in", values, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotIn(List<Double> values) {
            addCriterion("humidity not in", values, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityBetween(Double value1, Double value2) {
            addCriterion("humidity between", value1, value2, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotBetween(Double value1, Double value2) {
            addCriterion("humidity not between", value1, value2, "humidity");
            return (Criteria) this;
        }

        public Criteria andPressureIsNull() {
            addCriterion("pressure is null");
            return (Criteria) this;
        }

        public Criteria andPressureIsNotNull() {
            addCriterion("pressure is not null");
            return (Criteria) this;
        }

        public Criteria andPressureEqualTo(Double value) {
            addCriterion("pressure =", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureNotEqualTo(Double value) {
            addCriterion("pressure <>", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureGreaterThan(Double value) {
            addCriterion("pressure >", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureGreaterThanOrEqualTo(Double value) {
            addCriterion("pressure >=", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureLessThan(Double value) {
            addCriterion("pressure <", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureLessThanOrEqualTo(Double value) {
            addCriterion("pressure <=", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureIn(List<Double> values) {
            addCriterion("pressure in", values, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureNotIn(List<Double> values) {
            addCriterion("pressure not in", values, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureBetween(Double value1, Double value2) {
            addCriterion("pressure between", value1, value2, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureNotBetween(Double value1, Double value2) {
            addCriterion("pressure not between", value1, value2, "pressure");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallIsNull() {
            addCriterion("minuterainfall is null");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallIsNotNull() {
            addCriterion("minuterainfall is not null");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallEqualTo(Double value) {
            addCriterion("minuterainfall =", value, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallNotEqualTo(Double value) {
            addCriterion("minuterainfall <>", value, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallGreaterThan(Double value) {
            addCriterion("minuterainfall >", value, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallGreaterThanOrEqualTo(Double value) {
            addCriterion("minuterainfall >=", value, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallLessThan(Double value) {
            addCriterion("minuterainfall <", value, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallLessThanOrEqualTo(Double value) {
            addCriterion("minuterainfall <=", value, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallIn(List<Double> values) {
            addCriterion("minuterainfall in", values, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallNotIn(List<Double> values) {
            addCriterion("minuterainfall not in", values, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallBetween(Double value1, Double value2) {
            addCriterion("minuterainfall between", value1, value2, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andMinuterainfallNotBetween(Double value1, Double value2) {
            addCriterion("minuterainfall not between", value1, value2, "minuterainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallIsNull() {
            addCriterion("hourrainfall is null");
            return (Criteria) this;
        }

        public Criteria andHourrainfallIsNotNull() {
            addCriterion("hourrainfall is not null");
            return (Criteria) this;
        }

        public Criteria andHourrainfallEqualTo(Double value) {
            addCriterion("hourrainfall =", value, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallNotEqualTo(Double value) {
            addCriterion("hourrainfall <>", value, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallGreaterThan(Double value) {
            addCriterion("hourrainfall >", value, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallGreaterThanOrEqualTo(Double value) {
            addCriterion("hourrainfall >=", value, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallLessThan(Double value) {
            addCriterion("hourrainfall <", value, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallLessThanOrEqualTo(Double value) {
            addCriterion("hourrainfall <=", value, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallIn(List<Double> values) {
            addCriterion("hourrainfall in", values, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallNotIn(List<Double> values) {
            addCriterion("hourrainfall not in", values, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallBetween(Double value1, Double value2) {
            addCriterion("hourrainfall between", value1, value2, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andHourrainfallNotBetween(Double value1, Double value2) {
            addCriterion("hourrainfall not between", value1, value2, "hourrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallIsNull() {
            addCriterion("dayrainfall is null");
            return (Criteria) this;
        }

        public Criteria andDayrainfallIsNotNull() {
            addCriterion("dayrainfall is not null");
            return (Criteria) this;
        }

        public Criteria andDayrainfallEqualTo(Double value) {
            addCriterion("dayrainfall =", value, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallNotEqualTo(Double value) {
            addCriterion("dayrainfall <>", value, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallGreaterThan(Double value) {
            addCriterion("dayrainfall >", value, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallGreaterThanOrEqualTo(Double value) {
            addCriterion("dayrainfall >=", value, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallLessThan(Double value) {
            addCriterion("dayrainfall <", value, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallLessThanOrEqualTo(Double value) {
            addCriterion("dayrainfall <=", value, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallIn(List<Double> values) {
            addCriterion("dayrainfall in", values, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallNotIn(List<Double> values) {
            addCriterion("dayrainfall not in", values, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallBetween(Double value1, Double value2) {
            addCriterion("dayrainfall between", value1, value2, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andDayrainfallNotBetween(Double value1, Double value2) {
            addCriterion("dayrainfall not between", value1, value2, "dayrainfall");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationIsNull() {
            addCriterion("rainfallaccumulation is null");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationIsNotNull() {
            addCriterion("rainfallaccumulation is not null");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationEqualTo(Double value) {
            addCriterion("rainfallaccumulation =", value, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationNotEqualTo(Double value) {
            addCriterion("rainfallaccumulation <>", value, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationGreaterThan(Double value) {
            addCriterion("rainfallaccumulation >", value, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationGreaterThanOrEqualTo(Double value) {
            addCriterion("rainfallaccumulation >=", value, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationLessThan(Double value) {
            addCriterion("rainfallaccumulation <", value, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationLessThanOrEqualTo(Double value) {
            addCriterion("rainfallaccumulation <=", value, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationIn(List<Double> values) {
            addCriterion("rainfallaccumulation in", values, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationNotIn(List<Double> values) {
            addCriterion("rainfallaccumulation not in", values, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationBetween(Double value1, Double value2) {
            addCriterion("rainfallaccumulation between", value1, value2, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andRainfallaccumulationNotBetween(Double value1, Double value2) {
            addCriterion("rainfallaccumulation not between", value1, value2, "rainfallaccumulation");
            return (Criteria) this;
        }

        public Criteria andSolarintensityIsNull() {
            addCriterion("solarintensity is null");
            return (Criteria) this;
        }

        public Criteria andSolarintensityIsNotNull() {
            addCriterion("solarintensity is not null");
            return (Criteria) this;
        }

        public Criteria andSolarintensityEqualTo(Double value) {
            addCriterion("solarintensity =", value, "solarintensity");
            return (Criteria) this;
        }

        public Criteria andSolarintensityNotEqualTo(Double value) {
            addCriterion("solarintensity <>", value, "solarintensity");
            return (Criteria) this;
        }

        public Criteria andSolarintensityGreaterThan(Double value) {
            addCriterion("solarintensity >", value, "solarintensity");
            return (Criteria) this;
        }

        public Criteria andSolarintensityGreaterThanOrEqualTo(Double value) {
            addCriterion("solarintensity >=", value, "solarintensity");
            return (Criteria) this;
        }

        public Criteria andSolarintensityLessThan(Double value) {
            addCriterion("solarintensity <", value, "solarintensity");
            return (Criteria) this;
        }

        public Criteria andSolarintensityLessThanOrEqualTo(Double value) {
            addCriterion("solarintensity <=", value, "solarintensity");
            return (Criteria) this;
        }

        public Criteria andSolarintensityIn(List<Double> values) {
            addCriterion("solarintensity in", values, "solarintensity");
            return (Criteria) this;
        }

        public Criteria andSolarintensityNotIn(List<Double> values) {
            addCriterion("solarintensity not in", values, "solarintensity");
            return (Criteria) this;
        }

        public Criteria andSolarintensityBetween(Double value1, Double value2) {
            addCriterion("solarintensity between", value1, value2, "solarintensity");
            return (Criteria) this;
        }

        public Criteria andSolarintensityNotBetween(Double value1, Double value2) {
            addCriterion("solarintensity not between", value1, value2, "solarintensity");
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