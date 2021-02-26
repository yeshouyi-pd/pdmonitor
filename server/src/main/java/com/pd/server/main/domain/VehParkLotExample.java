package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehParkLotExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VehParkLotExample() {
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

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeIsNull() {
            addCriterion("sys_org_code is null");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeIsNotNull() {
            addCriterion("sys_org_code is not null");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeEqualTo(String value) {
            addCriterion("sys_org_code =", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotEqualTo(String value) {
            addCriterion("sys_org_code <>", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeGreaterThan(String value) {
            addCriterion("sys_org_code >", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_org_code >=", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeLessThan(String value) {
            addCriterion("sys_org_code <", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("sys_org_code <=", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeLike(String value) {
            addCriterion("sys_org_code like", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotLike(String value) {
            addCriterion("sys_org_code not like", value, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeIn(List<String> values) {
            addCriterion("sys_org_code in", values, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotIn(List<String> values) {
            addCriterion("sys_org_code not in", values, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeBetween(String value1, String value2) {
            addCriterion("sys_org_code between", value1, value2, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andSysOrgCodeNotBetween(String value1, String value2) {
            addCriterion("sys_org_code not between", value1, value2, "sysOrgCode");
            return (Criteria) this;
        }

        public Criteria andGlIsNull() {
            addCriterion("gl is null");
            return (Criteria) this;
        }

        public Criteria andGlIsNotNull() {
            addCriterion("gl is not null");
            return (Criteria) this;
        }

        public Criteria andGlEqualTo(String value) {
            addCriterion("gl =", value, "gl");
            return (Criteria) this;
        }

        public Criteria andGlNotEqualTo(String value) {
            addCriterion("gl <>", value, "gl");
            return (Criteria) this;
        }

        public Criteria andGlGreaterThan(String value) {
            addCriterion("gl >", value, "gl");
            return (Criteria) this;
        }

        public Criteria andGlGreaterThanOrEqualTo(String value) {
            addCriterion("gl >=", value, "gl");
            return (Criteria) this;
        }

        public Criteria andGlLessThan(String value) {
            addCriterion("gl <", value, "gl");
            return (Criteria) this;
        }

        public Criteria andGlLessThanOrEqualTo(String value) {
            addCriterion("gl <=", value, "gl");
            return (Criteria) this;
        }

        public Criteria andGlLike(String value) {
            addCriterion("gl like", value, "gl");
            return (Criteria) this;
        }

        public Criteria andGlNotLike(String value) {
            addCriterion("gl not like", value, "gl");
            return (Criteria) this;
        }

        public Criteria andGlIn(List<String> values) {
            addCriterion("gl in", values, "gl");
            return (Criteria) this;
        }

        public Criteria andGlNotIn(List<String> values) {
            addCriterion("gl not in", values, "gl");
            return (Criteria) this;
        }

        public Criteria andGlBetween(String value1, String value2) {
            addCriterion("gl between", value1, value2, "gl");
            return (Criteria) this;
        }

        public Criteria andGlNotBetween(String value1, String value2) {
            addCriterion("gl not between", value1, value2, "gl");
            return (Criteria) this;
        }

        public Criteria andLshIsNull() {
            addCriterion("lsh is null");
            return (Criteria) this;
        }

        public Criteria andLshIsNotNull() {
            addCriterion("lsh is not null");
            return (Criteria) this;
        }

        public Criteria andLshEqualTo(String value) {
            addCriterion("lsh =", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshNotEqualTo(String value) {
            addCriterion("lsh <>", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshGreaterThan(String value) {
            addCriterion("lsh >", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshGreaterThanOrEqualTo(String value) {
            addCriterion("lsh >=", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshLessThan(String value) {
            addCriterion("lsh <", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshLessThanOrEqualTo(String value) {
            addCriterion("lsh <=", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshLike(String value) {
            addCriterion("lsh like", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshNotLike(String value) {
            addCriterion("lsh not like", value, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshIn(List<String> values) {
            addCriterion("lsh in", values, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshNotIn(List<String> values) {
            addCriterion("lsh not in", values, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshBetween(String value1, String value2) {
            addCriterion("lsh between", value1, value2, "lsh");
            return (Criteria) this;
        }

        public Criteria andLshNotBetween(String value1, String value2) {
            addCriterion("lsh not between", value1, value2, "lsh");
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

        public Criteria andClsbdhIsNull() {
            addCriterion("clsbdh is null");
            return (Criteria) this;
        }

        public Criteria andClsbdhIsNotNull() {
            addCriterion("clsbdh is not null");
            return (Criteria) this;
        }

        public Criteria andClsbdhEqualTo(String value) {
            addCriterion("clsbdh =", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotEqualTo(String value) {
            addCriterion("clsbdh <>", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhGreaterThan(String value) {
            addCriterion("clsbdh >", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhGreaterThanOrEqualTo(String value) {
            addCriterion("clsbdh >=", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhLessThan(String value) {
            addCriterion("clsbdh <", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhLessThanOrEqualTo(String value) {
            addCriterion("clsbdh <=", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhLike(String value) {
            addCriterion("clsbdh like", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotLike(String value) {
            addCriterion("clsbdh not like", value, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhIn(List<String> values) {
            addCriterion("clsbdh in", values, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotIn(List<String> values) {
            addCriterion("clsbdh not in", values, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhBetween(String value1, String value2) {
            addCriterion("clsbdh between", value1, value2, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andClsbdhNotBetween(String value1, String value2) {
            addCriterion("clsbdh not between", value1, value2, "clsbdh");
            return (Criteria) this;
        }

        public Criteria andFdjhIsNull() {
            addCriterion("fdjh is null");
            return (Criteria) this;
        }

        public Criteria andFdjhIsNotNull() {
            addCriterion("fdjh is not null");
            return (Criteria) this;
        }

        public Criteria andFdjhEqualTo(String value) {
            addCriterion("fdjh =", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhNotEqualTo(String value) {
            addCriterion("fdjh <>", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhGreaterThan(String value) {
            addCriterion("fdjh >", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhGreaterThanOrEqualTo(String value) {
            addCriterion("fdjh >=", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhLessThan(String value) {
            addCriterion("fdjh <", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhLessThanOrEqualTo(String value) {
            addCriterion("fdjh <=", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhLike(String value) {
            addCriterion("fdjh like", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhNotLike(String value) {
            addCriterion("fdjh not like", value, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhIn(List<String> values) {
            addCriterion("fdjh in", values, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhNotIn(List<String> values) {
            addCriterion("fdjh not in", values, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhBetween(String value1, String value2) {
            addCriterion("fdjh between", value1, value2, "fdjh");
            return (Criteria) this;
        }

        public Criteria andFdjhNotBetween(String value1, String value2) {
            addCriterion("fdjh not between", value1, value2, "fdjh");
            return (Criteria) this;
        }

        public Criteria andCllbIsNull() {
            addCriterion("cllb is null");
            return (Criteria) this;
        }

        public Criteria andCllbIsNotNull() {
            addCriterion("cllb is not null");
            return (Criteria) this;
        }

        public Criteria andCllbEqualTo(String value) {
            addCriterion("cllb =", value, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbNotEqualTo(String value) {
            addCriterion("cllb <>", value, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbGreaterThan(String value) {
            addCriterion("cllb >", value, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbGreaterThanOrEqualTo(String value) {
            addCriterion("cllb >=", value, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbLessThan(String value) {
            addCriterion("cllb <", value, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbLessThanOrEqualTo(String value) {
            addCriterion("cllb <=", value, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbLike(String value) {
            addCriterion("cllb like", value, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbNotLike(String value) {
            addCriterion("cllb not like", value, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbIn(List<String> values) {
            addCriterion("cllb in", values, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbNotIn(List<String> values) {
            addCriterion("cllb not in", values, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbBetween(String value1, String value2) {
            addCriterion("cllb between", value1, value2, "cllb");
            return (Criteria) this;
        }

        public Criteria andCllbNotBetween(String value1, String value2) {
            addCriterion("cllb not between", value1, value2, "cllb");
            return (Criteria) this;
        }

        public Criteria andCpysIsNull() {
            addCriterion("cpys is null");
            return (Criteria) this;
        }

        public Criteria andCpysIsNotNull() {
            addCriterion("cpys is not null");
            return (Criteria) this;
        }

        public Criteria andCpysEqualTo(String value) {
            addCriterion("cpys =", value, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysNotEqualTo(String value) {
            addCriterion("cpys <>", value, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysGreaterThan(String value) {
            addCriterion("cpys >", value, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysGreaterThanOrEqualTo(String value) {
            addCriterion("cpys >=", value, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysLessThan(String value) {
            addCriterion("cpys <", value, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysLessThanOrEqualTo(String value) {
            addCriterion("cpys <=", value, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysLike(String value) {
            addCriterion("cpys like", value, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysNotLike(String value) {
            addCriterion("cpys not like", value, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysIn(List<String> values) {
            addCriterion("cpys in", values, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysNotIn(List<String> values) {
            addCriterion("cpys not in", values, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysBetween(String value1, String value2) {
            addCriterion("cpys between", value1, value2, "cpys");
            return (Criteria) this;
        }

        public Criteria andCpysNotBetween(String value1, String value2) {
            addCriterion("cpys not between", value1, value2, "cpys");
            return (Criteria) this;
        }

        public Criteria andCsysIsNull() {
            addCriterion("csys is null");
            return (Criteria) this;
        }

        public Criteria andCsysIsNotNull() {
            addCriterion("csys is not null");
            return (Criteria) this;
        }

        public Criteria andCsysEqualTo(String value) {
            addCriterion("csys =", value, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysNotEqualTo(String value) {
            addCriterion("csys <>", value, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysGreaterThan(String value) {
            addCriterion("csys >", value, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysGreaterThanOrEqualTo(String value) {
            addCriterion("csys >=", value, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysLessThan(String value) {
            addCriterion("csys <", value, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysLessThanOrEqualTo(String value) {
            addCriterion("csys <=", value, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysLike(String value) {
            addCriterion("csys like", value, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysNotLike(String value) {
            addCriterion("csys not like", value, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysIn(List<String> values) {
            addCriterion("csys in", values, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysNotIn(List<String> values) {
            addCriterion("csys not in", values, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysBetween(String value1, String value2) {
            addCriterion("csys between", value1, value2, "csys");
            return (Criteria) this;
        }

        public Criteria andCsysNotBetween(String value1, String value2) {
            addCriterion("csys not between", value1, value2, "csys");
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

        public Criteria andPzbhIsNull() {
            addCriterion("pzbh is null");
            return (Criteria) this;
        }

        public Criteria andPzbhIsNotNull() {
            addCriterion("pzbh is not null");
            return (Criteria) this;
        }

        public Criteria andPzbhEqualTo(String value) {
            addCriterion("pzbh =", value, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhNotEqualTo(String value) {
            addCriterion("pzbh <>", value, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhGreaterThan(String value) {
            addCriterion("pzbh >", value, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhGreaterThanOrEqualTo(String value) {
            addCriterion("pzbh >=", value, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhLessThan(String value) {
            addCriterion("pzbh <", value, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhLessThanOrEqualTo(String value) {
            addCriterion("pzbh <=", value, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhLike(String value) {
            addCriterion("pzbh like", value, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhNotLike(String value) {
            addCriterion("pzbh not like", value, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhIn(List<String> values) {
            addCriterion("pzbh in", values, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhNotIn(List<String> values) {
            addCriterion("pzbh not in", values, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhBetween(String value1, String value2) {
            addCriterion("pzbh between", value1, value2, "pzbh");
            return (Criteria) this;
        }

        public Criteria andPzbhNotBetween(String value1, String value2) {
            addCriterion("pzbh not between", value1, value2, "pzbh");
            return (Criteria) this;
        }

        public Criteria andXhIsNull() {
            addCriterion("xh is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("xh is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(String value) {
            addCriterion("xh =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(String value) {
            addCriterion("xh <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(String value) {
            addCriterion("xh >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(String value) {
            addCriterion("xh >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(String value) {
            addCriterion("xh <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(String value) {
            addCriterion("xh <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLike(String value) {
            addCriterion("xh like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotLike(String value) {
            addCriterion("xh not like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<String> values) {
            addCriterion("xh in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<String> values) {
            addCriterion("xh not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(String value1, String value2) {
            addCriterion("xh between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(String value1, String value2) {
            addCriterion("xh not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andDabhIsNull() {
            addCriterion("dabh is null");
            return (Criteria) this;
        }

        public Criteria andDabhIsNotNull() {
            addCriterion("dabh is not null");
            return (Criteria) this;
        }

        public Criteria andDabhEqualTo(String value) {
            addCriterion("dabh =", value, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhNotEqualTo(String value) {
            addCriterion("dabh <>", value, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhGreaterThan(String value) {
            addCriterion("dabh >", value, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhGreaterThanOrEqualTo(String value) {
            addCriterion("dabh >=", value, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhLessThan(String value) {
            addCriterion("dabh <", value, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhLessThanOrEqualTo(String value) {
            addCriterion("dabh <=", value, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhLike(String value) {
            addCriterion("dabh like", value, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhNotLike(String value) {
            addCriterion("dabh not like", value, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhIn(List<String> values) {
            addCriterion("dabh in", values, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhNotIn(List<String> values) {
            addCriterion("dabh not in", values, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhBetween(String value1, String value2) {
            addCriterion("dabh between", value1, value2, "dabh");
            return (Criteria) this;
        }

        public Criteria andDabhNotBetween(String value1, String value2) {
            addCriterion("dabh not between", value1, value2, "dabh");
            return (Criteria) this;
        }

        public Criteria andZsxxdzIsNull() {
            addCriterion("zsxxdz is null");
            return (Criteria) this;
        }

        public Criteria andZsxxdzIsNotNull() {
            addCriterion("zsxxdz is not null");
            return (Criteria) this;
        }

        public Criteria andZsxxdzEqualTo(String value) {
            addCriterion("zsxxdz =", value, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzNotEqualTo(String value) {
            addCriterion("zsxxdz <>", value, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzGreaterThan(String value) {
            addCriterion("zsxxdz >", value, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzGreaterThanOrEqualTo(String value) {
            addCriterion("zsxxdz >=", value, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzLessThan(String value) {
            addCriterion("zsxxdz <", value, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzLessThanOrEqualTo(String value) {
            addCriterion("zsxxdz <=", value, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzLike(String value) {
            addCriterion("zsxxdz like", value, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzNotLike(String value) {
            addCriterion("zsxxdz not like", value, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzIn(List<String> values) {
            addCriterion("zsxxdz in", values, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzNotIn(List<String> values) {
            addCriterion("zsxxdz not in", values, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzBetween(String value1, String value2) {
            addCriterion("zsxxdz between", value1, value2, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andZsxxdzNotBetween(String value1, String value2) {
            addCriterion("zsxxdz not between", value1, value2, "zsxxdz");
            return (Criteria) this;
        }

        public Criteria andWfsjIsNull() {
            addCriterion("wfsj is null");
            return (Criteria) this;
        }

        public Criteria andWfsjIsNotNull() {
            addCriterion("wfsj is not null");
            return (Criteria) this;
        }

        public Criteria andWfsjEqualTo(Date value) {
            addCriterion("wfsj =", value, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfsjNotEqualTo(Date value) {
            addCriterion("wfsj <>", value, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfsjGreaterThan(Date value) {
            addCriterion("wfsj >", value, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfsjGreaterThanOrEqualTo(Date value) {
            addCriterion("wfsj >=", value, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfsjLessThan(Date value) {
            addCriterion("wfsj <", value, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfsjLessThanOrEqualTo(Date value) {
            addCriterion("wfsj <=", value, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfsjIn(List<Date> values) {
            addCriterion("wfsj in", values, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfsjNotIn(List<Date> values) {
            addCriterion("wfsj not in", values, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfsjBetween(Date value1, Date value2) {
            addCriterion("wfsj between", value1, value2, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfsjNotBetween(Date value1, Date value2) {
            addCriterion("wfsj not between", value1, value2, "wfsj");
            return (Criteria) this;
        }

        public Criteria andWfddIsNull() {
            addCriterion("wfdd is null");
            return (Criteria) this;
        }

        public Criteria andWfddIsNotNull() {
            addCriterion("wfdd is not null");
            return (Criteria) this;
        }

        public Criteria andWfddEqualTo(String value) {
            addCriterion("wfdd =", value, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddNotEqualTo(String value) {
            addCriterion("wfdd <>", value, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddGreaterThan(String value) {
            addCriterion("wfdd >", value, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddGreaterThanOrEqualTo(String value) {
            addCriterion("wfdd >=", value, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddLessThan(String value) {
            addCriterion("wfdd <", value, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddLessThanOrEqualTo(String value) {
            addCriterion("wfdd <=", value, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddLike(String value) {
            addCriterion("wfdd like", value, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddNotLike(String value) {
            addCriterion("wfdd not like", value, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddIn(List<String> values) {
            addCriterion("wfdd in", values, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddNotIn(List<String> values) {
            addCriterion("wfdd not in", values, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddBetween(String value1, String value2) {
            addCriterion("wfdd between", value1, value2, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfddNotBetween(String value1, String value2) {
            addCriterion("wfdd not between", value1, value2, "wfdd");
            return (Criteria) this;
        }

        public Criteria andWfdzIsNull() {
            addCriterion("wfdz is null");
            return (Criteria) this;
        }

        public Criteria andWfdzIsNotNull() {
            addCriterion("wfdz is not null");
            return (Criteria) this;
        }

        public Criteria andWfdzEqualTo(String value) {
            addCriterion("wfdz =", value, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzNotEqualTo(String value) {
            addCriterion("wfdz <>", value, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzGreaterThan(String value) {
            addCriterion("wfdz >", value, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzGreaterThanOrEqualTo(String value) {
            addCriterion("wfdz >=", value, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzLessThan(String value) {
            addCriterion("wfdz <", value, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzLessThanOrEqualTo(String value) {
            addCriterion("wfdz <=", value, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzLike(String value) {
            addCriterion("wfdz like", value, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzNotLike(String value) {
            addCriterion("wfdz not like", value, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzIn(List<String> values) {
            addCriterion("wfdz in", values, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzNotIn(List<String> values) {
            addCriterion("wfdz not in", values, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzBetween(String value1, String value2) {
            addCriterion("wfdz between", value1, value2, "wfdz");
            return (Criteria) this;
        }

        public Criteria andWfdzNotBetween(String value1, String value2) {
            addCriterion("wfdz not between", value1, value2, "wfdz");
            return (Criteria) this;
        }

        public Criteria andZqmjIsNull() {
            addCriterion("zqmj is null");
            return (Criteria) this;
        }

        public Criteria andZqmjIsNotNull() {
            addCriterion("zqmj is not null");
            return (Criteria) this;
        }

        public Criteria andZqmjEqualTo(String value) {
            addCriterion("zqmj =", value, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjNotEqualTo(String value) {
            addCriterion("zqmj <>", value, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjGreaterThan(String value) {
            addCriterion("zqmj >", value, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjGreaterThanOrEqualTo(String value) {
            addCriterion("zqmj >=", value, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjLessThan(String value) {
            addCriterion("zqmj <", value, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjLessThanOrEqualTo(String value) {
            addCriterion("zqmj <=", value, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjLike(String value) {
            addCriterion("zqmj like", value, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjNotLike(String value) {
            addCriterion("zqmj not like", value, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjIn(List<String> values) {
            addCriterion("zqmj in", values, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjNotIn(List<String> values) {
            addCriterion("zqmj not in", values, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjBetween(String value1, String value2) {
            addCriterion("zqmj between", value1, value2, "zqmj");
            return (Criteria) this;
        }

        public Criteria andZqmjNotBetween(String value1, String value2) {
            addCriterion("zqmj not between", value1, value2, "zqmj");
            return (Criteria) this;
        }

        public Criteria andFxjgmcIsNull() {
            addCriterion("fxjgmc is null");
            return (Criteria) this;
        }

        public Criteria andFxjgmcIsNotNull() {
            addCriterion("fxjgmc is not null");
            return (Criteria) this;
        }

        public Criteria andFxjgmcEqualTo(String value) {
            addCriterion("fxjgmc =", value, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcNotEqualTo(String value) {
            addCriterion("fxjgmc <>", value, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcGreaterThan(String value) {
            addCriterion("fxjgmc >", value, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcGreaterThanOrEqualTo(String value) {
            addCriterion("fxjgmc >=", value, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcLessThan(String value) {
            addCriterion("fxjgmc <", value, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcLessThanOrEqualTo(String value) {
            addCriterion("fxjgmc <=", value, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcLike(String value) {
            addCriterion("fxjgmc like", value, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcNotLike(String value) {
            addCriterion("fxjgmc not like", value, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcIn(List<String> values) {
            addCriterion("fxjgmc in", values, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcNotIn(List<String> values) {
            addCriterion("fxjgmc not in", values, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcBetween(String value1, String value2) {
            addCriterion("fxjgmc between", value1, value2, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFxjgmcNotBetween(String value1, String value2) {
            addCriterion("fxjgmc not between", value1, value2, "fxjgmc");
            return (Criteria) this;
        }

        public Criteria andFzjgIsNull() {
            addCriterion("fzjg is null");
            return (Criteria) this;
        }

        public Criteria andFzjgIsNotNull() {
            addCriterion("fzjg is not null");
            return (Criteria) this;
        }

        public Criteria andFzjgEqualTo(String value) {
            addCriterion("fzjg =", value, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgNotEqualTo(String value) {
            addCriterion("fzjg <>", value, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgGreaterThan(String value) {
            addCriterion("fzjg >", value, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgGreaterThanOrEqualTo(String value) {
            addCriterion("fzjg >=", value, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgLessThan(String value) {
            addCriterion("fzjg <", value, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgLessThanOrEqualTo(String value) {
            addCriterion("fzjg <=", value, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgLike(String value) {
            addCriterion("fzjg like", value, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgNotLike(String value) {
            addCriterion("fzjg not like", value, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgIn(List<String> values) {
            addCriterion("fzjg in", values, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgNotIn(List<String> values) {
            addCriterion("fzjg not in", values, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgBetween(String value1, String value2) {
            addCriterion("fzjg between", value1, value2, "fzjg");
            return (Criteria) this;
        }

        public Criteria andFzjgNotBetween(String value1, String value2) {
            addCriterion("fzjg not between", value1, value2, "fzjg");
            return (Criteria) this;
        }

        public Criteria andSjxmIsNull() {
            addCriterion("sjxm is null");
            return (Criteria) this;
        }

        public Criteria andSjxmIsNotNull() {
            addCriterion("sjxm is not null");
            return (Criteria) this;
        }

        public Criteria andSjxmEqualTo(String value) {
            addCriterion("sjxm =", value, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmNotEqualTo(String value) {
            addCriterion("sjxm <>", value, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmGreaterThan(String value) {
            addCriterion("sjxm >", value, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmGreaterThanOrEqualTo(String value) {
            addCriterion("sjxm >=", value, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmLessThan(String value) {
            addCriterion("sjxm <", value, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmLessThanOrEqualTo(String value) {
            addCriterion("sjxm <=", value, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmLike(String value) {
            addCriterion("sjxm like", value, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmNotLike(String value) {
            addCriterion("sjxm not like", value, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmIn(List<String> values) {
            addCriterion("sjxm in", values, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmNotIn(List<String> values) {
            addCriterion("sjxm not in", values, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmBetween(String value1, String value2) {
            addCriterion("sjxm between", value1, value2, "sjxm");
            return (Criteria) this;
        }

        public Criteria andSjxmNotBetween(String value1, String value2) {
            addCriterion("sjxm not between", value1, value2, "sjxm");
            return (Criteria) this;
        }

        public Criteria andZjcbhIsNull() {
            addCriterion("zjcbh is null");
            return (Criteria) this;
        }

        public Criteria andZjcbhIsNotNull() {
            addCriterion("zjcbh is not null");
            return (Criteria) this;
        }

        public Criteria andZjcbhEqualTo(String value) {
            addCriterion("zjcbh =", value, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhNotEqualTo(String value) {
            addCriterion("zjcbh <>", value, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhGreaterThan(String value) {
            addCriterion("zjcbh >", value, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhGreaterThanOrEqualTo(String value) {
            addCriterion("zjcbh >=", value, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhLessThan(String value) {
            addCriterion("zjcbh <", value, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhLessThanOrEqualTo(String value) {
            addCriterion("zjcbh <=", value, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhLike(String value) {
            addCriterion("zjcbh like", value, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhNotLike(String value) {
            addCriterion("zjcbh not like", value, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhIn(List<String> values) {
            addCriterion("zjcbh in", values, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhNotIn(List<String> values) {
            addCriterion("zjcbh not in", values, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhBetween(String value1, String value2) {
            addCriterion("zjcbh between", value1, value2, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andZjcbhNotBetween(String value1, String value2) {
            addCriterion("zjcbh not between", value1, value2, "zjcbh");
            return (Criteria) this;
        }

        public Criteria andWfdmIsNull() {
            addCriterion("wfdm is null");
            return (Criteria) this;
        }

        public Criteria andWfdmIsNotNull() {
            addCriterion("wfdm is not null");
            return (Criteria) this;
        }

        public Criteria andWfdmEqualTo(String value) {
            addCriterion("wfdm =", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmNotEqualTo(String value) {
            addCriterion("wfdm <>", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmGreaterThan(String value) {
            addCriterion("wfdm >", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmGreaterThanOrEqualTo(String value) {
            addCriterion("wfdm >=", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmLessThan(String value) {
            addCriterion("wfdm <", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmLessThanOrEqualTo(String value) {
            addCriterion("wfdm <=", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmLike(String value) {
            addCriterion("wfdm like", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmNotLike(String value) {
            addCriterion("wfdm not like", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmIn(List<String> values) {
            addCriterion("wfdm in", values, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmNotIn(List<String> values) {
            addCriterion("wfdm not in", values, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmBetween(String value1, String value2) {
            addCriterion("wfdm between", value1, value2, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmNotBetween(String value1, String value2) {
            addCriterion("wfdm not between", value1, value2, "wfdm");
            return (Criteria) this;
        }

        public Criteria andRcrqIsNull() {
            addCriterion("rcrq is null");
            return (Criteria) this;
        }

        public Criteria andRcrqIsNotNull() {
            addCriterion("rcrq is not null");
            return (Criteria) this;
        }

        public Criteria andRcrqEqualTo(Date value) {
            addCriterion("rcrq =", value, "rcrq");
            return (Criteria) this;
        }

        public Criteria andRcrqNotEqualTo(Date value) {
            addCriterion("rcrq <>", value, "rcrq");
            return (Criteria) this;
        }

        public Criteria andRcrqGreaterThan(Date value) {
            addCriterion("rcrq >", value, "rcrq");
            return (Criteria) this;
        }

        public Criteria andRcrqGreaterThanOrEqualTo(Date value) {
            addCriterion("rcrq >=", value, "rcrq");
            return (Criteria) this;
        }

        public Criteria andRcrqLessThan(Date value) {
            addCriterion("rcrq <", value, "rcrq");
            return (Criteria) this;
        }

        public Criteria andRcrqLessThanOrEqualTo(Date value) {
            addCriterion("rcrq <=", value, "rcrq");
            return (Criteria) this;
        }

        public Criteria andRcrqIn(List<Date> values) {
            addCriterion("rcrq in", values, "rcrq");
            return (Criteria) this;
        }

        public Criteria andRcrqNotIn(List<Date> values) {
            addCriterion("rcrq not in", values, "rcrq");
            return (Criteria) this;
        }

        public Criteria andRcrqBetween(Date value1, Date value2) {
            addCriterion("rcrq between", value1, value2, "rcrq");
            return (Criteria) this;
        }

        public Criteria andRcrqNotBetween(Date value1, Date value2) {
            addCriterion("rcrq not between", value1, value2, "rcrq");
            return (Criteria) this;
        }

        public Criteria andYcyIsNull() {
            addCriterion("ycy is null");
            return (Criteria) this;
        }

        public Criteria andYcyIsNotNull() {
            addCriterion("ycy is not null");
            return (Criteria) this;
        }

        public Criteria andYcyEqualTo(String value) {
            addCriterion("ycy =", value, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyNotEqualTo(String value) {
            addCriterion("ycy <>", value, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyGreaterThan(String value) {
            addCriterion("ycy >", value, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyGreaterThanOrEqualTo(String value) {
            addCriterion("ycy >=", value, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyLessThan(String value) {
            addCriterion("ycy <", value, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyLessThanOrEqualTo(String value) {
            addCriterion("ycy <=", value, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyLike(String value) {
            addCriterion("ycy like", value, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyNotLike(String value) {
            addCriterion("ycy not like", value, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyIn(List<String> values) {
            addCriterion("ycy in", values, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyNotIn(List<String> values) {
            addCriterion("ycy not in", values, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyBetween(String value1, String value2) {
            addCriterion("ycy between", value1, value2, "ycy");
            return (Criteria) this;
        }

        public Criteria andYcyNotBetween(String value1, String value2) {
            addCriterion("ycy not between", value1, value2, "ycy");
            return (Criteria) this;
        }

        public Criteria andTfwzIsNull() {
            addCriterion("tfwz is null");
            return (Criteria) this;
        }

        public Criteria andTfwzIsNotNull() {
            addCriterion("tfwz is not null");
            return (Criteria) this;
        }

        public Criteria andTfwzEqualTo(String value) {
            addCriterion("tfwz =", value, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzNotEqualTo(String value) {
            addCriterion("tfwz <>", value, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzGreaterThan(String value) {
            addCriterion("tfwz >", value, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzGreaterThanOrEqualTo(String value) {
            addCriterion("tfwz >=", value, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzLessThan(String value) {
            addCriterion("tfwz <", value, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzLessThanOrEqualTo(String value) {
            addCriterion("tfwz <=", value, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzLike(String value) {
            addCriterion("tfwz like", value, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzNotLike(String value) {
            addCriterion("tfwz not like", value, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzIn(List<String> values) {
            addCriterion("tfwz in", values, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzNotIn(List<String> values) {
            addCriterion("tfwz not in", values, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzBetween(String value1, String value2) {
            addCriterion("tfwz between", value1, value2, "tfwz");
            return (Criteria) this;
        }

        public Criteria andTfwzNotBetween(String value1, String value2) {
            addCriterion("tfwz not between", value1, value2, "tfwz");
            return (Criteria) this;
        }

        public Criteria andCcrqIsNull() {
            addCriterion("ccrq is null");
            return (Criteria) this;
        }

        public Criteria andCcrqIsNotNull() {
            addCriterion("ccrq is not null");
            return (Criteria) this;
        }

        public Criteria andCcrqEqualTo(Date value) {
            addCriterion("ccrq =", value, "ccrq");
            return (Criteria) this;
        }

        public Criteria andCcrqNotEqualTo(Date value) {
            addCriterion("ccrq <>", value, "ccrq");
            return (Criteria) this;
        }

        public Criteria andCcrqGreaterThan(Date value) {
            addCriterion("ccrq >", value, "ccrq");
            return (Criteria) this;
        }

        public Criteria andCcrqGreaterThanOrEqualTo(Date value) {
            addCriterion("ccrq >=", value, "ccrq");
            return (Criteria) this;
        }

        public Criteria andCcrqLessThan(Date value) {
            addCriterion("ccrq <", value, "ccrq");
            return (Criteria) this;
        }

        public Criteria andCcrqLessThanOrEqualTo(Date value) {
            addCriterion("ccrq <=", value, "ccrq");
            return (Criteria) this;
        }

        public Criteria andCcrqIn(List<Date> values) {
            addCriterion("ccrq in", values, "ccrq");
            return (Criteria) this;
        }

        public Criteria andCcrqNotIn(List<Date> values) {
            addCriterion("ccrq not in", values, "ccrq");
            return (Criteria) this;
        }

        public Criteria andCcrqBetween(Date value1, Date value2) {
            addCriterion("ccrq between", value1, value2, "ccrq");
            return (Criteria) this;
        }

        public Criteria andCcrqNotBetween(Date value1, Date value2) {
            addCriterion("ccrq not between", value1, value2, "ccrq");
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

        public Criteria andCcyyIsNull() {
            addCriterion("ccyy is null");
            return (Criteria) this;
        }

        public Criteria andCcyyIsNotNull() {
            addCriterion("ccyy is not null");
            return (Criteria) this;
        }

        public Criteria andCcyyEqualTo(String value) {
            addCriterion("ccyy =", value, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyNotEqualTo(String value) {
            addCriterion("ccyy <>", value, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyGreaterThan(String value) {
            addCriterion("ccyy >", value, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyGreaterThanOrEqualTo(String value) {
            addCriterion("ccyy >=", value, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyLessThan(String value) {
            addCriterion("ccyy <", value, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyLessThanOrEqualTo(String value) {
            addCriterion("ccyy <=", value, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyLike(String value) {
            addCriterion("ccyy like", value, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyNotLike(String value) {
            addCriterion("ccyy not like", value, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyIn(List<String> values) {
            addCriterion("ccyy in", values, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyNotIn(List<String> values) {
            addCriterion("ccyy not in", values, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyBetween(String value1, String value2) {
            addCriterion("ccyy between", value1, value2, "ccyy");
            return (Criteria) this;
        }

        public Criteria andCcyyNotBetween(String value1, String value2) {
            addCriterion("ccyy not between", value1, value2, "ccyy");
            return (Criteria) this;
        }

        public Criteria andYjdwIsNull() {
            addCriterion("yjdw is null");
            return (Criteria) this;
        }

        public Criteria andYjdwIsNotNull() {
            addCriterion("yjdw is not null");
            return (Criteria) this;
        }

        public Criteria andYjdwEqualTo(String value) {
            addCriterion("yjdw =", value, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwNotEqualTo(String value) {
            addCriterion("yjdw <>", value, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwGreaterThan(String value) {
            addCriterion("yjdw >", value, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwGreaterThanOrEqualTo(String value) {
            addCriterion("yjdw >=", value, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwLessThan(String value) {
            addCriterion("yjdw <", value, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwLessThanOrEqualTo(String value) {
            addCriterion("yjdw <=", value, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwLike(String value) {
            addCriterion("yjdw like", value, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwNotLike(String value) {
            addCriterion("yjdw not like", value, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwIn(List<String> values) {
            addCriterion("yjdw in", values, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwNotIn(List<String> values) {
            addCriterion("yjdw not in", values, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwBetween(String value1, String value2) {
            addCriterion("yjdw between", value1, value2, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwNotBetween(String value1, String value2) {
            addCriterion("yjdw not between", value1, value2, "yjdw");
            return (Criteria) this;
        }

        public Criteria andYjdwmcIsNull() {
            addCriterion("yjdwmc is null");
            return (Criteria) this;
        }

        public Criteria andYjdwmcIsNotNull() {
            addCriterion("yjdwmc is not null");
            return (Criteria) this;
        }

        public Criteria andYjdwmcEqualTo(String value) {
            addCriterion("yjdwmc =", value, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcNotEqualTo(String value) {
            addCriterion("yjdwmc <>", value, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcGreaterThan(String value) {
            addCriterion("yjdwmc >", value, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("yjdwmc >=", value, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcLessThan(String value) {
            addCriterion("yjdwmc <", value, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcLessThanOrEqualTo(String value) {
            addCriterion("yjdwmc <=", value, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcLike(String value) {
            addCriterion("yjdwmc like", value, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcNotLike(String value) {
            addCriterion("yjdwmc not like", value, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcIn(List<String> values) {
            addCriterion("yjdwmc in", values, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcNotIn(List<String> values) {
            addCriterion("yjdwmc not in", values, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcBetween(String value1, String value2) {
            addCriterion("yjdwmc between", value1, value2, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andYjdwmcNotBetween(String value1, String value2) {
            addCriterion("yjdwmc not between", value1, value2, "yjdwmc");
            return (Criteria) this;
        }

        public Criteria andCcjsrIsNull() {
            addCriterion("ccjsr is null");
            return (Criteria) this;
        }

        public Criteria andCcjsrIsNotNull() {
            addCriterion("ccjsr is not null");
            return (Criteria) this;
        }

        public Criteria andCcjsrEqualTo(String value) {
            addCriterion("ccjsr =", value, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrNotEqualTo(String value) {
            addCriterion("ccjsr <>", value, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrGreaterThan(String value) {
            addCriterion("ccjsr >", value, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrGreaterThanOrEqualTo(String value) {
            addCriterion("ccjsr >=", value, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrLessThan(String value) {
            addCriterion("ccjsr <", value, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrLessThanOrEqualTo(String value) {
            addCriterion("ccjsr <=", value, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrLike(String value) {
            addCriterion("ccjsr like", value, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrNotLike(String value) {
            addCriterion("ccjsr not like", value, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrIn(List<String> values) {
            addCriterion("ccjsr in", values, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrNotIn(List<String> values) {
            addCriterion("ccjsr not in", values, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrBetween(String value1, String value2) {
            addCriterion("ccjsr between", value1, value2, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcjsrNotBetween(String value1, String value2) {
            addCriterion("ccjsr not between", value1, value2, "ccjsr");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhIsNull() {
            addCriterion("ccrlxdh is null");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhIsNotNull() {
            addCriterion("ccrlxdh is not null");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhEqualTo(String value) {
            addCriterion("ccrlxdh =", value, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhNotEqualTo(String value) {
            addCriterion("ccrlxdh <>", value, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhGreaterThan(String value) {
            addCriterion("ccrlxdh >", value, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhGreaterThanOrEqualTo(String value) {
            addCriterion("ccrlxdh >=", value, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhLessThan(String value) {
            addCriterion("ccrlxdh <", value, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhLessThanOrEqualTo(String value) {
            addCriterion("ccrlxdh <=", value, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhLike(String value) {
            addCriterion("ccrlxdh like", value, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhNotLike(String value) {
            addCriterion("ccrlxdh not like", value, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhIn(List<String> values) {
            addCriterion("ccrlxdh in", values, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhNotIn(List<String> values) {
            addCriterion("ccrlxdh not in", values, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhBetween(String value1, String value2) {
            addCriterion("ccrlxdh between", value1, value2, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcrlxdhNotBetween(String value1, String value2) {
            addCriterion("ccrlxdh not between", value1, value2, "ccrlxdh");
            return (Criteria) this;
        }

        public Criteria andCcmsIsNull() {
            addCriterion("ccms is null");
            return (Criteria) this;
        }

        public Criteria andCcmsIsNotNull() {
            addCriterion("ccms is not null");
            return (Criteria) this;
        }

        public Criteria andCcmsEqualTo(String value) {
            addCriterion("ccms =", value, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsNotEqualTo(String value) {
            addCriterion("ccms <>", value, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsGreaterThan(String value) {
            addCriterion("ccms >", value, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsGreaterThanOrEqualTo(String value) {
            addCriterion("ccms >=", value, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsLessThan(String value) {
            addCriterion("ccms <", value, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsLessThanOrEqualTo(String value) {
            addCriterion("ccms <=", value, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsLike(String value) {
            addCriterion("ccms like", value, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsNotLike(String value) {
            addCriterion("ccms not like", value, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsIn(List<String> values) {
            addCriterion("ccms in", values, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsNotIn(List<String> values) {
            addCriterion("ccms not in", values, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsBetween(String value1, String value2) {
            addCriterion("ccms between", value1, value2, "ccms");
            return (Criteria) this;
        }

        public Criteria andCcmsNotBetween(String value1, String value2) {
            addCriterion("ccms not between", value1, value2, "ccms");
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