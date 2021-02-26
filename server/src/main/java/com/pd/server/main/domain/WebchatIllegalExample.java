package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebchatIllegalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebchatIllegalExample() {
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
        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion(" DATE_FORMAT(create_time,'%Y-%m-%d') >=", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d')  <=", value, "createTime");
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

        public Criteria andWfxwmsIsNull() {
            addCriterion("wfxwms is null");
            return (Criteria) this;
        }

        public Criteria andWfxwmsIsNotNull() {
            addCriterion("wfxwms is not null");
            return (Criteria) this;
        }

        public Criteria andWfxwmsEqualTo(String value) {
            addCriterion("wfxwms =", value, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsNotEqualTo(String value) {
            addCriterion("wfxwms <>", value, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsGreaterThan(String value) {
            addCriterion("wfxwms >", value, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsGreaterThanOrEqualTo(String value) {
            addCriterion("wfxwms >=", value, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsLessThan(String value) {
            addCriterion("wfxwms <", value, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsLessThanOrEqualTo(String value) {
            addCriterion("wfxwms <=", value, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsLike(String value) {
            addCriterion("wfxwms like", value, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsNotLike(String value) {
            addCriterion("wfxwms not like", value, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsIn(List<String> values) {
            addCriterion("wfxwms in", values, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsNotIn(List<String> values) {
            addCriterion("wfxwms not in", values, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsBetween(String value1, String value2) {
            addCriterion("wfxwms between", value1, value2, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWfxwmsNotBetween(String value1, String value2) {
            addCriterion("wfxwms not between", value1, value2, "wfxwms");
            return (Criteria) this;
        }

        public Criteria andWflbIsNull() {
            addCriterion("wflb is null");
            return (Criteria) this;
        }

        public Criteria andWflbIsNotNull() {
            addCriterion("wflb is not null");
            return (Criteria) this;
        }

        public Criteria andWflbEqualTo(String value) {
            addCriterion("wflb =", value, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbNotEqualTo(String value) {
            addCriterion("wflb <>", value, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbGreaterThan(String value) {
            addCriterion("wflb >", value, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbGreaterThanOrEqualTo(String value) {
            addCriterion("wflb >=", value, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbLessThan(String value) {
            addCriterion("wflb <", value, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbLessThanOrEqualTo(String value) {
            addCriterion("wflb <=", value, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbLike(String value) {
            addCriterion("wflb like", value, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbNotLike(String value) {
            addCriterion("wflb not like", value, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbIn(List<String> values) {
            addCriterion("wflb in", values, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbNotIn(List<String> values) {
            addCriterion("wflb not in", values, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbBetween(String value1, String value2) {
            addCriterion("wflb between", value1, value2, "wflb");
            return (Criteria) this;
        }

        public Criteria andWflbNotBetween(String value1, String value2) {
            addCriterion("wflb not between", value1, value2, "wflb");
            return (Criteria) this;
        }

        public Criteria andWfxwIsNull() {
            addCriterion("wfxw is null");
            return (Criteria) this;
        }

        public Criteria andWfxwIsNotNull() {
            addCriterion("wfxw is not null");
            return (Criteria) this;
        }

        public Criteria andWfxwEqualTo(String value) {
            addCriterion("wfxw =", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwNotEqualTo(String value) {
            addCriterion("wfxw <>", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwGreaterThan(String value) {
            addCriterion("wfxw >", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwGreaterThanOrEqualTo(String value) {
            addCriterion("wfxw >=", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwLessThan(String value) {
            addCriterion("wfxw <", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwLessThanOrEqualTo(String value) {
            addCriterion("wfxw <=", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwLike(String value) {
            addCriterion("wfxw like", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwNotLike(String value) {
            addCriterion("wfxw not like", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwIn(List<String> values) {
            addCriterion("wfxw in", values, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwNotIn(List<String> values) {
            addCriterion("wfxw not in", values, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwBetween(String value1, String value2) {
            addCriterion("wfxw between", value1, value2, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwNotBetween(String value1, String value2) {
            addCriterion("wfxw not between", value1, value2, "wfxw");
            return (Criteria) this;
        }

        public Criteria andCzzpIsNull() {
            addCriterion("czzp is null");
            return (Criteria) this;
        }

        public Criteria andCzzpIsNotNull() {
            addCriterion("czzp is not null");
            return (Criteria) this;
        }

        public Criteria andCzzpEqualTo(String value) {
            addCriterion("czzp =", value, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpNotEqualTo(String value) {
            addCriterion("czzp <>", value, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpGreaterThan(String value) {
            addCriterion("czzp >", value, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpGreaterThanOrEqualTo(String value) {
            addCriterion("czzp >=", value, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpLessThan(String value) {
            addCriterion("czzp <", value, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpLessThanOrEqualTo(String value) {
            addCriterion("czzp <=", value, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpLike(String value) {
            addCriterion("czzp like", value, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpNotLike(String value) {
            addCriterion("czzp not like", value, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpIn(List<String> values) {
            addCriterion("czzp in", values, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpNotIn(List<String> values) {
            addCriterion("czzp not in", values, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpBetween(String value1, String value2) {
            addCriterion("czzp between", value1, value2, "czzp");
            return (Criteria) this;
        }

        public Criteria andCzzpNotBetween(String value1, String value2) {
            addCriterion("czzp not between", value1, value2, "czzp");
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
        public Criteria andWfsjGreaterThanOrEqualTo(String value) {
            addCriterion(" DATE_FORMAT(wfsj,'%Y-%m-%d') >=", value, "wfsj");
            return (Criteria) this;
        }
        public Criteria andWfsjLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(wfsj,'%Y-%m-%d')  <=", value, "wfsj");
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

        public Criteria andWfldIsNull() {
            addCriterion("wfld is null");
            return (Criteria) this;
        }

        public Criteria andWfldIsNotNull() {
            addCriterion("wfld is not null");
            return (Criteria) this;
        }

        public Criteria andWfldEqualTo(String value) {
            addCriterion("wfld =", value, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldNotEqualTo(String value) {
            addCriterion("wfld <>", value, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldGreaterThan(String value) {
            addCriterion("wfld >", value, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldGreaterThanOrEqualTo(String value) {
            addCriterion("wfld >=", value, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldLessThan(String value) {
            addCriterion("wfld <", value, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldLessThanOrEqualTo(String value) {
            addCriterion("wfld <=", value, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldLike(String value) {
            addCriterion("wfld like", value, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldNotLike(String value) {
            addCriterion("wfld not like", value, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldIn(List<String> values) {
            addCriterion("wfld in", values, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldNotIn(List<String> values) {
            addCriterion("wfld not in", values, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldBetween(String value1, String value2) {
            addCriterion("wfld between", value1, value2, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfldNotBetween(String value1, String value2) {
            addCriterion("wfld not between", value1, value2, "wfld");
            return (Criteria) this;
        }

        public Criteria andWfgpsIsNull() {
            addCriterion("wfgps is null");
            return (Criteria) this;
        }

        public Criteria andWfgpsIsNotNull() {
            addCriterion("wfgps is not null");
            return (Criteria) this;
        }

        public Criteria andWfgpsEqualTo(String value) {
            addCriterion("wfgps =", value, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsNotEqualTo(String value) {
            addCriterion("wfgps <>", value, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsGreaterThan(String value) {
            addCriterion("wfgps >", value, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsGreaterThanOrEqualTo(String value) {
            addCriterion("wfgps >=", value, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsLessThan(String value) {
            addCriterion("wfgps <", value, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsLessThanOrEqualTo(String value) {
            addCriterion("wfgps <=", value, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsLike(String value) {
            addCriterion("wfgps like", value, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsNotLike(String value) {
            addCriterion("wfgps not like", value, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsIn(List<String> values) {
            addCriterion("wfgps in", values, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsNotIn(List<String> values) {
            addCriterion("wfgps not in", values, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsBetween(String value1, String value2) {
            addCriterion("wfgps between", value1, value2, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfgpsNotBetween(String value1, String value2) {
            addCriterion("wfgps not between", value1, value2, "wfgps");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzIsNull() {
            addCriterion("wfldxxdz is null");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzIsNotNull() {
            addCriterion("wfldxxdz is not null");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzEqualTo(String value) {
            addCriterion("wfldxxdz =", value, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzNotEqualTo(String value) {
            addCriterion("wfldxxdz <>", value, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzGreaterThan(String value) {
            addCriterion("wfldxxdz >", value, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzGreaterThanOrEqualTo(String value) {
            addCriterion("wfldxxdz >=", value, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzLessThan(String value) {
            addCriterion("wfldxxdz <", value, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzLessThanOrEqualTo(String value) {
            addCriterion("wfldxxdz <=", value, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzLike(String value) {
            addCriterion("wfldxxdz like", value, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzNotLike(String value) {
            addCriterion("wfldxxdz not like", value, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzIn(List<String> values) {
            addCriterion("wfldxxdz in", values, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzNotIn(List<String> values) {
            addCriterion("wfldxxdz not in", values, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzBetween(String value1, String value2) {
            addCriterion("wfldxxdz between", value1, value2, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andWfldxxdzNotBetween(String value1, String value2) {
            addCriterion("wfldxxdz not between", value1, value2, "wfldxxdz");
            return (Criteria) this;
        }

        public Criteria andCplxIsNull() {
            addCriterion("cplx is null");
            return (Criteria) this;
        }

        public Criteria andCplxIsNotNull() {
            addCriterion("cplx is not null");
            return (Criteria) this;
        }

        public Criteria andCplxEqualTo(String value) {
            addCriterion("cplx =", value, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxNotEqualTo(String value) {
            addCriterion("cplx <>", value, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxGreaterThan(String value) {
            addCriterion("cplx >", value, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxGreaterThanOrEqualTo(String value) {
            addCriterion("cplx >=", value, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxLessThan(String value) {
            addCriterion("cplx <", value, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxLessThanOrEqualTo(String value) {
            addCriterion("cplx <=", value, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxLike(String value) {
            addCriterion("cplx like", value, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxNotLike(String value) {
            addCriterion("cplx not like", value, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxIn(List<String> values) {
            addCriterion("cplx in", values, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxNotIn(List<String> values) {
            addCriterion("cplx not in", values, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxBetween(String value1, String value2) {
            addCriterion("cplx between", value1, value2, "cplx");
            return (Criteria) this;
        }

        public Criteria andCplxNotBetween(String value1, String value2) {
            addCriterion("cplx not between", value1, value2, "cplx");
            return (Criteria) this;
        }

        public Criteria andCphmIsNull() {
            addCriterion("cphm is null");
            return (Criteria) this;
        }

        public Criteria andCphmIsNotNull() {
            addCriterion("cphm is not null");
            return (Criteria) this;
        }

        public Criteria andCphmEqualTo(String value) {
            addCriterion("cphm =", value, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmNotEqualTo(String value) {
            addCriterion("cphm <>", value, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmGreaterThan(String value) {
            addCriterion("cphm >", value, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmGreaterThanOrEqualTo(String value) {
            addCriterion("cphm >=", value, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmLessThan(String value) {
            addCriterion("cphm <", value, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmLessThanOrEqualTo(String value) {
            addCriterion("cphm <=", value, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmLike(String value) {
            addCriterion("cphm like", value, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmNotLike(String value) {
            addCriterion("cphm not like", value, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmIn(List<String> values) {
            addCriterion("cphm in", values, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmNotIn(List<String> values) {
            addCriterion("cphm not in", values, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmBetween(String value1, String value2) {
            addCriterion("cphm between", value1, value2, "cphm");
            return (Criteria) this;
        }

        public Criteria andCphmNotBetween(String value1, String value2) {
            addCriterion("cphm not between", value1, value2, "cphm");
            return (Criteria) this;
        }

        public Criteria andJbrIsNull() {
            addCriterion("jbr is null");
            return (Criteria) this;
        }

        public Criteria andJbrIsNotNull() {
            addCriterion("jbr is not null");
            return (Criteria) this;
        }

        public Criteria andJbrEqualTo(String value) {
            addCriterion("jbr =", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotEqualTo(String value) {
            addCriterion("jbr <>", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrGreaterThan(String value) {
            addCriterion("jbr >", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrGreaterThanOrEqualTo(String value) {
            addCriterion("jbr >=", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrLessThan(String value) {
            addCriterion("jbr <", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrLessThanOrEqualTo(String value) {
            addCriterion("jbr <=", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrLike(String value) {
            addCriterion("jbr like", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotLike(String value) {
            addCriterion("jbr not like", value, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrIn(List<String> values) {
            addCriterion("jbr in", values, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotIn(List<String> values) {
            addCriterion("jbr not in", values, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrBetween(String value1, String value2) {
            addCriterion("jbr between", value1, value2, "jbr");
            return (Criteria) this;
        }

        public Criteria andJbrNotBetween(String value1, String value2) {
            addCriterion("jbr not between", value1, value2, "jbr");
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

        public Criteria andShrIsNull() {
            addCriterion("shr is null");
            return (Criteria) this;
        }

        public Criteria andShrIsNotNull() {
            addCriterion("shr is not null");
            return (Criteria) this;
        }

        public Criteria andShrEqualTo(String value) {
            addCriterion("shr =", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrNotEqualTo(String value) {
            addCriterion("shr <>", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrGreaterThan(String value) {
            addCriterion("shr >", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrGreaterThanOrEqualTo(String value) {
            addCriterion("shr >=", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrLessThan(String value) {
            addCriterion("shr <", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrLessThanOrEqualTo(String value) {
            addCriterion("shr <=", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrLike(String value) {
            addCriterion("shr like", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrNotLike(String value) {
            addCriterion("shr not like", value, "shr");
            return (Criteria) this;
        }

        public Criteria andShrIn(List<String> values) {
            addCriterion("shr in", values, "shr");
            return (Criteria) this;
        }

        public Criteria andShrNotIn(List<String> values) {
            addCriterion("shr not in", values, "shr");
            return (Criteria) this;
        }

        public Criteria andShrBetween(String value1, String value2) {
            addCriterion("shr between", value1, value2, "shr");
            return (Criteria) this;
        }

        public Criteria andShrNotBetween(String value1, String value2) {
            addCriterion("shr not between", value1, value2, "shr");
            return (Criteria) this;
        }

        public Criteria andShyyIsNull() {
            addCriterion("shyy is null");
            return (Criteria) this;
        }

        public Criteria andShyyIsNotNull() {
            addCriterion("shyy is not null");
            return (Criteria) this;
        }

        public Criteria andShyyEqualTo(String value) {
            addCriterion("shyy =", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyNotEqualTo(String value) {
            addCriterion("shyy <>", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyGreaterThan(String value) {
            addCriterion("shyy >", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyGreaterThanOrEqualTo(String value) {
            addCriterion("shyy >=", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyLessThan(String value) {
            addCriterion("shyy <", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyLessThanOrEqualTo(String value) {
            addCriterion("shyy <=", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyLike(String value) {
            addCriterion("shyy like", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyNotLike(String value) {
            addCriterion("shyy not like", value, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyIn(List<String> values) {
            addCriterion("shyy in", values, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyNotIn(List<String> values) {
            addCriterion("shyy not in", values, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyBetween(String value1, String value2) {
            addCriterion("shyy between", value1, value2, "shyy");
            return (Criteria) this;
        }

        public Criteria andShyyNotBetween(String value1, String value2) {
            addCriterion("shyy not between", value1, value2, "shyy");
            return (Criteria) this;
        }

        public Criteria andShsjIsNull() {
            addCriterion("shsj is null");
            return (Criteria) this;
        }

        public Criteria andShsjIsNotNull() {
            addCriterion("shsj is not null");
            return (Criteria) this;
        }

        public Criteria andShsjEqualTo(Date value) {
            addCriterion("shsj =", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjNotEqualTo(Date value) {
            addCriterion("shsj <>", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjGreaterThan(Date value) {
            addCriterion("shsj >", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjGreaterThanOrEqualTo(Date value) {
            addCriterion("shsj >=", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjLessThan(Date value) {
            addCriterion("shsj <", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjLessThanOrEqualTo(Date value) {
            addCriterion("shsj <=", value, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjIn(List<Date> values) {
            addCriterion("shsj in", values, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjNotIn(List<Date> values) {
            addCriterion("shsj not in", values, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjBetween(Date value1, Date value2) {
            addCriterion("shsj between", value1, value2, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsjNotBetween(Date value1, Date value2) {
            addCriterion("shsj not between", value1, value2, "shsj");
            return (Criteria) this;
        }

        public Criteria andShsmIsNull() {
            addCriterion("shsm is null");
            return (Criteria) this;
        }

        public Criteria andShsmIsNotNull() {
            addCriterion("shsm is not null");
            return (Criteria) this;
        }

        public Criteria andShsmEqualTo(String value) {
            addCriterion("shsm =", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmNotEqualTo(String value) {
            addCriterion("shsm <>", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmGreaterThan(String value) {
            addCriterion("shsm >", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmGreaterThanOrEqualTo(String value) {
            addCriterion("shsm >=", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmLessThan(String value) {
            addCriterion("shsm <", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmLessThanOrEqualTo(String value) {
            addCriterion("shsm <=", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmLike(String value) {
            addCriterion("shsm like", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmNotLike(String value) {
            addCriterion("shsm not like", value, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmIn(List<String> values) {
            addCriterion("shsm in", values, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmNotIn(List<String> values) {
            addCriterion("shsm not in", values, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmBetween(String value1, String value2) {
            addCriterion("shsm between", value1, value2, "shsm");
            return (Criteria) this;
        }

        public Criteria andShsmNotBetween(String value1, String value2) {
            addCriterion("shsm not between", value1, value2, "shsm");
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

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
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

        public Criteria andJlffIsNull() {
            addCriterion("jlff is null");
            return (Criteria) this;
        }

        public Criteria andJlffIsNotNull() {
            addCriterion("jlff is not null");
            return (Criteria) this;
        }

        public Criteria andJlffEqualTo(String value) {
            addCriterion("jlff =", value, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffNotEqualTo(String value) {
            addCriterion("jlff <>", value, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffGreaterThan(String value) {
            addCriterion("jlff >", value, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffGreaterThanOrEqualTo(String value) {
            addCriterion("jlff >=", value, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffLessThan(String value) {
            addCriterion("jlff <", value, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffLessThanOrEqualTo(String value) {
            addCriterion("jlff <=", value, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffLike(String value) {
            addCriterion("jlff like", value, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffNotLike(String value) {
            addCriterion("jlff not like", value, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffIn(List<String> values) {
            addCriterion("jlff in", values, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffNotIn(List<String> values) {
            addCriterion("jlff not in", values, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffBetween(String value1, String value2) {
            addCriterion("jlff between", value1, value2, "jlff");
            return (Criteria) this;
        }

        public Criteria andJlffNotBetween(String value1, String value2) {
            addCriterion("jlff not between", value1, value2, "jlff");
            return (Criteria) this;
        }

        public Criteria andWfxwxxIsNull() {
            addCriterion("wfxwxx is null");
            return (Criteria) this;
        }

        public Criteria andWfxwxxIsNotNull() {
            addCriterion("wfxwxx is not null");
            return (Criteria) this;
        }

        public Criteria andWfxwxxEqualTo(String value) {
            addCriterion("wfxwxx =", value, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxNotEqualTo(String value) {
            addCriterion("wfxwxx <>", value, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxGreaterThan(String value) {
            addCriterion("wfxwxx >", value, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxGreaterThanOrEqualTo(String value) {
            addCriterion("wfxwxx >=", value, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxLessThan(String value) {
            addCriterion("wfxwxx <", value, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxLessThanOrEqualTo(String value) {
            addCriterion("wfxwxx <=", value, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxLike(String value) {
            addCriterion("wfxwxx like", value, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxNotLike(String value) {
            addCriterion("wfxwxx not like", value, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxIn(List<String> values) {
            addCriterion("wfxwxx in", values, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxNotIn(List<String> values) {
            addCriterion("wfxwxx not in", values, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxBetween(String value1, String value2) {
            addCriterion("wfxwxx between", value1, value2, "wfxwxx");
            return (Criteria) this;
        }

        public Criteria andWfxwxxNotBetween(String value1, String value2) {
            addCriterion("wfxwxx not between", value1, value2, "wfxwxx");
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