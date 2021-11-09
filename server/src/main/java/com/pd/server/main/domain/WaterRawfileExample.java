package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterRawfileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WaterRawfileExample() {
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

        public Criteria andXmbhIsNull() {
            addCriterion("xmbh is null");
            return (Criteria) this;
        }

        public Criteria andXmbhIsNotNull() {
            addCriterion("xmbh is not null");
            return (Criteria) this;
        }

        public Criteria andXmbhEqualTo(String value) {
            addCriterion("xmbh =", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhNotEqualTo(String value) {
            addCriterion("xmbh <>", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhGreaterThan(String value) {
            addCriterion("xmbh >", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhGreaterThanOrEqualTo(String value) {
            addCriterion("xmbh >=", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhLessThan(String value) {
            addCriterion("xmbh <", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhLessThanOrEqualTo(String value) {
            addCriterion("xmbh <=", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhLike(String value) {
            addCriterion("xmbh like", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhNotLike(String value) {
            addCriterion("xmbh not like", value, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhIn(List<String> values) {
            addCriterion("xmbh in", values, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhNotIn(List<String> values) {
            addCriterion("xmbh not in", values, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhBetween(String value1, String value2) {
            addCriterion("xmbh between", value1, value2, "xmbh");
            return (Criteria) this;
        }

        public Criteria andXmbhNotBetween(String value1, String value2) {
            addCriterion("xmbh not between", value1, value2, "xmbh");
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

        public Criteria andWjlxIsNull() {
            addCriterion("wjlx is null");
            return (Criteria) this;
        }

        public Criteria andWjlxIsNotNull() {
            addCriterion("wjlx is not null");
            return (Criteria) this;
        }

        public Criteria andWjlxEqualTo(String value) {
            addCriterion("wjlx =", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotEqualTo(String value) {
            addCriterion("wjlx <>", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxGreaterThan(String value) {
            addCriterion("wjlx >", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxGreaterThanOrEqualTo(String value) {
            addCriterion("wjlx >=", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxLessThan(String value) {
            addCriterion("wjlx <", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxLessThanOrEqualTo(String value) {
            addCriterion("wjlx <=", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxLike(String value) {
            addCriterion("wjlx like", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotLike(String value) {
            addCriterion("wjlx not like", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxIn(List<String> values) {
            addCriterion("wjlx in", values, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotIn(List<String> values) {
            addCriterion("wjlx not in", values, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxBetween(String value1, String value2) {
            addCriterion("wjlx between", value1, value2, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotBetween(String value1, String value2) {
            addCriterion("wjlx not between", value1, value2, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjljIsNull() {
            addCriterion("wjlj is null");
            return (Criteria) this;
        }

        public Criteria andWjljIsNotNull() {
            addCriterion("wjlj is not null");
            return (Criteria) this;
        }

        public Criteria andWjljEqualTo(String value) {
            addCriterion("wjlj =", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljNotEqualTo(String value) {
            addCriterion("wjlj <>", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljGreaterThan(String value) {
            addCriterion("wjlj >", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljGreaterThanOrEqualTo(String value) {
            addCriterion("wjlj >=", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljLessThan(String value) {
            addCriterion("wjlj <", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljLessThanOrEqualTo(String value) {
            addCriterion("wjlj <=", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljLike(String value) {
            addCriterion("wjlj like", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljNotLike(String value) {
            addCriterion("wjlj not like", value, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljIn(List<String> values) {
            addCriterion("wjlj in", values, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljNotIn(List<String> values) {
            addCriterion("wjlj not in", values, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljBetween(String value1, String value2) {
            addCriterion("wjlj between", value1, value2, "wjlj");
            return (Criteria) this;
        }

        public Criteria andWjljNotBetween(String value1, String value2) {
            addCriterion("wjlj not between", value1, value2, "wjlj");
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

        public Criteria andCjsjLessThan(Date value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(Date value) {
            addCriterion("cjsj <=", value, "cjsj");
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

        public Criteria andFxcsIsNull() {
            addCriterion("fxcs is null");
            return (Criteria) this;
        }

        public Criteria andFxcsIsNotNull() {
            addCriterion("fxcs is not null");
            return (Criteria) this;
        }

        public Criteria andFxcsEqualTo(Integer value) {
            addCriterion("fxcs =", value, "fxcs");
            return (Criteria) this;
        }

        public Criteria andFxcsNotEqualTo(Integer value) {
            addCriterion("fxcs <>", value, "fxcs");
            return (Criteria) this;
        }

        public Criteria andFxcsGreaterThan(Integer value) {
            addCriterion("fxcs >", value, "fxcs");
            return (Criteria) this;
        }

        public Criteria andFxcsGreaterThanOrEqualTo(Integer value) {
            addCriterion("fxcs >=", value, "fxcs");
            return (Criteria) this;
        }

        public Criteria andFxcsLessThan(Integer value) {
            addCriterion("fxcs <", value, "fxcs");
            return (Criteria) this;
        }

        public Criteria andFxcsLessThanOrEqualTo(Integer value) {
            addCriterion("fxcs <=", value, "fxcs");
            return (Criteria) this;
        }

        public Criteria andFxcsIn(List<Integer> values) {
            addCriterion("fxcs in", values, "fxcs");
            return (Criteria) this;
        }

        public Criteria andFxcsNotIn(List<Integer> values) {
            addCriterion("fxcs not in", values, "fxcs");
            return (Criteria) this;
        }

        public Criteria andFxcsBetween(Integer value1, Integer value2) {
            addCriterion("fxcs between", value1, value2, "fxcs");
            return (Criteria) this;
        }

        public Criteria andFxcsNotBetween(Integer value1, Integer value2) {
            addCriterion("fxcs not between", value1, value2, "fxcs");
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

        public Criteria andSm5IsNull() {
            addCriterion("sm5 is null");
            return (Criteria) this;
        }

        public Criteria andSm5IsNotNull() {
            addCriterion("sm5 is not null");
            return (Criteria) this;
        }

        public Criteria andSm5EqualTo(String value) {
            addCriterion("sm5 =", value, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5NotEqualTo(String value) {
            addCriterion("sm5 <>", value, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5GreaterThan(String value) {
            addCriterion("sm5 >", value, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5GreaterThanOrEqualTo(String value) {
            addCriterion("sm5 >=", value, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5LessThan(String value) {
            addCriterion("sm5 <", value, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5LessThanOrEqualTo(String value) {
            addCriterion("sm5 <=", value, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5Like(String value) {
            addCriterion("sm5 like", value, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5NotLike(String value) {
            addCriterion("sm5 not like", value, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5In(List<String> values) {
            addCriterion("sm5 in", values, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5NotIn(List<String> values) {
            addCriterion("sm5 not in", values, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5Between(String value1, String value2) {
            addCriterion("sm5 between", value1, value2, "sm5");
            return (Criteria) this;
        }

        public Criteria andSm5NotBetween(String value1, String value2) {
            addCriterion("sm5 not between", value1, value2, "sm5");
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