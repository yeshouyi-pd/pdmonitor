package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LdDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LdDataExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
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

        public Criteria andLxIsNull() {
            addCriterion("lx is null");
            return (Criteria) this;
        }

        public Criteria andLxIsNotNull() {
            addCriterion("lx is not null");
            return (Criteria) this;
        }

        public Criteria andLxEqualTo(String value) {
            addCriterion("lx =", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotEqualTo(String value) {
            addCriterion("lx <>", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxGreaterThan(String value) {
            addCriterion("lx >", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxGreaterThanOrEqualTo(String value) {
            addCriterion("lx >=", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxLessThan(String value) {
            addCriterion("lx <", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxLessThanOrEqualTo(String value) {
            addCriterion("lx <=", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxLike(String value) {
            addCriterion("lx like", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotLike(String value) {
            addCriterion("lx not like", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxIn(List<String> values) {
            addCriterion("lx in", values, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotIn(List<String> values) {
            addCriterion("lx not in", values, "lx");
            return (Criteria) this;
        }

        public Criteria andLxBetween(String value1, String value2) {
            addCriterion("lx between", value1, value2, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotBetween(String value1, String value2) {
            addCriterion("lx not between", value1, value2, "lx");
            return (Criteria) this;
        }

        public Criteria andLxsdIsNull() {
            addCriterion("lxsd is null");
            return (Criteria) this;
        }

        public Criteria andLxsdIsNotNull() {
            addCriterion("lxsd is not null");
            return (Criteria) this;
        }

        public Criteria andLxsdEqualTo(String value) {
            addCriterion("lxsd =", value, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdNotEqualTo(String value) {
            addCriterion("lxsd <>", value, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdGreaterThan(String value) {
            addCriterion("lxsd >", value, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdGreaterThanOrEqualTo(String value) {
            addCriterion("lxsd >=", value, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdLessThan(String value) {
            addCriterion("lxsd <", value, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdLessThanOrEqualTo(String value) {
            addCriterion("lxsd <=", value, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdLike(String value) {
            addCriterion("lxsd like", value, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdNotLike(String value) {
            addCriterion("lxsd not like", value, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdIn(List<String> values) {
            addCriterion("lxsd in", values, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdNotIn(List<String> values) {
            addCriterion("lxsd not in", values, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdBetween(String value1, String value2) {
            addCriterion("lxsd between", value1, value2, "lxsd");
            return (Criteria) this;
        }

        public Criteria andLxsdNotBetween(String value1, String value2) {
            addCriterion("lxsd not between", value1, value2, "lxsd");
            return (Criteria) this;
        }

        public Criteria andQxIsNull() {
            addCriterion("qx is null");
            return (Criteria) this;
        }

        public Criteria andQxIsNotNull() {
            addCriterion("qx is not null");
            return (Criteria) this;
        }

        public Criteria andQxEqualTo(String value) {
            addCriterion("qx =", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxNotEqualTo(String value) {
            addCriterion("qx <>", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxGreaterThan(String value) {
            addCriterion("qx >", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxGreaterThanOrEqualTo(String value) {
            addCriterion("qx >=", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxLessThan(String value) {
            addCriterion("qx <", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxLessThanOrEqualTo(String value) {
            addCriterion("qx <=", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxLike(String value) {
            addCriterion("qx like", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxNotLike(String value) {
            addCriterion("qx not like", value, "qx");
            return (Criteria) this;
        }

        public Criteria andQxIn(List<String> values) {
            addCriterion("qx in", values, "qx");
            return (Criteria) this;
        }

        public Criteria andQxNotIn(List<String> values) {
            addCriterion("qx not in", values, "qx");
            return (Criteria) this;
        }

        public Criteria andQxBetween(String value1, String value2) {
            addCriterion("qx between", value1, value2, "qx");
            return (Criteria) this;
        }

        public Criteria andQxNotBetween(String value1, String value2) {
            addCriterion("qx not between", value1, value2, "qx");
            return (Criteria) this;
        }

        public Criteria andQxsdIsNull() {
            addCriterion("qxsd is null");
            return (Criteria) this;
        }

        public Criteria andQxsdIsNotNull() {
            addCriterion("qxsd is not null");
            return (Criteria) this;
        }

        public Criteria andQxsdEqualTo(String value) {
            addCriterion("qxsd =", value, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdNotEqualTo(String value) {
            addCriterion("qxsd <>", value, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdGreaterThan(String value) {
            addCriterion("qxsd >", value, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdGreaterThanOrEqualTo(String value) {
            addCriterion("qxsd >=", value, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdLessThan(String value) {
            addCriterion("qxsd <", value, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdLessThanOrEqualTo(String value) {
            addCriterion("qxsd <=", value, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdLike(String value) {
            addCriterion("qxsd like", value, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdNotLike(String value) {
            addCriterion("qxsd not like", value, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdIn(List<String> values) {
            addCriterion("qxsd in", values, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdNotIn(List<String> values) {
            addCriterion("qxsd not in", values, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdBetween(String value1, String value2) {
            addCriterion("qxsd between", value1, value2, "qxsd");
            return (Criteria) this;
        }

        public Criteria andQxsdNotBetween(String value1, String value2) {
            addCriterion("qxsd not between", value1, value2, "qxsd");
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

        public Criteria andSjdateIsNull() {
            addCriterion("sjdate is null");
            return (Criteria) this;
        }

        public Criteria andSjdateIsNotNull() {
            addCriterion("sjdate is not null");
            return (Criteria) this;
        }

        public Criteria andSjdateEqualTo(Date value) {
            addCriterion("sjdate =", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateNotEqualTo(Date value) {
            addCriterion("sjdate <>", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateGreaterThan(Date value) {
            addCriterion("sjdate >", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateGreaterThanOrEqualTo(Date value) {
            addCriterion("sjdate >=", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateLessThan(Date value) {
            addCriterion("sjdate <", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateLessThanOrEqualTo(Date value) {
            addCriterion("sjdate <=", value, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateIn(List<Date> values) {
            addCriterion("sjdate in", values, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateNotIn(List<Date> values) {
            addCriterion("sjdate not in", values, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateBetween(Date value1, Date value2) {
            addCriterion("sjdate between", value1, value2, "sjdate");
            return (Criteria) this;
        }

        public Criteria andSjdateNotBetween(Date value1, Date value2) {
            addCriterion("sjdate not between", value1, value2, "sjdate");
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