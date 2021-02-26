package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class DeptYwlxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeptYwlxExample() {
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

        public Criteria andBslcIsNull() {
            addCriterion("bslc is null");
            return (Criteria) this;
        }

        public Criteria andBslcIsNotNull() {
            addCriterion("bslc is not null");
            return (Criteria) this;
        }

        public Criteria andBslcEqualTo(String value) {
            addCriterion("bslc =", value, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcNotEqualTo(String value) {
            addCriterion("bslc <>", value, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcGreaterThan(String value) {
            addCriterion("bslc >", value, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcGreaterThanOrEqualTo(String value) {
            addCriterion("bslc >=", value, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcLessThan(String value) {
            addCriterion("bslc <", value, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcLessThanOrEqualTo(String value) {
            addCriterion("bslc <=", value, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcLike(String value) {
            addCriterion("bslc like", value, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcNotLike(String value) {
            addCriterion("bslc not like", value, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcIn(List<String> values) {
            addCriterion("bslc in", values, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcNotIn(List<String> values) {
            addCriterion("bslc not in", values, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcBetween(String value1, String value2) {
            addCriterion("bslc between", value1, value2, "bslc");
            return (Criteria) this;
        }

        public Criteria andBslcNotBetween(String value1, String value2) {
            addCriterion("bslc not between", value1, value2, "bslc");
            return (Criteria) this;
        }

        public Criteria andSxzlIsNull() {
            addCriterion("sxzl is null");
            return (Criteria) this;
        }

        public Criteria andSxzlIsNotNull() {
            addCriterion("sxzl is not null");
            return (Criteria) this;
        }

        public Criteria andSxzlEqualTo(String value) {
            addCriterion("sxzl =", value, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlNotEqualTo(String value) {
            addCriterion("sxzl <>", value, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlGreaterThan(String value) {
            addCriterion("sxzl >", value, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlGreaterThanOrEqualTo(String value) {
            addCriterion("sxzl >=", value, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlLessThan(String value) {
            addCriterion("sxzl <", value, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlLessThanOrEqualTo(String value) {
            addCriterion("sxzl <=", value, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlLike(String value) {
            addCriterion("sxzl like", value, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlNotLike(String value) {
            addCriterion("sxzl not like", value, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlIn(List<String> values) {
            addCriterion("sxzl in", values, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlNotIn(List<String> values) {
            addCriterion("sxzl not in", values, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlBetween(String value1, String value2) {
            addCriterion("sxzl between", value1, value2, "sxzl");
            return (Criteria) this;
        }

        public Criteria andSxzlNotBetween(String value1, String value2) {
            addCriterion("sxzl not between", value1, value2, "sxzl");
            return (Criteria) this;
        }

        public Criteria andLctoIsNull() {
            addCriterion("lcto is null");
            return (Criteria) this;
        }

        public Criteria andLctoIsNotNull() {
            addCriterion("lcto is not null");
            return (Criteria) this;
        }

        public Criteria andLctoEqualTo(String value) {
            addCriterion("lcto =", value, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoNotEqualTo(String value) {
            addCriterion("lcto <>", value, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoGreaterThan(String value) {
            addCriterion("lcto >", value, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoGreaterThanOrEqualTo(String value) {
            addCriterion("lcto >=", value, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoLessThan(String value) {
            addCriterion("lcto <", value, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoLessThanOrEqualTo(String value) {
            addCriterion("lcto <=", value, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoLike(String value) {
            addCriterion("lcto like", value, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoNotLike(String value) {
            addCriterion("lcto not like", value, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoIn(List<String> values) {
            addCriterion("lcto in", values, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoNotIn(List<String> values) {
            addCriterion("lcto not in", values, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoBetween(String value1, String value2) {
            addCriterion("lcto between", value1, value2, "lcto");
            return (Criteria) this;
        }

        public Criteria andLctoNotBetween(String value1, String value2) {
            addCriterion("lcto not between", value1, value2, "lcto");
            return (Criteria) this;
        }

        public Criteria andLcttIsNull() {
            addCriterion("lctt is null");
            return (Criteria) this;
        }

        public Criteria andLcttIsNotNull() {
            addCriterion("lctt is not null");
            return (Criteria) this;
        }

        public Criteria andLcttEqualTo(String value) {
            addCriterion("lctt =", value, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttNotEqualTo(String value) {
            addCriterion("lctt <>", value, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttGreaterThan(String value) {
            addCriterion("lctt >", value, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttGreaterThanOrEqualTo(String value) {
            addCriterion("lctt >=", value, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttLessThan(String value) {
            addCriterion("lctt <", value, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttLessThanOrEqualTo(String value) {
            addCriterion("lctt <=", value, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttLike(String value) {
            addCriterion("lctt like", value, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttNotLike(String value) {
            addCriterion("lctt not like", value, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttIn(List<String> values) {
            addCriterion("lctt in", values, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttNotIn(List<String> values) {
            addCriterion("lctt not in", values, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttBetween(String value1, String value2) {
            addCriterion("lctt between", value1, value2, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcttNotBetween(String value1, String value2) {
            addCriterion("lctt not between", value1, value2, "lctt");
            return (Criteria) this;
        }

        public Criteria andLcthIsNull() {
            addCriterion("lcth is null");
            return (Criteria) this;
        }

        public Criteria andLcthIsNotNull() {
            addCriterion("lcth is not null");
            return (Criteria) this;
        }

        public Criteria andLcthEqualTo(String value) {
            addCriterion("lcth =", value, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthNotEqualTo(String value) {
            addCriterion("lcth <>", value, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthGreaterThan(String value) {
            addCriterion("lcth >", value, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthGreaterThanOrEqualTo(String value) {
            addCriterion("lcth >=", value, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthLessThan(String value) {
            addCriterion("lcth <", value, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthLessThanOrEqualTo(String value) {
            addCriterion("lcth <=", value, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthLike(String value) {
            addCriterion("lcth like", value, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthNotLike(String value) {
            addCriterion("lcth not like", value, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthIn(List<String> values) {
            addCriterion("lcth in", values, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthNotIn(List<String> values) {
            addCriterion("lcth not in", values, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthBetween(String value1, String value2) {
            addCriterion("lcth between", value1, value2, "lcth");
            return (Criteria) this;
        }

        public Criteria andLcthNotBetween(String value1, String value2) {
            addCriterion("lcth not between", value1, value2, "lcth");
            return (Criteria) this;
        }

        public Criteria andLctfIsNull() {
            addCriterion("lctf is null");
            return (Criteria) this;
        }

        public Criteria andLctfIsNotNull() {
            addCriterion("lctf is not null");
            return (Criteria) this;
        }

        public Criteria andLctfEqualTo(String value) {
            addCriterion("lctf =", value, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfNotEqualTo(String value) {
            addCriterion("lctf <>", value, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfGreaterThan(String value) {
            addCriterion("lctf >", value, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfGreaterThanOrEqualTo(String value) {
            addCriterion("lctf >=", value, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfLessThan(String value) {
            addCriterion("lctf <", value, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfLessThanOrEqualTo(String value) {
            addCriterion("lctf <=", value, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfLike(String value) {
            addCriterion("lctf like", value, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfNotLike(String value) {
            addCriterion("lctf not like", value, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfIn(List<String> values) {
            addCriterion("lctf in", values, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfNotIn(List<String> values) {
            addCriterion("lctf not in", values, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfBetween(String value1, String value2) {
            addCriterion("lctf between", value1, value2, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfNotBetween(String value1, String value2) {
            addCriterion("lctf not between", value1, value2, "lctf");
            return (Criteria) this;
        }

        public Criteria andLctfiIsNull() {
            addCriterion("lctfi is null");
            return (Criteria) this;
        }

        public Criteria andLctfiIsNotNull() {
            addCriterion("lctfi is not null");
            return (Criteria) this;
        }

        public Criteria andLctfiEqualTo(String value) {
            addCriterion("lctfi =", value, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiNotEqualTo(String value) {
            addCriterion("lctfi <>", value, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiGreaterThan(String value) {
            addCriterion("lctfi >", value, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiGreaterThanOrEqualTo(String value) {
            addCriterion("lctfi >=", value, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiLessThan(String value) {
            addCriterion("lctfi <", value, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiLessThanOrEqualTo(String value) {
            addCriterion("lctfi <=", value, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiLike(String value) {
            addCriterion("lctfi like", value, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiNotLike(String value) {
            addCriterion("lctfi not like", value, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiIn(List<String> values) {
            addCriterion("lctfi in", values, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiNotIn(List<String> values) {
            addCriterion("lctfi not in", values, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiBetween(String value1, String value2) {
            addCriterion("lctfi between", value1, value2, "lctfi");
            return (Criteria) this;
        }

        public Criteria andLctfiNotBetween(String value1, String value2) {
            addCriterion("lctfi not between", value1, value2, "lctfi");
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