package com.org.util;



public interface KeyGetter<KEY, ENTITY> {

  KEY getKey(ENTITY entity);
}
