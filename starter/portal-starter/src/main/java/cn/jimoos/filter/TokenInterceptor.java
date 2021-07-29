package cn.jimoos.filter;

import cn.jimoos.common.exception.UnauthorizedException;
import cn.jimoos.entity.AdminEntity;
import cn.jimoos.error.AdminError;
import cn.jimoos.repository.AdminRepository;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SiletFlower
 * @date 2021/7/23 10:10:04
 * @description 管理员token验证器
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Resource
    AdminRepository adminRepository;

    /**
     * 方法前校验token
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        AdminEntity adminEntity = new AdminEntity(adminRepository);
        boolean b = adminEntity.validToken(token);
        if(!b){
            throw new UnauthorizedException(AdminError.ADMIN_NOT_LOGIN.getDesc());
        }
        return true;
    }
}
