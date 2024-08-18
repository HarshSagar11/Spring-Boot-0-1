package com.week5Learning.Security.W5Learning.services;

import com.week5Learning.Security.W5Learning.dto.SignupDto;
import com.week5Learning.Security.W5Learning.dto.UserDto;
import com.week5Learning.Security.W5Learning.entities.UserEntity;
import com.week5Learning.Security.W5Learning.exceptions.ResourceNotFoundException;
import com.week5Learning.Security.W5Learning.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new BadCredentialsException("User with email "+username+" does not exist."));
    }

    public UserEntity getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with id "+userId+" does not exist."));
    }

    public UserDto signUp(SignupDto signupDto) {
        Optional<UserEntity> user = userRepository.findByEmail(signupDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User already exist with this email : " + signupDto.getEmail());
        }
        UserEntity toBeCreatedUser = modelMapper.map(signupDto, UserEntity.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        UserEntity savedEntity = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedEntity,UserDto.class);
    }

}
