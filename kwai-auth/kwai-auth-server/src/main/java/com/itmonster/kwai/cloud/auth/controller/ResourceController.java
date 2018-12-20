package com.itmonster.kwai.cloud.auth.controller;

import com.itmonster.kwai.cloud.auth.biz.ResourceBiz;
import com.itmonster.kwai.cloud.auth.biz.UserBiz;
import com.itmonster.kwai.cloud.auth.constant.AdminCommonConstant;
import com.itmonster.kwai.cloud.auth.entity.Resource;
import com.itmonster.kwai.cloud.auth.vo.AuthorityResourceTree;
import com.itmonster.kwai.cloud.auth.vo.ResourceTree;
import com.itmonster.kwai.cloud.common.rest.BaseController;
import com.itmonster.kwai.cloud.common.util.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author ITMonster Kwai
 * @create 2017-06-12 8:49
 */
@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController<ResourceBiz, Resource> {
    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> list(String title) {
        Example example = new Example(Resource.class);
        if (StringUtils.isNotBlank(title)) {
            example.createCriteria().andLike("title", "%" + title + "%");
        }
        return baseBiz.selectByExample(example);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<ResourceTree> getTree(String title) {
        Example example = new Example(Resource.class);
        if (StringUtils.isNotBlank(title)) {
            example.createCriteria().andLike("title", "%" + title + "%");
        }
        return getMenuTree(baseBiz.selectByExample(example), AdminCommonConstant.ROOT);
    }



    @RequestMapping(value = "/system", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> getSystem() {
        Resource menu = new Resource();
        menu.setParentId(AdminCommonConstant.ROOT);
        return baseBiz.selectList(menu);
    }

    @RequestMapping(value = "/menuTree", method = RequestMethod.GET)
    @ResponseBody
    public List<ResourceTree> listMenu(String parentId) {
        try {
            if (parentId == null) {
                parentId = this.getSystem().get(0).getId();
            }
        } catch (Exception e) {
            return new ArrayList<ResourceTree>();
        }
        List<ResourceTree> trees = new ArrayList<ResourceTree>();
        ResourceTree node = null;
        Example example = new Example(Resource.class);
        Resource parent = baseBiz.selectById(parentId);
        example.createCriteria().andLike("path", parent.getPath() + "%").andNotEqualTo("id",parent.getId());
        return getMenuTree(baseBiz.selectByExample(example), parent.getId());
    }

    @RequestMapping(value = "/authorityTree", method = RequestMethod.GET)
    @ResponseBody
    public List<AuthorityResourceTree> listAuthorityMenu() {
        List<AuthorityResourceTree> trees = new ArrayList<AuthorityResourceTree>();
        AuthorityResourceTree node = null;
        for (Resource menu : baseBiz.selectListAll()) {
            node = new AuthorityResourceTree();
            node.setText(menu.getTitle());
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees, AdminCommonConstant.ROOT);
    }

    @RequestMapping(value = "/user/authorityTree", method = RequestMethod.GET)
    @ResponseBody
    public List<ResourceTree> listUserAuthorityMenu(String parentId){
        String userId = userBiz.getUserByUsername(getCurrentUserName()).getId();
        try {
            if (parentId == null) {
                parentId = this.getSystem().get(0).getId();
            }
        } catch (Exception e) {
            return new ArrayList<ResourceTree>();
        }
        return getMenuTree(baseBiz.getUserAuthorityByUserId(AdminCommonConstant.RESOURCE_TYPE_MENU,userId),parentId);
    }

    @RequestMapping(value = "/user/system", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> listUserAuthoritySystem() {
        String userId = userBiz.getUserByUsername(getCurrentUserName()).getId();
        return baseBiz.getUserAuthoritySystemByUserId(userId);
    }

    private List<ResourceTree> getMenuTree(List<Resource> menus,String root) {
        List<ResourceTree> trees = new ArrayList<ResourceTree>();
        ResourceTree node = null;
        for (Resource menu : menus) {
            node = new ResourceTree();
            BeanUtils.copyProperties(menu, node);
            node.setLabel(menu.getTitle());
            trees.add(node);
        }
        return TreeUtil.bulid(trees,root) ;
    }


}
