package com.study.kmj;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)  // 适用于类、接口、枚举
@Retention(RetentionPolicy.SOURCE)  // 在源代码阶段处理
public @interface LogStudy {

}
