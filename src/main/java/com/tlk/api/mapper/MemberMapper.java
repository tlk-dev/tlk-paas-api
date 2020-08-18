package com.tlk.api.mapper;

import com.tlk.api.vo.MemberLoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    MemberLoginVO selectMemberAtLogin(@Param("loginId") String loginId, @Param("loginPassword") String loginPassword,
                                      @Param("memberType") Integer memberType);
}
