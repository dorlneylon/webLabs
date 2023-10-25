package com.nylon.fourth.security.sevices;


import com.nylon.fourth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nylon.fourth.entities.UserEntity;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthUsersDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthUsersDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (!user.isPresent())
            throw new UsernameNotFoundException("User Not Found with username: " + username);

        return AuthUsersDetails.build(user.get());
    }

}
