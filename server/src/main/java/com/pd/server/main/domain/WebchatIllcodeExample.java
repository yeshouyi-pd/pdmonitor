package com.pd.server.main.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebchatIllcodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebchatIllcodeExample() {
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

        public Criteria andWfdmIsNull() {
            addCriterion("wfdm is null");
            return (Criteria) this;
        }

        public Criteria andWfdmIsNotNull() {
            addCriterion("wfdm is not null");
            return (Criteria) this;
        }

        public Criteria andWfdmEqualTo(String value) {
            addCriterion("wfdm =", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmNotEqualTo(String value) {
            addCriterion("wfdm <>", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmGreaterThan(String value) {
            addCriterion("wfdm >", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmGreaterThanOrEqualTo(String value) {
            addCriterion("wfdm >=", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmLessThan(String value) {
            addCriterion("wfdm <", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmLessThanOrEqualTo(String value) {
            addCriterion("wfdm <=", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmLike(String value) {
            addCriterion("wfdm like", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmNotLike(String value) {
            addCriterion("wfdm not like", value, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmIn(List<String> values) {
            addCriterion("wfdm in", values, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmNotIn(List<String> values) {
            addCriterion("wfdm not in", values, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmBetween(String value1, String value2) {
            addCriterion("wfdm between", value1, value2, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfdmNotBetween(String value1, String value2) {
            addCriterion("wfdm not between", value1, value2, "wfdm");
            return (Criteria) this;
        }

        public Criteria andWfxwIsNull() {
            addCriterion("wfxw is null");
            return (Criteria) this;
        }

        public Criteria andWfxwIsNotNull() {
            addCriterion("wfxw is not null");
            return (Criteria) this;
        }

        public Criteria andWfxwEqualTo(String value) {
            addCriterion("wfxw =", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwNotEqualTo(String value) {
            addCriterion("wfxw <>", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwGreaterThan(String value) {
            addCriterion("wfxw >", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwGreaterThanOrEqualTo(String value) {
            addCriterion("wfxw >=", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwLessThan(String value) {
            addCriterion("wfxw <", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwLessThanOrEqualTo(String value) {
            addCriterion("wfxw <=", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwLike(String value) {
            addCriterion("wfxw like", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwNotLike(String value) {
            addCriterion("wfxw not like", value, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwIn(List<String> values) {
            addCriterion("wfxw in", values, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwNotIn(List<String> values) {
            addCriterion("wfxw not in", values, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwBetween(String value1, String value2) {
            addCriterion("wfxw between", value1, value2, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWfxwNotBetween(String value1, String value2) {
            addCriterion("wfxw not between", value1, value2, "wfxw");
            return (Criteria) this;
        }

        public Criteria andWftlIsNull() {
            addCriterion("wftl is null");
            return (Criteria) this;
        }

        public Criteria andWftlIsNotNull() {
            addCriterion("wftl is not null");
            return (Criteria) this;
        }

        public Criteria andWftlEqualTo(String value) {
            addCriterion("wftl =", value, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlNotEqualTo(String value) {
            addCriterion("wftl <>", value, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlGreaterThan(String value) {
            addCriterion("wftl >", value, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlGreaterThanOrEqualTo(String value) {
            addCriterion("wftl >=", value, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlLessThan(String value) {
            addCriterion("wftl <", value, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlLessThanOrEqualTo(String value) {
            addCriterion("wftl <=", value, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlLike(String value) {
            addCriterion("wftl like", value, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlNotLike(String value) {
            addCriterion("wftl not like", value, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlIn(List<String> values) {
            addCriterion("wftl in", values, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlNotIn(List<String> values) {
            addCriterion("wftl not in", values, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlBetween(String value1, String value2) {
            addCriterion("wftl between", value1, value2, "wftl");
            return (Criteria) this;
        }

        public Criteria andWftlNotBetween(String value1, String value2) {
            addCriterion("wftl not between", value1, value2, "wftl");
            return (Criteria) this;
        }

        public Criteria andCfyjIsNull() {
            addCriterion("cfyj is null");
            return (Criteria) this;
        }

        public Criteria andCfyjIsNotNull() {
            addCriterion("cfyj is not null");
            return (Criteria) this;
        }

        public Criteria andCfyjEqualTo(String value) {
            addCriterion("cfyj =", value, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjNotEqualTo(String value) {
            addCriterion("cfyj <>", value, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjGreaterThan(String value) {
            addCriterion("cfyj >", value, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjGreaterThanOrEqualTo(String value) {
            addCriterion("cfyj >=", value, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjLessThan(String value) {
            addCriterion("cfyj <", value, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjLessThanOrEqualTo(String value) {
            addCriterion("cfyj <=", value, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjLike(String value) {
            addCriterion("cfyj like", value, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjNotLike(String value) {
            addCriterion("cfyj not like", value, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjIn(List<String> values) {
            addCriterion("cfyj in", values, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjNotIn(List<String> values) {
            addCriterion("cfyj not in", values, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjBetween(String value1, String value2) {
            addCriterion("cfyj between", value1, value2, "cfyj");
            return (Criteria) this;
        }

        public Criteria andCfyjNotBetween(String value1, String value2) {
            addCriterion("cfyj not between", value1, value2, "cfyj");
            return (Criteria) this;
        }

        public Criteria andJfIsNull() {
            addCriterion("jf is null");
            return (Criteria) this;
        }

        public Criteria andJfIsNotNull() {
            addCriterion("jf is not null");
            return (Criteria) this;
        }

        public Criteria andJfEqualTo(String value) {
            addCriterion("jf =", value, "jf");
            return (Criteria) this;
        }

        public Criteria andJfNotEqualTo(String value) {
            addCriterion("jf <>", value, "jf");
            return (Criteria) this;
        }

        public Criteria andJfGreaterThan(String value) {
            addCriterion("jf >", value, "jf");
            return (Criteria) this;
        }

        public Criteria andJfGreaterThanOrEqualTo(String value) {
            addCriterion("jf >=", value, "jf");
            return (Criteria) this;
        }

        public Criteria andJfLessThan(String value) {
            addCriterion("jf <", value, "jf");
            return (Criteria) this;
        }

        public Criteria andJfLessThanOrEqualTo(String value) {
            addCriterion("jf <=", value, "jf");
            return (Criteria) this;
        }

        public Criteria andJfLike(String value) {
            addCriterion("jf like", value, "jf");
            return (Criteria) this;
        }

        public Criteria andJfNotLike(String value) {
            addCriterion("jf not like", value, "jf");
            return (Criteria) this;
        }

        public Criteria andJfIn(List<String> values) {
            addCriterion("jf in", values, "jf");
            return (Criteria) this;
        }

        public Criteria andJfNotIn(List<String> values) {
            addCriterion("jf not in", values, "jf");
            return (Criteria) this;
        }

        public Criteria andJfBetween(String value1, String value2) {
            addCriterion("jf between", value1, value2, "jf");
            return (Criteria) this;
        }

        public Criteria andJfNotBetween(String value1, String value2) {
            addCriterion("jf not between", value1, value2, "jf");
            return (Criteria) this;
        }

        public Criteria andFkjeIsNull() {
            addCriterion("fkje is null");
            return (Criteria) this;
        }

        public Criteria andFkjeIsNotNull() {
            addCriterion("fkje is not null");
            return (Criteria) this;
        }

        public Criteria andFkjeEqualTo(Float value) {
            addCriterion("fkje =", value, "fkje");
            return (Criteria) this;
        }

        public Criteria andFkjeNotEqualTo(Float value) {
            addCriterion("fkje <>", value, "fkje");
            return (Criteria) this;
        }

        public Criteria andFkjeGreaterThan(Float value) {
            addCriterion("fkje >", value, "fkje");
            return (Criteria) this;
        }

        public Criteria andFkjeGreaterThanOrEqualTo(Float value) {
            addCriterion("fkje >=", value, "fkje");
            return (Criteria) this;
        }

        public Criteria andFkjeLessThan(Float value) {
            addCriterion("fkje <", value, "fkje");
            return (Criteria) this;
        }

        public Criteria andFkjeLessThanOrEqualTo(Float value) {
            addCriterion("fkje <=", value, "fkje");
            return (Criteria) this;
        }

        public Criteria andFkjeIn(List<Float> values) {
            addCriterion("fkje in", values, "fkje");
            return (Criteria) this;
        }

        public Criteria andFkjeNotIn(List<Float> values) {
            addCriterion("fkje not in", values, "fkje");
            return (Criteria) this;
        }

        public Criteria andFkjeBetween(Float value1, Float value2) {
            addCriterion("fkje between", value1, value2, "fkje");
            return (Criteria) this;
        }

        public Criteria andFkjeNotBetween(Float value1, Float value2) {
            addCriterion("fkje not between", value1, value2, "fkje");
            return (Criteria) this;
        }

        public Criteria andQtcfIsNull() {
            addCriterion("qtcf is null");
            return (Criteria) this;
        }

        public Criteria andQtcfIsNotNull() {
            addCriterion("qtcf is not null");
            return (Criteria) this;
        }

        public Criteria andQtcfEqualTo(String value) {
            addCriterion("qtcf =", value, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfNotEqualTo(String value) {
            addCriterion("qtcf <>", value, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfGreaterThan(String value) {
            addCriterion("qtcf >", value, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfGreaterThanOrEqualTo(String value) {
            addCriterion("qtcf >=", value, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfLessThan(String value) {
            addCriterion("qtcf <", value, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfLessThanOrEqualTo(String value) {
            addCriterion("qtcf <=", value, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfLike(String value) {
            addCriterion("qtcf like", value, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfNotLike(String value) {
            addCriterion("qtcf not like", value, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfIn(List<String> values) {
            addCriterion("qtcf in", values, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfNotIn(List<String> values) {
            addCriterion("qtcf not in", values, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfBetween(String value1, String value2) {
            addCriterion("qtcf between", value1, value2, "qtcf");
            return (Criteria) this;
        }

        public Criteria andQtcfNotBetween(String value1, String value2) {
            addCriterion("qtcf not between", value1, value2, "qtcf");
            return (Criteria) this;
        }

        public Criteria andCsIsNull() {
            addCriterion("cs is null");
            return (Criteria) this;
        }

        public Criteria andCsIsNotNull() {
            addCriterion("cs is not null");
            return (Criteria) this;
        }

        public Criteria andCsEqualTo(String value) {
            addCriterion("cs =", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotEqualTo(String value) {
            addCriterion("cs <>", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThan(String value) {
            addCriterion("cs >", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThanOrEqualTo(String value) {
            addCriterion("cs >=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThan(String value) {
            addCriterion("cs <", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThanOrEqualTo(String value) {
            addCriterion("cs <=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLike(String value) {
            addCriterion("cs like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotLike(String value) {
            addCriterion("cs not like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsIn(List<String> values) {
            addCriterion("cs in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotIn(List<String> values) {
            addCriterion("cs not in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsBetween(String value1, String value2) {
            addCriterion("cs between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotBetween(String value1, String value2) {
            addCriterion("cs not between", value1, value2, "cs");
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

        public Criteria andUsercodeIsNull() {
            addCriterion("usercode is null");
            return (Criteria) this;
        }

        public Criteria andUsercodeIsNotNull() {
            addCriterion("usercode is not null");
            return (Criteria) this;
        }

        public Criteria andUsercodeEqualTo(String value) {
            addCriterion("usercode =", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeNotEqualTo(String value) {
            addCriterion("usercode <>", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeGreaterThan(String value) {
            addCriterion("usercode >", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeGreaterThanOrEqualTo(String value) {
            addCriterion("usercode >=", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeLessThan(String value) {
            addCriterion("usercode <", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeLessThanOrEqualTo(String value) {
            addCriterion("usercode <=", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeLike(String value) {
            addCriterion("usercode like", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeNotLike(String value) {
            addCriterion("usercode not like", value, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeIn(List<String> values) {
            addCriterion("usercode in", values, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeNotIn(List<String> values) {
            addCriterion("usercode not in", values, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeBetween(String value1, String value2) {
            addCriterion("usercode between", value1, value2, "usercode");
            return (Criteria) this;
        }

        public Criteria andUsercodeNotBetween(String value1, String value2) {
            addCriterion("usercode not between", value1, value2, "usercode");
            return (Criteria) this;
        }

        public Criteria andFbsjIsNull() {
            addCriterion("fbsj is null");
            return (Criteria) this;
        }

        public Criteria andFbsjIsNotNull() {
            addCriterion("fbsj is not null");
            return (Criteria) this;
        }

        public Criteria andFbsjEqualTo(Date value) {
            addCriterion("fbsj =", value, "fbsj");
            return (Criteria) this;
        }

        public Criteria andFbsjNotEqualTo(Date value) {
            addCriterion("fbsj <>", value, "fbsj");
            return (Criteria) this;
        }

        public Criteria andFbsjGreaterThan(Date value) {
            addCriterion("fbsj >", value, "fbsj");
            return (Criteria) this;
        }

        public Criteria andFbsjGreaterThanOrEqualTo(Date value) {
            addCriterion("fbsj >=", value, "fbsj");
            return (Criteria) this;
        }

        public Criteria andFbsjLessThan(Date value) {
            addCriterion("fbsj <", value, "fbsj");
            return (Criteria) this;
        }

        public Criteria andFbsjLessThanOrEqualTo(Date value) {
            addCriterion("fbsj <=", value, "fbsj");
            return (Criteria) this;
        }

        public Criteria andFbsjIn(List<Date> values) {
            addCriterion("fbsj in", values, "fbsj");
            return (Criteria) this;
        }

        public Criteria andFbsjNotIn(List<Date> values) {
            addCriterion("fbsj not in", values, "fbsj");
            return (Criteria) this;
        }

        public Criteria andFbsjBetween(Date value1, Date value2) {
            addCriterion("fbsj between", value1, value2, "fbsj");
            return (Criteria) this;
        }

        public Criteria andFbsjNotBetween(Date value1, Date value2) {
            addCriterion("fbsj not between", value1, value2, "fbsj");
            return (Criteria) this;
        }

        public Criteria andZtIsNull() {
            addCriterion("zt is null");
            return (Criteria) this;
        }

        public Criteria andZtIsNotNull() {
            addCriterion("zt is not null");
            return (Criteria) this;
        }

        public Criteria andZtEqualTo(String value) {
            addCriterion("zt =", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotEqualTo(String value) {
            addCriterion("zt <>", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThan(String value) {
            addCriterion("zt >", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThanOrEqualTo(String value) {
            addCriterion("zt >=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThan(String value) {
            addCriterion("zt <", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThanOrEqualTo(String value) {
            addCriterion("zt <=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLike(String value) {
            addCriterion("zt like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotLike(String value) {
            addCriterion("zt not like", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtIn(List<String> values) {
            addCriterion("zt in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotIn(List<String> values) {
            addCriterion("zt not in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtBetween(String value1, String value2) {
            addCriterion("zt between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotBetween(String value1, String value2) {
            addCriterion("zt not between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andLbIsNull() {
            addCriterion("lb is null");
            return (Criteria) this;
        }

        public Criteria andLbIsNotNull() {
            addCriterion("lb is not null");
            return (Criteria) this;
        }

        public Criteria andLbEqualTo(String value) {
            addCriterion("lb =", value, "lb");
            return (Criteria) this;
        }

        public Criteria andLbNotEqualTo(String value) {
            addCriterion("lb <>", value, "lb");
            return (Criteria) this;
        }

        public Criteria andLbGreaterThan(String value) {
            addCriterion("lb >", value, "lb");
            return (Criteria) this;
        }

        public Criteria andLbGreaterThanOrEqualTo(String value) {
            addCriterion("lb >=", value, "lb");
            return (Criteria) this;
        }

        public Criteria andLbLessThan(String value) {
            addCriterion("lb <", value, "lb");
            return (Criteria) this;
        }

        public Criteria andLbLessThanOrEqualTo(String value) {
            addCriterion("lb <=", value, "lb");
            return (Criteria) this;
        }

        public Criteria andLbLike(String value) {
            addCriterion("lb like", value, "lb");
            return (Criteria) this;
        }

        public Criteria andLbNotLike(String value) {
            addCriterion("lb not like", value, "lb");
            return (Criteria) this;
        }

        public Criteria andLbIn(List<String> values) {
            addCriterion("lb in", values, "lb");
            return (Criteria) this;
        }

        public Criteria andLbNotIn(List<String> values) {
            addCriterion("lb not in", values, "lb");
            return (Criteria) this;
        }

        public Criteria andLbBetween(String value1, String value2) {
            addCriterion("lb between", value1, value2, "lb");
            return (Criteria) this;
        }

        public Criteria andLbNotBetween(String value1, String value2) {
            addCriterion("lb not between", value1, value2, "lb");
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