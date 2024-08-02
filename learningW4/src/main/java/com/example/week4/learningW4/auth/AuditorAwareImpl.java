package com.example.week4.learningW4.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //get security context
        // get auth
        // get the principle
        // get the username
        return Optional.of("Harsh Kumar");
    }
}
