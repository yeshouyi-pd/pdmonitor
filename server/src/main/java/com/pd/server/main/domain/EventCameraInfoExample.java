package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventCameraInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EventCameraInfoExample() {
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

        public Criteria andDvripIsNull() {
            addCriterion("dvrip is null");
            return (Criteria) this;
        }

        public Criteria andDvripIsNotNull() {
            addCriterion("dvrip is not null");
            return (Criteria) this;
        }

        public Criteria andDvripEqualTo(String value) {
            addCriterion("dvrip =", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripNotEqualTo(String value) {
            addCriterion("dvrip <>", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripGreaterThan(String value) {
            addCriterion("dvrip >", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripGreaterThanOrEqualTo(String value) {
            addCriterion("dvrip >=", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripLessThan(String value) {
            addCriterion("dvrip <", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripLessThanOrEqualTo(String value) {
            addCriterion("dvrip <=", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripLike(String value) {
            addCriterion("dvrip like", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripNotLike(String value) {
            addCriterion("dvrip not like", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripIn(List<String> values) {
            addCriterion("dvrip in", values, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripNotIn(List<String> values) {
            addCriterion("dvrip not in", values, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripBetween(String value1, String value2) {
            addCriterion("dvrip between", value1, value2, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripNotBetween(String value1, String value2) {
            addCriterion("dvrip not between", value1, value2, "dvrip");
            return (Criteria) this;
        }

        public Criteria andTdhIsNull() {
            addCriterion("tdh is null");
            return (Criteria) this;
        }

        public Criteria andTdhIsNotNull() {
            addCriterion("tdh is not null");
            return (Criteria) this;
        }

        public Criteria andTdhEqualTo(Integer value) {
            addCriterion("tdh =", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotEqualTo(Integer value) {
            addCriterion("tdh <>", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhGreaterThan(Integer value) {
            addCriterion("tdh >", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhGreaterThanOrEqualTo(Integer value) {
            addCriterion("tdh >=", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhLessThan(Integer value) {
            addCriterion("tdh <", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhLessThanOrEqualTo(Integer value) {
            addCriterion("tdh <=", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhIn(List<Integer> values) {
            addCriterion("tdh in", values, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotIn(List<Integer> values) {
            addCriterion("tdh not in", values, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhBetween(Integer value1, Integer value2) {
            addCriterion("tdh between", value1, value2, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotBetween(Integer value1, Integer value2) {
            addCriterion("tdh not between", value1, value2, "tdh");
            return (Criteria) this;
        }

        public Criteria andSxtbhIsNull() {
            addCriterion("sxtbh is null");
            return (Criteria) this;
        }

        public Criteria andSxtbhIsNotNull() {
            addCriterion("sxtbh is not null");
            return (Criteria) this;
        }

        public Criteria andSxtbhEqualTo(String value) {
            addCriterion("sxtbh =", value, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhNotEqualTo(String value) {
            addCriterion("sxtbh <>", value, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhGreaterThan(String value) {
            addCriterion("sxtbh >", value, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhGreaterThanOrEqualTo(String value) {
            addCriterion("sxtbh >=", value, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhLessThan(String value) {
            addCriterion("sxtbh <", value, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhLessThanOrEqualTo(String value) {
            addCriterion("sxtbh <=", value, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhLike(String value) {
            addCriterion("sxtbh like", value, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhNotLike(String value) {
            addCriterion("sxtbh not like", value, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhIn(List<String> values) {
            addCriterion("sxtbh in", values, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhNotIn(List<String> values) {
            addCriterion("sxtbh not in", values, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhBetween(String value1, String value2) {
            addCriterion("sxtbh between", value1, value2, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andSxtbhNotBetween(String value1, String value2) {
            addCriterion("sxtbh not between", value1, value2, "sxtbh");
            return (Criteria) this;
        }

        public Criteria andKssjIsNull() {
            addCriterion("kssj is null");
            return (Criteria) this;
        }

        public Criteria andKssjIsNotNull() {
            addCriterion("kssj is not null");
            return (Criteria) this;
        }

        public Criteria andKssjEqualTo(Date value) {
            addCriterion("kssj =", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotEqualTo(Date value) {
            addCriterion("kssj <>", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjGreaterThan(Date value) {
            addCriterion("kssj >", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjGreaterThanOrEqualTo(Date value) {
            addCriterion("kssj >=", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjLessThan(Date value) {
            addCriterion("kssj <", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjLessThanOrEqualTo(Date value) {
            addCriterion("kssj <=", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjIn(List<Date> values) {
            addCriterion("kssj in", values, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotIn(List<Date> values) {
            addCriterion("kssj not in", values, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjBetween(Date value1, Date value2) {
            addCriterion("kssj between", value1, value2, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotBetween(Date value1, Date value2) {
            addCriterion("kssj not between", value1, value2, "kssj");
            return (Criteria) this;
        }

        public Criteria andJssjIsNull() {
            addCriterion("jssj is null");
            return (Criteria) this;
        }

        public Criteria andJssjIsNotNull() {
            addCriterion("jssj is not null");
            return (Criteria) this;
        }

        public Criteria andJssjEqualTo(Date value) {
            addCriterion("jssj =", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotEqualTo(Date value) {
            addCriterion("jssj <>", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjGreaterThan(Date value) {
            addCriterion("jssj >", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjGreaterThanOrEqualTo(Date value) {
            addCriterion("jssj >=", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjLessThan(Date value) {
            addCriterion("jssj <", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjLessThanOrEqualTo(Date value) {
            addCriterion("jssj <=", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjIn(List<Date> values) {
            addCriterion("jssj in", values, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotIn(List<Date> values) {
            addCriterion("jssj not in", values, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjBetween(Date value1, Date value2) {
            addCriterion("jssj between", value1, value2, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotBetween(Date value1, Date value2) {
            addCriterion("jssj not between", value1, value2, "jssj");
            return (Criteria) this;
        }

        public Criteria andZtIsNull() {
            addCriterion("zt is null");
            return (Criteria) this;
        }

        public Criteria andZtIsNotNull() {
            addCriterion("zt is not null");
            return (Criteria) this;
        }

        public Criteria andZtEqualTo(String value) {
            addCriterion("zt =", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotEqualTo(String value) {
            addCriterion("zt <>", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThan(String value) {
            addCriterion("zt >", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThanOrEqualTo(String value) {
            addCriterion("zt >=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThan(String value) {
            addCriterion("zt <", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThanOrEqualTo(String value) {
            addCriterion("zt <=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLike(String value) {
            addCriterion("zt like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotLike(String value) {
            addCriterion("zt not like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtIn(List<String> values) {
            addCriterion("zt in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotIn(List<String> values) {
            addCriterion("zt not in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtBetween(String value1, String value2) {
            addCriterion("zt between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotBetween(String value1, String value2) {
            addCriterion("zt not between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andDzIsNull() {
            addCriterion("dz is null");
            return (Criteria) this;
        }

        public Criteria andDzIsNotNull() {
            addCriterion("dz is not null");
            return (Criteria) this;
        }

        public Criteria andDzEqualTo(String value) {
            addCriterion("dz =", value, "dz");
            return (Criteria) this;
        }

        public Criteria andDzNotEqualTo(String value) {
            addCriterion("dz <>", value, "dz");
            return (Criteria) this;
        }

        public Criteria andDzGreaterThan(String value) {
            addCriterion("dz >", value, "dz");
            return (Criteria) this;
        }

        public Criteria andDzGreaterThanOrEqualTo(String value) {
            addCriterion("dz >=", value, "dz");
            return (Criteria) this;
        }

        public Criteria andDzLessThan(String value) {
            addCriterion("dz <", value, "dz");
            return (Criteria) this;
        }

        public Criteria andDzLessThanOrEqualTo(String value) {
            addCriterion("dz <=", value, "dz");
            return (Criteria) this;
        }

        public Criteria andDzLike(String value) {
            addCriterion("dz like", value, "dz");
            return (Criteria) this;
        }

        public Criteria andDzNotLike(String value) {
            addCriterion("dz not like", value, "dz");
            return (Criteria) this;
        }

        public Criteria andDzIn(List<String> values) {
            addCriterion("dz in", values, "dz");
            return (Criteria) this;
        }

        public Criteria andDzNotIn(List<String> values) {
            addCriterion("dz not in", values, "dz");
            return (Criteria) this;
        }

        public Criteria andDzBetween(String value1, String value2) {
            addCriterion("dz between", value1, value2, "dz");
            return (Criteria) this;
        }

        public Criteria andDzNotBetween(String value1, String value2) {
            addCriterion("dz not between", value1, value2, "dz");
            return (Criteria) this;
        }

        public Criteria andSjidIsNull() {
            addCriterion("sjid is null");
            return (Criteria) this;
        }

        public Criteria andSjidIsNotNull() {
            addCriterion("sjid is not null");
            return (Criteria) this;
        }

        public Criteria andSjidEqualTo(String value) {
            addCriterion("sjid =", value, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidNotEqualTo(String value) {
            addCriterion("sjid <>", value, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidGreaterThan(String value) {
            addCriterion("sjid >", value, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidGreaterThanOrEqualTo(String value) {
            addCriterion("sjid >=", value, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidLessThan(String value) {
            addCriterion("sjid <", value, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidLessThanOrEqualTo(String value) {
            addCriterion("sjid <=", value, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidLike(String value) {
            addCriterion("sjid like", value, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidNotLike(String value) {
            addCriterion("sjid not like", value, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidIn(List<String> values) {
            addCriterion("sjid in", values, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidNotIn(List<String> values) {
            addCriterion("sjid not in", values, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidBetween(String value1, String value2) {
            addCriterion("sjid between", value1, value2, "sjid");
            return (Criteria) this;
        }

        public Criteria andSjidNotBetween(String value1, String value2) {
            addCriterion("sjid not between", value1, value2, "sjid");
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