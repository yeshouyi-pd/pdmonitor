package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppMonitorDiscoveryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppMonitorDiscoveryExample() {
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

        public Criteria andFxsjIsNull() {
            addCriterion("fxsj is null");
            return (Criteria) this;
        }

        public Criteria andFxsjIsNotNull() {
            addCriterion("fxsj is not null");
            return (Criteria) this;
        }

        public Criteria andFxsjEqualTo(Date value) {
            addCriterion("fxsj =", value, "fxsj");
            return (Criteria) this;
        }

        public Criteria andFxsjNotEqualTo(Date value) {
            addCriterion("fxsj <>", value, "fxsj");
            return (Criteria) this;
        }

        public Criteria andFxsjGreaterThan(Date value) {
            addCriterion("fxsj >", value, "fxsj");
            return (Criteria) this;
        }

        public Criteria andFxsjGreaterThanOrEqualTo(Date value) {
            addCriterion("fxsj >=", value, "fxsj");
            return (Criteria) this;
        }

        public Criteria andFxsjLessThan(Date value) {
            addCriterion("fxsj <", value, "fxsj");
            return (Criteria) this;
        }

        public Criteria andFxsjLessThanOrEqualTo(Date value) {
            addCriterion("fxsj <=", value, "fxsj");
            return (Criteria) this;
        }

        public Criteria andFxsjIn(List<Date> values) {
            addCriterion("fxsj in", values, "fxsj");
            return (Criteria) this;
        }

        public Criteria andFxsjNotIn(List<Date> values) {
            addCriterion("fxsj not in", values, "fxsj");
            return (Criteria) this;
        }

        public Criteria andFxsjBetween(Date value1, Date value2) {
            addCriterion("fxsj between", value1, value2, "fxsj");
            return (Criteria) this;
        }

        public Criteria andFxsjNotBetween(Date value1, Date value2) {
            addCriterion("fxsj not between", value1, value2, "fxsj");
            return (Criteria) this;
        }

        public Criteria andSJlIsNull() {
            addCriterion("s_jl is null");
            return (Criteria) this;
        }

        public Criteria andSJlIsNotNull() {
            addCriterion("s_jl is not null");
            return (Criteria) this;
        }

        public Criteria andSJlEqualTo(String value) {
            addCriterion("s_jl =", value, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlNotEqualTo(String value) {
            addCriterion("s_jl <>", value, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlGreaterThan(String value) {
            addCriterion("s_jl >", value, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlGreaterThanOrEqualTo(String value) {
            addCriterion("s_jl >=", value, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlLessThan(String value) {
            addCriterion("s_jl <", value, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlLessThanOrEqualTo(String value) {
            addCriterion("s_jl <=", value, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlLike(String value) {
            addCriterion("s_jl like", value, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlNotLike(String value) {
            addCriterion("s_jl not like", value, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlIn(List<String> values) {
            addCriterion("s_jl in", values, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlNotIn(List<String> values) {
            addCriterion("s_jl not in", values, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlBetween(String value1, String value2) {
            addCriterion("s_jl between", value1, value2, "sjl");
            return (Criteria) this;
        }

        public Criteria andSJlNotBetween(String value1, String value2) {
            addCriterion("s_jl not between", value1, value2, "sjl");
            return (Criteria) this;
        }

        public Criteria andSGczIsNull() {
            addCriterion("s_gcz is null");
            return (Criteria) this;
        }

        public Criteria andSGczIsNotNull() {
            addCriterion("s_gcz is not null");
            return (Criteria) this;
        }

        public Criteria andSGczEqualTo(String value) {
            addCriterion("s_gcz =", value, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczNotEqualTo(String value) {
            addCriterion("s_gcz <>", value, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczGreaterThan(String value) {
            addCriterion("s_gcz >", value, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczGreaterThanOrEqualTo(String value) {
            addCriterion("s_gcz >=", value, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczLessThan(String value) {
            addCriterion("s_gcz <", value, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczLessThanOrEqualTo(String value) {
            addCriterion("s_gcz <=", value, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczLike(String value) {
            addCriterion("s_gcz like", value, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczNotLike(String value) {
            addCriterion("s_gcz not like", value, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczIn(List<String> values) {
            addCriterion("s_gcz in", values, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczNotIn(List<String> values) {
            addCriterion("s_gcz not in", values, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczBetween(String value1, String value2) {
            addCriterion("s_gcz between", value1, value2, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSGczNotBetween(String value1, String value2) {
            addCriterion("s_gcz not between", value1, value2, "sgcz");
            return (Criteria) this;
        }

        public Criteria andSFwIsNull() {
            addCriterion("s_fw is null");
            return (Criteria) this;
        }

        public Criteria andSFwIsNotNull() {
            addCriterion("s_fw is not null");
            return (Criteria) this;
        }

        public Criteria andSFwEqualTo(String value) {
            addCriterion("s_fw =", value, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwNotEqualTo(String value) {
            addCriterion("s_fw <>", value, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwGreaterThan(String value) {
            addCriterion("s_fw >", value, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwGreaterThanOrEqualTo(String value) {
            addCriterion("s_fw >=", value, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwLessThan(String value) {
            addCriterion("s_fw <", value, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwLessThanOrEqualTo(String value) {
            addCriterion("s_fw <=", value, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwLike(String value) {
            addCriterion("s_fw like", value, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwNotLike(String value) {
            addCriterion("s_fw not like", value, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwIn(List<String> values) {
            addCriterion("s_fw in", values, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwNotIn(List<String> values) {
            addCriterion("s_fw not in", values, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwBetween(String value1, String value2) {
            addCriterion("s_fw between", value1, value2, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFwNotBetween(String value1, String value2) {
            addCriterion("s_fw not between", value1, value2, "sfw");
            return (Criteria) this;
        }

        public Criteria andSFfIsNull() {
            addCriterion("s_ff is null");
            return (Criteria) this;
        }

        public Criteria andSFfIsNotNull() {
            addCriterion("s_ff is not null");
            return (Criteria) this;
        }

        public Criteria andSFfEqualTo(String value) {
            addCriterion("s_ff =", value, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfNotEqualTo(String value) {
            addCriterion("s_ff <>", value, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfGreaterThan(String value) {
            addCriterion("s_ff >", value, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfGreaterThanOrEqualTo(String value) {
            addCriterion("s_ff >=", value, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfLessThan(String value) {
            addCriterion("s_ff <", value, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfLessThanOrEqualTo(String value) {
            addCriterion("s_ff <=", value, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfLike(String value) {
            addCriterion("s_ff like", value, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfNotLike(String value) {
            addCriterion("s_ff not like", value, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfIn(List<String> values) {
            addCriterion("s_ff in", values, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfNotIn(List<String> values) {
            addCriterion("s_ff not in", values, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfBetween(String value1, String value2) {
            addCriterion("s_ff between", value1, value2, "sff");
            return (Criteria) this;
        }

        public Criteria andSFfNotBetween(String value1, String value2) {
            addCriterion("s_ff not between", value1, value2, "sff");
            return (Criteria) this;
        }

        public Criteria andSWzmIsNull() {
            addCriterion("s_wzm is null");
            return (Criteria) this;
        }

        public Criteria andSWzmIsNotNull() {
            addCriterion("s_wzm is not null");
            return (Criteria) this;
        }

        public Criteria andSWzmEqualTo(String value) {
            addCriterion("s_wzm =", value, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmNotEqualTo(String value) {
            addCriterion("s_wzm <>", value, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmGreaterThan(String value) {
            addCriterion("s_wzm >", value, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmGreaterThanOrEqualTo(String value) {
            addCriterion("s_wzm >=", value, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmLessThan(String value) {
            addCriterion("s_wzm <", value, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmLessThanOrEqualTo(String value) {
            addCriterion("s_wzm <=", value, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmLike(String value) {
            addCriterion("s_wzm like", value, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmNotLike(String value) {
            addCriterion("s_wzm not like", value, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmIn(List<String> values) {
            addCriterion("s_wzm in", values, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmNotIn(List<String> values) {
            addCriterion("s_wzm not in", values, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmBetween(String value1, String value2) {
            addCriterion("s_wzm between", value1, value2, "swzm");
            return (Criteria) this;
        }

        public Criteria andSWzmNotBetween(String value1, String value2) {
            addCriterion("s_wzm not between", value1, value2, "swzm");
            return (Criteria) this;
        }

        public Criteria andSQtZxIsNull() {
            addCriterion("s_qt_zx is null");
            return (Criteria) this;
        }

        public Criteria andSQtZxIsNotNull() {
            addCriterion("s_qt_zx is not null");
            return (Criteria) this;
        }

        public Criteria andSQtZxEqualTo(String value) {
            addCriterion("s_qt_zx =", value, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxNotEqualTo(String value) {
            addCriterion("s_qt_zx <>", value, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxGreaterThan(String value) {
            addCriterion("s_qt_zx >", value, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxGreaterThanOrEqualTo(String value) {
            addCriterion("s_qt_zx >=", value, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxLessThan(String value) {
            addCriterion("s_qt_zx <", value, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxLessThanOrEqualTo(String value) {
            addCriterion("s_qt_zx <=", value, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxLike(String value) {
            addCriterion("s_qt_zx like", value, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxNotLike(String value) {
            addCriterion("s_qt_zx not like", value, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxIn(List<String> values) {
            addCriterion("s_qt_zx in", values, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxNotIn(List<String> values) {
            addCriterion("s_qt_zx not in", values, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxBetween(String value1, String value2) {
            addCriterion("s_qt_zx between", value1, value2, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZxNotBetween(String value1, String value2) {
            addCriterion("s_qt_zx not between", value1, value2, "sqtzx");
            return (Criteria) this;
        }

        public Criteria andSQtZyIsNull() {
            addCriterion("s_qt_zy is null");
            return (Criteria) this;
        }

        public Criteria andSQtZyIsNotNull() {
            addCriterion("s_qt_zy is not null");
            return (Criteria) this;
        }

        public Criteria andSQtZyEqualTo(String value) {
            addCriterion("s_qt_zy =", value, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyNotEqualTo(String value) {
            addCriterion("s_qt_zy <>", value, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyGreaterThan(String value) {
            addCriterion("s_qt_zy >", value, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyGreaterThanOrEqualTo(String value) {
            addCriterion("s_qt_zy >=", value, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyLessThan(String value) {
            addCriterion("s_qt_zy <", value, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyLessThanOrEqualTo(String value) {
            addCriterion("s_qt_zy <=", value, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyLike(String value) {
            addCriterion("s_qt_zy like", value, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyNotLike(String value) {
            addCriterion("s_qt_zy not like", value, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyIn(List<String> values) {
            addCriterion("s_qt_zy in", values, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyNotIn(List<String> values) {
            addCriterion("s_qt_zy not in", values, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyBetween(String value1, String value2) {
            addCriterion("s_qt_zy between", value1, value2, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZyNotBetween(String value1, String value2) {
            addCriterion("s_qt_zy not between", value1, value2, "sqtzy");
            return (Criteria) this;
        }

        public Criteria andSQtZdIsNull() {
            addCriterion("s_qt_zd is null");
            return (Criteria) this;
        }

        public Criteria andSQtZdIsNotNull() {
            addCriterion("s_qt_zd is not null");
            return (Criteria) this;
        }

        public Criteria andSQtZdEqualTo(String value) {
            addCriterion("s_qt_zd =", value, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdNotEqualTo(String value) {
            addCriterion("s_qt_zd <>", value, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdGreaterThan(String value) {
            addCriterion("s_qt_zd >", value, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdGreaterThanOrEqualTo(String value) {
            addCriterion("s_qt_zd >=", value, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdLessThan(String value) {
            addCriterion("s_qt_zd <", value, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdLessThanOrEqualTo(String value) {
            addCriterion("s_qt_zd <=", value, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdLike(String value) {
            addCriterion("s_qt_zd like", value, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdNotLike(String value) {
            addCriterion("s_qt_zd not like", value, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdIn(List<String> values) {
            addCriterion("s_qt_zd in", values, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdNotIn(List<String> values) {
            addCriterion("s_qt_zd not in", values, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdBetween(String value1, String value2) {
            addCriterion("s_qt_zd between", value1, value2, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andSQtZdNotBetween(String value1, String value2) {
            addCriterion("s_qt_zd not between", value1, value2, "sqtzd");
            return (Criteria) this;
        }

        public Criteria andCZsbhIsNull() {
            addCriterion("c_zsbh is null");
            return (Criteria) this;
        }

        public Criteria andCZsbhIsNotNull() {
            addCriterion("c_zsbh is not null");
            return (Criteria) this;
        }

        public Criteria andCZsbhEqualTo(String value) {
            addCriterion("c_zsbh =", value, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhNotEqualTo(String value) {
            addCriterion("c_zsbh <>", value, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhGreaterThan(String value) {
            addCriterion("c_zsbh >", value, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhGreaterThanOrEqualTo(String value) {
            addCriterion("c_zsbh >=", value, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhLessThan(String value) {
            addCriterion("c_zsbh <", value, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhLessThanOrEqualTo(String value) {
            addCriterion("c_zsbh <=", value, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhLike(String value) {
            addCriterion("c_zsbh like", value, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhNotLike(String value) {
            addCriterion("c_zsbh not like", value, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhIn(List<String> values) {
            addCriterion("c_zsbh in", values, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhNotIn(List<String> values) {
            addCriterion("c_zsbh not in", values, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhBetween(String value1, String value2) {
            addCriterion("c_zsbh between", value1, value2, "czsbh");
            return (Criteria) this;
        }

        public Criteria andCZsbhNotBetween(String value1, String value2) {
            addCriterion("c_zsbh not between", value1, value2, "czsbh");
            return (Criteria) this;
        }

        public Criteria andHZjajlIsNull() {
            addCriterion("h_zjajl is null");
            return (Criteria) this;
        }

        public Criteria andHZjajlIsNotNull() {
            addCriterion("h_zjajl is not null");
            return (Criteria) this;
        }

        public Criteria andHZjajlEqualTo(String value) {
            addCriterion("h_zjajl =", value, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlNotEqualTo(String value) {
            addCriterion("h_zjajl <>", value, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlGreaterThan(String value) {
            addCriterion("h_zjajl >", value, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlGreaterThanOrEqualTo(String value) {
            addCriterion("h_zjajl >=", value, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlLessThan(String value) {
            addCriterion("h_zjajl <", value, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlLessThanOrEqualTo(String value) {
            addCriterion("h_zjajl <=", value, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlLike(String value) {
            addCriterion("h_zjajl like", value, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlNotLike(String value) {
            addCriterion("h_zjajl not like", value, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlIn(List<String> values) {
            addCriterion("h_zjajl in", values, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlNotIn(List<String> values) {
            addCriterion("h_zjajl not in", values, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlBetween(String value1, String value2) {
            addCriterion("h_zjajl between", value1, value2, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHZjajlNotBetween(String value1, String value2) {
            addCriterion("h_zjajl not between", value1, value2, "hzjajl");
            return (Criteria) this;
        }

        public Criteria andHMztdsIsNull() {
            addCriterion("h_mztds is null");
            return (Criteria) this;
        }

        public Criteria andHMztdsIsNotNull() {
            addCriterion("h_mztds is not null");
            return (Criteria) this;
        }

        public Criteria andHMztdsEqualTo(String value) {
            addCriterion("h_mztds =", value, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsNotEqualTo(String value) {
            addCriterion("h_mztds <>", value, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsGreaterThan(String value) {
            addCriterion("h_mztds >", value, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsGreaterThanOrEqualTo(String value) {
            addCriterion("h_mztds >=", value, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsLessThan(String value) {
            addCriterion("h_mztds <", value, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsLessThanOrEqualTo(String value) {
            addCriterion("h_mztds <=", value, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsLike(String value) {
            addCriterion("h_mztds like", value, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsNotLike(String value) {
            addCriterion("h_mztds not like", value, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsIn(List<String> values) {
            addCriterion("h_mztds in", values, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsNotIn(List<String> values) {
            addCriterion("h_mztds not in", values, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsBetween(String value1, String value2) {
            addCriterion("h_mztds between", value1, value2, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHMztdsNotBetween(String value1, String value2) {
            addCriterion("h_mztds not between", value1, value2, "hmztds");
            return (Criteria) this;
        }

        public Criteria andHXwmsIsNull() {
            addCriterion("h_xwms is null");
            return (Criteria) this;
        }

        public Criteria andHXwmsIsNotNull() {
            addCriterion("h_xwms is not null");
            return (Criteria) this;
        }

        public Criteria andHXwmsEqualTo(String value) {
            addCriterion("h_xwms =", value, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsNotEqualTo(String value) {
            addCriterion("h_xwms <>", value, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsGreaterThan(String value) {
            addCriterion("h_xwms >", value, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsGreaterThanOrEqualTo(String value) {
            addCriterion("h_xwms >=", value, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsLessThan(String value) {
            addCriterion("h_xwms <", value, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsLessThanOrEqualTo(String value) {
            addCriterion("h_xwms <=", value, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsLike(String value) {
            addCriterion("h_xwms like", value, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsNotLike(String value) {
            addCriterion("h_xwms not like", value, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsIn(List<String> values) {
            addCriterion("h_xwms in", values, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsNotIn(List<String> values) {
            addCriterion("h_xwms not in", values, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsBetween(String value1, String value2) {
            addCriterion("h_xwms between", value1, value2, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHXwmsNotBetween(String value1, String value2) {
            addCriterion("h_xwms not between", value1, value2, "hxwms");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmIsNull() {
            addCriterion("h_hjlxdm is null");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmIsNotNull() {
            addCriterion("h_hjlxdm is not null");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmEqualTo(String value) {
            addCriterion("h_hjlxdm =", value, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmNotEqualTo(String value) {
            addCriterion("h_hjlxdm <>", value, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmGreaterThan(String value) {
            addCriterion("h_hjlxdm >", value, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmGreaterThanOrEqualTo(String value) {
            addCriterion("h_hjlxdm >=", value, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmLessThan(String value) {
            addCriterion("h_hjlxdm <", value, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmLessThanOrEqualTo(String value) {
            addCriterion("h_hjlxdm <=", value, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmLike(String value) {
            addCriterion("h_hjlxdm like", value, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmNotLike(String value) {
            addCriterion("h_hjlxdm not like", value, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmIn(List<String> values) {
            addCriterion("h_hjlxdm in", values, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmNotIn(List<String> values) {
            addCriterion("h_hjlxdm not in", values, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmBetween(String value1, String value2) {
            addCriterion("h_hjlxdm between", value1, value2, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andHHjlxdmNotBetween(String value1, String value2) {
            addCriterion("h_hjlxdm not between", value1, value2, "hhjlxdm");
            return (Criteria) this;
        }

        public Criteria andScjsIsNull() {
            addCriterion("scjs is null");
            return (Criteria) this;
        }

        public Criteria andScjsIsNotNull() {
            addCriterion("scjs is not null");
            return (Criteria) this;
        }

        public Criteria andScjsEqualTo(Date value) {
            addCriterion("scjs =", value, "scjs");
            return (Criteria) this;
        }

        public Criteria andScjsNotEqualTo(Date value) {
            addCriterion("scjs <>", value, "scjs");
            return (Criteria) this;
        }

        public Criteria andScjsGreaterThan(Date value) {
            addCriterion("scjs >", value, "scjs");
            return (Criteria) this;
        }

        public Criteria andScjsGreaterThanOrEqualTo(Date value) {
            addCriterion("scjs >=", value, "scjs");
            return (Criteria) this;
        }

        public Criteria andScjsLessThan(Date value) {
            addCriterion("scjs <", value, "scjs");
            return (Criteria) this;
        }

        public Criteria andScjsLessThanOrEqualTo(Date value) {
            addCriterion("scjs <=", value, "scjs");
            return (Criteria) this;
        }

        public Criteria andScjsIn(List<Date> values) {
            addCriterion("scjs in", values, "scjs");
            return (Criteria) this;
        }

        public Criteria andScjsNotIn(List<Date> values) {
            addCriterion("scjs not in", values, "scjs");
            return (Criteria) this;
        }

        public Criteria andScjsBetween(Date value1, Date value2) {
            addCriterion("scjs between", value1, value2, "scjs");
            return (Criteria) this;
        }

        public Criteria andScjsNotBetween(Date value1, Date value2) {
            addCriterion("scjs not between", value1, value2, "scjs");
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

        public Criteria andSbbhEqualTo(String value) {
            addCriterion("sbbh =", value, "sbbh");
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