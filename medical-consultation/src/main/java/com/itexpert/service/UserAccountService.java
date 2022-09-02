package com.itexpert.service;

import com.itexpert.dto.UserAccountFilter;
import com.itexpert.dto.UserCreateEditDto;
import com.itexpert.dto.UserReadDto;

public interface UserAccountService extends BaseService<UserReadDto, UserCreateEditDto, UserAccountFilter> {

}
