package cn.itcast.ssm.po;

import java.util.ArrayList;
import java.util.List;

public class CipinExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CipinExample() {
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

        public Criteria andCipinIdIsNull() {
            addCriterion("cipin_id is null");
            return (Criteria) this;
        }

        public Criteria andCipinIdIsNotNull() {
            addCriterion("cipin_id is not null");
            return (Criteria) this;
        }

        public Criteria andCipinIdEqualTo(Integer value) {
            addCriterion("cipin_id =", value, "cipinId");
            return (Criteria) this;
        }

        public Criteria andCipinIdNotEqualTo(Integer value) {
            addCriterion("cipin_id <>", value, "cipinId");
            return (Criteria) this;
        }

        public Criteria andCipinIdGreaterThan(Integer value) {
            addCriterion("cipin_id >", value, "cipinId");
            return (Criteria) this;
        }

        public Criteria andCipinIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cipin_id >=", value, "cipinId");
            return (Criteria) this;
        }

        public Criteria andCipinIdLessThan(Integer value) {
            addCriterion("cipin_id <", value, "cipinId");
            return (Criteria) this;
        }

        public Criteria andCipinIdLessThanOrEqualTo(Integer value) {
            addCriterion("cipin_id <=", value, "cipinId");
            return (Criteria) this;
        }

        public Criteria andCipinIdIn(List<Integer> values) {
            addCriterion("cipin_id in", values, "cipinId");
            return (Criteria) this;
        }

        public Criteria andCipinIdNotIn(List<Integer> values) {
            addCriterion("cipin_id not in", values, "cipinId");
            return (Criteria) this;
        }

        public Criteria andCipinIdBetween(Integer value1, Integer value2) {
            addCriterion("cipin_id between", value1, value2, "cipinId");
            return (Criteria) this;
        }

        public Criteria andCipinIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cipin_id not between", value1, value2, "cipinId");
            return (Criteria) this;
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

        public Criteria andCipinTypeIsNull() {
            addCriterion("cipin_type is null");
            return (Criteria) this;
        }

        public Criteria andCipinTypeIsNotNull() {
            addCriterion("cipin_type is not null");
            return (Criteria) this;
        }

        public Criteria andCipinTypeEqualTo(String value) {
            addCriterion("cipin_type =", value, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeNotEqualTo(String value) {
            addCriterion("cipin_type <>", value, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeGreaterThan(String value) {
            addCriterion("cipin_type >", value, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cipin_type >=", value, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeLessThan(String value) {
            addCriterion("cipin_type <", value, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeLessThanOrEqualTo(String value) {
            addCriterion("cipin_type <=", value, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeLike(String value) {
            addCriterion("cipin_type like", value, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeNotLike(String value) {
            addCriterion("cipin_type not like", value, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeIn(List<String> values) {
            addCriterion("cipin_type in", values, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeNotIn(List<String> values) {
            addCriterion("cipin_type not in", values, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeBetween(String value1, String value2) {
            addCriterion("cipin_type between", value1, value2, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinTypeNotBetween(String value1, String value2) {
            addCriterion("cipin_type not between", value1, value2, "cipinType");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesIsNull() {
            addCriterion("cipin_species is null");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesIsNotNull() {
            addCriterion("cipin_species is not null");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesEqualTo(String value) {
            addCriterion("cipin_species =", value, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesNotEqualTo(String value) {
            addCriterion("cipin_species <>", value, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesGreaterThan(String value) {
            addCriterion("cipin_species >", value, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesGreaterThanOrEqualTo(String value) {
            addCriterion("cipin_species >=", value, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesLessThan(String value) {
            addCriterion("cipin_species <", value, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesLessThanOrEqualTo(String value) {
            addCriterion("cipin_species <=", value, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesLike(String value) {
            addCriterion("cipin_species like", value, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesNotLike(String value) {
            addCriterion("cipin_species not like", value, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesIn(List<String> values) {
            addCriterion("cipin_species in", values, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesNotIn(List<String> values) {
            addCriterion("cipin_species not in", values, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesBetween(String value1, String value2) {
            addCriterion("cipin_species between", value1, value2, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinSpeciesNotBetween(String value1, String value2) {
            addCriterion("cipin_species not between", value1, value2, "cipinSpecies");
            return (Criteria) this;
        }

        public Criteria andCipinNumIsNull() {
            addCriterion("cipin_num is null");
            return (Criteria) this;
        }

        public Criteria andCipinNumIsNotNull() {
            addCriterion("cipin_num is not null");
            return (Criteria) this;
        }

        public Criteria andCipinNumEqualTo(String value) {
            addCriterion("cipin_num =", value, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumNotEqualTo(String value) {
            addCriterion("cipin_num <>", value, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumGreaterThan(String value) {
            addCriterion("cipin_num >", value, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumGreaterThanOrEqualTo(String value) {
            addCriterion("cipin_num >=", value, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumLessThan(String value) {
            addCriterion("cipin_num <", value, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumLessThanOrEqualTo(String value) {
            addCriterion("cipin_num <=", value, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumLike(String value) {
            addCriterion("cipin_num like", value, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumNotLike(String value) {
            addCriterion("cipin_num not like", value, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumIn(List<String> values) {
            addCriterion("cipin_num in", values, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumNotIn(List<String> values) {
            addCriterion("cipin_num not in", values, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumBetween(String value1, String value2) {
            addCriterion("cipin_num between", value1, value2, "cipinNum");
            return (Criteria) this;
        }

        public Criteria andCipinNumNotBetween(String value1, String value2) {
            addCriterion("cipin_num not between", value1, value2, "cipinNum");
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