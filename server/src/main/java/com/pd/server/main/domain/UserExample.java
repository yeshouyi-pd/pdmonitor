package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andLoginNameIsNull() {
            addCriterion("login_name is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("login_name is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("login_name =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("login_name <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("login_name >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("login_name >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("login_name <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("login_name <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("login_name like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("login_name not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("login_name in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("login_name not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("login_name between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("login_name not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("`password` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("`password` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("`password` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("`password` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("`password` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`password` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("`password` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("`password` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("`password` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("`password` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("`password` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("`password` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("`password` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("`password` not between", value1, value2, "password");
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

        public Criteria andRodeIsNull() {
            addCriterion("rode is null");
            return (Criteria) this;
        }

        public Criteria andRodeIsNotNull() {
            addCriterion("rode is not null");
            return (Criteria) this;
        }

        public Criteria andRodeEqualTo(String value) {
            addCriterion("rode =", value, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeNotEqualTo(String value) {
            addCriterion("rode <>", value, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeGreaterThan(String value) {
            addCriterion("rode >", value, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeGreaterThanOrEqualTo(String value) {
            addCriterion("rode >=", value, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeLessThan(String value) {
            addCriterion("rode <", value, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeLessThanOrEqualTo(String value) {
            addCriterion("rode <=", value, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeLike(String value) {
            addCriterion("rode like", value, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeNotLike(String value) {
            addCriterion("rode not like", value, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeIn(List<String> values) {
            addCriterion("rode in", values, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeNotIn(List<String> values) {
            addCriterion("rode not in", values, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeBetween(String value1, String value2) {
            addCriterion("rode between", value1, value2, "rode");
            return (Criteria) this;
        }

        public Criteria andRodeNotBetween(String value1, String value2) {
            addCriterion("rode not between", value1, value2, "rode");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andYhyxqIsNull() {
            addCriterion("yhyxq is null");
            return (Criteria) this;
        }

        public Criteria andYhyxqIsNotNull() {
            addCriterion("yhyxq is not null");
            return (Criteria) this;
        }

        public Criteria andYhyxqEqualTo(Date value) {
            addCriterionForJDBCDate("yhyxq =", value, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andYhyxqNotEqualTo(Date value) {
            addCriterionForJDBCDate("yhyxq <>", value, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andYhyxqGreaterThan(Date value) {
            addCriterionForJDBCDate("yhyxq >", value, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andYhyxqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yhyxq >=", value, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andYhyxqLessThan(Date value) {
            addCriterionForJDBCDate("yhyxq <", value, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andYhyxqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yhyxq <=", value, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andYhyxqIn(List<Date> values) {
            addCriterionForJDBCDate("yhyxq in", values, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andYhyxqNotIn(List<Date> values) {
            addCriterionForJDBCDate("yhyxq not in", values, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andYhyxqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yhyxq between", value1, value2, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andYhyxqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yhyxq not between", value1, value2, "yhyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqIsNull() {
            addCriterion("mmyxq is null");
            return (Criteria) this;
        }

        public Criteria andMmyxqIsNotNull() {
            addCriterion("mmyxq is not null");
            return (Criteria) this;
        }

        public Criteria andMmyxqEqualTo(Date value) {
            addCriterionForJDBCDate("mmyxq =", value, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqNotEqualTo(Date value) {
            addCriterionForJDBCDate("mmyxq <>", value, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqGreaterThan(Date value) {
            addCriterionForJDBCDate("mmyxq >", value, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("mmyxq >=", value, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqLessThan(Date value) {
            addCriterionForJDBCDate("mmyxq <", value, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("mmyxq <=", value, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqIn(List<Date> values) {
            addCriterionForJDBCDate("mmyxq in", values, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqNotIn(List<Date> values) {
            addCriterionForJDBCDate("mmyxq not in", values, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("mmyxq between", value1, value2, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andMmyxqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("mmyxq not between", value1, value2, "mmyxq");
            return (Criteria) this;
        }

        public Criteria andSfjyIsNull() {
            addCriterion("sfjy is null");
            return (Criteria) this;
        }

        public Criteria andSfjyIsNotNull() {
            addCriterion("sfjy is not null");
            return (Criteria) this;
        }

        public Criteria andSfjyEqualTo(String value) {
            addCriterion("sfjy =", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyNotEqualTo(String value) {
            addCriterion("sfjy <>", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyGreaterThan(String value) {
            addCriterion("sfjy >", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyGreaterThanOrEqualTo(String value) {
            addCriterion("sfjy >=", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyLessThan(String value) {
            addCriterion("sfjy <", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyLessThanOrEqualTo(String value) {
            addCriterion("sfjy <=", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyLike(String value) {
            addCriterion("sfjy like", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyNotLike(String value) {
            addCriterion("sfjy not like", value, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyIn(List<String> values) {
            addCriterion("sfjy in", values, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyNotIn(List<String> values) {
            addCriterion("sfjy not in", values, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyBetween(String value1, String value2) {
            addCriterion("sfjy between", value1, value2, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfjyNotBetween(String value1, String value2) {
            addCriterion("sfjy not between", value1, value2, "sfjy");
            return (Criteria) this;
        }

        public Criteria andSfzhmIsNull() {
            addCriterion("sfzhm is null");
            return (Criteria) this;
        }

        public Criteria andSfzhmIsNotNull() {
            addCriterion("sfzhm is not null");
            return (Criteria) this;
        }

        public Criteria andSfzhmEqualTo(String value) {
            addCriterion("sfzhm =", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmNotEqualTo(String value) {
            addCriterion("sfzhm <>", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmGreaterThan(String value) {
            addCriterion("sfzhm >", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmGreaterThanOrEqualTo(String value) {
            addCriterion("sfzhm >=", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmLessThan(String value) {
            addCriterion("sfzhm <", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmLessThanOrEqualTo(String value) {
            addCriterion("sfzhm <=", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmLike(String value) {
            addCriterion("sfzhm like", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmNotLike(String value) {
            addCriterion("sfzhm not like", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmIn(List<String> values) {
            addCriterion("sfzhm in", values, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmNotIn(List<String> values) {
            addCriterion("sfzhm not in", values, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmBetween(String value1, String value2) {
            addCriterion("sfzhm between", value1, value2, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmNotBetween(String value1, String value2) {
            addCriterion("sfzhm not between", value1, value2, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andJjbhIsNull() {
            addCriterion("jjbh is null");
            return (Criteria) this;
        }

        public Criteria andJjbhIsNotNull() {
            addCriterion("jjbh is not null");
            return (Criteria) this;
        }

        public Criteria andJjbhEqualTo(String value) {
            addCriterion("jjbh =", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhNotEqualTo(String value) {
            addCriterion("jjbh <>", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhGreaterThan(String value) {
            addCriterion("jjbh >", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhGreaterThanOrEqualTo(String value) {
            addCriterion("jjbh >=", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhLessThan(String value) {
            addCriterion("jjbh <", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhLessThanOrEqualTo(String value) {
            addCriterion("jjbh <=", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhLike(String value) {
            addCriterion("jjbh like", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhNotLike(String value) {
            addCriterion("jjbh not like", value, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhIn(List<String> values) {
            addCriterion("jjbh in", values, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhNotIn(List<String> values) {
            addCriterion("jjbh not in", values, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhBetween(String value1, String value2) {
            addCriterion("jjbh between", value1, value2, "jjbh");
            return (Criteria) this;
        }

        public Criteria andJjbhNotBetween(String value1, String value2) {
            addCriterion("jjbh not between", value1, value2, "jjbh");
            return (Criteria) this;
        }

        public Criteria andIpstartIsNull() {
            addCriterion("ipstart is null");
            return (Criteria) this;
        }

        public Criteria andIpstartIsNotNull() {
            addCriterion("ipstart is not null");
            return (Criteria) this;
        }

        public Criteria andIpstartEqualTo(String value) {
            addCriterion("ipstart =", value, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartNotEqualTo(String value) {
            addCriterion("ipstart <>", value, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartGreaterThan(String value) {
            addCriterion("ipstart >", value, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartGreaterThanOrEqualTo(String value) {
            addCriterion("ipstart >=", value, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartLessThan(String value) {
            addCriterion("ipstart <", value, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartLessThanOrEqualTo(String value) {
            addCriterion("ipstart <=", value, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartLike(String value) {
            addCriterion("ipstart like", value, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartNotLike(String value) {
            addCriterion("ipstart not like", value, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartIn(List<String> values) {
            addCriterion("ipstart in", values, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartNotIn(List<String> values) {
            addCriterion("ipstart not in", values, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartBetween(String value1, String value2) {
            addCriterion("ipstart between", value1, value2, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpstartNotBetween(String value1, String value2) {
            addCriterion("ipstart not between", value1, value2, "ipstart");
            return (Criteria) this;
        }

        public Criteria andIpendIsNull() {
            addCriterion("ipend is null");
            return (Criteria) this;
        }

        public Criteria andIpendIsNotNull() {
            addCriterion("ipend is not null");
            return (Criteria) this;
        }

        public Criteria andIpendEqualTo(String value) {
            addCriterion("ipend =", value, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendNotEqualTo(String value) {
            addCriterion("ipend <>", value, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendGreaterThan(String value) {
            addCriterion("ipend >", value, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendGreaterThanOrEqualTo(String value) {
            addCriterion("ipend >=", value, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendLessThan(String value) {
            addCriterion("ipend <", value, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendLessThanOrEqualTo(String value) {
            addCriterion("ipend <=", value, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendLike(String value) {
            addCriterion("ipend like", value, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendNotLike(String value) {
            addCriterion("ipend not like", value, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendIn(List<String> values) {
            addCriterion("ipend in", values, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendNotIn(List<String> values) {
            addCriterion("ipend not in", values, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendBetween(String value1, String value2) {
            addCriterion("ipend between", value1, value2, "ipend");
            return (Criteria) this;
        }

        public Criteria andIpendNotBetween(String value1, String value2) {
            addCriterion("ipend not between", value1, value2, "ipend");
            return (Criteria) this;
        }

        public Criteria andMacIsNull() {
            addCriterion("mac is null");
            return (Criteria) this;
        }

        public Criteria andMacIsNotNull() {
            addCriterion("mac is not null");
            return (Criteria) this;
        }

        public Criteria andMacEqualTo(String value) {
            addCriterion("mac =", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotEqualTo(String value) {
            addCriterion("mac <>", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThan(String value) {
            addCriterion("mac >", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThanOrEqualTo(String value) {
            addCriterion("mac >=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThan(String value) {
            addCriterion("mac <", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThanOrEqualTo(String value) {
            addCriterion("mac <=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLike(String value) {
            addCriterion("mac like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotLike(String value) {
            addCriterion("mac not like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacIn(List<String> values) {
            addCriterion("mac in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotIn(List<String> values) {
            addCriterion("mac not in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacBetween(String value1, String value2) {
            addCriterion("mac between", value1, value2, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotBetween(String value1, String value2) {
            addCriterion("mac not between", value1, value2, "mac");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
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
            addCriterionForJDBCDate("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeIsNull() {
            addCriterion("lastlogin_time is null");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeIsNotNull() {
            addCriterion("lastlogin_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeEqualTo(Date value) {
            addCriterionForJDBCDate("lastlogin_time =", value, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("lastlogin_time <>", value, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("lastlogin_time >", value, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastlogin_time >=", value, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeLessThan(Date value) {
            addCriterionForJDBCDate("lastlogin_time <", value, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastlogin_time <=", value, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeIn(List<Date> values) {
            addCriterionForJDBCDate("lastlogin_time in", values, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("lastlogin_time not in", values, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastlogin_time between", value1, value2, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andLastloginTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastlogin_time not between", value1, value2, "lastloginTime");
            return (Criteria) this;
        }

        public Criteria andSbcsIsNull() {
            addCriterion("sbcs is null");
            return (Criteria) this;
        }

        public Criteria andSbcsIsNotNull() {
            addCriterion("sbcs is not null");
            return (Criteria) this;
        }

        public Criteria andSbcsEqualTo(Integer value) {
            addCriterion("sbcs =", value, "sbcs");
            return (Criteria) this;
        }

        public Criteria andSbcsNotEqualTo(Integer value) {
            addCriterion("sbcs <>", value, "sbcs");
            return (Criteria) this;
        }

        public Criteria andSbcsGreaterThan(Integer value) {
            addCriterion("sbcs >", value, "sbcs");
            return (Criteria) this;
        }

        public Criteria andSbcsGreaterThanOrEqualTo(Integer value) {
            addCriterion("sbcs >=", value, "sbcs");
            return (Criteria) this;
        }

        public Criteria andSbcsLessThan(Integer value) {
            addCriterion("sbcs <", value, "sbcs");
            return (Criteria) this;
        }

        public Criteria andSbcsLessThanOrEqualTo(Integer value) {
            addCriterion("sbcs <=", value, "sbcs");
            return (Criteria) this;
        }

        public Criteria andSbcsIn(List<Integer> values) {
            addCriterion("sbcs in", values, "sbcs");
            return (Criteria) this;
        }

        public Criteria andSbcsNotIn(List<Integer> values) {
            addCriterion("sbcs not in", values, "sbcs");
            return (Criteria) this;
        }

        public Criteria andSbcsBetween(Integer value1, Integer value2) {
            addCriterion("sbcs between", value1, value2, "sbcs");
            return (Criteria) this;
        }

        public Criteria andSbcsNotBetween(Integer value1, Integer value2) {
            addCriterion("sbcs not between", value1, value2, "sbcs");
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