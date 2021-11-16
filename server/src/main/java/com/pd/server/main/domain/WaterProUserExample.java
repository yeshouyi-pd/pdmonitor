package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterProUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WaterProUserExample() {
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

        public Criteria andUsercodeIsNull() {
            addCriterion("usercode is null");
            return (Criteria) this;
        }

        public Criteria andUsercodeIsNotNull() {
            addCriterion("usercode is not null");
            return (Criteria) this;
        }

        public Criteria andUsercodeEqualTo(String value) {
            addCriterion("usercode =", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeNotEqualTo(String value) {
            addCriterion("usercode <>", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeGreaterThan(String value) {
            addCriterion("usercode >", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeGreaterThanOrEqualTo(String value) {
            addCriterion("usercode >=", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeLessThan(String value) {
            addCriterion("usercode <", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeLessThanOrEqualTo(String value) {
            addCriterion("usercode <=", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeLike(String value) {
            addCriterion("usercode like", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeNotLike(String value) {
            addCriterion("usercode not like", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeIn(List<String> values) {
            addCriterion("usercode in", values, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeNotIn(List<String> values) {
            addCriterion("usercode not in", values, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeBetween(String value1, String value2) {
            addCriterion("usercode between", value1, value2, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeNotBetween(String value1, String value2) {
            addCriterion("usercode not between", value1, value2, "usercode");
            return (Criteria) this;
        }

        public Criteria andCjrIsNull() {
            addCriterion("cjr is null");
            return (Criteria) this;
        }

        public Criteria andCjrIsNotNull() {
            addCriterion("cjr is not null");
            return (Criteria) this;
        }

        public Criteria andCjrEqualTo(String value) {
            addCriterion("cjr =", value, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrNotEqualTo(String value) {
            addCriterion("cjr <>", value, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrGreaterThan(String value) {
            addCriterion("cjr >", value, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrGreaterThanOrEqualTo(String value) {
            addCriterion("cjr >=", value, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrLessThan(String value) {
            addCriterion("cjr <", value, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrLessThanOrEqualTo(String value) {
            addCriterion("cjr <=", value, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrLike(String value) {
            addCriterion("cjr like", value, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrNotLike(String value) {
            addCriterion("cjr not like", value, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrIn(List<String> values) {
            addCriterion("cjr in", values, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrNotIn(List<String> values) {
            addCriterion("cjr not in", values, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrBetween(String value1, String value2) {
            addCriterion("cjr between", value1, value2, "cjr");
            return (Criteria) this;
        }

        public Criteria andCjrNotBetween(String value1, String value2) {
            addCriterion("cjr not between", value1, value2, "cjr");
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

        public Criteria andIsbossIsNull() {
            addCriterion("isBoss is null");
            return (Criteria) this;
        }

        public Criteria andIsbossIsNotNull() {
            addCriterion("isBoss is not null");
            return (Criteria) this;
        }

        public Criteria andIsbossEqualTo(String value) {
            addCriterion("isBoss =", value, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossNotEqualTo(String value) {
            addCriterion("isBoss <>", value, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossGreaterThan(String value) {
            addCriterion("isBoss >", value, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossGreaterThanOrEqualTo(String value) {
            addCriterion("isBoss >=", value, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossLessThan(String value) {
            addCriterion("isBoss <", value, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossLessThanOrEqualTo(String value) {
            addCriterion("isBoss <=", value, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossLike(String value) {
            addCriterion("isBoss like", value, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossNotLike(String value) {
            addCriterion("isBoss not like", value, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossIn(List<String> values) {
            addCriterion("isBoss in", values, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossNotIn(List<String> values) {
            addCriterion("isBoss not in", values, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossBetween(String value1, String value2) {
            addCriterion("isBoss between", value1, value2, "isboss");
            return (Criteria) this;
        }

        public Criteria andIsbossNotBetween(String value1, String value2) {
            addCriterion("isBoss not between", value1, value2, "isboss");
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