package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendCommandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SendCommandExample() {
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

        public Criteria andNodeIdIsNull() {
            addCriterion("node_id is null");
            return (Criteria) this;
        }

        public Criteria andNodeIdIsNotNull() {
            addCriterion("node_id is not null");
            return (Criteria) this;
        }

        public Criteria andNodeIdEqualTo(String value) {
            addCriterion("node_id =", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotEqualTo(String value) {
            addCriterion("node_id <>", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThan(String value) {
            addCriterion("node_id >", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThanOrEqualTo(String value) {
            addCriterion("node_id >=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThan(String value) {
            addCriterion("node_id <", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThanOrEqualTo(String value) {
            addCriterion("node_id <=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLike(String value) {
            addCriterion("node_id like", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotLike(String value) {
            addCriterion("node_id not like", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdIn(List<String> values) {
            addCriterion("node_id in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotIn(List<String> values) {
            addCriterion("node_id not in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdBetween(String value1, String value2) {
            addCriterion("node_id between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotBetween(String value1, String value2) {
            addCriterion("node_id not between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andLoraIdIsNull() {
            addCriterion("lora_id is null");
            return (Criteria) this;
        }

        public Criteria andLoraIdIsNotNull() {
            addCriterion("lora_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoraIdEqualTo(String value) {
            addCriterion("lora_id =", value, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdNotEqualTo(String value) {
            addCriterion("lora_id <>", value, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdGreaterThan(String value) {
            addCriterion("lora_id >", value, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdGreaterThanOrEqualTo(String value) {
            addCriterion("lora_id >=", value, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdLessThan(String value) {
            addCriterion("lora_id <", value, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdLessThanOrEqualTo(String value) {
            addCriterion("lora_id <=", value, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdLike(String value) {
            addCriterion("lora_id like", value, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdNotLike(String value) {
            addCriterion("lora_id not like", value, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdIn(List<String> values) {
            addCriterion("lora_id in", values, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdNotIn(List<String> values) {
            addCriterion("lora_id not in", values, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdBetween(String value1, String value2) {
            addCriterion("lora_id between", value1, value2, "loraId");
            return (Criteria) this;
        }

        public Criteria andLoraIdNotBetween(String value1, String value2) {
            addCriterion("lora_id not between", value1, value2, "loraId");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNull() {
            addCriterion("serial_number is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNotNull() {
            addCriterion("serial_number is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberEqualTo(String value) {
            addCriterion("serial_number =", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotEqualTo(String value) {
            addCriterion("serial_number <>", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThan(String value) {
            addCriterion("serial_number >", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThanOrEqualTo(String value) {
            addCriterion("serial_number >=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThan(String value) {
            addCriterion("serial_number <", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThanOrEqualTo(String value) {
            addCriterion("serial_number <=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLike(String value) {
            addCriterion("serial_number like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotLike(String value) {
            addCriterion("serial_number not like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIn(List<String> values) {
            addCriterion("serial_number in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotIn(List<String> values) {
            addCriterion("serial_number not in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberBetween(String value1, String value2) {
            addCriterion("serial_number between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotBetween(String value1, String value2) {
            addCriterion("serial_number not between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andWholeSecIsNull() {
            addCriterion("whole_sec is null");
            return (Criteria) this;
        }

        public Criteria andWholeSecIsNotNull() {
            addCriterion("whole_sec is not null");
            return (Criteria) this;
        }

        public Criteria andWholeSecEqualTo(String value) {
            addCriterion("whole_sec =", value, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecNotEqualTo(String value) {
            addCriterion("whole_sec <>", value, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecGreaterThan(String value) {
            addCriterion("whole_sec >", value, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecGreaterThanOrEqualTo(String value) {
            addCriterion("whole_sec >=", value, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecLessThan(String value) {
            addCriterion("whole_sec <", value, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecLessThanOrEqualTo(String value) {
            addCriterion("whole_sec <=", value, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecLike(String value) {
            addCriterion("whole_sec like", value, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecNotLike(String value) {
            addCriterion("whole_sec not like", value, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecIn(List<String> values) {
            addCriterion("whole_sec in", values, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecNotIn(List<String> values) {
            addCriterion("whole_sec not in", values, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecBetween(String value1, String value2) {
            addCriterion("whole_sec between", value1, value2, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andWholeSecNotBetween(String value1, String value2) {
            addCriterion("whole_sec not between", value1, value2, "wholeSec");
            return (Criteria) this;
        }

        public Criteria andFracSecIsNull() {
            addCriterion("frac_sec is null");
            return (Criteria) this;
        }

        public Criteria andFracSecIsNotNull() {
            addCriterion("frac_sec is not null");
            return (Criteria) this;
        }

        public Criteria andFracSecEqualTo(Double value) {
            addCriterion("frac_sec =", value, "fracSec");
            return (Criteria) this;
        }

        public Criteria andFracSecNotEqualTo(Double value) {
            addCriterion("frac_sec <>", value, "fracSec");
            return (Criteria) this;
        }

        public Criteria andFracSecGreaterThan(Double value) {
            addCriterion("frac_sec >", value, "fracSec");
            return (Criteria) this;
        }

        public Criteria andFracSecGreaterThanOrEqualTo(Double value) {
            addCriterion("frac_sec >=", value, "fracSec");
            return (Criteria) this;
        }

        public Criteria andFracSecLessThan(Double value) {
            addCriterion("frac_sec <", value, "fracSec");
            return (Criteria) this;
        }

        public Criteria andFracSecLessThanOrEqualTo(Double value) {
            addCriterion("frac_sec <=", value, "fracSec");
            return (Criteria) this;
        }

        public Criteria andFracSecIn(List<Double> values) {
            addCriterion("frac_sec in", values, "fracSec");
            return (Criteria) this;
        }

        public Criteria andFracSecNotIn(List<Double> values) {
            addCriterion("frac_sec not in", values, "fracSec");
            return (Criteria) this;
        }

        public Criteria andFracSecBetween(Double value1, Double value2) {
            addCriterion("frac_sec between", value1, value2, "fracSec");
            return (Criteria) this;
        }

        public Criteria andFracSecNotBetween(Double value1, Double value2) {
            addCriterion("frac_sec not between", value1, value2, "fracSec");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorIsNull() {
            addCriterion("up_chirp_cor is null");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorIsNotNull() {
            addCriterion("up_chirp_cor is not null");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorEqualTo(Float value) {
            addCriterion("up_chirp_cor =", value, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorNotEqualTo(Float value) {
            addCriterion("up_chirp_cor <>", value, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorGreaterThan(Float value) {
            addCriterion("up_chirp_cor >", value, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorGreaterThanOrEqualTo(Float value) {
            addCriterion("up_chirp_cor >=", value, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorLessThan(Float value) {
            addCriterion("up_chirp_cor <", value, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorLessThanOrEqualTo(Float value) {
            addCriterion("up_chirp_cor <=", value, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorIn(List<Float> values) {
            addCriterion("up_chirp_cor in", values, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorNotIn(List<Float> values) {
            addCriterion("up_chirp_cor not in", values, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorBetween(Float value1, Float value2) {
            addCriterion("up_chirp_cor between", value1, value2, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andUpChirpCorNotBetween(Float value1, Float value2) {
            addCriterion("up_chirp_cor not between", value1, value2, "upChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorIsNull() {
            addCriterion("down_chirp_cor is null");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorIsNotNull() {
            addCriterion("down_chirp_cor is not null");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorEqualTo(Float value) {
            addCriterion("down_chirp_cor =", value, "downChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorNotEqualTo(Float value) {
            addCriterion("down_chirp_cor <>", value, "downChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorGreaterThan(Float value) {
            addCriterion("down_chirp_cor >", value, "downChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorGreaterThanOrEqualTo(Float value) {
            addCriterion("down_chirp_cor >=", value, "downChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorLessThan(Float value) {
            addCriterion("down_chirp_cor <", value, "downChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorLessThanOrEqualTo(Float value) {
            addCriterion("down_chirp_cor <=", value, "downChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorIn(List<Float> values) {
            addCriterion("down_chirp_cor in", values, "downChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorNotIn(List<Float> values) {
            addCriterion("down_chirp_cor not in", values, "downChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorBetween(Float value1, Float value2) {
            addCriterion("down_chirp_cor between", value1, value2, "downChirpCor");
            return (Criteria) this;
        }

        public Criteria andDownChirpCorNotBetween(Float value1, Float value2) {
            addCriterion("down_chirp_cor not between", value1, value2, "downChirpCor");
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

        public Criteria andPpmEqualTo(Double value) {
            addCriterion("ppm =", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmNotEqualTo(Double value) {
            addCriterion("ppm <>", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmGreaterThan(Double value) {
            addCriterion("ppm >", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmGreaterThanOrEqualTo(Double value) {
            addCriterion("ppm >=", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmLessThan(Double value) {
            addCriterion("ppm <", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmLessThanOrEqualTo(Double value) {
            addCriterion("ppm <=", value, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmIn(List<Double> values) {
            addCriterion("ppm in", values, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmNotIn(List<Double> values) {
            addCriterion("ppm not in", values, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmBetween(Double value1, Double value2) {
            addCriterion("ppm between", value1, value2, "ppm");
            return (Criteria) this;
        }

        public Criteria andPpmNotBetween(Double value1, Double value2) {
            addCriterion("ppm not between", value1, value2, "ppm");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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