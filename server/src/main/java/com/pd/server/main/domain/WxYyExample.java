package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class WxYyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WxYyExample() {
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

        public Criteria andYysdIsNull() {
            addCriterion("yysd is null");
            return (Criteria) this;
        }

        public Criteria andYysdIsNotNull() {
            addCriterion("yysd is not null");
            return (Criteria) this;
        }

        public Criteria andYysdEqualTo(String value) {
            addCriterion("yysd =", value, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdNotEqualTo(String value) {
            addCriterion("yysd <>", value, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdGreaterThan(String value) {
            addCriterion("yysd >", value, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdGreaterThanOrEqualTo(String value) {
            addCriterion("yysd >=", value, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdLessThan(String value) {
            addCriterion("yysd <", value, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdLessThanOrEqualTo(String value) {
            addCriterion("yysd <=", value, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdLike(String value) {
            addCriterion("yysd like", value, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdNotLike(String value) {
            addCriterion("yysd not like", value, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdIn(List<String> values) {
            addCriterion("yysd in", values, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdNotIn(List<String> values) {
            addCriterion("yysd not in", values, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdBetween(String value1, String value2) {
            addCriterion("yysd between", value1, value2, "yysd");
            return (Criteria) this;
        }

        public Criteria andYysdNotBetween(String value1, String value2) {
            addCriterion("yysd not between", value1, value2, "yysd");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSjhmIsNull() {
            addCriterion("sjhm is null");
            return (Criteria) this;
        }

        public Criteria andSjhmIsNotNull() {
            addCriterion("sjhm is not null");
            return (Criteria) this;
        }

        public Criteria andSjhmEqualTo(String value) {
            addCriterion("sjhm =", value, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmNotEqualTo(String value) {
            addCriterion("sjhm <>", value, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmGreaterThan(String value) {
            addCriterion("sjhm >", value, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmGreaterThanOrEqualTo(String value) {
            addCriterion("sjhm >=", value, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmLessThan(String value) {
            addCriterion("sjhm <", value, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmLessThanOrEqualTo(String value) {
            addCriterion("sjhm <=", value, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmLike(String value) {
            addCriterion("sjhm like", value, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmNotLike(String value) {
            addCriterion("sjhm not like", value, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmIn(List<String> values) {
            addCriterion("sjhm in", values, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmNotIn(List<String> values) {
            addCriterion("sjhm not in", values, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmBetween(String value1, String value2) {
            addCriterion("sjhm between", value1, value2, "sjhm");
            return (Criteria) this;
        }

        public Criteria andSjhmNotBetween(String value1, String value2) {
            addCriterion("sjhm not between", value1, value2, "sjhm");
            return (Criteria) this;
        }

        public Criteria andZjlxIsNull() {
            addCriterion("zjlx is null");
            return (Criteria) this;
        }

        public Criteria andZjlxIsNotNull() {
            addCriterion("zjlx is not null");
            return (Criteria) this;
        }

        public Criteria andZjlxEqualTo(String value) {
            addCriterion("zjlx =", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotEqualTo(String value) {
            addCriterion("zjlx <>", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxGreaterThan(String value) {
            addCriterion("zjlx >", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxGreaterThanOrEqualTo(String value) {
            addCriterion("zjlx >=", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxLessThan(String value) {
            addCriterion("zjlx <", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxLessThanOrEqualTo(String value) {
            addCriterion("zjlx <=", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxLike(String value) {
            addCriterion("zjlx like", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotLike(String value) {
            addCriterion("zjlx not like", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxIn(List<String> values) {
            addCriterion("zjlx in", values, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotIn(List<String> values) {
            addCriterion("zjlx not in", values, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxBetween(String value1, String value2) {
            addCriterion("zjlx between", value1, value2, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotBetween(String value1, String value2) {
            addCriterion("zjlx not between", value1, value2, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjhmIsNull() {
            addCriterion("zjhm is null");
            return (Criteria) this;
        }

        public Criteria andZjhmIsNotNull() {
            addCriterion("zjhm is not null");
            return (Criteria) this;
        }

        public Criteria andZjhmEqualTo(String value) {
            addCriterion("zjhm =", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotEqualTo(String value) {
            addCriterion("zjhm <>", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmGreaterThan(String value) {
            addCriterion("zjhm >", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmGreaterThanOrEqualTo(String value) {
            addCriterion("zjhm >=", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmLessThan(String value) {
            addCriterion("zjhm <", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmLessThanOrEqualTo(String value) {
            addCriterion("zjhm <=", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmLike(String value) {
            addCriterion("zjhm like", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotLike(String value) {
            addCriterion("zjhm not like", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmIn(List<String> values) {
            addCriterion("zjhm in", values, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotIn(List<String> values) {
            addCriterion("zjhm not in", values, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmBetween(String value1, String value2) {
            addCriterion("zjhm between", value1, value2, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotBetween(String value1, String value2) {
            addCriterion("zjhm not between", value1, value2, "zjhm");
            return (Criteria) this;
        }

        public Criteria andKclxIsNull() {
            addCriterion("kclx is null");
            return (Criteria) this;
        }

        public Criteria andKclxIsNotNull() {
            addCriterion("kclx is not null");
            return (Criteria) this;
        }

        public Criteria andKclxEqualTo(String value) {
            addCriterion("kclx =", value, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxNotEqualTo(String value) {
            addCriterion("kclx <>", value, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxGreaterThan(String value) {
            addCriterion("kclx >", value, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxGreaterThanOrEqualTo(String value) {
            addCriterion("kclx >=", value, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxLessThan(String value) {
            addCriterion("kclx <", value, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxLessThanOrEqualTo(String value) {
            addCriterion("kclx <=", value, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxLike(String value) {
            addCriterion("kclx like", value, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxNotLike(String value) {
            addCriterion("kclx not like", value, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxIn(List<String> values) {
            addCriterion("kclx in", values, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxNotIn(List<String> values) {
            addCriterion("kclx not in", values, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxBetween(String value1, String value2) {
            addCriterion("kclx between", value1, value2, "kclx");
            return (Criteria) this;
        }

        public Criteria andKclxNotBetween(String value1, String value2) {
            addCriterion("kclx not between", value1, value2, "kclx");
            return (Criteria) this;
        }

        public Criteria andSfxnyIsNull() {
            addCriterion("sfxny is null");
            return (Criteria) this;
        }

        public Criteria andSfxnyIsNotNull() {
            addCriterion("sfxny is not null");
            return (Criteria) this;
        }

        public Criteria andSfxnyEqualTo(String value) {
            addCriterion("sfxny =", value, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyNotEqualTo(String value) {
            addCriterion("sfxny <>", value, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyGreaterThan(String value) {
            addCriterion("sfxny >", value, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyGreaterThanOrEqualTo(String value) {
            addCriterion("sfxny >=", value, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyLessThan(String value) {
            addCriterion("sfxny <", value, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyLessThanOrEqualTo(String value) {
            addCriterion("sfxny <=", value, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyLike(String value) {
            addCriterion("sfxny like", value, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyNotLike(String value) {
            addCriterion("sfxny not like", value, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyIn(List<String> values) {
            addCriterion("sfxny in", values, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyNotIn(List<String> values) {
            addCriterion("sfxny not in", values, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyBetween(String value1, String value2) {
            addCriterion("sfxny between", value1, value2, "sfxny");
            return (Criteria) this;
        }

        public Criteria andSfxnyNotBetween(String value1, String value2) {
            addCriterion("sfxny not between", value1, value2, "sfxny");
            return (Criteria) this;
        }

        public Criteria andVinIsNull() {
            addCriterion("vin is null");
            return (Criteria) this;
        }

        public Criteria andVinIsNotNull() {
            addCriterion("vin is not null");
            return (Criteria) this;
        }

        public Criteria andVinEqualTo(String value) {
            addCriterion("vin =", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotEqualTo(String value) {
            addCriterion("vin <>", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinGreaterThan(String value) {
            addCriterion("vin >", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinGreaterThanOrEqualTo(String value) {
            addCriterion("vin >=", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLessThan(String value) {
            addCriterion("vin <", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLessThanOrEqualTo(String value) {
            addCriterion("vin <=", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLike(String value) {
            addCriterion("vin like", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotLike(String value) {
            addCriterion("vin not like", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinIn(List<String> values) {
            addCriterion("vin in", values, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotIn(List<String> values) {
            addCriterion("vin not in", values, "vin");
            return (Criteria) this;
        }

        public Criteria andVinBetween(String value1, String value2) {
            addCriterion("vin between", value1, value2, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotBetween(String value1, String value2) {
            addCriterion("vin not between", value1, value2, "vin");
            return (Criteria) this;
        }

        public Criteria andDwmcIsNull() {
            addCriterion("dwmc is null");
            return (Criteria) this;
        }

        public Criteria andDwmcIsNotNull() {
            addCriterion("dwmc is not null");
            return (Criteria) this;
        }

        public Criteria andDwmcEqualTo(String value) {
            addCriterion("dwmc =", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotEqualTo(String value) {
            addCriterion("dwmc <>", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcGreaterThan(String value) {
            addCriterion("dwmc >", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("dwmc >=", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcLessThan(String value) {
            addCriterion("dwmc <", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcLessThanOrEqualTo(String value) {
            addCriterion("dwmc <=", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcLike(String value) {
            addCriterion("dwmc like", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotLike(String value) {
            addCriterion("dwmc not like", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcIn(List<String> values) {
            addCriterion("dwmc in", values, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotIn(List<String> values) {
            addCriterion("dwmc not in", values, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcBetween(String value1, String value2) {
            addCriterion("dwmc between", value1, value2, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotBetween(String value1, String value2) {
            addCriterion("dwmc not between", value1, value2, "dwmc");
            return (Criteria) this;
        }

        public Criteria andXydmIsNull() {
            addCriterion("xydm is null");
            return (Criteria) this;
        }

        public Criteria andXydmIsNotNull() {
            addCriterion("xydm is not null");
            return (Criteria) this;
        }

        public Criteria andXydmEqualTo(String value) {
            addCriterion("xydm =", value, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmNotEqualTo(String value) {
            addCriterion("xydm <>", value, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmGreaterThan(String value) {
            addCriterion("xydm >", value, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmGreaterThanOrEqualTo(String value) {
            addCriterion("xydm >=", value, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmLessThan(String value) {
            addCriterion("xydm <", value, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmLessThanOrEqualTo(String value) {
            addCriterion("xydm <=", value, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmLike(String value) {
            addCriterion("xydm like", value, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmNotLike(String value) {
            addCriterion("xydm not like", value, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmIn(List<String> values) {
            addCriterion("xydm in", values, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmNotIn(List<String> values) {
            addCriterion("xydm not in", values, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmBetween(String value1, String value2) {
            addCriterion("xydm between", value1, value2, "xydm");
            return (Criteria) this;
        }

        public Criteria andXydmNotBetween(String value1, String value2) {
            addCriterion("xydm not between", value1, value2, "xydm");
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

        public Criteria andCjsjEqualTo(String value) {
            addCriterion("cjsj =", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotEqualTo(String value) {
            addCriterion("cjsj <>", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThan(String value) {
            addCriterion("cjsj >", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThanOrEqualTo(String value) {
            addCriterion("cjsj >=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThan(String value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(String value) {
            addCriterion("cjsj <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLike(String value) {
            addCriterion("cjsj like", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotLike(String value) {
            addCriterion("cjsj not like", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjIn(List<String> values) {
            addCriterion("cjsj in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotIn(List<String> values) {
            addCriterion("cjsj not in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjBetween(String value1, String value2) {
            addCriterion("cjsj between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotBetween(String value1, String value2) {
            addCriterion("cjsj not between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andYysjIsNull() {
            addCriterion("yysj is null");
            return (Criteria) this;
        }

        public Criteria andYysjIsNotNull() {
            addCriterion("yysj is not null");
            return (Criteria) this;
        }

        public Criteria andYysjEqualTo(String value) {
            addCriterion("yysj =", value, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjNotEqualTo(String value) {
            addCriterion("yysj <>", value, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjGreaterThan(String value) {
            addCriterion("yysj >", value, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjGreaterThanOrEqualTo(String value) {
            addCriterion("yysj >=", value, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjLessThan(String value) {
            addCriterion("yysj <", value, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjLessThanOrEqualTo(String value) {
            addCriterion("yysj <=", value, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjLike(String value) {
            addCriterion("yysj like", value, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjNotLike(String value) {
            addCriterion("yysj not like", value, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjIn(List<String> values) {
            addCriterion("yysj in", values, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjNotIn(List<String> values) {
            addCriterion("yysj not in", values, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjBetween(String value1, String value2) {
            addCriterion("yysj between", value1, value2, "yysj");
            return (Criteria) this;
        }

        public Criteria andYysjNotBetween(String value1, String value2) {
            addCriterion("yysj not between", value1, value2, "yysj");
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

        public Criteria andSlsjIsNull() {
            addCriterion("slsj is null");
            return (Criteria) this;
        }

        public Criteria andSlsjIsNotNull() {
            addCriterion("slsj is not null");
            return (Criteria) this;
        }

        public Criteria andSlsjEqualTo(String value) {
            addCriterion("slsj =", value, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjNotEqualTo(String value) {
            addCriterion("slsj <>", value, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjGreaterThan(String value) {
            addCriterion("slsj >", value, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjGreaterThanOrEqualTo(String value) {
            addCriterion("slsj >=", value, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjLessThan(String value) {
            addCriterion("slsj <", value, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjLessThanOrEqualTo(String value) {
            addCriterion("slsj <=", value, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjLike(String value) {
            addCriterion("slsj like", value, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjNotLike(String value) {
            addCriterion("slsj not like", value, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjIn(List<String> values) {
            addCriterion("slsj in", values, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjNotIn(List<String> values) {
            addCriterion("slsj not in", values, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjBetween(String value1, String value2) {
            addCriterion("slsj between", value1, value2, "slsj");
            return (Criteria) this;
        }

        public Criteria andSlsjNotBetween(String value1, String value2) {
            addCriterion("slsj not between", value1, value2, "slsj");
            return (Criteria) this;
        }

        public Criteria andYyrqIsNull() {
            addCriterion("yyrq is null");
            return (Criteria) this;
        }

        public Criteria andYyrqIsNotNull() {
            addCriterion("yyrq is not null");
            return (Criteria) this;
        }

        public Criteria andYyrqEqualTo(String value) {
            addCriterion("yyrq =", value, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqNotEqualTo(String value) {
            addCriterion("yyrq <>", value, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqGreaterThan(String value) {
            addCriterion("yyrq >", value, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqGreaterThanOrEqualTo(String value) {
            addCriterion("yyrq >=", value, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqLessThan(String value) {
            addCriterion("yyrq <", value, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqLessThanOrEqualTo(String value) {
            addCriterion("yyrq <=", value, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqLike(String value) {
            addCriterion("yyrq like", value, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqNotLike(String value) {
            addCriterion("yyrq not like", value, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqIn(List<String> values) {
            addCriterion("yyrq in", values, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqNotIn(List<String> values) {
            addCriterion("yyrq not in", values, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqBetween(String value1, String value2) {
            addCriterion("yyrq between", value1, value2, "yyrq");
            return (Criteria) this;
        }

        public Criteria andYyrqNotBetween(String value1, String value2) {
            addCriterion("yyrq not between", value1, value2, "yyrq");
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

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andYytypeIsNull() {
            addCriterion("yytype is null");
            return (Criteria) this;
        }

        public Criteria andYytypeIsNotNull() {
            addCriterion("yytype is not null");
            return (Criteria) this;
        }

        public Criteria andYytypeEqualTo(String value) {
            addCriterion("yytype =", value, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeNotEqualTo(String value) {
            addCriterion("yytype <>", value, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeGreaterThan(String value) {
            addCriterion("yytype >", value, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeGreaterThanOrEqualTo(String value) {
            addCriterion("yytype >=", value, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeLessThan(String value) {
            addCriterion("yytype <", value, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeLessThanOrEqualTo(String value) {
            addCriterion("yytype <=", value, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeLike(String value) {
            addCriterion("yytype like", value, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeNotLike(String value) {
            addCriterion("yytype not like", value, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeIn(List<String> values) {
            addCriterion("yytype in", values, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeNotIn(List<String> values) {
            addCriterion("yytype not in", values, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeBetween(String value1, String value2) {
            addCriterion("yytype between", value1, value2, "yytype");
            return (Criteria) this;
        }

        public Criteria andYytypeNotBetween(String value1, String value2) {
            addCriterion("yytype not between", value1, value2, "yytype");
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