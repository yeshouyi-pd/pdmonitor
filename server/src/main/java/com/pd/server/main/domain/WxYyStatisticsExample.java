package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class WxYyStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WxYyStatisticsExample() {
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

        public Criteria andYwflIsNull() {
            addCriterion("ywfl is null");
            return (Criteria) this;
        }

        public Criteria andYwflIsNotNull() {
            addCriterion("ywfl is not null");
            return (Criteria) this;
        }

        public Criteria andYwflEqualTo(String value) {
            addCriterion("ywfl =", value, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflNotEqualTo(String value) {
            addCriterion("ywfl <>", value, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflGreaterThan(String value) {
            addCriterion("ywfl >", value, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflGreaterThanOrEqualTo(String value) {
            addCriterion("ywfl >=", value, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflLessThan(String value) {
            addCriterion("ywfl <", value, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflLessThanOrEqualTo(String value) {
            addCriterion("ywfl <=", value, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflLike(String value) {
            addCriterion("ywfl like", value, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflNotLike(String value) {
            addCriterion("ywfl not like", value, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflIn(List<String> values) {
            addCriterion("ywfl in", values, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflNotIn(List<String> values) {
            addCriterion("ywfl not in", values, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflBetween(String value1, String value2) {
            addCriterion("ywfl between", value1, value2, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwflNotBetween(String value1, String value2) {
            addCriterion("ywfl not between", value1, value2, "ywfl");
            return (Criteria) this;
        }

        public Criteria andYwlxIsNull() {
            addCriterion("ywlx is null");
            return (Criteria) this;
        }

        public Criteria andYwlxIsNotNull() {
            addCriterion("ywlx is not null");
            return (Criteria) this;
        }

        public Criteria andYwlxEqualTo(String value) {
            addCriterion("ywlx =", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxNotEqualTo(String value) {
            addCriterion("ywlx <>", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxGreaterThan(String value) {
            addCriterion("ywlx >", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxGreaterThanOrEqualTo(String value) {
            addCriterion("ywlx >=", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxLessThan(String value) {
            addCriterion("ywlx <", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxLessThanOrEqualTo(String value) {
            addCriterion("ywlx <=", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxLike(String value) {
            addCriterion("ywlx like", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxNotLike(String value) {
            addCriterion("ywlx not like", value, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxIn(List<String> values) {
            addCriterion("ywlx in", values, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxNotIn(List<String> values) {
            addCriterion("ywlx not in", values, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxBetween(String value1, String value2) {
            addCriterion("ywlx between", value1, value2, "ywlx");
            return (Criteria) this;
        }

        public Criteria andYwlxNotBetween(String value1, String value2) {
            addCriterion("ywlx not between", value1, value2, "ywlx");
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

        public Criteria andNfIsNull() {
            addCriterion("nf is null");
            return (Criteria) this;
        }

        public Criteria andNfIsNotNull() {
            addCriterion("nf is not null");
            return (Criteria) this;
        }

        public Criteria andNfEqualTo(String value) {
            addCriterion("nf =", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfNotEqualTo(String value) {
            addCriterion("nf <>", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfGreaterThan(String value) {
            addCriterion("nf >", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfGreaterThanOrEqualTo(String value) {
            addCriterion("nf >=", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfLessThan(String value) {
            addCriterion("nf <", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfLessThanOrEqualTo(String value) {
            addCriterion("nf <=", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfLike(String value) {
            addCriterion("nf like", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfNotLike(String value) {
            addCriterion("nf not like", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfIn(List<String> values) {
            addCriterion("nf in", values, "nf");
            return (Criteria) this;
        }

        public Criteria andNfNotIn(List<String> values) {
            addCriterion("nf not in", values, "nf");
            return (Criteria) this;
        }

        public Criteria andNfBetween(String value1, String value2) {
            addCriterion("nf between", value1, value2, "nf");
            return (Criteria) this;
        }

        public Criteria andNfNotBetween(String value1, String value2) {
            addCriterion("nf not between", value1, value2, "nf");
            return (Criteria) this;
        }

        public Criteria andYfIsNull() {
            addCriterion("yf is null");
            return (Criteria) this;
        }

        public Criteria andYfIsNotNull() {
            addCriterion("yf is not null");
            return (Criteria) this;
        }

        public Criteria andYfEqualTo(String value) {
            addCriterion("yf =", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfNotEqualTo(String value) {
            addCriterion("yf <>", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfGreaterThan(String value) {
            addCriterion("yf >", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfGreaterThanOrEqualTo(String value) {
            addCriterion("yf >=", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfLessThan(String value) {
            addCriterion("yf <", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfLessThanOrEqualTo(String value) {
            addCriterion("yf <=", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfLike(String value) {
            addCriterion("yf like", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfNotLike(String value) {
            addCriterion("yf not like", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfIn(List<String> values) {
            addCriterion("yf in", values, "yf");
            return (Criteria) this;
        }

        public Criteria andYfNotIn(List<String> values) {
            addCriterion("yf not in", values, "yf");
            return (Criteria) this;
        }

        public Criteria andYfBetween(String value1, String value2) {
            addCriterion("yf between", value1, value2, "yf");
            return (Criteria) this;
        }

        public Criteria andYfNotBetween(String value1, String value2) {
            addCriterion("yf not between", value1, value2, "yf");
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

        public Criteria andYyslIsNull() {
            addCriterion("yysl is null");
            return (Criteria) this;
        }

        public Criteria andYyslIsNotNull() {
            addCriterion("yysl is not null");
            return (Criteria) this;
        }

        public Criteria andYyslEqualTo(Integer value) {
            addCriterion("yysl =", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslNotEqualTo(Integer value) {
            addCriterion("yysl <>", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslGreaterThan(Integer value) {
            addCriterion("yysl >", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslGreaterThanOrEqualTo(Integer value) {
            addCriterion("yysl >=", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslLessThan(Integer value) {
            addCriterion("yysl <", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslLessThanOrEqualTo(Integer value) {
            addCriterion("yysl <=", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslIn(List<Integer> values) {
            addCriterion("yysl in", values, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslNotIn(List<Integer> values) {
            addCriterion("yysl not in", values, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslBetween(Integer value1, Integer value2) {
            addCriterion("yysl between", value1, value2, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslNotBetween(Integer value1, Integer value2) {
            addCriterion("yysl not between", value1, value2, "yysl");
            return (Criteria) this;
        }

        public Criteria andBzzd1IsNull() {
            addCriterion("bzzd1 is null");
            return (Criteria) this;
        }

        public Criteria andBzzd1IsNotNull() {
            addCriterion("bzzd1 is not null");
            return (Criteria) this;
        }

        public Criteria andBzzd1EqualTo(String value) {
            addCriterion("bzzd1 =", value, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1NotEqualTo(String value) {
            addCriterion("bzzd1 <>", value, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1GreaterThan(String value) {
            addCriterion("bzzd1 >", value, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1GreaterThanOrEqualTo(String value) {
            addCriterion("bzzd1 >=", value, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1LessThan(String value) {
            addCriterion("bzzd1 <", value, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1LessThanOrEqualTo(String value) {
            addCriterion("bzzd1 <=", value, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1Like(String value) {
            addCriterion("bzzd1 like", value, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1NotLike(String value) {
            addCriterion("bzzd1 not like", value, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1In(List<String> values) {
            addCriterion("bzzd1 in", values, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1NotIn(List<String> values) {
            addCriterion("bzzd1 not in", values, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1Between(String value1, String value2) {
            addCriterion("bzzd1 between", value1, value2, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd1NotBetween(String value1, String value2) {
            addCriterion("bzzd1 not between", value1, value2, "bzzd1");
            return (Criteria) this;
        }

        public Criteria andBzzd2IsNull() {
            addCriterion("bzzd2 is null");
            return (Criteria) this;
        }

        public Criteria andBzzd2IsNotNull() {
            addCriterion("bzzd2 is not null");
            return (Criteria) this;
        }

        public Criteria andBzzd2EqualTo(String value) {
            addCriterion("bzzd2 =", value, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2NotEqualTo(String value) {
            addCriterion("bzzd2 <>", value, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2GreaterThan(String value) {
            addCriterion("bzzd2 >", value, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2GreaterThanOrEqualTo(String value) {
            addCriterion("bzzd2 >=", value, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2LessThan(String value) {
            addCriterion("bzzd2 <", value, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2LessThanOrEqualTo(String value) {
            addCriterion("bzzd2 <=", value, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2Like(String value) {
            addCriterion("bzzd2 like", value, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2NotLike(String value) {
            addCriterion("bzzd2 not like", value, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2In(List<String> values) {
            addCriterion("bzzd2 in", values, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2NotIn(List<String> values) {
            addCriterion("bzzd2 not in", values, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2Between(String value1, String value2) {
            addCriterion("bzzd2 between", value1, value2, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd2NotBetween(String value1, String value2) {
            addCriterion("bzzd2 not between", value1, value2, "bzzd2");
            return (Criteria) this;
        }

        public Criteria andBzzd3IsNull() {
            addCriterion("bzzd3 is null");
            return (Criteria) this;
        }

        public Criteria andBzzd3IsNotNull() {
            addCriterion("bzzd3 is not null");
            return (Criteria) this;
        }

        public Criteria andBzzd3EqualTo(String value) {
            addCriterion("bzzd3 =", value, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3NotEqualTo(String value) {
            addCriterion("bzzd3 <>", value, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3GreaterThan(String value) {
            addCriterion("bzzd3 >", value, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3GreaterThanOrEqualTo(String value) {
            addCriterion("bzzd3 >=", value, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3LessThan(String value) {
            addCriterion("bzzd3 <", value, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3LessThanOrEqualTo(String value) {
            addCriterion("bzzd3 <=", value, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3Like(String value) {
            addCriterion("bzzd3 like", value, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3NotLike(String value) {
            addCriterion("bzzd3 not like", value, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3In(List<String> values) {
            addCriterion("bzzd3 in", values, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3NotIn(List<String> values) {
            addCriterion("bzzd3 not in", values, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3Between(String value1, String value2) {
            addCriterion("bzzd3 between", value1, value2, "bzzd3");
            return (Criteria) this;
        }

        public Criteria andBzzd3NotBetween(String value1, String value2) {
            addCriterion("bzzd3 not between", value1, value2, "bzzd3");
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