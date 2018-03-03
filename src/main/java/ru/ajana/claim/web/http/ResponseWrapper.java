package ru.ajana.claim.web.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Обёртка HTTP-ответа
 *
 * @author Andrey Kharintsev
 * @date 03.09.2017.
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

  private static final Logger LOG = LoggerFactory.getLogger(ResponseWrapper.class);
  private static final String EMPTY = "";
  private final ByteArrayOutputStream bos = new ByteArrayOutputStream();


  public ResponseWrapper(HttpServletResponse response) {
    super(response);
  }

  @Override
  public ServletResponse getResponse() {
    return this;
  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return new ServletOutputStream() {
      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setWriteListener(WriteListener writeListener) {

      }

      private TeeOutputStream tee = new TeeOutputStream(ResponseWrapper.super.getOutputStream(),
          bos);

      @Override
      public void write(int b) throws IOException {
        tee.write(b);
      }
    };
  }

  public byte[] toByteArray() {
    return bos.toByteArray();
  }

  public String getPayload() {
    try {
      return new String(toByteArray(), this.getCharacterEncoding());
    } catch (UnsupportedEncodingException e) {
      LOG.warn("Failed to parse response payload", e);
    }
    return EMPTY;
  }

  public Map<String, String> getHeaders() {
    Map<String, String> headers = new HashMap<>(0);
    for (String headerName : getHeaderNames()) {
      headers.put(headerName, getHeader(headerName));
    }
    return headers;
  }
}
