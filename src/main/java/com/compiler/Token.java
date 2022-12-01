package com.compiler;


/**
 * 一个简单的Token。
 * 只有类型和文本值两个属性。
 */
public interface Token {

    public TokenType getType();

    public String getText();
}
