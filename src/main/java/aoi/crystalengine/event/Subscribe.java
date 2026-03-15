package com.aoi.crystalengine.event;

import java.lang.annotation.*;

/*
#【!】Code:
Annotation cho event handler
*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
} 
