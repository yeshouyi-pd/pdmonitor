package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class CameraMiddleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CameraMiddleExample() {
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

        public Criteria andJqsjIsNull() {
            addCriterion("jqsj is null");
            return (Criteria) this;
        }

        public Criteria andJqsjIsNotNull() {
            addCriterion("jqsj is not null");
            return (Criteria) this;
        }

        public Criteria andJqsjEqualTo(String value) {
            addCriterion("jqsj =", value, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjNotEqualTo(String value) {
            addCriterion("jqsj <>", value, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjGreaterThan(String value) {
            addCriterion("jqsj >", value, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjGreaterThanOrEqualTo(String value) {
            addCriterion("jqsj >=", value, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjLessThan(String value) {
            addCriterion("jqsj <", value, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjLessThanOrEqualTo(String value) {
            addCriterion("jqsj <=", value, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjLike(String value) {
            addCriterion("jqsj like", value, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjNotLike(String value) {
            addCriterion("jqsj not like", value, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjIn(List<String> values) {
            addCriterion("jqsj in", values, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjNotIn(List<String> values) {
            addCriterion("jqsj not in", values, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjBetween(String value1, String value2) {
            addCriterion("jqsj between", value1, value2, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJqsjNotBetween(String value1, String value2) {
            addCriterion("jqsj not between", value1, value2, "jqsj");
            return (Criteria) this;
        }

        public Criteria andJgsjIsNull() {
            addCriterion("jgsj is null");
            return (Criteria) this;
        }

        public Criteria andJgsjIsNotNull() {
            addCriterion("jgsj is not null");
            return (Criteria) this;
        }

        public Criteria andJgsjEqualTo(String value) {
            addCriterion("jgsj =", value, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjNotEqualTo(String value) {
            addCriterion("jgsj <>", value, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjGreaterThan(String value) {
            addCriterion("jgsj >", value, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjGreaterThanOrEqualTo(String value) {
            addCriterion("jgsj >=", value, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjLessThan(String value) {
            addCriterion("jgsj <", value, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjLessThanOrEqualTo(String value) {
            addCriterion("jgsj <=", value, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjLike(String value) {
            addCriterion("jgsj like", value, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjNotLike(String value) {
            addCriterion("jgsj not like", value, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjIn(List<String> values) {
            addCriterion("jgsj in", values, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjNotIn(List<String> values) {
            addCriterion("jgsj not in", values, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjBetween(String value1, String value2) {
            addCriterion("jgsj between", value1, value2, "jgsj");
            return (Criteria) this;
        }

        public Criteria andJgsjNotBetween(String value1, String value2) {
            addCriterion("jgsj not between", value1, value2, "jgsj");
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

        public Criteria andPortEqualTo(String value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(String value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(String value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(String value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(String value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(String value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLike(String value) {
            addCriterion("port like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotLike(String value) {
            addCriterion("port not like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<String> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<String> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(String value1, String value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(String value1, String value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andCamerapwsIsNull() {
            addCriterion("camerapws is null");
            return (Criteria) this;
        }

        public Criteria andCamerapwsIsNotNull() {
            addCriterion("camerapws is not null");
            return (Criteria) this;
        }

        public Criteria andCamerapwsEqualTo(String value) {
            addCriterion("camerapws =", value, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsNotEqualTo(String value) {
            addCriterion("camerapws <>", value, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsGreaterThan(String value) {
            addCriterion("camerapws >", value, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsGreaterThanOrEqualTo(String value) {
            addCriterion("camerapws >=", value, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsLessThan(String value) {
            addCriterion("camerapws <", value, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsLessThanOrEqualTo(String value) {
            addCriterion("camerapws <=", value, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsLike(String value) {
            addCriterion("camerapws like", value, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsNotLike(String value) {
            addCriterion("camerapws not like", value, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsIn(List<String> values) {
            addCriterion("camerapws in", values, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsNotIn(List<String> values) {
            addCriterion("camerapws not in", values, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsBetween(String value1, String value2) {
            addCriterion("camerapws between", value1, value2, "camerapws");
            return (Criteria) this;
        }

        public Criteria andCamerapwsNotBetween(String value1, String value2) {
            addCriterion("camerapws not between", value1, value2, "camerapws");
            return (Criteria) this;
        }

        public Criteria andTdhIsNull() {
            addCriterion("tdh is null");
            return (Criteria) this;
        }

        public Criteria andTdhIsNotNull() {
            addCriterion("tdh is not null");
            return (Criteria) this;
        }

        public Criteria andTdhEqualTo(String value) {
            addCriterion("tdh =", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotEqualTo(String value) {
            addCriterion("tdh <>", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhGreaterThan(String value) {
            addCriterion("tdh >", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhGreaterThanOrEqualTo(String value) {
            addCriterion("tdh >=", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhLessThan(String value) {
            addCriterion("tdh <", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhLessThanOrEqualTo(String value) {
            addCriterion("tdh <=", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhLike(String value) {
            addCriterion("tdh like", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotLike(String value) {
            addCriterion("tdh not like", value, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhIn(List<String> values) {
            addCriterion("tdh in", values, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotIn(List<String> values) {
            addCriterion("tdh not in", values, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhBetween(String value1, String value2) {
            addCriterion("tdh between", value1, value2, "tdh");
            return (Criteria) this;
        }

        public Criteria andTdhNotBetween(String value1, String value2) {
            addCriterion("tdh not between", value1, value2, "tdh");
            return (Criteria) this;
        }

        public Criteria andDvripIsNull() {
            addCriterion("dvrip is null");
            return (Criteria) this;
        }

        public Criteria andDvripIsNotNull() {
            addCriterion("dvrip is not null");
            return (Criteria) this;
        }

        public Criteria andDvripEqualTo(String value) {
            addCriterion("dvrip =", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripNotEqualTo(String value) {
            addCriterion("dvrip <>", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripGreaterThan(String value) {
            addCriterion("dvrip >", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripGreaterThanOrEqualTo(String value) {
            addCriterion("dvrip >=", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripLessThan(String value) {
            addCriterion("dvrip <", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripLessThanOrEqualTo(String value) {
            addCriterion("dvrip <=", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripLike(String value) {
            addCriterion("dvrip like", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripNotLike(String value) {
            addCriterion("dvrip not like", value, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripIn(List<String> values) {
            addCriterion("dvrip in", values, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripNotIn(List<String> values) {
            addCriterion("dvrip not in", values, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripBetween(String value1, String value2) {
            addCriterion("dvrip between", value1, value2, "dvrip");
            return (Criteria) this;
        }

        public Criteria andDvripNotBetween(String value1, String value2) {
            addCriterion("dvrip not between", value1, value2, "dvrip");
            return (Criteria) this;
        }

        public Criteria andSfjqIsNull() {
            addCriterion("sfjq is null");
            return (Criteria) this;
        }

        public Criteria andSfjqIsNotNull() {
            addCriterion("sfjq is not null");
            return (Criteria) this;
        }

        public Criteria andSfjqEqualTo(String value) {
            addCriterion("sfjq =", value, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqNotEqualTo(String value) {
            addCriterion("sfjq <>", value, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqGreaterThan(String value) {
            addCriterion("sfjq >", value, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqGreaterThanOrEqualTo(String value) {
            addCriterion("sfjq >=", value, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqLessThan(String value) {
            addCriterion("sfjq <", value, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqLessThanOrEqualTo(String value) {
            addCriterion("sfjq <=", value, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqLike(String value) {
            addCriterion("sfjq like", value, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqNotLike(String value) {
            addCriterion("sfjq not like", value, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqIn(List<String> values) {
            addCriterion("sfjq in", values, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqNotIn(List<String> values) {
            addCriterion("sfjq not in", values, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqBetween(String value1, String value2) {
            addCriterion("sfjq between", value1, value2, "sfjq");
            return (Criteria) this;
        }

        public Criteria andSfjqNotBetween(String value1, String value2) {
            addCriterion("sfjq not between", value1, value2, "sfjq");
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

        public Criteria andSmIsNull() {
            addCriterion("sm is null");
            return (Criteria) this;
        }

        public Criteria andSmIsNotNull() {
            addCriterion("sm is not null");
            return (Criteria) this;
        }

        public Criteria andSmEqualTo(String value) {
            addCriterion("sm =", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotEqualTo(String value) {
            addCriterion("sm <>", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmGreaterThan(String value) {
            addCriterion("sm >", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmGreaterThanOrEqualTo(String value) {
            addCriterion("sm >=", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLessThan(String value) {
            addCriterion("sm <", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLessThanOrEqualTo(String value) {
            addCriterion("sm <=", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLike(String value) {
            addCriterion("sm like", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotLike(String value) {
            addCriterion("sm not like", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmIn(List<String> values) {
            addCriterion("sm in", values, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotIn(List<String> values) {
            addCriterion("sm not in", values, "sm");
            return (Criteria) this;
        }

        public Criteria andSmBetween(String value1, String value2) {
            addCriterion("sm between", value1, value2, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotBetween(String value1, String value2) {
            addCriterion("sm not between", value1, value2, "sm");
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