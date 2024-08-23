package com.week5Learning.Security.W5Learning.services;

import com.week5Learning.Security.W5Learning.entities.SessionEntity;
import com.week5Learning.Security.W5Learning.entities.UserEntity;
import com.week5Learning.Security.W5Learning.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(UserEntity user, String refreshToken){
        List<SessionEntity> userSessions = sessionRepository.findByUser(user);
        if(userSessions.size() == SESSION_LIMIT){
            userSessions.sort(Comparator.comparing(SessionEntity::getLastUsedAt));

            SessionEntity leastRecentlyUsedSession = userSessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }
        SessionEntity newSession = SessionEntity.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken){
        SessionEntity session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new SessionAuthenticationException("Session not found"));

    }
}
