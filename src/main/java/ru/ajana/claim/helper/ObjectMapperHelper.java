package ru.ajana.claim.helper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Класс-утилита для маппинга объектов.
 *
 * @author Andrey Kharintsev
 * @date 23.07.2017.
 */
public class ObjectMapperHelper {

  private static Mapper mapper;

  static {

    mapper = new DozerBeanMapper();
  }

  public static void map(Object srcObj, Object descObject) {
    mapper.map(srcObj, descObject);
  }

  public static <T> T map(Object srcObj, Class<T> descClazz) {
    if (srcObj == null) {
      return null;
    }

    return mapper.map(srcObj, descClazz);
  }
}

