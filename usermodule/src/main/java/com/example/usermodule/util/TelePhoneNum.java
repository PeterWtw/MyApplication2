package com.example.usermodule.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/10.
 */

public class TelePhoneNum {

    public static boolean isMobile(String str) {
        Pattern pattern = Pattern.compile("[1][358]\\\\d{9}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
