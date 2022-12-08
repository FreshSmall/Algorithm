package com.compiler;

import java.util.List;

/**
 * @author: yinchao
 * @ClassName: ASTNode
 * @Description:
 * @team wuhan operational dev.
 * @date: 2022/12/8 16:44
 */
public interface ASTNode {
    //父节点
    public ASTNode getParent();

    //子节点
    public List<ASTNode> getChildren();

    //AST类型
    public ASTNodeType getType();

    //文本值
    public String getText();
}
