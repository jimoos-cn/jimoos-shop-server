package cn.jimoos.handler;

import cn.jimoos.common.error.ErrorCodeDefine;
import cn.jimoos.common.exception.*;
import cn.jimoos.common.exception.BussException;
import cn.jimoos.utils.http.ErrorRes;
import cn.jimoos.utils.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.*;


/**
 * 统一处理后台异常
 *
 * @author wangyiwen
 */
@Slf4j
@ControllerAdvice(basePackages = "cn.jimoos")
public class GlobalExceptionHandler {
    private static final MultiValueMap<String, String> MULTI_VALUE_MAP = new HttpHeaders();

    static {
        MULTI_VALUE_MAP.add(CONTENT_TYPE, "application/json; charset=utf-8");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(value = BussException.class)
    @ResponseBody
    public ResponseEntity<String> bussExceptionHandler(BussException ex, HttpServletRequest request) {
        HttpStatus status;
        ErrorRes errorRes = new ErrorRes();
        status = ex.getHttpStatus();
        errorRes.setMessage(ex.getMessage());
        errorRes.setCode(ex.getCode());

        ResponseEntity<String> res = new ResponseEntity<>(JsonMapper.defaultMapper().toJson(errorRes), MULTI_VALUE_MAP, status);
        log.debug("BussException,url:{}, message:{},throwable:{}", request.getRequestURL().toString(), res.getBody(), ex);
        return res;
    }


    /**
     * 参数缺失异常
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<String> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex, HttpServletRequest request) {
        ErrorRes errorRes = new ErrorRes();
        errorRes.setCode(ErrorCodeDefine.MISSING_REQUEST_PARAM_ERROR.getCode());
        errorRes.setMessage(ErrorCodeDefine.MISSING_REQUEST_PARAM_ERROR.getDesc() + ":" + ex.getMessage());
        ResponseEntity<String> res = new ResponseEntity<>(JsonMapper.defaultMapper().toJson(errorRes), MULTI_VALUE_MAP, BAD_REQUEST);
        log.info("MissingServletRequestParameterException,url:{}, message:{},throwable:", request.getRequestURL().toString(), res.getBody(), ex);
        return res;
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity<String> bindExceptionHandler(BindException ex, HttpServletRequest request) {
        ErrorRes errorRes = new ErrorRes();
        BindingResult bindingResult = ex.getBindingResult();
        List<String> msgList = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        errorRes.setMessage(String.join(",", msgList));
        errorRes.setCode(ErrorCodeDefine.REQUEST_PARAMS_NOT_VALID.getCode());
        ResponseEntity<String> res = new ResponseEntity<>(JsonMapper.defaultMapper().toJson(errorRes), MULTI_VALUE_MAP, BAD_REQUEST);
        log.info("BindException,url:{}, message:{},throwable:", request.getRequestURL().toString(), res.getBody(), ex);
        return res;
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<String> constraintViolationExceptionHandler(ConstraintViolationException ex, HttpServletRequest request, HttpServletResponse response) {
        StringBuilder detailMessage = new StringBuilder("\n\n详细错误如下：");
        ex.getConstraintViolations().forEach(constraintViolation -> detailMessage.append("\n").append(constraintViolation.getMessage()));
        ErrorRes errorRes = new ErrorRes();
        errorRes.setCode(ErrorCodeDefine.REQUEST_PARAMS_NOT_VALID.getCode());
        errorRes.setMessage(detailMessage.toString());
        ResponseEntity<String> res = new ResponseEntity<>(JsonMapper.defaultMapper().toJson(errorRes), MULTI_VALUE_MAP, BAD_REQUEST);
        log.info("constraintViolationException,url:{}, message:{},throwable:", request.getRequestURL().toString(), res.getBody(), ex);
        return res;
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errorMap = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, r -> {
            String defaultMessage = r.getDefaultMessage();
            if (StringUtils.isBlank(defaultMessage)) {
                return "";
            } else {
                return defaultMessage;
            }
        }));
        ErrorRes errorRes = new ErrorRes();
        errorRes.setCode(ErrorCodeDefine.REQUEST_PARAMS_NOT_VALID.getCode());
        errorRes.setMessage(errorMap.toString());
        ResponseEntity<String> res = new ResponseEntity<>(JsonMapper.defaultMapper().toJson(errorRes), MULTI_VALUE_MAP, BAD_REQUEST);

        log.info("MethodArgumentNotValidException,url:{}, message:{},throwable:", request.getRequestURL().toString(), res.getBody(), ex);
        return res;
    }


    /**
     * 表单验证异常
     */
    @ExceptionHandler(value = FormValidateException.class)
    @ResponseBody
    public ResponseEntity<String> formValidateExceptionHandler(FormValidateException ex, HttpServletRequest request) {
        HttpStatus status;
        ErrorRes errorRes = new ErrorRes();
        status = BAD_REQUEST;
        Set<ConstraintViolation<?>> violations = ex.getCurrentViolations();
        errorRes.setMessage(parseViolations(violations).toString());
        errorRes.setCode(ErrorCodeDefine.REQUEST_PARAMS_NOT_VALID.getCode());

        ResponseEntity<String> res = new ResponseEntity<>(JsonMapper.defaultMapper().toJson(errorRes), MULTI_VALUE_MAP, status);
        log.info("Business error request,url:{}, message:{},throwable:", request.getRequestURL().toString(), res.getBody(), ex);
        return res;
    }

    /**
     * 未经授权异常，应该返回 FORBIDDEN 403 权限不足
     */
    @ExceptionHandler(value = {AccessForbiddenException.class})
    @ResponseBody
    public ResponseEntity<String> accessForbiddenExceptionHandler(Exception ex, HttpServletRequest request) {
        HttpStatus status = FORBIDDEN;
        ErrorRes errorRes = new ErrorRes();
        errorRes.setCode(ErrorCodeDefine.UNAUTHORIZED.getCode());
        errorRes.setMessage(ErrorCodeDefine.UNAUTHORIZED.getDesc());
        ResponseEntity<String> res = new ResponseEntity<>(JsonMapper.defaultMapper().toJson(errorRes), MULTI_VALUE_MAP, status);
        log.info("Not authorized,url:{}, message:{},throwable:", request.getRequestURL().toString(), res.getBody(), ex);
        return res;
    }

    /**
     * 未登陆 应该返回 401
     */
    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseBody
    public ResponseEntity<String> unauthorizedExceptionHandler(Exception ex, HttpServletRequest request) {
        HttpStatus status = UNAUTHORIZED;
        ErrorRes errorRes = new ErrorRes();
        errorRes.setCode(ErrorCodeDefine.UNAUTHORIZED.getCode());
        errorRes.setMessage(ErrorCodeDefine.UNAUTHORIZED.getDesc());
        ResponseEntity<String> res = new ResponseEntity<>(JsonMapper.defaultMapper().toJson(errorRes), MULTI_VALUE_MAP, status);
        log.info("Not authorized,url:{}, message:{},throwable:", request.getRequestURL().toString(), res.getBody(), ex);
        return res;
    }


    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String> runtimeExceptionHandler(RuntimeException ex, HttpServletRequest request) {
        ErrorRes errorRes = new ErrorRes();
        errorRes.setMessage(ex.getMessage());
        errorRes.setCode(ErrorCodeDefine.DEFAULT_ERROR.getCode());
        ResponseEntity<String> res = new ResponseEntity<>(JsonMapper.defaultMapper().toJson(errorRes), MULTI_VALUE_MAP, INTERNAL_SERVER_ERROR);
        log.info("A internal server error,url:{}, message:{},throwable {} ", request.getRequestURL().toString(), res.getBody(), ex);
        return res;
    }


    /**
     * 解析表单约束， 提取错误属性名称作为key,错误消息作为value，返回map
     *
     * @param violations violations
     * @return Map<String, String>
     */
    private Map<String, String> parseViolations(Set<ConstraintViolation<?>> violations) {
        Map<String, String> pvMap = new HashMap<>();
        for (ConstraintViolation<?> cv : violations) {
            Path.Node last = getLastNode(cv);
            if (last != null) {
                pvMap.put(last.getName(), cv.getMessage());
            }
        }

        return pvMap;
    }


    /**
     * 获取最后一个属性名称，当存在多个关联对象时
     *
     * @param cv ConstraintViolation
     * @return Path.Node
     */
    private Path.Node getLastNode(ConstraintViolation<?> cv) {
        Iterator<Path.Node> ite = cv.getPropertyPath().iterator();
        Path.Node last = null;
        while (ite.hasNext()) {
            last = ite.next();
        }
        return last;
    }

}
