package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebchatReportIllcodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebchatReportIllcodeExample() {
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

        public Criteria andSfsxIsNull() {
            addCriterion("sfsx is null");
            return (Criteria) this;
        }

        public Criteria andSfsxIsNotNull() {
            addCriterion("sfsx is not null");
            return (Criteria) this;
        }

        public Criteria andSfsxEqualTo(String value) {
            addCriterion("sfsx =", value, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxNotEqualTo(String value) {
            addCriterion("sfsx <>", value, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxGreaterThan(String value) {
            addCriterion("sfsx >", value, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxGreaterThanOrEqualTo(String value) {
            addCriterion("sfsx >=", value, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxLessThan(String value) {
            addCriterion("sfsx <", value, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxLessThanOrEqualTo(String value) {
            addCriterion("sfsx <=", value, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxLike(String value) {
            addCriterion("sfsx like", value, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxNotLike(String value) {
            addCriterion("sfsx not like", value, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxIn(List<String> values) {
            addCriterion("sfsx in", values, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxNotIn(List<String> values) {
            addCriterion("sfsx not in", values, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxBetween(String value1, String value2) {
            addCriterion("sfsx between", value1, value2, "sfsx");
            return (Criteria) this;
        }

        public Criteria andSfsxNotBetween(String value1, String value2) {
            addCriterion("sfsx not between", value1, value2, "sfsx");
            return (Criteria) this;
        }

        public Criteria andJljrIsNull() {
            addCriterion("jljr is null");
            return (Criteria) this;
        }

        public Criteria andJljrIsNotNull() {
            addCriterion("jljr is not null");
            return (Criteria) this;
        }

        public Criteria andJljrEqualTo(Integer value) {
            addCriterion("jljr =", value, "jljr");
            return (Criteria) this;
        }

        public Criteria andJljrNotEqualTo(Integer value) {
            addCriterion("jljr <>", value, "jljr");
            return (Criteria) this;
        }

        public Criteria andJljrGreaterThan(Integer value) {
            addCriterion("jljr >", value, "jljr");
            return (Criteria) this;
        }

        public Criteria andJljrGreaterThanOrEqualTo(Integer value) {
            addCriterion("jljr >=", value, "jljr");
            return (Criteria) this;
        }

        public Criteria andJljrLessThan(Integer value) {
            addCriterion("jljr <", value, "jljr");
            return (Criteria) this;
        }

        public Criteria andJljrLessThanOrEqualTo(Integer value) {
            addCriterion("jljr <=", value, "jljr");
            return (Criteria) this;
        }

        public Criteria andJljrIn(List<Integer> values) {
            addCriterion("jljr in", values, "jljr");
            return (Criteria) this;
        }

        public Criteria andJljrNotIn(List<Integer> values) {
            addCriterion("jljr not in", values, "jljr");
            return (Criteria) this;
        }

        public Criteria andJljrBetween(Integer value1, Integer value2) {
            addCriterion("jljr between", value1, value2, "jljr");
            return (Criteria) this;
        }

        public Criteria andJljrNotBetween(Integer value1, Integer value2) {
            addCriterion("jljr not between", value1, value2, "jljr");
            return (Criteria) this;
        }

        public Criteria andFffsIsNull() {
            addCriterion("fffs is null");
            return (Criteria) this;
        }

        public Criteria andFffsIsNotNull() {
            addCriterion("fffs is not null");
            return (Criteria) this;
        }

        public Criteria andFffsEqualTo(String value) {
            addCriterion("fffs =", value, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsNotEqualTo(String value) {
            addCriterion("fffs <>", value, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsGreaterThan(String value) {
            addCriterion("fffs >", value, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsGreaterThanOrEqualTo(String value) {
            addCriterion("fffs >=", value, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsLessThan(String value) {
            addCriterion("fffs <", value, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsLessThanOrEqualTo(String value) {
            addCriterion("fffs <=", value, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsLike(String value) {
            addCriterion("fffs like", value, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsNotLike(String value) {
            addCriterion("fffs not like", value, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsIn(List<String> values) {
            addCriterion("fffs in", values, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsNotIn(List<String> values) {
            addCriterion("fffs not in", values, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsBetween(String value1, String value2) {
            addCriterion("fffs between", value1, value2, "fffs");
            return (Criteria) this;
        }

        public Criteria andFffsNotBetween(String value1, String value2) {
            addCriterion("fffs not between", value1, value2, "fffs");
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