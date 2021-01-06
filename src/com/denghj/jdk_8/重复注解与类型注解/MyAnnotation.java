package com.denghj.jdk_8.重复注解与类型注解;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Repeatable(MyAnnotations.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE,TYPE_PARAMETER})
public @interface MyAnnotation {

    String value() default "";
}
