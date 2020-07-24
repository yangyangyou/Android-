package cn.itcast.ssm.po;

import java.util.ArrayList;
import java.util.List;

public class DailyCheckExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DailyCheckExample() {
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

        public Criteria andCheckIdIsNull() {
            addCriterion("check_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckIdIsNotNull() {
            addCriterion("check_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckIdEqualTo(Integer value) {
            addCriterion("check_id =", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdNotEqualTo(Integer value) {
            addCriterion("check_id <>", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdGreaterThan(Integer value) {
            addCriterion("check_id >", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_id >=", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdLessThan(Integer value) {
            addCriterion("check_id <", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdLessThanOrEqualTo(Integer value) {
            addCriterion("check_id <=", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdIn(List<Integer> values) {
            addCriterion("check_id in", values, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdNotIn(List<Integer> values) {
            addCriterion("check_id not in", values, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdBetween(Integer value1, Integer value2) {
            addCriterion("check_id between", value1, value2, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdNotBetween(Integer value1, Integer value2) {
            addCriterion("check_id not between", value1, value2, "checkId");
            return (Criteria) this;
        }

        public Criteria andPlanNoIsNull() {
            addCriterion("plan_no is null");
            return (Criteria) this;
        }

        public Criteria andPlanNoIsNotNull() {
            addCriterion("plan_no is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNoEqualTo(String value) {
            addCriterion("plan_no =", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoNotEqualTo(String value) {
            addCriterion("plan_no <>", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoGreaterThan(String value) {
            addCriterion("plan_no >", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoGreaterThanOrEqualTo(String value) {
            addCriterion("plan_no >=", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoLessThan(String value) {
            addCriterion("plan_no <", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoLessThanOrEqualTo(String value) {
            addCriterion("plan_no <=", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoLike(String value) {
            addCriterion("plan_no like", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoNotLike(String value) {
            addCriterion("plan_no not like", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoIn(List<String> values) {
            addCriterion("plan_no in", values, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoNotIn(List<String> values) {
            addCriterion("plan_no not in", values, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoBetween(String value1, String value2) {
            addCriterion("plan_no between", value1, value2, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoNotBetween(String value1, String value2) {
            addCriterion("plan_no not between", value1, value2, "planNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoIsNull() {
            addCriterion("client_material_no is null");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoIsNotNull() {
            addCriterion("client_material_no is not null");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoEqualTo(String value) {
            addCriterion("client_material_no =", value, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoNotEqualTo(String value) {
            addCriterion("client_material_no <>", value, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoGreaterThan(String value) {
            addCriterion("client_material_no >", value, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoGreaterThanOrEqualTo(String value) {
            addCriterion("client_material_no >=", value, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoLessThan(String value) {
            addCriterion("client_material_no <", value, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoLessThanOrEqualTo(String value) {
            addCriterion("client_material_no <=", value, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoLike(String value) {
            addCriterion("client_material_no like", value, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoNotLike(String value) {
            addCriterion("client_material_no not like", value, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoIn(List<String> values) {
            addCriterion("client_material_no in", values, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoNotIn(List<String> values) {
            addCriterion("client_material_no not in", values, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoBetween(String value1, String value2) {
            addCriterion("client_material_no between", value1, value2, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andClientMaterialNoNotBetween(String value1, String value2) {
            addCriterion("client_material_no not between", value1, value2, "clientMaterialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoIsNull() {
            addCriterion("material_no is null");
            return (Criteria) this;
        }

        public Criteria andMaterialNoIsNotNull() {
            addCriterion("material_no is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialNoEqualTo(String value) {
            addCriterion("material_no =", value, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoNotEqualTo(String value) {
            addCriterion("material_no <>", value, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoGreaterThan(String value) {
            addCriterion("material_no >", value, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoGreaterThanOrEqualTo(String value) {
            addCriterion("material_no >=", value, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoLessThan(String value) {
            addCriterion("material_no <", value, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoLessThanOrEqualTo(String value) {
            addCriterion("material_no <=", value, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoLike(String value) {
            addCriterion("material_no like", value, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoNotLike(String value) {
            addCriterion("material_no not like", value, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoIn(List<String> values) {
            addCriterion("material_no in", values, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoNotIn(List<String> values) {
            addCriterion("material_no not in", values, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoBetween(String value1, String value2) {
            addCriterion("material_no between", value1, value2, "materialNo");
            return (Criteria) this;
        }

        public Criteria andMaterialNoNotBetween(String value1, String value2) {
            addCriterion("material_no not between", value1, value2, "materialNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("batch_no like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("batch_no not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNull() {
            addCriterion("process_name is null");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNotNull() {
            addCriterion("process_name is not null");
            return (Criteria) this;
        }

        public Criteria andProcessNameEqualTo(String value) {
            addCriterion("process_name =", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotEqualTo(String value) {
            addCriterion("process_name <>", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThan(String value) {
            addCriterion("process_name >", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThanOrEqualTo(String value) {
            addCriterion("process_name >=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThan(String value) {
            addCriterion("process_name <", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThanOrEqualTo(String value) {
            addCriterion("process_name <=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLike(String value) {
            addCriterion("process_name like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotLike(String value) {
            addCriterion("process_name not like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameIn(List<String> values) {
            addCriterion("process_name in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotIn(List<String> values) {
            addCriterion("process_name not in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameBetween(String value1, String value2) {
            addCriterion("process_name between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotBetween(String value1, String value2) {
            addCriterion("process_name not between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andAssetNameIsNull() {
            addCriterion("asset_name is null");
            return (Criteria) this;
        }

        public Criteria andAssetNameIsNotNull() {
            addCriterion("asset_name is not null");
            return (Criteria) this;
        }

        public Criteria andAssetNameEqualTo(String value) {
            addCriterion("asset_name =", value, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameNotEqualTo(String value) {
            addCriterion("asset_name <>", value, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameGreaterThan(String value) {
            addCriterion("asset_name >", value, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameGreaterThanOrEqualTo(String value) {
            addCriterion("asset_name >=", value, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameLessThan(String value) {
            addCriterion("asset_name <", value, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameLessThanOrEqualTo(String value) {
            addCriterion("asset_name <=", value, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameLike(String value) {
            addCriterion("asset_name like", value, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameNotLike(String value) {
            addCriterion("asset_name not like", value, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameIn(List<String> values) {
            addCriterion("asset_name in", values, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameNotIn(List<String> values) {
            addCriterion("asset_name not in", values, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameBetween(String value1, String value2) {
            addCriterion("asset_name between", value1, value2, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNameNotBetween(String value1, String value2) {
            addCriterion("asset_name not between", value1, value2, "assetName");
            return (Criteria) this;
        }

        public Criteria andAssetNoIsNull() {
            addCriterion("asset_no is null");
            return (Criteria) this;
        }

        public Criteria andAssetNoIsNotNull() {
            addCriterion("asset_no is not null");
            return (Criteria) this;
        }

        public Criteria andAssetNoEqualTo(String value) {
            addCriterion("asset_no =", value, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoNotEqualTo(String value) {
            addCriterion("asset_no <>", value, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoGreaterThan(String value) {
            addCriterion("asset_no >", value, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoGreaterThanOrEqualTo(String value) {
            addCriterion("asset_no >=", value, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoLessThan(String value) {
            addCriterion("asset_no <", value, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoLessThanOrEqualTo(String value) {
            addCriterion("asset_no <=", value, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoLike(String value) {
            addCriterion("asset_no like", value, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoNotLike(String value) {
            addCriterion("asset_no not like", value, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoIn(List<String> values) {
            addCriterion("asset_no in", values, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoNotIn(List<String> values) {
            addCriterion("asset_no not in", values, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoBetween(String value1, String value2) {
            addCriterion("asset_no between", value1, value2, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetNoNotBetween(String value1, String value2) {
            addCriterion("asset_no not between", value1, value2, "assetNo");
            return (Criteria) this;
        }

        public Criteria andAssetXhIsNull() {
            addCriterion("asset_xh is null");
            return (Criteria) this;
        }

        public Criteria andAssetXhIsNotNull() {
            addCriterion("asset_xh is not null");
            return (Criteria) this;
        }

        public Criteria andAssetXhEqualTo(String value) {
            addCriterion("asset_xh =", value, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhNotEqualTo(String value) {
            addCriterion("asset_xh <>", value, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhGreaterThan(String value) {
            addCriterion("asset_xh >", value, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhGreaterThanOrEqualTo(String value) {
            addCriterion("asset_xh >=", value, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhLessThan(String value) {
            addCriterion("asset_xh <", value, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhLessThanOrEqualTo(String value) {
            addCriterion("asset_xh <=", value, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhLike(String value) {
            addCriterion("asset_xh like", value, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhNotLike(String value) {
            addCriterion("asset_xh not like", value, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhIn(List<String> values) {
            addCriterion("asset_xh in", values, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhNotIn(List<String> values) {
            addCriterion("asset_xh not in", values, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhBetween(String value1, String value2) {
            addCriterion("asset_xh between", value1, value2, "assetXh");
            return (Criteria) this;
        }

        public Criteria andAssetXhNotBetween(String value1, String value2) {
            addCriterion("asset_xh not between", value1, value2, "assetXh");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andErrorDateIsNull() {
            addCriterion("error_date is null");
            return (Criteria) this;
        }

        public Criteria andErrorDateIsNotNull() {
            addCriterion("error_date is not null");
            return (Criteria) this;
        }

        public Criteria andErrorDateEqualTo(String value) {
            addCriterion("error_date =", value, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateNotEqualTo(String value) {
            addCriterion("error_date <>", value, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateGreaterThan(String value) {
            addCriterion("error_date >", value, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateGreaterThanOrEqualTo(String value) {
            addCriterion("error_date >=", value, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateLessThan(String value) {
            addCriterion("error_date <", value, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateLessThanOrEqualTo(String value) {
            addCriterion("error_date <=", value, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateLike(String value) {
            addCriterion("error_date like", value, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateNotLike(String value) {
            addCriterion("error_date not like", value, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateIn(List<String> values) {
            addCriterion("error_date in", values, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateNotIn(List<String> values) {
            addCriterion("error_date not in", values, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateBetween(String value1, String value2) {
            addCriterion("error_date between", value1, value2, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorDateNotBetween(String value1, String value2) {
            addCriterion("error_date not between", value1, value2, "errorDate");
            return (Criteria) this;
        }

        public Criteria andErrorContentIsNull() {
            addCriterion("error_content is null");
            return (Criteria) this;
        }

        public Criteria andErrorContentIsNotNull() {
            addCriterion("error_content is not null");
            return (Criteria) this;
        }

        public Criteria andErrorContentEqualTo(String value) {
            addCriterion("error_content =", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentNotEqualTo(String value) {
            addCriterion("error_content <>", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentGreaterThan(String value) {
            addCriterion("error_content >", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentGreaterThanOrEqualTo(String value) {
            addCriterion("error_content >=", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentLessThan(String value) {
            addCriterion("error_content <", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentLessThanOrEqualTo(String value) {
            addCriterion("error_content <=", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentLike(String value) {
            addCriterion("error_content like", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentNotLike(String value) {
            addCriterion("error_content not like", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentIn(List<String> values) {
            addCriterion("error_content in", values, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentNotIn(List<String> values) {
            addCriterion("error_content not in", values, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentBetween(String value1, String value2) {
            addCriterion("error_content between", value1, value2, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentNotBetween(String value1, String value2) {
            addCriterion("error_content not between", value1, value2, "errorContent");
            return (Criteria) this;
        }

        public Criteria andIsRepairIsNull() {
            addCriterion("is_repair is null");
            return (Criteria) this;
        }

        public Criteria andIsRepairIsNotNull() {
            addCriterion("is_repair is not null");
            return (Criteria) this;
        }

        public Criteria andIsRepairEqualTo(Integer value) {
            addCriterion("is_repair =", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairNotEqualTo(Integer value) {
            addCriterion("is_repair <>", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairGreaterThan(Integer value) {
            addCriterion("is_repair >", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_repair >=", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairLessThan(Integer value) {
            addCriterion("is_repair <", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairLessThanOrEqualTo(Integer value) {
            addCriterion("is_repair <=", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairIn(List<Integer> values) {
            addCriterion("is_repair in", values, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairNotIn(List<Integer> values) {
            addCriterion("is_repair not in", values, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairBetween(Integer value1, Integer value2) {
            addCriterion("is_repair between", value1, value2, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairNotBetween(Integer value1, Integer value2) {
            addCriterion("is_repair not between", value1, value2, "isRepair");
            return (Criteria) this;
        }

        public Criteria andSendPersonIsNull() {
            addCriterion("send_person is null");
            return (Criteria) this;
        }

        public Criteria andSendPersonIsNotNull() {
            addCriterion("send_person is not null");
            return (Criteria) this;
        }

        public Criteria andSendPersonEqualTo(String value) {
            addCriterion("send_person =", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonNotEqualTo(String value) {
            addCriterion("send_person <>", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonGreaterThan(String value) {
            addCriterion("send_person >", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonGreaterThanOrEqualTo(String value) {
            addCriterion("send_person >=", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonLessThan(String value) {
            addCriterion("send_person <", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonLessThanOrEqualTo(String value) {
            addCriterion("send_person <=", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonLike(String value) {
            addCriterion("send_person like", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonNotLike(String value) {
            addCriterion("send_person not like", value, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonIn(List<String> values) {
            addCriterion("send_person in", values, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonNotIn(List<String> values) {
            addCriterion("send_person not in", values, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonBetween(String value1, String value2) {
            addCriterion("send_person between", value1, value2, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andSendPersonNotBetween(String value1, String value2) {
            addCriterion("send_person not between", value1, value2, "sendPerson");
            return (Criteria) this;
        }

        public Criteria andIsRijianIsNull() {
            addCriterion("is_rijian is null");
            return (Criteria) this;
        }

        public Criteria andIsRijianIsNotNull() {
            addCriterion("is_rijian is not null");
            return (Criteria) this;
        }

        public Criteria andIsRijianEqualTo(Integer value) {
            addCriterion("is_rijian =", value, "isRijian");
            return (Criteria) this;
        }

        public Criteria andIsRijianNotEqualTo(Integer value) {
            addCriterion("is_rijian <>", value, "isRijian");
            return (Criteria) this;
        }

        public Criteria andIsRijianGreaterThan(Integer value) {
            addCriterion("is_rijian >", value, "isRijian");
            return (Criteria) this;
        }

        public Criteria andIsRijianGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_rijian >=", value, "isRijian");
            return (Criteria) this;
        }

        public Criteria andIsRijianLessThan(Integer value) {
            addCriterion("is_rijian <", value, "isRijian");
            return (Criteria) this;
        }

        public Criteria andIsRijianLessThanOrEqualTo(Integer value) {
            addCriterion("is_rijian <=", value, "isRijian");
            return (Criteria) this;
        }

        public Criteria andIsRijianIn(List<Integer> values) {
            addCriterion("is_rijian in", values, "isRijian");
            return (Criteria) this;
        }

        public Criteria andIsRijianNotIn(List<Integer> values) {
            addCriterion("is_rijian not in", values, "isRijian");
            return (Criteria) this;
        }

        public Criteria andIsRijianBetween(Integer value1, Integer value2) {
            addCriterion("is_rijian between", value1, value2, "isRijian");
            return (Criteria) this;
        }

        public Criteria andIsRijianNotBetween(Integer value1, Integer value2) {
            addCriterion("is_rijian not between", value1, value2, "isRijian");
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