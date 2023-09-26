package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrentMeterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CurrentMeterExample() {
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

        public Criteria andProductNumberIsNull() {
            addCriterion("product_number is null");
            return (Criteria) this;
        }

        public Criteria andProductNumberIsNotNull() {
            addCriterion("product_number is not null");
            return (Criteria) this;
        }

        public Criteria andProductNumberEqualTo(String value) {
            addCriterion("product_number =", value, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberNotEqualTo(String value) {
            addCriterion("product_number <>", value, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberGreaterThan(String value) {
            addCriterion("product_number >", value, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberGreaterThanOrEqualTo(String value) {
            addCriterion("product_number >=", value, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberLessThan(String value) {
            addCriterion("product_number <", value, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberLessThanOrEqualTo(String value) {
            addCriterion("product_number <=", value, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberLike(String value) {
            addCriterion("product_number like", value, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberNotLike(String value) {
            addCriterion("product_number not like", value, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberIn(List<String> values) {
            addCriterion("product_number in", values, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberNotIn(List<String> values) {
            addCriterion("product_number not in", values, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberBetween(String value1, String value2) {
            addCriterion("product_number between", value1, value2, "productNumber");
            return (Criteria) this;
        }

        public Criteria andProductNumberNotBetween(String value1, String value2) {
            addCriterion("product_number not between", value1, value2, "productNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNull() {
            addCriterion("serial_number is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNotNull() {
            addCriterion("serial_number is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberEqualTo(String value) {
            addCriterion("serial_number =", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotEqualTo(String value) {
            addCriterion("serial_number <>", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThan(String value) {
            addCriterion("serial_number >", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThanOrEqualTo(String value) {
            addCriterion("serial_number >=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThan(String value) {
            addCriterion("serial_number <", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThanOrEqualTo(String value) {
            addCriterion("serial_number <=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLike(String value) {
            addCriterion("serial_number like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotLike(String value) {
            addCriterion("serial_number not like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIn(List<String> values) {
            addCriterion("serial_number in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotIn(List<String> values) {
            addCriterion("serial_number not in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberBetween(String value1, String value2) {
            addCriterion("serial_number between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotBetween(String value1, String value2) {
            addCriterion("serial_number not between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedIsNull() {
            addCriterion("abs_speed is null");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedIsNotNull() {
            addCriterion("abs_speed is not null");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedEqualTo(String value) {
            addCriterion("abs_speed =", value, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedNotEqualTo(String value) {
            addCriterion("abs_speed <>", value, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedGreaterThan(String value) {
            addCriterion("abs_speed >", value, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedGreaterThanOrEqualTo(String value) {
            addCriterion("abs_speed >=", value, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedLessThan(String value) {
            addCriterion("abs_speed <", value, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedLessThanOrEqualTo(String value) {
            addCriterion("abs_speed <=", value, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedLike(String value) {
            addCriterion("abs_speed like", value, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedNotLike(String value) {
            addCriterion("abs_speed not like", value, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedIn(List<String> values) {
            addCriterion("abs_speed in", values, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedNotIn(List<String> values) {
            addCriterion("abs_speed not in", values, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedBetween(String value1, String value2) {
            addCriterion("abs_speed between", value1, value2, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andAbsSpeedNotBetween(String value1, String value2) {
            addCriterion("abs_speed not between", value1, value2, "absSpeed");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNull() {
            addCriterion("direction is null");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNotNull() {
            addCriterion("direction is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionEqualTo(String value) {
            addCriterion("direction =", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotEqualTo(String value) {
            addCriterion("direction <>", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThan(String value) {
            addCriterion("direction >", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("direction >=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThan(String value) {
            addCriterion("direction <", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThanOrEqualTo(String value) {
            addCriterion("direction <=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLike(String value) {
            addCriterion("direction like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotLike(String value) {
            addCriterion("direction not like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionIn(List<String> values) {
            addCriterion("direction in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotIn(List<String> values) {
            addCriterion("direction not in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionBetween(String value1, String value2) {
            addCriterion("direction between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotBetween(String value1, String value2) {
            addCriterion("direction not between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andNorthIsNull() {
            addCriterion("north is null");
            return (Criteria) this;
        }

        public Criteria andNorthIsNotNull() {
            addCriterion("north is not null");
            return (Criteria) this;
        }

        public Criteria andNorthEqualTo(String value) {
            addCriterion("north =", value, "north");
            return (Criteria) this;
        }

        public Criteria andNorthNotEqualTo(String value) {
            addCriterion("north <>", value, "north");
            return (Criteria) this;
        }

        public Criteria andNorthGreaterThan(String value) {
            addCriterion("north >", value, "north");
            return (Criteria) this;
        }

        public Criteria andNorthGreaterThanOrEqualTo(String value) {
            addCriterion("north >=", value, "north");
            return (Criteria) this;
        }

        public Criteria andNorthLessThan(String value) {
            addCriterion("north <", value, "north");
            return (Criteria) this;
        }

        public Criteria andNorthLessThanOrEqualTo(String value) {
            addCriterion("north <=", value, "north");
            return (Criteria) this;
        }

        public Criteria andNorthLike(String value) {
            addCriterion("north like", value, "north");
            return (Criteria) this;
        }

        public Criteria andNorthNotLike(String value) {
            addCriterion("north not like", value, "north");
            return (Criteria) this;
        }

        public Criteria andNorthIn(List<String> values) {
            addCriterion("north in", values, "north");
            return (Criteria) this;
        }

        public Criteria andNorthNotIn(List<String> values) {
            addCriterion("north not in", values, "north");
            return (Criteria) this;
        }

        public Criteria andNorthBetween(String value1, String value2) {
            addCriterion("north between", value1, value2, "north");
            return (Criteria) this;
        }

        public Criteria andNorthNotBetween(String value1, String value2) {
            addCriterion("north not between", value1, value2, "north");
            return (Criteria) this;
        }

        public Criteria andEastIsNull() {
            addCriterion("east is null");
            return (Criteria) this;
        }

        public Criteria andEastIsNotNull() {
            addCriterion("east is not null");
            return (Criteria) this;
        }

        public Criteria andEastEqualTo(String value) {
            addCriterion("east =", value, "east");
            return (Criteria) this;
        }

        public Criteria andEastNotEqualTo(String value) {
            addCriterion("east <>", value, "east");
            return (Criteria) this;
        }

        public Criteria andEastGreaterThan(String value) {
            addCriterion("east >", value, "east");
            return (Criteria) this;
        }

        public Criteria andEastGreaterThanOrEqualTo(String value) {
            addCriterion("east >=", value, "east");
            return (Criteria) this;
        }

        public Criteria andEastLessThan(String value) {
            addCriterion("east <", value, "east");
            return (Criteria) this;
        }

        public Criteria andEastLessThanOrEqualTo(String value) {
            addCriterion("east <=", value, "east");
            return (Criteria) this;
        }

        public Criteria andEastLike(String value) {
            addCriterion("east like", value, "east");
            return (Criteria) this;
        }

        public Criteria andEastNotLike(String value) {
            addCriterion("east not like", value, "east");
            return (Criteria) this;
        }

        public Criteria andEastIn(List<String> values) {
            addCriterion("east in", values, "east");
            return (Criteria) this;
        }

        public Criteria andEastNotIn(List<String> values) {
            addCriterion("east not in", values, "east");
            return (Criteria) this;
        }

        public Criteria andEastBetween(String value1, String value2) {
            addCriterion("east between", value1, value2, "east");
            return (Criteria) this;
        }

        public Criteria andEastNotBetween(String value1, String value2) {
            addCriterion("east not between", value1, value2, "east");
            return (Criteria) this;
        }

        public Criteria andHeadingIsNull() {
            addCriterion("heading is null");
            return (Criteria) this;
        }

        public Criteria andHeadingIsNotNull() {
            addCriterion("heading is not null");
            return (Criteria) this;
        }

        public Criteria andHeadingEqualTo(String value) {
            addCriterion("heading =", value, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingNotEqualTo(String value) {
            addCriterion("heading <>", value, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingGreaterThan(String value) {
            addCriterion("heading >", value, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingGreaterThanOrEqualTo(String value) {
            addCriterion("heading >=", value, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingLessThan(String value) {
            addCriterion("heading <", value, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingLessThanOrEqualTo(String value) {
            addCriterion("heading <=", value, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingLike(String value) {
            addCriterion("heading like", value, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingNotLike(String value) {
            addCriterion("heading not like", value, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingIn(List<String> values) {
            addCriterion("heading in", values, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingNotIn(List<String> values) {
            addCriterion("heading not in", values, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingBetween(String value1, String value2) {
            addCriterion("heading between", value1, value2, "heading");
            return (Criteria) this;
        }

        public Criteria andHeadingNotBetween(String value1, String value2) {
            addCriterion("heading not between", value1, value2, "heading");
            return (Criteria) this;
        }

        public Criteria andTiltXIsNull() {
            addCriterion("tilt_x is null");
            return (Criteria) this;
        }

        public Criteria andTiltXIsNotNull() {
            addCriterion("tilt_x is not null");
            return (Criteria) this;
        }

        public Criteria andTiltXEqualTo(String value) {
            addCriterion("tilt_x =", value, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXNotEqualTo(String value) {
            addCriterion("tilt_x <>", value, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXGreaterThan(String value) {
            addCriterion("tilt_x >", value, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXGreaterThanOrEqualTo(String value) {
            addCriterion("tilt_x >=", value, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXLessThan(String value) {
            addCriterion("tilt_x <", value, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXLessThanOrEqualTo(String value) {
            addCriterion("tilt_x <=", value, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXLike(String value) {
            addCriterion("tilt_x like", value, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXNotLike(String value) {
            addCriterion("tilt_x not like", value, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXIn(List<String> values) {
            addCriterion("tilt_x in", values, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXNotIn(List<String> values) {
            addCriterion("tilt_x not in", values, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXBetween(String value1, String value2) {
            addCriterion("tilt_x between", value1, value2, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltXNotBetween(String value1, String value2) {
            addCriterion("tilt_x not between", value1, value2, "tiltX");
            return (Criteria) this;
        }

        public Criteria andTiltYIsNull() {
            addCriterion("tilt_y is null");
            return (Criteria) this;
        }

        public Criteria andTiltYIsNotNull() {
            addCriterion("tilt_y is not null");
            return (Criteria) this;
        }

        public Criteria andTiltYEqualTo(String value) {
            addCriterion("tilt_y =", value, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYNotEqualTo(String value) {
            addCriterion("tilt_y <>", value, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYGreaterThan(String value) {
            addCriterion("tilt_y >", value, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYGreaterThanOrEqualTo(String value) {
            addCriterion("tilt_y >=", value, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYLessThan(String value) {
            addCriterion("tilt_y <", value, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYLessThanOrEqualTo(String value) {
            addCriterion("tilt_y <=", value, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYLike(String value) {
            addCriterion("tilt_y like", value, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYNotLike(String value) {
            addCriterion("tilt_y not like", value, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYIn(List<String> values) {
            addCriterion("tilt_y in", values, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYNotIn(List<String> values) {
            addCriterion("tilt_y not in", values, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYBetween(String value1, String value2) {
            addCriterion("tilt_y between", value1, value2, "tiltY");
            return (Criteria) this;
        }

        public Criteria andTiltYNotBetween(String value1, String value2) {
            addCriterion("tilt_y not between", value1, value2, "tiltY");
            return (Criteria) this;
        }

        public Criteria andSpStdIsNull() {
            addCriterion("sp_std is null");
            return (Criteria) this;
        }

        public Criteria andSpStdIsNotNull() {
            addCriterion("sp_std is not null");
            return (Criteria) this;
        }

        public Criteria andSpStdEqualTo(String value) {
            addCriterion("sp_std =", value, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdNotEqualTo(String value) {
            addCriterion("sp_std <>", value, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdGreaterThan(String value) {
            addCriterion("sp_std >", value, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdGreaterThanOrEqualTo(String value) {
            addCriterion("sp_std >=", value, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdLessThan(String value) {
            addCriterion("sp_std <", value, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdLessThanOrEqualTo(String value) {
            addCriterion("sp_std <=", value, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdLike(String value) {
            addCriterion("sp_std like", value, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdNotLike(String value) {
            addCriterion("sp_std not like", value, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdIn(List<String> values) {
            addCriterion("sp_std in", values, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdNotIn(List<String> values) {
            addCriterion("sp_std not in", values, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdBetween(String value1, String value2) {
            addCriterion("sp_std between", value1, value2, "spStd");
            return (Criteria) this;
        }

        public Criteria andSpStdNotBetween(String value1, String value2) {
            addCriterion("sp_std not between", value1, value2, "spStd");
            return (Criteria) this;
        }

        public Criteria andStrengthIsNull() {
            addCriterion("strength is null");
            return (Criteria) this;
        }

        public Criteria andStrengthIsNotNull() {
            addCriterion("strength is not null");
            return (Criteria) this;
        }

        public Criteria andStrengthEqualTo(String value) {
            addCriterion("strength =", value, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthNotEqualTo(String value) {
            addCriterion("strength <>", value, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthGreaterThan(String value) {
            addCriterion("strength >", value, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthGreaterThanOrEqualTo(String value) {
            addCriterion("strength >=", value, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthLessThan(String value) {
            addCriterion("strength <", value, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthLessThanOrEqualTo(String value) {
            addCriterion("strength <=", value, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthLike(String value) {
            addCriterion("strength like", value, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthNotLike(String value) {
            addCriterion("strength not like", value, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthIn(List<String> values) {
            addCriterion("strength in", values, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthNotIn(List<String> values) {
            addCriterion("strength not in", values, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthBetween(String value1, String value2) {
            addCriterion("strength between", value1, value2, "strength");
            return (Criteria) this;
        }

        public Criteria andStrengthNotBetween(String value1, String value2) {
            addCriterion("strength not between", value1, value2, "strength");
            return (Criteria) this;
        }

        public Criteria andPingCountIsNull() {
            addCriterion("ping_count is null");
            return (Criteria) this;
        }

        public Criteria andPingCountIsNotNull() {
            addCriterion("ping_count is not null");
            return (Criteria) this;
        }

        public Criteria andPingCountEqualTo(String value) {
            addCriterion("ping_count =", value, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountNotEqualTo(String value) {
            addCriterion("ping_count <>", value, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountGreaterThan(String value) {
            addCriterion("ping_count >", value, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountGreaterThanOrEqualTo(String value) {
            addCriterion("ping_count >=", value, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountLessThan(String value) {
            addCriterion("ping_count <", value, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountLessThanOrEqualTo(String value) {
            addCriterion("ping_count <=", value, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountLike(String value) {
            addCriterion("ping_count like", value, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountNotLike(String value) {
            addCriterion("ping_count not like", value, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountIn(List<String> values) {
            addCriterion("ping_count in", values, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountNotIn(List<String> values) {
            addCriterion("ping_count not in", values, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountBetween(String value1, String value2) {
            addCriterion("ping_count between", value1, value2, "pingCount");
            return (Criteria) this;
        }

        public Criteria andPingCountNotBetween(String value1, String value2) {
            addCriterion("ping_count not between", value1, value2, "pingCount");
            return (Criteria) this;
        }

        public Criteria andAbsTiltIsNull() {
            addCriterion("abs_tilt is null");
            return (Criteria) this;
        }

        public Criteria andAbsTiltIsNotNull() {
            addCriterion("abs_tilt is not null");
            return (Criteria) this;
        }

        public Criteria andAbsTiltEqualTo(String value) {
            addCriterion("abs_tilt =", value, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltNotEqualTo(String value) {
            addCriterion("abs_tilt <>", value, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltGreaterThan(String value) {
            addCriterion("abs_tilt >", value, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltGreaterThanOrEqualTo(String value) {
            addCriterion("abs_tilt >=", value, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltLessThan(String value) {
            addCriterion("abs_tilt <", value, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltLessThanOrEqualTo(String value) {
            addCriterion("abs_tilt <=", value, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltLike(String value) {
            addCriterion("abs_tilt like", value, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltNotLike(String value) {
            addCriterion("abs_tilt not like", value, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltIn(List<String> values) {
            addCriterion("abs_tilt in", values, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltNotIn(List<String> values) {
            addCriterion("abs_tilt not in", values, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltBetween(String value1, String value2) {
            addCriterion("abs_tilt between", value1, value2, "absTilt");
            return (Criteria) this;
        }

        public Criteria andAbsTiltNotBetween(String value1, String value2) {
            addCriterion("abs_tilt not between", value1, value2, "absTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltIsNull() {
            addCriterion("max_tilt is null");
            return (Criteria) this;
        }

        public Criteria andMaxTiltIsNotNull() {
            addCriterion("max_tilt is not null");
            return (Criteria) this;
        }

        public Criteria andMaxTiltEqualTo(String value) {
            addCriterion("max_tilt =", value, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltNotEqualTo(String value) {
            addCriterion("max_tilt <>", value, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltGreaterThan(String value) {
            addCriterion("max_tilt >", value, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltGreaterThanOrEqualTo(String value) {
            addCriterion("max_tilt >=", value, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltLessThan(String value) {
            addCriterion("max_tilt <", value, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltLessThanOrEqualTo(String value) {
            addCriterion("max_tilt <=", value, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltLike(String value) {
            addCriterion("max_tilt like", value, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltNotLike(String value) {
            addCriterion("max_tilt not like", value, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltIn(List<String> values) {
            addCriterion("max_tilt in", values, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltNotIn(List<String> values) {
            addCriterion("max_tilt not in", values, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltBetween(String value1, String value2) {
            addCriterion("max_tilt between", value1, value2, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andMaxTiltNotBetween(String value1, String value2) {
            addCriterion("max_tilt not between", value1, value2, "maxTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltIsNull() {
            addCriterion("std_tilt is null");
            return (Criteria) this;
        }

        public Criteria andStdTiltIsNotNull() {
            addCriterion("std_tilt is not null");
            return (Criteria) this;
        }

        public Criteria andStdTiltEqualTo(String value) {
            addCriterion("std_tilt =", value, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltNotEqualTo(String value) {
            addCriterion("std_tilt <>", value, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltGreaterThan(String value) {
            addCriterion("std_tilt >", value, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltGreaterThanOrEqualTo(String value) {
            addCriterion("std_tilt >=", value, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltLessThan(String value) {
            addCriterion("std_tilt <", value, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltLessThanOrEqualTo(String value) {
            addCriterion("std_tilt <=", value, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltLike(String value) {
            addCriterion("std_tilt like", value, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltNotLike(String value) {
            addCriterion("std_tilt not like", value, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltIn(List<String> values) {
            addCriterion("std_tilt in", values, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltNotIn(List<String> values) {
            addCriterion("std_tilt not in", values, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltBetween(String value1, String value2) {
            addCriterion("std_tilt between", value1, value2, "stdTilt");
            return (Criteria) this;
        }

        public Criteria andStdTiltNotBetween(String value1, String value2) {
            addCriterion("std_tilt not between", value1, value2, "stdTilt");
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

        public Criteria andCjsjEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(cjsj,'"+type+"') =", value, "cjsj");
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

        public Criteria andCjsjGreaterThanOrEqualTo(String value,String type) {
            addCriterion("DATE_FORMAT(cjsj,'"+type+"') >=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThan(Date value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(Date value) {
            addCriterion("cjsj <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(String value,String type) {
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