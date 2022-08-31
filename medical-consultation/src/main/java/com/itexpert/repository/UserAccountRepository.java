package com.itexpert.repository;

import com.itexpert.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserAccountRepository extends
    JpaRepository<UserAccount, Long>,
    QuerydslPredicateExecutor<UserAccount> {

}
