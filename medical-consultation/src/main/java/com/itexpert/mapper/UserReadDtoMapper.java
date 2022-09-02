package com.itexpert.mapper;

import com.itexpert.domain.UserAccount;
import com.itexpert.dto.UserReadDto;
import org.springframework.stereotype.Component;

@Component
public class UserReadDtoMapper implements Mapper<UserAccount, UserReadDto> {

  @Override
  public UserReadDto mapFrom(UserAccount userAccount) {
    return new UserReadDto(
        userAccount.getId(),
        userAccount.getEmail(),
        userAccount.getPassword()
    );
  }
}
