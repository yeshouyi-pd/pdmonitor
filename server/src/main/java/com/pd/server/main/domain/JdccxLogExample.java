package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdccxLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JdccxLogExample() {
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

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andQqcsIsNull() {
            addCriterion("qqcs is null");
            return (Criteria) this;
        }

        public Criteria andQqcsIsNotNull() {
            addCriterion("qqcs is not null");
            return (Criteria) this;
        }

        public Criteria andQqcsEqualTo(String value) {
            addCriterion("qqcs =", value, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsNotEqualTo(String value) {
            addCriterion("qqcs <>", value, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsGreaterThan(String value) {
            addCriterion("qqcs >", value, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsGreaterThanOrEqualTo(String value) {
            addCriterion("qqcs >=", value, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsLessThan(String value) {
            addCriterion("qqcs <", value, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsLessThanOrEqualTo(String value) {
            addCriterion("qqcs <=", value, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsLike(String value) {
            addCriterion("qqcs like", value, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsNotLike(String value) {
            addCriterion("qqcs not like", value, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsIn(List<String> values) {
            addCriterion("qqcs in", values, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsNotIn(List<String> values) {
            addCriterion("qqcs not in", values, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsBetween(String value1, String value2) {
            addCriterion("qqcs between", value1, value2, "qqcs");
            return (Criteria) this;
        }

        public Criteria andQqcsNotBetween(String value1, String value2) {
            addCriterion("qqcs not between", value1, value2, "qqcs");
            return (Criteria) this;
        }

        public Criteria andFhztmIsNull() {
            addCriterion("fhztm is null");
            return (Criteria) this;
        }

        public Criteria andFhztmIsNotNull() {
            addCriterion("fhztm is not null");
            return (Criteria) this;
        }

        public Criteria andFhztmEqualTo(String value) {
            addCriterion("fhztm =", value, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmNotEqualTo(String value) {
            addCriterion("fhztm <>", value, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmGreaterThan(String value) {
            addCriterion("fhztm >", value, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmGreaterThanOrEqualTo(String value) {
            addCriterion("fhztm >=", value, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmLessThan(String value) {
            addCriterion("fhztm <", value, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmLessThanOrEqualTo(String value) {
            addCriterion("fhztm <=", value, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmLike(String value) {
            addCriterion("fhztm like", value, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmNotLike(String value) {
            addCriterion("fhztm not like", value, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmIn(List<String> values) {
            addCriterion("fhztm in", values, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmNotIn(List<String> values) {
            addCriterion("fhztm not in", values, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmBetween(String value1, String value2) {
            addCriterion("fhztm between", value1, value2, "fhztm");
            return (Criteria) this;
        }

        public Criteria andFhztmNotBetween(String value1, String value2) {
            addCriterion("fhztm not between", value1, value2, "fhztm");
            return (Criteria) this;
        }

        public Criteria andCjhIsNull() {
            addCriterion("cjh is null");
            return (Criteria) this;
        }

        public Criteria andCjhIsNotNull() {
            addCriterion("cjh is not null");
            return (Criteria) this;
        }

        public Criteria andCjhEqualTo(String value) {
            addCriterion("cjh =", value, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhNotEqualTo(String value) {
            addCriterion("cjh <>", value, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhGreaterThan(String value) {
            addCriterion("cjh >", value, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhGreaterThanOrEqualTo(String value) {
            addCriterion("cjh >=", value, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhLessThan(String value) {
            addCriterion("cjh <", value, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhLessThanOrEqualTo(String value) {
            addCriterion("cjh <=", value, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhLike(String value) {
            addCriterion("cjh like", value, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhNotLike(String value) {
            addCriterion("cjh not like", value, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhIn(List<String> values) {
            addCriterion("cjh in", values, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhNotIn(List<String> values) {
            addCriterion("cjh not in", values, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhBetween(String value1, String value2) {
            addCriterion("cjh between", value1, value2, "cjh");
            return (Criteria) this;
        }

        public Criteria andCjhNotBetween(String value1, String value2) {
            addCriterion("cjh not between", value1, value2, "cjh");
            return (Criteria) this;
        }

        public Criteria andZcrqIsNull() {
            addCriterion("zcrq is null");
            return (Criteria) this;
        }

        public Criteria andZcrqIsNotNull() {
            addCriterion("zcrq is not null");
            return (Criteria) this;
        }

        public Criteria andZcrqEqualTo(String value) {
            addCriterion("zcrq =", value, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqNotEqualTo(String value) {
            addCriterion("zcrq <>", value, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqGreaterThan(String value) {
            addCriterion("zcrq >", value, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqGreaterThanOrEqualTo(String value) {
            addCriterion("zcrq >=", value, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqLessThan(String value) {
            addCriterion("zcrq <", value, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqLessThanOrEqualTo(String value) {
            addCriterion("zcrq <=", value, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqLike(String value) {
            addCriterion("zcrq like", value, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqNotLike(String value) {
            addCriterion("zcrq not like", value, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqIn(List<String> values) {
            addCriterion("zcrq in", values, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqNotIn(List<String> values) {
            addCriterion("zcrq not in", values, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqBetween(String value1, String value2) {
            addCriterion("zcrq between", value1, value2, "zcrq");
            return (Criteria) this;
        }

        public Criteria andZcrqNotBetween(String value1, String value2) {
            addCriterion("zcrq not between", value1, value2, "zcrq");
            return (Criteria) this;
        }

        public Criteria andSccjIsNull() {
            addCriterion("sccj is null");
            return (Criteria) this;
        }

        public Criteria andSccjIsNotNull() {
            addCriterion("sccj is not null");
            return (Criteria) this;
        }

        public Criteria andSccjEqualTo(String value) {
            addCriterion("sccj =", value, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjNotEqualTo(String value) {
            addCriterion("sccj <>", value, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjGreaterThan(String value) {
            addCriterion("sccj >", value, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjGreaterThanOrEqualTo(String value) {
            addCriterion("sccj >=", value, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjLessThan(String value) {
            addCriterion("sccj <", value, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjLessThanOrEqualTo(String value) {
            addCriterion("sccj <=", value, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjLike(String value) {
            addCriterion("sccj like", value, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjNotLike(String value) {
            addCriterion("sccj not like", value, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjIn(List<String> values) {
            addCriterion("sccj in", values, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjNotIn(List<String> values) {
            addCriterion("sccj not in", values, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjBetween(String value1, String value2) {
            addCriterion("sccj between", value1, value2, "sccj");
            return (Criteria) this;
        }

        public Criteria andSccjNotBetween(String value1, String value2) {
            addCriterion("sccj not between", value1, value2, "sccj");
            return (Criteria) this;
        }

        public Criteria andDwIsNull() {
            addCriterion("dw is null");
            return (Criteria) this;
        }

        public Criteria andDwIsNotNull() {
            addCriterion("dw is not null");
            return (Criteria) this;
        }

        public Criteria andDwEqualTo(String value) {
            addCriterion("dw =", value, "dw");
            return (Criteria) this;
        }

        public Criteria andDwNotEqualTo(String value) {
            addCriterion("dw <>", value, "dw");
            return (Criteria) this;
        }

        public Criteria andDwGreaterThan(String value) {
            addCriterion("dw >", value, "dw");
            return (Criteria) this;
        }

        public Criteria andDwGreaterThanOrEqualTo(String value) {
            addCriterion("dw >=", value, "dw");
            return (Criteria) this;
        }

        public Criteria andDwLessThan(String value) {
            addCriterion("dw <", value, "dw");
            return (Criteria) this;
        }

        public Criteria andDwLessThanOrEqualTo(String value) {
            addCriterion("dw <=", value, "dw");
            return (Criteria) this;
        }

        public Criteria andDwLike(String value) {
            addCriterion("dw like", value, "dw");
            return (Criteria) this;
        }

        public Criteria andDwNotLike(String value) {
            addCriterion("dw not like", value, "dw");
            return (Criteria) this;
        }

        public Criteria andDwIn(List<String> values) {
            addCriterion("dw in", values, "dw");
            return (Criteria) this;
        }

        public Criteria andDwNotIn(List<String> values) {
            addCriterion("dw not in", values, "dw");
            return (Criteria) this;
        }

        public Criteria andDwBetween(String value1, String value2) {
            addCriterion("dw between", value1, value2, "dw");
            return (Criteria) this;
        }

        public Criteria andDwNotBetween(String value1, String value2) {
            addCriterion("dw not between", value1, value2, "dw");
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

        public Criteria andHpzlIsNull() {
            addCriterion("hpzl is null");
            return (Criteria) this;
        }

        public Criteria andHpzlIsNotNull() {
            addCriterion("hpzl is not null");
            return (Criteria) this;
        }

        public Criteria andHpzlEqualTo(String value) {
            addCriterion("hpzl =", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotEqualTo(String value) {
            addCriterion("hpzl <>", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlGreaterThan(String value) {
            addCriterion("hpzl >", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlGreaterThanOrEqualTo(String value) {
            addCriterion("hpzl >=", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlLessThan(String value) {
            addCriterion("hpzl <", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlLessThanOrEqualTo(String value) {
            addCriterion("hpzl <=", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlLike(String value) {
            addCriterion("hpzl like", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotLike(String value) {
            addCriterion("hpzl not like", value, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlIn(List<String> values) {
            addCriterion("hpzl in", values, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotIn(List<String> values) {
            addCriterion("hpzl not in", values, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlBetween(String value1, String value2) {
            addCriterion("hpzl between", value1, value2, "hpzl");
            return (Criteria) this;
        }

        public Criteria andHpzlNotBetween(String value1, String value2) {
            addCriterion("hpzl not between", value1, value2, "hpzl");
            return (Criteria) this;
        }

        public Criteria andZhhmIsNull() {
            addCriterion("zhhm is null");
            return (Criteria) this;
        }

        public Criteria andZhhmIsNotNull() {
            addCriterion("zhhm is not null");
            return (Criteria) this;
        }

        public Criteria andZhhmEqualTo(String value) {
            addCriterion("zhhm =", value, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmNotEqualTo(String value) {
            addCriterion("zhhm <>", value, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmGreaterThan(String value) {
            addCriterion("zhhm >", value, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmGreaterThanOrEqualTo(String value) {
            addCriterion("zhhm >=", value, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmLessThan(String value) {
            addCriterion("zhhm <", value, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmLessThanOrEqualTo(String value) {
            addCriterion("zhhm <=", value, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmLike(String value) {
            addCriterion("zhhm like", value, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmNotLike(String value) {
            addCriterion("zhhm not like", value, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmIn(List<String> values) {
            addCriterion("zhhm in", values, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmNotIn(List<String> values) {
            addCriterion("zhhm not in", values, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmBetween(String value1, String value2) {
            addCriterion("zhhm between", value1, value2, "zhhm");
            return (Criteria) this;
        }

        public Criteria andZhhmNotBetween(String value1, String value2) {
            addCriterion("zhhm not between", value1, value2, "zhhm");
            return (Criteria) this;
        }

        public Criteria andHphmIsNull() {
            addCriterion("hphm is null");
            return (Criteria) this;
        }

        public Criteria andHphmIsNotNull() {
            addCriterion("hphm is not null");
            return (Criteria) this;
        }

        public Criteria andHphmEqualTo(String value) {
            addCriterion("hphm =", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotEqualTo(String value) {
            addCriterion("hphm <>", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmGreaterThan(String value) {
            addCriterion("hphm >", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmGreaterThanOrEqualTo(String value) {
            addCriterion("hphm >=", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmLessThan(String value) {
            addCriterion("hphm <", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmLessThanOrEqualTo(String value) {
            addCriterion("hphm <=", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmLike(String value) {
            addCriterion("hphm like", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotLike(String value) {
            addCriterion("hphm not like", value, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmIn(List<String> values) {
            addCriterion("hphm in", values, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotIn(List<String> values) {
            addCriterion("hphm not in", values, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmBetween(String value1, String value2) {
            addCriterion("hphm between", value1, value2, "hphm");
            return (Criteria) this;
        }

        public Criteria andHphmNotBetween(String value1, String value2) {
            addCriterion("hphm not between", value1, value2, "hphm");
            return (Criteria) this;
        }

        public Criteria andSyrIsNull() {
            addCriterion("syr is null");
            return (Criteria) this;
        }

        public Criteria andSyrIsNotNull() {
            addCriterion("syr is not null");
            return (Criteria) this;
        }

        public Criteria andSyrEqualTo(String value) {
            addCriterion("syr =", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotEqualTo(String value) {
            addCriterion("syr <>", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrGreaterThan(String value) {
            addCriterion("syr >", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrGreaterThanOrEqualTo(String value) {
            addCriterion("syr >=", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrLessThan(String value) {
            addCriterion("syr <", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrLessThanOrEqualTo(String value) {
            addCriterion("syr <=", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrLike(String value) {
            addCriterion("syr like", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotLike(String value) {
            addCriterion("syr not like", value, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrIn(List<String> values) {
            addCriterion("syr in", values, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotIn(List<String> values) {
            addCriterion("syr not in", values, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrBetween(String value1, String value2) {
            addCriterion("syr between", value1, value2, "syr");
            return (Criteria) this;
        }

        public Criteria andSyrNotBetween(String value1, String value2) {
            addCriterion("syr not between", value1, value2, "syr");
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