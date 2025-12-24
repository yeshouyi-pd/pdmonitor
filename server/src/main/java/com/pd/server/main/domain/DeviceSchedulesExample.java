package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DeviceSchedulesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceSchedulesExample() {
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

        public Criteria andScheduleNameIsNull() {
            addCriterion("schedule_name is null");
            return (Criteria) this;
        }

        public Criteria andScheduleNameIsNotNull() {
            addCriterion("schedule_name is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleNameEqualTo(String value) {
            addCriterion("schedule_name =", value, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameNotEqualTo(String value) {
            addCriterion("schedule_name <>", value, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameGreaterThan(String value) {
            addCriterion("schedule_name >", value, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameGreaterThanOrEqualTo(String value) {
            addCriterion("schedule_name >=", value, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameLessThan(String value) {
            addCriterion("schedule_name <", value, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameLessThanOrEqualTo(String value) {
            addCriterion("schedule_name <=", value, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameLike(String value) {
            addCriterion("schedule_name like", value, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameNotLike(String value) {
            addCriterion("schedule_name not like", value, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameIn(List<String> values) {
            addCriterion("schedule_name in", values, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameNotIn(List<String> values) {
            addCriterion("schedule_name not in", values, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameBetween(String value1, String value2) {
            addCriterion("schedule_name between", value1, value2, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andScheduleNameNotBetween(String value1, String value2) {
            addCriterion("schedule_name not between", value1, value2, "scheduleName");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNull() {
            addCriterion("device_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNotNull() {
            addCriterion("device_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdEqualTo(String value) {
            addCriterion("device_id =", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotEqualTo(String value) {
            addCriterion("device_id <>", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThan(String value) {
            addCriterion("device_id >", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThanOrEqualTo(String value) {
            addCriterion("device_id >=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThan(String value) {
            addCriterion("device_id <", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThanOrEqualTo(String value) {
            addCriterion("device_id <=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLike(String value) {
            addCriterion("device_id like", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotLike(String value) {
            addCriterion("device_id not like", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIn(List<String> values) {
            addCriterion("device_id in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotIn(List<String> values) {
            addCriterion("device_id not in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdBetween(String value1, String value2) {
            addCriterion("device_id between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotBetween(String value1, String value2) {
            addCriterion("device_id not between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(String value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(String value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(String value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(String value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(String value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLike(String value) {
            addCriterion("start_time like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotLike(String value) {
            addCriterion("start_time not like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<String> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<String> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(String value1, String value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(String value1, String value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("end_time like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("end_time not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesIsNull() {
            addCriterion("duration_minutes is null");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesIsNotNull() {
            addCriterion("duration_minutes is not null");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesEqualTo(Integer value) {
            addCriterion("duration_minutes =", value, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesNotEqualTo(Integer value) {
            addCriterion("duration_minutes <>", value, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesGreaterThan(Integer value) {
            addCriterion("duration_minutes >", value, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesGreaterThanOrEqualTo(Integer value) {
            addCriterion("duration_minutes >=", value, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesLessThan(Integer value) {
            addCriterion("duration_minutes <", value, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesLessThanOrEqualTo(Integer value) {
            addCriterion("duration_minutes <=", value, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesIn(List<Integer> values) {
            addCriterion("duration_minutes in", values, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesNotIn(List<Integer> values) {
            addCriterion("duration_minutes not in", values, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesBetween(Integer value1, Integer value2) {
            addCriterion("duration_minutes between", value1, value2, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andDurationMinutesNotBetween(Integer value1, Integer value2) {
            addCriterion("duration_minutes not between", value1, value2, "durationMinutes");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeIsNull() {
            addCriterion("repeat_type is null");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeIsNotNull() {
            addCriterion("repeat_type is not null");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeEqualTo(String value) {
            addCriterion("repeat_type =", value, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeNotEqualTo(String value) {
            addCriterion("repeat_type <>", value, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeGreaterThan(String value) {
            addCriterion("repeat_type >", value, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeGreaterThanOrEqualTo(String value) {
            addCriterion("repeat_type >=", value, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeLessThan(String value) {
            addCriterion("repeat_type <", value, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeLessThanOrEqualTo(String value) {
            addCriterion("repeat_type <=", value, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeLike(String value) {
            addCriterion("repeat_type like", value, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeNotLike(String value) {
            addCriterion("repeat_type not like", value, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeIn(List<String> values) {
            addCriterion("repeat_type in", values, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeNotIn(List<String> values) {
            addCriterion("repeat_type not in", values, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeBetween(String value1, String value2) {
            addCriterion("repeat_type between", value1, value2, "repeatType");
            return (Criteria) this;
        }

        public Criteria andRepeatTypeNotBetween(String value1, String value2) {
            addCriterion("repeat_type not between", value1, value2, "repeatType");
            return (Criteria) this;
        }

        public Criteria andMondayIsNull() {
            addCriterion("monday is null");
            return (Criteria) this;
        }

        public Criteria andMondayIsNotNull() {
            addCriterion("monday is not null");
            return (Criteria) this;
        }

        public Criteria andMondayEqualTo(Integer value) {
            addCriterion("monday =", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayNotEqualTo(Integer value) {
            addCriterion("monday <>", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayGreaterThan(Integer value) {
            addCriterion("monday >", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayGreaterThanOrEqualTo(Integer value) {
            addCriterion("monday >=", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayLessThan(Integer value) {
            addCriterion("monday <", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayLessThanOrEqualTo(Integer value) {
            addCriterion("monday <=", value, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayIn(List<Integer> values) {
            addCriterion("monday in", values, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayNotIn(List<Integer> values) {
            addCriterion("monday not in", values, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayBetween(Integer value1, Integer value2) {
            addCriterion("monday between", value1, value2, "monday");
            return (Criteria) this;
        }

        public Criteria andMondayNotBetween(Integer value1, Integer value2) {
            addCriterion("monday not between", value1, value2, "monday");
            return (Criteria) this;
        }

        public Criteria andTuesdayIsNull() {
            addCriterion("tuesday is null");
            return (Criteria) this;
        }

        public Criteria andTuesdayIsNotNull() {
            addCriterion("tuesday is not null");
            return (Criteria) this;
        }

        public Criteria andTuesdayEqualTo(Integer value) {
            addCriterion("tuesday =", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayNotEqualTo(Integer value) {
            addCriterion("tuesday <>", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayGreaterThan(Integer value) {
            addCriterion("tuesday >", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("tuesday >=", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayLessThan(Integer value) {
            addCriterion("tuesday <", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayLessThanOrEqualTo(Integer value) {
            addCriterion("tuesday <=", value, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayIn(List<Integer> values) {
            addCriterion("tuesday in", values, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayNotIn(List<Integer> values) {
            addCriterion("tuesday not in", values, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayBetween(Integer value1, Integer value2) {
            addCriterion("tuesday between", value1, value2, "tuesday");
            return (Criteria) this;
        }

        public Criteria andTuesdayNotBetween(Integer value1, Integer value2) {
            addCriterion("tuesday not between", value1, value2, "tuesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayIsNull() {
            addCriterion("wednesday is null");
            return (Criteria) this;
        }

        public Criteria andWednesdayIsNotNull() {
            addCriterion("wednesday is not null");
            return (Criteria) this;
        }

        public Criteria andWednesdayEqualTo(Integer value) {
            addCriterion("wednesday =", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayNotEqualTo(Integer value) {
            addCriterion("wednesday <>", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayGreaterThan(Integer value) {
            addCriterion("wednesday >", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("wednesday >=", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayLessThan(Integer value) {
            addCriterion("wednesday <", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayLessThanOrEqualTo(Integer value) {
            addCriterion("wednesday <=", value, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayIn(List<Integer> values) {
            addCriterion("wednesday in", values, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayNotIn(List<Integer> values) {
            addCriterion("wednesday not in", values, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayBetween(Integer value1, Integer value2) {
            addCriterion("wednesday between", value1, value2, "wednesday");
            return (Criteria) this;
        }

        public Criteria andWednesdayNotBetween(Integer value1, Integer value2) {
            addCriterion("wednesday not between", value1, value2, "wednesday");
            return (Criteria) this;
        }

        public Criteria andThursdayIsNull() {
            addCriterion("thursday is null");
            return (Criteria) this;
        }

        public Criteria andThursdayIsNotNull() {
            addCriterion("thursday is not null");
            return (Criteria) this;
        }

        public Criteria andThursdayEqualTo(Integer value) {
            addCriterion("thursday =", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayNotEqualTo(Integer value) {
            addCriterion("thursday <>", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayGreaterThan(Integer value) {
            addCriterion("thursday >", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("thursday >=", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayLessThan(Integer value) {
            addCriterion("thursday <", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayLessThanOrEqualTo(Integer value) {
            addCriterion("thursday <=", value, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayIn(List<Integer> values) {
            addCriterion("thursday in", values, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayNotIn(List<Integer> values) {
            addCriterion("thursday not in", values, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayBetween(Integer value1, Integer value2) {
            addCriterion("thursday between", value1, value2, "thursday");
            return (Criteria) this;
        }

        public Criteria andThursdayNotBetween(Integer value1, Integer value2) {
            addCriterion("thursday not between", value1, value2, "thursday");
            return (Criteria) this;
        }

        public Criteria andFridayIsNull() {
            addCriterion("friday is null");
            return (Criteria) this;
        }

        public Criteria andFridayIsNotNull() {
            addCriterion("friday is not null");
            return (Criteria) this;
        }

        public Criteria andFridayEqualTo(Integer value) {
            addCriterion("friday =", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayNotEqualTo(Integer value) {
            addCriterion("friday <>", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayGreaterThan(Integer value) {
            addCriterion("friday >", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayGreaterThanOrEqualTo(Integer value) {
            addCriterion("friday >=", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayLessThan(Integer value) {
            addCriterion("friday <", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayLessThanOrEqualTo(Integer value) {
            addCriterion("friday <=", value, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayIn(List<Integer> values) {
            addCriterion("friday in", values, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayNotIn(List<Integer> values) {
            addCriterion("friday not in", values, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayBetween(Integer value1, Integer value2) {
            addCriterion("friday between", value1, value2, "friday");
            return (Criteria) this;
        }

        public Criteria andFridayNotBetween(Integer value1, Integer value2) {
            addCriterion("friday not between", value1, value2, "friday");
            return (Criteria) this;
        }

        public Criteria andSaturdayIsNull() {
            addCriterion("saturday is null");
            return (Criteria) this;
        }

        public Criteria andSaturdayIsNotNull() {
            addCriterion("saturday is not null");
            return (Criteria) this;
        }

        public Criteria andSaturdayEqualTo(Integer value) {
            addCriterion("saturday =", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayNotEqualTo(Integer value) {
            addCriterion("saturday <>", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayGreaterThan(Integer value) {
            addCriterion("saturday >", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("saturday >=", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayLessThan(Integer value) {
            addCriterion("saturday <", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayLessThanOrEqualTo(Integer value) {
            addCriterion("saturday <=", value, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayIn(List<Integer> values) {
            addCriterion("saturday in", values, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayNotIn(List<Integer> values) {
            addCriterion("saturday not in", values, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayBetween(Integer value1, Integer value2) {
            addCriterion("saturday between", value1, value2, "saturday");
            return (Criteria) this;
        }

        public Criteria andSaturdayNotBetween(Integer value1, Integer value2) {
            addCriterion("saturday not between", value1, value2, "saturday");
            return (Criteria) this;
        }

        public Criteria andSundayIsNull() {
            addCriterion("sunday is null");
            return (Criteria) this;
        }

        public Criteria andSundayIsNotNull() {
            addCriterion("sunday is not null");
            return (Criteria) this;
        }

        public Criteria andSundayEqualTo(Integer value) {
            addCriterion("sunday =", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayNotEqualTo(Integer value) {
            addCriterion("sunday <>", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayGreaterThan(Integer value) {
            addCriterion("sunday >", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayGreaterThanOrEqualTo(Integer value) {
            addCriterion("sunday >=", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayLessThan(Integer value) {
            addCriterion("sunday <", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayLessThanOrEqualTo(Integer value) {
            addCriterion("sunday <=", value, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayIn(List<Integer> values) {
            addCriterion("sunday in", values, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayNotIn(List<Integer> values) {
            addCriterion("sunday not in", values, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayBetween(Integer value1, Integer value2) {
            addCriterion("sunday between", value1, value2, "sunday");
            return (Criteria) this;
        }

        public Criteria andSundayNotBetween(Integer value1, Integer value2) {
            addCriterion("sunday not between", value1, value2, "sunday");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNull() {
            addCriterion("is_active is null");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNotNull() {
            addCriterion("is_active is not null");
            return (Criteria) this;
        }

        public Criteria andIsActiveEqualTo(Integer value) {
            addCriterion("is_active =", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotEqualTo(Integer value) {
            addCriterion("is_active <>", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThan(Integer value) {
            addCriterion("is_active >", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_active >=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThan(Integer value) {
            addCriterion("is_active <", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThanOrEqualTo(Integer value) {
            addCriterion("is_active <=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveIn(List<Integer> values) {
            addCriterion("is_active in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotIn(List<Integer> values) {
            addCriterion("is_active not in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveBetween(Integer value1, Integer value2) {
            addCriterion("is_active between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotBetween(Integer value1, Integer value2) {
            addCriterion("is_active not between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsRunningIsNull() {
            addCriterion("is_running is null");
            return (Criteria) this;
        }

        public Criteria andIsRunningIsNotNull() {
            addCriterion("is_running is not null");
            return (Criteria) this;
        }

        public Criteria andIsRunningEqualTo(Integer value) {
            addCriterion("is_running =", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningNotEqualTo(Integer value) {
            addCriterion("is_running <>", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningGreaterThan(Integer value) {
            addCriterion("is_running >", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_running >=", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningLessThan(Integer value) {
            addCriterion("is_running <", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningLessThanOrEqualTo(Integer value) {
            addCriterion("is_running <=", value, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningIn(List<Integer> values) {
            addCriterion("is_running in", values, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningNotIn(List<Integer> values) {
            addCriterion("is_running not in", values, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningBetween(Integer value1, Integer value2) {
            addCriterion("is_running between", value1, value2, "isRunning");
            return (Criteria) this;
        }

        public Criteria andIsRunningNotBetween(Integer value1, Integer value2) {
            addCriterion("is_running not between", value1, value2, "isRunning");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeIsNull() {
            addCriterion("last_run_time is null");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeIsNotNull() {
            addCriterion("last_run_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeEqualTo(Date value) {
            addCriterion("last_run_time =", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeNotEqualTo(Date value) {
            addCriterion("last_run_time <>", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeGreaterThan(Date value) {
            addCriterion("last_run_time >", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_run_time >=", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeLessThan(Date value) {
            addCriterion("last_run_time <", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_run_time <=", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeIn(List<Date> values) {
            addCriterion("last_run_time in", values, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeNotIn(List<Date> values) {
            addCriterion("last_run_time not in", values, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeBetween(Date value1, Date value2) {
            addCriterion("last_run_time between", value1, value2, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_run_time not between", value1, value2, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeIsNull() {
            addCriterion("next_run_time is null");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeIsNotNull() {
            addCriterion("next_run_time is not null");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeEqualTo(Date value) {
            addCriterion("next_run_time =", value, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeNotEqualTo(Date value) {
            addCriterion("next_run_time <>", value, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeGreaterThan(Date value) {
            addCriterion("next_run_time >", value, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("next_run_time >=", value, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeLessThan(Date value) {
            addCriterion("next_run_time <", value, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeLessThanOrEqualTo(Date value) {
            addCriterion("next_run_time <=", value, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeIn(List<Date> values) {
            addCriterion("next_run_time in", values, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeNotIn(List<Date> values) {
            addCriterion("next_run_time not in", values, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeBetween(Date value1, Date value2) {
            addCriterion("next_run_time between", value1, value2, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andNextRunTimeNotBetween(Date value1, Date value2) {
            addCriterion("next_run_time not between", value1, value2, "nextRunTime");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterionForJDBCDate("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterionForJDBCDate("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterionForJDBCDate("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterionForJDBCDate("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterionForJDBCDate("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterionForJDBCDate("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("created_at not between", value1, value2, "createdAt");
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