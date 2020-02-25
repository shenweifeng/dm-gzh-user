package com.guozhengood.user.domain.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @date 2013-9-12 下午6:37:16
 * 
 * @author swf
 * 
 * @Description 存储树形结构 for easyui
 */
public class TreeNode implements Serializable {

    private static final long   serialVersionUID = 4829999569757014896L;

    private String              id;
    private String              text;
    private String              iconCls;
    private Boolean             checked          = false;
    private String              state;                                  // open,
                                                                        // closed
    private Map<String, String> attributes;
    private List<TreeNode>      children;                               // children

    public TreeNode() {
    }

    public TreeNode(String id, String text) {
        super();
        this.id = id;
        this.text = text;
    }

    public String toJson() {
        StringBuffer sb = new StringBuffer(100);
        sb.append("[");
        if (children == null || children.isEmpty()) {
            sb.append("{id=\"").append(id).append("\",text=\"").append(text).append("\"}");
        } else {
            sb.append("{id=\"").append(id).append("\",text=\"").append(text).append("\"}");
        }
        sb.append("]");
        return sb.toString();
    }

    // 添加子节点的方法
    public void addChild(TreeNode node) {
        if (this.children == null) {
            children = new ArrayList<TreeNode>();
            children.add(node);
        } else {
            children.add(node);
        }
    }

    public void addAttributes(String key, String value) {
        if (this.attributes == null) {
            attributes = new HashMap<String, String>();
        }
        attributes.put(key, value);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

}
