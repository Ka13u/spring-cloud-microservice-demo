package com.org.util;

/**
 * 获取一个map中的entry
 */
public interface EntryGetter<ENTITY,K,V> extends KeyGetter<K, ENTITY> {

  V getValue(ENTITY entity);
}
