package cn.itcast.ssm.po;

import java.util.ArrayList;
import java.util.List;

public class WorkCardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkCardExample() {
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

        public Criteria andTrackIdIsNull() {
            addCriterion("track_id is null");
            return (Criteria) this;
        }

        public Criteria andTrackIdIsNotNull() {
            addCriterion("track_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrackIdEqualTo(Integer value) {
            addCriterion("track_id =", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdNotEqualTo(Integer value) {
            addCriterion("track_id <>", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdGreaterThan(Integer value) {
            addCriterion("track_id >", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("track_id >=", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdLessThan(Integer value) {
            addCriterion("track_id <", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdLessThanOrEqualTo(Integer value) {
            addCriterion("track_id <=", value, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdIn(List<Integer> values) {
            addCriterion("track_id in", values, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdNotIn(List<Integer> values) {
            addCriterion("track_id not in", values, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdBetween(Integer value1, Integer value2) {
            addCriterion("track_id between", value1, value2, "trackId");
            return (Criteria) this;
        }

        public Criteria andTrackIdNotBetween(Integer value1, Integer value2) {
            addCriterion("track_id not between", value1, value2, "trackId");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNull() {
            addCriterion("card_id is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("card_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(Integer value) {
            addCriterion("card_id =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(Integer value) {
            addCriterion("card_id <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(Integer value) {
            addCriterion("card_id >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_id >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(Integer value) {
            addCriterion("card_id <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(Integer value) {
            addCriterion("card_id <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<Integer> values) {
            addCriterion("card_id in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<Integer> values) {
            addCriterion("card_id not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(Integer value1, Integer value2) {
            addCriterion("card_id between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(Integer value1, Integer value2) {
            addCriterion("card_id not between", value1, value2, "cardId");
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

        public Criteria andAssetIsNull() {
            addCriterion("asset is null");
            return (Criteria) this;
        }

        public Criteria andAssetIsNotNull() {
            addCriterion("asset is not null");
            return (Criteria) this;
        }

        public Criteria andAssetEqualTo(String value) {
            addCriterion("asset =", value, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetNotEqualTo(String value) {
            addCriterion("asset <>", value, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetGreaterThan(String value) {
            addCriterion("asset >", value, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetGreaterThanOrEqualTo(String value) {
            addCriterion("asset >=", value, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetLessThan(String value) {
            addCriterion("asset <", value, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetLessThanOrEqualTo(String value) {
            addCriterion("asset <=", value, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetLike(String value) {
            addCriterion("asset like", value, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetNotLike(String value) {
            addCriterion("asset not like", value, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetIn(List<String> values) {
            addCriterion("asset in", values, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetNotIn(List<String> values) {
            addCriterion("asset not in", values, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetBetween(String value1, String value2) {
            addCriterion("asset between", value1, value2, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetNotBetween(String value1, String value2) {
            addCriterion("asset not between", value1, value2, "asset");
            return (Criteria) this;
        }

        public Criteria andAssetStateIsNull() {
            addCriterion("asset_state is null");
            return (Criteria) this;
        }

        public Criteria andAssetStateIsNotNull() {
            addCriterion("asset_state is not null");
            return (Criteria) this;
        }

        public Criteria andAssetStateEqualTo(String value) {
            addCriterion("asset_state =", value, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateNotEqualTo(String value) {
            addCriterion("asset_state <>", value, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateGreaterThan(String value) {
            addCriterion("asset_state >", value, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateGreaterThanOrEqualTo(String value) {
            addCriterion("asset_state >=", value, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateLessThan(String value) {
            addCriterion("asset_state <", value, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateLessThanOrEqualTo(String value) {
            addCriterion("asset_state <=", value, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateLike(String value) {
            addCriterion("asset_state like", value, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateNotLike(String value) {
            addCriterion("asset_state not like", value, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateIn(List<String> values) {
            addCriterion("asset_state in", values, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateNotIn(List<String> values) {
            addCriterion("asset_state not in", values, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateBetween(String value1, String value2) {
            addCriterion("asset_state between", value1, value2, "assetState");
            return (Criteria) this;
        }

        public Criteria andAssetStateNotBetween(String value1, String value2) {
            addCriterion("asset_state not between", value1, value2, "assetState");
            return (Criteria) this;
        }

        public Criteria andMoldIsNull() {
            addCriterion("mold is null");
            return (Criteria) this;
        }

        public Criteria andMoldIsNotNull() {
            addCriterion("mold is not null");
            return (Criteria) this;
        }

        public Criteria andMoldEqualTo(String value) {
            addCriterion("mold =", value, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldNotEqualTo(String value) {
            addCriterion("mold <>", value, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldGreaterThan(String value) {
            addCriterion("mold >", value, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldGreaterThanOrEqualTo(String value) {
            addCriterion("mold >=", value, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldLessThan(String value) {
            addCriterion("mold <", value, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldLessThanOrEqualTo(String value) {
            addCriterion("mold <=", value, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldLike(String value) {
            addCriterion("mold like", value, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldNotLike(String value) {
            addCriterion("mold not like", value, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldIn(List<String> values) {
            addCriterion("mold in", values, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldNotIn(List<String> values) {
            addCriterion("mold not in", values, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldBetween(String value1, String value2) {
            addCriterion("mold between", value1, value2, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldNotBetween(String value1, String value2) {
            addCriterion("mold not between", value1, value2, "mold");
            return (Criteria) this;
        }

        public Criteria andMoldStateIsNull() {
            addCriterion("mold_state is null");
            return (Criteria) this;
        }

        public Criteria andMoldStateIsNotNull() {
            addCriterion("mold_state is not null");
            return (Criteria) this;
        }

        public Criteria andMoldStateEqualTo(String value) {
            addCriterion("mold_state =", value, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateNotEqualTo(String value) {
            addCriterion("mold_state <>", value, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateGreaterThan(String value) {
            addCriterion("mold_state >", value, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateGreaterThanOrEqualTo(String value) {
            addCriterion("mold_state >=", value, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateLessThan(String value) {
            addCriterion("mold_state <", value, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateLessThanOrEqualTo(String value) {
            addCriterion("mold_state <=", value, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateLike(String value) {
            addCriterion("mold_state like", value, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateNotLike(String value) {
            addCriterion("mold_state not like", value, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateIn(List<String> values) {
            addCriterion("mold_state in", values, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateNotIn(List<String> values) {
            addCriterion("mold_state not in", values, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateBetween(String value1, String value2) {
            addCriterion("mold_state between", value1, value2, "moldState");
            return (Criteria) this;
        }

        public Criteria andMoldStateNotBetween(String value1, String value2) {
            addCriterion("mold_state not between", value1, value2, "moldState");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNull() {
            addCriterion("total_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNotNull() {
            addCriterion("total_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumEqualTo(String value) {
            addCriterion("total_num =", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotEqualTo(String value) {
            addCriterion("total_num <>", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThan(String value) {
            addCriterion("total_num >", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThanOrEqualTo(String value) {
            addCriterion("total_num >=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThan(String value) {
            addCriterion("total_num <", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThanOrEqualTo(String value) {
            addCriterion("total_num <=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLike(String value) {
            addCriterion("total_num like", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotLike(String value) {
            addCriterion("total_num not like", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumIn(List<String> values) {
            addCriterion("total_num in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotIn(List<String> values) {
            addCriterion("total_num not in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumBetween(String value1, String value2) {
            addCriterion("total_num between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotBetween(String value1, String value2) {
            addCriterion("total_num not between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumIsNull() {
            addCriterion("hege_num is null");
            return (Criteria) this;
        }

        public Criteria andHegeNumIsNotNull() {
            addCriterion("hege_num is not null");
            return (Criteria) this;
        }

        public Criteria andHegeNumEqualTo(String value) {
            addCriterion("hege_num =", value, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumNotEqualTo(String value) {
            addCriterion("hege_num <>", value, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumGreaterThan(String value) {
            addCriterion("hege_num >", value, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumGreaterThanOrEqualTo(String value) {
            addCriterion("hege_num >=", value, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumLessThan(String value) {
            addCriterion("hege_num <", value, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumLessThanOrEqualTo(String value) {
            addCriterion("hege_num <=", value, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumLike(String value) {
            addCriterion("hege_num like", value, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumNotLike(String value) {
            addCriterion("hege_num not like", value, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumIn(List<String> values) {
            addCriterion("hege_num in", values, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumNotIn(List<String> values) {
            addCriterion("hege_num not in", values, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumBetween(String value1, String value2) {
            addCriterion("hege_num between", value1, value2, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andHegeNumNotBetween(String value1, String value2) {
            addCriterion("hege_num not between", value1, value2, "hegeNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumIsNull() {
            addCriterion("total_cipin_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumIsNotNull() {
            addCriterion("total_cipin_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumEqualTo(String value) {
            addCriterion("total_cipin_num =", value, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumNotEqualTo(String value) {
            addCriterion("total_cipin_num <>", value, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumGreaterThan(String value) {
            addCriterion("total_cipin_num >", value, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumGreaterThanOrEqualTo(String value) {
            addCriterion("total_cipin_num >=", value, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumLessThan(String value) {
            addCriterion("total_cipin_num <", value, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumLessThanOrEqualTo(String value) {
            addCriterion("total_cipin_num <=", value, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumLike(String value) {
            addCriterion("total_cipin_num like", value, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumNotLike(String value) {
            addCriterion("total_cipin_num not like", value, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumIn(List<String> values) {
            addCriterion("total_cipin_num in", values, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumNotIn(List<String> values) {
            addCriterion("total_cipin_num not in", values, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumBetween(String value1, String value2) {
            addCriterion("total_cipin_num between", value1, value2, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andTotalCipinNumNotBetween(String value1, String value2) {
            addCriterion("total_cipin_num not between", value1, value2, "totalCipinNum");
            return (Criteria) this;
        }

        public Criteria andProduceDateIsNull() {
            addCriterion("produce_date is null");
            return (Criteria) this;
        }

        public Criteria andProduceDateIsNotNull() {
            addCriterion("produce_date is not null");
            return (Criteria) this;
        }

        public Criteria andProduceDateEqualTo(String value) {
            addCriterion("produce_date =", value, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateNotEqualTo(String value) {
            addCriterion("produce_date <>", value, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateGreaterThan(String value) {
            addCriterion("produce_date >", value, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateGreaterThanOrEqualTo(String value) {
            addCriterion("produce_date >=", value, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateLessThan(String value) {
            addCriterion("produce_date <", value, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateLessThanOrEqualTo(String value) {
            addCriterion("produce_date <=", value, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateLike(String value) {
            addCriterion("produce_date like", value, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateNotLike(String value) {
            addCriterion("produce_date not like", value, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateIn(List<String> values) {
            addCriterion("produce_date in", values, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateNotIn(List<String> values) {
            addCriterion("produce_date not in", values, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateBetween(String value1, String value2) {
            addCriterion("produce_date between", value1, value2, "produceDate");
            return (Criteria) this;
        }

        public Criteria andProduceDateNotBetween(String value1, String value2) {
            addCriterion("produce_date not between", value1, value2, "produceDate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(String value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(String value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(String value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(String value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(String value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(String value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLike(String value) {
            addCriterion("price like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotLike(String value) {
            addCriterion("price not like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<String> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<String> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(String value1, String value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(String value1, String value2) {
            addCriterion("price not between", value1, value2, "price");
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