package ru.ajana.claim.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Класс утилита для работы с JSON.
 *
 * @author Andrey Kharintsev
 * @date 28.06.2017.
 */
public class JsonHelper<T> {

  private final static Logger LOG = LoggerFactory.getLogger(JsonHelper.class);

  public List<T> parse(final String path) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final Resource resource = new ClassPathResource(path);
      TypeReference<List<T>> typeRef = new TypeReference<List<T>>() {
      };

      return mapper.readValue(resource.getFile(), typeRef);
    } catch (IOException e) {
      LOG.error("Error json parse", e);
    }
    return null;
  }
}
