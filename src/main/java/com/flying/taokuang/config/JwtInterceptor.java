package com.flying.taokuang.config;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author NNShadow
 * @date 2019/8/27 8:58
 */
public class JwtInterceptor implements HandlerInterceptor {
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//        String token = request.getHeader("token");
//        //如果不是映射到方法直接通过
//        if(!(object instanceof HandlerMethod)){
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) object;
//        Method method = handlerMethod.getMethod();
//        //检查是否有 @PassToken 注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
//        //检查有没有需要用户权限的注解
////        if (method.isAnnotationPresent(UserLoginToken.class)) {
////            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
////            if (userLoginToken.required()) {
//                // 执行认证
//                if (token == null) {
//                    throw new RuntimeException("无token，请重新登录");
//                }
//                try {
//                    if (!JwtUtil.isExpiration(token, "salt")){
//                        int userId = (int) JwtUtil.getClamis(token, "salt").get("userId");
//                        User user = userService.selectById(userId);
//                        if (user != null){
//                            return true;
//                        }
//                        return false;
//                    }
//                } catch (Exception e){
//                    throw new Exception("token过期");
//                }
////            }
////        }
//        return false;
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
