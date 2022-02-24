package app.foodapp.controller.login;

import java.util.HashMap;

public class IDandPasswords {
    private HashMap<String, String> loginInfo;

    public IDandPasswords(HashMap<String, String> loginInfo) {
        this.loginInfo = loginInfo;
    }

    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }
}
