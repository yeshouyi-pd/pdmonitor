package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class SonarEchoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SonarEchoExample() {
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

        public Criteria andAcTimeIsNull() {
            addCriterion("ac_time is null");
            return (Criteria) this;
        }

        public Criteria andAcTimeIsNotNull() {
            addCriterion("ac_time is not null");
            return (Criteria) this;
        }

        public Criteria andAcTimeEqualTo(String value) {
            addCriterion("ac_time =", value, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeNotEqualTo(String value) {
            addCriterion("ac_time <>", value, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeGreaterThan(String value) {
            addCriterion("ac_time >", value, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ac_time >=", value, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeLessThan(String value) {
            addCriterion("ac_time <", value, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeLessThanOrEqualTo(String value) {
            addCriterion("ac_time <=", value, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeLike(String value) {
            addCriterion("ac_time like", value, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeNotLike(String value) {
            addCriterion("ac_time not like", value, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeIn(List<String> values) {
            addCriterion("ac_time in", values, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeNotIn(List<String> values) {
            addCriterion("ac_time not in", values, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeBetween(String value1, String value2) {
            addCriterion("ac_time between", value1, value2, "acTime");
            return (Criteria) this;
        }

        public Criteria andAcTimeNotBetween(String value1, String value2) {
            addCriterion("ac_time not between", value1, value2, "acTime");
            return (Criteria) this;
        }

        public Criteria andNumPingIsNull() {
            addCriterion("num_ping is null");
            return (Criteria) this;
        }

        public Criteria andNumPingIsNotNull() {
            addCriterion("num_ping is not null");
            return (Criteria) this;
        }

        public Criteria andNumPingEqualTo(Integer value) {
            addCriterion("num_ping =", value, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumPingNotEqualTo(Integer value) {
            addCriterion("num_ping <>", value, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumPingGreaterThan(Integer value) {
            addCriterion("num_ping >", value, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumPingGreaterThanOrEqualTo(Integer value) {
            addCriterion("num_ping >=", value, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumPingLessThan(Integer value) {
            addCriterion("num_ping <", value, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumPingLessThanOrEqualTo(Integer value) {
            addCriterion("num_ping <=", value, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumPingIn(List<Integer> values) {
            addCriterion("num_ping in", values, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumPingNotIn(List<Integer> values) {
            addCriterion("num_ping not in", values, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumPingBetween(Integer value1, Integer value2) {
            addCriterion("num_ping between", value1, value2, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumPingNotBetween(Integer value1, Integer value2) {
            addCriterion("num_ping not between", value1, value2, "numPing");
            return (Criteria) this;
        }

        public Criteria andNumDotIsNull() {
            addCriterion("num_dot is null");
            return (Criteria) this;
        }

        public Criteria andNumDotIsNotNull() {
            addCriterion("num_dot is not null");
            return (Criteria) this;
        }

        public Criteria andNumDotEqualTo(Integer value) {
            addCriterion("num_dot =", value, "numDot");
            return (Criteria) this;
        }

        public Criteria andNumDotNotEqualTo(Integer value) {
            addCriterion("num_dot <>", value, "numDot");
            return (Criteria) this;
        }

        public Criteria andNumDotGreaterThan(Integer value) {
            addCriterion("num_dot >", value, "numDot");
            return (Criteria) this;
        }

        public Criteria andNumDotGreaterThanOrEqualTo(Integer value) {
            addCriterion("num_dot >=", value, "numDot");
            return (Criteria) this;
        }

        public Criteria andNumDotLessThan(Integer value) {
            addCriterion("num_dot <", value, "numDot");
            return (Criteria) this;
        }

        public Criteria andNumDotLessThanOrEqualTo(Integer value) {
            addCriterion("num_dot <=", value, "numDot");
            return (Criteria) this;
        }

        public Criteria andNumDotIn(List<Integer> values) {
            addCriterion("num_dot in", values, "numDot");
            return (Criteria) this;
        }

        public Criteria andNumDotNotIn(List<Integer> values) {
            addCriterion("num_dot not in", values, "numDot");
            return (Criteria) this;
        }

        public Criteria andNumDotBetween(Integer value1, Integer value2) {
            addCriterion("num_dot between", value1, value2, "numDot");
            return (Criteria) this;
        }

        public Criteria andNumDotNotBetween(Integer value1, Integer value2) {
            addCriterion("num_dot not between", value1, value2, "numDot");
            return (Criteria) this;
        }

        public Criteria andDrIsNull() {
            addCriterion("dR is null");
            return (Criteria) this;
        }

        public Criteria andDrIsNotNull() {
            addCriterion("dR is not null");
            return (Criteria) this;
        }

        public Criteria andDrEqualTo(Double value) {
            addCriterion("dR =", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotEqualTo(Double value) {
            addCriterion("dR <>", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThan(Double value) {
            addCriterion("dR >", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThanOrEqualTo(Double value) {
            addCriterion("dR >=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThan(Double value) {
            addCriterion("dR <", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThanOrEqualTo(Double value) {
            addCriterion("dR <=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrIn(List<Double> values) {
            addCriterion("dR in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotIn(List<Double> values) {
            addCriterion("dR not in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrBetween(Double value1, Double value2) {
            addCriterion("dR between", value1, value2, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotBetween(Double value1, Double value2) {
            addCriterion("dR not between", value1, value2, "dr");
            return (Criteria) this;
        }

        public Criteria andDbMaxIsNull() {
            addCriterion("db_max is null");
            return (Criteria) this;
        }

        public Criteria andDbMaxIsNotNull() {
            addCriterion("db_max is not null");
            return (Criteria) this;
        }

        public Criteria andDbMaxEqualTo(Double value) {
            addCriterion("db_max =", value, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMaxNotEqualTo(Double value) {
            addCriterion("db_max <>", value, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMaxGreaterThan(Double value) {
            addCriterion("db_max >", value, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMaxGreaterThanOrEqualTo(Double value) {
            addCriterion("db_max >=", value, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMaxLessThan(Double value) {
            addCriterion("db_max <", value, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMaxLessThanOrEqualTo(Double value) {
            addCriterion("db_max <=", value, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMaxIn(List<Double> values) {
            addCriterion("db_max in", values, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMaxNotIn(List<Double> values) {
            addCriterion("db_max not in", values, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMaxBetween(Double value1, Double value2) {
            addCriterion("db_max between", value1, value2, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMaxNotBetween(Double value1, Double value2) {
            addCriterion("db_max not between", value1, value2, "dbMax");
            return (Criteria) this;
        }

        public Criteria andDbMinIsNull() {
            addCriterion("db_min is null");
            return (Criteria) this;
        }

        public Criteria andDbMinIsNotNull() {
            addCriterion("db_min is not null");
            return (Criteria) this;
        }

        public Criteria andDbMinEqualTo(Double value) {
            addCriterion("db_min =", value, "dbMin");
            return (Criteria) this;
        }

        public Criteria andDbMinNotEqualTo(Double value) {
            addCriterion("db_min <>", value, "dbMin");
            return (Criteria) this;
        }

        public Criteria andDbMinGreaterThan(Double value) {
            addCriterion("db_min >", value, "dbMin");
            return (Criteria) this;
        }

        public Criteria andDbMinGreaterThanOrEqualTo(Double value) {
            addCriterion("db_min >=", value, "dbMin");
            return (Criteria) this;
        }

        public Criteria andDbMinLessThan(Double value) {
            addCriterion("db_min <", value, "dbMin");
            return (Criteria) this;
        }

        public Criteria andDbMinLessThanOrEqualTo(Double value) {
            addCriterion("db_min <=", value, "dbMin");
            return (Criteria) this;
        }

        public Criteria andDbMinIn(List<Double> values) {
            addCriterion("db_min in", values, "dbMin");
            return (Criteria) this;
        }

        public Criteria andDbMinNotIn(List<Double> values) {
            addCriterion("db_min not in", values, "dbMin");
            return (Criteria) this;
        }

        public Criteria andDbMinBetween(Double value1, Double value2) {
            addCriterion("db_min between", value1, value2, "dbMin");
            return (Criteria) this;
        }

        public Criteria andDbMinNotBetween(Double value1, Double value2) {
            addCriterion("db_min not between", value1, value2, "dbMin");
            return (Criteria) this;
        }

        public Criteria andAverSvIsNull() {
            addCriterion("aver_sv is null");
            return (Criteria) this;
        }

        public Criteria andAverSvIsNotNull() {
            addCriterion("aver_sv is not null");
            return (Criteria) this;
        }

        public Criteria andAverSvEqualTo(Double value) {
            addCriterion("aver_sv =", value, "averSv");
            return (Criteria) this;
        }

        public Criteria andAverSvNotEqualTo(Double value) {
            addCriterion("aver_sv <>", value, "averSv");
            return (Criteria) this;
        }

        public Criteria andAverSvGreaterThan(Double value) {
            addCriterion("aver_sv >", value, "averSv");
            return (Criteria) this;
        }

        public Criteria andAverSvGreaterThanOrEqualTo(Double value) {
            addCriterion("aver_sv >=", value, "averSv");
            return (Criteria) this;
        }

        public Criteria andAverSvLessThan(Double value) {
            addCriterion("aver_sv <", value, "averSv");
            return (Criteria) this;
        }

        public Criteria andAverSvLessThanOrEqualTo(Double value) {
            addCriterion("aver_sv <=", value, "averSv");
            return (Criteria) this;
        }

        public Criteria andAverSvIn(List<Double> values) {
            addCriterion("aver_sv in", values, "averSv");
            return (Criteria) this;
        }

        public Criteria andAverSvNotIn(List<Double> values) {
            addCriterion("aver_sv not in", values, "averSv");
            return (Criteria) this;
        }

        public Criteria andAverSvBetween(Double value1, Double value2) {
            addCriterion("aver_sv between", value1, value2, "averSv");
            return (Criteria) this;
        }

        public Criteria andAverSvNotBetween(Double value1, Double value2) {
            addCriterion("aver_sv not between", value1, value2, "averSv");
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

        public Criteria andBufferSizeSonarIsNull() {
            addCriterion("buffer_size_sonar is null");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarIsNotNull() {
            addCriterion("buffer_size_sonar is not null");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarEqualTo(Integer value) {
            addCriterion("buffer_size_sonar =", value, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarNotEqualTo(Integer value) {
            addCriterion("buffer_size_sonar <>", value, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarGreaterThan(Integer value) {
            addCriterion("buffer_size_sonar >", value, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarGreaterThanOrEqualTo(Integer value) {
            addCriterion("buffer_size_sonar >=", value, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarLessThan(Integer value) {
            addCriterion("buffer_size_sonar <", value, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarLessThanOrEqualTo(Integer value) {
            addCriterion("buffer_size_sonar <=", value, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarIn(List<Integer> values) {
            addCriterion("buffer_size_sonar in", values, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarNotIn(List<Integer> values) {
            addCriterion("buffer_size_sonar not in", values, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarBetween(Integer value1, Integer value2) {
            addCriterion("buffer_size_sonar between", value1, value2, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeSonarNotBetween(Integer value1, Integer value2) {
            addCriterion("buffer_size_sonar not between", value1, value2, "bufferSizeSonar");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampIsNull() {
            addCriterion("buffer_size_time_stamp is null");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampIsNotNull() {
            addCriterion("buffer_size_time_stamp is not null");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampEqualTo(Integer value) {
            addCriterion("buffer_size_time_stamp =", value, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampNotEqualTo(Integer value) {
            addCriterion("buffer_size_time_stamp <>", value, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampGreaterThan(Integer value) {
            addCriterion("buffer_size_time_stamp >", value, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampGreaterThanOrEqualTo(Integer value) {
            addCriterion("buffer_size_time_stamp >=", value, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampLessThan(Integer value) {
            addCriterion("buffer_size_time_stamp <", value, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampLessThanOrEqualTo(Integer value) {
            addCriterion("buffer_size_time_stamp <=", value, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampIn(List<Integer> values) {
            addCriterion("buffer_size_time_stamp in", values, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampNotIn(List<Integer> values) {
            addCriterion("buffer_size_time_stamp not in", values, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampBetween(Integer value1, Integer value2) {
            addCriterion("buffer_size_time_stamp between", value1, value2, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andBufferSizeTimeStampNotBetween(Integer value1, Integer value2) {
            addCriterion("buffer_size_time_stamp not between", value1, value2, "bufferSizeTimeStamp");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("`type` not between", value1, value2, "type");
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