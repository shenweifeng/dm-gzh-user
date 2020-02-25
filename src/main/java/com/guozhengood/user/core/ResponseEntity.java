package com.guozhengood.user.core;

import java.io.Serializable;

/**
 * 请求返回信息
 *
 */
public class ResponseEntity<T extends Object> implements Serializable {

    private String  code;       // 错误编码
    private String  message;         // 错误信息描述
    private T       data;          // 返回数据实体
    private boolean success   = true;// 请求是否操作成功
    private String  type      = "0"; // 错误类型,0-字段串，1-JSON对象
    private String  errorType = "0"; // 异常类型：0－业务异常，1－系统异常

    public ResponseEntity() {
    }

    public static ResponseEntity success() {
        ResponseEntity re = new ResponseEntity();
        re.setCode("200");
        return  re;
    }
    public static ResponseEntity success(Object data) {
        ResponseEntity re = new ResponseEntity();
        re.setCode("200");
        re.setData(data);
        return  re;
    }
    public static ResponseEntity failure(String code, String message) {
        ResponseEntity re = new ResponseEntity();
        re.setCode(code);
        re.setMessage(message);
        re.setSuccess(false);
        return  re;
    }
    public static ResponseEntity failure() {
        ResponseEntity re = new ResponseEntity();
        re.setCode("600");
        re.setMessage("输入参数不合法！");
        re.setSuccess(false);
        return  re;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}
