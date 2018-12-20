package com.itmonster.kwai.cloud.admin.rest;

import com.itmonster.kwai.cloud.admin.biz.MenuBiz;
import com.itmonster.kwai.cloud.admin.biz.UserBiz;
import com.itmonster.kwai.cloud.admin.entity.Menu;
import com.itmonster.kwai.cloud.admin.entity.User;
import com.itmonster.kwai.cloud.admin.rpc.service.PermissionService;
import com.itmonster.kwai.cloud.admin.vo.FrontUser;
import com.itmonster.kwai.cloud.admin.vo.MenuTree;
import com.itmonster.kwai.cloud.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    private MenuBiz menuBiz;

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
    public @ResponseBody
    List<MenuTree> getMenusByUsername(String token) throws Exception {
        return permissionService.getMenusByUsername(token);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Menu> getAllMenus() throws Exception {
        return menuBiz.selectListAll();
    }
}
