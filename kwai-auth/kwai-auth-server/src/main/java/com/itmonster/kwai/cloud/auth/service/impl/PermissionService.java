package com.itmonster.kwai.cloud.auth.service.impl;

import com.itmonster.kwai.cloud.api.vo.authority.PermissionInfo;
import com.itmonster.kwai.cloud.api.vo.user.UserInfo;
import com.itmonster.kwai.cloud.auth.biz.ResourceBiz;
import com.itmonster.kwai.cloud.auth.biz.UserBiz;
import com.itmonster.kwai.cloud.auth.constant.AdminCommonConstant;
import com.itmonster.kwai.cloud.auth.entity.Resource;
import com.itmonster.kwai.cloud.auth.entity.User;
import com.itmonster.kwai.cloud.auth.util.user.JwtTokenUtil;
import com.itmonster.kwai.cloud.auth.vo.FrontUser;
import com.itmonster.kwai.cloud.auth.vo.ResourceTree;
import com.itmonster.kwai.cloud.common.constant.CommonConstants;
import com.itmonster.kwai.cloud.common.util.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ace on 2017/9/12.
 */
@Service
public class PermissionService {
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private ResourceBiz menuBiz;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public UserInfo getUserByUsername(String username) {
        UserInfo info = new UserInfo();
        User user = userBiz.getUserByUsername(username);
        BeanUtils.copyProperties(user, info);
        info.setId(user.getId().toString());
        return info;
    }

    public UserInfo validate(String username,String password){
        UserInfo info = new UserInfo();
        User user = userBiz.getUserByUsername(username);
        if (encoder.matches(password, user.getPassword())) {
            BeanUtils.copyProperties(user, info);
            info.setId(user.getId().toString());
        }
        return info;
    }

    public List<PermissionInfo> getAllPermission() {
        List<Resource> menus = menuBiz.getAllPermissionsByType(AdminCommonConstant.RESOURCE_TYPE_MENU);
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Resource> elements = menuBiz.getAllPermissionsByType(AdminCommonConstant.RESOURCE_TYPE_BTN);
        element2permission(result, elements);
        return result;
    }

    private void menu2permission(List<Resource> menus, List<PermissionInfo> result) {
        PermissionInfo info;
        for (Resource menu : menus) {
            if (StringUtils.isBlank(menu.getHref())) {
                menu.setHref("/" + menu.getCode());
            }
            info = new PermissionInfo();
            info.setCode(menu.getCode());
            info.setType(AdminCommonConstant.RESOURCE_TYPE_MENU);
            info.setName(AdminCommonConstant.RESOURCE_ACTION_VISIT);
            String uri = menu.getHref();
            if (!uri.startsWith("/")) {
                uri = "/" + uri;
            }
            info.setUri(uri);
            info.setMethod(AdminCommonConstant.RESOURCE_REQUEST_METHOD_GET);
            result.add(info
            );
            info.setMenu(menu.getTitle());
        }
    }

    public List<PermissionInfo> getPermissionByUsername(String username) {
        User user = userBiz.getUserByUsername(username);
        List<Resource> menus = menuBiz.getUserAuthorityByUserId(AdminCommonConstant.RESOURCE_TYPE_MENU,user.getId());
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Resource> elements = menuBiz.getUserAuthorityByUserId(AdminCommonConstant.RESOURCE_TYPE_MENU,user.getId());
        element2permission(result, elements);
        return result;
    }

    private void element2permission(List<PermissionInfo> result, List<Resource> elements) {
        PermissionInfo info;
        for (Resource element : elements) {
            info = new PermissionInfo();
            info.setCode(element.getCode());
            info.setType(element.getType());
            info.setUri(element.getHref());
            info.setMethod(element.getMethod());
            info.setName(element.getTitle());
            info.setMenu(element.getParentId());
            result.add(info);
        }
    }


    private List<ResourceTree> getMenuTree(List<Resource> menus, String root) {
        List<ResourceTree> trees = new ArrayList<ResourceTree>();
        ResourceTree node = null;
        for (Resource menu : menus) {
            node = new ResourceTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }

    public FrontUser getUserInfo(String token) throws Exception {
        String username = jwtTokenUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        UserInfo user = this.getUserByUsername(username);
        FrontUser frontUser = new FrontUser();
        BeanUtils.copyProperties(user, frontUser);
        List<PermissionInfo> permissionInfos = this.getPermissionByUsername(username);
        Stream<PermissionInfo> menus = permissionInfos.parallelStream().filter((permission) -> {
            return permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setMenus(menus.collect(Collectors.toList()));
        Stream<PermissionInfo> elements = permissionInfos.parallelStream().filter((permission) -> {
            return !permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setElements(elements.collect(Collectors.toList()));
        return frontUser;
    }

    public List<ResourceTree> getMenusByUsername(String token) throws Exception {
        String username = jwtTokenUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        User user = userBiz.getUserByUsername(username);
        List<Resource> menus = menuBiz.getUserAuthorityByUserId(AdminCommonConstant.RESOURCE_TYPE_MENU,user.getId());
        return getMenuTree(menus,AdminCommonConstant.ROOT);
    }
}
