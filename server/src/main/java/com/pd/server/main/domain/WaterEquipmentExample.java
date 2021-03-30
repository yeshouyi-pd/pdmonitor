package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterEquipmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WaterEquipmentExample() {
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

        public Criteria andSbmcIsNull() {
            addCriterion("sbmc is null");
            return (Criteria) this;
        }

        public Criteria andSbmcIsNotNull() {
            addCriterion("sbmc is not null");
            return (Criteria) this;
        }

        public Criteria andSbmcEqualTo(String value) {
            addCriterion("sbmc =", value, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcNotEqualTo(String value) {
            addCriterion("sbmc <>", value, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcGreaterThan(String value) {
            addCriterion("sbmc >", value, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcGreaterThanOrEqualTo(String value) {
            addCriterion("sbmc >=", value, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcLessThan(String value) {
            addCriterion("sbmc <", value, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcLessThanOrEqualTo(String value) {
            addCriterion("sbmc <=", value, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcLike(String value) {
            addCriterion("sbmc like", value, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcNotLike(String value) {
            addCriterion("sbmc not like", value, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcIn(List<String> values) {
            addCriterion("sbmc in", values, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcNotIn(List<String> values) {
            addCriterion("sbmc not in", values, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcBetween(String value1, String value2) {
            addCriterion("sbmc between", value1, value2, "sbmc");
            return (Criteria) this;
        }

        public Criteria andSbmcNotBetween(String value1, String value2) {
            addCriterion("sbmc not between", value1, value2, "sbmc");
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

        public Criteria andDeptcodeIsNull() {
            addCriterion("deptcode is null");
            return (Criteria) this;
        }

        public Criteria andDeptcodeIsNotNull() {
            addCriterion("deptcode is not null");
            return (Criteria) this;
        }

        public Criteria andDeptcodeEqualTo(String value) {
            addCriterion("deptcode =", value, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeNotEqualTo(String value) {
            addCriterion("deptcode <>", value, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeGreaterThan(String value) {
            addCriterion("deptcode >", value, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeGreaterThanOrEqualTo(String value) {
            addCriterion("deptcode >=", value, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeLessThan(String value) {
            addCriterion("deptcode <", value, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeLessThanOrEqualTo(String value) {
            addCriterion("deptcode <=", value, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeLike(String value) {
            addCriterion("deptcode like", value, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeNotLike(String value) {
            addCriterion("deptcode not like", value, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeIn(List<String> values) {
            addCriterion("deptcode in", values, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeNotIn(List<String> values) {
            addCriterion("deptcode not in", values, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeBetween(String value1, String value2) {
            addCriterion("deptcode between", value1, value2, "deptcode");
            return (Criteria) this;
        }

        public Criteria andDeptcodeNotBetween(String value1, String value2) {
            addCriterion("deptcode not between", value1, value2, "deptcode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeIsNull() {
            addCriterion("center_code is null");
            return (Criteria) this;
        }

        public Criteria andCenterCodeIsNotNull() {
            addCriterion("center_code is not null");
            return (Criteria) this;
        }

        public Criteria andCenterCodeEqualTo(String value) {
            addCriterion("center_code =", value, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeNotEqualTo(String value) {
            addCriterion("center_code <>", value, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeGreaterThan(String value) {
            addCriterion("center_code >", value, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeGreaterThanOrEqualTo(String value) {
            addCriterion("center_code >=", value, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeLessThan(String value) {
            addCriterion("center_code <", value, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeLessThanOrEqualTo(String value) {
            addCriterion("center_code <=", value, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeLike(String value) {
            addCriterion("center_code like", value, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeNotLike(String value) {
            addCriterion("center_code not like", value, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeIn(List<String> values) {
            addCriterion("center_code in", values, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeNotIn(List<String> values) {
            addCriterion("center_code not in", values, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeBetween(String value1, String value2) {
            addCriterion("center_code between", value1, value2, "centerCode");
            return (Criteria) this;
        }

        public Criteria andCenterCodeNotBetween(String value1, String value2) {
            addCriterion("center_code not between", value1, value2, "centerCode");
            return (Criteria) this;
        }

        public Criteria andSblbIsNull() {
            addCriterion("sblb is null");
            return (Criteria) this;
        }

        public Criteria andSblbIsNotNull() {
            addCriterion("sblb is not null");
            return (Criteria) this;
        }

        public Criteria andSblbEqualTo(String value) {
            addCriterion("sblb =", value, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbNotEqualTo(String value) {
            addCriterion("sblb <>", value, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbGreaterThan(String value) {
            addCriterion("sblb >", value, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbGreaterThanOrEqualTo(String value) {
            addCriterion("sblb >=", value, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbLessThan(String value) {
            addCriterion("sblb <", value, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbLessThanOrEqualTo(String value) {
            addCriterion("sblb <=", value, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbLike(String value) {
            addCriterion("sblb like", value, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbNotLike(String value) {
            addCriterion("sblb not like", value, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbIn(List<String> values) {
            addCriterion("sblb in", values, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbNotIn(List<String> values) {
            addCriterion("sblb not in", values, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbBetween(String value1, String value2) {
            addCriterion("sblb between", value1, value2, "sblb");
            return (Criteria) this;
        }

        public Criteria andSblbNotBetween(String value1, String value2) {
            addCriterion("sblb not between", value1, value2, "sblb");
            return (Criteria) this;
        }

        public Criteria andDqzlIsNull() {
            addCriterion("dqzl is null");
            return (Criteria) this;
        }

        public Criteria andDqzlIsNotNull() {
            addCriterion("dqzl is not null");
            return (Criteria) this;
        }

        public Criteria andDqzlEqualTo(String value) {
            addCriterion("dqzl =", value, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlNotEqualTo(String value) {
            addCriterion("dqzl <>", value, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlGreaterThan(String value) {
            addCriterion("dqzl >", value, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlGreaterThanOrEqualTo(String value) {
            addCriterion("dqzl >=", value, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlLessThan(String value) {
            addCriterion("dqzl <", value, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlLessThanOrEqualTo(String value) {
            addCriterion("dqzl <=", value, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlLike(String value) {
            addCriterion("dqzl like", value, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlNotLike(String value) {
            addCriterion("dqzl not like", value, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlIn(List<String> values) {
            addCriterion("dqzl in", values, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlNotIn(List<String> values) {
            addCriterion("dqzl not in", values, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlBetween(String value1, String value2) {
            addCriterion("dqzl between", value1, value2, "dqzl");
            return (Criteria) this;
        }

        public Criteria andDqzlNotBetween(String value1, String value2) {
            addCriterion("dqzl not between", value1, value2, "dqzl");
            return (Criteria) this;
        }

        public Criteria andSbxhIsNull() {
            addCriterion("sbxh is null");
            return (Criteria) this;
        }

        public Criteria andSbxhIsNotNull() {
            addCriterion("sbxh is not null");
            return (Criteria) this;
        }

        public Criteria andSbxhEqualTo(String value) {
            addCriterion("sbxh =", value, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhNotEqualTo(String value) {
            addCriterion("sbxh <>", value, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhGreaterThan(String value) {
            addCriterion("sbxh >", value, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhGreaterThanOrEqualTo(String value) {
            addCriterion("sbxh >=", value, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhLessThan(String value) {
            addCriterion("sbxh <", value, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhLessThanOrEqualTo(String value) {
            addCriterion("sbxh <=", value, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhLike(String value) {
            addCriterion("sbxh like", value, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhNotLike(String value) {
            addCriterion("sbxh not like", value, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhIn(List<String> values) {
            addCriterion("sbxh in", values, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhNotIn(List<String> values) {
            addCriterion("sbxh not in", values, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhBetween(String value1, String value2) {
            addCriterion("sbxh between", value1, value2, "sbxh");
            return (Criteria) this;
        }

        public Criteria andSbxhNotBetween(String value1, String value2) {
            addCriterion("sbxh not between", value1, value2, "sbxh");
            return (Criteria) this;
        }

        public Criteria andGpsIsNull() {
            addCriterion("gps is null");
            return (Criteria) this;
        }

        public Criteria andGpsIsNotNull() {
            addCriterion("gps is not null");
            return (Criteria) this;
        }

        public Criteria andGpsEqualTo(String value) {
            addCriterion("gps =", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotEqualTo(String value) {
            addCriterion("gps <>", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsGreaterThan(String value) {
            addCriterion("gps >", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsGreaterThanOrEqualTo(String value) {
            addCriterion("gps >=", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsLessThan(String value) {
            addCriterion("gps <", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsLessThanOrEqualTo(String value) {
            addCriterion("gps <=", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsLike(String value) {
            addCriterion("gps like", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotLike(String value) {
            addCriterion("gps not like", value, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsIn(List<String> values) {
            addCriterion("gps in", values, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotIn(List<String> values) {
            addCriterion("gps not in", values, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsBetween(String value1, String value2) {
            addCriterion("gps between", value1, value2, "gps");
            return (Criteria) this;
        }

        public Criteria andGpsNotBetween(String value1, String value2) {
            addCriterion("gps not between", value1, value2, "gps");
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

        public Criteria andFzrdhIsNull() {
            addCriterion("fzrdh is null");
            return (Criteria) this;
        }

        public Criteria andFzrdhIsNotNull() {
            addCriterion("fzrdh is not null");
            return (Criteria) this;
        }

        public Criteria andFzrdhEqualTo(String value) {
            addCriterion("fzrdh =", value, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhNotEqualTo(String value) {
            addCriterion("fzrdh <>", value, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhGreaterThan(String value) {
            addCriterion("fzrdh >", value, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhGreaterThanOrEqualTo(String value) {
            addCriterion("fzrdh >=", value, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhLessThan(String value) {
            addCriterion("fzrdh <", value, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhLessThanOrEqualTo(String value) {
            addCriterion("fzrdh <=", value, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhLike(String value) {
            addCriterion("fzrdh like", value, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhNotLike(String value) {
            addCriterion("fzrdh not like", value, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhIn(List<String> values) {
            addCriterion("fzrdh in", values, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhNotIn(List<String> values) {
            addCriterion("fzrdh not in", values, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhBetween(String value1, String value2) {
            addCriterion("fzrdh between", value1, value2, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andFzrdhNotBetween(String value1, String value2) {
            addCriterion("fzrdh not between", value1, value2, "fzrdh");
            return (Criteria) this;
        }

        public Criteria andSblcIsNull() {
            addCriterion("sblc is null");
            return (Criteria) this;
        }

        public Criteria andSblcIsNotNull() {
            addCriterion("sblc is not null");
            return (Criteria) this;
        }

        public Criteria andSblcEqualTo(String value) {
            addCriterion("sblc =", value, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcNotEqualTo(String value) {
            addCriterion("sblc <>", value, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcGreaterThan(String value) {
            addCriterion("sblc >", value, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcGreaterThanOrEqualTo(String value) {
            addCriterion("sblc >=", value, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcLessThan(String value) {
            addCriterion("sblc <", value, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcLessThanOrEqualTo(String value) {
            addCriterion("sblc <=", value, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcLike(String value) {
            addCriterion("sblc like", value, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcNotLike(String value) {
            addCriterion("sblc not like", value, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcIn(List<String> values) {
            addCriterion("sblc in", values, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcNotIn(List<String> values) {
            addCriterion("sblc not in", values, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcBetween(String value1, String value2) {
            addCriterion("sblc between", value1, value2, "sblc");
            return (Criteria) this;
        }

        public Criteria andSblcNotBetween(String value1, String value2) {
            addCriterion("sblc not between", value1, value2, "sblc");
            return (Criteria) this;
        }

        public Criteria andJdfwIsNull() {
            addCriterion("jdfw is null");
            return (Criteria) this;
        }

        public Criteria andJdfwIsNotNull() {
            addCriterion("jdfw is not null");
            return (Criteria) this;
        }

        public Criteria andJdfwEqualTo(String value) {
            addCriterion("jdfw =", value, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwNotEqualTo(String value) {
            addCriterion("jdfw <>", value, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwGreaterThan(String value) {
            addCriterion("jdfw >", value, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwGreaterThanOrEqualTo(String value) {
            addCriterion("jdfw >=", value, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwLessThan(String value) {
            addCriterion("jdfw <", value, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwLessThanOrEqualTo(String value) {
            addCriterion("jdfw <=", value, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwLike(String value) {
            addCriterion("jdfw like", value, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwNotLike(String value) {
            addCriterion("jdfw not like", value, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwIn(List<String> values) {
            addCriterion("jdfw in", values, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwNotIn(List<String> values) {
            addCriterion("jdfw not in", values, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwBetween(String value1, String value2) {
            addCriterion("jdfw between", value1, value2, "jdfw");
            return (Criteria) this;
        }

        public Criteria andJdfwNotBetween(String value1, String value2) {
            addCriterion("jdfw not between", value1, value2, "jdfw");
            return (Criteria) this;
        }

        public Criteria andFzwzIsNull() {
            addCriterion("fzwz is null");
            return (Criteria) this;
        }

        public Criteria andFzwzIsNotNull() {
            addCriterion("fzwz is not null");
            return (Criteria) this;
        }

        public Criteria andFzwzEqualTo(String value) {
            addCriterion("fzwz =", value, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzNotEqualTo(String value) {
            addCriterion("fzwz <>", value, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzGreaterThan(String value) {
            addCriterion("fzwz >", value, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzGreaterThanOrEqualTo(String value) {
            addCriterion("fzwz >=", value, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzLessThan(String value) {
            addCriterion("fzwz <", value, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzLessThanOrEqualTo(String value) {
            addCriterion("fzwz <=", value, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzLike(String value) {
            addCriterion("fzwz like", value, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzNotLike(String value) {
            addCriterion("fzwz not like", value, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzIn(List<String> values) {
            addCriterion("fzwz in", values, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzNotIn(List<String> values) {
            addCriterion("fzwz not in", values, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzBetween(String value1, String value2) {
            addCriterion("fzwz between", value1, value2, "fzwz");
            return (Criteria) this;
        }

        public Criteria andFzwzNotBetween(String value1, String value2) {
            addCriterion("fzwz not between", value1, value2, "fzwz");
            return (Criteria) this;
        }

        public Criteria andSbcjIsNull() {
            addCriterion("sbcj is null");
            return (Criteria) this;
        }

        public Criteria andSbcjIsNotNull() {
            addCriterion("sbcj is not null");
            return (Criteria) this;
        }

        public Criteria andSbcjEqualTo(String value) {
            addCriterion("sbcj =", value, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjNotEqualTo(String value) {
            addCriterion("sbcj <>", value, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjGreaterThan(String value) {
            addCriterion("sbcj >", value, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjGreaterThanOrEqualTo(String value) {
            addCriterion("sbcj >=", value, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjLessThan(String value) {
            addCriterion("sbcj <", value, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjLessThanOrEqualTo(String value) {
            addCriterion("sbcj <=", value, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjLike(String value) {
            addCriterion("sbcj like", value, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjNotLike(String value) {
            addCriterion("sbcj not like", value, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjIn(List<String> values) {
            addCriterion("sbcj in", values, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjNotIn(List<String> values) {
            addCriterion("sbcj not in", values, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjBetween(String value1, String value2) {
            addCriterion("sbcj between", value1, value2, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbcjNotBetween(String value1, String value2) {
            addCriterion("sbcj not between", value1, value2, "sbcj");
            return (Criteria) this;
        }

        public Criteria andSbztIsNull() {
            addCriterion("sbzt is null");
            return (Criteria) this;
        }

        public Criteria andSbztIsNotNull() {
            addCriterion("sbzt is not null");
            return (Criteria) this;
        }

        public Criteria andSbztEqualTo(String value) {
            addCriterion("sbzt =", value, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztNotEqualTo(String value) {
            addCriterion("sbzt <>", value, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztGreaterThan(String value) {
            addCriterion("sbzt >", value, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztGreaterThanOrEqualTo(String value) {
            addCriterion("sbzt >=", value, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztLessThan(String value) {
            addCriterion("sbzt <", value, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztLessThanOrEqualTo(String value) {
            addCriterion("sbzt <=", value, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztLike(String value) {
            addCriterion("sbzt like", value, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztNotLike(String value) {
            addCriterion("sbzt not like", value, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztIn(List<String> values) {
            addCriterion("sbzt in", values, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztNotIn(List<String> values) {
            addCriterion("sbzt not in", values, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztBetween(String value1, String value2) {
            addCriterion("sbzt between", value1, value2, "sbzt");
            return (Criteria) this;
        }

        public Criteria andSbztNotBetween(String value1, String value2) {
            addCriterion("sbzt not between", value1, value2, "sbzt");
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

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andGxsjIsNull() {
            addCriterion("gxsj is null");
            return (Criteria) this;
        }

        public Criteria andGxsjIsNotNull() {
            addCriterion("gxsj is not null");
            return (Criteria) this;
        }

        public Criteria andGxsjEqualTo(Date value) {
            addCriterion("gxsj =", value, "gxsj");
            return (Criteria) this;
        }

        public Criteria andGxsjNotEqualTo(Date value) {
            addCriterion("gxsj <>", value, "gxsj");
            return (Criteria) this;
        }

        public Criteria andGxsjGreaterThan(Date value) {
            addCriterion("gxsj >", value, "gxsj");
            return (Criteria) this;
        }

        public Criteria andGxsjGreaterThanOrEqualTo(Date value) {
            addCriterion("gxsj >=", value, "gxsj");
            return (Criteria) this;
        }

        public Criteria andGxsjLessThan(Date value) {
            addCriterion("gxsj <", value, "gxsj");
            return (Criteria) this;
        }

        public Criteria andGxsjLessThanOrEqualTo(Date value) {
            addCriterion("gxsj <=", value, "gxsj");
            return (Criteria) this;
        }

        public Criteria andGxsjIn(List<Date> values) {
            addCriterion("gxsj in", values, "gxsj");
            return (Criteria) this;
        }

        public Criteria andGxsjNotIn(List<Date> values) {
            addCriterion("gxsj not in", values, "gxsj");
            return (Criteria) this;
        }

        public Criteria andGxsjBetween(Date value1, Date value2) {
            addCriterion("gxsj between", value1, value2, "gxsj");
            return (Criteria) this;
        }

        public Criteria andGxsjNotBetween(Date value1, Date value2) {
            addCriterion("gxsj not between", value1, value2, "gxsj");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
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