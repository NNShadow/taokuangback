package com.flying.taokuang.config;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author NNShadow
 * @date 2019/8/27 8:58
 */
public class JwtInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token");
//        //token验证
//        if(StringUtils.isEmpty(token)){
//            System.out.println("token验证失败");
//            response.setStatus(203);
//            response.sendRedirect("/");
//            return false;
//        }
//        System.out.println("token验证成功");
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
}
