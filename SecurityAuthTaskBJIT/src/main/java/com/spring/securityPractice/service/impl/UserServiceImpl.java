package com.spring.securityPractice.service.impl;

import com.spring.securityPractice.entity.RoleEntity;
import com.spring.securityPractice.entity.UserEntity;
import com.spring.securityPractice.model.UserCreateDTO;
import com.spring.securityPractice.model.UserLoginDTO;
import com.spring.securityPractice.model.UserReadDto;
import com.spring.securityPractice.repository.UserRepository;
import com.spring.securityPractice.service.UserService;
import com.spring.securityPractice.utils.JWTUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserReadDto createUser(UserCreateDTO user) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UsernameNotFoundException("Record already exists");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setRoles(new HashSet<>());

        // Assigning random roles
        Random random = new Random();
        String role;
        if(user.getEmail().startsWith("a")) role = "ROLE_ADMIN";
        else role = "ROLE_USER";
        userEntity.getRoles().add(new RoleEntity(role));
        if(user.getEmail().equals("both")) userEntity.getRoles()
                .add(new RoleEntity("ROLE_ADMIN"));

        String publicUserId = JWTUtils.generateUserID(10);
        userEntity.setUserId(publicUserId);
        UserEntity storedUserDetails = userRepository.save(userEntity);
        List<String> roles = new ArrayList<String>();
        for(RoleEntity roleEntity : storedUserDetails.getRoles()) {
            roles.add(roleEntity.getName());
        }
        String accessToken = JWTUtils.generateToken(publicUserId, roles);
        UserReadDto userReadDto = modelMapper.map(storedUserDetails, UserReadDto.class);
        userReadDto.setRoles(roles);
        userReadDto.setToken(accessToken);
        return userReadDto;
    }

    @Override
    public UserReadDto getUser(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        UserEntity userEntity = userOptional.get();
        UserReadDto returnValue = new UserReadDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        List<String> roles = new ArrayList<>();
        for(RoleEntity roleEntity : userEntity.getRoles()){
            roles.add(roleEntity.getName());
        }
        returnValue.setRoles(roles);
        return returnValue;
    }

    @Override
    public UserReadDto getUserByUserId(String userId) throws Exception {
        Optional<UserEntity> userOptional = userRepository.findByUserId(userId);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        UserEntity userEntity = userOptional.get();
        UserReadDto returnValue = new UserReadDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        List<String> roles = new ArrayList<>();
        for(RoleEntity roleEntity : userEntity.getRoles()){
            roles.add(roleEntity.getName());
        }
        returnValue.setRoles(roles);
        return returnValue;
    }

    @Override
    public UserReadDto login(UserLoginDTO userDto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<UserEntity> userOptional = userRepository.findByEmail(email);
       if(userOptional.isEmpty()){
           throw new UsernameNotFoundException("User not found");
       }
       UserEntity userEntity = userOptional.get();
       List<GrantedAuthority> roles = new ArrayList<>();
       for(RoleEntity roleEntity : userOptional.get().getRoles()){
           roles.add(new SimpleGrantedAuthority(roleEntity.getName()));
       }
       return new User(userEntity.getEmail(),userEntity.getPassword(),
               true,true,true,true,
               roles);
    }



    /*@Override
    public Authentication authenticate(UserLoginDTO userLoginDTO) {
        try{
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userLoginDTO.getEmail(),
                    userLoginDTO.getPassword()
            ));
        }
        catch(Exception e){
            return null;
        }
    }*/
}