package com.itmonster.kwai.cloud.auth.vo;

import com.itmonster.kwai.cloud.common.vo.TreeNode;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author ITMonster Kwai
 * @create 2017-06-19 13:03
 */
public class AuthorityResourceTree extends TreeNode implements Serializable{
    String text;
    List<AuthorityResourceTree> nodes = new ArrayList<AuthorityResourceTree>();
    String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public AuthorityResourceTree(String text, List<AuthorityResourceTree> nodes) {
        this.text = text;
        this.nodes = nodes;
    }

    public AuthorityResourceTree() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AuthorityResourceTree> getNodes() {
        return nodes;
    }

    public void setNodes(List<AuthorityResourceTree> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void setChildren(List<TreeNode> children) {
        super.setChildren(children);
        nodes = new ArrayList<AuthorityResourceTree>();
    }

    @Override
    public void add(TreeNode node) {
        super.add(node);
        AuthorityResourceTree n = new AuthorityResourceTree();
        BeanUtils.copyProperties(node,n);
        nodes.add(n);
    }
}
