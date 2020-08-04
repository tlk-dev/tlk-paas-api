package com.tlk.api.mapper;

import com.tlk.api.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestMapper {

    UserVO getUser(@Param("username") String userName, @Param("password") String password);
}
