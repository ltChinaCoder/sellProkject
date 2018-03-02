package com.lt.sell.util;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

public class TimeToStringUtil {
    public static String convertToLong(Data data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        return simpleDateFormat.format(data);
    }
}
