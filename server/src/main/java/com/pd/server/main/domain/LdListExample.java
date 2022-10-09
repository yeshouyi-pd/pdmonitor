package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LdListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LdListExample() {
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

        public Criteria andIccidIsNull() {
            addCriterion("ICCID is null");
            return (Criteria) this;
        }

        public Criteria andIccidIsNotNull() {
            addCriterion("ICCID is not null");
            return (Criteria) this;
        }

        public Criteria andIccidEqualTo(String value) {
            addCriterion("ICCID =", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidNotEqualTo(String value) {
            addCriterion("ICCID <>", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidGreaterThan(String value) {
            addCriterion("ICCID >", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidGreaterThanOrEqualTo(String value) {
            addCriterion("ICCID >=", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidLessThan(String value) {
            addCriterion("ICCID <", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidLessThanOrEqualTo(String value) {
            addCriterion("ICCID <=", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidLike(String value) {
            addCriterion("ICCID like", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidNotLike(String value) {
            addCriterion("ICCID not like", value, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidIn(List<String> values) {
            addCriterion("ICCID in", values, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidNotIn(List<String> values) {
            addCriterion("ICCID not in", values, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidBetween(String value1, String value2) {
            addCriterion("ICCID between", value1, value2, "iccid");
            return (Criteria) this;
        }

        public Criteria andIccidNotBetween(String value1, String value2) {
            addCriterion("ICCID not between", value1, value2, "iccid");
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

        public Criteria andSbmsIsNull() {
            addCriterion("sbms is null");
            return (Criteria) this;
        }

        public Criteria andSbmsIsNotNull() {
            addCriterion("sbms is not null");
            return (Criteria) this;
        }

        public Criteria andSbmsEqualTo(String value) {
            addCriterion("sbms =", value, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsNotEqualTo(String value) {
            addCriterion("sbms <>", value, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsGreaterThan(String value) {
            addCriterion("sbms >", value, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsGreaterThanOrEqualTo(String value) {
            addCriterion("sbms >=", value, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsLessThan(String value) {
            addCriterion("sbms <", value, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsLessThanOrEqualTo(String value) {
            addCriterion("sbms <=", value, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsLike(String value) {
            addCriterion("sbms like", value, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsNotLike(String value) {
            addCriterion("sbms not like", value, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsIn(List<String> values) {
            addCriterion("sbms in", values, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsNotIn(List<String> values) {
            addCriterion("sbms not in", values, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsBetween(String value1, String value2) {
            addCriterion("sbms between", value1, value2, "sbms");
            return (Criteria) this;
        }

        public Criteria andSbmsNotBetween(String value1, String value2) {
            addCriterion("sbms not between", value1, value2, "sbms");
            return (Criteria) this;
        }

        public Criteria andIpportIsNull() {
            addCriterion("IPPort is null");
            return (Criteria) this;
        }

        public Criteria andIpportIsNotNull() {
            addCriterion("IPPort is not null");
            return (Criteria) this;
        }

        public Criteria andIpportEqualTo(String value) {
            addCriterion("IPPort =", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportNotEqualTo(String value) {
            addCriterion("IPPort <>", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportGreaterThan(String value) {
            addCriterion("IPPort >", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportGreaterThanOrEqualTo(String value) {
            addCriterion("IPPort >=", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportLessThan(String value) {
            addCriterion("IPPort <", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportLessThanOrEqualTo(String value) {
            addCriterion("IPPort <=", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportLike(String value) {
            addCriterion("IPPort like", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportNotLike(String value) {
            addCriterion("IPPort not like", value, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportIn(List<String> values) {
            addCriterion("IPPort in", values, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportNotIn(List<String> values) {
            addCriterion("IPPort not in", values, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportBetween(String value1, String value2) {
            addCriterion("IPPort between", value1, value2, "ipport");
            return (Criteria) this;
        }

        public Criteria andIpportNotBetween(String value1, String value2) {
            addCriterion("IPPort not between", value1, value2, "ipport");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("`state` like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("`state` not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andUpsjIsNull() {
            addCriterion("upsj is null");
            return (Criteria) this;
        }

        public Criteria andUpsjIsNotNull() {
            addCriterion("upsj is not null");
            return (Criteria) this;
        }

        public Criteria andUpsjEqualTo(Date value) {
            addCriterion("upsj =", value, "upsj");
            return (Criteria) this;
        }

        public Criteria andUpsjNotEqualTo(Date value) {
            addCriterion("upsj <>", value, "upsj");
            return (Criteria) this;
        }

        public Criteria andUpsjGreaterThan(Date value) {
            addCriterion("upsj >", value, "upsj");
            return (Criteria) this;
        }

        public Criteria andUpsjGreaterThanOrEqualTo(Date value) {
            addCriterion("upsj >=", value, "upsj");
            return (Criteria) this;
        }

        public Criteria andUpsjLessThan(Date value) {
            addCriterion("upsj <", value, "upsj");
            return (Criteria) this;
        }

        public Criteria andUpsjLessThanOrEqualTo(Date value) {
            addCriterion("upsj <=", value, "upsj");
            return (Criteria) this;
        }

        public Criteria andUpsjIn(List<Date> values) {
            addCriterion("upsj in", values, "upsj");
            return (Criteria) this;
        }

        public Criteria andUpsjNotIn(List<Date> values) {
            addCriterion("upsj not in", values, "upsj");
            return (Criteria) this;
        }

        public Criteria andUpsjBetween(Date value1, Date value2) {
            addCriterion("upsj between", value1, value2, "upsj");
            return (Criteria) this;
        }

        public Criteria andUpsjNotBetween(Date value1, Date value2) {
            addCriterion("upsj not between", value1, value2, "upsj");
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