package com.wwx.tob.util;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * 字符串工具类，继承lang3字符串工具类
 *
 * @author Javen
 * 2016年4月3日
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    private final static String YYYY_MM_DD = "yyyy/MM/dd";

    private final static Pattern PROPERTY_PATTERN = Pattern.compile("\\$\\{([\\w\\.]+)\\}");

    public static String encode(String str) {
        String encode = null;
        try {
            encode = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encode;
    }

    /**
     * 获取UUID，去掉`-`的
     *
     * @return uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 将字符串中特定模式的字符转换成map中对应的值
     * <p>
     * use: format("my name is ${name}, and i like ${like}!", {"name":"L.cm", "like": "Java"})
     *
     * @param s   需要转换的字符串
     * @param map 转换所需的键值对集合
     * @return 转换后的字符串
     */
    public static String format(String s, Map<String, String> map) {
        StringBuilder sb = new StringBuilder((int) (s.length() * 1.5));
        int cursor = 0;
        for (int start, end; (start = s.indexOf("${", cursor)) != -1 && (end = s.indexOf('}', start)) != -1; ) {
            sb.append(s.substring(cursor, start));
            String key = s.substring(start + 2, end);
            sb.append(map.get(trim(key)));
            cursor = end + 1;
        }
        sb.append(s.substring(cursor, s.length()));
        return sb.toString();
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }


    /**
     * 字符串格式化
     * <p>
     * use: format("my name is {0}, and i like {1}!", "L.cm", "java")
     * <p>
     * int long use {0,number,#}
     *
     * @param s
     * @param args
     * @return 转换后的字符串
     */
    public static String format(String s, Object... args) {
        return MessageFormat.format(s, args);
    }

    /**
     * 替换某个字符
     *
     * @param str
     * @param regex
     * @param args
     * @return
     */
    public static String replace(String str, String regex, String... args) {
        int length = args.length;
        for (int i = 0; i < length; i++) {
            str = str.replaceFirst(regex, args[i]);
        }
        return str;
    }

    /**
     * 清理字符串，清理出某些不可见字符
     *
     * @param txt
     * @return {String}
     */
    public static String cleanChars(String txt) {
        return txt.replaceAll("[ 　	`·•�\\f\\t\\v]", "");
    }

    // 随机字符串
    private static final String _INT = "0123456789";
    private static final String _STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String _ALL = _INT + _STR;

    private static final Random RANDOM = new Random();

    /**
     * 生成的随机数类型
     */
    public static enum RandomType {
        INT, STRING, ALL;
    }

    /**
     * 随机数生成
     *
     * @param count
     * @return
     */
    public static String random(int count, RandomType randomType) {
        if (count == 0) return "";
        if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            if (randomType.equals(RandomType.INT)) {
                buffer[i] = _INT.charAt(RANDOM.nextInt(_INT.length()));
            } else if (randomType.equals(RandomType.STRING)) {
                buffer[i] = _STR.charAt(RANDOM.nextInt(_STR.length()));
            } else {
                buffer[i] = _ALL.charAt(RANDOM.nextInt(_ALL.length()));
            }
        }
        return new String(buffer);
    }

    public static String lpad(int count, String str, char pad) {
        String ret = str;
        while (ret.length() < count) {
            ret = pad + ret;
        }
        return ret;
    }

    public static String rpad(int count, String str, char pad) {
        String ret = str;
        while (ret.length() < count) {
            ret = ret + pad;
        }
        return ret;
    }

    public static final boolean isBlank(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String objToString(Object obj) {
        if (null == obj) {
            return "";
        }
        return obj.toString();
    }

    public static boolean isEmptyList(List<?> lst) {
        return lst == null || lst.isEmpty();
    }

    public static boolean isNotEmptyList(List<?> lst) {
        return !isEmptyList(lst);
    }

    public static String longToDate(long msec) {
        DateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(msec);
        return (formatter.format(calendar.getTime()));
    }

    /**
     * 连接字符串
     *
     * @param arr       字符串
     * @param delimiter 区隔符号
     * @return 连接之后的字符串
     */
    public static String concat(String[] arr, String delimiter) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        return concat(delimiter, arr);
    }

    /**
     * 连接字符串
     *
     * @param arr       字符串
     * @param delimiter 区隔符号
     * @return 连接之后的字符串
     */
    public static String concat(String delimiter, String... arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(delimiter == null ? "" : delimiter);
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * 连接字符串
     *
     * @param list 字符串
     * @param delimiter 区隔符号
     * @return 连接之后的字符串
     */
    public static String concat(List<String> list, String delimiter) {
        if (list == null || list.size() == 0) {
            return "";
        }
        return concat(delimiter, list.toArray(new String[0]));
    }

    public static String nvl(String str) {
        return str == null ? "" : str;
    }

    public static boolean equal(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public static String replaceProperty(Environment environment, String oldString) {
        String newString = oldString;
        Matcher matcher = PROPERTY_PATTERN.matcher(oldString);
        while (matcher.find()) {
            String replaceString = matcher.group(0);
            String propertyName = matcher.group(1);
            newString = newString.replace(replaceString, environment.getProperty(propertyName, ""));
        }
        return newString;
    }

    public static String generateIdentiCode(final int length, final String dictionary) {
        return IntStream.range(0, length).mapToObj(index -> {
            int position = RANDOM.nextInt(dictionary.length());
            return String.valueOf(dictionary.charAt(position));
        }).reduce("", (result, element) -> result + element);
    }

    public static String firstCharToUpper(final String str) {
        if (isBlank(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String firstCharToLower(final String str) {
        if (isBlank(str)) return str;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String cutString(String param, int length) {
        if (param == null || length <= 0) {
            return param;
        }
        if (param.length() > length) {
            return param.substring(0, length);
        } else {
            return param;
        }
    }

    public static String toString(Object obj) {
        if (obj == null || obj instanceof String) {
            return (String) obj;
        } else {
            Gson gson = new Gson();
            return gson.toJson(obj);
        }
    }
}
