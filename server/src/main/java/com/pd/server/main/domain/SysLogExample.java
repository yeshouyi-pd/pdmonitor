package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysLogExample() {
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

        public Criteria andCzrIsNull() {
            addCriterion("czr is null");
            return (Criteria) this;
        }

        public Criteria andCzrIsNotNull() {
            addCriterion("czr is not null");
            return (Criteria) this;
        }

        public Criteria andCzrEqualTo(String value) {
            addCriterion("czr =", value, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrNotEqualTo(String value) {
            addCriterion("czr <>", value, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrGreaterThan(String value) {
            addCriterion("czr >", value, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrGreaterThanOrEqualTo(String value) {
            addCriterion("czr >=", value, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrLessThan(String value) {
            addCriterion("czr <", value, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrLessThanOrEqualTo(String value) {
            addCriterion("czr <=", value, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrLike(String value) {
            addCriterion("czr like", value, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrNotLike(String value) {
            addCriterion("czr not like", value, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrIn(List<String> values) {
            addCriterion("czr in", values, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrNotIn(List<String> values) {
            addCriterion("czr not in", values, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrBetween(String value1, String value2) {
            addCriterion("czr between", value1, value2, "czr");
            return (Criteria) this;
        }

        public Criteria andCzrNotBetween(String value1, String value2) {
            addCriterion("czr not between", value1, value2, "czr");
            return (Criteria) this;
        }

        public Criteria andCzipIsNull() {
            addCriterion("czip is null");
            return (Criteria) this;
        }

        public Criteria andCzipIsNotNull() {
            addCriterion("czip is not null");
            return (Criteria) this;
        }

        public Criteria andCzipEqualTo(String value) {
            addCriterion("czip =", value, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipNotEqualTo(String value) {
            addCriterion("czip <>", value, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipGreaterThan(String value) {
            addCriterion("czip >", value, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipGreaterThanOrEqualTo(String value) {
            addCriterion("czip >=", value, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipLessThan(String value) {
            addCriterion("czip <", value, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipLessThanOrEqualTo(String value) {
            addCriterion("czip <=", value, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipLike(String value) {
            addCriterion("czip like", value, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipNotLike(String value) {
            addCriterion("czip not like", value, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipIn(List<String> values) {
            addCriterion("czip in", values, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipNotIn(List<String> values) {
            addCriterion("czip not in", values, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipBetween(String value1, String value2) {
            addCriterion("czip between", value1, value2, "czip");
            return (Criteria) this;
        }

        public Criteria andCzipNotBetween(String value1, String value2) {
            addCriterion("czip not between", value1, value2, "czip");
            return (Criteria) this;
        }

        public Criteria andCzcdIsNull() {
            addCriterion("czcd is null");
            return (Criteria) this;
        }

        public Criteria andCzcdIsNotNull() {
            addCriterion("czcd is not null");
            return (Criteria) this;
        }

        public Criteria andCzcdEqualTo(String value) {
            addCriterion("czcd =", value, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdNotEqualTo(String value) {
            addCriterion("czcd <>", value, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdGreaterThan(String value) {
            addCriterion("czcd >", value, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdGreaterThanOrEqualTo(String value) {
            addCriterion("czcd >=", value, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdLessThan(String value) {
            addCriterion("czcd <", value, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdLessThanOrEqualTo(String value) {
            addCriterion("czcd <=", value, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdLike(String value) {
            addCriterion("czcd like", value, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdNotLike(String value) {
            addCriterion("czcd not like", value, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdIn(List<String> values) {
            addCriterion("czcd in", values, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdNotIn(List<String> values) {
            addCriterion("czcd not in", values, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdBetween(String value1, String value2) {
            addCriterion("czcd between", value1, value2, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzcdNotBetween(String value1, String value2) {
            addCriterion("czcd not between", value1, value2, "czcd");
            return (Criteria) this;
        }

        public Criteria andCzsmIsNull() {
            addCriterion("czsm is null");
            return (Criteria) this;
        }

        public Criteria andCzsmIsNotNull() {
            addCriterion("czsm is not null");
            return (Criteria) this;
        }

        public Criteria andCzsmEqualTo(String value) {
            addCriterion("czsm =", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmNotEqualTo(String value) {
            addCriterion("czsm <>", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmGreaterThan(String value) {
            addCriterion("czsm >", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmGreaterThanOrEqualTo(String value) {
            addCriterion("czsm >=", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmLessThan(String value) {
            addCriterion("czsm <", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmLessThanOrEqualTo(String value) {
            addCriterion("czsm <=", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmLike(String value) {
            addCriterion("czsm like", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmNotLike(String value) {
            addCriterion("czsm not like", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmIn(List<String> values) {
            addCriterion("czsm in", values, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmNotIn(List<String> values) {
            addCriterion("czsm not in", values, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmBetween(String value1, String value2) {
            addCriterion("czsm between", value1, value2, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmNotBetween(String value1, String value2) {
            addCriterion("czsm not between", value1, value2, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzjgIsNull() {
            addCriterion("czjg is null");
            return (Criteria) this;
        }

        public Criteria andCzjgIsNotNull() {
            addCriterion("czjg is not null");
            return (Criteria) this;
        }

        public Criteria andCzjgEqualTo(String value) {
            addCriterion("czjg =", value, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgNotEqualTo(String value) {
            addCriterion("czjg <>", value, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgGreaterThan(String value) {
            addCriterion("czjg >", value, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgGreaterThanOrEqualTo(String value) {
            addCriterion("czjg >=", value, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgLessThan(String value) {
            addCriterion("czjg <", value, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgLessThanOrEqualTo(String value) {
            addCriterion("czjg <=", value, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgLike(String value) {
            addCriterion("czjg like", value, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgNotLike(String value) {
            addCriterion("czjg not like", value, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgIn(List<String> values) {
            addCriterion("czjg in", values, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgNotIn(List<String> values) {
            addCriterion("czjg not in", values, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgBetween(String value1, String value2) {
            addCriterion("czjg between", value1, value2, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzjgNotBetween(String value1, String value2) {
            addCriterion("czjg not between", value1, value2, "czjg");
            return (Criteria) this;
        }

        public Criteria andCzsbyyIsNull() {
            addCriterion("czsbyy is null");
            return (Criteria) this;
        }

        public Criteria andCzsbyyIsNotNull() {
            addCriterion("czsbyy is not null");
            return (Criteria) this;
        }

        public Criteria andCzsbyyEqualTo(String value) {
            addCriterion("czsbyy =", value, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyNotEqualTo(String value) {
            addCriterion("czsbyy <>", value, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyGreaterThan(String value) {
            addCriterion("czsbyy >", value, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyGreaterThanOrEqualTo(String value) {
            addCriterion("czsbyy >=", value, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyLessThan(String value) {
            addCriterion("czsbyy <", value, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyLessThanOrEqualTo(String value) {
            addCriterion("czsbyy <=", value, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyLike(String value) {
            addCriterion("czsbyy like", value, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyNotLike(String value) {
            addCriterion("czsbyy not like", value, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyIn(List<String> values) {
            addCriterion("czsbyy in", values, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyNotIn(List<String> values) {
            addCriterion("czsbyy not in", values, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyBetween(String value1, String value2) {
            addCriterion("czsbyy between", value1, value2, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsbyyNotBetween(String value1, String value2) {
            addCriterion("czsbyy not between", value1, value2, "czsbyy");
            return (Criteria) this;
        }

        public Criteria andCzsjIsNull() {
            addCriterion("czsj is null");
            return (Criteria) this;
        }

        public Criteria andCzsjIsNotNull() {
            addCriterion("czsj is not null");
            return (Criteria) this;
        }

        public Criteria andCzsjEqualTo(Date value) {
            addCriterion("czsj =", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjNotEqualTo(Date value) {
            addCriterion("czsj <>", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjGreaterThan(Date value) {
            addCriterion("czsj >", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjGreaterThanOrEqualTo(Date value) {
            addCriterion("czsj >=", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjGreaterThanOrEqualTo(String value) {
            addCriterion(" DATE_FORMAT(czsj,'%Y-%m-%d') >=", value, "czsj");
            return (Criteria) this;
        }
        public Criteria andCzsjLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(czsj,'%Y-%m-%d')  <=", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjLessThan(Date value) {
            addCriterion("czsj <", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjLessThanOrEqualTo(Date value) {
            addCriterion("czsj <=", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjIn(List<Date> values) {
            addCriterion("czsj in", values, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjNotIn(List<Date> values) {
            addCriterion("czsj not in", values, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjBetween(Date value1, Date value2) {
            addCriterion("czsj between", value1, value2, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjNotBetween(Date value1, Date value2) {
            addCriterion("czsj not between", value1, value2, "czsj");
            return (Criteria) this;
        }

        public Criteria andCztjIsNull() {
            addCriterion("cztj is null");
            return (Criteria) this;
        }

        public Criteria andCztjIsNotNull() {
            addCriterion("cztj is not null");
            return (Criteria) this;
        }

        public Criteria andCztjEqualTo(String value) {
            addCriterion("cztj =", value, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjNotEqualTo(String value) {
            addCriterion("cztj <>", value, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjGreaterThan(String value) {
            addCriterion("cztj >", value, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjGreaterThanOrEqualTo(String value) {
            addCriterion("cztj >=", value, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjLessThan(String value) {
            addCriterion("cztj <", value, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjLessThanOrEqualTo(String value) {
            addCriterion("cztj <=", value, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjLike(String value) {
            addCriterion("cztj like", value, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjNotLike(String value) {
            addCriterion("cztj not like", value, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjIn(List<String> values) {
            addCriterion("cztj in", values, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjNotIn(List<String> values) {
            addCriterion("cztj not in", values, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjBetween(String value1, String value2) {
            addCriterion("cztj between", value1, value2, "cztj");
            return (Criteria) this;
        }

        public Criteria andCztjNotBetween(String value1, String value2) {
            addCriterion("cztj not between", value1, value2, "cztj");
            return (Criteria) this;
        }

        public Criteria andQqsjIsNull() {
            addCriterion("qqsj is null");
            return (Criteria) this;
        }

        public Criteria andQqsjIsNotNull() {
            addCriterion("qqsj is not null");
            return (Criteria) this;
        }

        public Criteria andQqsjEqualTo(String value) {
            addCriterion("qqsj =", value, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjNotEqualTo(String value) {
            addCriterion("qqsj <>", value, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjGreaterThan(String value) {
            addCriterion("qqsj >", value, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjGreaterThanOrEqualTo(String value) {
            addCriterion("qqsj >=", value, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjLessThan(String value) {
            addCriterion("qqsj <", value, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjLessThanOrEqualTo(String value) {
            addCriterion("qqsj <=", value, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjLike(String value) {
            addCriterion("qqsj like", value, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjNotLike(String value) {
            addCriterion("qqsj not like", value, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjIn(List<String> values) {
            addCriterion("qqsj in", values, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjNotIn(List<String> values) {
            addCriterion("qqsj not in", values, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjBetween(String value1, String value2) {
            addCriterion("qqsj between", value1, value2, "qqsj");
            return (Criteria) this;
        }

        public Criteria andQqsjNotBetween(String value1, String value2) {
            addCriterion("qqsj not between", value1, value2, "qqsj");
            return (Criteria) this;
        }

        public Criteria andFfsjIsNull() {
            addCriterion("ffsj is null");
            return (Criteria) this;
        }

        public Criteria andFfsjIsNotNull() {
            addCriterion("ffsj is not null");
            return (Criteria) this;
        }

        public Criteria andFfsjEqualTo(String value) {
            addCriterion("ffsj =", value, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjNotEqualTo(String value) {
            addCriterion("ffsj <>", value, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjGreaterThan(String value) {
            addCriterion("ffsj >", value, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjGreaterThanOrEqualTo(String value) {
            addCriterion("ffsj >=", value, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjLessThan(String value) {
            addCriterion("ffsj <", value, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjLessThanOrEqualTo(String value) {
            addCriterion("ffsj <=", value, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjLike(String value) {
            addCriterion("ffsj like", value, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjNotLike(String value) {
            addCriterion("ffsj not like", value, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjIn(List<String> values) {
            addCriterion("ffsj in", values, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjNotIn(List<String> values) {
            addCriterion("ffsj not in", values, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjBetween(String value1, String value2) {
            addCriterion("ffsj between", value1, value2, "ffsj");
            return (Criteria) this;
        }

        public Criteria andFfsjNotBetween(String value1, String value2) {
            addCriterion("ffsj not between", value1, value2, "ffsj");
            return (Criteria) this;
        }

        public Criteria andRzlxIsNull() {
            addCriterion("rzlx is null");
            return (Criteria) this;
        }

        public Criteria andRzlxIsNotNull() {
            addCriterion("rzlx is not null");
            return (Criteria) this;
        }

        public Criteria andRzlxEqualTo(String value) {
            addCriterion("rzlx =", value, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxNotEqualTo(String value) {
            addCriterion("rzlx <>", value, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxGreaterThan(String value) {
            addCriterion("rzlx >", value, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxGreaterThanOrEqualTo(String value) {
            addCriterion("rzlx >=", value, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxLessThan(String value) {
            addCriterion("rzlx <", value, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxLessThanOrEqualTo(String value) {
            addCriterion("rzlx <=", value, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxLike(String value) {
            addCriterion("rzlx like", value, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxNotLike(String value) {
            addCriterion("rzlx not like", value, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxIn(List<String> values) {
            addCriterion("rzlx in", values, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxNotIn(List<String> values) {
            addCriterion("rzlx not in", values, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxBetween(String value1, String value2) {
            addCriterion("rzlx between", value1, value2, "rzlx");
            return (Criteria) this;
        }

        public Criteria andRzlxNotBetween(String value1, String value2) {
            addCriterion("rzlx not between", value1, value2, "rzlx");
            return (Criteria) this;
        }

        public Criteria andYjIsNull() {
            addCriterion("yj is null");
            return (Criteria) this;
        }

        public Criteria andYjIsNotNull() {
            addCriterion("yj is not null");
            return (Criteria) this;
        }

        public Criteria andYjEqualTo(String value) {
            addCriterion("yj =", value, "yj");
            return (Criteria) this;
        }

        public Criteria andYjNotEqualTo(String value) {
            addCriterion("yj <>", value, "yj");
            return (Criteria) this;
        }

        public Criteria andYjGreaterThan(String value) {
            addCriterion("yj >", value, "yj");
            return (Criteria) this;
        }

        public Criteria andYjGreaterThanOrEqualTo(String value) {
            addCriterion("yj >=", value, "yj");
            return (Criteria) this;
        }

        public Criteria andYjLessThan(String value) {
            addCriterion("yj <", value, "yj");
            return (Criteria) this;
        }

        public Criteria andYjLessThanOrEqualTo(String value) {
            addCriterion("yj <=", value, "yj");
            return (Criteria) this;
        }

        public Criteria andYjLike(String value) {
            addCriterion("yj like", value, "yj");
            return (Criteria) this;
        }

        public Criteria andYjNotLike(String value) {
            addCriterion("yj not like", value, "yj");
            return (Criteria) this;
        }

        public Criteria andYjIn(List<String> values) {
            addCriterion("yj in", values, "yj");
            return (Criteria) this;
        }

        public Criteria andYjNotIn(List<String> values) {
            addCriterion("yj not in", values, "yj");
            return (Criteria) this;
        }

        public Criteria andYjBetween(String value1, String value2) {
            addCriterion("yj between", value1, value2, "yj");
            return (Criteria) this;
        }

        public Criteria andYjNotBetween(String value1, String value2) {
            addCriterion("yj not between", value1, value2, "yj");
            return (Criteria) this;
        }

        public Criteria andF1IsNull() {
            addCriterion("f1 is null");
            return (Criteria) this;
        }

        public Criteria andF1IsNotNull() {
            addCriterion("f1 is not null");
            return (Criteria) this;
        }

        public Criteria andF1EqualTo(String value) {
            addCriterion("f1 =", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1NotEqualTo(String value) {
            addCriterion("f1 <>", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1GreaterThan(String value) {
            addCriterion("f1 >", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1GreaterThanOrEqualTo(String value) {
            addCriterion("f1 >=", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1LessThan(String value) {
            addCriterion("f1 <", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1LessThanOrEqualTo(String value) {
            addCriterion("f1 <=", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1Like(String value) {
            addCriterion("f1 like", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1NotLike(String value) {
            addCriterion("f1 not like", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1In(List<String> values) {
            addCriterion("f1 in", values, "f1");
            return (Criteria) this;
        }

        public Criteria andF1NotIn(List<String> values) {
            addCriterion("f1 not in", values, "f1");
            return (Criteria) this;
        }

        public Criteria andF1Between(String value1, String value2) {
            addCriterion("f1 between", value1, value2, "f1");
            return (Criteria) this;
        }

        public Criteria andF1NotBetween(String value1, String value2) {
            addCriterion("f1 not between", value1, value2, "f1");
            return (Criteria) this;
        }

        public Criteria andF2IsNull() {
            addCriterion("f2 is null");
            return (Criteria) this;
        }

        public Criteria andF2IsNotNull() {
            addCriterion("f2 is not null");
            return (Criteria) this;
        }

        public Criteria andF2EqualTo(String value) {
            addCriterion("f2 =", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2NotEqualTo(String value) {
            addCriterion("f2 <>", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2GreaterThan(String value) {
            addCriterion("f2 >", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2GreaterThanOrEqualTo(String value) {
            addCriterion("f2 >=", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2LessThan(String value) {
            addCriterion("f2 <", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2LessThanOrEqualTo(String value) {
            addCriterion("f2 <=", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2Like(String value) {
            addCriterion("f2 like", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2NotLike(String value) {
            addCriterion("f2 not like", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2In(List<String> values) {
            addCriterion("f2 in", values, "f2");
            return (Criteria) this;
        }

        public Criteria andF2NotIn(List<String> values) {
            addCriterion("f2 not in", values, "f2");
            return (Criteria) this;
        }

        public Criteria andF2Between(String value1, String value2) {
            addCriterion("f2 between", value1, value2, "f2");
            return (Criteria) this;
        }

        public Criteria andF2NotBetween(String value1, String value2) {
            addCriterion("f2 not between", value1, value2, "f2");
            return (Criteria) this;
        }

        public Criteria andJywIsNull() {
            addCriterion("jyw is null");
            return (Criteria) this;
        }

        public Criteria andJywIsNotNull() {
            addCriterion("jyw is not null");
            return (Criteria) this;
        }

        public Criteria andJywEqualTo(String value) {
            addCriterion("jyw =", value, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywNotEqualTo(String value) {
            addCriterion("jyw <>", value, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywGreaterThan(String value) {
            addCriterion("jyw >", value, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywGreaterThanOrEqualTo(String value) {
            addCriterion("jyw >=", value, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywLessThan(String value) {
            addCriterion("jyw <", value, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywLessThanOrEqualTo(String value) {
            addCriterion("jyw <=", value, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywLike(String value) {
            addCriterion("jyw like", value, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywNotLike(String value) {
            addCriterion("jyw not like", value, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywIn(List<String> values) {
            addCriterion("jyw in", values, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywNotIn(List<String> values) {
            addCriterion("jyw not in", values, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywBetween(String value1, String value2) {
            addCriterion("jyw between", value1, value2, "jyw");
            return (Criteria) this;
        }

        public Criteria andJywNotBetween(String value1, String value2) {
            addCriterion("jyw not between", value1, value2, "jyw");
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