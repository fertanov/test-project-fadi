package org.machinestalk.service.impl;
import org.machinestalk.api.dto.UserDto;
import org.machinestalk.api.dto.UserRegistrationDto;
import org.machinestalk.domain.Address;
import org.machinestalk.domain.User;
import org.machinestalk.repository.UserRepository;
import org.machinestalk.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public UserDto registerUser(final UserRegistrationDto userRegistrationDto) {
    User mappedUser = modelMapper.map(userRegistrationDto, User.class);
    if (Objects.nonNull(userRegistrationDto.getPrincipalAddress())) {
      Address principalAddress = modelMapper.map(userRegistrationDto.getPrincipalAddress(), Address.class);
      mappedUser.setAddresses(Collections.singleton(principalAddress));
    }
    return modelMapper.map(userRepository.save(mappedUser), UserDto.class);
  }

  @Override
  public Mono<UserDto> getById(final long id) {
    return Mono.justOrEmpty(modelMapper.map(userRepository.findById(id), UserDto.class));
  }
}