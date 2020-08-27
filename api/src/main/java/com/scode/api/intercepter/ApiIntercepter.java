package com.scode.api.intercepter;

import com.scode.api.constant.AuthExclusionUrls;
import com.scode.api.dto.AuthDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ApiIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();

        String requestUri = request.getRequestURI();
        String methodName = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(methodName)) {
            return true;
        }

        if (StringUtils.startsWithIgnoreCase(requestUri, "/api") && !StringUtils.startsWithIgnoreCase(requestUri, "/api/api")) {

            String authHeader = request.getHeader("Authorization");
            System.out.println(authHeader);

            if (AuthExclusionUrls.list().contains(requestUri)) {
                log.info("NA");
            } else {
//                String authToken = request.getHeader(ApiConstant.AUTH_HEADER_NAME);
//                AuthValidationDTO authValidation = authTokenService.validate(authToken, requestUri, request.getMethod());
//                if (!AuthTokenStatus.VALID.equals(authValidation.getAuthStatus())) {
//                    throw new WcsApiException(new GenericResponse(authValidation.getHttpStatus().value(),
//                            authValidation.getMessage()));
//                } else {
//                    request.setAttribute("authDetails", authValidation.getTokenConfig());
//                }
                request.setAttribute("authDetails", AuthDTO.builder().userId("1001").userName("John Doe").role("Developer").build());
            }
            request.setAttribute("startTime", startTime);
        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        response.setHeader("x-powered-by", "scode");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        String requestUri = request.getRequestURI();
        if (StringUtils.startsWithIgnoreCase(requestUri, "/api")) {
            long startTime = (Long) request.getAttribute("startTime");
            long endTime = System.currentTimeMillis();
            log.info("Request URL: " + request.getMethod() + ": " + requestUri
                    + " , Time Taken: " + (endTime - startTime) + " ms");
        }
    }


}
