package com.mkst.mini.systemplus.resful.domains;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DevDeviceBaseCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DevDeviceBaseCriteria() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDevNoIsNull() {
            addCriterion("dev_no is null");
            return (Criteria) this;
        }

        public Criteria andDevNoIsNotNull() {
            addCriterion("dev_no is not null");
            return (Criteria) this;
        }

        public Criteria andDevNoEqualTo(String value) {
            addCriterion("dev_no =", value, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoNotEqualTo(String value) {
            addCriterion("dev_no <>", value, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoGreaterThan(String value) {
            addCriterion("dev_no >", value, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoGreaterThanOrEqualTo(String value) {
            addCriterion("dev_no >=", value, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoLessThan(String value) {
            addCriterion("dev_no <", value, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoLessThanOrEqualTo(String value) {
            addCriterion("dev_no <=", value, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoLike(String value) {
            addCriterion("dev_no like", value, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoNotLike(String value) {
            addCriterion("dev_no not like", value, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoIn(List<String> values) {
            addCriterion("dev_no in", values, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoNotIn(List<String> values) {
            addCriterion("dev_no not in", values, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoBetween(String value1, String value2) {
            addCriterion("dev_no between", value1, value2, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevNoNotBetween(String value1, String value2) {
            addCriterion("dev_no not between", value1, value2, "devNo");
            return (Criteria) this;
        }

        public Criteria andDevTypeIsNull() {
            addCriterion("dev_type is null");
            return (Criteria) this;
        }

        public Criteria andDevTypeIsNotNull() {
            addCriterion("dev_type is not null");
            return (Criteria) this;
        }

        public Criteria andDevTypeEqualTo(String value) {
            addCriterion("dev_type =", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotEqualTo(String value) {
            addCriterion("dev_type <>", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeGreaterThan(String value) {
            addCriterion("dev_type >", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeGreaterThanOrEqualTo(String value) {
            addCriterion("dev_type >=", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeLessThan(String value) {
            addCriterion("dev_type <", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeLessThanOrEqualTo(String value) {
            addCriterion("dev_type <=", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeLike(String value) {
            addCriterion("dev_type like", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotLike(String value) {
            addCriterion("dev_type not like", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeIn(List<String> values) {
            addCriterion("dev_type in", values, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotIn(List<String> values) {
            addCriterion("dev_type not in", values, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeBetween(String value1, String value2) {
            addCriterion("dev_type between", value1, value2, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotBetween(String value1, String value2) {
            addCriterion("dev_type not between", value1, value2, "devType");
            return (Criteria) this;
        }

        public Criteria andDevNameIsNull() {
            addCriterion("dev_name is null");
            return (Criteria) this;
        }

        public Criteria andDevNameIsNotNull() {
            addCriterion("dev_name is not null");
            return (Criteria) this;
        }

        public Criteria andDevNameEqualTo(String value) {
            addCriterion("dev_name =", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameNotEqualTo(String value) {
            addCriterion("dev_name <>", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameGreaterThan(String value) {
            addCriterion("dev_name >", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameGreaterThanOrEqualTo(String value) {
            addCriterion("dev_name >=", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameLessThan(String value) {
            addCriterion("dev_name <", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameLessThanOrEqualTo(String value) {
            addCriterion("dev_name <=", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameLike(String value) {
            addCriterion("dev_name like", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameNotLike(String value) {
            addCriterion("dev_name not like", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameIn(List<String> values) {
            addCriterion("dev_name in", values, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameNotIn(List<String> values) {
            addCriterion("dev_name not in", values, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameBetween(String value1, String value2) {
            addCriterion("dev_name between", value1, value2, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameNotBetween(String value1, String value2) {
            addCriterion("dev_name not between", value1, value2, "devName");
            return (Criteria) this;
        }

        public Criteria andSpecModelIsNull() {
            addCriterion("spec_model is null");
            return (Criteria) this;
        }

        public Criteria andSpecModelIsNotNull() {
            addCriterion("spec_model is not null");
            return (Criteria) this;
        }

        public Criteria andSpecModelEqualTo(String value) {
            addCriterion("spec_model =", value, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelNotEqualTo(String value) {
            addCriterion("spec_model <>", value, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelGreaterThan(String value) {
            addCriterion("spec_model >", value, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelGreaterThanOrEqualTo(String value) {
            addCriterion("spec_model >=", value, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelLessThan(String value) {
            addCriterion("spec_model <", value, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelLessThanOrEqualTo(String value) {
            addCriterion("spec_model <=", value, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelLike(String value) {
            addCriterion("spec_model like", value, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelNotLike(String value) {
            addCriterion("spec_model not like", value, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelIn(List<String> values) {
            addCriterion("spec_model in", values, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelNotIn(List<String> values) {
            addCriterion("spec_model not in", values, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelBetween(String value1, String value2) {
            addCriterion("spec_model between", value1, value2, "specModel");
            return (Criteria) this;
        }

        public Criteria andSpecModelNotBetween(String value1, String value2) {
            addCriterion("spec_model not between", value1, value2, "specModel");
            return (Criteria) this;
        }

        public Criteria andDevFactoryIsNull() {
            addCriterion("dev_factory is null");
            return (Criteria) this;
        }

        public Criteria andDevFactoryIsNotNull() {
            addCriterion("dev_factory is not null");
            return (Criteria) this;
        }

        public Criteria andDevFactoryEqualTo(String value) {
            addCriterion("dev_factory =", value, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryNotEqualTo(String value) {
            addCriterion("dev_factory <>", value, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryGreaterThan(String value) {
            addCriterion("dev_factory >", value, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryGreaterThanOrEqualTo(String value) {
            addCriterion("dev_factory >=", value, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryLessThan(String value) {
            addCriterion("dev_factory <", value, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryLessThanOrEqualTo(String value) {
            addCriterion("dev_factory <=", value, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryLike(String value) {
            addCriterion("dev_factory like", value, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryNotLike(String value) {
            addCriterion("dev_factory not like", value, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryIn(List<String> values) {
            addCriterion("dev_factory in", values, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryNotIn(List<String> values) {
            addCriterion("dev_factory not in", values, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryBetween(String value1, String value2) {
            addCriterion("dev_factory between", value1, value2, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevFactoryNotBetween(String value1, String value2) {
            addCriterion("dev_factory not between", value1, value2, "devFactory");
            return (Criteria) this;
        }

        public Criteria andDevNumberIsNull() {
            addCriterion("dev_number is null");
            return (Criteria) this;
        }

        public Criteria andDevNumberIsNotNull() {
            addCriterion("dev_number is not null");
            return (Criteria) this;
        }

        public Criteria andDevNumberEqualTo(BigDecimal value) {
            addCriterion("dev_number =", value, "devNumber");
            return (Criteria) this;
        }

        public Criteria andDevNumberNotEqualTo(BigDecimal value) {
            addCriterion("dev_number <>", value, "devNumber");
            return (Criteria) this;
        }

        public Criteria andDevNumberGreaterThan(BigDecimal value) {
            addCriterion("dev_number >", value, "devNumber");
            return (Criteria) this;
        }

        public Criteria andDevNumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dev_number >=", value, "devNumber");
            return (Criteria) this;
        }

        public Criteria andDevNumberLessThan(BigDecimal value) {
            addCriterion("dev_number <", value, "devNumber");
            return (Criteria) this;
        }

        public Criteria andDevNumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dev_number <=", value, "devNumber");
            return (Criteria) this;
        }

        public Criteria andDevNumberIn(List<BigDecimal> values) {
            addCriterion("dev_number in", values, "devNumber");
            return (Criteria) this;
        }

        public Criteria andDevNumberNotIn(List<BigDecimal> values) {
            addCriterion("dev_number not in", values, "devNumber");
            return (Criteria) this;
        }

        public Criteria andDevNumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dev_number between", value1, value2, "devNumber");
            return (Criteria) this;
        }

        public Criteria andDevNumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dev_number not between", value1, value2, "devNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIsNull() {
            addCriterion("purchase_date is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIsNotNull() {
            addCriterion("purchase_date is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateEqualTo(Date value) {
            addCriterion("purchase_date =", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotEqualTo(Date value) {
            addCriterion("purchase_date <>", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateGreaterThan(Date value) {
            addCriterion("purchase_date >", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("purchase_date >=", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLessThan(Date value) {
            addCriterion("purchase_date <", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLessThanOrEqualTo(Date value) {
            addCriterion("purchase_date <=", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIn(List<Date> values) {
            addCriterion("purchase_date in", values, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotIn(List<Date> values) {
            addCriterion("purchase_date not in", values, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateBetween(Date value1, Date value2) {
            addCriterion("purchase_date between", value1, value2, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotBetween(Date value1, Date value2) {
            addCriterion("purchase_date not between", value1, value2, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeIsNull() {
            addCriterion("dev_guarantee_time is null");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeIsNotNull() {
            addCriterion("dev_guarantee_time is not null");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeEqualTo(String value) {
            addCriterion("dev_guarantee_time =", value, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeNotEqualTo(String value) {
            addCriterion("dev_guarantee_time <>", value, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeGreaterThan(String value) {
            addCriterion("dev_guarantee_time >", value, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("dev_guarantee_time >=", value, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeLessThan(String value) {
            addCriterion("dev_guarantee_time <", value, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeLessThanOrEqualTo(String value) {
            addCriterion("dev_guarantee_time <=", value, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeLike(String value) {
            addCriterion("dev_guarantee_time like", value, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeNotLike(String value) {
            addCriterion("dev_guarantee_time not like", value, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeIn(List<String> values) {
            addCriterion("dev_guarantee_time in", values, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeNotIn(List<String> values) {
            addCriterion("dev_guarantee_time not in", values, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeBetween(String value1, String value2) {
            addCriterion("dev_guarantee_time between", value1, value2, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDevGuaranteeTimeNotBetween(String value1, String value2) {
            addCriterion("dev_guarantee_time not between", value1, value2, "devGuaranteeTime");
            return (Criteria) this;
        }

        public Criteria andDeptIdIsNull() {
            addCriterion("dept_id is null");
            return (Criteria) this;
        }

        public Criteria andDeptIdIsNotNull() {
            addCriterion("dept_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeptIdEqualTo(String value) {
            addCriterion("dept_id =", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotEqualTo(String value) {
            addCriterion("dept_id <>", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThan(String value) {
            addCriterion("dept_id >", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThanOrEqualTo(String value) {
            addCriterion("dept_id >=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThan(String value) {
            addCriterion("dept_id <", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThanOrEqualTo(String value) {
            addCriterion("dept_id <=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLike(String value) {
            addCriterion("dept_id like", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotLike(String value) {
            addCriterion("dept_id not like", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdIn(List<String> values) {
            addCriterion("dept_id in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotIn(List<String> values) {
            addCriterion("dept_id not in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdBetween(String value1, String value2) {
            addCriterion("dept_id between", value1, value2, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotBetween(String value1, String value2) {
            addCriterion("dept_id not between", value1, value2, "deptId");
            return (Criteria) this;
        }

        public Criteria andManageuserIsNull() {
            addCriterion("manageuser is null");
            return (Criteria) this;
        }

        public Criteria andManageuserIsNotNull() {
            addCriterion("manageuser is not null");
            return (Criteria) this;
        }

        public Criteria andManageuserEqualTo(String value) {
            addCriterion("manageuser =", value, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserNotEqualTo(String value) {
            addCriterion("manageuser <>", value, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserGreaterThan(String value) {
            addCriterion("manageuser >", value, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserGreaterThanOrEqualTo(String value) {
            addCriterion("manageuser >=", value, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserLessThan(String value) {
            addCriterion("manageuser <", value, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserLessThanOrEqualTo(String value) {
            addCriterion("manageuser <=", value, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserLike(String value) {
            addCriterion("manageuser like", value, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserNotLike(String value) {
            addCriterion("manageuser not like", value, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserIn(List<String> values) {
            addCriterion("manageuser in", values, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserNotIn(List<String> values) {
            addCriterion("manageuser not in", values, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserBetween(String value1, String value2) {
            addCriterion("manageuser between", value1, value2, "manageuser");
            return (Criteria) this;
        }

        public Criteria andManageuserNotBetween(String value1, String value2) {
            addCriterion("manageuser not between", value1, value2, "manageuser");
            return (Criteria) this;
        }

        public Criteria andDevValueIsNull() {
            addCriterion("dev_value is null");
            return (Criteria) this;
        }

        public Criteria andDevValueIsNotNull() {
            addCriterion("dev_value is not null");
            return (Criteria) this;
        }

        public Criteria andDevValueEqualTo(BigDecimal value) {
            addCriterion("dev_value =", value, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevValueNotEqualTo(BigDecimal value) {
            addCriterion("dev_value <>", value, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevValueGreaterThan(BigDecimal value) {
            addCriterion("dev_value >", value, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dev_value >=", value, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevValueLessThan(BigDecimal value) {
            addCriterion("dev_value <", value, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dev_value <=", value, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevValueIn(List<BigDecimal> values) {
            addCriterion("dev_value in", values, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevValueNotIn(List<BigDecimal> values) {
            addCriterion("dev_value not in", values, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dev_value between", value1, value2, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dev_value not between", value1, value2, "devValue");
            return (Criteria) this;
        }

        public Criteria andDevLocalIsNull() {
            addCriterion("dev_local is null");
            return (Criteria) this;
        }

        public Criteria andDevLocalIsNotNull() {
            addCriterion("dev_local is not null");
            return (Criteria) this;
        }

        public Criteria andDevLocalEqualTo(String value) {
            addCriterion("dev_local =", value, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalNotEqualTo(String value) {
            addCriterion("dev_local <>", value, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalGreaterThan(String value) {
            addCriterion("dev_local >", value, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalGreaterThanOrEqualTo(String value) {
            addCriterion("dev_local >=", value, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalLessThan(String value) {
            addCriterion("dev_local <", value, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalLessThanOrEqualTo(String value) {
            addCriterion("dev_local <=", value, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalLike(String value) {
            addCriterion("dev_local like", value, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalNotLike(String value) {
            addCriterion("dev_local not like", value, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalIn(List<String> values) {
            addCriterion("dev_local in", values, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalNotIn(List<String> values) {
            addCriterion("dev_local not in", values, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalBetween(String value1, String value2) {
            addCriterion("dev_local between", value1, value2, "devLocal");
            return (Criteria) this;
        }

        public Criteria andDevLocalNotBetween(String value1, String value2) {
            addCriterion("dev_local not between", value1, value2, "devLocal");
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

        public Criteria andDevStatusIsNull() {
            addCriterion("dev_status is null");
            return (Criteria) this;
        }

        public Criteria andDevStatusIsNotNull() {
            addCriterion("dev_status is not null");
            return (Criteria) this;
        }

        public Criteria andDevStatusEqualTo(Integer value) {
            addCriterion("dev_status =", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusNotEqualTo(Integer value) {
            addCriterion("dev_status <>", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusGreaterThan(Integer value) {
            addCriterion("dev_status >", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("dev_status >=", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusLessThan(Integer value) {
            addCriterion("dev_status <", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusLessThanOrEqualTo(Integer value) {
            addCriterion("dev_status <=", value, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusIn(List<Integer> values) {
            addCriterion("dev_status in", values, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusNotIn(List<Integer> values) {
            addCriterion("dev_status not in", values, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusBetween(Integer value1, Integer value2) {
            addCriterion("dev_status between", value1, value2, "devStatus");
            return (Criteria) this;
        }

        public Criteria andDevStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("dev_status not between", value1, value2, "devStatus");
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

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
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
