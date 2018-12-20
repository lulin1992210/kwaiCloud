package com.itmonster.kwai.cloud.auth.biz;

//import com.ace.cache.annotation.Cache;
//import com.ace.cache.annotation.CacheClear;

import com.itmonster.kwai.cloud.auth.constant.AdminCommonConstant;
import com.itmonster.kwai.cloud.auth.entity.Resource;
import com.itmonster.kwai.cloud.auth.mapper.ResourceMapper;
import com.itmonster.kwai.cloud.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author ITMonster Kwai
 * @create 2017-06-12 8:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceBiz extends BaseBiz<ResourceMapper, Resource> {
    @Override
//    @Cache(key="permission:menu")
    public List<Resource> selectListAll() {
        return super.selectListAll();
    }

    @Override
//    @CacheClear(keys={"permission:menu","permission"})
    public void insertSelective(Resource entity) {
        if (AdminCommonConstant.ROOT == entity.getParentId()) {
            entity.setPath("/" + entity.getCode());
        } else {
            Resource parent = this.selectById(entity.getParentId());
            entity.setPath(parent.getPath() + "/" + entity.getCode());
        }
        super.insertSelective(entity);
    }

    @Override
//    @CacheClear(keys={"permission:menu","permission"})
    public void updateById(Resource entity) {
        if (AdminCommonConstant.ROOT == entity.getParentId()) {
            entity.setPath("/" + entity.getCode());
        } else {
            Resource parent = this.selectById(entity.getParentId());
            entity.setPath(parent.getPath() + "/" + entity.getCode());
        }
        super.updateById(entity);
    }

    @Override
//    @CacheClear(keys={"permission:menu","permission"})
    public void updateSelectiveById(Resource entity) {
        super.updateSelectiveById(entity);
    }

    /**
     * 获取用户可以访问的菜单
     *
     * @param id
     * @return
     */
//    @Cache(key = "permission:menu:u{1}")
    public List<Resource> getUserAuthorityByUserId(String type,String id) {
        return mapper.selectAuthorityByUserId(type,id);
    }


    public List<Resource> getAllPermissionsByType(String type){
        return mapper.getAllPermissionsByType(type);
    }
    /**
     * 根据用户获取可以访问的系统
     *
     * @param id
     * @return
     */
    public List<Resource> getUserAuthoritySystemByUserId(String id) {
        return mapper.selectAuthoritySystemByUserId(id);
    }
}
