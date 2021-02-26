package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class DrvExamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DrvExamExample() {
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

        public Criteria andKskmIsNull() {
            addCriterion("kskm is null");
            return (Criteria) this;
        }

        public Criteria andKskmIsNotNull() {
            addCriterion("kskm is not null");
            return (Criteria) this;
        }

        public Criteria andKskmEqualTo(String value) {
            addCriterion("kskm =", value, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmNotEqualTo(String value) {
            addCriterion("kskm <>", value, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmGreaterThan(String value) {
            addCriterion("kskm >", value, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmGreaterThanOrEqualTo(String value) {
            addCriterion("kskm >=", value, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmLessThan(String value) {
            addCriterion("kskm <", value, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmLessThanOrEqualTo(String value) {
            addCriterion("kskm <=", value, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmLike(String value) {
            addCriterion("kskm like", value, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmNotLike(String value) {
            addCriterion("kskm not like", value, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmIn(List<String> values) {
            addCriterion("kskm in", values, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmNotIn(List<String> values) {
            addCriterion("kskm not in", values, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmBetween(String value1, String value2) {
            addCriterion("kskm between", value1, value2, "kskm");
            return (Criteria) this;
        }

        public Criteria andKskmNotBetween(String value1, String value2) {
            addCriterion("kskm not between", value1, value2, "kskm");
            return (Criteria) this;
        }

        public Criteria andDtlbIsNull() {
            addCriterion("dtlb is null");
            return (Criteria) this;
        }

        public Criteria andDtlbIsNotNull() {
            addCriterion("dtlb is not null");
            return (Criteria) this;
        }

        public Criteria andDtlbEqualTo(String value) {
            addCriterion("dtlb =", value, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbNotEqualTo(String value) {
            addCriterion("dtlb <>", value, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbGreaterThan(String value) {
            addCriterion("dtlb >", value, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbGreaterThanOrEqualTo(String value) {
            addCriterion("dtlb >=", value, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbLessThan(String value) {
            addCriterion("dtlb <", value, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbLessThanOrEqualTo(String value) {
            addCriterion("dtlb <=", value, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbLike(String value) {
            addCriterion("dtlb like", value, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbNotLike(String value) {
            addCriterion("dtlb not like", value, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbIn(List<String> values) {
            addCriterion("dtlb in", values, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbNotIn(List<String> values) {
            addCriterion("dtlb not in", values, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbBetween(String value1, String value2) {
            addCriterion("dtlb between", value1, value2, "dtlb");
            return (Criteria) this;
        }

        public Criteria andDtlbNotBetween(String value1, String value2) {
            addCriterion("dtlb not between", value1, value2, "dtlb");
            return (Criteria) this;
        }

        public Criteria andTmbtIsNull() {
            addCriterion("tmbt is null");
            return (Criteria) this;
        }

        public Criteria andTmbtIsNotNull() {
            addCriterion("tmbt is not null");
            return (Criteria) this;
        }

        public Criteria andTmbtEqualTo(String value) {
            addCriterion("tmbt =", value, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtNotEqualTo(String value) {
            addCriterion("tmbt <>", value, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtGreaterThan(String value) {
            addCriterion("tmbt >", value, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtGreaterThanOrEqualTo(String value) {
            addCriterion("tmbt >=", value, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtLessThan(String value) {
            addCriterion("tmbt <", value, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtLessThanOrEqualTo(String value) {
            addCriterion("tmbt <=", value, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtLike(String value) {
            addCriterion("tmbt like", value, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtNotLike(String value) {
            addCriterion("tmbt not like", value, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtIn(List<String> values) {
            addCriterion("tmbt in", values, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtNotIn(List<String> values) {
            addCriterion("tmbt not in", values, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtBetween(String value1, String value2) {
            addCriterion("tmbt between", value1, value2, "tmbt");
            return (Criteria) this;
        }

        public Criteria andTmbtNotBetween(String value1, String value2) {
            addCriterion("tmbt not between", value1, value2, "tmbt");
            return (Criteria) this;
        }

        public Criteria andAIsNull() {
            addCriterion("`a` is null");
            return (Criteria) this;
        }

        public Criteria andAIsNotNull() {
            addCriterion("`a` is not null");
            return (Criteria) this;
        }

        public Criteria andAEqualTo(String value) {
            addCriterion("`a` =", value, "a");
            return (Criteria) this;
        }

        public Criteria andANotEqualTo(String value) {
            addCriterion("`a` <>", value, "a");
            return (Criteria) this;
        }

        public Criteria andAGreaterThan(String value) {
            addCriterion("`a` >", value, "a");
            return (Criteria) this;
        }

        public Criteria andAGreaterThanOrEqualTo(String value) {
            addCriterion("`a` >=", value, "a");
            return (Criteria) this;
        }

        public Criteria andALessThan(String value) {
            addCriterion("`a` <", value, "a");
            return (Criteria) this;
        }

        public Criteria andALessThanOrEqualTo(String value) {
            addCriterion("`a` <=", value, "a");
            return (Criteria) this;
        }

        public Criteria andALike(String value) {
            addCriterion("`a` like", value, "a");
            return (Criteria) this;
        }

        public Criteria andANotLike(String value) {
            addCriterion("`a` not like", value, "a");
            return (Criteria) this;
        }

        public Criteria andAIn(List<String> values) {
            addCriterion("`a` in", values, "a");
            return (Criteria) this;
        }

        public Criteria andANotIn(List<String> values) {
            addCriterion("`a` not in", values, "a");
            return (Criteria) this;
        }

        public Criteria andABetween(String value1, String value2) {
            addCriterion("`a` between", value1, value2, "a");
            return (Criteria) this;
        }

        public Criteria andANotBetween(String value1, String value2) {
            addCriterion("`a` not between", value1, value2, "a");
            return (Criteria) this;
        }

        public Criteria andBIsNull() {
            addCriterion("b is null");
            return (Criteria) this;
        }

        public Criteria andBIsNotNull() {
            addCriterion("b is not null");
            return (Criteria) this;
        }

        public Criteria andBEqualTo(String value) {
            addCriterion("b =", value, "b");
            return (Criteria) this;
        }

        public Criteria andBNotEqualTo(String value) {
            addCriterion("b <>", value, "b");
            return (Criteria) this;
        }

        public Criteria andBGreaterThan(String value) {
            addCriterion("b >", value, "b");
            return (Criteria) this;
        }

        public Criteria andBGreaterThanOrEqualTo(String value) {
            addCriterion("b >=", value, "b");
            return (Criteria) this;
        }

        public Criteria andBLessThan(String value) {
            addCriterion("b <", value, "b");
            return (Criteria) this;
        }

        public Criteria andBLessThanOrEqualTo(String value) {
            addCriterion("b <=", value, "b");
            return (Criteria) this;
        }

        public Criteria andBLike(String value) {
            addCriterion("b like", value, "b");
            return (Criteria) this;
        }

        public Criteria andBNotLike(String value) {
            addCriterion("b not like", value, "b");
            return (Criteria) this;
        }

        public Criteria andBIn(List<String> values) {
            addCriterion("b in", values, "b");
            return (Criteria) this;
        }

        public Criteria andBNotIn(List<String> values) {
            addCriterion("b not in", values, "b");
            return (Criteria) this;
        }

        public Criteria andBBetween(String value1, String value2) {
            addCriterion("b between", value1, value2, "b");
            return (Criteria) this;
        }

        public Criteria andBNotBetween(String value1, String value2) {
            addCriterion("b not between", value1, value2, "b");
            return (Criteria) this;
        }

        public Criteria andCIsNull() {
            addCriterion("`c` is null");
            return (Criteria) this;
        }

        public Criteria andCIsNotNull() {
            addCriterion("`c` is not null");
            return (Criteria) this;
        }

        public Criteria andCEqualTo(String value) {
            addCriterion("`c` =", value, "c");
            return (Criteria) this;
        }

        public Criteria andCNotEqualTo(String value) {
            addCriterion("`c` <>", value, "c");
            return (Criteria) this;
        }

        public Criteria andCGreaterThan(String value) {
            addCriterion("`c` >", value, "c");
            return (Criteria) this;
        }

        public Criteria andCGreaterThanOrEqualTo(String value) {
            addCriterion("`c` >=", value, "c");
            return (Criteria) this;
        }

        public Criteria andCLessThan(String value) {
            addCriterion("`c` <", value, "c");
            return (Criteria) this;
        }

        public Criteria andCLessThanOrEqualTo(String value) {
            addCriterion("`c` <=", value, "c");
            return (Criteria) this;
        }

        public Criteria andCLike(String value) {
            addCriterion("`c` like", value, "c");
            return (Criteria) this;
        }

        public Criteria andCNotLike(String value) {
            addCriterion("`c` not like", value, "c");
            return (Criteria) this;
        }

        public Criteria andCIn(List<String> values) {
            addCriterion("`c` in", values, "c");
            return (Criteria) this;
        }

        public Criteria andCNotIn(List<String> values) {
            addCriterion("`c` not in", values, "c");
            return (Criteria) this;
        }

        public Criteria andCBetween(String value1, String value2) {
            addCriterion("`c` between", value1, value2, "c");
            return (Criteria) this;
        }

        public Criteria andCNotBetween(String value1, String value2) {
            addCriterion("`c` not between", value1, value2, "c");
            return (Criteria) this;
        }

        public Criteria andDIsNull() {
            addCriterion("d is null");
            return (Criteria) this;
        }

        public Criteria andDIsNotNull() {
            addCriterion("d is not null");
            return (Criteria) this;
        }

        public Criteria andDEqualTo(String value) {
            addCriterion("d =", value, "d");
            return (Criteria) this;
        }

        public Criteria andDNotEqualTo(String value) {
            addCriterion("d <>", value, "d");
            return (Criteria) this;
        }

        public Criteria andDGreaterThan(String value) {
            addCriterion("d >", value, "d");
            return (Criteria) this;
        }

        public Criteria andDGreaterThanOrEqualTo(String value) {
            addCriterion("d >=", value, "d");
            return (Criteria) this;
        }

        public Criteria andDLessThan(String value) {
            addCriterion("d <", value, "d");
            return (Criteria) this;
        }

        public Criteria andDLessThanOrEqualTo(String value) {
            addCriterion("d <=", value, "d");
            return (Criteria) this;
        }

        public Criteria andDLike(String value) {
            addCriterion("d like", value, "d");
            return (Criteria) this;
        }

        public Criteria andDNotLike(String value) {
            addCriterion("d not like", value, "d");
            return (Criteria) this;
        }

        public Criteria andDIn(List<String> values) {
            addCriterion("d in", values, "d");
            return (Criteria) this;
        }

        public Criteria andDNotIn(List<String> values) {
            addCriterion("d not in", values, "d");
            return (Criteria) this;
        }

        public Criteria andDBetween(String value1, String value2) {
            addCriterion("d between", value1, value2, "d");
            return (Criteria) this;
        }

        public Criteria andDNotBetween(String value1, String value2) {
            addCriterion("d not between", value1, value2, "d");
            return (Criteria) this;
        }

        public Criteria andTp1IsNull() {
            addCriterion("tp1 is null");
            return (Criteria) this;
        }

        public Criteria andTp1IsNotNull() {
            addCriterion("tp1 is not null");
            return (Criteria) this;
        }

        public Criteria andTp1EqualTo(String value) {
            addCriterion("tp1 =", value, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1NotEqualTo(String value) {
            addCriterion("tp1 <>", value, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1GreaterThan(String value) {
            addCriterion("tp1 >", value, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1GreaterThanOrEqualTo(String value) {
            addCriterion("tp1 >=", value, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1LessThan(String value) {
            addCriterion("tp1 <", value, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1LessThanOrEqualTo(String value) {
            addCriterion("tp1 <=", value, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1Like(String value) {
            addCriterion("tp1 like", value, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1NotLike(String value) {
            addCriterion("tp1 not like", value, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1In(List<String> values) {
            addCriterion("tp1 in", values, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1NotIn(List<String> values) {
            addCriterion("tp1 not in", values, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1Between(String value1, String value2) {
            addCriterion("tp1 between", value1, value2, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp1NotBetween(String value1, String value2) {
            addCriterion("tp1 not between", value1, value2, "tp1");
            return (Criteria) this;
        }

        public Criteria andTp2IsNull() {
            addCriterion("tp2 is null");
            return (Criteria) this;
        }

        public Criteria andTp2IsNotNull() {
            addCriterion("tp2 is not null");
            return (Criteria) this;
        }

        public Criteria andTp2EqualTo(String value) {
            addCriterion("tp2 =", value, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2NotEqualTo(String value) {
            addCriterion("tp2 <>", value, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2GreaterThan(String value) {
            addCriterion("tp2 >", value, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2GreaterThanOrEqualTo(String value) {
            addCriterion("tp2 >=", value, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2LessThan(String value) {
            addCriterion("tp2 <", value, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2LessThanOrEqualTo(String value) {
            addCriterion("tp2 <=", value, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2Like(String value) {
            addCriterion("tp2 like", value, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2NotLike(String value) {
            addCriterion("tp2 not like", value, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2In(List<String> values) {
            addCriterion("tp2 in", values, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2NotIn(List<String> values) {
            addCriterion("tp2 not in", values, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2Between(String value1, String value2) {
            addCriterion("tp2 between", value1, value2, "tp2");
            return (Criteria) this;
        }

        public Criteria andTp2NotBetween(String value1, String value2) {
            addCriterion("tp2 not between", value1, value2, "tp2");
            return (Criteria) this;
        }

        public Criteria andZqdaIsNull() {
            addCriterion("zqda is null");
            return (Criteria) this;
        }

        public Criteria andZqdaIsNotNull() {
            addCriterion("zqda is not null");
            return (Criteria) this;
        }

        public Criteria andZqdaEqualTo(String value) {
            addCriterion("zqda =", value, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaNotEqualTo(String value) {
            addCriterion("zqda <>", value, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaGreaterThan(String value) {
            addCriterion("zqda >", value, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaGreaterThanOrEqualTo(String value) {
            addCriterion("zqda >=", value, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaLessThan(String value) {
            addCriterion("zqda <", value, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaLessThanOrEqualTo(String value) {
            addCriterion("zqda <=", value, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaLike(String value) {
            addCriterion("zqda like", value, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaNotLike(String value) {
            addCriterion("zqda not like", value, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaIn(List<String> values) {
            addCriterion("zqda in", values, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaNotIn(List<String> values) {
            addCriterion("zqda not in", values, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaBetween(String value1, String value2) {
            addCriterion("zqda between", value1, value2, "zqda");
            return (Criteria) this;
        }

        public Criteria andZqdaNotBetween(String value1, String value2) {
            addCriterion("zqda not between", value1, value2, "zqda");
            return (Criteria) this;
        }

        public Criteria andDtsmIsNull() {
            addCriterion("dtsm is null");
            return (Criteria) this;
        }

        public Criteria andDtsmIsNotNull() {
            addCriterion("dtsm is not null");
            return (Criteria) this;
        }

        public Criteria andDtsmEqualTo(String value) {
            addCriterion("dtsm =", value, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmNotEqualTo(String value) {
            addCriterion("dtsm <>", value, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmGreaterThan(String value) {
            addCriterion("dtsm >", value, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmGreaterThanOrEqualTo(String value) {
            addCriterion("dtsm >=", value, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmLessThan(String value) {
            addCriterion("dtsm <", value, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmLessThanOrEqualTo(String value) {
            addCriterion("dtsm <=", value, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmLike(String value) {
            addCriterion("dtsm like", value, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmNotLike(String value) {
            addCriterion("dtsm not like", value, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmIn(List<String> values) {
            addCriterion("dtsm in", values, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmNotIn(List<String> values) {
            addCriterion("dtsm not in", values, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmBetween(String value1, String value2) {
            addCriterion("dtsm between", value1, value2, "dtsm");
            return (Criteria) this;
        }

        public Criteria andDtsmNotBetween(String value1, String value2) {
            addCriterion("dtsm not between", value1, value2, "dtsm");
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