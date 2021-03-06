package org.cloud.mae.model.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Mae
 * @Date: 2021/2/22 12:38 上午
 *
 * Phone number login check.
 *
 */
public class PhoneUtil {
    private static String REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    private static Pattern P = Pattern.compile(REGEX);

    /**
     * phone number validation method.
     */
    public static boolean checkPhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return Boolean.FALSE;
        }
        Matcher m = P.matcher(phone);
        return m.matches();
    }
}
