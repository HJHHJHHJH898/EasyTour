package com.example.easytour.createluggage.CalendarUtil.util.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by 黄家慧 on 2018/12/16.
 */
@Retention(RUNTIME) @Target(FIELD)
 public @interface Inject {
    int[] value();


}