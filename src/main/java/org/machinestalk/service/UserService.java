package org.machinestalk.service;
import org.machinestalk.api.dto.UserDto;
import org.machinestalk.api.dto.UserRegistrationDto;
import org.machinestalk.domain.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService {

  /**
   * Register a new user.
   *
   * @param userRegistrationDto dto for user registration.
   * @return user entity.
   */
  UserDto registerUser(@NotNull @Valid UserRegistrationDto userRegistrationDto);

  /**
   * Get user by its id.
   *
   * @param id user id.
   * @return user entity.
   */
  Mono<UserDto> getById(long id);
}