package com.example.beta1.Data;

import java.util.HashMap;
import java.util.Map;

public class UserBean {
    public Map<String,String> userinfo=new HashMap<String,String>();
    {
        userinfo.put("15682518657","LI200011");
    }

    public Map<String, String> getUserinfo() {
        return userinfo;
    }

}
