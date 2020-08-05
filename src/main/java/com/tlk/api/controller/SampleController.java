package com.tlk.api.controller;

import com.tlk.api.jpa.UserJpa;
import com.tlk.api.jpa.repository.UserJpaRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sample/")
public class SampleController {

    private static final Logger logger = LogManager.getLogger(SampleController.class);

    @Autowired
    private UserJpaRepository userJpaRepository;

    @RequestMapping(method = RequestMethod.GET, value = "getUserJpaList")
    @ApiOperation(value = "UserJpa테이블정보 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "키값", dataType = "string", paramType = "query", required = true),
    })
    public ResponseEntity getUserJpaList() {
        List<UserJpa> userJpaList = userJpaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        logger.info("info : " + userJpaList);
        return ResponseEntity.ok(userJpaList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUserJpa")
    @ApiOperation(value = "UserJpa 정보 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "키값", dataType = "string", paramType = "query", required = true),
    })
    public ResponseEntity getUserJpa(@RequestParam(value = "userName") String useName) {
        UserJpa userJpa = userJpaRepository.findAllByUserName(useName);
        logger.info("info : " + userJpa);
        return ResponseEntity.ok(userJpa);
    }

    @RequestMapping(method = RequestMethod.POST, value = "getUserJpa2")
    @ApiOperation(value = "UserJpa 정보 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "키값", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "키값", dataType = "string", paramType = "query", required = true),
    })
    public ResponseEntity getUserJpa(@RequestParam(value = "userName") String useName,
                                     @RequestParam(value = "password") String password) {
        UserJpa userJpa = userJpaRepository.findByUserNameAndPassword(useName, password);
        logger.info("info : " + userJpa);
        return ResponseEntity.ok(userJpa);
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveUserJpa")
    @ApiOperation(value = "UserJpa 정보 저장")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "키값", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "키값", dataType = "string", paramType = "query", required = true),
    })
    public ResponseEntity saveUserJpa(@RequestParam(value = "userName") String useName,
                                      @RequestParam(value = "password") String password) {
        UserJpa userJpa = new UserJpa(useName, password);
        userJpaRepository.save(userJpa);
        logger.info("info : " + userJpa);
        return ResponseEntity.ok(userJpa);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUserJpa")
    @ApiOperation(value = "UserJpa 정보 업데이트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "키값", dataType = "long", paramType = "query", required = false),
            @ApiImplicitParam(name = "userName", value = "키값", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "키값", dataType = "string", paramType = "query", required = true),
    })
    public ResponseEntity updateUserJpa(@RequestParam(value = "id", required = false) Long id,
                                        @RequestParam(value = "userName") String useName,
                                        @RequestParam(value = "password") String password) {
        UserJpa userJpa = new UserJpa(id, useName, password);
        userJpaRepository.save(userJpa);
        logger.info("info : " + userJpa);
        return ResponseEntity.ok(userJpa);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUserJpa")
    @ApiOperation(value = "UserJpa 정보 업데이트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "키값", dataType = "long", paramType = "query", required = true)
    })
    public ResponseEntity deleteUserJpa(@RequestParam(value = "id") Long id) {
        userJpaRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
}

