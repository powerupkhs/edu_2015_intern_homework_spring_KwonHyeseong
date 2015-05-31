/*
 * @(#)Auth.java 2015. 5. 7.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 커스톰 어노테이션 만든거
 * 
 * @author hyeseong.kwon@nhn.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// 타겟이 메소드
public @interface Auth {
	boolean check() default false;
}
