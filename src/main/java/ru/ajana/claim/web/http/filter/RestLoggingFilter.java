package ru.ajana.claim.web.http.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ajana.claim.web.http.RequestWrapper;
import ru.ajana.claim.web.http.ResponseWrapper;

/**
 * Фильтр для мониторинга запросов и ответов Rest-сервисов.
 *
 * @author Andrey Kharintsev
 * @date 02.07.2017.
 */
public class RestLoggingFilter implements Filter {

  private final static Logger LOG = LoggerFactory.getLogger(RestLoggingFilter.class);
  public final static String REQUEST_PREFIX = "REQUEST";
  public final static String RESPONSE_PREFIX = "RESPONSE";
  public final static String POST_METHOD = "POST";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response,
      final FilterChain chain)
      throws IOException, ServletException {
    RequestWrapper requestWrapper = null;
    ResponseWrapper responseWrapper = null;
    try {
      requestWrapper = new RequestWrapper((HttpServletRequest) request);
      responseWrapper = new ResponseWrapper((HttpServletResponse) response);
      chain.doFilter(requestWrapper, responseWrapper);
    } finally {
      LOG.info(getRequestData(requestWrapper));
      LOG.info(getResponseData(responseWrapper));
    }
  }

  @Override
  public void destroy() {
  }

  protected String getRequestData(final RequestWrapper request) {

    final StringBuilder sb = new StringBuilder();

    sb.append("\n").append(REQUEST_PREFIX).append(" --------------------------");
    sb.append("\nClient: ").append(request.getRemoteAddr());
    sb.append("\nPath: ").append(request.getRequestURI());
    sb.append("\nContentType: ")
        .append(request.getContentType() == null ? "" : request.getContentType());
    sb.append("\nMethod: ").append(request.getMethod());

    final Map<String, String> params = getParameters(request);
    if (params.size() > 0) {
      sb.append("\nQueryParams: ").append(params);
    }
    final Map<String, String> headers = getReaquestHeaders(request);
    if (headers.size() > 0) {
      sb.append("\nHeaders: ").append(headers);
    }
    if (POST_METHOD.equals(request.getMethod())) {
      sb.append("\nBody: ").append(request.getPayload());
    }
    sb.append("\n---------------------------------");

    return sb.toString();
  }

  protected String getResponseData(final ResponseWrapper response) {
    final StringBuilder sb = new StringBuilder();

    sb.append("\n").append(RESPONSE_PREFIX).append(" --------------------------");
    sb.append("\nStatus: ").append(response.getStatus());
    sb.append("\nContextType: ")
        .append(response.getContentType() == null ? "" : response.getContentType());

    final Map<String, String> headers = getResponseHeaders(response);
    if (headers.size() > 0) {
      sb.append("\nHeaders: ").append(headers);
    }
    sb.append("\nBody: ").append(response.getPayload());

    sb.append("\n---------------------------------");
    return sb.toString();
  }

  public Map<String, String> getParameters(HttpServletRequest httpRequest) {
    return httpRequest.getParameterMap().entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> {
          String[] values = e.getValue();
          return values.length > 0 ? values[0] : "";
        }));
  }

  public Map<String, String> getReaquestHeaders(final HttpServletRequest httpRequest) {
    Map<String, String> headers = new HashMap<>(0);
    Enumeration<String> headerNames = httpRequest.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      if (headerName != null) {
        headers.put(headerName, httpRequest.getHeader(headerName));
      }
    }
    return headers;
  }

  public Map<String, String> getResponseHeaders(final HttpServletResponse response) {
    Map<String, String> headers = new HashMap<>(0);
    Collection<String> headerNames = response.getHeaderNames();
    headerNames.stream().forEach(headerName -> {
      headers.put(headerName, response.getHeader(headerName));
    });
    return headers;
  }
}
