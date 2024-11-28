package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class AzimuthAngleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AzimuthAngleExample() {
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

        public Criteria andSbbhIsNull() {
            addCriterion("sbbh is null");
            return (Criteria) this;
        }

        public Criteria andSbbhIsNotNull() {
            addCriterion("sbbh is not null");
            return (Criteria) this;
        }

        public Criteria andSbbhEqualTo(String value) {
            addCriterion("sbbh =", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotEqualTo(String value) {
            addCriterion("sbbh <>", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhGreaterThan(String value) {
            addCriterion("sbbh >", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhGreaterThanOrEqualTo(String value) {
            addCriterion("sbbh >=", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhLessThan(String value) {
            addCriterion("sbbh <", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhLessThanOrEqualTo(String value) {
            addCriterion("sbbh <=", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhLike(String value) {
            addCriterion("sbbh like", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotLike(String value) {
            addCriterion("sbbh not like", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhIn(List<String> values) {
            addCriterion("sbbh in", values, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotIn(List<String> values) {
            addCriterion("sbbh not in", values, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhBetween(String value1, String value2) {
            addCriterion("sbbh between", value1, value2, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotBetween(String value1, String value2) {
            addCriterion("sbbh not between", value1, value2, "sbbh");
            return (Criteria) this;
        }

        public Criteria andRqIsNull() {
            addCriterion("rq is null");
            return (Criteria) this;
        }

        public Criteria andRqIsNotNull() {
            addCriterion("rq is not null");
            return (Criteria) this;
        }

        public Criteria andRqEqualTo(String value) {
            addCriterion("rq =", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotEqualTo(String value) {
            addCriterion("rq <>", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqGreaterThan(String value) {
            addCriterion("rq >", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqGreaterThanOrEqualTo(String value) {
            addCriterion("rq >=", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqLessThan(String value) {
            addCriterion("rq <", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqLessThanOrEqualTo(String value) {
            addCriterion("rq <=", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqLike(String value) {
            addCriterion("rq like", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotLike(String value) {
            addCriterion("rq not like", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqIn(List<String> values) {
            addCriterion("rq in", values, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotIn(List<String> values) {
            addCriterion("rq not in", values, "rq");
            return (Criteria) this;
        }

        public Criteria andRqBetween(String value1, String value2) {
            addCriterion("rq between", value1, value2, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotBetween(String value1, String value2) {
            addCriterion("rq not between", value1, value2, "rq");
            return (Criteria) this;
        }

        public Criteria andXsIsNull() {
            addCriterion("xs is null");
            return (Criteria) this;
        }

        public Criteria andXsIsNotNull() {
            addCriterion("xs is not null");
            return (Criteria) this;
        }

        public Criteria andXsEqualTo(String value) {
            addCriterion("xs =", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotEqualTo(String value) {
            addCriterion("xs <>", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsGreaterThan(String value) {
            addCriterion("xs >", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsGreaterThanOrEqualTo(String value) {
            addCriterion("xs >=", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsLessThan(String value) {
            addCriterion("xs <", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsLessThanOrEqualTo(String value) {
            addCriterion("xs <=", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsLike(String value) {
            addCriterion("xs like", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotLike(String value) {
            addCriterion("xs not like", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsIn(List<String> values) {
            addCriterion("xs in", values, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotIn(List<String> values) {
            addCriterion("xs not in", values, "xs");
            return (Criteria) this;
        }

        public Criteria andXsBetween(String value1, String value2) {
            addCriterion("xs between", value1, value2, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotBetween(String value1, String value2) {
            addCriterion("xs not between", value1, value2, "xs");
            return (Criteria) this;
        }

        public Criteria andFzIsNull() {
            addCriterion("fz is null");
            return (Criteria) this;
        }

        public Criteria andFzIsNotNull() {
            addCriterion("fz is not null");
            return (Criteria) this;
        }

        public Criteria andFzEqualTo(String value) {
            addCriterion("fz =", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotEqualTo(String value) {
            addCriterion("fz <>", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzGreaterThan(String value) {
            addCriterion("fz >", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzGreaterThanOrEqualTo(String value) {
            addCriterion("fz >=", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzLessThan(String value) {
            addCriterion("fz <", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzLessThanOrEqualTo(String value) {
            addCriterion("fz <=", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzLike(String value) {
            addCriterion("fz like", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotLike(String value) {
            addCriterion("fz not like", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzIn(List<String> values) {
            addCriterion("fz in", values, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotIn(List<String> values) {
            addCriterion("fz not in", values, "fz");
            return (Criteria) this;
        }

        public Criteria andFzBetween(String value1, String value2) {
            addCriterion("fz between", value1, value2, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotBetween(String value1, String value2) {
            addCriterion("fz not between", value1, value2, "fz");
            return (Criteria) this;
        }

        public Criteria andTsIsNull() {
            addCriterion("ts is null");
            return (Criteria) this;
        }

        public Criteria andTsIsNotNull() {
            addCriterion("ts is not null");
            return (Criteria) this;
        }

        public Criteria andTsEqualTo(String value) {
            addCriterion("ts =", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotEqualTo(String value) {
            addCriterion("ts <>", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThan(String value) {
            addCriterion("ts >", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThanOrEqualTo(String value) {
            addCriterion("ts >=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThan(String value) {
            addCriterion("ts <", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThanOrEqualTo(String value) {
            addCriterion("ts <=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLike(String value) {
            addCriterion("ts like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotLike(String value) {
            addCriterion("ts not like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsIn(List<String> values) {
            addCriterion("ts in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotIn(List<String> values) {
            addCriterion("ts not in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsBetween(String value1, String value2) {
            addCriterion("ts between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotBetween(String value1, String value2) {
            addCriterion("ts not between", value1, value2, "ts");
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

        public Criteria andNorthNortheastIsNull() {
            addCriterion("north_northeast is null");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastIsNotNull() {
            addCriterion("north_northeast is not null");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastEqualTo(Integer value) {
            addCriterion("north_northeast =", value, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastNotEqualTo(Integer value) {
            addCriterion("north_northeast <>", value, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastGreaterThan(Integer value) {
            addCriterion("north_northeast >", value, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastGreaterThanOrEqualTo(Integer value) {
            addCriterion("north_northeast >=", value, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastLessThan(Integer value) {
            addCriterion("north_northeast <", value, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastLessThanOrEqualTo(Integer value) {
            addCriterion("north_northeast <=", value, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastIn(List<Integer> values) {
            addCriterion("north_northeast in", values, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastNotIn(List<Integer> values) {
            addCriterion("north_northeast not in", values, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastBetween(Integer value1, Integer value2) {
            addCriterion("north_northeast between", value1, value2, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNorthNortheastNotBetween(Integer value1, Integer value2) {
            addCriterion("north_northeast not between", value1, value2, "northNortheast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastIsNull() {
            addCriterion("northeast_east is null");
            return (Criteria) this;
        }

        public Criteria andNortheastEastIsNotNull() {
            addCriterion("northeast_east is not null");
            return (Criteria) this;
        }

        public Criteria andNortheastEastEqualTo(Integer value) {
            addCriterion("northeast_east =", value, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastNotEqualTo(Integer value) {
            addCriterion("northeast_east <>", value, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastGreaterThan(Integer value) {
            addCriterion("northeast_east >", value, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastGreaterThanOrEqualTo(Integer value) {
            addCriterion("northeast_east >=", value, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastLessThan(Integer value) {
            addCriterion("northeast_east <", value, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastLessThanOrEqualTo(Integer value) {
            addCriterion("northeast_east <=", value, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastIn(List<Integer> values) {
            addCriterion("northeast_east in", values, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastNotIn(List<Integer> values) {
            addCriterion("northeast_east not in", values, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastBetween(Integer value1, Integer value2) {
            addCriterion("northeast_east between", value1, value2, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andNortheastEastNotBetween(Integer value1, Integer value2) {
            addCriterion("northeast_east not between", value1, value2, "northeastEast");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthIsNull() {
            addCriterion("east_eastsouth is null");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthIsNotNull() {
            addCriterion("east_eastsouth is not null");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthEqualTo(Integer value) {
            addCriterion("east_eastsouth =", value, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthNotEqualTo(Integer value) {
            addCriterion("east_eastsouth <>", value, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthGreaterThan(Integer value) {
            addCriterion("east_eastsouth >", value, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthGreaterThanOrEqualTo(Integer value) {
            addCriterion("east_eastsouth >=", value, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthLessThan(Integer value) {
            addCriterion("east_eastsouth <", value, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthLessThanOrEqualTo(Integer value) {
            addCriterion("east_eastsouth <=", value, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthIn(List<Integer> values) {
            addCriterion("east_eastsouth in", values, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthNotIn(List<Integer> values) {
            addCriterion("east_eastsouth not in", values, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthBetween(Integer value1, Integer value2) {
            addCriterion("east_eastsouth between", value1, value2, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastEastsouthNotBetween(Integer value1, Integer value2) {
            addCriterion("east_eastsouth not between", value1, value2, "eastEastsouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthIsNull() {
            addCriterion("eastsouth_south is null");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthIsNotNull() {
            addCriterion("eastsouth_south is not null");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthEqualTo(Integer value) {
            addCriterion("eastsouth_south =", value, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthNotEqualTo(Integer value) {
            addCriterion("eastsouth_south <>", value, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthGreaterThan(Integer value) {
            addCriterion("eastsouth_south >", value, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthGreaterThanOrEqualTo(Integer value) {
            addCriterion("eastsouth_south >=", value, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthLessThan(Integer value) {
            addCriterion("eastsouth_south <", value, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthLessThanOrEqualTo(Integer value) {
            addCriterion("eastsouth_south <=", value, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthIn(List<Integer> values) {
            addCriterion("eastsouth_south in", values, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthNotIn(List<Integer> values) {
            addCriterion("eastsouth_south not in", values, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthBetween(Integer value1, Integer value2) {
            addCriterion("eastsouth_south between", value1, value2, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andEastsouthSouthNotBetween(Integer value1, Integer value2) {
            addCriterion("eastsouth_south not between", value1, value2, "eastsouthSouth");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestIsNull() {
            addCriterion("south_southwest is null");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestIsNotNull() {
            addCriterion("south_southwest is not null");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestEqualTo(Integer value) {
            addCriterion("south_southwest =", value, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestNotEqualTo(Integer value) {
            addCriterion("south_southwest <>", value, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestGreaterThan(Integer value) {
            addCriterion("south_southwest >", value, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestGreaterThanOrEqualTo(Integer value) {
            addCriterion("south_southwest >=", value, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestLessThan(Integer value) {
            addCriterion("south_southwest <", value, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestLessThanOrEqualTo(Integer value) {
            addCriterion("south_southwest <=", value, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestIn(List<Integer> values) {
            addCriterion("south_southwest in", values, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestNotIn(List<Integer> values) {
            addCriterion("south_southwest not in", values, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestBetween(Integer value1, Integer value2) {
            addCriterion("south_southwest between", value1, value2, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthSouthwestNotBetween(Integer value1, Integer value2) {
            addCriterion("south_southwest not between", value1, value2, "southSouthwest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestIsNull() {
            addCriterion("southwest_west is null");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestIsNotNull() {
            addCriterion("southwest_west is not null");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestEqualTo(Integer value) {
            addCriterion("southwest_west =", value, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestNotEqualTo(Integer value) {
            addCriterion("southwest_west <>", value, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestGreaterThan(Integer value) {
            addCriterion("southwest_west >", value, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestGreaterThanOrEqualTo(Integer value) {
            addCriterion("southwest_west >=", value, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestLessThan(Integer value) {
            addCriterion("southwest_west <", value, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestLessThanOrEqualTo(Integer value) {
            addCriterion("southwest_west <=", value, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestIn(List<Integer> values) {
            addCriterion("southwest_west in", values, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestNotIn(List<Integer> values) {
            addCriterion("southwest_west not in", values, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestBetween(Integer value1, Integer value2) {
            addCriterion("southwest_west between", value1, value2, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andSouthwestWestNotBetween(Integer value1, Integer value2) {
            addCriterion("southwest_west not between", value1, value2, "southwestWest");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthIsNull() {
            addCriterion("west_westnorth is null");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthIsNotNull() {
            addCriterion("west_westnorth is not null");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthEqualTo(Integer value) {
            addCriterion("west_westnorth =", value, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthNotEqualTo(Integer value) {
            addCriterion("west_westnorth <>", value, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthGreaterThan(Integer value) {
            addCriterion("west_westnorth >", value, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthGreaterThanOrEqualTo(Integer value) {
            addCriterion("west_westnorth >=", value, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthLessThan(Integer value) {
            addCriterion("west_westnorth <", value, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthLessThanOrEqualTo(Integer value) {
            addCriterion("west_westnorth <=", value, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthIn(List<Integer> values) {
            addCriterion("west_westnorth in", values, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthNotIn(List<Integer> values) {
            addCriterion("west_westnorth not in", values, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthBetween(Integer value1, Integer value2) {
            addCriterion("west_westnorth between", value1, value2, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestWestnorthNotBetween(Integer value1, Integer value2) {
            addCriterion("west_westnorth not between", value1, value2, "westWestnorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthIsNull() {
            addCriterion("westnorth_north is null");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthIsNotNull() {
            addCriterion("westnorth_north is not null");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthEqualTo(Integer value) {
            addCriterion("westnorth_north =", value, "westnorthNorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthNotEqualTo(Integer value) {
            addCriterion("westnorth_north <>", value, "westnorthNorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthGreaterThan(Integer value) {
            addCriterion("westnorth_north >", value, "westnorthNorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthGreaterThanOrEqualTo(Integer value) {
            addCriterion("westnorth_north >=", value, "westnorthNorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthLessThan(Integer value) {
            addCriterion("westnorth_north <", value, "westnorthNorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthLessThanOrEqualTo(Integer value) {
            addCriterion("westnorth_north <=", value, "westnorthNorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthIn(List<Integer> values) {
            addCriterion("westnorth_north in", values, "westnorthNorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthNotIn(List<Integer> values) {
            addCriterion("westnorth_north not in", values, "westnorthNorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthBetween(Integer value1, Integer value2) {
            addCriterion("westnorth_north between", value1, value2, "westnorthNorth");
            return (Criteria) this;
        }

        public Criteria andWestnorthNorthNotBetween(Integer value1, Integer value2) {
            addCriterion("westnorth_north not between", value1, value2, "westnorthNorth");
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