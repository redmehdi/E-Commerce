package com.red.one.controller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaseTest {

  @Before
  public void onBefore() throws Exception {
    MockitoAnnotations.initMocks(this);
    setUp();
  }

  protected void setUp() throws Exception {
    // Method implemented when required
  }

  protected String getJson(final Object object) throws Exception {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    return objectMapper.writeValueAsString(object);
  }

  protected Object getField(final Object parent, final String fieldName) throws Exception {
    return getField(parent.getClass(), parent, fieldName);
  }

  protected Object getField(final Class klass, final Object parent, final String fieldName) throws Exception {
    Field field = klass.getDeclaredField(fieldName);
    field.setAccessible(true);
    return field.get(parent);
  }

  protected <S, T> void setField(final S element, final String key, final T value) throws Exception {

    final Field field = element.getClass().getDeclaredField(key);
    field.setAccessible(true);
    field.set(element, value);
  }

  protected <T> T parseJson(final String json, Class<T> klass) throws Exception {
    final ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, klass);
  }

  protected Object invokeMethod(final Object parent, final String methodName, final Class<?>[] paramTypes, final Object[] params)
      throws Exception {
    return invokeMethod(parent.getClass(), parent, methodName, paramTypes, params);
  }

  protected Object invokeMethod(final Class klass, final Object parent, final String methodName, final Class<?>[] paramTypes,
      final Object[] params) throws Exception {
    final Method method = klass.getDeclaredMethod(methodName, paramTypes);
    method.setAccessible(true);
    return method.invoke(parent, params);
  }

}
