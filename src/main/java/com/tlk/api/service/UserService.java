package com.tlk.api.service;

import com.tlk.api.mapper.TestMapper;
import com.tlk.api.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OAuth2 설정 클래스
 */
@Service
public class UserService {

    @Autowired
    private TestMapper testMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserVO getUser(String userId, String pass) {
        return testMapper.getUser(userId, pass);
    }



}
