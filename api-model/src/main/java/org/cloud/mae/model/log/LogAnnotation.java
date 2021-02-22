package org.cloud.mae.model.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Mae
 * @Date: 2021/2/22 2:44 上午
 * <p>
 * log annotation
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    /**
     * log module
     *
     * @return
     */
    String module();

    /**
     * parameter recorder
     *
     * @return
     */
    boolean recordParam() default true;
}
