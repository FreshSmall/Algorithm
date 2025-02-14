package com.genericity;

import lombok.Data;

/**
 * @author: yinchao
 * @ClassName: Box
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/14 14:37
 */
@Data
public class Box<T>{

    private T content;
}
