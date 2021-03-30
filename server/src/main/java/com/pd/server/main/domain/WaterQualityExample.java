package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterQualityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WaterQualityExample() {
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

        public Criteria andSbsnIsNull() {
            addCriterion("sbsn is null");
            return (Criteria) this;
        }

        public Criteria andSbsnIsNotNull() {
            addCriterion("sbsn is not null");
            return (Criteria) this;
        }

        public Criteria andSbsnEqualTo(String value) {
            addCriterion("sbsn =", value, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnNotEqualTo(String value) {
            addCriterion("sbsn <>", value, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnGreaterThan(String value) {
            addCriterion("sbsn >", value, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnGreaterThanOrEqualTo(String value) {
            addCriterion("sbsn >=", value, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnLessThan(String value) {
            addCriterion("sbsn <", value, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnLessThanOrEqualTo(String value) {
            addCriterion("sbsn <=", value, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnLike(String value) {
            addCriterion("sbsn like", value, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnNotLike(String value) {
            addCriterion("sbsn not like", value, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnIn(List<String> values) {
            addCriterion("sbsn in", values, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnNotIn(List<String> values) {
            addCriterion("sbsn not in", values, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnBetween(String value1, String value2) {
            addCriterion("sbsn between", value1, value2, "sbsn");
            return (Criteria) this;
        }

        public Criteria andSbsnNotBetween(String value1, String value2) {
            addCriterion("sbsn not between", value1, value2, "sbsn");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andDatacenterIsNull() {
            addCriterion("datacenter is null");
            return (Criteria) this;
        }

        public Criteria andDatacenterIsNotNull() {
            addCriterion("datacenter is not null");
            return (Criteria) this;
        }

        public Criteria andDatacenterEqualTo(String value) {
            addCriterion("datacenter =", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterNotEqualTo(String value) {
            addCriterion("datacenter <>", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterGreaterThan(String value) {
            addCriterion("datacenter >", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterGreaterThanOrEqualTo(String value) {
            addCriterion("datacenter >=", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterLessThan(String value) {
            addCriterion("datacenter <", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterLessThanOrEqualTo(String value) {
            addCriterion("datacenter <=", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterLike(String value) {
            addCriterion("datacenter like", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterNotLike(String value) {
            addCriterion("datacenter not like", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterIn(List<String> values) {
            addCriterion("datacenter in", values, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterNotIn(List<String> values) {
            addCriterion("datacenter not in", values, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterBetween(String value1, String value2) {
            addCriterion("datacenter between", value1, value2, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterNotBetween(String value1, String value2) {
            addCriterion("datacenter not between", value1, value2, "datacenter");
            return (Criteria) this;
        }

        public Criteria andWaterDepthIsNull() {
            addCriterion("water_depth is null");
            return (Criteria) this;
        }

        public Criteria andWaterDepthIsNotNull() {
            addCriterion("water_depth is not null");
            return (Criteria) this;
        }

        public Criteria andWaterDepthEqualTo(Float value) {
            addCriterion("water_depth =", value, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterDepthNotEqualTo(Float value) {
            addCriterion("water_depth <>", value, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterDepthGreaterThan(Float value) {
            addCriterion("water_depth >", value, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterDepthGreaterThanOrEqualTo(Float value) {
            addCriterion("water_depth >=", value, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterDepthLessThan(Float value) {
            addCriterion("water_depth <", value, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterDepthLessThanOrEqualTo(Float value) {
            addCriterion("water_depth <=", value, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterDepthIn(List<Float> values) {
            addCriterion("water_depth in", values, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterDepthNotIn(List<Float> values) {
            addCriterion("water_depth not in", values, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterDepthBetween(Float value1, Float value2) {
            addCriterion("water_depth between", value1, value2, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterDepthNotBetween(Float value1, Float value2) {
            addCriterion("water_depth not between", value1, value2, "waterDepth");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityIsNull() {
            addCriterion("water_velocity is null");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityIsNotNull() {
            addCriterion("water_velocity is not null");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityEqualTo(Float value) {
            addCriterion("water_velocity =", value, "waterVelocity");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityNotEqualTo(Float value) {
            addCriterion("water_velocity <>", value, "waterVelocity");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityGreaterThan(Float value) {
            addCriterion("water_velocity >", value, "waterVelocity");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityGreaterThanOrEqualTo(Float value) {
            addCriterion("water_velocity >=", value, "waterVelocity");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityLessThan(Float value) {
            addCriterion("water_velocity <", value, "waterVelocity");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityLessThanOrEqualTo(Float value) {
            addCriterion("water_velocity <=", value, "waterVelocity");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityIn(List<Float> values) {
            addCriterion("water_velocity in", values, "waterVelocity");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityNotIn(List<Float> values) {
            addCriterion("water_velocity not in", values, "waterVelocity");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityBetween(Float value1, Float value2) {
            addCriterion("water_velocity between", value1, value2, "waterVelocity");
            return (Criteria) this;
        }

        public Criteria andWaterVelocityNotBetween(Float value1, Float value2) {
            addCriterion("water_velocity not between", value1, value2, "waterVelocity");
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

        public Criteria andOxidativeIsNull() {
            addCriterion("oxidative is null");
            return (Criteria) this;
        }

        public Criteria andOxidativeIsNotNull() {
            addCriterion("oxidative is not null");
            return (Criteria) this;
        }

        public Criteria andOxidativeEqualTo(String value) {
            addCriterion("oxidative =", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeNotEqualTo(String value) {
            addCriterion("oxidative <>", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeGreaterThan(String value) {
            addCriterion("oxidative >", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeGreaterThanOrEqualTo(String value) {
            addCriterion("oxidative >=", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeLessThan(String value) {
            addCriterion("oxidative <", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeLessThanOrEqualTo(String value) {
            addCriterion("oxidative <=", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeLike(String value) {
            addCriterion("oxidative like", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeNotLike(String value) {
            addCriterion("oxidative not like", value, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeIn(List<String> values) {
            addCriterion("oxidative in", values, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeNotIn(List<String> values) {
            addCriterion("oxidative not in", values, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeBetween(String value1, String value2) {
            addCriterion("oxidative between", value1, value2, "oxidative");
            return (Criteria) this;
        }

        public Criteria andOxidativeNotBetween(String value1, String value2) {
            addCriterion("oxidative not between", value1, value2, "oxidative");
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

        public Criteria andCodIsNull() {
            addCriterion("cod is null");
            return (Criteria) this;
        }

        public Criteria andCodIsNotNull() {
            addCriterion("cod is not null");
            return (Criteria) this;
        }

        public Criteria andCodEqualTo(String value) {
            addCriterion("cod =", value, "cod");
            return (Criteria) this;
        }

        public Criteria andCodNotEqualTo(String value) {
            addCriterion("cod <>", value, "cod");
            return (Criteria) this;
        }

        public Criteria andCodGreaterThan(String value) {
            addCriterion("cod >", value, "cod");
            return (Criteria) this;
        }

        public Criteria andCodGreaterThanOrEqualTo(String value) {
            addCriterion("cod >=", value, "cod");
            return (Criteria) this;
        }

        public Criteria andCodLessThan(String value) {
            addCriterion("cod <", value, "cod");
            return (Criteria) this;
        }

        public Criteria andCodLessThanOrEqualTo(String value) {
            addCriterion("cod <=", value, "cod");
            return (Criteria) this;
        }

        public Criteria andCodLike(String value) {
            addCriterion("cod like", value, "cod");
            return (Criteria) this;
        }

        public Criteria andCodNotLike(String value) {
            addCriterion("cod not like", value, "cod");
            return (Criteria) this;
        }

        public Criteria andCodIn(List<String> values) {
            addCriterion("cod in", values, "cod");
            return (Criteria) this;
        }

        public Criteria andCodNotIn(List<String> values) {
            addCriterion("cod not in", values, "cod");
            return (Criteria) this;
        }

        public Criteria andCodBetween(String value1, String value2) {
            addCriterion("cod between", value1, value2, "cod");
            return (Criteria) this;
        }

        public Criteria andCodNotBetween(String value1, String value2) {
            addCriterion("cod not between", value1, value2, "cod");
            return (Criteria) this;
        }

        public Criteria andTurbidityIsNull() {
            addCriterion("turbidity is null");
            return (Criteria) this;
        }

        public Criteria andTurbidityIsNotNull() {
            addCriterion("turbidity is not null");
            return (Criteria) this;
        }

        public Criteria andTurbidityEqualTo(String value) {
            addCriterion("turbidity =", value, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityNotEqualTo(String value) {
            addCriterion("turbidity <>", value, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityGreaterThan(String value) {
            addCriterion("turbidity >", value, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityGreaterThanOrEqualTo(String value) {
            addCriterion("turbidity >=", value, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityLessThan(String value) {
            addCriterion("turbidity <", value, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityLessThanOrEqualTo(String value) {
            addCriterion("turbidity <=", value, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityLike(String value) {
            addCriterion("turbidity like", value, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityNotLike(String value) {
            addCriterion("turbidity not like", value, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityIn(List<String> values) {
            addCriterion("turbidity in", values, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityNotIn(List<String> values) {
            addCriterion("turbidity not in", values, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityBetween(String value1, String value2) {
            addCriterion("turbidity between", value1, value2, "turbidity");
            return (Criteria) this;
        }

        public Criteria andTurbidityNotBetween(String value1, String value2) {
            addCriterion("turbidity not between", value1, value2, "turbidity");
            return (Criteria) this;
        }

        public Criteria andPhIsNull() {
            addCriterion("ph is null");
            return (Criteria) this;
        }

        public Criteria andPhIsNotNull() {
            addCriterion("ph is not null");
            return (Criteria) this;
        }

        public Criteria andPhEqualTo(String value) {
            addCriterion("ph =", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotEqualTo(String value) {
            addCriterion("ph <>", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhGreaterThan(String value) {
            addCriterion("ph >", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhGreaterThanOrEqualTo(String value) {
            addCriterion("ph >=", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhLessThan(String value) {
            addCriterion("ph <", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhLessThanOrEqualTo(String value) {
            addCriterion("ph <=", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhLike(String value) {
            addCriterion("ph like", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotLike(String value) {
            addCriterion("ph not like", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhIn(List<String> values) {
            addCriterion("ph in", values, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotIn(List<String> values) {
            addCriterion("ph not in", values, "ph");
            return (Criteria) this;
        }

        public Criteria andPhBetween(String value1, String value2) {
            addCriterion("ph between", value1, value2, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotBetween(String value1, String value2) {
            addCriterion("ph not between", value1, value2, "ph");
            return (Criteria) this;
        }

        public Criteria andTransparencyIsNull() {
            addCriterion("transparency is null");
            return (Criteria) this;
        }

        public Criteria andTransparencyIsNotNull() {
            addCriterion("transparency is not null");
            return (Criteria) this;
        }

        public Criteria andTransparencyEqualTo(String value) {
            addCriterion("transparency =", value, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyNotEqualTo(String value) {
            addCriterion("transparency <>", value, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyGreaterThan(String value) {
            addCriterion("transparency >", value, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyGreaterThanOrEqualTo(String value) {
            addCriterion("transparency >=", value, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyLessThan(String value) {
            addCriterion("transparency <", value, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyLessThanOrEqualTo(String value) {
            addCriterion("transparency <=", value, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyLike(String value) {
            addCriterion("transparency like", value, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyNotLike(String value) {
            addCriterion("transparency not like", value, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyIn(List<String> values) {
            addCriterion("transparency in", values, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyNotIn(List<String> values) {
            addCriterion("transparency not in", values, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyBetween(String value1, String value2) {
            addCriterion("transparency between", value1, value2, "transparency");
            return (Criteria) this;
        }

        public Criteria andTransparencyNotBetween(String value1, String value2) {
            addCriterion("transparency not between", value1, value2, "transparency");
            return (Criteria) this;
        }

        public Criteria andAdIsNull() {
            addCriterion("ad is null");
            return (Criteria) this;
        }

        public Criteria andAdIsNotNull() {
            addCriterion("ad is not null");
            return (Criteria) this;
        }

        public Criteria andAdEqualTo(String value) {
            addCriterion("ad =", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdNotEqualTo(String value) {
            addCriterion("ad <>", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdGreaterThan(String value) {
            addCriterion("ad >", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdGreaterThanOrEqualTo(String value) {
            addCriterion("ad >=", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdLessThan(String value) {
            addCriterion("ad <", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdLessThanOrEqualTo(String value) {
            addCriterion("ad <=", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdLike(String value) {
            addCriterion("ad like", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdNotLike(String value) {
            addCriterion("ad not like", value, "ad");
            return (Criteria) this;
        }

        public Criteria andAdIn(List<String> values) {
            addCriterion("ad in", values, "ad");
            return (Criteria) this;
        }

        public Criteria andAdNotIn(List<String> values) {
            addCriterion("ad not in", values, "ad");
            return (Criteria) this;
        }

        public Criteria andAdBetween(String value1, String value2) {
            addCriterion("ad between", value1, value2, "ad");
            return (Criteria) this;
        }

        public Criteria andAdNotBetween(String value1, String value2) {
            addCriterion("ad not between", value1, value2, "ad");
            return (Criteria) this;
        }

        public Criteria andSuspendedIsNull() {
            addCriterion("suspended is null");
            return (Criteria) this;
        }

        public Criteria andSuspendedIsNotNull() {
            addCriterion("suspended is not null");
            return (Criteria) this;
        }

        public Criteria andSuspendedEqualTo(String value) {
            addCriterion("suspended =", value, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedNotEqualTo(String value) {
            addCriterion("suspended <>", value, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedGreaterThan(String value) {
            addCriterion("suspended >", value, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedGreaterThanOrEqualTo(String value) {
            addCriterion("suspended >=", value, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedLessThan(String value) {
            addCriterion("suspended <", value, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedLessThanOrEqualTo(String value) {
            addCriterion("suspended <=", value, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedLike(String value) {
            addCriterion("suspended like", value, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedNotLike(String value) {
            addCriterion("suspended not like", value, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedIn(List<String> values) {
            addCriterion("suspended in", values, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedNotIn(List<String> values) {
            addCriterion("suspended not in", values, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedBetween(String value1, String value2) {
            addCriterion("suspended between", value1, value2, "suspended");
            return (Criteria) this;
        }

        public Criteria andSuspendedNotBetween(String value1, String value2) {
            addCriterion("suspended not between", value1, value2, "suspended");
            return (Criteria) this;
        }

        public Criteria andPpmIsNull() {
            addCriterion("ppm is null");
            return (Criteria) this;
        }

        public Criteria andPpmIsNotNull() {
            addCriterion("ppm is not null");
            return (Criteria) this;
        }

        public Criteria andPpmEqualTo(String value) {
            addCriterion("ppm =", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmNotEqualTo(String value) {
            addCriterion("ppm <>", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmGreaterThan(String value) {
            addCriterion("ppm >", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmGreaterThanOrEqualTo(String value) {
            addCriterion("ppm >=", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmLessThan(String value) {
            addCriterion("ppm <", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmLessThanOrEqualTo(String value) {
            addCriterion("ppm <=", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmLike(String value) {
            addCriterion("ppm like", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmNotLike(String value) {
            addCriterion("ppm not like", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmIn(List<String> values) {
            addCriterion("ppm in", values, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmNotIn(List<String> values) {
            addCriterion("ppm not in", values, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmBetween(String value1, String value2) {
            addCriterion("ppm between", value1, value2, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmNotBetween(String value1, String value2) {
            addCriterion("ppm not between", value1, value2, "ppm");
            return (Criteria) this;
        }

        public Criteria andChlFluIsNull() {
            addCriterion("chl_flu is null");
            return (Criteria) this;
        }

        public Criteria andChlFluIsNotNull() {
            addCriterion("chl_flu is not null");
            return (Criteria) this;
        }

        public Criteria andChlFluEqualTo(String value) {
            addCriterion("chl_flu =", value, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluNotEqualTo(String value) {
            addCriterion("chl_flu <>", value, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluGreaterThan(String value) {
            addCriterion("chl_flu >", value, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluGreaterThanOrEqualTo(String value) {
            addCriterion("chl_flu >=", value, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluLessThan(String value) {
            addCriterion("chl_flu <", value, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluLessThanOrEqualTo(String value) {
            addCriterion("chl_flu <=", value, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluLike(String value) {
            addCriterion("chl_flu like", value, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluNotLike(String value) {
            addCriterion("chl_flu not like", value, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluIn(List<String> values) {
            addCriterion("chl_flu in", values, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluNotIn(List<String> values) {
            addCriterion("chl_flu not in", values, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluBetween(String value1, String value2) {
            addCriterion("chl_flu between", value1, value2, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlFluNotBetween(String value1, String value2) {
            addCriterion("chl_flu not between", value1, value2, "chlFlu");
            return (Criteria) this;
        }

        public Criteria andChlorophyllIsNull() {
            addCriterion("chlorophyll is null");
            return (Criteria) this;
        }

        public Criteria andChlorophyllIsNotNull() {
            addCriterion("chlorophyll is not null");
            return (Criteria) this;
        }

        public Criteria andChlorophyllEqualTo(String value) {
            addCriterion("chlorophyll =", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllNotEqualTo(String value) {
            addCriterion("chlorophyll <>", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllGreaterThan(String value) {
            addCriterion("chlorophyll >", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllGreaterThanOrEqualTo(String value) {
            addCriterion("chlorophyll >=", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllLessThan(String value) {
            addCriterion("chlorophyll <", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllLessThanOrEqualTo(String value) {
            addCriterion("chlorophyll <=", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllLike(String value) {
            addCriterion("chlorophyll like", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllNotLike(String value) {
            addCriterion("chlorophyll not like", value, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllIn(List<String> values) {
            addCriterion("chlorophyll in", values, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllNotIn(List<String> values) {
            addCriterion("chlorophyll not in", values, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllBetween(String value1, String value2) {
            addCriterion("chlorophyll between", value1, value2, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andChlorophyllNotBetween(String value1, String value2) {
            addCriterion("chlorophyll not between", value1, value2, "chlorophyll");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaIsNull() {
            addCriterion("cyanobacteria is null");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaIsNotNull() {
            addCriterion("cyanobacteria is not null");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaEqualTo(String value) {
            addCriterion("cyanobacteria =", value, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaNotEqualTo(String value) {
            addCriterion("cyanobacteria <>", value, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaGreaterThan(String value) {
            addCriterion("cyanobacteria >", value, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaGreaterThanOrEqualTo(String value) {
            addCriterion("cyanobacteria >=", value, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaLessThan(String value) {
            addCriterion("cyanobacteria <", value, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaLessThanOrEqualTo(String value) {
            addCriterion("cyanobacteria <=", value, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaLike(String value) {
            addCriterion("cyanobacteria like", value, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaNotLike(String value) {
            addCriterion("cyanobacteria not like", value, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaIn(List<String> values) {
            addCriterion("cyanobacteria in", values, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaNotIn(List<String> values) {
            addCriterion("cyanobacteria not in", values, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaBetween(String value1, String value2) {
            addCriterion("cyanobacteria between", value1, value2, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andCyanobacteriaNotBetween(String value1, String value2) {
            addCriterion("cyanobacteria not between", value1, value2, "cyanobacteria");
            return (Criteria) this;
        }

        public Criteria andOrpIsNull() {
            addCriterion("orp is null");
            return (Criteria) this;
        }

        public Criteria andOrpIsNotNull() {
            addCriterion("orp is not null");
            return (Criteria) this;
        }

        public Criteria andOrpEqualTo(String value) {
            addCriterion("orp =", value, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpNotEqualTo(String value) {
            addCriterion("orp <>", value, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpGreaterThan(String value) {
            addCriterion("orp >", value, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpGreaterThanOrEqualTo(String value) {
            addCriterion("orp >=", value, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpLessThan(String value) {
            addCriterion("orp <", value, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpLessThanOrEqualTo(String value) {
            addCriterion("orp <=", value, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpLike(String value) {
            addCriterion("orp like", value, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpNotLike(String value) {
            addCriterion("orp not like", value, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpIn(List<String> values) {
            addCriterion("orp in", values, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpNotIn(List<String> values) {
            addCriterion("orp not in", values, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpBetween(String value1, String value2) {
            addCriterion("orp between", value1, value2, "orp");
            return (Criteria) this;
        }

        public Criteria andOrpNotBetween(String value1, String value2) {
            addCriterion("orp not between", value1, value2, "orp");
            return (Criteria) this;
        }

        public Criteria andOilIsNull() {
            addCriterion("oil is null");
            return (Criteria) this;
        }

        public Criteria andOilIsNotNull() {
            addCriterion("oil is not null");
            return (Criteria) this;
        }

        public Criteria andOilEqualTo(String value) {
            addCriterion("oil =", value, "oil");
            return (Criteria) this;
        }

        public Criteria andOilNotEqualTo(String value) {
            addCriterion("oil <>", value, "oil");
            return (Criteria) this;
        }

        public Criteria andOilGreaterThan(String value) {
            addCriterion("oil >", value, "oil");
            return (Criteria) this;
        }

        public Criteria andOilGreaterThanOrEqualTo(String value) {
            addCriterion("oil >=", value, "oil");
            return (Criteria) this;
        }

        public Criteria andOilLessThan(String value) {
            addCriterion("oil <", value, "oil");
            return (Criteria) this;
        }

        public Criteria andOilLessThanOrEqualTo(String value) {
            addCriterion("oil <=", value, "oil");
            return (Criteria) this;
        }

        public Criteria andOilLike(String value) {
            addCriterion("oil like", value, "oil");
            return (Criteria) this;
        }

        public Criteria andOilNotLike(String value) {
            addCriterion("oil not like", value, "oil");
            return (Criteria) this;
        }

        public Criteria andOilIn(List<String> values) {
            addCriterion("oil in", values, "oil");
            return (Criteria) this;
        }

        public Criteria andOilNotIn(List<String> values) {
            addCriterion("oil not in", values, "oil");
            return (Criteria) this;
        }

        public Criteria andOilBetween(String value1, String value2) {
            addCriterion("oil between", value1, value2, "oil");
            return (Criteria) this;
        }

        public Criteria andOilNotBetween(String value1, String value2) {
            addCriterion("oil not between", value1, value2, "oil");
            return (Criteria) this;
        }

        public Criteria andPhosphorusIsNull() {
            addCriterion("phosphorus is null");
            return (Criteria) this;
        }

        public Criteria andPhosphorusIsNotNull() {
            addCriterion("phosphorus is not null");
            return (Criteria) this;
        }

        public Criteria andPhosphorusEqualTo(String value) {
            addCriterion("phosphorus =", value, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusNotEqualTo(String value) {
            addCriterion("phosphorus <>", value, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusGreaterThan(String value) {
            addCriterion("phosphorus >", value, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusGreaterThanOrEqualTo(String value) {
            addCriterion("phosphorus >=", value, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusLessThan(String value) {
            addCriterion("phosphorus <", value, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusLessThanOrEqualTo(String value) {
            addCriterion("phosphorus <=", value, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusLike(String value) {
            addCriterion("phosphorus like", value, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusNotLike(String value) {
            addCriterion("phosphorus not like", value, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusIn(List<String> values) {
            addCriterion("phosphorus in", values, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusNotIn(List<String> values) {
            addCriterion("phosphorus not in", values, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusBetween(String value1, String value2) {
            addCriterion("phosphorus between", value1, value2, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andPhosphorusNotBetween(String value1, String value2) {
            addCriterion("phosphorus not between", value1, value2, "phosphorus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andFzrIsNull() {
            addCriterion("fzr is null");
            return (Criteria) this;
        }

        public Criteria andFzrIsNotNull() {
            addCriterion("fzr is not null");
            return (Criteria) this;
        }

        public Criteria andFzrEqualTo(String value) {
            addCriterion("fzr =", value, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrNotEqualTo(String value) {
            addCriterion("fzr <>", value, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrGreaterThan(String value) {
            addCriterion("fzr >", value, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrGreaterThanOrEqualTo(String value) {
            addCriterion("fzr >=", value, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrLessThan(String value) {
            addCriterion("fzr <", value, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrLessThanOrEqualTo(String value) {
            addCriterion("fzr <=", value, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrLike(String value) {
            addCriterion("fzr like", value, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrNotLike(String value) {
            addCriterion("fzr not like", value, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrIn(List<String> values) {
            addCriterion("fzr in", values, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrNotIn(List<String> values) {
            addCriterion("fzr not in", values, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrBetween(String value1, String value2) {
            addCriterion("fzr between", value1, value2, "fzr");
            return (Criteria) this;
        }

        public Criteria andFzrNotBetween(String value1, String value2) {
            addCriterion("fzr not between", value1, value2, "fzr");
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

        public Criteria andSm1IsNull() {
            addCriterion("sm1 is null");
            return (Criteria) this;
        }

        public Criteria andSm1IsNotNull() {
            addCriterion("sm1 is not null");
            return (Criteria) this;
        }

        public Criteria andSm1EqualTo(String value) {
            addCriterion("sm1 =", value, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1NotEqualTo(String value) {
            addCriterion("sm1 <>", value, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1GreaterThan(String value) {
            addCriterion("sm1 >", value, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1GreaterThanOrEqualTo(String value) {
            addCriterion("sm1 >=", value, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1LessThan(String value) {
            addCriterion("sm1 <", value, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1LessThanOrEqualTo(String value) {
            addCriterion("sm1 <=", value, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1Like(String value) {
            addCriterion("sm1 like", value, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1NotLike(String value) {
            addCriterion("sm1 not like", value, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1In(List<String> values) {
            addCriterion("sm1 in", values, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1NotIn(List<String> values) {
            addCriterion("sm1 not in", values, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1Between(String value1, String value2) {
            addCriterion("sm1 between", value1, value2, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm1NotBetween(String value1, String value2) {
            addCriterion("sm1 not between", value1, value2, "sm1");
            return (Criteria) this;
        }

        public Criteria andSm2IsNull() {
            addCriterion("sm2 is null");
            return (Criteria) this;
        }

        public Criteria andSm2IsNotNull() {
            addCriterion("sm2 is not null");
            return (Criteria) this;
        }

        public Criteria andSm2EqualTo(String value) {
            addCriterion("sm2 =", value, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2NotEqualTo(String value) {
            addCriterion("sm2 <>", value, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2GreaterThan(String value) {
            addCriterion("sm2 >", value, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2GreaterThanOrEqualTo(String value) {
            addCriterion("sm2 >=", value, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2LessThan(String value) {
            addCriterion("sm2 <", value, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2LessThanOrEqualTo(String value) {
            addCriterion("sm2 <=", value, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2Like(String value) {
            addCriterion("sm2 like", value, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2NotLike(String value) {
            addCriterion("sm2 not like", value, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2In(List<String> values) {
            addCriterion("sm2 in", values, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2NotIn(List<String> values) {
            addCriterion("sm2 not in", values, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2Between(String value1, String value2) {
            addCriterion("sm2 between", value1, value2, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm2NotBetween(String value1, String value2) {
            addCriterion("sm2 not between", value1, value2, "sm2");
            return (Criteria) this;
        }

        public Criteria andSm3IsNull() {
            addCriterion("sm3 is null");
            return (Criteria) this;
        }

        public Criteria andSm3IsNotNull() {
            addCriterion("sm3 is not null");
            return (Criteria) this;
        }

        public Criteria andSm3EqualTo(String value) {
            addCriterion("sm3 =", value, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3NotEqualTo(String value) {
            addCriterion("sm3 <>", value, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3GreaterThan(String value) {
            addCriterion("sm3 >", value, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3GreaterThanOrEqualTo(String value) {
            addCriterion("sm3 >=", value, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3LessThan(String value) {
            addCriterion("sm3 <", value, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3LessThanOrEqualTo(String value) {
            addCriterion("sm3 <=", value, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3Like(String value) {
            addCriterion("sm3 like", value, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3NotLike(String value) {
            addCriterion("sm3 not like", value, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3In(List<String> values) {
            addCriterion("sm3 in", values, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3NotIn(List<String> values) {
            addCriterion("sm3 not in", values, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3Between(String value1, String value2) {
            addCriterion("sm3 between", value1, value2, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm3NotBetween(String value1, String value2) {
            addCriterion("sm3 not between", value1, value2, "sm3");
            return (Criteria) this;
        }

        public Criteria andSm4IsNull() {
            addCriterion("sm4 is null");
            return (Criteria) this;
        }

        public Criteria andSm4IsNotNull() {
            addCriterion("sm4 is not null");
            return (Criteria) this;
        }

        public Criteria andSm4EqualTo(String value) {
            addCriterion("sm4 =", value, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4NotEqualTo(String value) {
            addCriterion("sm4 <>", value, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4GreaterThan(String value) {
            addCriterion("sm4 >", value, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4GreaterThanOrEqualTo(String value) {
            addCriterion("sm4 >=", value, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4LessThan(String value) {
            addCriterion("sm4 <", value, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4LessThanOrEqualTo(String value) {
            addCriterion("sm4 <=", value, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4Like(String value) {
            addCriterion("sm4 like", value, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4NotLike(String value) {
            addCriterion("sm4 not like", value, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4In(List<String> values) {
            addCriterion("sm4 in", values, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4NotIn(List<String> values) {
            addCriterion("sm4 not in", values, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4Between(String value1, String value2) {
            addCriterion("sm4 between", value1, value2, "sm4");
            return (Criteria) this;
        }

        public Criteria andSm4NotBetween(String value1, String value2) {
            addCriterion("sm4 not between", value1, value2, "sm4");
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