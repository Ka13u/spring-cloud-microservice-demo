/**
 *
 */
package com.org.util;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Description: 常用工具类
 * @Autnor: kabu
 */
public class GeneralUtil {

    /**
     * @param obj
     * @return boolean
     * @Title: isNull
     * @Description: 只判断 !=null
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    public static boolean isNullOrEmpty(String s) {
        return null == s || s.isEmpty();
    }

    public static boolean isNotNullAndEmpty(String s) {
        return !isNullOrEmpty(s);
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * @param obj 输入对象 (Object,Long,String...等对象)
     * @return 不为空返回 true, 否则返回false
     * @Title: isObjNotNull
     * @Description: (判断Object 是否为空, 常用于字符串的判断)
     */
    public static boolean isObjNotNull(Object obj) {
        return !isObjNull(obj);
    }

    public static boolean isNotNullOrEmpty(String s) {
        return !isNullOrEmpty(s);
    }


    public static boolean isObjNull(Object obj) {
        return obj == null || "".equals(obj.toString().trim());
    }

    /**
     * @param list
     * @return 如果list不为空 返回 true, 为空返回 false
     * @Title: isListNotNull
     * @Description: (判断 list 是否为空)
     */
    public static boolean isListNotNull(List list) {
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isListNull(List list) {
        return !isListNotNull(list);
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @param objects 传入的可变参数
     * @return boolean
     * @Title: isMultiNotNull
     * @Description: (判断可变参数是否为空，只要一个为null 或 空字符串，就返回 false, 否则返回true)
     */
    public static boolean isMultiNotNull(Object... objects) {

        if (objects == null) {
            return false;
        } else {
            for (Object object : objects) {
                if (isObjNull(object)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断非空并且不等于0， 常用与  判断 主键id  非空并且非0
     *
     * @param objects
     * @return
     */
    public static boolean isMultiNotNullAndNotZero(Object... objects) {

        if (objects == null) {
            return false;
        } else {
            for (Object object : objects) {
                if (isObjNull(object) || "0".equals(object.toString())) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isMultiHasNullOrZero(Object... objects) {
        return !isMultiNotNullAndNotZero(objects);
    }

    public static boolean isObjNullOrZero(Object obj) {
        return isObjNull(obj) || "0".equals(obj.toString());
    }

    public static boolean isObjNotNullAndZero(Object obj) {
        return !isObjNullOrZero(obj);
    }

    /**
     * 判断非 0
     * @param obj int ,long 等基本类型 整形数据
     * @return
     */
    public static boolean isObjEqualsZero(Object obj) {
        if (isNull(obj)) {
            return true;
        }
        return "0".equals(obj.toString());
    }

    public static boolean isObjNotZero(Object obj) {
        return !isObjEqualsZero(obj);
    }

    /**
     * @param objects
     * @return boolean
     * @Title: isMultiHasNull
     * @Description: 判断传进来的可变参数中 是否有null或者""
     */
    public static boolean isMultiHasNull(Object... objects) {
        return !isMultiNotNull(objects);
    }

    /**
     * @param doubleVal
     * @return String
     * @Title: getFormatDouble
     * @Description: (格式化double值 位两位小数点数的字符串 eg:0.00, 4.23)
     */
    public static String getFormatDouble(Double doubleVal) {
        DecimalFormat decimalFormat = new DecimalFormat("######0.00");
        return decimalFormat.format(doubleVal);
    }

    /**
     * @param doubleVal
     * @return double
     * @Title: getDecimalFraction
     * @Description: 对double值 四舍五入
     */
    public static double getDecimalFraction(Double doubleVal) {
        if (GeneralUtil.isNotNull(doubleVal)) {
            return new BigDecimal(doubleVal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return 0;
    }

    /**
     * @param str
     * @return String
     * @Title: transformRichText
     * @Description: 转换富文本为阿里云CDN地址
     */
    public static String transformRichText(String str) {
        if (str.trim().isEmpty()) {
            return str;
        }
        Pattern pattern = Pattern.compile("src=\".*/resources/images/trinyMCE");
        Matcher matcher = pattern.matcher(str);
        str = matcher.replaceAll("src=\"http://ossimg.xinli001.com/ceping/resources/images/trinyMCE");
        return str;
    }

    //首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * @param str
     * @return String
     * @Title: filterEmoji
     * @Description: 过滤表情
     */
    public static String filterEmoji(String str) {

        if (isObjNull(str)) {
            return "";
        }
        String pattern = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
        String reStr = "";
        Pattern emoji = Pattern.compile(pattern);
        Matcher emojiMatcher = emoji.matcher(str);
        str = emojiMatcher.replaceAll(reStr);
        return str;
    }

    public static String replaceBlankSpace(String str) {
        String pattern = "\\s{1}";
        String reStr = "+";
        Pattern emoji = Pattern.compile(pattern);
        Matcher blankSpaceMatcher = emoji.matcher(str);
        str = blankSpaceMatcher.replaceAll(reStr);
        return str;
    }

    /**
     * @param str
     * @return boolean
     * @Title: isHasEmoji
     * @Description: 有emoji表情
     */
    public static boolean isHasEmoji(String str) {
        String pattern = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
        String reStr = "";
        Pattern emoji = Pattern.compile(pattern);
        Matcher emojiMatcher = emoji.matcher(str);
        if (emojiMatcher.find()) {
            return true;
        }
        return false;
    }

    public static String wipeBlankSpace(String str) {
        String pattern = "\\s+";
        String reStr = "";
        Pattern emoji = Pattern.compile(pattern);
        Matcher blankSpaceMatcher = emoji.matcher(str);
        str = blankSpaceMatcher.replaceAll(reStr);
        return str;
    }

    // 判断数组 非空，非空返回 true, 否则 返回false
    public static boolean isArrayNotNull(Object[] array) {
        if (array != null && array.length > 0) {
            return true;
        }
        return false;
    }

    public static boolean isArrayNull(Object[] array) {
        return !isArrayNotNull(array);
    }

    /**
     * 四舍五入 取整数
     *
     * @param scoringRatio 得分百分数
     * @return
     */
    public static double getRound(double scoringRatio) {
        return Math.round(scoringRatio);
    }

    /**
     * 将 html 转义字符  转为 符号文字
     *
     * @param content
     * @return
     */
    public static String unescapeHtml(String content) {
        if (GeneralUtil.isObjNotNull(content)) {
            return StringEscapeUtils.unescapeHtml(content);
        }
        return "";
    }

    /**
     * 去掉其它的<>之间的东西
     *
     * @param content
     * @return
     */
    public static String removeHtmlTag(String content) {
        if (GeneralUtil.isObjNotNull(content)) {
            return content.replaceAll("\\<.*?>", "");
        }
        return "";
    }

    /**
     * @param list       需要根据 comparator 规则 去重 的 list ，无序 treeSet
     * @param comparator 自定义 类型 的比较器
     * @param <E>        类型
     * @return 去重的 list 数据
     */
    public static <E> List<E> getSetData(List<E> list, Comparator<E> comparator) {
        if (GeneralUtil.isListNull(list)) {
            return null;
        }
        Set<E> set = new TreeSet<E>(comparator);
        set.addAll(list);
        return new ArrayList<E>(set);
    }

    /**
     * 根据  key 和 参数值后缀 获得 格式化 后的  key
     * @param formerKey         eg: cp/account/id/%s
     * @param suffixParamValues eg: id
     * @return
     */
    public static String getformatKey(String formerKey, Object... suffixParamValues) {
//		String.format("cp/account/id/%s", id);
        return String.format(formerKey, suffixParamValues);
    }

    /**
     * 判断 集合 是否为空
     * @param map 集合
     * @param <k> map 的 key 类型
     * @param <v> map 的 value 类型
     * @return
     */
    public static <k, v> boolean isMapNotNull(Map<k, v> map) {
        if (isNotNull(map) && map.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 抽取实体列表的key作为一个新的列表,null的对象会被跳过
     *
     * @param entities
     * @return
     */
    public final static <KEY extends Serializable, ENTITY> List<KEY> extractKeys(Collection<? extends ENTITY> entities, KeyGetter<KEY, ENTITY> keyGetter) {
        if (isCollecitonNull(entities)) {
            return new ArrayList<KEY>(0);
        }
        List<KEY> keys = new ArrayList<KEY>(entities.size());
        for (ENTITY entity : entities) {
            if (entity != null) {
                if(isObjNotNull(keyGetter.getKey(entity))){      //todo 如果元素为空，添加进list 无意义，所以增加此判断
                    keys.add(keyGetter.getKey(entity));
                }
            }
        }
        return keys;
    }

    /**
     * 将entities中的每个对象提取出相应的key与value组成一个map
     *
     * @param entities
     * @param entryGetter
     * @return
     */
    public final static <K, V, ENTITY> Map<K, List<V>> extract2KeyListMap(Collection<ENTITY> entities, EntryGetter<ENTITY, K, V> entryGetter) {
        if (isCollecitonNull(entities)) {
            return new HashMap<K, List<V>>();
        }
        Map<K, List<V>> map = new HashMap<K, List<V>>();
        for (ENTITY entity : entities) {
            if (entity != null) {

                K key = entryGetter.getKey(entity);
                V value = entryGetter.getValue(entity);
                List<V> list = map.get(key);
                if (list == null) {
                    list = new ArrayList<V>();
                    map.put(key, list);
                }
                list.add(value);
            }
        }
        return map;
    }

    /**
     * 将entities中的每个对象提取出相应的key与value组成一个map
     *
     * @param entities
     * @param entryGetter
     * @return
     */
    public final static <K, V, ENTITY> Map<K, V> extract2Map(Collection<ENTITY> entities, EntryGetter<ENTITY, K, V> entryGetter) {
        if (isCollecitonNull(entities)) {
            return new HashMap<K, V>();
        }
        Map<K, V> map = new HashMap<K, V>();
        for (ENTITY entity : entities) {
            K key = entryGetter.getKey(entity);
            V value = entryGetter.getValue(entity);
            map.put(key, value);
        }
        return map;
    }

    public static boolean isCollectionNotNull(Collection collection) {
        return collection != null && collection.size() > 0;
    }

    public static boolean isCollecitonNull(Collection collection) {
        return !isCollectionNotNull(collection);
    }

    /**
     * 把 分 价格 转为  元价格
     *
     * @param minutePrice
     * @return
     */
    public static Double getYuanPrice(int minutePrice) {
        String yuanPrice = BigDecimal.valueOf(minutePrice).divide(new BigDecimal(100)).toString();
        return Double.parseDouble(yuanPrice);
    }

    public static double getFinalPrice(int dividePrice) {
        if (isObjNullOrZero(dividePrice)) {
            return 0;
        }
        return dividePrice / 100;
    }

    /**
     * 返回  数据库查询数据的第一个索引位置
     * @param page     当前页
     * @param pageSize 分页Size
     * @return
     */
    public static int getFirstIndex(int page, int pageSize) {
        return (page - 1) * pageSize;
    }

    /**
     * 获取 总页数
     * @param rowNum   总行数（数据总数）
     * @param pageSize 分页Size
     * @return
     */
    public static int getTotalPageNum(int rowNum, int pageSize) {
        if (0 == pageSize) {
            return 0;
        }
        int totalPageNum = (rowNum + pageSize - 1) / pageSize;
        return totalPageNum;
    }

    /**
     * 判断输入流是否有效
     * @param inputStream
     * @return
     */
    public static boolean isInputStreamLegal(InputStream inputStream) {
        try {
            if (GeneralUtil.isNotNull(inputStream) && inputStream.available() > 0) {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public static boolean isMultipartFileNotNull(MultipartFile multipartFile) {
        return multipartFile != null && multipartFile.getSize() > 0;
    }

    /**
     * 把 String 数组 转化为 其它 类型的数组
     * @param stringArray
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T[] stringToTypeArray(String[] stringArray, Class<T> clazz) {
        if (isNull(stringArray)) {
            return null;
        }
        return (T[]) ConvertUtils.convert(stringArray, clazz);
    }

    public static <T> String changeListToCommaStr(List<T> list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (isListNotNull(list)) {
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                if (0 != i) {
                    stringBuilder.append("," + list.get(i));
                } else {
                    stringBuilder.append(list.get(i));
                }
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 根据 cache key 和 参数值后缀 获得 格式化 后的 cache key
     * @param formerCacheKey    eg: cp/account/id/%s
     * @param suffixParamValues eg: id
     * @return
     */
    public static String getformatCacheKey(String formerCacheKey, Object... suffixParamValues) {
        return String.format(formerCacheKey, suffixParamValues);
    }


    /**
     * 判断String 字符串的 值 是否是数字,  是的话 返回 true
     * @param inputText
     * @return
     */
    public static boolean isNumeric(String inputText) {
        boolean isNumeric = isObjNotNull(inputText) && StringUtils.isNumeric(inputText);
        return isNumeric;
    }


    /**
     * 数组 转 list
     * @param array 数组
     * @param <T>   泛型 参数类型
     * @return 转换的List
     */
    public static <T> List<T> arrayToList(T[] array) {
        if (isArrayNull(array)) {
            return new ArrayList<T>();
        }
        List<T> list = new ArrayList<T>();
        for (T t : array) {
            list.add(t);
        }
        return list;
    }


    public static <T> String getCommaStrFromList(List<T> list) {
        if (isListNull(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T t : list) {
            sb.append(t + ",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static <T> String getCommaStrFromArray(T[] array) {
        if (isArrayNull(array)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T t : array) {
            sb.append(t + ",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }


    /**
     * 获取用户userKey的最后一位
     * @param userKey
     * @return
     */
    public static String getUserIndex(String userKey) {
        String userIndex = userKey.substring(userKey.length() - 1, userKey.length());
        return userIndex;
    }

    /**
     * 对double 值 保留一位小数
     * @param originalVal 原始值
     * @return
     */
    public static double retainADecimal(double originalVal) {
        return (double) Math.round(originalVal * 10) / 10;
    }

    /**
     *  获取请求的 Url地址
     * @param request
     * @return
     */
    public static String getRequestUrl(HttpServletRequest request){
        if(GeneralUtil.isNull(request)){
            return "";
        }
        String requestUrl = request.getRequestURL().append("?").append(request.getQueryString()).toString();
        return requestUrl;
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
