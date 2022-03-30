package com.example.beta1.Data;

import java.util.HashMap;
import java.util.Map;

public class DataLab {
    UserBean userBean = new UserBean();
    private static DataLab INSTANCE = null;
    public static DataLab getInstance(){
        if (INSTANCE == null){
            INSTANCE =new DataLab();
        }
        return INSTANCE;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
