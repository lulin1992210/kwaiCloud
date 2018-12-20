package com.itmonster.kwai.cloud.auth.mapper;

import com.itmonster.kwai.cloud.auth.entity.Group;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GroupMapper extends Mapper<Group> {
    public void deleteGroupMembersById(@Param("groupId") String groupId);
    public void deleteGroupLeadersById(@Param("groupId") String groupId);
    public void insertGroupMembersById(@Param("groupId") String groupId, @Param("userId") String userId);
    public void insertGroupLeadersById(@Param("groupId") String groupId, @Param("userId") String userId);
}