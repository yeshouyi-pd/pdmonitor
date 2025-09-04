package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.List;

public class AppMonitorExpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppMonitorExpExample() {
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

        public Criteria andDate1IsNull() {
            addCriterion("date1 is null");
            return (Criteria) this;
        }

        public Criteria andDate1IsNotNull() {
            addCriterion("date1 is not null");
            return (Criteria) this;
        }

        public Criteria andDate1EqualTo(String value) {
            addCriterion("date1 =", value, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1NotEqualTo(String value) {
            addCriterion("date1 <>", value, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1GreaterThan(String value) {
            addCriterion("date1 >", value, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1GreaterThanOrEqualTo(String value) {
            addCriterion("date1 >=", value, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1LessThan(String value) {
            addCriterion("date1 <", value, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1LessThanOrEqualTo(String value) {
            addCriterion("date1 <=", value, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1Like(String value) {
            addCriterion("date1 like", value, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1NotLike(String value) {
            addCriterion("date1 not like", value, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1In(List<String> values) {
            addCriterion("date1 in", values, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1NotIn(List<String> values) {
            addCriterion("date1 not in", values, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1Between(String value1, String value2) {
            addCriterion("date1 between", value1, value2, "date1");
            return (Criteria) this;
        }

        public Criteria andDate1NotBetween(String value1, String value2) {
            addCriterion("date1 not between", value1, value2, "date1");
            return (Criteria) this;
        }

        public Criteria andTime1IsNull() {
            addCriterion("time1 is null");
            return (Criteria) this;
        }

        public Criteria andTime1IsNotNull() {
            addCriterion("time1 is not null");
            return (Criteria) this;
        }

        public Criteria andTime1EqualTo(String value) {
            addCriterion("time1 =", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1NotEqualTo(String value) {
            addCriterion("time1 <>", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1GreaterThan(String value) {
            addCriterion("time1 >", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1GreaterThanOrEqualTo(String value) {
            addCriterion("time1 >=", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1LessThan(String value) {
            addCriterion("time1 <", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1LessThanOrEqualTo(String value) {
            addCriterion("time1 <=", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1Like(String value) {
            addCriterion("time1 like", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1NotLike(String value) {
            addCriterion("time1 not like", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1In(List<String> values) {
            addCriterion("time1 in", values, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1NotIn(List<String> values) {
            addCriterion("time1 not in", values, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1Between(String value1, String value2) {
            addCriterion("time1 between", value1, value2, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1NotBetween(String value1, String value2) {
            addCriterion("time1 not between", value1, value2, "time1");
            return (Criteria) this;
        }

        public Criteria andDeclatIsNull() {
            addCriterion("decLat is null");
            return (Criteria) this;
        }

        public Criteria andDeclatIsNotNull() {
            addCriterion("decLat is not null");
            return (Criteria) this;
        }

        public Criteria andDeclatEqualTo(String value) {
            addCriterion("decLat =", value, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatNotEqualTo(String value) {
            addCriterion("decLat <>", value, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatGreaterThan(String value) {
            addCriterion("decLat >", value, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatGreaterThanOrEqualTo(String value) {
            addCriterion("decLat >=", value, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatLessThan(String value) {
            addCriterion("decLat <", value, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatLessThanOrEqualTo(String value) {
            addCriterion("decLat <=", value, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatLike(String value) {
            addCriterion("decLat like", value, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatNotLike(String value) {
            addCriterion("decLat not like", value, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatIn(List<String> values) {
            addCriterion("decLat in", values, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatNotIn(List<String> values) {
            addCriterion("decLat not in", values, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatBetween(String value1, String value2) {
            addCriterion("decLat between", value1, value2, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclatNotBetween(String value1, String value2) {
            addCriterion("decLat not between", value1, value2, "declat");
            return (Criteria) this;
        }

        public Criteria andDeclongIsNull() {
            addCriterion("decLong is null");
            return (Criteria) this;
        }

        public Criteria andDeclongIsNotNull() {
            addCriterion("decLong is not null");
            return (Criteria) this;
        }

        public Criteria andDeclongEqualTo(String value) {
            addCriterion("decLong =", value, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongNotEqualTo(String value) {
            addCriterion("decLong <>", value, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongGreaterThan(String value) {
            addCriterion("decLong >", value, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongGreaterThanOrEqualTo(String value) {
            addCriterion("decLong >=", value, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongLessThan(String value) {
            addCriterion("decLong <", value, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongLessThanOrEqualTo(String value) {
            addCriterion("decLong <=", value, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongLike(String value) {
            addCriterion("decLong like", value, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongNotLike(String value) {
            addCriterion("decLong not like", value, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongIn(List<String> values) {
            addCriterion("decLong in", values, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongNotIn(List<String> values) {
            addCriterion("decLong not in", values, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongBetween(String value1, String value2) {
            addCriterion("decLong between", value1, value2, "declong");
            return (Criteria) this;
        }

        public Criteria andDeclongNotBetween(String value1, String value2) {
            addCriterion("decLong not between", value1, value2, "declong");
            return (Criteria) this;
        }

        public Criteria andEventsIsNull() {
            addCriterion("events is null");
            return (Criteria) this;
        }

        public Criteria andEventsIsNotNull() {
            addCriterion("events is not null");
            return (Criteria) this;
        }

        public Criteria andEventsEqualTo(String value) {
            addCriterion("events =", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotEqualTo(String value) {
            addCriterion("events <>", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsGreaterThan(String value) {
            addCriterion("events >", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsGreaterThanOrEqualTo(String value) {
            addCriterion("events >=", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsLessThan(String value) {
            addCriterion("events <", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsLessThanOrEqualTo(String value) {
            addCriterion("events <=", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsLike(String value) {
            addCriterion("events like", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotLike(String value) {
            addCriterion("events not like", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsIn(List<String> values) {
            addCriterion("events in", values, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotIn(List<String> values) {
            addCriterion("events not in", values, "events");
            return (Criteria) this;
        }

        public Criteria andEventsBetween(String value1, String value2) {
            addCriterion("events between", value1, value2, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotBetween(String value1, String value2) {
            addCriterion("events not between", value1, value2, "events");
            return (Criteria) this;
        }

        public Criteria andData1IsNull() {
            addCriterion("data1 is null");
            return (Criteria) this;
        }

        public Criteria andData1IsNotNull() {
            addCriterion("data1 is not null");
            return (Criteria) this;
        }

        public Criteria andData1EqualTo(String value) {
            addCriterion("data1 =", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1NotEqualTo(String value) {
            addCriterion("data1 <>", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1GreaterThan(String value) {
            addCriterion("data1 >", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1GreaterThanOrEqualTo(String value) {
            addCriterion("data1 >=", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1LessThan(String value) {
            addCriterion("data1 <", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1LessThanOrEqualTo(String value) {
            addCriterion("data1 <=", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1Like(String value) {
            addCriterion("data1 like", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1NotLike(String value) {
            addCriterion("data1 not like", value, "data1");
            return (Criteria) this;
        }

        public Criteria andData1In(List<String> values) {
            addCriterion("data1 in", values, "data1");
            return (Criteria) this;
        }

        public Criteria andData1NotIn(List<String> values) {
            addCriterion("data1 not in", values, "data1");
            return (Criteria) this;
        }

        public Criteria andData1Between(String value1, String value2) {
            addCriterion("data1 between", value1, value2, "data1");
            return (Criteria) this;
        }

        public Criteria andData1NotBetween(String value1, String value2) {
            addCriterion("data1 not between", value1, value2, "data1");
            return (Criteria) this;
        }

        public Criteria andData2IsNull() {
            addCriterion("data2 is null");
            return (Criteria) this;
        }

        public Criteria andData2IsNotNull() {
            addCriterion("data2 is not null");
            return (Criteria) this;
        }

        public Criteria andData2EqualTo(String value) {
            addCriterion("data2 =", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2NotEqualTo(String value) {
            addCriterion("data2 <>", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2GreaterThan(String value) {
            addCriterion("data2 >", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2GreaterThanOrEqualTo(String value) {
            addCriterion("data2 >=", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2LessThan(String value) {
            addCriterion("data2 <", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2LessThanOrEqualTo(String value) {
            addCriterion("data2 <=", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2Like(String value) {
            addCriterion("data2 like", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2NotLike(String value) {
            addCriterion("data2 not like", value, "data2");
            return (Criteria) this;
        }

        public Criteria andData2In(List<String> values) {
            addCriterion("data2 in", values, "data2");
            return (Criteria) this;
        }

        public Criteria andData2NotIn(List<String> values) {
            addCriterion("data2 not in", values, "data2");
            return (Criteria) this;
        }

        public Criteria andData2Between(String value1, String value2) {
            addCriterion("data2 between", value1, value2, "data2");
            return (Criteria) this;
        }

        public Criteria andData2NotBetween(String value1, String value2) {
            addCriterion("data2 not between", value1, value2, "data2");
            return (Criteria) this;
        }

        public Criteria andData3IsNull() {
            addCriterion("data3 is null");
            return (Criteria) this;
        }

        public Criteria andData3IsNotNull() {
            addCriterion("data3 is not null");
            return (Criteria) this;
        }

        public Criteria andData3EqualTo(String value) {
            addCriterion("data3 =", value, "data3");
            return (Criteria) this;
        }

        public Criteria andData3NotEqualTo(String value) {
            addCriterion("data3 <>", value, "data3");
            return (Criteria) this;
        }

        public Criteria andData3GreaterThan(String value) {
            addCriterion("data3 >", value, "data3");
            return (Criteria) this;
        }

        public Criteria andData3GreaterThanOrEqualTo(String value) {
            addCriterion("data3 >=", value, "data3");
            return (Criteria) this;
        }

        public Criteria andData3LessThan(String value) {
            addCriterion("data3 <", value, "data3");
            return (Criteria) this;
        }

        public Criteria andData3LessThanOrEqualTo(String value) {
            addCriterion("data3 <=", value, "data3");
            return (Criteria) this;
        }

        public Criteria andData3Like(String value) {
            addCriterion("data3 like", value, "data3");
            return (Criteria) this;
        }

        public Criteria andData3NotLike(String value) {
            addCriterion("data3 not like", value, "data3");
            return (Criteria) this;
        }

        public Criteria andData3In(List<String> values) {
            addCriterion("data3 in", values, "data3");
            return (Criteria) this;
        }

        public Criteria andData3NotIn(List<String> values) {
            addCriterion("data3 not in", values, "data3");
            return (Criteria) this;
        }

        public Criteria andData3Between(String value1, String value2) {
            addCriterion("data3 between", value1, value2, "data3");
            return (Criteria) this;
        }

        public Criteria andData3NotBetween(String value1, String value2) {
            addCriterion("data3 not between", value1, value2, "data3");
            return (Criteria) this;
        }

        public Criteria andData4IsNull() {
            addCriterion("data4 is null");
            return (Criteria) this;
        }

        public Criteria andData4IsNotNull() {
            addCriterion("data4 is not null");
            return (Criteria) this;
        }

        public Criteria andData4EqualTo(String value) {
            addCriterion("data4 =", value, "data4");
            return (Criteria) this;
        }

        public Criteria andData4NotEqualTo(String value) {
            addCriterion("data4 <>", value, "data4");
            return (Criteria) this;
        }

        public Criteria andData4GreaterThan(String value) {
            addCriterion("data4 >", value, "data4");
            return (Criteria) this;
        }

        public Criteria andData4GreaterThanOrEqualTo(String value) {
            addCriterion("data4 >=", value, "data4");
            return (Criteria) this;
        }

        public Criteria andData4LessThan(String value) {
            addCriterion("data4 <", value, "data4");
            return (Criteria) this;
        }

        public Criteria andData4LessThanOrEqualTo(String value) {
            addCriterion("data4 <=", value, "data4");
            return (Criteria) this;
        }

        public Criteria andData4Like(String value) {
            addCriterion("data4 like", value, "data4");
            return (Criteria) this;
        }

        public Criteria andData4NotLike(String value) {
            addCriterion("data4 not like", value, "data4");
            return (Criteria) this;
        }

        public Criteria andData4In(List<String> values) {
            addCriterion("data4 in", values, "data4");
            return (Criteria) this;
        }

        public Criteria andData4NotIn(List<String> values) {
            addCriterion("data4 not in", values, "data4");
            return (Criteria) this;
        }

        public Criteria andData4Between(String value1, String value2) {
            addCriterion("data4 between", value1, value2, "data4");
            return (Criteria) this;
        }

        public Criteria andData4NotBetween(String value1, String value2) {
            addCriterion("data4 not between", value1, value2, "data4");
            return (Criteria) this;
        }

        public Criteria andData5IsNull() {
            addCriterion("data5 is null");
            return (Criteria) this;
        }

        public Criteria andData5IsNotNull() {
            addCriterion("data5 is not null");
            return (Criteria) this;
        }

        public Criteria andData5EqualTo(String value) {
            addCriterion("data5 =", value, "data5");
            return (Criteria) this;
        }

        public Criteria andData5NotEqualTo(String value) {
            addCriterion("data5 <>", value, "data5");
            return (Criteria) this;
        }

        public Criteria andData5GreaterThan(String value) {
            addCriterion("data5 >", value, "data5");
            return (Criteria) this;
        }

        public Criteria andData5GreaterThanOrEqualTo(String value) {
            addCriterion("data5 >=", value, "data5");
            return (Criteria) this;
        }

        public Criteria andData5LessThan(String value) {
            addCriterion("data5 <", value, "data5");
            return (Criteria) this;
        }

        public Criteria andData5LessThanOrEqualTo(String value) {
            addCriterion("data5 <=", value, "data5");
            return (Criteria) this;
        }

        public Criteria andData5Like(String value) {
            addCriterion("data5 like", value, "data5");
            return (Criteria) this;
        }

        public Criteria andData5NotLike(String value) {
            addCriterion("data5 not like", value, "data5");
            return (Criteria) this;
        }

        public Criteria andData5In(List<String> values) {
            addCriterion("data5 in", values, "data5");
            return (Criteria) this;
        }

        public Criteria andData5NotIn(List<String> values) {
            addCriterion("data5 not in", values, "data5");
            return (Criteria) this;
        }

        public Criteria andData5Between(String value1, String value2) {
            addCriterion("data5 between", value1, value2, "data5");
            return (Criteria) this;
        }

        public Criteria andData5NotBetween(String value1, String value2) {
            addCriterion("data5 not between", value1, value2, "data5");
            return (Criteria) this;
        }

        public Criteria andData6IsNull() {
            addCriterion("data6 is null");
            return (Criteria) this;
        }

        public Criteria andData6IsNotNull() {
            addCriterion("data6 is not null");
            return (Criteria) this;
        }

        public Criteria andData6EqualTo(String value) {
            addCriterion("data6 =", value, "data6");
            return (Criteria) this;
        }

        public Criteria andData6NotEqualTo(String value) {
            addCriterion("data6 <>", value, "data6");
            return (Criteria) this;
        }

        public Criteria andData6GreaterThan(String value) {
            addCriterion("data6 >", value, "data6");
            return (Criteria) this;
        }

        public Criteria andData6GreaterThanOrEqualTo(String value) {
            addCriterion("data6 >=", value, "data6");
            return (Criteria) this;
        }

        public Criteria andData6LessThan(String value) {
            addCriterion("data6 <", value, "data6");
            return (Criteria) this;
        }

        public Criteria andData6LessThanOrEqualTo(String value) {
            addCriterion("data6 <=", value, "data6");
            return (Criteria) this;
        }

        public Criteria andData6Like(String value) {
            addCriterion("data6 like", value, "data6");
            return (Criteria) this;
        }

        public Criteria andData6NotLike(String value) {
            addCriterion("data6 not like", value, "data6");
            return (Criteria) this;
        }

        public Criteria andData6In(List<String> values) {
            addCriterion("data6 in", values, "data6");
            return (Criteria) this;
        }

        public Criteria andData6NotIn(List<String> values) {
            addCriterion("data6 not in", values, "data6");
            return (Criteria) this;
        }

        public Criteria andData6Between(String value1, String value2) {
            addCriterion("data6 between", value1, value2, "data6");
            return (Criteria) this;
        }

        public Criteria andData6NotBetween(String value1, String value2) {
            addCriterion("data6 not between", value1, value2, "data6");
            return (Criteria) this;
        }

        public Criteria andData7IsNull() {
            addCriterion("data7 is null");
            return (Criteria) this;
        }

        public Criteria andData7IsNotNull() {
            addCriterion("data7 is not null");
            return (Criteria) this;
        }

        public Criteria andData7EqualTo(String value) {
            addCriterion("data7 =", value, "data7");
            return (Criteria) this;
        }

        public Criteria andData7NotEqualTo(String value) {
            addCriterion("data7 <>", value, "data7");
            return (Criteria) this;
        }

        public Criteria andData7GreaterThan(String value) {
            addCriterion("data7 >", value, "data7");
            return (Criteria) this;
        }

        public Criteria andData7GreaterThanOrEqualTo(String value) {
            addCriterion("data7 >=", value, "data7");
            return (Criteria) this;
        }

        public Criteria andData7LessThan(String value) {
            addCriterion("data7 <", value, "data7");
            return (Criteria) this;
        }

        public Criteria andData7LessThanOrEqualTo(String value) {
            addCriterion("data7 <=", value, "data7");
            return (Criteria) this;
        }

        public Criteria andData7Like(String value) {
            addCriterion("data7 like", value, "data7");
            return (Criteria) this;
        }

        public Criteria andData7NotLike(String value) {
            addCriterion("data7 not like", value, "data7");
            return (Criteria) this;
        }

        public Criteria andData7In(List<String> values) {
            addCriterion("data7 in", values, "data7");
            return (Criteria) this;
        }

        public Criteria andData7NotIn(List<String> values) {
            addCriterion("data7 not in", values, "data7");
            return (Criteria) this;
        }

        public Criteria andData7Between(String value1, String value2) {
            addCriterion("data7 between", value1, value2, "data7");
            return (Criteria) this;
        }

        public Criteria andData7NotBetween(String value1, String value2) {
            addCriterion("data7 not between", value1, value2, "data7");
            return (Criteria) this;
        }

        public Criteria andData8IsNull() {
            addCriterion("data8 is null");
            return (Criteria) this;
        }

        public Criteria andData8IsNotNull() {
            addCriterion("data8 is not null");
            return (Criteria) this;
        }

        public Criteria andData8EqualTo(String value) {
            addCriterion("data8 =", value, "data8");
            return (Criteria) this;
        }

        public Criteria andData8NotEqualTo(String value) {
            addCriterion("data8 <>", value, "data8");
            return (Criteria) this;
        }

        public Criteria andData8GreaterThan(String value) {
            addCriterion("data8 >", value, "data8");
            return (Criteria) this;
        }

        public Criteria andData8GreaterThanOrEqualTo(String value) {
            addCriterion("data8 >=", value, "data8");
            return (Criteria) this;
        }

        public Criteria andData8LessThan(String value) {
            addCriterion("data8 <", value, "data8");
            return (Criteria) this;
        }

        public Criteria andData8LessThanOrEqualTo(String value) {
            addCriterion("data8 <=", value, "data8");
            return (Criteria) this;
        }

        public Criteria andData8Like(String value) {
            addCriterion("data8 like", value, "data8");
            return (Criteria) this;
        }

        public Criteria andData8NotLike(String value) {
            addCriterion("data8 not like", value, "data8");
            return (Criteria) this;
        }

        public Criteria andData8In(List<String> values) {
            addCriterion("data8 in", values, "data8");
            return (Criteria) this;
        }

        public Criteria andData8NotIn(List<String> values) {
            addCriterion("data8 not in", values, "data8");
            return (Criteria) this;
        }

        public Criteria andData8Between(String value1, String value2) {
            addCriterion("data8 between", value1, value2, "data8");
            return (Criteria) this;
        }

        public Criteria andData8NotBetween(String value1, String value2) {
            addCriterion("data8 not between", value1, value2, "data8");
            return (Criteria) this;
        }

        public Criteria andData9IsNull() {
            addCriterion("data9 is null");
            return (Criteria) this;
        }

        public Criteria andData9IsNotNull() {
            addCriterion("data9 is not null");
            return (Criteria) this;
        }

        public Criteria andData9EqualTo(String value) {
            addCriterion("data9 =", value, "data9");
            return (Criteria) this;
        }

        public Criteria andData9NotEqualTo(String value) {
            addCriterion("data9 <>", value, "data9");
            return (Criteria) this;
        }

        public Criteria andData9GreaterThan(String value) {
            addCriterion("data9 >", value, "data9");
            return (Criteria) this;
        }

        public Criteria andData9GreaterThanOrEqualTo(String value) {
            addCriterion("data9 >=", value, "data9");
            return (Criteria) this;
        }

        public Criteria andData9LessThan(String value) {
            addCriterion("data9 <", value, "data9");
            return (Criteria) this;
        }

        public Criteria andData9LessThanOrEqualTo(String value) {
            addCriterion("data9 <=", value, "data9");
            return (Criteria) this;
        }

        public Criteria andData9Like(String value) {
            addCriterion("data9 like", value, "data9");
            return (Criteria) this;
        }

        public Criteria andData9NotLike(String value) {
            addCriterion("data9 not like", value, "data9");
            return (Criteria) this;
        }

        public Criteria andData9In(List<String> values) {
            addCriterion("data9 in", values, "data9");
            return (Criteria) this;
        }

        public Criteria andData9NotIn(List<String> values) {
            addCriterion("data9 not in", values, "data9");
            return (Criteria) this;
        }

        public Criteria andData9Between(String value1, String value2) {
            addCriterion("data9 between", value1, value2, "data9");
            return (Criteria) this;
        }

        public Criteria andData9NotBetween(String value1, String value2) {
            addCriterion("data9 not between", value1, value2, "data9");
            return (Criteria) this;
        }

        public Criteria andData10IsNull() {
            addCriterion("data10 is null");
            return (Criteria) this;
        }

        public Criteria andData10IsNotNull() {
            addCriterion("data10 is not null");
            return (Criteria) this;
        }

        public Criteria andData10EqualTo(String value) {
            addCriterion("data10 =", value, "data10");
            return (Criteria) this;
        }

        public Criteria andData10NotEqualTo(String value) {
            addCriterion("data10 <>", value, "data10");
            return (Criteria) this;
        }

        public Criteria andData10GreaterThan(String value) {
            addCriterion("data10 >", value, "data10");
            return (Criteria) this;
        }

        public Criteria andData10GreaterThanOrEqualTo(String value) {
            addCriterion("data10 >=", value, "data10");
            return (Criteria) this;
        }

        public Criteria andData10LessThan(String value) {
            addCriterion("data10 <", value, "data10");
            return (Criteria) this;
        }

        public Criteria andData10LessThanOrEqualTo(String value) {
            addCriterion("data10 <=", value, "data10");
            return (Criteria) this;
        }

        public Criteria andData10Like(String value) {
            addCriterion("data10 like", value, "data10");
            return (Criteria) this;
        }

        public Criteria andData10NotLike(String value) {
            addCriterion("data10 not like", value, "data10");
            return (Criteria) this;
        }

        public Criteria andData10In(List<String> values) {
            addCriterion("data10 in", values, "data10");
            return (Criteria) this;
        }

        public Criteria andData10NotIn(List<String> values) {
            addCriterion("data10 not in", values, "data10");
            return (Criteria) this;
        }

        public Criteria andData10Between(String value1, String value2) {
            addCriterion("data10 between", value1, value2, "data10");
            return (Criteria) this;
        }

        public Criteria andData10NotBetween(String value1, String value2) {
            addCriterion("data10 not between", value1, value2, "data10");
            return (Criteria) this;
        }

        public Criteria andData11IsNull() {
            addCriterion("data11 is null");
            return (Criteria) this;
        }

        public Criteria andData11IsNotNull() {
            addCriterion("data11 is not null");
            return (Criteria) this;
        }

        public Criteria andData11EqualTo(String value) {
            addCriterion("data11 =", value, "data11");
            return (Criteria) this;
        }

        public Criteria andData11NotEqualTo(String value) {
            addCriterion("data11 <>", value, "data11");
            return (Criteria) this;
        }

        public Criteria andData11GreaterThan(String value) {
            addCriterion("data11 >", value, "data11");
            return (Criteria) this;
        }

        public Criteria andData11GreaterThanOrEqualTo(String value) {
            addCriterion("data11 >=", value, "data11");
            return (Criteria) this;
        }

        public Criteria andData11LessThan(String value) {
            addCriterion("data11 <", value, "data11");
            return (Criteria) this;
        }

        public Criteria andData11LessThanOrEqualTo(String value) {
            addCriterion("data11 <=", value, "data11");
            return (Criteria) this;
        }

        public Criteria andData11Like(String value) {
            addCriterion("data11 like", value, "data11");
            return (Criteria) this;
        }

        public Criteria andData11NotLike(String value) {
            addCriterion("data11 not like", value, "data11");
            return (Criteria) this;
        }

        public Criteria andData11In(List<String> values) {
            addCriterion("data11 in", values, "data11");
            return (Criteria) this;
        }

        public Criteria andData11NotIn(List<String> values) {
            addCriterion("data11 not in", values, "data11");
            return (Criteria) this;
        }

        public Criteria andData11Between(String value1, String value2) {
            addCriterion("data11 between", value1, value2, "data11");
            return (Criteria) this;
        }

        public Criteria andData11NotBetween(String value1, String value2) {
            addCriterion("data11 not between", value1, value2, "data11");
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

        public Criteria andGczxmIsNull() {
            addCriterion("gczxm is null");
            return (Criteria) this;
        }

        public Criteria andGczxmIsNotNull() {
            addCriterion("gczxm is not null");
            return (Criteria) this;
        }

        public Criteria andGczxmEqualTo(String value) {
            addCriterion("gczxm =", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmNotEqualTo(String value) {
            addCriterion("gczxm <>", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmGreaterThan(String value) {
            addCriterion("gczxm >", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmGreaterThanOrEqualTo(String value) {
            addCriterion("gczxm >=", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmLessThan(String value) {
            addCriterion("gczxm <", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmLessThanOrEqualTo(String value) {
            addCriterion("gczxm <=", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmLike(String value) {
            addCriterion("gczxm like", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmNotLike(String value) {
            addCriterion("gczxm not like", value, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmIn(List<String> values) {
            addCriterion("gczxm in", values, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmNotIn(List<String> values) {
            addCriterion("gczxm not in", values, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmBetween(String value1, String value2) {
            addCriterion("gczxm between", value1, value2, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczxmNotBetween(String value1, String value2) {
            addCriterion("gczxm not between", value1, value2, "gczxm");
            return (Criteria) this;
        }

        public Criteria andGczzwmIsNull() {
            addCriterion("gczzwm is null");
            return (Criteria) this;
        }

        public Criteria andGczzwmIsNotNull() {
            addCriterion("gczzwm is not null");
            return (Criteria) this;
        }

        public Criteria andGczzwmEqualTo(String value) {
            addCriterion("gczzwm =", value, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmNotEqualTo(String value) {
            addCriterion("gczzwm <>", value, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmGreaterThan(String value) {
            addCriterion("gczzwm >", value, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmGreaterThanOrEqualTo(String value) {
            addCriterion("gczzwm >=", value, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmLessThan(String value) {
            addCriterion("gczzwm <", value, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmLessThanOrEqualTo(String value) {
            addCriterion("gczzwm <=", value, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmLike(String value) {
            addCriterion("gczzwm like", value, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmNotLike(String value) {
            addCriterion("gczzwm not like", value, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmIn(List<String> values) {
            addCriterion("gczzwm in", values, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmNotIn(List<String> values) {
            addCriterion("gczzwm not in", values, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmBetween(String value1, String value2) {
            addCriterion("gczzwm between", value1, value2, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andGczzwmNotBetween(String value1, String value2) {
            addCriterion("gczzwm not between", value1, value2, "gczzwm");
            return (Criteria) this;
        }

        public Criteria andTypesIsNull() {
            addCriterion("types is null");
            return (Criteria) this;
        }

        public Criteria andTypesIsNotNull() {
            addCriterion("types is not null");
            return (Criteria) this;
        }

        public Criteria andTypesEqualTo(String value) {
            addCriterion("types =", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesNotEqualTo(String value) {
            addCriterion("types <>", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesGreaterThan(String value) {
            addCriterion("types >", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesGreaterThanOrEqualTo(String value) {
            addCriterion("types >=", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesLessThan(String value) {
            addCriterion("types <", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesLessThanOrEqualTo(String value) {
            addCriterion("types <=", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesLike(String value) {
            addCriterion("types like", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesNotLike(String value) {
            addCriterion("types not like", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesIn(List<String> values) {
            addCriterion("types in", values, "types");
            return (Criteria) this;
        }

        public Criteria andTypesNotIn(List<String> values) {
            addCriterion("types not in", values, "types");
            return (Criteria) this;
        }

        public Criteria andTypesBetween(String value1, String value2) {
            addCriterion("types between", value1, value2, "types");
            return (Criteria) this;
        }

        public Criteria andTypesNotBetween(String value1, String value2) {
            addCriterion("types not between", value1, value2, "types");
            return (Criteria) this;
        }

        public Criteria andMidIsNull() {
            addCriterion("mid is null");
            return (Criteria) this;
        }

        public Criteria andMidIsNotNull() {
            addCriterion("mid is not null");
            return (Criteria) this;
        }

        public Criteria andMidEqualTo(String value) {
            addCriterion("mid =", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotEqualTo(String value) {
            addCriterion("mid <>", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThan(String value) {
            addCriterion("mid >", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThanOrEqualTo(String value) {
            addCriterion("mid >=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThan(String value) {
            addCriterion("mid <", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThanOrEqualTo(String value) {
            addCriterion("mid <=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLike(String value) {
            addCriterion("mid like", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotLike(String value) {
            addCriterion("mid not like", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidIn(List<String> values) {
            addCriterion("mid in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotIn(List<String> values) {
            addCriterion("mid not in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidBetween(String value1, String value2) {
            addCriterion("mid between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotBetween(String value1, String value2) {
            addCriterion("mid not between", value1, value2, "mid");
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