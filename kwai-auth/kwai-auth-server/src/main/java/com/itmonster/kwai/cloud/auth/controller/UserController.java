package com.itmonster.kwai.cloud.auth.controller;

import com.itmonster.kwai.cloud.auth.biz.ResourceBiz;
import com.itmonster.kwai.cloud.auth.biz.UserBiz;
import com.itmonster.kwai.cloud.auth.constant.AdminCommonConstant;
import com.itmonster.kwai.cloud.auth.entity.Resource;
import com.itmonster.kwai.cloud.auth.entity.User;
import com.itmonster.kwai.cloud.auth.service.impl.PermissionService;
import com.itmonster.kwai.cloud.auth.vo.FrontUser;
import com.itmonster.kwai.cloud.auth.vo.ResourceTree;
import com.itmonster.kwai.cloud.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author ITMonster Kwai
 * @create 2017-06-08 11:51
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz, User> {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ResourceBiz menuBiz;

    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(String token) throws Exception {
        FrontUser userInfo = permissionService.getUserInfo(token);
        if(userInfo==null) {
            return ResponseEntity.status(401).body(false);
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    @ResponseBody
    public List<ResourceTree> getMenusByUsername(String token) throws Exception {
        return permissionService.getMenusByUsername(token);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> getAllMenus() throws Exception {
        return menuBiz.getAllPermissionsByType(AdminCommonConstant.RESOURCE_TYPE_MENU);
    }
}
