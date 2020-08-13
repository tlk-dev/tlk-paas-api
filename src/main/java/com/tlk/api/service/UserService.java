package com.tlk.api.service;

import com.tlk.api.jpa.UserJpa;
import com.tlk.api.jpa.repository.UserJpaRepository;
import com.tlk.api.mapper.TestMapper;
import com.tlk.api.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OAuth2 설정 클래스
 */
@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional(readOnly = true)
    public UserJpa getUser(String userId, String pass) {
        UserJpa userInfo = userJpaRepository.findByUserNameAndPassword(userId, pass);
        logger.info("userInfo ::: " + userInfo.toString());
        return userInfo;
    }
}
