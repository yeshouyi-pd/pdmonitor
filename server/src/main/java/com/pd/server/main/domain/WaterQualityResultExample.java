package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterQualityResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WaterQualityResultExample() {
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

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andDatacenterIsNull() {
            addCriterion("datacenter is null");
            return (Criteria) this;
        }

        public Criteria andDatacenterIsNotNull() {
            addCriterion("datacenter is not null");
            return (Criteria) this;
        }

        public Criteria andDatacenterEqualTo(String value) {
            addCriterion("datacenter =", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterNotEqualTo(String value) {
            addCriterion("datacenter <>", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterGreaterThan(String value) {
            addCriterion("datacenter >", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterGreaterThanOrEqualTo(String value) {
            addCriterion("datacenter >=", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterLessThan(String value) {
            addCriterion("datacenter <", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterLessThanOrEqualTo(String value) {
            addCriterion("datacenter <=", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterLike(String value) {
            addCriterion("datacenter like", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterNotLike(String value) {
            addCriterion("datacenter not like", value, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterIn(List<String> values) {
            addCriterion("datacenter in", values, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterNotIn(List<String> values) {
            addCriterion("datacenter not in", values, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterBetween(String value1, String value2) {
            addCriterion("datacenter between", value1, value2, "datacenter");
            return (Criteria) this;
        }

        public Criteria andDatacenterNotBetween(String value1, String value2) {
            addCriterion("datacenter not between", value1, value2, "datacenter");
            return (Criteria) this;
        }

        public Criteria andJcxmIsNull() {
            addCriterion("jcxm is null");
            return (Criteria) this;
        }

        public Criteria andJcxmIsNotNull() {
            addCriterion("jcxm is not null");
            return (Criteria) this;
        }

        public Criteria andJcxmEqualTo(String value) {
            addCriterion("jcxm =", value, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmNotEqualTo(String value) {
            addCriterion("jcxm <>", value, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmGreaterThan(String value) {
            addCriterion("jcxm >", value, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmGreaterThanOrEqualTo(String value) {
            addCriterion("jcxm >=", value, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmLessThan(String value) {
            addCriterion("jcxm <", value, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmLessThanOrEqualTo(String value) {
            addCriterion("jcxm <=", value, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmLike(String value) {
            addCriterion("jcxm like", value, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmNotLike(String value) {
            addCriterion("jcxm not like", value, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmIn(List<String> values) {
            addCriterion("jcxm in", values, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmNotIn(List<String> values) {
            addCriterion("jcxm not in", values, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmBetween(String value1, String value2) {
            addCriterion("jcxm between", value1, value2, "jcxm");
            return (Criteria) this;
        }

        public Criteria andJcxmNotBetween(String value1, String value2) {
            addCriterion("jcxm not between", value1, value2, "jcxm");
            return (Criteria) this;
        }

        public Criteria andInstructIsNull() {
            addCriterion("instruct is null");
            return (Criteria) this;
        }

        public Criteria andInstructIsNotNull() {
            addCriterion("instruct is not null");
            return (Criteria) this;
        }

        public Criteria andInstructEqualTo(String value) {
            addCriterion("instruct =", value, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructNotEqualTo(String value) {
            addCriterion("instruct <>", value, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructGreaterThan(String value) {
            addCriterion("instruct >", value, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructGreaterThanOrEqualTo(String value) {
            addCriterion("instruct >=", value, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructLessThan(String value) {
            addCriterion("instruct <", value, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructLessThanOrEqualTo(String value) {
            addCriterion("instruct <=", value, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructLike(String value) {
            addCriterion("instruct like", value, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructNotLike(String value) {
            addCriterion("instruct not like", value, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructIn(List<String> values) {
            addCriterion("instruct in", values, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructNotIn(List<String> values) {
            addCriterion("instruct not in", values, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructBetween(String value1, String value2) {
            addCriterion("instruct between", value1, value2, "instruct");
            return (Criteria) this;
        }

        public Criteria andInstructNotBetween(String value1, String value2) {
            addCriterion("instruct not between", value1, value2, "instruct");
            return (Criteria) this;
        }

        public Criteria andDataResultIsNull() {
            addCriterion("data_result is null");
            return (Criteria) this;
        }

        public Criteria andDataResultIsNotNull() {
            addCriterion("data_result is not null");
            return (Criteria) this;
        }

        public Criteria andDataResultEqualTo(String value) {
            addCriterion("data_result =", value, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultNotEqualTo(String value) {
            addCriterion("data_result <>", value, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultGreaterThan(String value) {
            addCriterion("data_result >", value, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultGreaterThanOrEqualTo(String value) {
            addCriterion("data_result >=", value, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultLessThan(String value) {
            addCriterion("data_result <", value, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultLessThanOrEqualTo(String value) {
            addCriterion("data_result <=", value, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultLike(String value) {
            addCriterion("data_result like", value, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultNotLike(String value) {
            addCriterion("data_result not like", value, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultIn(List<String> values) {
            addCriterion("data_result in", values, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultNotIn(List<String> values) {
            addCriterion("data_result not in", values, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultBetween(String value1, String value2) {
            addCriterion("data_result between", value1, value2, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataResultNotBetween(String value1, String value2) {
            addCriterion("data_result not between", value1, value2, "dataResult");
            return (Criteria) this;
        }

        public Criteria andDataOriginalIsNull() {
            addCriterion("data_original is null");
            return (Criteria) this;
        }

        public Criteria andDataOriginalIsNotNull() {
            addCriterion("data_original is not null");
            return (Criteria) this;
        }

        public Criteria andDataOriginalEqualTo(String value) {
            addCriterion("data_original =", value, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalNotEqualTo(String value) {
            addCriterion("data_original <>", value, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalGreaterThan(String value) {
            addCriterion("data_original >", value, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalGreaterThanOrEqualTo(String value) {
            addCriterion("data_original >=", value, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalLessThan(String value) {
            addCriterion("data_original <", value, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalLessThanOrEqualTo(String value) {
            addCriterion("data_original <=", value, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalLike(String value) {
            addCriterion("data_original like", value, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalNotLike(String value) {
            addCriterion("data_original not like", value, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalIn(List<String> values) {
            addCriterion("data_original in", values, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalNotIn(List<String> values) {
            addCriterion("data_original not in", values, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalBetween(String value1, String value2) {
            addCriterion("data_original between", value1, value2, "dataOriginal");
            return (Criteria) this;
        }

        public Criteria andDataOriginalNotBetween(String value1, String value2) {
            addCriterion("data_original not between", value1, value2, "dataOriginal");
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

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d') =", value, "createTime");
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
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d') >=", value, "createTime");
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

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(create_time,'%Y-%m-%d') <=", value, "createTime");
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