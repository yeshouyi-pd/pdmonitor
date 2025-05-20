package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BodyAssessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BodyAssessExample() {
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

        public Criteria andVolumeIsNull() {
            addCriterion("volume is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("volume is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(String value) {
            addCriterion("volume =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(String value) {
            addCriterion("volume <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(String value) {
            addCriterion("volume >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("volume >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(String value) {
            addCriterion("volume <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(String value) {
            addCriterion("volume <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLike(String value) {
            addCriterion("volume like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotLike(String value) {
            addCriterion("volume not like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<String> values) {
            addCriterion("volume in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<String> values) {
            addCriterion("volume not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(String value1, String value2) {
            addCriterion("volume between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(String value1, String value2) {
            addCriterion("volume not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andBaiIsNull() {
            addCriterion("bai is null");
            return (Criteria) this;
        }

        public Criteria andBaiIsNotNull() {
            addCriterion("bai is not null");
            return (Criteria) this;
        }

        public Criteria andBaiEqualTo(String value) {
            addCriterion("bai =", value, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiNotEqualTo(String value) {
            addCriterion("bai <>", value, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiGreaterThan(String value) {
            addCriterion("bai >", value, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiGreaterThanOrEqualTo(String value) {
            addCriterion("bai >=", value, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiLessThan(String value) {
            addCriterion("bai <", value, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiLessThanOrEqualTo(String value) {
            addCriterion("bai <=", value, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiLike(String value) {
            addCriterion("bai like", value, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiNotLike(String value) {
            addCriterion("bai not like", value, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiIn(List<String> values) {
            addCriterion("bai in", values, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiNotIn(List<String> values) {
            addCriterion("bai not in", values, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiBetween(String value1, String value2) {
            addCriterion("bai between", value1, value2, "bai");
            return (Criteria) this;
        }

        public Criteria andBaiNotBetween(String value1, String value2) {
            addCriterion("bai not between", value1, value2, "bai");
            return (Criteria) this;
        }

        public Criteria andBodyLengthIsNull() {
            addCriterion("body_length is null");
            return (Criteria) this;
        }

        public Criteria andBodyLengthIsNotNull() {
            addCriterion("body_length is not null");
            return (Criteria) this;
        }

        public Criteria andBodyLengthEqualTo(String value) {
            addCriterion("body_length =", value, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthNotEqualTo(String value) {
            addCriterion("body_length <>", value, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthGreaterThan(String value) {
            addCriterion("body_length >", value, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthGreaterThanOrEqualTo(String value) {
            addCriterion("body_length >=", value, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthLessThan(String value) {
            addCriterion("body_length <", value, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthLessThanOrEqualTo(String value) {
            addCriterion("body_length <=", value, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthLike(String value) {
            addCriterion("body_length like", value, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthNotLike(String value) {
            addCriterion("body_length not like", value, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthIn(List<String> values) {
            addCriterion("body_length in", values, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthNotIn(List<String> values) {
            addCriterion("body_length not in", values, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthBetween(String value1, String value2) {
            addCriterion("body_length between", value1, value2, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andBodyLengthNotBetween(String value1, String value2) {
            addCriterion("body_length not between", value1, value2, "bodyLength");
            return (Criteria) this;
        }

        public Criteria andAgeGroupIsNull() {
            addCriterion("age_group is null");
            return (Criteria) this;
        }

        public Criteria andAgeGroupIsNotNull() {
            addCriterion("age_group is not null");
            return (Criteria) this;
        }

        public Criteria andAgeGroupEqualTo(String value) {
            addCriterion("age_group =", value, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupNotEqualTo(String value) {
            addCriterion("age_group <>", value, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupGreaterThan(String value) {
            addCriterion("age_group >", value, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupGreaterThanOrEqualTo(String value) {
            addCriterion("age_group >=", value, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupLessThan(String value) {
            addCriterion("age_group <", value, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupLessThanOrEqualTo(String value) {
            addCriterion("age_group <=", value, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupLike(String value) {
            addCriterion("age_group like", value, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupNotLike(String value) {
            addCriterion("age_group not like", value, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupIn(List<String> values) {
            addCriterion("age_group in", values, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupNotIn(List<String> values) {
            addCriterion("age_group not in", values, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupBetween(String value1, String value2) {
            addCriterion("age_group between", value1, value2, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andAgeGroupNotBetween(String value1, String value2) {
            addCriterion("age_group not between", value1, value2, "ageGroup");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIsNull() {
            addCriterion("total_weight is null");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIsNotNull() {
            addCriterion("total_weight is not null");
            return (Criteria) this;
        }

        public Criteria andTotalWeightEqualTo(String value) {
            addCriterion("total_weight =", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotEqualTo(String value) {
            addCriterion("total_weight <>", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightGreaterThan(String value) {
            addCriterion("total_weight >", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightGreaterThanOrEqualTo(String value) {
            addCriterion("total_weight >=", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLessThan(String value) {
            addCriterion("total_weight <", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLessThanOrEqualTo(String value) {
            addCriterion("total_weight <=", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLike(String value) {
            addCriterion("total_weight like", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotLike(String value) {
            addCriterion("total_weight not like", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIn(List<String> values) {
            addCriterion("total_weight in", values, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotIn(List<String> values) {
            addCriterion("total_weight not in", values, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightBetween(String value1, String value2) {
            addCriterion("total_weight between", value1, value2, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotBetween(String value1, String value2) {
            addCriterion("total_weight not between", value1, value2, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalBmiIsNull() {
            addCriterion("total_bmi is null");
            return (Criteria) this;
        }

        public Criteria andTotalBmiIsNotNull() {
            addCriterion("total_bmi is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBmiEqualTo(String value) {
            addCriterion("total_bmi =", value, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiNotEqualTo(String value) {
            addCriterion("total_bmi <>", value, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiGreaterThan(String value) {
            addCriterion("total_bmi >", value, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiGreaterThanOrEqualTo(String value) {
            addCriterion("total_bmi >=", value, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiLessThan(String value) {
            addCriterion("total_bmi <", value, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiLessThanOrEqualTo(String value) {
            addCriterion("total_bmi <=", value, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiLike(String value) {
            addCriterion("total_bmi like", value, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiNotLike(String value) {
            addCriterion("total_bmi not like", value, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiIn(List<String> values) {
            addCriterion("total_bmi in", values, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiNotIn(List<String> values) {
            addCriterion("total_bmi not in", values, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiBetween(String value1, String value2) {
            addCriterion("total_bmi between", value1, value2, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andTotalBmiNotBetween(String value1, String value2) {
            addCriterion("total_bmi not between", value1, value2, "totalBmi");
            return (Criteria) this;
        }

        public Criteria andFatThinIsNull() {
            addCriterion("fat_thin is null");
            return (Criteria) this;
        }

        public Criteria andFatThinIsNotNull() {
            addCriterion("fat_thin is not null");
            return (Criteria) this;
        }

        public Criteria andFatThinEqualTo(String value) {
            addCriterion("fat_thin =", value, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinNotEqualTo(String value) {
            addCriterion("fat_thin <>", value, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinGreaterThan(String value) {
            addCriterion("fat_thin >", value, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinGreaterThanOrEqualTo(String value) {
            addCriterion("fat_thin >=", value, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinLessThan(String value) {
            addCriterion("fat_thin <", value, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinLessThanOrEqualTo(String value) {
            addCriterion("fat_thin <=", value, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinLike(String value) {
            addCriterion("fat_thin like", value, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinNotLike(String value) {
            addCriterion("fat_thin not like", value, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinIn(List<String> values) {
            addCriterion("fat_thin in", values, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinNotIn(List<String> values) {
            addCriterion("fat_thin not in", values, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinBetween(String value1, String value2) {
            addCriterion("fat_thin between", value1, value2, "fatThin");
            return (Criteria) this;
        }

        public Criteria andFatThinNotBetween(String value1, String value2) {
            addCriterion("fat_thin not between", value1, value2, "fatThin");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
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

        public Criteria andCjsjLessThan(Date value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(Date value) {
            addCriterion("cjsj <=", value, "cjsj");
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