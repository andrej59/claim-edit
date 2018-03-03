package ru.ajana.claim.web.http;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 * Обёртка HTTP-запроса.
 *
 * @author Andrey Kharintsev
 * @date 03.09.2017.
 */
public class RequestWrapper extends ContentCachingRequestWrapper {

  private static final Logger LOG = LoggerFactory.getLogger(RequestWrapper.class);
  private static final String EMPTY = "";

  public RequestWrapper(HttpServletRequest request) {
    super(request);
  }

  public String getPayload() {
    try {
      return new String(this.getContentAsByteArray(), this.getCharacterEncoding());
    } catch (UnsupportedEncodingException e) {
      LOG.error("Error set encoding", e);
    }
    return EMPTY;
  }
}
