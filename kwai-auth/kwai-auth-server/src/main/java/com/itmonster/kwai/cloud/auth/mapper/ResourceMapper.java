package com.itmonster.kwai.cloud.auth.mapper;

import com.itmonster.kwai.cloud.auth.entity.Resource;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResourceMapper extends Mapper<Resource> {
    public List<Resource> selectMenuByAuthorityId(@Param("resourceType") String resourceType,@Param("authorityId") String authorityId, @Param("authorityType") String authorityType);

    /**
     * 根据用户和组的权限关系查找用户可访问菜单
     * @param userId
     * @return
     */
    public List<Resource> selectAuthorityByUserId(@Param("type") String type,@Param("userId") String userId);

    /**
     * 根据用户和组的权限关系查找用户可访问的系统
     * @param userId
     * @return
     */
    public List<Resource> selectAuthoritySystemByUserId(@Param("userId") String userId);

    List<Resource> getAllPermissionsByType(@Param("type")String type);
}