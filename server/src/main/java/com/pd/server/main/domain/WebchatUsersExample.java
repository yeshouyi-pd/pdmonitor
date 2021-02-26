package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebchatUsersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebchatUsersExample() {
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

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
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

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andZjmcIsNull() {
            addCriterion("zjmc is null");
            return (Criteria) this;
        }

        public Criteria andZjmcIsNotNull() {
            addCriterion("zjmc is not null");
            return (Criteria) this;
        }

        public Criteria andZjmcEqualTo(String value) {
            addCriterion("zjmc =", value, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcNotEqualTo(String value) {
            addCriterion("zjmc <>", value, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcGreaterThan(String value) {
            addCriterion("zjmc >", value, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcGreaterThanOrEqualTo(String value) {
            addCriterion("zjmc >=", value, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcLessThan(String value) {
            addCriterion("zjmc <", value, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcLessThanOrEqualTo(String value) {
            addCriterion("zjmc <=", value, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcLike(String value) {
            addCriterion("zjmc like", value, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcNotLike(String value) {
            addCriterion("zjmc not like", value, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcIn(List<String> values) {
            addCriterion("zjmc in", values, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcNotIn(List<String> values) {
            addCriterion("zjmc not in", values, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcBetween(String value1, String value2) {
            addCriterion("zjmc between", value1, value2, "zjmc");
            return (Criteria) this;
        }

        public Criteria andZjmcNotBetween(String value1, String value2) {
            addCriterion("zjmc not between", value1, value2, "zjmc");
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

        public Criteria andYxqsIsNull() {
            addCriterion("yxqs is null");
            return (Criteria) this;
        }

        public Criteria andYxqsIsNotNull() {
            addCriterion("yxqs is not null");
            return (Criteria) this;
        }

        public Criteria andYxqsEqualTo(Date value) {
            addCriterion("yxqs =", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsNotEqualTo(Date value) {
            addCriterion("yxqs <>", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsGreaterThan(Date value) {
            addCriterion("yxqs >", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsGreaterThanOrEqualTo(Date value) {
            addCriterion("yxqs >=", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsLessThan(Date value) {
            addCriterion("yxqs <", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsLessThanOrEqualTo(Date value) {
            addCriterion("yxqs <=", value, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsIn(List<Date> values) {
            addCriterion("yxqs in", values, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsNotIn(List<Date> values) {
            addCriterion("yxqs not in", values, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsBetween(Date value1, Date value2) {
            addCriterion("yxqs between", value1, value2, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqsNotBetween(Date value1, Date value2) {
            addCriterion("yxqs not between", value1, value2, "yxqs");
            return (Criteria) this;
        }

        public Criteria andYxqzIsNull() {
            addCriterion("yxqz is null");
            return (Criteria) this;
        }

        public Criteria andYxqzIsNotNull() {
            addCriterion("yxqz is not null");
            return (Criteria) this;
        }

        public Criteria andYxqzEqualTo(Date value) {
            addCriterion("yxqz =", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzNotEqualTo(Date value) {
            addCriterion("yxqz <>", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzGreaterThan(Date value) {
            addCriterion("yxqz >", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzGreaterThanOrEqualTo(Date value) {
            addCriterion("yxqz >=", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzLessThan(Date value) {
            addCriterion("yxqz <", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzLessThanOrEqualTo(Date value) {
            addCriterion("yxqz <=", value, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzIn(List<Date> values) {
            addCriterion("yxqz in", values, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzNotIn(List<Date> values) {
            addCriterion("yxqz not in", values, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzBetween(Date value1, Date value2) {
            addCriterion("yxqz between", value1, value2, "yxqz");
            return (Criteria) this;
        }

        public Criteria andYxqzNotBetween(Date value1, Date value2) {
            addCriterion("yxqz not between", value1, value2, "yxqz");
            return (Criteria) this;
        }

        public Criteria andZjdzIsNull() {
            addCriterion("zjdz is null");
            return (Criteria) this;
        }

        public Criteria andZjdzIsNotNull() {
            addCriterion("zjdz is not null");
            return (Criteria) this;
        }

        public Criteria andZjdzEqualTo(String value) {
            addCriterion("zjdz =", value, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzNotEqualTo(String value) {
            addCriterion("zjdz <>", value, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzGreaterThan(String value) {
            addCriterion("zjdz >", value, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzGreaterThanOrEqualTo(String value) {
            addCriterion("zjdz >=", value, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzLessThan(String value) {
            addCriterion("zjdz <", value, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzLessThanOrEqualTo(String value) {
            addCriterion("zjdz <=", value, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzLike(String value) {
            addCriterion("zjdz like", value, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzNotLike(String value) {
            addCriterion("zjdz not like", value, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzIn(List<String> values) {
            addCriterion("zjdz in", values, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzNotIn(List<String> values) {
            addCriterion("zjdz not in", values, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzBetween(String value1, String value2) {
            addCriterion("zjdz between", value1, value2, "zjdz");
            return (Criteria) this;
        }

        public Criteria andZjdzNotBetween(String value1, String value2) {
            addCriterion("zjdz not between", value1, value2, "zjdz");
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

        public Criteria andCjsjLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(cjsj,'%Y-%m-%d')  <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThanOrEqualTo(String value) {
            addCriterion(" DATE_FORMAT(cjsj,'%Y-%m-%d') >=", value, "cjsj");
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

        public Criteria andSfrzIsNull() {
            addCriterion("sfrz is null");
            return (Criteria) this;
        }

        public Criteria andSfrzIsNotNull() {
            addCriterion("sfrz is not null");
            return (Criteria) this;
        }

        public Criteria andSfrzEqualTo(String value) {
            addCriterion("sfrz =", value, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzNotEqualTo(String value) {
            addCriterion("sfrz <>", value, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzGreaterThan(String value) {
            addCriterion("sfrz >", value, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzGreaterThanOrEqualTo(String value) {
            addCriterion("sfrz >=", value, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzLessThan(String value) {
            addCriterion("sfrz <", value, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzLessThanOrEqualTo(String value) {
            addCriterion("sfrz <=", value, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzLike(String value) {
            addCriterion("sfrz like", value, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzNotLike(String value) {
            addCriterion("sfrz not like", value, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzIn(List<String> values) {
            addCriterion("sfrz in", values, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzNotIn(List<String> values) {
            addCriterion("sfrz not in", values, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzBetween(String value1, String value2) {
            addCriterion("sfrz between", value1, value2, "sfrz");
            return (Criteria) this;
        }

        public Criteria andSfrzNotBetween(String value1, String value2) {
            addCriterion("sfrz not between", value1, value2, "sfrz");
            return (Criteria) this;
        }

        public Criteria andZjcxIsNull() {
            addCriterion("zjcx is null");
            return (Criteria) this;
        }

        public Criteria andZjcxIsNotNull() {
            addCriterion("zjcx is not null");
            return (Criteria) this;
        }

        public Criteria andZjcxEqualTo(String value) {
            addCriterion("zjcx =", value, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxNotEqualTo(String value) {
            addCriterion("zjcx <>", value, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxGreaterThan(String value) {
            addCriterion("zjcx >", value, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxGreaterThanOrEqualTo(String value) {
            addCriterion("zjcx >=", value, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxLessThan(String value) {
            addCriterion("zjcx <", value, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxLessThanOrEqualTo(String value) {
            addCriterion("zjcx <=", value, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxLike(String value) {
            addCriterion("zjcx like", value, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxNotLike(String value) {
            addCriterion("zjcx not like", value, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxIn(List<String> values) {
            addCriterion("zjcx in", values, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxNotIn(List<String> values) {
            addCriterion("zjcx not in", values, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxBetween(String value1, String value2) {
            addCriterion("zjcx between", value1, value2, "zjcx");
            return (Criteria) this;
        }

        public Criteria andZjcxNotBetween(String value1, String value2) {
            addCriterion("zjcx not between", value1, value2, "zjcx");
            return (Criteria) this;
        }

        public Criteria andXtbhIsNull() {
            addCriterion("xtbh is null");
            return (Criteria) this;
        }

        public Criteria andXtbhIsNotNull() {
            addCriterion("xtbh is not null");
            return (Criteria) this;
        }

        public Criteria andXtbhEqualTo(String value) {
            addCriterion("xtbh =", value, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhNotEqualTo(String value) {
            addCriterion("xtbh <>", value, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhGreaterThan(String value) {
            addCriterion("xtbh >", value, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhGreaterThanOrEqualTo(String value) {
            addCriterion("xtbh >=", value, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhLessThan(String value) {
            addCriterion("xtbh <", value, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhLessThanOrEqualTo(String value) {
            addCriterion("xtbh <=", value, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhLike(String value) {
            addCriterion("xtbh like", value, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhNotLike(String value) {
            addCriterion("xtbh not like", value, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhIn(List<String> values) {
            addCriterion("xtbh in", values, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhNotIn(List<String> values) {
            addCriterion("xtbh not in", values, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhBetween(String value1, String value2) {
            addCriterion("xtbh between", value1, value2, "xtbh");
            return (Criteria) this;
        }

        public Criteria andXtbhNotBetween(String value1, String value2) {
            addCriterion("xtbh not between", value1, value2, "xtbh");
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

        public Criteria andSimilarityIsNull() {
            addCriterion("similarity is null");
            return (Criteria) this;
        }

        public Criteria andSimilarityIsNotNull() {
            addCriterion("similarity is not null");
            return (Criteria) this;
        }

        public Criteria andSimilarityEqualTo(String value) {
            addCriterion("similarity =", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityNotEqualTo(String value) {
            addCriterion("similarity <>", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityGreaterThan(String value) {
            addCriterion("similarity >", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityGreaterThanOrEqualTo(String value) {
            addCriterion("similarity >=", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityLessThan(String value) {
            addCriterion("similarity <", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityLessThanOrEqualTo(String value) {
            addCriterion("similarity <=", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityLike(String value) {
            addCriterion("similarity like", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityNotLike(String value) {
            addCriterion("similarity not like", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityIn(List<String> values) {
            addCriterion("similarity in", values, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityNotIn(List<String> values) {
            addCriterion("similarity not in", values, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityBetween(String value1, String value2) {
            addCriterion("similarity between", value1, value2, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityNotBetween(String value1, String value2) {
            addCriterion("similarity not between", value1, value2, "similarity");
            return (Criteria) this;
        }

        public Criteria andFailflagIsNull() {
            addCriterion("failFlag is null");
            return (Criteria) this;
        }

        public Criteria andFailflagIsNotNull() {
            addCriterion("failFlag is not null");
            return (Criteria) this;
        }

        public Criteria andFailflagEqualTo(String value) {
            addCriterion("failFlag =", value, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagNotEqualTo(String value) {
            addCriterion("failFlag <>", value, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagGreaterThan(String value) {
            addCriterion("failFlag >", value, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagGreaterThanOrEqualTo(String value) {
            addCriterion("failFlag >=", value, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagLessThan(String value) {
            addCriterion("failFlag <", value, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagLessThanOrEqualTo(String value) {
            addCriterion("failFlag <=", value, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagLike(String value) {
            addCriterion("failFlag like", value, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagNotLike(String value) {
            addCriterion("failFlag not like", value, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagIn(List<String> values) {
            addCriterion("failFlag in", values, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagNotIn(List<String> values) {
            addCriterion("failFlag not in", values, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagBetween(String value1, String value2) {
            addCriterion("failFlag between", value1, value2, "failflag");
            return (Criteria) this;
        }

        public Criteria andFailflagNotBetween(String value1, String value2) {
            addCriterion("failFlag not between", value1, value2, "failflag");
            return (Criteria) this;
        }

        public Criteria andJszhIsNull() {
            addCriterion("jszh is null");
            return (Criteria) this;
        }

        public Criteria andJszhIsNotNull() {
            addCriterion("jszh is not null");
            return (Criteria) this;
        }

        public Criteria andJszhEqualTo(String value) {
            addCriterion("jszh =", value, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhNotEqualTo(String value) {
            addCriterion("jszh <>", value, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhGreaterThan(String value) {
            addCriterion("jszh >", value, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhGreaterThanOrEqualTo(String value) {
            addCriterion("jszh >=", value, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhLessThan(String value) {
            addCriterion("jszh <", value, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhLessThanOrEqualTo(String value) {
            addCriterion("jszh <=", value, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhLike(String value) {
            addCriterion("jszh like", value, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhNotLike(String value) {
            addCriterion("jszh not like", value, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhIn(List<String> values) {
            addCriterion("jszh in", values, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhNotIn(List<String> values) {
            addCriterion("jszh not in", values, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhBetween(String value1, String value2) {
            addCriterion("jszh between", value1, value2, "jszh");
            return (Criteria) this;
        }

        public Criteria andJszhNotBetween(String value1, String value2) {
            addCriterion("jszh not between", value1, value2, "jszh");
            return (Criteria) this;
        }

        public Criteria andCclzrqIsNull() {
            addCriterion("cclzrq is null");
            return (Criteria) this;
        }

        public Criteria andCclzrqIsNotNull() {
            addCriterion("cclzrq is not null");
            return (Criteria) this;
        }

        public Criteria andCclzrqEqualTo(Date value) {
            addCriterion("cclzrq =", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqNotEqualTo(Date value) {
            addCriterion("cclzrq <>", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqGreaterThan(Date value) {
            addCriterion("cclzrq >", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqGreaterThanOrEqualTo(Date value) {
            addCriterion("cclzrq >=", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqLessThan(Date value) {
            addCriterion("cclzrq <", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqLessThanOrEqualTo(Date value) {
            addCriterion("cclzrq <=", value, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqIn(List<Date> values) {
            addCriterion("cclzrq in", values, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqNotIn(List<Date> values) {
            addCriterion("cclzrq not in", values, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqBetween(Date value1, Date value2) {
            addCriterion("cclzrq between", value1, value2, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andCclzrqNotBetween(Date value1, Date value2) {
            addCriterion("cclzrq not between", value1, value2, "cclzrq");
            return (Criteria) this;
        }

        public Criteria andJszyxqzIsNull() {
            addCriterion("jszyxqz is null");
            return (Criteria) this;
        }

        public Criteria andJszyxqzIsNotNull() {
            addCriterion("jszyxqz is not null");
            return (Criteria) this;
        }

        public Criteria andJszyxqzEqualTo(Date value) {
            addCriterion("jszyxqz =", value, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andJszyxqzNotEqualTo(Date value) {
            addCriterion("jszyxqz <>", value, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andJszyxqzGreaterThan(Date value) {
            addCriterion("jszyxqz >", value, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andJszyxqzGreaterThanOrEqualTo(Date value) {
            addCriterion("jszyxqz >=", value, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andJszyxqzLessThan(Date value) {
            addCriterion("jszyxqz <", value, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andJszyxqzLessThanOrEqualTo(Date value) {
            addCriterion("jszyxqz <=", value, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andJszyxqzIn(List<Date> values) {
            addCriterion("jszyxqz in", values, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andJszyxqzNotIn(List<Date> values) {
            addCriterion("jszyxqz not in", values, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andJszyxqzBetween(Date value1, Date value2) {
            addCriterion("jszyxqz between", value1, value2, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andJszyxqzNotBetween(Date value1, Date value2) {
            addCriterion("jszyxqz not between", value1, value2, "jszyxqz");
            return (Criteria) this;
        }

        public Criteria andLxdhIsNull() {
            addCriterion("lxdh is null");
            return (Criteria) this;
        }

        public Criteria andLxdhIsNotNull() {
            addCriterion("lxdh is not null");
            return (Criteria) this;
        }

        public Criteria andLxdhEqualTo(String value) {
            addCriterion("lxdh =", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhNotEqualTo(String value) {
            addCriterion("lxdh <>", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhGreaterThan(String value) {
            addCriterion("lxdh >", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhGreaterThanOrEqualTo(String value) {
            addCriterion("lxdh >=", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhLessThan(String value) {
            addCriterion("lxdh <", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhLessThanOrEqualTo(String value) {
            addCriterion("lxdh <=", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhLike(String value) {
            addCriterion("lxdh like", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhNotLike(String value) {
            addCriterion("lxdh not like", value, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhIn(List<String> values) {
            addCriterion("lxdh in", values, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhNotIn(List<String> values) {
            addCriterion("lxdh not in", values, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhBetween(String value1, String value2) {
            addCriterion("lxdh between", value1, value2, "lxdh");
            return (Criteria) this;
        }

        public Criteria andLxdhNotBetween(String value1, String value2) {
            addCriterion("lxdh not between", value1, value2, "lxdh");
            return (Criteria) this;
        }

        public Criteria andYhdjIsNull() {
            addCriterion("yhdj is null");
            return (Criteria) this;
        }

        public Criteria andYhdjIsNotNull() {
            addCriterion("yhdj is not null");
            return (Criteria) this;
        }

        public Criteria andYhdjEqualTo(String value) {
            addCriterion("yhdj =", value, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjNotEqualTo(String value) {
            addCriterion("yhdj <>", value, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjGreaterThan(String value) {
            addCriterion("yhdj >", value, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjGreaterThanOrEqualTo(String value) {
            addCriterion("yhdj >=", value, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjLessThan(String value) {
            addCriterion("yhdj <", value, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjLessThanOrEqualTo(String value) {
            addCriterion("yhdj <=", value, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjLike(String value) {
            addCriterion("yhdj like", value, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjNotLike(String value) {
            addCriterion("yhdj not like", value, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjIn(List<String> values) {
            addCriterion("yhdj in", values, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjNotIn(List<String> values) {
            addCriterion("yhdj not in", values, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjBetween(String value1, String value2) {
            addCriterion("yhdj between", value1, value2, "yhdj");
            return (Criteria) this;
        }

        public Criteria andYhdjNotBetween(String value1, String value2) {
            addCriterion("yhdj not between", value1, value2, "yhdj");
            return (Criteria) this;
        }

        public Criteria andGzclIsNull() {
            addCriterion("gzcl is null");
            return (Criteria) this;
        }

        public Criteria andGzclIsNotNull() {
            addCriterion("gzcl is not null");
            return (Criteria) this;
        }

        public Criteria andGzclEqualTo(String value) {
            addCriterion("gzcl =", value, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclNotEqualTo(String value) {
            addCriterion("gzcl <>", value, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclGreaterThan(String value) {
            addCriterion("gzcl >", value, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclGreaterThanOrEqualTo(String value) {
            addCriterion("gzcl >=", value, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclLessThan(String value) {
            addCriterion("gzcl <", value, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclLessThanOrEqualTo(String value) {
            addCriterion("gzcl <=", value, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclLike(String value) {
            addCriterion("gzcl like", value, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclNotLike(String value) {
            addCriterion("gzcl not like", value, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclIn(List<String> values) {
            addCriterion("gzcl in", values, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclNotIn(List<String> values) {
            addCriterion("gzcl not in", values, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclBetween(String value1, String value2) {
            addCriterion("gzcl between", value1, value2, "gzcl");
            return (Criteria) this;
        }

        public Criteria andGzclNotBetween(String value1, String value2) {
            addCriterion("gzcl not between", value1, value2, "gzcl");
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