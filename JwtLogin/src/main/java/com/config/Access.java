package com.config;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {

    String[] value() default {};

    String[] authorities() default {};

    String[] roles() default {};

}