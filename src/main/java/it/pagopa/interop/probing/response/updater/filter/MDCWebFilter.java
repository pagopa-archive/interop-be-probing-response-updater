package it.pagopa.interop.probing.response.updater.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import it.pagopa.interop.probing.response.updater.util.logging.LoggingPlaceholders;

@Component
public class MDCWebFilter extends OncePerRequestFilter {

  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    try {
      MDC.put(LoggingPlaceholders.TRACE_ID_PLACEHOLDER,
          "- [CID= " + UUID.randomUUID().toString().toLowerCase() + "]");

      filterChain.doFilter(request, response);
    } finally {
      MDC.remove(LoggingPlaceholders.TRACE_ID_PLACEHOLDER);
    }
  }
}