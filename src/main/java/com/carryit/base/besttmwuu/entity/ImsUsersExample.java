package com.carryit.base.besttmwuu.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ImsUsersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImsUsersExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNull() {
            addCriterion("groupid is null");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNotNull() {
            addCriterion("groupid is not null");
            return (Criteria) this;
        }

        public Criteria andGroupidEqualTo(Integer value) {
            addCriterion("groupid =", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotEqualTo(Integer value) {
            addCriterion("groupid <>", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThan(Integer value) {
            addCriterion("groupid >", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThanOrEqualTo(Integer value) {
            addCriterion("groupid >=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThan(Integer value) {
            addCriterion("groupid <", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThanOrEqualTo(Integer value) {
            addCriterion("groupid <=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidIn(List<Integer> values) {
            addCriterion("groupid in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotIn(List<Integer> values) {
            addCriterion("groupid not in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidBetween(Integer value1, Integer value2) {
            addCriterion("groupid between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotBetween(Integer value1, Integer value2) {
            addCriterion("groupid not between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andJoindateIsNull() {
            addCriterion("joindate is null");
            return (Criteria) this;
        }

        public Criteria andJoindateIsNotNull() {
            addCriterion("joindate is not null");
            return (Criteria) this;
        }

        public Criteria andJoindateEqualTo(Integer value) {
            addCriterion("joindate =", value, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoindateNotEqualTo(Integer value) {
            addCriterion("joindate <>", value, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoindateGreaterThan(Integer value) {
            addCriterion("joindate >", value, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoindateGreaterThanOrEqualTo(Integer value) {
            addCriterion("joindate >=", value, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoindateLessThan(Integer value) {
            addCriterion("joindate <", value, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoindateLessThanOrEqualTo(Integer value) {
            addCriterion("joindate <=", value, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoindateIn(List<Integer> values) {
            addCriterion("joindate in", values, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoindateNotIn(List<Integer> values) {
            addCriterion("joindate not in", values, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoindateBetween(Integer value1, Integer value2) {
            addCriterion("joindate between", value1, value2, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoindateNotBetween(Integer value1, Integer value2) {
            addCriterion("joindate not between", value1, value2, "joindate");
            return (Criteria) this;
        }

        public Criteria andJoinipIsNull() {
            addCriterion("joinip is null");
            return (Criteria) this;
        }

        public Criteria andJoinipIsNotNull() {
            addCriterion("joinip is not null");
            return (Criteria) this;
        }

        public Criteria andJoinipEqualTo(String value) {
            addCriterion("joinip =", value, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipNotEqualTo(String value) {
            addCriterion("joinip <>", value, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipGreaterThan(String value) {
            addCriterion("joinip >", value, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipGreaterThanOrEqualTo(String value) {
            addCriterion("joinip >=", value, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipLessThan(String value) {
            addCriterion("joinip <", value, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipLessThanOrEqualTo(String value) {
            addCriterion("joinip <=", value, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipLike(String value) {
            addCriterion("joinip like", value, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipNotLike(String value) {
            addCriterion("joinip not like", value, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipIn(List<String> values) {
            addCriterion("joinip in", values, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipNotIn(List<String> values) {
            addCriterion("joinip not in", values, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipBetween(String value1, String value2) {
            addCriterion("joinip between", value1, value2, "joinip");
            return (Criteria) this;
        }

        public Criteria andJoinipNotBetween(String value1, String value2) {
            addCriterion("joinip not between", value1, value2, "joinip");
            return (Criteria) this;
        }

        public Criteria andLastvisitIsNull() {
            addCriterion("lastvisit is null");
            return (Criteria) this;
        }

        public Criteria andLastvisitIsNotNull() {
            addCriterion("lastvisit is not null");
            return (Criteria) this;
        }

        public Criteria andLastvisitEqualTo(Integer value) {
            addCriterion("lastvisit =", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitNotEqualTo(Integer value) {
            addCriterion("lastvisit <>", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitGreaterThan(Integer value) {
            addCriterion("lastvisit >", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastvisit >=", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitLessThan(Integer value) {
            addCriterion("lastvisit <", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitLessThanOrEqualTo(Integer value) {
            addCriterion("lastvisit <=", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitIn(List<Integer> values) {
            addCriterion("lastvisit in", values, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitNotIn(List<Integer> values) {
            addCriterion("lastvisit not in", values, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitBetween(Integer value1, Integer value2) {
            addCriterion("lastvisit between", value1, value2, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitNotBetween(Integer value1, Integer value2) {
            addCriterion("lastvisit not between", value1, value2, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastipIsNull() {
            addCriterion("lastip is null");
            return (Criteria) this;
        }

        public Criteria andLastipIsNotNull() {
            addCriterion("lastip is not null");
            return (Criteria) this;
        }

        public Criteria andLastipEqualTo(String value) {
            addCriterion("lastip =", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipNotEqualTo(String value) {
            addCriterion("lastip <>", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipGreaterThan(String value) {
            addCriterion("lastip >", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipGreaterThanOrEqualTo(String value) {
            addCriterion("lastip >=", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipLessThan(String value) {
            addCriterion("lastip <", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipLessThanOrEqualTo(String value) {
            addCriterion("lastip <=", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipLike(String value) {
            addCriterion("lastip like", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipNotLike(String value) {
            addCriterion("lastip not like", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipIn(List<String> values) {
            addCriterion("lastip in", values, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipNotIn(List<String> values) {
            addCriterion("lastip not in", values, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipBetween(String value1, String value2) {
            addCriterion("lastip between", value1, value2, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipNotBetween(String value1, String value2) {
            addCriterion("lastip not between", value1, value2, "lastip");
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

        public Criteria andUcuseridIsNull() {
            addCriterion("ucuserid is null");
            return (Criteria) this;
        }

        public Criteria andUcuseridIsNotNull() {
            addCriterion("ucuserid is not null");
            return (Criteria) this;
        }

        public Criteria andUcuseridEqualTo(Integer value) {
            addCriterion("ucuserid =", value, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andUcuseridNotEqualTo(Integer value) {
            addCriterion("ucuserid <>", value, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andUcuseridGreaterThan(Integer value) {
            addCriterion("ucuserid >", value, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andUcuseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("ucuserid >=", value, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andUcuseridLessThan(Integer value) {
            addCriterion("ucuserid <", value, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andUcuseridLessThanOrEqualTo(Integer value) {
            addCriterion("ucuserid <=", value, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andUcuseridIn(List<Integer> values) {
            addCriterion("ucuserid in", values, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andUcuseridNotIn(List<Integer> values) {
            addCriterion("ucuserid not in", values, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andUcuseridBetween(Integer value1, Integer value2) {
            addCriterion("ucuserid between", value1, value2, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andUcuseridNotBetween(Integer value1, Integer value2) {
            addCriterion("ucuserid not between", value1, value2, "ucuserid");
            return (Criteria) this;
        }

        public Criteria andViptimeIsNull() {
            addCriterion("viptime is null");
            return (Criteria) this;
        }

        public Criteria andViptimeIsNotNull() {
            addCriterion("viptime is not null");
            return (Criteria) this;
        }

        public Criteria andViptimeEqualTo(String value) {
            addCriterion("viptime =", value, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeNotEqualTo(String value) {
            addCriterion("viptime <>", value, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeGreaterThan(String value) {
            addCriterion("viptime >", value, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeGreaterThanOrEqualTo(String value) {
            addCriterion("viptime >=", value, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeLessThan(String value) {
            addCriterion("viptime <", value, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeLessThanOrEqualTo(String value) {
            addCriterion("viptime <=", value, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeLike(String value) {
            addCriterion("viptime like", value, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeNotLike(String value) {
            addCriterion("viptime not like", value, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeIn(List<String> values) {
            addCriterion("viptime in", values, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeNotIn(List<String> values) {
            addCriterion("viptime not in", values, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeBetween(String value1, String value2) {
            addCriterion("viptime between", value1, value2, "viptime");
            return (Criteria) this;
        }

        public Criteria andViptimeNotBetween(String value1, String value2) {
            addCriterion("viptime not between", value1, value2, "viptime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Integer value) {
            addCriterion("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Integer value) {
            addCriterion("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Integer value) {
            addCriterion("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Integer value) {
            addCriterion("starttime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Integer value) {
            addCriterion("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Integer> values) {
            addCriterion("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Integer> values) {
            addCriterion("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Integer value1, Integer value2) {
            addCriterion("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Integer value1, Integer value2) {
            addCriterion("starttime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Integer value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Integer value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Integer value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Integer value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Integer value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Integer> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Integer> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Integer value1, Integer value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andCredit1IsNull() {
            addCriterion("credit1 is null");
            return (Criteria) this;
        }

        public Criteria andCredit1IsNotNull() {
            addCriterion("credit1 is not null");
            return (Criteria) this;
        }

        public Criteria andCredit1EqualTo(BigDecimal value) {
            addCriterion("credit1 =", value, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit1NotEqualTo(BigDecimal value) {
            addCriterion("credit1 <>", value, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit1GreaterThan(BigDecimal value) {
            addCriterion("credit1 >", value, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit1GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("credit1 >=", value, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit1LessThan(BigDecimal value) {
            addCriterion("credit1 <", value, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit1LessThanOrEqualTo(BigDecimal value) {
            addCriterion("credit1 <=", value, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit1In(List<BigDecimal> values) {
            addCriterion("credit1 in", values, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit1NotIn(List<BigDecimal> values) {
            addCriterion("credit1 not in", values, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit1Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit1 between", value1, value2, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit1NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit1 not between", value1, value2, "credit1");
            return (Criteria) this;
        }

        public Criteria andCredit2IsNull() {
            addCriterion("credit2 is null");
            return (Criteria) this;
        }

        public Criteria andCredit2IsNotNull() {
            addCriterion("credit2 is not null");
            return (Criteria) this;
        }

        public Criteria andCredit2EqualTo(BigDecimal value) {
            addCriterion("credit2 =", value, "credit2");
            return (Criteria) this;
        }

        public Criteria andCredit2NotEqualTo(BigDecimal value) {
            addCriterion("credit2 <>", value, "credit2");
            return (Criteria) this;
        }

        public Criteria andCredit2GreaterThan(BigDecimal value) {
            addCriterion("credit2 >", value, "credit2");
            return (Criteria) this;
        }

        public Criteria andCredit2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("credit2 >=", value, "credit2");
            return (Criteria) this;
        }

        public Criteria andCredit2LessThan(BigDecimal value) {
            addCriterion("credit2 <", value, "credit2");
            return (Criteria) this;
        }

        public Criteria andCredit2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("credit2 <=", value, "credit2");
            return (Criteria) this;
        }

        public Criteria andCredit2In(List<BigDecimal> values) {
            addCriterion("credit2 in", values, "credit2");
            return (Criteria) this;
        }

        public Criteria andCredit2NotIn(List<BigDecimal> values) {
            addCriterion("credit2 not in", values, "credit2");
            return (Criteria) this;
        }

        public Criteria andCredit2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit2 between", value1, value2, "credit2");
            return (Criteria) this;
        }

        public Criteria andCredit2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit2 not between", value1, value2, "credit2");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andOwnerUidIsNull() {
            addCriterion("owner_uid is null");
            return (Criteria) this;
        }

        public Criteria andOwnerUidIsNotNull() {
            addCriterion("owner_uid is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerUidEqualTo(Integer value) {
            addCriterion("owner_uid =", value, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andOwnerUidNotEqualTo(Integer value) {
            addCriterion("owner_uid <>", value, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andOwnerUidGreaterThan(Integer value) {
            addCriterion("owner_uid >", value, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andOwnerUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("owner_uid >=", value, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andOwnerUidLessThan(Integer value) {
            addCriterion("owner_uid <", value, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andOwnerUidLessThanOrEqualTo(Integer value) {
            addCriterion("owner_uid <=", value, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andOwnerUidIn(List<Integer> values) {
            addCriterion("owner_uid in", values, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andOwnerUidNotIn(List<Integer> values) {
            addCriterion("owner_uid not in", values, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andOwnerUidBetween(Integer value1, Integer value2) {
            addCriterion("owner_uid between", value1, value2, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andOwnerUidNotBetween(Integer value1, Integer value2) {
            addCriterion("owner_uid not between", value1, value2, "ownerUid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidIsNull() {
            addCriterion("founder_groupid is null");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidIsNotNull() {
            addCriterion("founder_groupid is not null");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidEqualTo(Byte value) {
            addCriterion("founder_groupid =", value, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidNotEqualTo(Byte value) {
            addCriterion("founder_groupid <>", value, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidGreaterThan(Byte value) {
            addCriterion("founder_groupid >", value, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidGreaterThanOrEqualTo(Byte value) {
            addCriterion("founder_groupid >=", value, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidLessThan(Byte value) {
            addCriterion("founder_groupid <", value, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidLessThanOrEqualTo(Byte value) {
            addCriterion("founder_groupid <=", value, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidIn(List<Byte> values) {
            addCriterion("founder_groupid in", values, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidNotIn(List<Byte> values) {
            addCriterion("founder_groupid not in", values, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidBetween(Byte value1, Byte value2) {
            addCriterion("founder_groupid between", value1, value2, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andFounderGroupidNotBetween(Byte value1, Byte value2) {
            addCriterion("founder_groupid not between", value1, value2, "founderGroupid");
            return (Criteria) this;
        }

        public Criteria andAgentidIsNull() {
            addCriterion("agentid is null");
            return (Criteria) this;
        }

        public Criteria andAgentidIsNotNull() {
            addCriterion("agentid is not null");
            return (Criteria) this;
        }

        public Criteria andAgentidEqualTo(Integer value) {
            addCriterion("agentid =", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotEqualTo(Integer value) {
            addCriterion("agentid <>", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidGreaterThan(Integer value) {
            addCriterion("agentid >", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("agentid >=", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidLessThan(Integer value) {
            addCriterion("agentid <", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidLessThanOrEqualTo(Integer value) {
            addCriterion("agentid <=", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidIn(List<Integer> values) {
            addCriterion("agentid in", values, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotIn(List<Integer> values) {
            addCriterion("agentid not in", values, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidBetween(Integer value1, Integer value2) {
            addCriterion("agentid between", value1, value2, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotBetween(Integer value1, Integer value2) {
            addCriterion("agentid not between", value1, value2, "agentid");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeIsNull() {
            addCriterion("register_type is null");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeIsNotNull() {
            addCriterion("register_type is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeEqualTo(Byte value) {
            addCriterion("register_type =", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeNotEqualTo(Byte value) {
            addCriterion("register_type <>", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeGreaterThan(Byte value) {
            addCriterion("register_type >", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("register_type >=", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeLessThan(Byte value) {
            addCriterion("register_type <", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeLessThanOrEqualTo(Byte value) {
            addCriterion("register_type <=", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeIn(List<Byte> values) {
            addCriterion("register_type in", values, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeNotIn(List<Byte> values) {
            addCriterion("register_type not in", values, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeBetween(Byte value1, Byte value2) {
            addCriterion("register_type between", value1, value2, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("register_type not between", value1, value2, "registerType");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(Integer value) {
            addCriterion("age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(Integer value) {
            addCriterion("age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(Integer value) {
            addCriterion("age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(Integer value) {
            addCriterion("age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(Integer value) {
            addCriterion("age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<Integer> values) {
            addCriterion("age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<Integer> values) {
            addCriterion("age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(Integer value1, Integer value2) {
            addCriterion("age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("idCard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("idCard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("idCard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("idCard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("idCard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("idCard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("idCard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("idCard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("idCard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("idCard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("idCard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("idCard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("idCard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("idCard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andSpecialityIsNull() {
            addCriterion("speciality is null");
            return (Criteria) this;
        }

        public Criteria andSpecialityIsNotNull() {
            addCriterion("speciality is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialityEqualTo(String value) {
            addCriterion("speciality =", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityNotEqualTo(String value) {
            addCriterion("speciality <>", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityGreaterThan(String value) {
            addCriterion("speciality >", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityGreaterThanOrEqualTo(String value) {
            addCriterion("speciality >=", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityLessThan(String value) {
            addCriterion("speciality <", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityLessThanOrEqualTo(String value) {
            addCriterion("speciality <=", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityLike(String value) {
            addCriterion("speciality like", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityNotLike(String value) {
            addCriterion("speciality not like", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityIn(List<String> values) {
            addCriterion("speciality in", values, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityNotIn(List<String> values) {
            addCriterion("speciality not in", values, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityBetween(String value1, String value2) {
            addCriterion("speciality between", value1, value2, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityNotBetween(String value1, String value2) {
            addCriterion("speciality not between", value1, value2, "speciality");
            return (Criteria) this;
        }

        public Criteria andNeedIsNull() {
            addCriterion("need is null");
            return (Criteria) this;
        }

        public Criteria andNeedIsNotNull() {
            addCriterion("need is not null");
            return (Criteria) this;
        }

        public Criteria andNeedEqualTo(String value) {
            addCriterion("need =", value, "need");
            return (Criteria) this;
        }

        public Criteria andNeedNotEqualTo(String value) {
            addCriterion("need <>", value, "need");
            return (Criteria) this;
        }

        public Criteria andNeedGreaterThan(String value) {
            addCriterion("need >", value, "need");
            return (Criteria) this;
        }

        public Criteria andNeedGreaterThanOrEqualTo(String value) {
            addCriterion("need >=", value, "need");
            return (Criteria) this;
        }

        public Criteria andNeedLessThan(String value) {
            addCriterion("need <", value, "need");
            return (Criteria) this;
        }

        public Criteria andNeedLessThanOrEqualTo(String value) {
            addCriterion("need <=", value, "need");
            return (Criteria) this;
        }

        public Criteria andNeedLike(String value) {
            addCriterion("need like", value, "need");
            return (Criteria) this;
        }

        public Criteria andNeedNotLike(String value) {
            addCriterion("need not like", value, "need");
            return (Criteria) this;
        }

        public Criteria andNeedIn(List<String> values) {
            addCriterion("need in", values, "need");
            return (Criteria) this;
        }

        public Criteria andNeedNotIn(List<String> values) {
            addCriterion("need not in", values, "need");
            return (Criteria) this;
        }

        public Criteria andNeedBetween(String value1, String value2) {
            addCriterion("need between", value1, value2, "need");
            return (Criteria) this;
        }

        public Criteria andNeedNotBetween(String value1, String value2) {
            addCriterion("need not between", value1, value2, "need");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andIssingleIsNull() {
            addCriterion("isSingle is null");
            return (Criteria) this;
        }

        public Criteria andIssingleIsNotNull() {
            addCriterion("isSingle is not null");
            return (Criteria) this;
        }

        public Criteria andIssingleEqualTo(String value) {
            addCriterion("isSingle =", value, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleNotEqualTo(String value) {
            addCriterion("isSingle <>", value, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleGreaterThan(String value) {
            addCriterion("isSingle >", value, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleGreaterThanOrEqualTo(String value) {
            addCriterion("isSingle >=", value, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleLessThan(String value) {
            addCriterion("isSingle <", value, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleLessThanOrEqualTo(String value) {
            addCriterion("isSingle <=", value, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleLike(String value) {
            addCriterion("isSingle like", value, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleNotLike(String value) {
            addCriterion("isSingle not like", value, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleIn(List<String> values) {
            addCriterion("isSingle in", values, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleNotIn(List<String> values) {
            addCriterion("isSingle not in", values, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleBetween(String value1, String value2) {
            addCriterion("isSingle between", value1, value2, "issingle");
            return (Criteria) this;
        }

        public Criteria andIssingleNotBetween(String value1, String value2) {
            addCriterion("isSingle not between", value1, value2, "issingle");
            return (Criteria) this;
        }

        public Criteria andCorporatenameIsNull() {
            addCriterion("corporateName is null");
            return (Criteria) this;
        }

        public Criteria andCorporatenameIsNotNull() {
            addCriterion("corporateName is not null");
            return (Criteria) this;
        }

        public Criteria andCorporatenameEqualTo(String value) {
            addCriterion("corporateName =", value, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameNotEqualTo(String value) {
            addCriterion("corporateName <>", value, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameGreaterThan(String value) {
            addCriterion("corporateName >", value, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameGreaterThanOrEqualTo(String value) {
            addCriterion("corporateName >=", value, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameLessThan(String value) {
            addCriterion("corporateName <", value, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameLessThanOrEqualTo(String value) {
            addCriterion("corporateName <=", value, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameLike(String value) {
            addCriterion("corporateName like", value, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameNotLike(String value) {
            addCriterion("corporateName not like", value, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameIn(List<String> values) {
            addCriterion("corporateName in", values, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameNotIn(List<String> values) {
            addCriterion("corporateName not in", values, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameBetween(String value1, String value2) {
            addCriterion("corporateName between", value1, value2, "corporatename");
            return (Criteria) this;
        }

        public Criteria andCorporatenameNotBetween(String value1, String value2) {
            addCriterion("corporateName not between", value1, value2, "corporatename");
            return (Criteria) this;
        }

        public Criteria andMailboxIsNull() {
            addCriterion("mailbox is null");
            return (Criteria) this;
        }

        public Criteria andMailboxIsNotNull() {
            addCriterion("mailbox is not null");
            return (Criteria) this;
        }

        public Criteria andMailboxEqualTo(String value) {
            addCriterion("mailbox =", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotEqualTo(String value) {
            addCriterion("mailbox <>", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxGreaterThan(String value) {
            addCriterion("mailbox >", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxGreaterThanOrEqualTo(String value) {
            addCriterion("mailbox >=", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxLessThan(String value) {
            addCriterion("mailbox <", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxLessThanOrEqualTo(String value) {
            addCriterion("mailbox <=", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxLike(String value) {
            addCriterion("mailbox like", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotLike(String value) {
            addCriterion("mailbox not like", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxIn(List<String> values) {
            addCriterion("mailbox in", values, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotIn(List<String> values) {
            addCriterion("mailbox not in", values, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxBetween(String value1, String value2) {
            addCriterion("mailbox between", value1, value2, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotBetween(String value1, String value2) {
            addCriterion("mailbox not between", value1, value2, "mailbox");
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