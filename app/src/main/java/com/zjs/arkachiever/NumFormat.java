package com.zjs.arkachiever;

import java.text.DecimalFormat;

public class NumFormat {
    public static String moneyFormat(double number) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        return formatter.format(number);
    }
}
