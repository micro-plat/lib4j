package com.lib4j.pager;

import java.util.Map;

import com.lib4j.errs.CodeException;

public class JwtUser extends Result<Map<String, Object>> {

    private String token;

    public JwtUser(Map<String, Object> userInfo) throws Exception {
        Result<String> tk = Jwt.create(userInfo);
        if (!tk.IsSuccess()) {
            throw new CodeException(tk.getCode(), tk.getData());
        }
        token = tk.getData();
    }

    
    /** 获取token
     * @return String
     */
    public String getToken(){
        return token;
    }
}
