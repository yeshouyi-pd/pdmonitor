package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppMonitorInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppMonitorInfoExample() {
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

        public Criteria andKcclxIsNull() {
            addCriterion("kcclx is null");
            return (Criteria) this;
        }

        public Criteria andKcclxIsNotNull() {
            addCriterion("kcclx is not null");
            return (Criteria) this;
        }

        public Criteria andKcclxEqualTo(String value) {
            addCriterion("kcclx =", value, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxNotEqualTo(String value) {
            addCriterion("kcclx <>", value, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxGreaterThan(String value) {
            addCriterion("kcclx >", value, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxGreaterThanOrEqualTo(String value) {
            addCriterion("kcclx >=", value, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxLessThan(String value) {
            addCriterion("kcclx <", value, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxLessThanOrEqualTo(String value) {
            addCriterion("kcclx <=", value, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxLike(String value) {
            addCriterion("kcclx like", value, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxNotLike(String value) {
            addCriterion("kcclx not like", value, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxIn(List<String> values) {
            addCriterion("kcclx in", values, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxNotIn(List<String> values) {
            addCriterion("kcclx not in", values, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxBetween(String value1, String value2) {
            addCriterion("kcclx between", value1, value2, "kcclx");
            return (Criteria) this;
        }

        public Criteria andKcclxNotBetween(String value1, String value2) {
            addCriterion("kcclx not between", value1, value2, "kcclx");
            return (Criteria) this;
        }

        public Criteria andGczxmIsNull() {
            addCriterion("gczxm is null");
            return (Criteria) this;
        }

        public Criteria andGczxmIsNotNull() {
            addCriterion("gczxm is not null");
            return (Criteria) this;
        }

        public Criteria andGczxmEqualTo(String value) {
            addCriterion("gczxm =", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmNotEqualTo(String value) {
            addCriterion("gczxm <>", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmGreaterThan(String value) {
            addCriterion("gczxm >", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmGreaterThanOrEqualTo(String value) {
            addCriterion("gczxm >=", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmLessThan(String value) {
            addCriterion("gczxm <", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmLessThanOrEqualTo(String value) {
            addCriterion("gczxm <=", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmLike(String value) {
            addCriterion("gczxm like", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmNotLike(String value) {
            addCriterion("gczxm not like", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmIn(List<String> values) {
            addCriterion("gczxm in", values, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmNotIn(List<String> values) {
            addCriterion("gczxm not in", values, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmBetween(String value1, String value2) {
            addCriterion("gczxm between", value1, value2, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmNotBetween(String value1, String value2) {
            addCriterion("gczxm not between", value1, value2, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGcqyIsNull() {
            addCriterion("gcqy is null");
            return (Criteria) this;
        }

        public Criteria andGcqyIsNotNull() {
            addCriterion("gcqy is not null");
            return (Criteria) this;
        }

        public Criteria andGcqyEqualTo(String value) {
            addCriterion("gcqy =", value, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyNotEqualTo(String value) {
            addCriterion("gcqy <>", value, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyGreaterThan(String value) {
            addCriterion("gcqy >", value, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyGreaterThanOrEqualTo(String value) {
            addCriterion("gcqy >=", value, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyLessThan(String value) {
            addCriterion("gcqy <", value, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyLessThanOrEqualTo(String value) {
            addCriterion("gcqy <=", value, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyLike(String value) {
            addCriterion("gcqy like", value, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyNotLike(String value) {
            addCriterion("gcqy not like", value, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyIn(List<String> values) {
            addCriterion("gcqy in", values, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyNotIn(List<String> values) {
            addCriterion("gcqy not in", values, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyBetween(String value1, String value2) {
            addCriterion("gcqy between", value1, value2, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcqyNotBetween(String value1, String value2) {
            addCriterion("gcqy not between", value1, value2, "gcqy");
            return (Criteria) this;
        }

        public Criteria andGcdwIsNull() {
            addCriterion("gcdw is null");
            return (Criteria) this;
        }

        public Criteria andGcdwIsNotNull() {
            addCriterion("gcdw is not null");
            return (Criteria) this;
        }

        public Criteria andGcdwEqualTo(String value) {
            addCriterion("gcdw =", value, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwNotEqualTo(String value) {
            addCriterion("gcdw <>", value, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwGreaterThan(String value) {
            addCriterion("gcdw >", value, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwGreaterThanOrEqualTo(String value) {
            addCriterion("gcdw >=", value, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwLessThan(String value) {
            addCriterion("gcdw <", value, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwLessThanOrEqualTo(String value) {
            addCriterion("gcdw <=", value, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwLike(String value) {
            addCriterion("gcdw like", value, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwNotLike(String value) {
            addCriterion("gcdw not like", value, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwIn(List<String> values) {
            addCriterion("gcdw in", values, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwNotIn(List<String> values) {
            addCriterion("gcdw not in", values, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwBetween(String value1, String value2) {
            addCriterion("gcdw between", value1, value2, "gcdw");
            return (Criteria) this;
        }

        public Criteria andGcdwNotBetween(String value1, String value2) {
            addCriterion("gcdw not between", value1, value2, "gcdw");
            return (Criteria) this;
        }

        public Criteria andKsgcsjIsNull() {
            addCriterion("ksgcsj is null");
            return (Criteria) this;
        }

        public Criteria andKsgcsjIsNotNull() {
            addCriterion("ksgcsj is not null");
            return (Criteria) this;
        }

        public Criteria andKsgcsjEqualTo(Date value) {
            addCriterion("ksgcsj =", value, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andKsgcsjNotEqualTo(Date value) {
            addCriterion("ksgcsj <>", value, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andKsgcsjGreaterThan(Date value) {
            addCriterion("ksgcsj >", value, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andKsgcsjGreaterThanOrEqualTo(Date value) {
            addCriterion("ksgcsj >=", value, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andKsgcsjLessThan(Date value) {
            addCriterion("ksgcsj <", value, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andKsgcsjLessThanOrEqualTo(Date value) {
            addCriterion("ksgcsj <=", value, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andKsgcsjIn(List<Date> values) {
            addCriterion("ksgcsj in", values, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andKsgcsjNotIn(List<Date> values) {
            addCriterion("ksgcsj not in", values, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andKsgcsjBetween(Date value1, Date value2) {
            addCriterion("ksgcsj between", value1, value2, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andKsgcsjNotBetween(Date value1, Date value2) {
            addCriterion("ksgcsj not between", value1, value2, "ksgcsj");
            return (Criteria) this;
        }

        public Criteria andPZfgczIsNull() {
            addCriterion("p_zfgcz is null");
            return (Criteria) this;
        }

        public Criteria andPZfgczIsNotNull() {
            addCriterion("p_zfgcz is not null");
            return (Criteria) this;
        }

        public Criteria andPZfgczEqualTo(String value) {
            addCriterion("p_zfgcz =", value, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczNotEqualTo(String value) {
            addCriterion("p_zfgcz <>", value, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczGreaterThan(String value) {
            addCriterion("p_zfgcz >", value, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczGreaterThanOrEqualTo(String value) {
            addCriterion("p_zfgcz >=", value, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczLessThan(String value) {
            addCriterion("p_zfgcz <", value, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczLessThanOrEqualTo(String value) {
            addCriterion("p_zfgcz <=", value, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczLike(String value) {
            addCriterion("p_zfgcz like", value, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczNotLike(String value) {
            addCriterion("p_zfgcz not like", value, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczIn(List<String> values) {
            addCriterion("p_zfgcz in", values, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczNotIn(List<String> values) {
            addCriterion("p_zfgcz not in", values, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczBetween(String value1, String value2) {
            addCriterion("p_zfgcz between", value1, value2, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPZfgczNotBetween(String value1, String value2) {
            addCriterion("p_zfgcz not between", value1, value2, "pZfgcz");
            return (Criteria) this;
        }

        public Criteria andPJlzIsNull() {
            addCriterion("p_jlz is null");
            return (Criteria) this;
        }

        public Criteria andPJlzIsNotNull() {
            addCriterion("p_jlz is not null");
            return (Criteria) this;
        }

        public Criteria andPJlzEqualTo(String value) {
            addCriterion("p_jlz =", value, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzNotEqualTo(String value) {
            addCriterion("p_jlz <>", value, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzGreaterThan(String value) {
            addCriterion("p_jlz >", value, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzGreaterThanOrEqualTo(String value) {
            addCriterion("p_jlz >=", value, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzLessThan(String value) {
            addCriterion("p_jlz <", value, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzLessThanOrEqualTo(String value) {
            addCriterion("p_jlz <=", value, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzLike(String value) {
            addCriterion("p_jlz like", value, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzNotLike(String value) {
            addCriterion("p_jlz not like", value, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzIn(List<String> values) {
            addCriterion("p_jlz in", values, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzNotIn(List<String> values) {
            addCriterion("p_jlz not in", values, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzBetween(String value1, String value2) {
            addCriterion("p_jlz between", value1, value2, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPJlzNotBetween(String value1, String value2) {
            addCriterion("p_jlz not between", value1, value2, "pJlz");
            return (Criteria) this;
        }

        public Criteria andPYfgczIsNull() {
            addCriterion("p_yfgcz is null");
            return (Criteria) this;
        }

        public Criteria andPYfgczIsNotNull() {
            addCriterion("p_yfgcz is not null");
            return (Criteria) this;
        }

        public Criteria andPYfgczEqualTo(String value) {
            addCriterion("p_yfgcz =", value, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczNotEqualTo(String value) {
            addCriterion("p_yfgcz <>", value, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczGreaterThan(String value) {
            addCriterion("p_yfgcz >", value, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczGreaterThanOrEqualTo(String value) {
            addCriterion("p_yfgcz >=", value, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczLessThan(String value) {
            addCriterion("p_yfgcz <", value, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczLessThanOrEqualTo(String value) {
            addCriterion("p_yfgcz <=", value, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczLike(String value) {
            addCriterion("p_yfgcz like", value, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczNotLike(String value) {
            addCriterion("p_yfgcz not like", value, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczIn(List<String> values) {
            addCriterion("p_yfgcz in", values, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczNotIn(List<String> values) {
            addCriterion("p_yfgcz not in", values, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczBetween(String value1, String value2) {
            addCriterion("p_yfgcz between", value1, value2, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPYfgczNotBetween(String value1, String value2) {
            addCriterion("p_yfgcz not between", value1, value2, "pYfgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczIsNull() {
            addCriterion("p_dlgcz is null");
            return (Criteria) this;
        }

        public Criteria andPDlgczIsNotNull() {
            addCriterion("p_dlgcz is not null");
            return (Criteria) this;
        }

        public Criteria andPDlgczEqualTo(String value) {
            addCriterion("p_dlgcz =", value, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczNotEqualTo(String value) {
            addCriterion("p_dlgcz <>", value, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczGreaterThan(String value) {
            addCriterion("p_dlgcz >", value, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczGreaterThanOrEqualTo(String value) {
            addCriterion("p_dlgcz >=", value, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczLessThan(String value) {
            addCriterion("p_dlgcz <", value, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczLessThanOrEqualTo(String value) {
            addCriterion("p_dlgcz <=", value, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczLike(String value) {
            addCriterion("p_dlgcz like", value, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczNotLike(String value) {
            addCriterion("p_dlgcz not like", value, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczIn(List<String> values) {
            addCriterion("p_dlgcz in", values, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczNotIn(List<String> values) {
            addCriterion("p_dlgcz not in", values, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczBetween(String value1, String value2) {
            addCriterion("p_dlgcz between", value1, value2, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczNotBetween(String value1, String value2) {
            addCriterion("p_dlgcz not between", value1, value2, "pDlgcz");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffIsNull() {
            addCriterion("p_dlgczsyff is null");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffIsNotNull() {
            addCriterion("p_dlgczsyff is not null");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffEqualTo(String value) {
            addCriterion("p_dlgczsyff =", value, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffNotEqualTo(String value) {
            addCriterion("p_dlgczsyff <>", value, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffGreaterThan(String value) {
            addCriterion("p_dlgczsyff >", value, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffGreaterThanOrEqualTo(String value) {
            addCriterion("p_dlgczsyff >=", value, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffLessThan(String value) {
            addCriterion("p_dlgczsyff <", value, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffLessThanOrEqualTo(String value) {
            addCriterion("p_dlgczsyff <=", value, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffLike(String value) {
            addCriterion("p_dlgczsyff like", value, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffNotLike(String value) {
            addCriterion("p_dlgczsyff not like", value, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffIn(List<String> values) {
            addCriterion("p_dlgczsyff in", values, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffNotIn(List<String> values) {
            addCriterion("p_dlgczsyff not in", values, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffBetween(String value1, String value2) {
            addCriterion("p_dlgczsyff between", value1, value2, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPDlgczsyffNotBetween(String value1, String value2) {
            addCriterion("p_dlgczsyff not between", value1, value2, "pDlgczsyff");
            return (Criteria) this;
        }

        public Criteria andPFjgczIsNull() {
            addCriterion("p_fjgcz is null");
            return (Criteria) this;
        }

        public Criteria andPFjgczIsNotNull() {
            addCriterion("p_fjgcz is not null");
            return (Criteria) this;
        }

        public Criteria andPFjgczEqualTo(String value) {
            addCriterion("p_fjgcz =", value, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczNotEqualTo(String value) {
            addCriterion("p_fjgcz <>", value, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczGreaterThan(String value) {
            addCriterion("p_fjgcz >", value, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczGreaterThanOrEqualTo(String value) {
            addCriterion("p_fjgcz >=", value, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczLessThan(String value) {
            addCriterion("p_fjgcz <", value, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczLessThanOrEqualTo(String value) {
            addCriterion("p_fjgcz <=", value, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczLike(String value) {
            addCriterion("p_fjgcz like", value, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczNotLike(String value) {
            addCriterion("p_fjgcz not like", value, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczIn(List<String> values) {
            addCriterion("p_fjgcz in", values, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczNotIn(List<String> values) {
            addCriterion("p_fjgcz not in", values, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczBetween(String value1, String value2) {
            addCriterion("p_fjgcz between", value1, value2, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andPFjgczNotBetween(String value1, String value2) {
            addCriterion("p_fjgcz not between", value1, value2, "pFjgcz");
            return (Criteria) this;
        }

        public Criteria andWZtgczkIsNull() {
            addCriterion("w_ztgczk is null");
            return (Criteria) this;
        }

        public Criteria andWZtgczkIsNotNull() {
            addCriterion("w_ztgczk is not null");
            return (Criteria) this;
        }

        public Criteria andWZtgczkEqualTo(String value) {
            addCriterion("w_ztgczk =", value, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkNotEqualTo(String value) {
            addCriterion("w_ztgczk <>", value, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkGreaterThan(String value) {
            addCriterion("w_ztgczk >", value, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkGreaterThanOrEqualTo(String value) {
            addCriterion("w_ztgczk >=", value, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkLessThan(String value) {
            addCriterion("w_ztgczk <", value, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkLessThanOrEqualTo(String value) {
            addCriterion("w_ztgczk <=", value, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkLike(String value) {
            addCriterion("w_ztgczk like", value, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkNotLike(String value) {
            addCriterion("w_ztgczk not like", value, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkIn(List<String> values) {
            addCriterion("w_ztgczk in", values, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkNotIn(List<String> values) {
            addCriterion("w_ztgczk not in", values, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkBetween(String value1, String value2) {
            addCriterion("w_ztgczk between", value1, value2, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWZtgczkNotBetween(String value1, String value2) {
            addCriterion("w_ztgczk not between", value1, value2, "wZtgczk");
            return (Criteria) this;
        }

        public Criteria andWXgszfwIsNull() {
            addCriterion("w_xgszfw is null");
            return (Criteria) this;
        }

        public Criteria andWXgszfwIsNotNull() {
            addCriterion("w_xgszfw is not null");
            return (Criteria) this;
        }

        public Criteria andWXgszfwEqualTo(String value) {
            addCriterion("w_xgszfw =", value, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwNotEqualTo(String value) {
            addCriterion("w_xgszfw <>", value, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwGreaterThan(String value) {
            addCriterion("w_xgszfw >", value, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwGreaterThanOrEqualTo(String value) {
            addCriterion("w_xgszfw >=", value, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwLessThan(String value) {
            addCriterion("w_xgszfw <", value, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwLessThanOrEqualTo(String value) {
            addCriterion("w_xgszfw <=", value, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwLike(String value) {
            addCriterion("w_xgszfw like", value, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwNotLike(String value) {
            addCriterion("w_xgszfw not like", value, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwIn(List<String> values) {
            addCriterion("w_xgszfw in", values, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwNotIn(List<String> values) {
            addCriterion("w_xgszfw not in", values, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwBetween(String value1, String value2) {
            addCriterion("w_xgszfw between", value1, value2, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgszfwNotBetween(String value1, String value2) {
            addCriterion("w_xgszfw not between", value1, value2, "wXgszfw");
            return (Criteria) this;
        }

        public Criteria andWXgzkIsNull() {
            addCriterion("w_xgzk is null");
            return (Criteria) this;
        }

        public Criteria andWXgzkIsNotNull() {
            addCriterion("w_xgzk is not null");
            return (Criteria) this;
        }

        public Criteria andWXgzkEqualTo(String value) {
            addCriterion("w_xgzk =", value, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkNotEqualTo(String value) {
            addCriterion("w_xgzk <>", value, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkGreaterThan(String value) {
            addCriterion("w_xgzk >", value, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkGreaterThanOrEqualTo(String value) {
            addCriterion("w_xgzk >=", value, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkLessThan(String value) {
            addCriterion("w_xgzk <", value, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkLessThanOrEqualTo(String value) {
            addCriterion("w_xgzk <=", value, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkLike(String value) {
            addCriterion("w_xgzk like", value, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkNotLike(String value) {
            addCriterion("w_xgzk not like", value, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkIn(List<String> values) {
            addCriterion("w_xgzk in", values, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkNotIn(List<String> values) {
            addCriterion("w_xgzk not in", values, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkBetween(String value1, String value2) {
            addCriterion("w_xgzk between", value1, value2, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andWXgzkNotBetween(String value1, String value2) {
            addCriterion("w_xgzk not between", value1, value2, "wXgzk");
            return (Criteria) this;
        }

        public Criteria andNJajlIsNull() {
            addCriterion("n_jajl is null");
            return (Criteria) this;
        }

        public Criteria andNJajlIsNotNull() {
            addCriterion("n_jajl is not null");
            return (Criteria) this;
        }

        public Criteria andNJajlEqualTo(String value) {
            addCriterion("n_jajl =", value, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlNotEqualTo(String value) {
            addCriterion("n_jajl <>", value, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlGreaterThan(String value) {
            addCriterion("n_jajl >", value, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlGreaterThanOrEqualTo(String value) {
            addCriterion("n_jajl >=", value, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlLessThan(String value) {
            addCriterion("n_jajl <", value, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlLessThanOrEqualTo(String value) {
            addCriterion("n_jajl <=", value, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlLike(String value) {
            addCriterion("n_jajl like", value, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlNotLike(String value) {
            addCriterion("n_jajl not like", value, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlIn(List<String> values) {
            addCriterion("n_jajl in", values, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlNotIn(List<String> values) {
            addCriterion("n_jajl not in", values, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlBetween(String value1, String value2) {
            addCriterion("n_jajl between", value1, value2, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNJajlNotBetween(String value1, String value2) {
            addCriterion("n_jajl not between", value1, value2, "nJajl");
            return (Criteria) this;
        }

        public Criteria andNSsIsNull() {
            addCriterion("n_ss is null");
            return (Criteria) this;
        }

        public Criteria andNSsIsNotNull() {
            addCriterion("n_ss is not null");
            return (Criteria) this;
        }

        public Criteria andNSsEqualTo(String value) {
            addCriterion("n_ss =", value, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsNotEqualTo(String value) {
            addCriterion("n_ss <>", value, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsGreaterThan(String value) {
            addCriterion("n_ss >", value, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsGreaterThanOrEqualTo(String value) {
            addCriterion("n_ss >=", value, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsLessThan(String value) {
            addCriterion("n_ss <", value, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsLessThanOrEqualTo(String value) {
            addCriterion("n_ss <=", value, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsLike(String value) {
            addCriterion("n_ss like", value, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsNotLike(String value) {
            addCriterion("n_ss not like", value, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsIn(List<String> values) {
            addCriterion("n_ss in", values, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsNotIn(List<String> values) {
            addCriterion("n_ss not in", values, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsBetween(String value1, String value2) {
            addCriterion("n_ss between", value1, value2, "nSs");
            return (Criteria) this;
        }

        public Criteria andNSsNotBetween(String value1, String value2) {
            addCriterion("n_ss not between", value1, value2, "nSs");
            return (Criteria) this;
        }

        public Criteria andNCsIsNull() {
            addCriterion("n_cs is null");
            return (Criteria) this;
        }

        public Criteria andNCsIsNotNull() {
            addCriterion("n_cs is not null");
            return (Criteria) this;
        }

        public Criteria andNCsEqualTo(String value) {
            addCriterion("n_cs =", value, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsNotEqualTo(String value) {
            addCriterion("n_cs <>", value, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsGreaterThan(String value) {
            addCriterion("n_cs >", value, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsGreaterThanOrEqualTo(String value) {
            addCriterion("n_cs >=", value, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsLessThan(String value) {
            addCriterion("n_cs <", value, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsLessThanOrEqualTo(String value) {
            addCriterion("n_cs <=", value, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsLike(String value) {
            addCriterion("n_cs like", value, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsNotLike(String value) {
            addCriterion("n_cs not like", value, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsIn(List<String> values) {
            addCriterion("n_cs in", values, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsNotIn(List<String> values) {
            addCriterion("n_cs not in", values, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsBetween(String value1, String value2) {
            addCriterion("n_cs between", value1, value2, "nCs");
            return (Criteria) this;
        }

        public Criteria andNCsNotBetween(String value1, String value2) {
            addCriterion("n_cs not between", value1, value2, "nCs");
            return (Criteria) this;
        }

        public Criteria andNJafxIsNull() {
            addCriterion("n_jafx is null");
            return (Criteria) this;
        }

        public Criteria andNJafxIsNotNull() {
            addCriterion("n_jafx is not null");
            return (Criteria) this;
        }

        public Criteria andNJafxEqualTo(String value) {
            addCriterion("n_jafx =", value, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxNotEqualTo(String value) {
            addCriterion("n_jafx <>", value, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxGreaterThan(String value) {
            addCriterion("n_jafx >", value, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxGreaterThanOrEqualTo(String value) {
            addCriterion("n_jafx >=", value, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxLessThan(String value) {
            addCriterion("n_jafx <", value, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxLessThanOrEqualTo(String value) {
            addCriterion("n_jafx <=", value, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxLike(String value) {
            addCriterion("n_jafx like", value, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxNotLike(String value) {
            addCriterion("n_jafx not like", value, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxIn(List<String> values) {
            addCriterion("n_jafx in", values, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxNotIn(List<String> values) {
            addCriterion("n_jafx not in", values, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxBetween(String value1, String value2) {
            addCriterion("n_jafx between", value1, value2, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNJafxNotBetween(String value1, String value2) {
            addCriterion("n_jafx not between", value1, value2, "nJafx");
            return (Criteria) this;
        }

        public Criteria andNFxIsNull() {
            addCriterion("n_fx is null");
            return (Criteria) this;
        }

        public Criteria andNFxIsNotNull() {
            addCriterion("n_fx is not null");
            return (Criteria) this;
        }

        public Criteria andNFxEqualTo(String value) {
            addCriterion("n_fx =", value, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxNotEqualTo(String value) {
            addCriterion("n_fx <>", value, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxGreaterThan(String value) {
            addCriterion("n_fx >", value, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxGreaterThanOrEqualTo(String value) {
            addCriterion("n_fx >=", value, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxLessThan(String value) {
            addCriterion("n_fx <", value, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxLessThanOrEqualTo(String value) {
            addCriterion("n_fx <=", value, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxLike(String value) {
            addCriterion("n_fx like", value, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxNotLike(String value) {
            addCriterion("n_fx not like", value, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxIn(List<String> values) {
            addCriterion("n_fx in", values, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxNotIn(List<String> values) {
            addCriterion("n_fx not in", values, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxBetween(String value1, String value2) {
            addCriterion("n_fx between", value1, value2, "nFx");
            return (Criteria) this;
        }

        public Criteria andNFxNotBetween(String value1, String value2) {
            addCriterion("n_fx not between", value1, value2, "nFx");
            return (Criteria) this;
        }

        public Criteria andJsgcsjIsNull() {
            addCriterion("jsgcsj is null");
            return (Criteria) this;
        }

        public Criteria andJsgcsjIsNotNull() {
            addCriterion("jsgcsj is not null");
            return (Criteria) this;
        }

        public Criteria andJsgcsjEqualTo(Date value) {
            addCriterion("jsgcsj =", value, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andJsgcsjNotEqualTo(Date value) {
            addCriterion("jsgcsj <>", value, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andJsgcsjGreaterThan(Date value) {
            addCriterion("jsgcsj >", value, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andJsgcsjGreaterThanOrEqualTo(Date value) {
            addCriterion("jsgcsj >=", value, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andJsgcsjLessThan(Date value) {
            addCriterion("jsgcsj <", value, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andJsgcsjLessThanOrEqualTo(Date value) {
            addCriterion("jsgcsj <=", value, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andJsgcsjIn(List<Date> values) {
            addCriterion("jsgcsj in", values, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andJsgcsjNotIn(List<Date> values) {
            addCriterion("jsgcsj not in", values, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andJsgcsjBetween(Date value1, Date value2) {
            addCriterion("jsgcsj between", value1, value2, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andJsgcsjNotBetween(Date value1, Date value2) {
            addCriterion("jsgcsj not between", value1, value2, "jsgcsj");
            return (Criteria) this;
        }

        public Criteria andScsjIsNull() {
            addCriterion("scsj is null");
            return (Criteria) this;
        }

        public Criteria andScsjIsNotNull() {
            addCriterion("scsj is not null");
            return (Criteria) this;
        }

        public Criteria andScsjEqualTo(Date value) {
            addCriterion("scsj =", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotEqualTo(Date value) {
            addCriterion("scsj <>", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjGreaterThan(Date value) {
            addCriterion("scsj >", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjGreaterThanOrEqualTo(Date value) {
            addCriterion("scsj >=", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjLessThan(Date value) {
            addCriterion("scsj <", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjLessThanOrEqualTo(Date value) {
            addCriterion("scsj <=", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjIn(List<Date> values) {
            addCriterion("scsj in", values, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotIn(List<Date> values) {
            addCriterion("scsj not in", values, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjBetween(Date value1, Date value2) {
            addCriterion("scsj between", value1, value2, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotBetween(Date value1, Date value2) {
            addCriterion("scsj not between", value1, value2, "scsj");
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