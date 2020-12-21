package com.ocean.supplier.tools;

public class TimeUtils {
  public static String getHours(long second) {//计算秒有多少小时
    long h = 00;
    if (second > 3600) {
      h = second / 3600;
    }
    return h+"";
  }
 
  public static String getMins(long second) {//计算秒有多少分
    long d = 00;
    long temp = second % 3600;
    if (second > 3600) {
      if (temp != 0) {
        if (temp > 60) {
          d = temp / 60;
        }
      }
    } else {
      d = second / 60;
    }
    return d + "";
  }
  public static String getSeconds(long second) {//计算秒有多少秒
    long s = 0;
    long temp = second % 3600;
    if (second > 3600) {
      if (temp != 0) {
        if (temp > 60) {
          if (temp % 60 != 0) {
            s = temp % 60;
          }
        } else {
          s = temp;
        }
      }
    } else {
      if (second % 60 != 0) {
        s = second % 60;
      }
    }
    return s + "";
  }
 
}