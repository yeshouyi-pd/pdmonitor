package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipmentFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentFileExample() {
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

        public Criteria andSbbhIsNull() {
            addCriterion("sbbh is null");
            return (Criteria) this;
        }

        public Criteria andSbbhIsNotNull() {
            addCriterion("sbbh is not null");
            return (Criteria) this;
        }

        public Criteria andSbbhEqualTo(String value) {
            addCriterion("sbbh =", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotEqualTo(String value) {
            addCriterion("sbbh <>", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhGreaterThan(String value) {
            addCriterion("sbbh >", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhGreaterThanOrEqualTo(String value) {
            addCriterion("sbbh >=", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhLessThan(String value) {
            addCriterion("sbbh <", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhLessThanOrEqualTo(String value) {
            addCriterion("sbbh <=", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhLike(String value) {
            addCriterion("sbbh like", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotLike(String value) {
            addCriterion("sbbh not like", value, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhIn(List<String> values) {
            addCriterion("sbbh in", values, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotIn(List<String> values) {
            addCriterion("sbbh not in", values, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhBetween(String value1, String value2) {
            addCriterion("sbbh between", value1, value2, "sbbh");
            return (Criteria) this;
        }

        public Criteria andSbbhNotBetween(String value1, String value2) {
            addCriterion("sbbh not between", value1, value2, "sbbh");
            return (Criteria) this;
        }

        public Criteria andTpljIsNull() {
            addCriterion("tplj is null");
            return (Criteria) this;
        }

        public Criteria andTpljIsNotNull() {
            addCriterion("tplj is not null");
            return (Criteria) this;
        }

        public Criteria andTpljEqualTo(String value) {
            addCriterion("tplj =", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljNotEqualTo(String value) {
            addCriterion("tplj <>", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljGreaterThan(String value) {
            addCriterion("tplj >", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljGreaterThanOrEqualTo(String value) {
            addCriterion("tplj >=", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljLessThan(String value) {
            addCriterion("tplj <", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljLessThanOrEqualTo(String value) {
            addCriterion("tplj <=", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljLike(String value) {
            addCriterion("tplj like", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljNotLike(String value) {
            addCriterion("tplj not like", value, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljIn(List<String> values) {
            addCriterion("tplj in", values, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljNotIn(List<String> values) {
            addCriterion("tplj not in", values, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljBetween(String value1, String value2) {
            addCriterion("tplj between", value1, value2, "tplj");
            return (Criteria) this;
        }

        public Criteria andTpljNotBetween(String value1, String value2) {
            addCriterion("tplj not between", value1, value2, "tplj");
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

        public Criteria andCjsjGreaterThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(cjsj,'%Y-%m-%d %H:%i') >=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThanOrEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(cjsj,'"+type+"') >=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThan(Date value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThan(String value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(Date value) {
            addCriterion("cjsj <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(String value) {
            addCriterion("DATE_FORMAT(cjsj,'%Y-%m-%d %H:%i') <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(String value, String type) {
            addCriterion("DATE_FORMAT(cjsj,'"+type+"') <=", value, "cjsj");
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

        public Criteria andNfIsNull() {
            addCriterion("nf is null");
            return (Criteria) this;
        }

        public Criteria andNfIsNotNull() {
            addCriterion("nf is not null");
            return (Criteria) this;
        }

        public Criteria andNfEqualTo(String value) {
            addCriterion("nf =", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfNotEqualTo(String value) {
            addCriterion("nf <>", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfGreaterThan(String value) {
            addCriterion("nf >", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfGreaterThanOrEqualTo(String value) {
            addCriterion("nf >=", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfLessThan(String value) {
            addCriterion("nf <", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfLessThanOrEqualTo(String value) {
            addCriterion("nf <=", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfLike(String value) {
            addCriterion("nf like", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfNotLike(String value) {
            addCriterion("nf not like", value, "nf");
            return (Criteria) this;
        }

        public Criteria andNfIn(List<String> values) {
            addCriterion("nf in", values, "nf");
            return (Criteria) this;
        }

        public Criteria andNfNotIn(List<String> values) {
            addCriterion("nf not in", values, "nf");
            return (Criteria) this;
        }

        public Criteria andNfBetween(String value1, String value2) {
            addCriterion("nf between", value1, value2, "nf");
            return (Criteria) this;
        }

        public Criteria andNfNotBetween(String value1, String value2) {
            addCriterion("nf not between", value1, value2, "nf");
            return (Criteria) this;
        }

        public Criteria andYfIsNull() {
            addCriterion("yf is null");
            return (Criteria) this;
        }

        public Criteria andYfIsNotNull() {
            addCriterion("yf is not null");
            return (Criteria) this;
        }

        public Criteria andYfEqualTo(String value) {
            addCriterion("yf =", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfNotEqualTo(String value) {
            addCriterion("yf <>", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfGreaterThan(String value) {
            addCriterion("yf >", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfGreaterThanOrEqualTo(String value) {
            addCriterion("yf >=", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfLessThan(String value) {
            addCriterion("yf <", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfLessThanOrEqualTo(String value) {
            addCriterion("yf <=", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfLike(String value) {
            addCriterion("yf like", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfNotLike(String value) {
            addCriterion("yf not like", value, "yf");
            return (Criteria) this;
        }

        public Criteria andYfIn(List<String> values) {
            addCriterion("yf in", values, "yf");
            return (Criteria) this;
        }

        public Criteria andYfNotIn(List<String> values) {
            addCriterion("yf not in", values, "yf");
            return (Criteria) this;
        }

        public Criteria andYfBetween(String value1, String value2) {
            addCriterion("yf between", value1, value2, "yf");
            return (Criteria) this;
        }

        public Criteria andYfNotBetween(String value1, String value2) {
            addCriterion("yf not between", value1, value2, "yf");
            return (Criteria) this;
        }

        public Criteria andRqIsNull() {
            addCriterion("rq is null");
            return (Criteria) this;
        }

        public Criteria andRqIsNotNull() {
            addCriterion("rq is not null");
            return (Criteria) this;
        }

        public Criteria andRqEqualTo(String value) {
            addCriterion("rq =", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotEqualTo(String value) {
            addCriterion("rq <>", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqGreaterThan(String value) {
            addCriterion("rq >", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqGreaterThanOrEqualTo(String value) {
            addCriterion("rq >=", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqLessThan(String value) {
            addCriterion("rq <", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqLessThanOrEqualTo(String value) {
            addCriterion("rq <=", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqLike(String value) {
            addCriterion("rq like", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotLike(String value) {
            addCriterion("rq not like", value, "rq");
            return (Criteria) this;
        }

        public Criteria andRqIn(List<String> values) {
            addCriterion("rq in", values, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotIn(List<String> values) {
            addCriterion("rq not in", values, "rq");
            return (Criteria) this;
        }

        public Criteria andRqBetween(String value1, String value2) {
            addCriterion("rq between", value1, value2, "rq");
            return (Criteria) this;
        }

        public Criteria andRqNotBetween(String value1, String value2) {
            addCriterion("rq not between", value1, value2, "rq");
            return (Criteria) this;
        }

        public Criteria andXsIsNull() {
            addCriterion("xs is null");
            return (Criteria) this;
        }

        public Criteria andXsIsNotNull() {
            addCriterion("xs is not null");
            return (Criteria) this;
        }

        public Criteria andXsEqualTo(String value) {
            addCriterion("xs =", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotEqualTo(String value) {
            addCriterion("xs <>", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsGreaterThan(String value) {
            addCriterion("xs >", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsGreaterThanOrEqualTo(String value) {
            addCriterion("xs >=", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsLessThan(String value) {
            addCriterion("xs <", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsLessThanOrEqualTo(String value) {
            addCriterion("xs <=", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsLike(String value) {
            addCriterion("xs like", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotLike(String value) {
            addCriterion("xs not like", value, "xs");
            return (Criteria) this;
        }

        public Criteria andXsIn(List<String> values) {
            addCriterion("xs in", values, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotIn(List<String> values) {
            addCriterion("xs not in", values, "xs");
            return (Criteria) this;
        }

        public Criteria andXsBetween(String value1, String value2) {
            addCriterion("xs between", value1, value2, "xs");
            return (Criteria) this;
        }

        public Criteria andXsNotBetween(String value1, String value2) {
            addCriterion("xs not between", value1, value2, "xs");
            return (Criteria) this;
        }

        public Criteria andFzIsNull() {
            addCriterion("fz is null");
            return (Criteria) this;
        }

        public Criteria andFzIsNotNull() {
            addCriterion("fz is not null");
            return (Criteria) this;
        }

        public Criteria andFzEqualTo(String value) {
            addCriterion("fz =", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotEqualTo(String value) {
            addCriterion("fz <>", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzGreaterThan(String value) {
            addCriterion("fz >", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzGreaterThanOrEqualTo(String value) {
            addCriterion("fz >=", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzLessThan(String value) {
            addCriterion("fz <", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzLessThanOrEqualTo(String value) {
            addCriterion("fz <=", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzLike(String value) {
            addCriterion("fz like", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotLike(String value) {
            addCriterion("fz not like", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzIn(List<String> values) {
            addCriterion("fz in", values, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotIn(List<String> values) {
            addCriterion("fz not in", values, "fz");
            return (Criteria) this;
        }

        public Criteria andFzBetween(String value1, String value2) {
            addCriterion("fz between", value1, value2, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotBetween(String value1, String value2) {
            addCriterion("fz not between", value1, value2, "fz");
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

        public Criteria andWjlxIsNull() {
            addCriterion("wjlx is null");
            return (Criteria) this;
        }

        public Criteria andWjlxIsNotNull() {
            addCriterion("wjlx is not null");
            return (Criteria) this;
        }

        public Criteria andWjlxEqualTo(String value) {
            addCriterion("wjlx =", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotEqualTo(String value) {
            addCriterion("wjlx <>", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxGreaterThan(String value) {
            addCriterion("wjlx >", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxGreaterThanOrEqualTo(String value) {
            addCriterion("wjlx >=", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxLessThan(String value) {
            addCriterion("wjlx <", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxLessThanOrEqualTo(String value) {
            addCriterion("wjlx <=", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxLike(String value) {
            addCriterion("wjlx like", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotLike(String value) {
            addCriterion("wjlx not like", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxIn(List<String> values) {
            addCriterion("wjlx in", values, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotIn(List<String> values) {
            addCriterion("wjlx not in", values, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxBetween(String value1, String value2) {
            addCriterion("wjlx between", value1, value2, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotBetween(String value1, String value2) {
            addCriterion("wjlx not between", value1, value2, "wjlx");
            return (Criteria) this;
        }

        public Criteria andJczlIsNull() {
            addCriterion("jczl is null");
            return (Criteria) this;
        }

        public Criteria andJczlIsNotNull() {
            addCriterion("jczl is not null");
            return (Criteria) this;
        }

        public Criteria andJczlEqualTo(String value) {
            addCriterion("jczl =", value, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlNotEqualTo(String value) {
            addCriterion("jczl <>", value, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlGreaterThan(String value) {
            addCriterion("jczl >", value, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlGreaterThanOrEqualTo(String value) {
            addCriterion("jczl >=", value, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlLessThan(String value) {
            addCriterion("jczl <", value, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlLessThanOrEqualTo(String value) {
            addCriterion("jczl <=", value, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlLike(String value) {
            addCriterion("jczl like", value, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlNotLike(String value) {
            addCriterion("jczl not like", value, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlIn(List<String> values) {
            addCriterion("jczl in", values, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlNotIn(List<String> values) {
            addCriterion("jczl not in", values, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlBetween(String value1, String value2) {
            addCriterion("jczl between", value1, value2, "jczl");
            return (Criteria) this;
        }

        public Criteria andJczlNotBetween(String value1, String value2) {
            addCriterion("jczl not between", value1, value2, "jczl");
            return (Criteria) this;
        }

        public Criteria andLyIsNull() {
            addCriterion("ly is null");
            return (Criteria) this;
        }

        public Criteria andLyIsNotNull() {
            addCriterion("ly is not null");
            return (Criteria) this;
        }

        public Criteria andLyEqualTo(String value) {
            addCriterion("ly =", value, "ly");
            return (Criteria) this;
        }

        public Criteria andLyNotEqualTo(String value) {
            addCriterion("ly <>", value, "ly");
            return (Criteria) this;
        }

        public Criteria andLyGreaterThan(String value) {
            addCriterion("ly >", value, "ly");
            return (Criteria) this;
        }

        public Criteria andLyGreaterThanOrEqualTo(String value) {
            addCriterion("ly >=", value, "ly");
            return (Criteria) this;
        }

        public Criteria andLyLessThan(String value) {
            addCriterion("ly <", value, "ly");
            return (Criteria) this;
        }

        public Criteria andLyLessThanOrEqualTo(String value) {
            addCriterion("ly <=", value, "ly");
            return (Criteria) this;
        }

        public Criteria andLyLike(String value) {
            addCriterion("ly like", value, "ly");
            return (Criteria) this;
        }

        public Criteria andLyNotLike(String value) {
            addCriterion("ly not like", value, "ly");
            return (Criteria) this;
        }

        public Criteria andLyIn(List<String> values) {
            addCriterion("ly in", values, "ly");
            return (Criteria) this;
        }

        public Criteria andLyNotIn(List<String> values) {
            addCriterion("ly not in", values, "ly");
            return (Criteria) this;
        }

        public Criteria andLyBetween(String value1, String value2) {
            addCriterion("ly between", value1, value2, "ly");
            return (Criteria) this;
        }

        public Criteria andLyNotBetween(String value1, String value2) {
            addCriterion("ly not between", value1, value2, "ly");
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

        public Criteria andSm6IsNull() {
            addCriterion("sm6 is null");
            return (Criteria) this;
        }

        public Criteria andSm6IsNotNull() {
            addCriterion("sm6 is not null");
            return (Criteria) this;
        }

        public Criteria andSm6EqualTo(String value) {
            addCriterion("sm6 =", value, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6NotEqualTo(String value) {
            addCriterion("sm6 <>", value, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6GreaterThan(String value) {
            addCriterion("sm6 >", value, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6GreaterThanOrEqualTo(String value) {
            addCriterion("sm6 >=", value, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6LessThan(String value) {
            addCriterion("sm6 <", value, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6LessThanOrEqualTo(String value) {
            addCriterion("sm6 <=", value, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6Like(String value) {
            addCriterion("sm6 like", value, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6NotLike(String value) {
            addCriterion("sm6 not like", value, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6In(List<String> values) {
            addCriterion("sm6 in", values, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6NotIn(List<String> values) {
            addCriterion("sm6 not in", values, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6Between(String value1, String value2) {
            addCriterion("sm6 between", value1, value2, "sm6");
            return (Criteria) this;
        }

        public Criteria andSm6NotBetween(String value1, String value2) {
            addCriterion("sm6 not between", value1, value2, "sm6");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("`type` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("`type` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTsIsNull() {
            addCriterion("ts is null");
            return (Criteria) this;
        }

        public Criteria andTsIsNotNull() {
            addCriterion("ts is not null");
            return (Criteria) this;
        }

        public Criteria andTsEqualTo(String value) {
            addCriterion("ts =", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotEqualTo(String value) {
            addCriterion("ts <>", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThan(String value) {
            addCriterion("ts >", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThanOrEqualTo(String value) {
            addCriterion("ts >=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThan(String value) {
            addCriterion("ts <", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThanOrEqualTo(String value) {
            addCriterion("ts <=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLike(String value) {
            addCriterion("ts like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotLike(String value) {
            addCriterion("ts not like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsIn(List<String> values) {
            addCriterion("ts in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotIn(List<String> values) {
            addCriterion("ts not in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsBetween(String value1, String value2) {
            addCriterion("ts between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotBetween(String value1, String value2) {
            addCriterion("ts not between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTxtlxIsNull() {
            addCriterion("txtlx is null");
            return (Criteria) this;
        }

        public Criteria andTxtlxIsNotNull() {
            addCriterion("txtlx is not null");
            return (Criteria) this;
        }

        public Criteria andTxtlxEqualTo(String value) {
            addCriterion("txtlx =", value, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxNotEqualTo(String value) {
            addCriterion("txtlx <>", value, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxGreaterThan(String value) {
            addCriterion("txtlx >", value, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxGreaterThanOrEqualTo(String value) {
            addCriterion("txtlx >=", value, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxLessThan(String value) {
            addCriterion("txtlx <", value, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxLessThanOrEqualTo(String value) {
            addCriterion("txtlx <=", value, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxLike(String value) {
            addCriterion("txtlx like", value, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxNotLike(String value) {
            addCriterion("txtlx not like", value, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxIn(List<String> values) {
            addCriterion("txtlx in", values, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxNotIn(List<String> values) {
            addCriterion("txtlx not in", values, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxBetween(String value1, String value2) {
            addCriterion("txtlx between", value1, value2, "txtlx");
            return (Criteria) this;
        }

        public Criteria andTxtlxNotBetween(String value1, String value2) {
            addCriterion("txtlx not between", value1, value2, "txtlx");
            return (Criteria) this;
        }

        public Criteria andWjmcIsNull() {
            addCriterion("wjmc is null");
            return (Criteria) this;
        }

        public Criteria andWjmcIsNotNull() {
            addCriterion("wjmc is not null");
            return (Criteria) this;
        }

        public Criteria andWjmcEqualTo(String value) {
            addCriterion("wjmc =", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotEqualTo(String value) {
            addCriterion("wjmc <>", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcGreaterThan(String value) {
            addCriterion("wjmc >", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcGreaterThanOrEqualTo(String value) {
            addCriterion("wjmc >=", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcLessThan(String value) {
            addCriterion("wjmc <", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcLessThanOrEqualTo(String value) {
            addCriterion("wjmc <=", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcLike(String value) {
            addCriterion("wjmc like", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotLike(String value) {
            addCriterion("wjmc not like", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcIn(List<String> values) {
            addCriterion("wjmc in", values, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotIn(List<String> values) {
            addCriterion("wjmc not in", values, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcBetween(String value1, String value2) {
            addCriterion("wjmc between", value1, value2, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotBetween(String value1, String value2) {
            addCriterion("wjmc not between", value1, value2, "wjmc");
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