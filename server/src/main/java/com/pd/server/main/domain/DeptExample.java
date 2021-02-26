package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeptExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeptExample() {
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

        public Criteria andDeptnameIsNull() {
            addCriterion("deptname is null");
            return (Criteria) this;
        }

        public Criteria andDeptnameIsNotNull() {
            addCriterion("deptname is not null");
            return (Criteria) this;
        }

        public Criteria andDeptnameEqualTo(String value) {
            addCriterion("deptname =", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotEqualTo(String value) {
            addCriterion("deptname <>", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameGreaterThan(String value) {
            addCriterion("deptname >", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameGreaterThanOrEqualTo(String value) {
            addCriterion("deptname >=", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameLessThan(String value) {
            addCriterion("deptname <", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameLessThanOrEqualTo(String value) {
            addCriterion("deptname <=", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameLike(String value) {
            addCriterion("deptname like", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotLike(String value) {
            addCriterion("deptname not like", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameIn(List<String> values) {
            addCriterion("deptname in", values, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotIn(List<String> values) {
            addCriterion("deptname not in", values, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameBetween(String value1, String value2) {
            addCriterion("deptname between", value1, value2, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotBetween(String value1, String value2) {
            addCriterion("deptname not between", value1, value2, "deptname");
            return (Criteria) this;
        }

        public Criteria andUpcodeIsNull() {
            addCriterion("upcode is null");
            return (Criteria) this;
        }

        public Criteria andUpcodeIsNotNull() {
            addCriterion("upcode is not null");
            return (Criteria) this;
        }

        public Criteria andUpcodeEqualTo(String value) {
            addCriterion("upcode =", value, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeNotEqualTo(String value) {
            addCriterion("upcode <>", value, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeGreaterThan(String value) {
            addCriterion("upcode >", value, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeGreaterThanOrEqualTo(String value) {
            addCriterion("upcode >=", value, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeLessThan(String value) {
            addCriterion("upcode <", value, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeLessThanOrEqualTo(String value) {
            addCriterion("upcode <=", value, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeLike(String value) {
            addCriterion("upcode like", value, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeNotLike(String value) {
            addCriterion("upcode not like", value, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeIn(List<String> values) {
            addCriterion("upcode in", values, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeNotIn(List<String> values) {
            addCriterion("upcode not in", values, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeBetween(String value1, String value2) {
            addCriterion("upcode between", value1, value2, "upcode");
            return (Criteria) this;
        }

        public Criteria andUpcodeNotBetween(String value1, String value2) {
            addCriterion("upcode not between", value1, value2, "upcode");
            return (Criteria) this;
        }

        public Criteria andDeptdescIsNull() {
            addCriterion("deptdesc is null");
            return (Criteria) this;
        }

        public Criteria andDeptdescIsNotNull() {
            addCriterion("deptdesc is not null");
            return (Criteria) this;
        }

        public Criteria andDeptdescEqualTo(String value) {
            addCriterion("deptdesc =", value, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescNotEqualTo(String value) {
            addCriterion("deptdesc <>", value, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescGreaterThan(String value) {
            addCriterion("deptdesc >", value, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescGreaterThanOrEqualTo(String value) {
            addCriterion("deptdesc >=", value, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescLessThan(String value) {
            addCriterion("deptdesc <", value, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescLessThanOrEqualTo(String value) {
            addCriterion("deptdesc <=", value, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescLike(String value) {
            addCriterion("deptdesc like", value, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescNotLike(String value) {
            addCriterion("deptdesc not like", value, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescIn(List<String> values) {
            addCriterion("deptdesc in", values, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescNotIn(List<String> values) {
            addCriterion("deptdesc not in", values, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescBetween(String value1, String value2) {
            addCriterion("deptdesc between", value1, value2, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andDeptdescNotBetween(String value1, String value2) {
            addCriterion("deptdesc not between", value1, value2, "deptdesc");
            return (Criteria) this;
        }

        public Criteria andLinkaddIsNull() {
            addCriterion("linkadd is null");
            return (Criteria) this;
        }

        public Criteria andLinkaddIsNotNull() {
            addCriterion("linkadd is not null");
            return (Criteria) this;
        }

        public Criteria andLinkaddEqualTo(String value) {
            addCriterion("linkadd =", value, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddNotEqualTo(String value) {
            addCriterion("linkadd <>", value, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddGreaterThan(String value) {
            addCriterion("linkadd >", value, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddGreaterThanOrEqualTo(String value) {
            addCriterion("linkadd >=", value, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddLessThan(String value) {
            addCriterion("linkadd <", value, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddLessThanOrEqualTo(String value) {
            addCriterion("linkadd <=", value, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddLike(String value) {
            addCriterion("linkadd like", value, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddNotLike(String value) {
            addCriterion("linkadd not like", value, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddIn(List<String> values) {
            addCriterion("linkadd in", values, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddNotIn(List<String> values) {
            addCriterion("linkadd not in", values, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddBetween(String value1, String value2) {
            addCriterion("linkadd between", value1, value2, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinkaddNotBetween(String value1, String value2) {
            addCriterion("linkadd not between", value1, value2, "linkadd");
            return (Criteria) this;
        }

        public Criteria andLinktelIsNull() {
            addCriterion("linktel is null");
            return (Criteria) this;
        }

        public Criteria andLinktelIsNotNull() {
            addCriterion("linktel is not null");
            return (Criteria) this;
        }

        public Criteria andLinktelEqualTo(String value) {
            addCriterion("linktel =", value, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelNotEqualTo(String value) {
            addCriterion("linktel <>", value, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelGreaterThan(String value) {
            addCriterion("linktel >", value, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelGreaterThanOrEqualTo(String value) {
            addCriterion("linktel >=", value, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelLessThan(String value) {
            addCriterion("linktel <", value, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelLessThanOrEqualTo(String value) {
            addCriterion("linktel <=", value, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelLike(String value) {
            addCriterion("linktel like", value, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelNotLike(String value) {
            addCriterion("linktel not like", value, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelIn(List<String> values) {
            addCriterion("linktel in", values, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelNotIn(List<String> values) {
            addCriterion("linktel not in", values, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelBetween(String value1, String value2) {
            addCriterion("linktel between", value1, value2, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinktelNotBetween(String value1, String value2) {
            addCriterion("linktel not between", value1, value2, "linktel");
            return (Criteria) this;
        }

        public Criteria andLinkmanIsNull() {
            addCriterion("linkman is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanIsNotNull() {
            addCriterion("linkman is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanEqualTo(String value) {
            addCriterion("linkman =", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotEqualTo(String value) {
            addCriterion("linkman <>", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanGreaterThan(String value) {
            addCriterion("linkman >", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanGreaterThanOrEqualTo(String value) {
            addCriterion("linkman >=", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanLessThan(String value) {
            addCriterion("linkman <", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanLessThanOrEqualTo(String value) {
            addCriterion("linkman <=", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanLike(String value) {
            addCriterion("linkman like", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotLike(String value) {
            addCriterion("linkman not like", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanIn(List<String> values) {
            addCriterion("linkman in", values, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotIn(List<String> values) {
            addCriterion("linkman not in", values, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanBetween(String value1, String value2) {
            addCriterion("linkman between", value1, value2, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotBetween(String value1, String value2) {
            addCriterion("linkman not between", value1, value2, "linkman");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDepttypeIsNull() {
            addCriterion("depttype is null");
            return (Criteria) this;
        }

        public Criteria andDepttypeIsNotNull() {
            addCriterion("depttype is not null");
            return (Criteria) this;
        }

        public Criteria andDepttypeEqualTo(String value) {
            addCriterion("depttype =", value, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeNotEqualTo(String value) {
            addCriterion("depttype <>", value, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeGreaterThan(String value) {
            addCriterion("depttype >", value, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeGreaterThanOrEqualTo(String value) {
            addCriterion("depttype >=", value, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeLessThan(String value) {
            addCriterion("depttype <", value, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeLessThanOrEqualTo(String value) {
            addCriterion("depttype <=", value, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeLike(String value) {
            addCriterion("depttype like", value, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeNotLike(String value) {
            addCriterion("depttype not like", value, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeIn(List<String> values) {
            addCriterion("depttype in", values, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeNotIn(List<String> values) {
            addCriterion("depttype not in", values, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeBetween(String value1, String value2) {
            addCriterion("depttype between", value1, value2, "depttype");
            return (Criteria) this;
        }

        public Criteria andDepttypeNotBetween(String value1, String value2) {
            addCriterion("depttype not between", value1, value2, "depttype");
            return (Criteria) this;
        }

        public Criteria andZffwIsNull() {
            addCriterion("zffw is null");
            return (Criteria) this;
        }

        public Criteria andZffwIsNotNull() {
            addCriterion("zffw is not null");
            return (Criteria) this;
        }

        public Criteria andZffwEqualTo(String value) {
            addCriterion("zffw =", value, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwNotEqualTo(String value) {
            addCriterion("zffw <>", value, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwGreaterThan(String value) {
            addCriterion("zffw >", value, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwGreaterThanOrEqualTo(String value) {
            addCriterion("zffw >=", value, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwLessThan(String value) {
            addCriterion("zffw <", value, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwLessThanOrEqualTo(String value) {
            addCriterion("zffw <=", value, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwLike(String value) {
            addCriterion("zffw like", value, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwNotLike(String value) {
            addCriterion("zffw not like", value, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwIn(List<String> values) {
            addCriterion("zffw in", values, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwNotIn(List<String> values) {
            addCriterion("zffw not in", values, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwBetween(String value1, String value2) {
            addCriterion("zffw between", value1, value2, "zffw");
            return (Criteria) this;
        }

        public Criteria andZffwNotBetween(String value1, String value2) {
            addCriterion("zffw not between", value1, value2, "zffw");
            return (Criteria) this;
        }

        public Criteria andJdIsNull() {
            addCriterion("jd is null");
            return (Criteria) this;
        }

        public Criteria andJdIsNotNull() {
            addCriterion("jd is not null");
            return (Criteria) this;
        }

        public Criteria andJdEqualTo(String value) {
            addCriterion("jd =", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotEqualTo(String value) {
            addCriterion("jd <>", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdGreaterThan(String value) {
            addCriterion("jd >", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdGreaterThanOrEqualTo(String value) {
            addCriterion("jd >=", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdLessThan(String value) {
            addCriterion("jd <", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdLessThanOrEqualTo(String value) {
            addCriterion("jd <=", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdLike(String value) {
            addCriterion("jd like", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotLike(String value) {
            addCriterion("jd not like", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdIn(List<String> values) {
            addCriterion("jd in", values, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotIn(List<String> values) {
            addCriterion("jd not in", values, "jd");
            return (Criteria) this;
        }

        public Criteria andJdBetween(String value1, String value2) {
            addCriterion("jd between", value1, value2, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotBetween(String value1, String value2) {
            addCriterion("jd not between", value1, value2, "jd");
            return (Criteria) this;
        }

        public Criteria andWdIsNull() {
            addCriterion("wd is null");
            return (Criteria) this;
        }

        public Criteria andWdIsNotNull() {
            addCriterion("wd is not null");
            return (Criteria) this;
        }

        public Criteria andWdEqualTo(String value) {
            addCriterion("wd =", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotEqualTo(String value) {
            addCriterion("wd <>", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdGreaterThan(String value) {
            addCriterion("wd >", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdGreaterThanOrEqualTo(String value) {
            addCriterion("wd >=", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLessThan(String value) {
            addCriterion("wd <", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLessThanOrEqualTo(String value) {
            addCriterion("wd <=", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLike(String value) {
            addCriterion("wd like", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotLike(String value) {
            addCriterion("wd not like", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdIn(List<String> values) {
            addCriterion("wd in", values, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotIn(List<String> values) {
            addCriterion("wd not in", values, "wd");
            return (Criteria) this;
        }

        public Criteria andWdBetween(String value1, String value2) {
            addCriterion("wd between", value1, value2, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotBetween(String value1, String value2) {
            addCriterion("wd not between", value1, value2, "wd");
            return (Criteria) this;
        }

        public Criteria andFdrIsNull() {
            addCriterion("fdr is null");
            return (Criteria) this;
        }

        public Criteria andFdrIsNotNull() {
            addCriterion("fdr is not null");
            return (Criteria) this;
        }

        public Criteria andFdrEqualTo(String value) {
            addCriterion("fdr =", value, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrNotEqualTo(String value) {
            addCriterion("fdr <>", value, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrGreaterThan(String value) {
            addCriterion("fdr >", value, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrGreaterThanOrEqualTo(String value) {
            addCriterion("fdr >=", value, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrLessThan(String value) {
            addCriterion("fdr <", value, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrLessThanOrEqualTo(String value) {
            addCriterion("fdr <=", value, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrLike(String value) {
            addCriterion("fdr like", value, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrNotLike(String value) {
            addCriterion("fdr not like", value, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrIn(List<String> values) {
            addCriterion("fdr in", values, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrNotIn(List<String> values) {
            addCriterion("fdr not in", values, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrBetween(String value1, String value2) {
            addCriterion("fdr between", value1, value2, "fdr");
            return (Criteria) this;
        }

        public Criteria andFdrNotBetween(String value1, String value2) {
            addCriterion("fdr not between", value1, value2, "fdr");
            return (Criteria) this;
        }

        public Criteria andMaxdayIsNull() {
            addCriterion("maxday is null");
            return (Criteria) this;
        }

        public Criteria andMaxdayIsNotNull() {
            addCriterion("maxday is not null");
            return (Criteria) this;
        }

        public Criteria andMaxdayEqualTo(Integer value) {
            addCriterion("maxday =", value, "maxday");
            return (Criteria) this;
        }

        public Criteria andMaxdayNotEqualTo(Integer value) {
            addCriterion("maxday <>", value, "maxday");
            return (Criteria) this;
        }

        public Criteria andMaxdayGreaterThan(Integer value) {
            addCriterion("maxday >", value, "maxday");
            return (Criteria) this;
        }

        public Criteria andMaxdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxday >=", value, "maxday");
            return (Criteria) this;
        }

        public Criteria andMaxdayLessThan(Integer value) {
            addCriterion("maxday <", value, "maxday");
            return (Criteria) this;
        }

        public Criteria andMaxdayLessThanOrEqualTo(Integer value) {
            addCriterion("maxday <=", value, "maxday");
            return (Criteria) this;
        }

        public Criteria andMaxdayIn(List<Integer> values) {
            addCriterion("maxday in", values, "maxday");
            return (Criteria) this;
        }

        public Criteria andMaxdayNotIn(List<Integer> values) {
            addCriterion("maxday not in", values, "maxday");
            return (Criteria) this;
        }

        public Criteria andMaxdayBetween(Integer value1, Integer value2) {
            addCriterion("maxday between", value1, value2, "maxday");
            return (Criteria) this;
        }

        public Criteria andMaxdayNotBetween(Integer value1, Integer value2) {
            addCriterion("maxday not between", value1, value2, "maxday");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatemanIsNull() {
            addCriterion("createman is null");
            return (Criteria) this;
        }

        public Criteria andCreatemanIsNotNull() {
            addCriterion("createman is not null");
            return (Criteria) this;
        }

        public Criteria andCreatemanEqualTo(String value) {
            addCriterion("createman =", value, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanNotEqualTo(String value) {
            addCriterion("createman <>", value, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanGreaterThan(String value) {
            addCriterion("createman >", value, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanGreaterThanOrEqualTo(String value) {
            addCriterion("createman >=", value, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanLessThan(String value) {
            addCriterion("createman <", value, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanLessThanOrEqualTo(String value) {
            addCriterion("createman <=", value, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanLike(String value) {
            addCriterion("createman like", value, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanNotLike(String value) {
            addCriterion("createman not like", value, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanIn(List<String> values) {
            addCriterion("createman in", values, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanNotIn(List<String> values) {
            addCriterion("createman not in", values, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanBetween(String value1, String value2) {
            addCriterion("createman between", value1, value2, "createman");
            return (Criteria) this;
        }

        public Criteria andCreatemanNotBetween(String value1, String value2) {
            addCriterion("createman not between", value1, value2, "createman");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatemanIsNull() {
            addCriterion("updateman is null");
            return (Criteria) this;
        }

        public Criteria andUpdatemanIsNotNull() {
            addCriterion("updateman is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatemanEqualTo(String value) {
            addCriterion("updateman =", value, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanNotEqualTo(String value) {
            addCriterion("updateman <>", value, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanGreaterThan(String value) {
            addCriterion("updateman >", value, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanGreaterThanOrEqualTo(String value) {
            addCriterion("updateman >=", value, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanLessThan(String value) {
            addCriterion("updateman <", value, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanLessThanOrEqualTo(String value) {
            addCriterion("updateman <=", value, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanLike(String value) {
            addCriterion("updateman like", value, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanNotLike(String value) {
            addCriterion("updateman not like", value, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanIn(List<String> values) {
            addCriterion("updateman in", values, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanNotIn(List<String> values) {
            addCriterion("updateman not in", values, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanBetween(String value1, String value2) {
            addCriterion("updateman between", value1, value2, "updateman");
            return (Criteria) this;
        }

        public Criteria andUpdatemanNotBetween(String value1, String value2) {
            addCriterion("updateman not between", value1, value2, "updateman");
            return (Criteria) this;
        }

        public Criteria andYyztIsNull() {
            addCriterion("yyzt is null");
            return (Criteria) this;
        }

        public Criteria andYyztIsNotNull() {
            addCriterion("yyzt is not null");
            return (Criteria) this;
        }

        public Criteria andYyztEqualTo(String value) {
            addCriterion("yyzt =", value, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztNotEqualTo(String value) {
            addCriterion("yyzt <>", value, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztGreaterThan(String value) {
            addCriterion("yyzt >", value, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztGreaterThanOrEqualTo(String value) {
            addCriterion("yyzt >=", value, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztLessThan(String value) {
            addCriterion("yyzt <", value, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztLessThanOrEqualTo(String value) {
            addCriterion("yyzt <=", value, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztLike(String value) {
            addCriterion("yyzt like", value, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztNotLike(String value) {
            addCriterion("yyzt not like", value, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztIn(List<String> values) {
            addCriterion("yyzt in", values, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztNotIn(List<String> values) {
            addCriterion("yyzt not in", values, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztBetween(String value1, String value2) {
            addCriterion("yyzt between", value1, value2, "yyzt");
            return (Criteria) this;
        }

        public Criteria andYyztNotBetween(String value1, String value2) {
            addCriterion("yyzt not between", value1, value2, "yyzt");
            return (Criteria) this;
        }

        public Criteria andGryymaxIsNull() {
            addCriterion("gryymax is null");
            return (Criteria) this;
        }

        public Criteria andGryymaxIsNotNull() {
            addCriterion("gryymax is not null");
            return (Criteria) this;
        }

        public Criteria andGryymaxEqualTo(Integer value) {
            addCriterion("gryymax =", value, "gryymax");
            return (Criteria) this;
        }

        public Criteria andGryymaxNotEqualTo(Integer value) {
            addCriterion("gryymax <>", value, "gryymax");
            return (Criteria) this;
        }

        public Criteria andGryymaxGreaterThan(Integer value) {
            addCriterion("gryymax >", value, "gryymax");
            return (Criteria) this;
        }

        public Criteria andGryymaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("gryymax >=", value, "gryymax");
            return (Criteria) this;
        }

        public Criteria andGryymaxLessThan(Integer value) {
            addCriterion("gryymax <", value, "gryymax");
            return (Criteria) this;
        }

        public Criteria andGryymaxLessThanOrEqualTo(Integer value) {
            addCriterion("gryymax <=", value, "gryymax");
            return (Criteria) this;
        }

        public Criteria andGryymaxIn(List<Integer> values) {
            addCriterion("gryymax in", values, "gryymax");
            return (Criteria) this;
        }

        public Criteria andGryymaxNotIn(List<Integer> values) {
            addCriterion("gryymax not in", values, "gryymax");
            return (Criteria) this;
        }

        public Criteria andGryymaxBetween(Integer value1, Integer value2) {
            addCriterion("gryymax between", value1, value2, "gryymax");
            return (Criteria) this;
        }

        public Criteria andGryymaxNotBetween(Integer value1, Integer value2) {
            addCriterion("gryymax not between", value1, value2, "gryymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxIsNull() {
            addCriterion("qyyymax is null");
            return (Criteria) this;
        }

        public Criteria andQyyymaxIsNotNull() {
            addCriterion("qyyymax is not null");
            return (Criteria) this;
        }

        public Criteria andQyyymaxEqualTo(Integer value) {
            addCriterion("qyyymax =", value, "qyyymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxNotEqualTo(Integer value) {
            addCriterion("qyyymax <>", value, "qyyymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxGreaterThan(Integer value) {
            addCriterion("qyyymax >", value, "qyyymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("qyyymax >=", value, "qyyymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxLessThan(Integer value) {
            addCriterion("qyyymax <", value, "qyyymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxLessThanOrEqualTo(Integer value) {
            addCriterion("qyyymax <=", value, "qyyymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxIn(List<Integer> values) {
            addCriterion("qyyymax in", values, "qyyymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxNotIn(List<Integer> values) {
            addCriterion("qyyymax not in", values, "qyyymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxBetween(Integer value1, Integer value2) {
            addCriterion("qyyymax between", value1, value2, "qyyymax");
            return (Criteria) this;
        }

        public Criteria andQyyymaxNotBetween(Integer value1, Integer value2) {
            addCriterion("qyyymax not between", value1, value2, "qyyymax");
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