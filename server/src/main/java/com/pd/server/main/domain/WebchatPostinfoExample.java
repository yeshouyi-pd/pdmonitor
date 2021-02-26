package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebchatPostinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebchatPostinfoExample() {
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

        public Criteria andLoginNameIsNull() {
            addCriterion("login_name is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("login_name is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("login_name =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("login_name <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("login_name >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("login_name >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("login_name <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("login_name <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("login_name like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("login_name not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("login_name in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("login_name not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("login_name between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("login_name not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginPwdIsNull() {
            addCriterion("login_pwd is null");
            return (Criteria) this;
        }

        public Criteria andLoginPwdIsNotNull() {
            addCriterion("login_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPwdEqualTo(String value) {
            addCriterion("login_pwd =", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdNotEqualTo(String value) {
            addCriterion("login_pwd <>", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdGreaterThan(String value) {
            addCriterion("login_pwd >", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdGreaterThanOrEqualTo(String value) {
            addCriterion("login_pwd >=", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdLessThan(String value) {
            addCriterion("login_pwd <", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdLessThanOrEqualTo(String value) {
            addCriterion("login_pwd <=", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdLike(String value) {
            addCriterion("login_pwd like", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdNotLike(String value) {
            addCriterion("login_pwd not like", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdIn(List<String> values) {
            addCriterion("login_pwd in", values, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdNotIn(List<String> values) {
            addCriterion("login_pwd not in", values, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdBetween(String value1, String value2) {
            addCriterion("login_pwd between", value1, value2, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdNotBetween(String value1, String value2) {
            addCriterion("login_pwd not between", value1, value2, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andSecretKeyIsNull() {
            addCriterion("secret_key is null");
            return (Criteria) this;
        }

        public Criteria andSecretKeyIsNotNull() {
            addCriterion("secret_key is not null");
            return (Criteria) this;
        }

        public Criteria andSecretKeyEqualTo(String value) {
            addCriterion("secret_key =", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotEqualTo(String value) {
            addCriterion("secret_key <>", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyGreaterThan(String value) {
            addCriterion("secret_key >", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyGreaterThanOrEqualTo(String value) {
            addCriterion("secret_key >=", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyLessThan(String value) {
            addCriterion("secret_key <", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyLessThanOrEqualTo(String value) {
            addCriterion("secret_key <=", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyLike(String value) {
            addCriterion("secret_key like", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotLike(String value) {
            addCriterion("secret_key not like", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyIn(List<String> values) {
            addCriterion("secret_key in", values, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotIn(List<String> values) {
            addCriterion("secret_key not in", values, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyBetween(String value1, String value2) {
            addCriterion("secret_key between", value1, value2, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotBetween(String value1, String value2) {
            addCriterion("secret_key not between", value1, value2, "secretKey");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidIsNull() {
            addCriterion("distributor_orderID is null");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidIsNotNull() {
            addCriterion("distributor_orderID is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidEqualTo(String value) {
            addCriterion("distributor_orderID =", value, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidNotEqualTo(String value) {
            addCriterion("distributor_orderID <>", value, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidGreaterThan(String value) {
            addCriterion("distributor_orderID >", value, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("distributor_orderID >=", value, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidLessThan(String value) {
            addCriterion("distributor_orderID <", value, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidLessThanOrEqualTo(String value) {
            addCriterion("distributor_orderID <=", value, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidLike(String value) {
            addCriterion("distributor_orderID like", value, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidNotLike(String value) {
            addCriterion("distributor_orderID not like", value, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidIn(List<String> values) {
            addCriterion("distributor_orderID in", values, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidNotIn(List<String> values) {
            addCriterion("distributor_orderID not in", values, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidBetween(String value1, String value2) {
            addCriterion("distributor_orderID between", value1, value2, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andDistributorOrderidNotBetween(String value1, String value2) {
            addCriterion("distributor_orderID not between", value1, value2, "distributorOrderid");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeIsNull() {
            addCriterion("sender_province_code is null");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeIsNotNull() {
            addCriterion("sender_province_code is not null");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeEqualTo(String value) {
            addCriterion("sender_province_code =", value, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeNotEqualTo(String value) {
            addCriterion("sender_province_code <>", value, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeGreaterThan(String value) {
            addCriterion("sender_province_code >", value, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sender_province_code >=", value, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeLessThan(String value) {
            addCriterion("sender_province_code <", value, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeLessThanOrEqualTo(String value) {
            addCriterion("sender_province_code <=", value, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeLike(String value) {
            addCriterion("sender_province_code like", value, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeNotLike(String value) {
            addCriterion("sender_province_code not like", value, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeIn(List<String> values) {
            addCriterion("sender_province_code in", values, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeNotIn(List<String> values) {
            addCriterion("sender_province_code not in", values, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeBetween(String value1, String value2) {
            addCriterion("sender_province_code between", value1, value2, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderProvinceCodeNotBetween(String value1, String value2) {
            addCriterion("sender_province_code not between", value1, value2, "senderProvinceCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeIsNull() {
            addCriterion("sender_city_code is null");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeIsNotNull() {
            addCriterion("sender_city_code is not null");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeEqualTo(String value) {
            addCriterion("sender_city_code =", value, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeNotEqualTo(String value) {
            addCriterion("sender_city_code <>", value, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeGreaterThan(String value) {
            addCriterion("sender_city_code >", value, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sender_city_code >=", value, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeLessThan(String value) {
            addCriterion("sender_city_code <", value, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeLessThanOrEqualTo(String value) {
            addCriterion("sender_city_code <=", value, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeLike(String value) {
            addCriterion("sender_city_code like", value, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeNotLike(String value) {
            addCriterion("sender_city_code not like", value, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeIn(List<String> values) {
            addCriterion("sender_city_code in", values, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeNotIn(List<String> values) {
            addCriterion("sender_city_code not in", values, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeBetween(String value1, String value2) {
            addCriterion("sender_city_code between", value1, value2, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCityCodeNotBetween(String value1, String value2) {
            addCriterion("sender_city_code not between", value1, value2, "senderCityCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeIsNull() {
            addCriterion("sender_county_code is null");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeIsNotNull() {
            addCriterion("sender_county_code is not null");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeEqualTo(String value) {
            addCriterion("sender_county_code =", value, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeNotEqualTo(String value) {
            addCriterion("sender_county_code <>", value, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeGreaterThan(String value) {
            addCriterion("sender_county_code >", value, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sender_county_code >=", value, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeLessThan(String value) {
            addCriterion("sender_county_code <", value, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeLessThanOrEqualTo(String value) {
            addCriterion("sender_county_code <=", value, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeLike(String value) {
            addCriterion("sender_county_code like", value, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeNotLike(String value) {
            addCriterion("sender_county_code not like", value, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeIn(List<String> values) {
            addCriterion("sender_county_code in", values, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeNotIn(List<String> values) {
            addCriterion("sender_county_code not in", values, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeBetween(String value1, String value2) {
            addCriterion("sender_county_code between", value1, value2, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCountyCodeNotBetween(String value1, String value2) {
            addCriterion("sender_county_code not between", value1, value2, "senderCountyCode");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyIsNull() {
            addCriterion("sender_company is null");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyIsNotNull() {
            addCriterion("sender_company is not null");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyEqualTo(String value) {
            addCriterion("sender_company =", value, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyNotEqualTo(String value) {
            addCriterion("sender_company <>", value, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyGreaterThan(String value) {
            addCriterion("sender_company >", value, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("sender_company >=", value, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyLessThan(String value) {
            addCriterion("sender_company <", value, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyLessThanOrEqualTo(String value) {
            addCriterion("sender_company <=", value, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyLike(String value) {
            addCriterion("sender_company like", value, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyNotLike(String value) {
            addCriterion("sender_company not like", value, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyIn(List<String> values) {
            addCriterion("sender_company in", values, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyNotIn(List<String> values) {
            addCriterion("sender_company not in", values, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyBetween(String value1, String value2) {
            addCriterion("sender_company between", value1, value2, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderCompanyNotBetween(String value1, String value2) {
            addCriterion("sender_company not between", value1, value2, "senderCompany");
            return (Criteria) this;
        }

        public Criteria andSenderNameIsNull() {
            addCriterion("sender_name is null");
            return (Criteria) this;
        }

        public Criteria andSenderNameIsNotNull() {
            addCriterion("sender_name is not null");
            return (Criteria) this;
        }

        public Criteria andSenderNameEqualTo(String value) {
            addCriterion("sender_name =", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotEqualTo(String value) {
            addCriterion("sender_name <>", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameGreaterThan(String value) {
            addCriterion("sender_name >", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameGreaterThanOrEqualTo(String value) {
            addCriterion("sender_name >=", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameLessThan(String value) {
            addCriterion("sender_name <", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameLessThanOrEqualTo(String value) {
            addCriterion("sender_name <=", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameLike(String value) {
            addCriterion("sender_name like", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotLike(String value) {
            addCriterion("sender_name not like", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameIn(List<String> values) {
            addCriterion("sender_name in", values, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotIn(List<String> values) {
            addCriterion("sender_name not in", values, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameBetween(String value1, String value2) {
            addCriterion("sender_name between", value1, value2, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotBetween(String value1, String value2) {
            addCriterion("sender_name not between", value1, value2, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneIsNull() {
            addCriterion("sender_phone is null");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneIsNotNull() {
            addCriterion("sender_phone is not null");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneEqualTo(String value) {
            addCriterion("sender_phone =", value, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneNotEqualTo(String value) {
            addCriterion("sender_phone <>", value, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneGreaterThan(String value) {
            addCriterion("sender_phone >", value, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("sender_phone >=", value, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneLessThan(String value) {
            addCriterion("sender_phone <", value, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneLessThanOrEqualTo(String value) {
            addCriterion("sender_phone <=", value, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneLike(String value) {
            addCriterion("sender_phone like", value, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneNotLike(String value) {
            addCriterion("sender_phone not like", value, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneIn(List<String> values) {
            addCriterion("sender_phone in", values, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneNotIn(List<String> values) {
            addCriterion("sender_phone not in", values, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneBetween(String value1, String value2) {
            addCriterion("sender_phone between", value1, value2, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderPhoneNotBetween(String value1, String value2) {
            addCriterion("sender_phone not between", value1, value2, "senderPhone");
            return (Criteria) this;
        }

        public Criteria andSenderAddressIsNull() {
            addCriterion("sender_address is null");
            return (Criteria) this;
        }

        public Criteria andSenderAddressIsNotNull() {
            addCriterion("sender_address is not null");
            return (Criteria) this;
        }

        public Criteria andSenderAddressEqualTo(String value) {
            addCriterion("sender_address =", value, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressNotEqualTo(String value) {
            addCriterion("sender_address <>", value, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressGreaterThan(String value) {
            addCriterion("sender_address >", value, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressGreaterThanOrEqualTo(String value) {
            addCriterion("sender_address >=", value, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressLessThan(String value) {
            addCriterion("sender_address <", value, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressLessThanOrEqualTo(String value) {
            addCriterion("sender_address <=", value, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressLike(String value) {
            addCriterion("sender_address like", value, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressNotLike(String value) {
            addCriterion("sender_address not like", value, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressIn(List<String> values) {
            addCriterion("sender_address in", values, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressNotIn(List<String> values) {
            addCriterion("sender_address not in", values, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressBetween(String value1, String value2) {
            addCriterion("sender_address between", value1, value2, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andSenderAddressNotBetween(String value1, String value2) {
            addCriterion("sender_address not between", value1, value2, "senderAddress");
            return (Criteria) this;
        }

        public Criteria andProtectPriceIsNull() {
            addCriterion("protect_price is null");
            return (Criteria) this;
        }

        public Criteria andProtectPriceIsNotNull() {
            addCriterion("protect_price is not null");
            return (Criteria) this;
        }

        public Criteria andProtectPriceEqualTo(Integer value) {
            addCriterion("protect_price =", value, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andProtectPriceNotEqualTo(Integer value) {
            addCriterion("protect_price <>", value, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andProtectPriceGreaterThan(Integer value) {
            addCriterion("protect_price >", value, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andProtectPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("protect_price >=", value, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andProtectPriceLessThan(Integer value) {
            addCriterion("protect_price <", value, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andProtectPriceLessThanOrEqualTo(Integer value) {
            addCriterion("protect_price <=", value, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andProtectPriceIn(List<Integer> values) {
            addCriterion("protect_price in", values, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andProtectPriceNotIn(List<Integer> values) {
            addCriterion("protect_price not in", values, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andProtectPriceBetween(Integer value1, Integer value2) {
            addCriterion("protect_price between", value1, value2, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andProtectPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("protect_price not between", value1, value2, "protectPrice");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountIsNull() {
            addCriterion("insurance_amount is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountIsNotNull() {
            addCriterion("insurance_amount is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountEqualTo(Integer value) {
            addCriterion("insurance_amount =", value, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountNotEqualTo(Integer value) {
            addCriterion("insurance_amount <>", value, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountGreaterThan(Integer value) {
            addCriterion("insurance_amount >", value, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("insurance_amount >=", value, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountLessThan(Integer value) {
            addCriterion("insurance_amount <", value, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountLessThanOrEqualTo(Integer value) {
            addCriterion("insurance_amount <=", value, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountIn(List<Integer> values) {
            addCriterion("insurance_amount in", values, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountNotIn(List<Integer> values) {
            addCriterion("insurance_amount not in", values, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountBetween(Integer value1, Integer value2) {
            addCriterion("insurance_amount between", value1, value2, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andInsuranceAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("insurance_amount not between", value1, value2, "insuranceAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyIsNull() {
            addCriterion("deliver_money is null");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyIsNotNull() {
            addCriterion("deliver_money is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyEqualTo(Integer value) {
            addCriterion("deliver_money =", value, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyNotEqualTo(Integer value) {
            addCriterion("deliver_money <>", value, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyGreaterThan(Integer value) {
            addCriterion("deliver_money >", value, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliver_money >=", value, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyLessThan(Integer value) {
            addCriterion("deliver_money <", value, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("deliver_money <=", value, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyIn(List<Integer> values) {
            addCriterion("deliver_money in", values, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyNotIn(List<Integer> values) {
            addCriterion("deliver_money not in", values, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyBetween(Integer value1, Integer value2) {
            addCriterion("deliver_money between", value1, value2, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andDeliverMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("deliver_money not between", value1, value2, "deliverMoney");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNull() {
            addCriterion("goods is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNotNull() {
            addCriterion("goods is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsEqualTo(String value) {
            addCriterion("goods =", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotEqualTo(String value) {
            addCriterion("goods <>", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThan(String value) {
            addCriterion("goods >", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("goods >=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThan(String value) {
            addCriterion("goods <", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThanOrEqualTo(String value) {
            addCriterion("goods <=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLike(String value) {
            addCriterion("goods like", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotLike(String value) {
            addCriterion("goods not like", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsIn(List<String> values) {
            addCriterion("goods in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotIn(List<String> values) {
            addCriterion("goods not in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsBetween(String value1, String value2) {
            addCriterion("goods between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotBetween(String value1, String value2) {
            addCriterion("goods not between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityIsNull() {
            addCriterion("goods_quantity is null");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityIsNotNull() {
            addCriterion("goods_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityEqualTo(Integer value) {
            addCriterion("goods_quantity =", value, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityNotEqualTo(Integer value) {
            addCriterion("goods_quantity <>", value, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityGreaterThan(Integer value) {
            addCriterion("goods_quantity >", value, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_quantity >=", value, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityLessThan(Integer value) {
            addCriterion("goods_quantity <", value, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("goods_quantity <=", value, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityIn(List<Integer> values) {
            addCriterion("goods_quantity in", values, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityNotIn(List<Integer> values) {
            addCriterion("goods_quantity not in", values, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityBetween(Integer value1, Integer value2) {
            addCriterion("goods_quantity between", value1, value2, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_quantity not between", value1, value2, "goodsQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIsNull() {
            addCriterion("goods_weight is null");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIsNotNull() {
            addCriterion("goods_weight is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightEqualTo(Integer value) {
            addCriterion("goods_weight =", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotEqualTo(Integer value) {
            addCriterion("goods_weight <>", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightGreaterThan(Integer value) {
            addCriterion("goods_weight >", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_weight >=", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightLessThan(Integer value) {
            addCriterion("goods_weight <", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightLessThanOrEqualTo(Integer value) {
            addCriterion("goods_weight <=", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIn(List<Integer> values) {
            addCriterion("goods_weight in", values, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotIn(List<Integer> values) {
            addCriterion("goods_weight not in", values, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightBetween(Integer value1, Integer value2) {
            addCriterion("goods_weight between", value1, value2, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_weight not between", value1, value2, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andDemoIsNull() {
            addCriterion("demo is null");
            return (Criteria) this;
        }

        public Criteria andDemoIsNotNull() {
            addCriterion("demo is not null");
            return (Criteria) this;
        }

        public Criteria andDemoEqualTo(String value) {
            addCriterion("demo =", value, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoNotEqualTo(String value) {
            addCriterion("demo <>", value, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoGreaterThan(String value) {
            addCriterion("demo >", value, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoGreaterThanOrEqualTo(String value) {
            addCriterion("demo >=", value, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoLessThan(String value) {
            addCriterion("demo <", value, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoLessThanOrEqualTo(String value) {
            addCriterion("demo <=", value, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoLike(String value) {
            addCriterion("demo like", value, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoNotLike(String value) {
            addCriterion("demo not like", value, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoIn(List<String> values) {
            addCriterion("demo in", values, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoNotIn(List<String> values) {
            addCriterion("demo not in", values, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoBetween(String value1, String value2) {
            addCriterion("demo between", value1, value2, "demo");
            return (Criteria) this;
        }

        public Criteria andDemoNotBetween(String value1, String value2) {
            addCriterion("demo not between", value1, value2, "demo");
            return (Criteria) this;
        }

        public Criteria andEmsTypeIsNull() {
            addCriterion("ems_type is null");
            return (Criteria) this;
        }

        public Criteria andEmsTypeIsNotNull() {
            addCriterion("ems_type is not null");
            return (Criteria) this;
        }

        public Criteria andEmsTypeEqualTo(Integer value) {
            addCriterion("ems_type =", value, "emsType");
            return (Criteria) this;
        }

        public Criteria andEmsTypeNotEqualTo(Integer value) {
            addCriterion("ems_type <>", value, "emsType");
            return (Criteria) this;
        }

        public Criteria andEmsTypeGreaterThan(Integer value) {
            addCriterion("ems_type >", value, "emsType");
            return (Criteria) this;
        }

        public Criteria andEmsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ems_type >=", value, "emsType");
            return (Criteria) this;
        }

        public Criteria andEmsTypeLessThan(Integer value) {
            addCriterion("ems_type <", value, "emsType");
            return (Criteria) this;
        }

        public Criteria andEmsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ems_type <=", value, "emsType");
            return (Criteria) this;
        }

        public Criteria andEmsTypeIn(List<Integer> values) {
            addCriterion("ems_type in", values, "emsType");
            return (Criteria) this;
        }

        public Criteria andEmsTypeNotIn(List<Integer> values) {
            addCriterion("ems_type not in", values, "emsType");
            return (Criteria) this;
        }

        public Criteria andEmsTypeBetween(Integer value1, Integer value2) {
            addCriterion("ems_type between", value1, value2, "emsType");
            return (Criteria) this;
        }

        public Criteria andEmsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ems_type not between", value1, value2, "emsType");
            return (Criteria) this;
        }

        public Criteria andMailNoIsNull() {
            addCriterion("mail_no is null");
            return (Criteria) this;
        }

        public Criteria andMailNoIsNotNull() {
            addCriterion("mail_no is not null");
            return (Criteria) this;
        }

        public Criteria andMailNoEqualTo(String value) {
            addCriterion("mail_no =", value, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoNotEqualTo(String value) {
            addCriterion("mail_no <>", value, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoGreaterThan(String value) {
            addCriterion("mail_no >", value, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoGreaterThanOrEqualTo(String value) {
            addCriterion("mail_no >=", value, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoLessThan(String value) {
            addCriterion("mail_no <", value, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoLessThanOrEqualTo(String value) {
            addCriterion("mail_no <=", value, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoLike(String value) {
            addCriterion("mail_no like", value, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoNotLike(String value) {
            addCriterion("mail_no not like", value, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoIn(List<String> values) {
            addCriterion("mail_no in", values, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoNotIn(List<String> values) {
            addCriterion("mail_no not in", values, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoBetween(String value1, String value2) {
            addCriterion("mail_no between", value1, value2, "mailNo");
            return (Criteria) this;
        }

        public Criteria andMailNoNotBetween(String value1, String value2) {
            addCriterion("mail_no not between", value1, value2, "mailNo");
            return (Criteria) this;
        }

        public Criteria andOtherIsNull() {
            addCriterion("other is null");
            return (Criteria) this;
        }

        public Criteria andOtherIsNotNull() {
            addCriterion("other is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEqualTo(String value) {
            addCriterion("other =", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotEqualTo(String value) {
            addCriterion("other <>", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThan(String value) {
            addCriterion("other >", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThanOrEqualTo(String value) {
            addCriterion("other >=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThan(String value) {
            addCriterion("other <", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThanOrEqualTo(String value) {
            addCriterion("other <=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLike(String value) {
            addCriterion("other like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotLike(String value) {
            addCriterion("other not like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherIn(List<String> values) {
            addCriterion("other in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotIn(List<String> values) {
            addCriterion("other not in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherBetween(String value1, String value2) {
            addCriterion("other between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotBetween(String value1, String value2) {
            addCriterion("other not between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andWhoPayIsNull() {
            addCriterion("who_pay is null");
            return (Criteria) this;
        }

        public Criteria andWhoPayIsNotNull() {
            addCriterion("who_pay is not null");
            return (Criteria) this;
        }

        public Criteria andWhoPayEqualTo(Integer value) {
            addCriterion("who_pay =", value, "whoPay");
            return (Criteria) this;
        }

        public Criteria andWhoPayNotEqualTo(Integer value) {
            addCriterion("who_pay <>", value, "whoPay");
            return (Criteria) this;
        }

        public Criteria andWhoPayGreaterThan(Integer value) {
            addCriterion("who_pay >", value, "whoPay");
            return (Criteria) this;
        }

        public Criteria andWhoPayGreaterThanOrEqualTo(Integer value) {
            addCriterion("who_pay >=", value, "whoPay");
            return (Criteria) this;
        }

        public Criteria andWhoPayLessThan(Integer value) {
            addCriterion("who_pay <", value, "whoPay");
            return (Criteria) this;
        }

        public Criteria andWhoPayLessThanOrEqualTo(Integer value) {
            addCriterion("who_pay <=", value, "whoPay");
            return (Criteria) this;
        }

        public Criteria andWhoPayIn(List<Integer> values) {
            addCriterion("who_pay in", values, "whoPay");
            return (Criteria) this;
        }

        public Criteria andWhoPayNotIn(List<Integer> values) {
            addCriterion("who_pay not in", values, "whoPay");
            return (Criteria) this;
        }

        public Criteria andWhoPayBetween(Integer value1, Integer value2) {
            addCriterion("who_pay between", value1, value2, "whoPay");
            return (Criteria) this;
        }

        public Criteria andWhoPayNotBetween(Integer value1, Integer value2) {
            addCriterion("who_pay not between", value1, value2, "whoPay");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeIsNull() {
            addCriterion("sender_address_type is null");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeIsNotNull() {
            addCriterion("sender_address_type is not null");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeEqualTo(Integer value) {
            addCriterion("sender_address_type =", value, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeNotEqualTo(Integer value) {
            addCriterion("sender_address_type <>", value, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeGreaterThan(Integer value) {
            addCriterion("sender_address_type >", value, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sender_address_type >=", value, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeLessThan(Integer value) {
            addCriterion("sender_address_type <", value, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeLessThanOrEqualTo(Integer value) {
            addCriterion("sender_address_type <=", value, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeIn(List<Integer> values) {
            addCriterion("sender_address_type in", values, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeNotIn(List<Integer> values) {
            addCriterion("sender_address_type not in", values, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeBetween(Integer value1, Integer value2) {
            addCriterion("sender_address_type between", value1, value2, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andSenderAddressTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sender_address_type not between", value1, value2, "senderAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeIsNull() {
            addCriterion("receiver_address_type is null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeIsNotNull() {
            addCriterion("receiver_address_type is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeEqualTo(Integer value) {
            addCriterion("receiver_address_type =", value, "receiverAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeNotEqualTo(Integer value) {
            addCriterion("receiver_address_type <>", value, "receiverAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeGreaterThan(Integer value) {
            addCriterion("receiver_address_type >", value, "receiverAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("receiver_address_type >=", value, "receiverAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeLessThan(Integer value) {
            addCriterion("receiver_address_type <", value, "receiverAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeLessThanOrEqualTo(Integer value) {
            addCriterion("receiver_address_type <=", value, "receiverAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeIn(List<Integer> values) {
            addCriterion("receiver_address_type in", values, "receiverAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeNotIn(List<Integer> values) {
            addCriterion("receiver_address_type not in", values, "receiverAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeBetween(Integer value1, Integer value2) {
            addCriterion("receiver_address_type between", value1, value2, "receiverAddressType");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("receiver_address_type not between", value1, value2, "receiverAddressType");
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