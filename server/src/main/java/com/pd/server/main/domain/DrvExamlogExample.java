package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DrvExamlogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DrvExamlogExample() {
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

        public Criteria andXmIsNull() {
            addCriterion("xm is null");
            return (Criteria) this;
        }

        public Criteria andXmIsNotNull() {
            addCriterion("xm is not null");
            return (Criteria) this;
        }

        public Criteria andXmEqualTo(String value) {
            addCriterion("xm =", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotEqualTo(String value) {
            addCriterion("xm <>", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmGreaterThan(String value) {
            addCriterion("xm >", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmGreaterThanOrEqualTo(String value) {
            addCriterion("xm >=", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmLessThan(String value) {
            addCriterion("xm <", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmLessThanOrEqualTo(String value) {
            addCriterion("xm <=", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmLike(String value) {
            addCriterion("xm like", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotLike(String value) {
            addCriterion("xm not like", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmIn(List<String> values) {
            addCriterion("xm in", values, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotIn(List<String> values) {
            addCriterion("xm not in", values, "xm");
            return (Criteria) this;
        }

        public Criteria andXmBetween(String value1, String value2) {
            addCriterion("xm between", value1, value2, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotBetween(String value1, String value2) {
            addCriterion("xm not between", value1, value2, "xm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmIsNull() {
            addCriterion("sfzmhm is null");
            return (Criteria) this;
        }

        public Criteria andSfzmhmIsNotNull() {
            addCriterion("sfzmhm is not null");
            return (Criteria) this;
        }

        public Criteria andSfzmhmEqualTo(String value) {
            addCriterion("sfzmhm =", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotEqualTo(String value) {
            addCriterion("sfzmhm <>", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmGreaterThan(String value) {
            addCriterion("sfzmhm >", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmGreaterThanOrEqualTo(String value) {
            addCriterion("sfzmhm >=", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmLessThan(String value) {
            addCriterion("sfzmhm <", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmLessThanOrEqualTo(String value) {
            addCriterion("sfzmhm <=", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmLike(String value) {
            addCriterion("sfzmhm like", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotLike(String value) {
            addCriterion("sfzmhm not like", value, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmIn(List<String> values) {
            addCriterion("sfzmhm in", values, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotIn(List<String> values) {
            addCriterion("sfzmhm not in", values, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmBetween(String value1, String value2) {
            addCriterion("sfzmhm between", value1, value2, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSfzmhmNotBetween(String value1, String value2) {
            addCriterion("sfzmhm not between", value1, value2, "sfzmhm");
            return (Criteria) this;
        }

        public Criteria andSjhIsNull() {
            addCriterion("sjh is null");
            return (Criteria) this;
        }

        public Criteria andSjhIsNotNull() {
            addCriterion("sjh is not null");
            return (Criteria) this;
        }

        public Criteria andSjhEqualTo(String value) {
            addCriterion("sjh =", value, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhNotEqualTo(String value) {
            addCriterion("sjh <>", value, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhGreaterThan(String value) {
            addCriterion("sjh >", value, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhGreaterThanOrEqualTo(String value) {
            addCriterion("sjh >=", value, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhLessThan(String value) {
            addCriterion("sjh <", value, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhLessThanOrEqualTo(String value) {
            addCriterion("sjh <=", value, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhLike(String value) {
            addCriterion("sjh like", value, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhNotLike(String value) {
            addCriterion("sjh not like", value, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhIn(List<String> values) {
            addCriterion("sjh in", values, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhNotIn(List<String> values) {
            addCriterion("sjh not in", values, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhBetween(String value1, String value2) {
            addCriterion("sjh between", value1, value2, "sjh");
            return (Criteria) this;
        }

        public Criteria andSjhNotBetween(String value1, String value2) {
            addCriterion("sjh not between", value1, value2, "sjh");
            return (Criteria) this;
        }

        public Criteria andCfbhIsNull() {
            addCriterion("cfbh is null");
            return (Criteria) this;
        }

        public Criteria andCfbhIsNotNull() {
            addCriterion("cfbh is not null");
            return (Criteria) this;
        }

        public Criteria andCfbhEqualTo(String value) {
            addCriterion("cfbh =", value, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhNotEqualTo(String value) {
            addCriterion("cfbh <>", value, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhGreaterThan(String value) {
            addCriterion("cfbh >", value, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhGreaterThanOrEqualTo(String value) {
            addCriterion("cfbh >=", value, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhLessThan(String value) {
            addCriterion("cfbh <", value, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhLessThanOrEqualTo(String value) {
            addCriterion("cfbh <=", value, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhLike(String value) {
            addCriterion("cfbh like", value, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhNotLike(String value) {
            addCriterion("cfbh not like", value, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhIn(List<String> values) {
            addCriterion("cfbh in", values, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhNotIn(List<String> values) {
            addCriterion("cfbh not in", values, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhBetween(String value1, String value2) {
            addCriterion("cfbh between", value1, value2, "cfbh");
            return (Criteria) this;
        }

        public Criteria andCfbhNotBetween(String value1, String value2) {
            addCriterion("cfbh not between", value1, value2, "cfbh");
            return (Criteria) this;
        }

        public Criteria andDtkmIsNull() {
            addCriterion("dtkm is null");
            return (Criteria) this;
        }

        public Criteria andDtkmIsNotNull() {
            addCriterion("dtkm is not null");
            return (Criteria) this;
        }

        public Criteria andDtkmEqualTo(String value) {
            addCriterion("dtkm =", value, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmNotEqualTo(String value) {
            addCriterion("dtkm <>", value, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmGreaterThan(String value) {
            addCriterion("dtkm >", value, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmGreaterThanOrEqualTo(String value) {
            addCriterion("dtkm >=", value, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmLessThan(String value) {
            addCriterion("dtkm <", value, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmLessThanOrEqualTo(String value) {
            addCriterion("dtkm <=", value, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmLike(String value) {
            addCriterion("dtkm like", value, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmNotLike(String value) {
            addCriterion("dtkm not like", value, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmIn(List<String> values) {
            addCriterion("dtkm in", values, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmNotIn(List<String> values) {
            addCriterion("dtkm not in", values, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmBetween(String value1, String value2) {
            addCriterion("dtkm between", value1, value2, "dtkm");
            return (Criteria) this;
        }

        public Criteria andDtkmNotBetween(String value1, String value2) {
            addCriterion("dtkm not between", value1, value2, "dtkm");
            return (Criteria) this;
        }

        public Criteria andKssjIsNull() {
            addCriterion("kssj is null");
            return (Criteria) this;
        }

        public Criteria andKssjIsNotNull() {
            addCriterion("kssj is not null");
            return (Criteria) this;
        }

        public Criteria andKssjEqualTo(Date value) {
            addCriterion("kssj =", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotEqualTo(Date value) {
            addCriterion("kssj <>", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjGreaterThan(Date value) {
            addCriterion("kssj >", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjGreaterThanOrEqualTo(Date value) {
            addCriterion("kssj >=", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjLessThan(Date value) {
            addCriterion("kssj <", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjLessThanOrEqualTo(Date value) {
            addCriterion("kssj <=", value, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjIn(List<Date> values) {
            addCriterion("kssj in", values, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotIn(List<Date> values) {
            addCriterion("kssj not in", values, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjBetween(Date value1, Date value2) {
            addCriterion("kssj between", value1, value2, "kssj");
            return (Criteria) this;
        }

        public Criteria andKssjNotBetween(Date value1, Date value2) {
            addCriterion("kssj not between", value1, value2, "kssj");
            return (Criteria) this;
        }

        public Criteria andJssjIsNull() {
            addCriterion("jssj is null");
            return (Criteria) this;
        }

        public Criteria andJssjIsNotNull() {
            addCriterion("jssj is not null");
            return (Criteria) this;
        }

        public Criteria andJssjEqualTo(Date value) {
            addCriterion("jssj =", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotEqualTo(Date value) {
            addCriterion("jssj <>", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjGreaterThan(Date value) {
            addCriterion("jssj >", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjGreaterThanOrEqualTo(Date value) {
            addCriterion("jssj >=", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjLessThan(Date value) {
            addCriterion("jssj <", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjLessThanOrEqualTo(Date value) {
            addCriterion("jssj <=", value, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjIn(List<Date> values) {
            addCriterion("jssj in", values, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotIn(List<Date> values) {
            addCriterion("jssj not in", values, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjBetween(Date value1, Date value2) {
            addCriterion("jssj between", value1, value2, "jssj");
            return (Criteria) this;
        }

        public Criteria andJssjNotBetween(Date value1, Date value2) {
            addCriterion("jssj not between", value1, value2, "jssj");
            return (Criteria) this;
        }

        public Criteria andSdfsIsNull() {
            addCriterion("sdfs is null");
            return (Criteria) this;
        }

        public Criteria andSdfsIsNotNull() {
            addCriterion("sdfs is not null");
            return (Criteria) this;
        }

        public Criteria andSdfsEqualTo(Integer value) {
            addCriterion("sdfs =", value, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSdfsNotEqualTo(Integer value) {
            addCriterion("sdfs <>", value, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSdfsGreaterThan(Integer value) {
            addCriterion("sdfs >", value, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSdfsGreaterThanOrEqualTo(Integer value) {
            addCriterion("sdfs >=", value, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSdfsLessThan(Integer value) {
            addCriterion("sdfs <", value, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSdfsLessThanOrEqualTo(Integer value) {
            addCriterion("sdfs <=", value, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSdfsIn(List<Integer> values) {
            addCriterion("sdfs in", values, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSdfsNotIn(List<Integer> values) {
            addCriterion("sdfs not in", values, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSdfsBetween(Integer value1, Integer value2) {
            addCriterion("sdfs between", value1, value2, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSdfsNotBetween(Integer value1, Integer value2) {
            addCriterion("sdfs not between", value1, value2, "sdfs");
            return (Criteria) this;
        }

        public Criteria andSfjgIsNull() {
            addCriterion("sfjg is null");
            return (Criteria) this;
        }

        public Criteria andSfjgIsNotNull() {
            addCriterion("sfjg is not null");
            return (Criteria) this;
        }

        public Criteria andSfjgEqualTo(String value) {
            addCriterion("sfjg =", value, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgNotEqualTo(String value) {
            addCriterion("sfjg <>", value, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgGreaterThan(String value) {
            addCriterion("sfjg >", value, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgGreaterThanOrEqualTo(String value) {
            addCriterion("sfjg >=", value, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgLessThan(String value) {
            addCriterion("sfjg <", value, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgLessThanOrEqualTo(String value) {
            addCriterion("sfjg <=", value, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgLike(String value) {
            addCriterion("sfjg like", value, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgNotLike(String value) {
            addCriterion("sfjg not like", value, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgIn(List<String> values) {
            addCriterion("sfjg in", values, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgNotIn(List<String> values) {
            addCriterion("sfjg not in", values, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgBetween(String value1, String value2) {
            addCriterion("sfjg between", value1, value2, "sfjg");
            return (Criteria) this;
        }

        public Criteria andSfjgNotBetween(String value1, String value2) {
            addCriterion("sfjg not between", value1, value2, "sfjg");
            return (Criteria) this;
        }

        public Criteria andWxidIsNull() {
            addCriterion("wxid is null");
            return (Criteria) this;
        }

        public Criteria andWxidIsNotNull() {
            addCriterion("wxid is not null");
            return (Criteria) this;
        }

        public Criteria andWxidEqualTo(String value) {
            addCriterion("wxid =", value, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidNotEqualTo(String value) {
            addCriterion("wxid <>", value, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidGreaterThan(String value) {
            addCriterion("wxid >", value, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidGreaterThanOrEqualTo(String value) {
            addCriterion("wxid >=", value, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidLessThan(String value) {
            addCriterion("wxid <", value, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidLessThanOrEqualTo(String value) {
            addCriterion("wxid <=", value, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidLike(String value) {
            addCriterion("wxid like", value, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidNotLike(String value) {
            addCriterion("wxid not like", value, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidIn(List<String> values) {
            addCriterion("wxid in", values, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidNotIn(List<String> values) {
            addCriterion("wxid not in", values, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidBetween(String value1, String value2) {
            addCriterion("wxid between", value1, value2, "wxid");
            return (Criteria) this;
        }

        public Criteria andWxidNotBetween(String value1, String value2) {
            addCriterion("wxid not between", value1, value2, "wxid");
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