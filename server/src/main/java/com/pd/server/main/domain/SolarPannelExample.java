package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolarPannelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SolarPannelExample() {
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

        public Criteria andDelIsNull() {
            addCriterion("del is null");
            return (Criteria) this;
        }

        public Criteria andDelIsNotNull() {
            addCriterion("del is not null");
            return (Criteria) this;
        }

        public Criteria andDelEqualTo(String value) {
            addCriterion("del =", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotEqualTo(String value) {
            addCriterion("del <>", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThan(String value) {
            addCriterion("del >", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThanOrEqualTo(String value) {
            addCriterion("del >=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThan(String value) {
            addCriterion("del <", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThanOrEqualTo(String value) {
            addCriterion("del <=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLike(String value) {
            addCriterion("del like", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotLike(String value) {
            addCriterion("del not like", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelIn(List<String> values) {
            addCriterion("del in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotIn(List<String> values) {
            addCriterion("del not in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelBetween(String value1, String value2) {
            addCriterion("del between", value1, value2, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotBetween(String value1, String value2) {
            addCriterion("del not between", value1, value2, "del");
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

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(String value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(String value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(String value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(String value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(String value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLike(String value) {
            addCriterion("channel_id like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotLike(String value) {
            addCriterion("channel_id not like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<String> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<String> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(String value1, String value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(String value1, String value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberIsNull() {
            addCriterion("device_number is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberIsNotNull() {
            addCriterion("device_number is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberEqualTo(String value) {
            addCriterion("device_number =", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberNotEqualTo(String value) {
            addCriterion("device_number <>", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberGreaterThan(String value) {
            addCriterion("device_number >", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberGreaterThanOrEqualTo(String value) {
            addCriterion("device_number >=", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberLessThan(String value) {
            addCriterion("device_number <", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberLessThanOrEqualTo(String value) {
            addCriterion("device_number <=", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberLike(String value) {
            addCriterion("device_number like", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberNotLike(String value) {
            addCriterion("device_number not like", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberIn(List<String> values) {
            addCriterion("device_number in", values, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberNotIn(List<String> values) {
            addCriterion("device_number not in", values, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberBetween(String value1, String value2) {
            addCriterion("device_number between", value1, value2, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberNotBetween(String value1, String value2) {
            addCriterion("device_number not between", value1, value2, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNull() {
            addCriterion("device_name is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("device_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("device_name =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("device_name <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("device_name >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("device_name >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("device_name <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("device_name <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("device_name like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("device_name not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("device_name in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("device_name not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("device_name between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("device_name not between", value1, value2, "deviceName");
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

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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

        public Criteria andHeartbeatTimeIsNull() {
            addCriterion("heartbeat_time is null");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeIsNotNull() {
            addCriterion("heartbeat_time is not null");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeEqualTo(Date value) {
            addCriterion("heartbeat_time =", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeNotEqualTo(Date value) {
            addCriterion("heartbeat_time <>", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeGreaterThan(Date value) {
            addCriterion("heartbeat_time >", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("heartbeat_time >=", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeLessThan(Date value) {
            addCriterion("heartbeat_time <", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeLessThanOrEqualTo(Date value) {
            addCriterion("heartbeat_time <=", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeIn(List<Date> values) {
            addCriterion("heartbeat_time in", values, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeNotIn(List<Date> values) {
            addCriterion("heartbeat_time not in", values, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeBetween(Date value1, Date value2) {
            addCriterion("heartbeat_time between", value1, value2, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeNotBetween(Date value1, Date value2) {
            addCriterion("heartbeat_time not between", value1, value2, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andOnlineIsNull() {
            addCriterion("`online` is null");
            return (Criteria) this;
        }

        public Criteria andOnlineIsNotNull() {
            addCriterion("`online` is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineEqualTo(Integer value) {
            addCriterion("`online` =", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineNotEqualTo(Integer value) {
            addCriterion("`online` <>", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineGreaterThan(Integer value) {
            addCriterion("`online` >", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineGreaterThanOrEqualTo(Integer value) {
            addCriterion("`online` >=", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineLessThan(Integer value) {
            addCriterion("`online` <", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineLessThanOrEqualTo(Integer value) {
            addCriterion("`online` <=", value, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineIn(List<Integer> values) {
            addCriterion("`online` in", values, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineNotIn(List<Integer> values) {
            addCriterion("`online` not in", values, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineBetween(Integer value1, Integer value2) {
            addCriterion("`online` between", value1, value2, "online");
            return (Criteria) this;
        }

        public Criteria andOnlineNotBetween(Integer value1, Integer value2) {
            addCriterion("`online` not between", value1, value2, "online");
            return (Criteria) this;
        }

        public Criteria andHandSwitchIsNull() {
            addCriterion("hand_switch is null");
            return (Criteria) this;
        }

        public Criteria andHandSwitchIsNotNull() {
            addCriterion("hand_switch is not null");
            return (Criteria) this;
        }

        public Criteria andHandSwitchEqualTo(Integer value) {
            addCriterion("hand_switch =", value, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andHandSwitchNotEqualTo(Integer value) {
            addCriterion("hand_switch <>", value, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andHandSwitchGreaterThan(Integer value) {
            addCriterion("hand_switch >", value, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andHandSwitchGreaterThanOrEqualTo(Integer value) {
            addCriterion("hand_switch >=", value, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andHandSwitchLessThan(Integer value) {
            addCriterion("hand_switch <", value, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andHandSwitchLessThanOrEqualTo(Integer value) {
            addCriterion("hand_switch <=", value, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andHandSwitchIn(List<Integer> values) {
            addCriterion("hand_switch in", values, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andHandSwitchNotIn(List<Integer> values) {
            addCriterion("hand_switch not in", values, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andHandSwitchBetween(Integer value1, Integer value2) {
            addCriterion("hand_switch between", value1, value2, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andHandSwitchNotBetween(Integer value1, Integer value2) {
            addCriterion("hand_switch not between", value1, value2, "handSwitch");
            return (Criteria) this;
        }

        public Criteria andAutoModeIsNull() {
            addCriterion("auto_mode is null");
            return (Criteria) this;
        }

        public Criteria andAutoModeIsNotNull() {
            addCriterion("auto_mode is not null");
            return (Criteria) this;
        }

        public Criteria andAutoModeEqualTo(Integer value) {
            addCriterion("auto_mode =", value, "autoMode");
            return (Criteria) this;
        }

        public Criteria andAutoModeNotEqualTo(Integer value) {
            addCriterion("auto_mode <>", value, "autoMode");
            return (Criteria) this;
        }

        public Criteria andAutoModeGreaterThan(Integer value) {
            addCriterion("auto_mode >", value, "autoMode");
            return (Criteria) this;
        }

        public Criteria andAutoModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_mode >=", value, "autoMode");
            return (Criteria) this;
        }

        public Criteria andAutoModeLessThan(Integer value) {
            addCriterion("auto_mode <", value, "autoMode");
            return (Criteria) this;
        }

        public Criteria andAutoModeLessThanOrEqualTo(Integer value) {
            addCriterion("auto_mode <=", value, "autoMode");
            return (Criteria) this;
        }

        public Criteria andAutoModeIn(List<Integer> values) {
            addCriterion("auto_mode in", values, "autoMode");
            return (Criteria) this;
        }

        public Criteria andAutoModeNotIn(List<Integer> values) {
            addCriterion("auto_mode not in", values, "autoMode");
            return (Criteria) this;
        }

        public Criteria andAutoModeBetween(Integer value1, Integer value2) {
            addCriterion("auto_mode between", value1, value2, "autoMode");
            return (Criteria) this;
        }

        public Criteria andAutoModeNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_mode not between", value1, value2, "autoMode");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNull() {
            addCriterion("temperature is null");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNotNull() {
            addCriterion("temperature is not null");
            return (Criteria) this;
        }

        public Criteria andTemperatureEqualTo(String value) {
            addCriterion("temperature =", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotEqualTo(String value) {
            addCriterion("temperature <>", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThan(String value) {
            addCriterion("temperature >", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("temperature >=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThan(String value) {
            addCriterion("temperature <", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThanOrEqualTo(String value) {
            addCriterion("temperature <=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLike(String value) {
            addCriterion("temperature like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotLike(String value) {
            addCriterion("temperature not like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureIn(List<String> values) {
            addCriterion("temperature in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotIn(List<String> values) {
            addCriterion("temperature not in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureBetween(String value1, String value2) {
            addCriterion("temperature between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotBetween(String value1, String value2) {
            addCriterion("temperature not between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageIsNull() {
            addCriterion("battery_voltage is null");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageIsNotNull() {
            addCriterion("battery_voltage is not null");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageEqualTo(String value) {
            addCriterion("battery_voltage =", value, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageNotEqualTo(String value) {
            addCriterion("battery_voltage <>", value, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageGreaterThan(String value) {
            addCriterion("battery_voltage >", value, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageGreaterThanOrEqualTo(String value) {
            addCriterion("battery_voltage >=", value, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageLessThan(String value) {
            addCriterion("battery_voltage <", value, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageLessThanOrEqualTo(String value) {
            addCriterion("battery_voltage <=", value, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageLike(String value) {
            addCriterion("battery_voltage like", value, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageNotLike(String value) {
            addCriterion("battery_voltage not like", value, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageIn(List<String> values) {
            addCriterion("battery_voltage in", values, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageNotIn(List<String> values) {
            addCriterion("battery_voltage not in", values, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageBetween(String value1, String value2) {
            addCriterion("battery_voltage between", value1, value2, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryVoltageNotBetween(String value1, String value2) {
            addCriterion("battery_voltage not between", value1, value2, "batteryVoltage");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionIsNull() {
            addCriterion("daily_electricity_consumption is null");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionIsNotNull() {
            addCriterion("daily_electricity_consumption is not null");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionEqualTo(String value) {
            addCriterion("daily_electricity_consumption =", value, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionNotEqualTo(String value) {
            addCriterion("daily_electricity_consumption <>", value, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionGreaterThan(String value) {
            addCriterion("daily_electricity_consumption >", value, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionGreaterThanOrEqualTo(String value) {
            addCriterion("daily_electricity_consumption >=", value, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionLessThan(String value) {
            addCriterion("daily_electricity_consumption <", value, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionLessThanOrEqualTo(String value) {
            addCriterion("daily_electricity_consumption <=", value, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionLike(String value) {
            addCriterion("daily_electricity_consumption like", value, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionNotLike(String value) {
            addCriterion("daily_electricity_consumption not like", value, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionIn(List<String> values) {
            addCriterion("daily_electricity_consumption in", values, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionNotIn(List<String> values) {
            addCriterion("daily_electricity_consumption not in", values, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionBetween(String value1, String value2) {
            addCriterion("daily_electricity_consumption between", value1, value2, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyElectricityConsumptionNotBetween(String value1, String value2) {
            addCriterion("daily_electricity_consumption not between", value1, value2, "dailyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionIsNull() {
            addCriterion("monthly_electricity_consumption is null");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionIsNotNull() {
            addCriterion("monthly_electricity_consumption is not null");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionEqualTo(String value) {
            addCriterion("monthly_electricity_consumption =", value, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionNotEqualTo(String value) {
            addCriterion("monthly_electricity_consumption <>", value, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionGreaterThan(String value) {
            addCriterion("monthly_electricity_consumption >", value, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionGreaterThanOrEqualTo(String value) {
            addCriterion("monthly_electricity_consumption >=", value, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionLessThan(String value) {
            addCriterion("monthly_electricity_consumption <", value, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionLessThanOrEqualTo(String value) {
            addCriterion("monthly_electricity_consumption <=", value, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionLike(String value) {
            addCriterion("monthly_electricity_consumption like", value, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionNotLike(String value) {
            addCriterion("monthly_electricity_consumption not like", value, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionIn(List<String> values) {
            addCriterion("monthly_electricity_consumption in", values, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionNotIn(List<String> values) {
            addCriterion("monthly_electricity_consumption not in", values, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionBetween(String value1, String value2) {
            addCriterion("monthly_electricity_consumption between", value1, value2, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andMonthlyElectricityConsumptionNotBetween(String value1, String value2) {
            addCriterion("monthly_electricity_consumption not between", value1, value2, "monthlyElectricityConsumption");
            return (Criteria) this;
        }

        public Criteria andDailyChargeIsNull() {
            addCriterion("daily_charge is null");
            return (Criteria) this;
        }

        public Criteria andDailyChargeIsNotNull() {
            addCriterion("daily_charge is not null");
            return (Criteria) this;
        }

        public Criteria andDailyChargeEqualTo(String value) {
            addCriterion("daily_charge =", value, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeNotEqualTo(String value) {
            addCriterion("daily_charge <>", value, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeGreaterThan(String value) {
            addCriterion("daily_charge >", value, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeGreaterThanOrEqualTo(String value) {
            addCriterion("daily_charge >=", value, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeLessThan(String value) {
            addCriterion("daily_charge <", value, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeLessThanOrEqualTo(String value) {
            addCriterion("daily_charge <=", value, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeLike(String value) {
            addCriterion("daily_charge like", value, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeNotLike(String value) {
            addCriterion("daily_charge not like", value, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeIn(List<String> values) {
            addCriterion("daily_charge in", values, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeNotIn(List<String> values) {
            addCriterion("daily_charge not in", values, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeBetween(String value1, String value2) {
            addCriterion("daily_charge between", value1, value2, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andDailyChargeNotBetween(String value1, String value2) {
            addCriterion("daily_charge not between", value1, value2, "dailyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeIsNull() {
            addCriterion("monthly_charge is null");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeIsNotNull() {
            addCriterion("monthly_charge is not null");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeEqualTo(String value) {
            addCriterion("monthly_charge =", value, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeNotEqualTo(String value) {
            addCriterion("monthly_charge <>", value, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeGreaterThan(String value) {
            addCriterion("monthly_charge >", value, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeGreaterThanOrEqualTo(String value) {
            addCriterion("monthly_charge >=", value, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeLessThan(String value) {
            addCriterion("monthly_charge <", value, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeLessThanOrEqualTo(String value) {
            addCriterion("monthly_charge <=", value, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeLike(String value) {
            addCriterion("monthly_charge like", value, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeNotLike(String value) {
            addCriterion("monthly_charge not like", value, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeIn(List<String> values) {
            addCriterion("monthly_charge in", values, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeNotIn(List<String> values) {
            addCriterion("monthly_charge not in", values, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeBetween(String value1, String value2) {
            addCriterion("monthly_charge between", value1, value2, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andMonthlyChargeNotBetween(String value1, String value2) {
            addCriterion("monthly_charge not between", value1, value2, "monthlyCharge");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentIsNull() {
            addCriterion("battery_current is null");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentIsNotNull() {
            addCriterion("battery_current is not null");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentEqualTo(String value) {
            addCriterion("battery_current =", value, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentNotEqualTo(String value) {
            addCriterion("battery_current <>", value, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentGreaterThan(String value) {
            addCriterion("battery_current >", value, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentGreaterThanOrEqualTo(String value) {
            addCriterion("battery_current >=", value, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentLessThan(String value) {
            addCriterion("battery_current <", value, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentLessThanOrEqualTo(String value) {
            addCriterion("battery_current <=", value, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentLike(String value) {
            addCriterion("battery_current like", value, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentNotLike(String value) {
            addCriterion("battery_current not like", value, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentIn(List<String> values) {
            addCriterion("battery_current in", values, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentNotIn(List<String> values) {
            addCriterion("battery_current not in", values, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentBetween(String value1, String value2) {
            addCriterion("battery_current between", value1, value2, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryCurrentNotBetween(String value1, String value2) {
            addCriterion("battery_current not between", value1, value2, "batteryCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageIsNull() {
            addCriterion("solar_panel_voltage is null");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageIsNotNull() {
            addCriterion("solar_panel_voltage is not null");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageEqualTo(String value) {
            addCriterion("solar_panel_voltage =", value, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageNotEqualTo(String value) {
            addCriterion("solar_panel_voltage <>", value, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageGreaterThan(String value) {
            addCriterion("solar_panel_voltage >", value, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageGreaterThanOrEqualTo(String value) {
            addCriterion("solar_panel_voltage >=", value, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageLessThan(String value) {
            addCriterion("solar_panel_voltage <", value, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageLessThanOrEqualTo(String value) {
            addCriterion("solar_panel_voltage <=", value, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageLike(String value) {
            addCriterion("solar_panel_voltage like", value, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageNotLike(String value) {
            addCriterion("solar_panel_voltage not like", value, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageIn(List<String> values) {
            addCriterion("solar_panel_voltage in", values, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageNotIn(List<String> values) {
            addCriterion("solar_panel_voltage not in", values, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageBetween(String value1, String value2) {
            addCriterion("solar_panel_voltage between", value1, value2, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPanelVoltageNotBetween(String value1, String value2) {
            addCriterion("solar_panel_voltage not between", value1, value2, "solarPanelVoltage");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentIsNull() {
            addCriterion("solar_pannel_current is null");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentIsNotNull() {
            addCriterion("solar_pannel_current is not null");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentEqualTo(String value) {
            addCriterion("solar_pannel_current =", value, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentNotEqualTo(String value) {
            addCriterion("solar_pannel_current <>", value, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentGreaterThan(String value) {
            addCriterion("solar_pannel_current >", value, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentGreaterThanOrEqualTo(String value) {
            addCriterion("solar_pannel_current >=", value, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentLessThan(String value) {
            addCriterion("solar_pannel_current <", value, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentLessThanOrEqualTo(String value) {
            addCriterion("solar_pannel_current <=", value, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentLike(String value) {
            addCriterion("solar_pannel_current like", value, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentNotLike(String value) {
            addCriterion("solar_pannel_current not like", value, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentIn(List<String> values) {
            addCriterion("solar_pannel_current in", values, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentNotIn(List<String> values) {
            addCriterion("solar_pannel_current not in", values, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentBetween(String value1, String value2) {
            addCriterion("solar_pannel_current between", value1, value2, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andSolarPannelCurrentNotBetween(String value1, String value2) {
            addCriterion("solar_pannel_current not between", value1, value2, "solarPannelCurrent");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationIsNull() {
            addCriterion("power_generation is null");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationIsNotNull() {
            addCriterion("power_generation is not null");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationEqualTo(String value) {
            addCriterion("power_generation =", value, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationNotEqualTo(String value) {
            addCriterion("power_generation <>", value, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationGreaterThan(String value) {
            addCriterion("power_generation >", value, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationGreaterThanOrEqualTo(String value) {
            addCriterion("power_generation >=", value, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationLessThan(String value) {
            addCriterion("power_generation <", value, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationLessThanOrEqualTo(String value) {
            addCriterion("power_generation <=", value, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationLike(String value) {
            addCriterion("power_generation like", value, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationNotLike(String value) {
            addCriterion("power_generation not like", value, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationIn(List<String> values) {
            addCriterion("power_generation in", values, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationNotIn(List<String> values) {
            addCriterion("power_generation not in", values, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationBetween(String value1, String value2) {
            addCriterion("power_generation between", value1, value2, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andPowerGenerationNotBetween(String value1, String value2) {
            addCriterion("power_generation not between", value1, value2, "powerGeneration");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageIsNull() {
            addCriterion("load_voltage is null");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageIsNotNull() {
            addCriterion("load_voltage is not null");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageEqualTo(String value) {
            addCriterion("load_voltage =", value, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageNotEqualTo(String value) {
            addCriterion("load_voltage <>", value, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageGreaterThan(String value) {
            addCriterion("load_voltage >", value, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageGreaterThanOrEqualTo(String value) {
            addCriterion("load_voltage >=", value, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageLessThan(String value) {
            addCriterion("load_voltage <", value, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageLessThanOrEqualTo(String value) {
            addCriterion("load_voltage <=", value, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageLike(String value) {
            addCriterion("load_voltage like", value, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageNotLike(String value) {
            addCriterion("load_voltage not like", value, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageIn(List<String> values) {
            addCriterion("load_voltage in", values, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageNotIn(List<String> values) {
            addCriterion("load_voltage not in", values, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageBetween(String value1, String value2) {
            addCriterion("load_voltage between", value1, value2, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadVoltageNotBetween(String value1, String value2) {
            addCriterion("load_voltage not between", value1, value2, "loadVoltage");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentIsNull() {
            addCriterion("load_current is null");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentIsNotNull() {
            addCriterion("load_current is not null");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentEqualTo(String value) {
            addCriterion("load_current =", value, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentNotEqualTo(String value) {
            addCriterion("load_current <>", value, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentGreaterThan(String value) {
            addCriterion("load_current >", value, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentGreaterThanOrEqualTo(String value) {
            addCriterion("load_current >=", value, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentLessThan(String value) {
            addCriterion("load_current <", value, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentLessThanOrEqualTo(String value) {
            addCriterion("load_current <=", value, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentLike(String value) {
            addCriterion("load_current like", value, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentNotLike(String value) {
            addCriterion("load_current not like", value, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentIn(List<String> values) {
            addCriterion("load_current in", values, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentNotIn(List<String> values) {
            addCriterion("load_current not in", values, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentBetween(String value1, String value2) {
            addCriterion("load_current between", value1, value2, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andLoadCurrentNotBetween(String value1, String value2) {
            addCriterion("load_current not between", value1, value2, "loadCurrent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentIsNull() {
            addCriterion("battery_percent is null");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentIsNotNull() {
            addCriterion("battery_percent is not null");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentEqualTo(String value) {
            addCriterion("battery_percent =", value, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentNotEqualTo(String value) {
            addCriterion("battery_percent <>", value, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentGreaterThan(String value) {
            addCriterion("battery_percent >", value, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentGreaterThanOrEqualTo(String value) {
            addCriterion("battery_percent >=", value, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentLessThan(String value) {
            addCriterion("battery_percent <", value, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentLessThanOrEqualTo(String value) {
            addCriterion("battery_percent <=", value, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentLike(String value) {
            addCriterion("battery_percent like", value, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentNotLike(String value) {
            addCriterion("battery_percent not like", value, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentIn(List<String> values) {
            addCriterion("battery_percent in", values, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentNotIn(List<String> values) {
            addCriterion("battery_percent not in", values, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentBetween(String value1, String value2) {
            addCriterion("battery_percent between", value1, value2, "batteryPercent");
            return (Criteria) this;
        }

        public Criteria andBatteryPercentNotBetween(String value1, String value2) {
            addCriterion("battery_percent not between", value1, value2, "batteryPercent");
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

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andErrmsgIsNull() {
            addCriterion("errMsg is null");
            return (Criteria) this;
        }

        public Criteria andErrmsgIsNotNull() {
            addCriterion("errMsg is not null");
            return (Criteria) this;
        }

        public Criteria andErrmsgEqualTo(String value) {
            addCriterion("errMsg =", value, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgNotEqualTo(String value) {
            addCriterion("errMsg <>", value, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgGreaterThan(String value) {
            addCriterion("errMsg >", value, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgGreaterThanOrEqualTo(String value) {
            addCriterion("errMsg >=", value, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgLessThan(String value) {
            addCriterion("errMsg <", value, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgLessThanOrEqualTo(String value) {
            addCriterion("errMsg <=", value, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgLike(String value) {
            addCriterion("errMsg like", value, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgNotLike(String value) {
            addCriterion("errMsg not like", value, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgIn(List<String> values) {
            addCriterion("errMsg in", values, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgNotIn(List<String> values) {
            addCriterion("errMsg not in", values, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgBetween(String value1, String value2) {
            addCriterion("errMsg between", value1, value2, "errmsg");
            return (Criteria) this;
        }

        public Criteria andErrmsgNotBetween(String value1, String value2) {
            addCriterion("errMsg not between", value1, value2, "errmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgIsNull() {
            addCriterion("ledMsg is null");
            return (Criteria) this;
        }

        public Criteria andLedmsgIsNotNull() {
            addCriterion("ledMsg is not null");
            return (Criteria) this;
        }

        public Criteria andLedmsgEqualTo(String value) {
            addCriterion("ledMsg =", value, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgNotEqualTo(String value) {
            addCriterion("ledMsg <>", value, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgGreaterThan(String value) {
            addCriterion("ledMsg >", value, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgGreaterThanOrEqualTo(String value) {
            addCriterion("ledMsg >=", value, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgLessThan(String value) {
            addCriterion("ledMsg <", value, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgLessThanOrEqualTo(String value) {
            addCriterion("ledMsg <=", value, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgLike(String value) {
            addCriterion("ledMsg like", value, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgNotLike(String value) {
            addCriterion("ledMsg not like", value, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgIn(List<String> values) {
            addCriterion("ledMsg in", values, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgNotIn(List<String> values) {
            addCriterion("ledMsg not in", values, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgBetween(String value1, String value2) {
            addCriterion("ledMsg between", value1, value2, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andLedmsgNotBetween(String value1, String value2) {
            addCriterion("ledMsg not between", value1, value2, "ledmsg");
            return (Criteria) this;
        }

        public Criteria andCardinfoIsNull() {
            addCriterion("cardInfo is null");
            return (Criteria) this;
        }

        public Criteria andCardinfoIsNotNull() {
            addCriterion("cardInfo is not null");
            return (Criteria) this;
        }

        public Criteria andCardinfoEqualTo(Integer value) {
            addCriterion("cardInfo =", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotEqualTo(Integer value) {
            addCriterion("cardInfo <>", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoGreaterThan(Integer value) {
            addCriterion("cardInfo >", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoGreaterThanOrEqualTo(Integer value) {
            addCriterion("cardInfo >=", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoLessThan(Integer value) {
            addCriterion("cardInfo <", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoLessThanOrEqualTo(Integer value) {
            addCriterion("cardInfo <=", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoIn(List<Integer> values) {
            addCriterion("cardInfo in", values, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotIn(List<Integer> values) {
            addCriterion("cardInfo not in", values, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoBetween(Integer value1, Integer value2) {
            addCriterion("cardInfo between", value1, value2, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotBetween(Integer value1, Integer value2) {
            addCriterion("cardInfo not between", value1, value2, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardNumberIsNull() {
            addCriterion("card_number is null");
            return (Criteria) this;
        }

        public Criteria andCardNumberIsNotNull() {
            addCriterion("card_number is not null");
            return (Criteria) this;
        }

        public Criteria andCardNumberEqualTo(String value) {
            addCriterion("card_number =", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotEqualTo(String value) {
            addCriterion("card_number <>", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberGreaterThan(String value) {
            addCriterion("card_number >", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberGreaterThanOrEqualTo(String value) {
            addCriterion("card_number >=", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberLessThan(String value) {
            addCriterion("card_number <", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberLessThanOrEqualTo(String value) {
            addCriterion("card_number <=", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberLike(String value) {
            addCriterion("card_number like", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotLike(String value) {
            addCriterion("card_number not like", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberIn(List<String> values) {
            addCriterion("card_number in", values, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotIn(List<String> values) {
            addCriterion("card_number not in", values, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberBetween(String value1, String value2) {
            addCriterion("card_number between", value1, value2, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotBetween(String value1, String value2) {
            addCriterion("card_number not between", value1, value2, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andMinVoltageIsNull() {
            addCriterion("min_voltage is null");
            return (Criteria) this;
        }

        public Criteria andMinVoltageIsNotNull() {
            addCriterion("min_voltage is not null");
            return (Criteria) this;
        }

        public Criteria andMinVoltageEqualTo(Double value) {
            addCriterion("min_voltage =", value, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMinVoltageNotEqualTo(Double value) {
            addCriterion("min_voltage <>", value, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMinVoltageGreaterThan(Double value) {
            addCriterion("min_voltage >", value, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMinVoltageGreaterThanOrEqualTo(Double value) {
            addCriterion("min_voltage >=", value, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMinVoltageLessThan(Double value) {
            addCriterion("min_voltage <", value, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMinVoltageLessThanOrEqualTo(Double value) {
            addCriterion("min_voltage <=", value, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMinVoltageIn(List<Double> values) {
            addCriterion("min_voltage in", values, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMinVoltageNotIn(List<Double> values) {
            addCriterion("min_voltage not in", values, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMinVoltageBetween(Double value1, Double value2) {
            addCriterion("min_voltage between", value1, value2, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMinVoltageNotBetween(Double value1, Double value2) {
            addCriterion("min_voltage not between", value1, value2, "minVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageIsNull() {
            addCriterion("max_voltage is null");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageIsNotNull() {
            addCriterion("max_voltage is not null");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageEqualTo(Double value) {
            addCriterion("max_voltage =", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageNotEqualTo(Double value) {
            addCriterion("max_voltage <>", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageGreaterThan(Double value) {
            addCriterion("max_voltage >", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageGreaterThanOrEqualTo(Double value) {
            addCriterion("max_voltage >=", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageLessThan(Double value) {
            addCriterion("max_voltage <", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageLessThanOrEqualTo(Double value) {
            addCriterion("max_voltage <=", value, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageIn(List<Double> values) {
            addCriterion("max_voltage in", values, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageNotIn(List<Double> values) {
            addCriterion("max_voltage not in", values, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageBetween(Double value1, Double value2) {
            addCriterion("max_voltage between", value1, value2, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andMaxVoltageNotBetween(Double value1, Double value2) {
            addCriterion("max_voltage not between", value1, value2, "maxVoltage");
            return (Criteria) this;
        }

        public Criteria andCsqIsNull() {
            addCriterion("csq is null");
            return (Criteria) this;
        }

        public Criteria andCsqIsNotNull() {
            addCriterion("csq is not null");
            return (Criteria) this;
        }

        public Criteria andCsqEqualTo(String value) {
            addCriterion("csq =", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqNotEqualTo(String value) {
            addCriterion("csq <>", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqGreaterThan(String value) {
            addCriterion("csq >", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqGreaterThanOrEqualTo(String value) {
            addCriterion("csq >=", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqLessThan(String value) {
            addCriterion("csq <", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqLessThanOrEqualTo(String value) {
            addCriterion("csq <=", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqLike(String value) {
            addCriterion("csq like", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqNotLike(String value) {
            addCriterion("csq not like", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqIn(List<String> values) {
            addCriterion("csq in", values, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqNotIn(List<String> values) {
            addCriterion("csq not in", values, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqBetween(String value1, String value2) {
            addCriterion("csq between", value1, value2, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqNotBetween(String value1, String value2) {
            addCriterion("csq not between", value1, value2, "csq");
            return (Criteria) this;
        }

        public Criteria andTipcountIsNull() {
            addCriterion("tipCount is null");
            return (Criteria) this;
        }

        public Criteria andTipcountIsNotNull() {
            addCriterion("tipCount is not null");
            return (Criteria) this;
        }

        public Criteria andTipcountEqualTo(Integer value) {
            addCriterion("tipCount =", value, "tipcount");
            return (Criteria) this;
        }

        public Criteria andTipcountNotEqualTo(Integer value) {
            addCriterion("tipCount <>", value, "tipcount");
            return (Criteria) this;
        }

        public Criteria andTipcountGreaterThan(Integer value) {
            addCriterion("tipCount >", value, "tipcount");
            return (Criteria) this;
        }

        public Criteria andTipcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("tipCount >=", value, "tipcount");
            return (Criteria) this;
        }

        public Criteria andTipcountLessThan(Integer value) {
            addCriterion("tipCount <", value, "tipcount");
            return (Criteria) this;
        }

        public Criteria andTipcountLessThanOrEqualTo(Integer value) {
            addCriterion("tipCount <=", value, "tipcount");
            return (Criteria) this;
        }

        public Criteria andTipcountIn(List<Integer> values) {
            addCriterion("tipCount in", values, "tipcount");
            return (Criteria) this;
        }

        public Criteria andTipcountNotIn(List<Integer> values) {
            addCriterion("tipCount not in", values, "tipcount");
            return (Criteria) this;
        }

        public Criteria andTipcountBetween(Integer value1, Integer value2) {
            addCriterion("tipCount between", value1, value2, "tipcount");
            return (Criteria) this;
        }

        public Criteria andTipcountNotBetween(Integer value1, Integer value2) {
            addCriterion("tipCount not between", value1, value2, "tipcount");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectIsNull() {
            addCriterion("batteryCorrect is null");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectIsNotNull() {
            addCriterion("batteryCorrect is not null");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectEqualTo(String value) {
            addCriterion("batteryCorrect =", value, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectNotEqualTo(String value) {
            addCriterion("batteryCorrect <>", value, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectGreaterThan(String value) {
            addCriterion("batteryCorrect >", value, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectGreaterThanOrEqualTo(String value) {
            addCriterion("batteryCorrect >=", value, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectLessThan(String value) {
            addCriterion("batteryCorrect <", value, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectLessThanOrEqualTo(String value) {
            addCriterion("batteryCorrect <=", value, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectLike(String value) {
            addCriterion("batteryCorrect like", value, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectNotLike(String value) {
            addCriterion("batteryCorrect not like", value, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectIn(List<String> values) {
            addCriterion("batteryCorrect in", values, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectNotIn(List<String> values) {
            addCriterion("batteryCorrect not in", values, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectBetween(String value1, String value2) {
            addCriterion("batteryCorrect between", value1, value2, "batterycorrect");
            return (Criteria) this;
        }

        public Criteria andBatterycorrectNotBetween(String value1, String value2) {
            addCriterion("batteryCorrect not between", value1, value2, "batterycorrect");
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