package com.changhong.sdk.baseapi;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;
import android.util.Log;

/**
 * <功能详细描述>
 * 
 * @author wanghonghong
 * @version [版本号, 2013-12-31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StringUtils
{
    
    private static final String TAG = "StringUtil";
    
    /**
     * 判断是否为null或空值
     * 
     * @param str
     *            String
     * @return true or false
     */
    public static boolean isNullOrEmpty(String str)
    {
        return str == null || str.trim().length() == 0;
    }
    
    /**
     * 判断str1和str2是否相同
     * 
     * @param str1
     *            str1
     * @param str2
     *            str2
     * @return true or false
     */
    public static boolean equals(String str1, String str2)
    {
        return str1 == str2 || str1 != null && str1.equals(str2);
    }
    
    /**
     * 判断str1和str2是否相同(不区分大小写)
     * 
     * @param str1
     *            str1
     * @param str2
     *            str2
     * @return true or false
     */
    public static boolean equalsIgnoreCase(String str1, String str2)
    {
        return str1 != null && str1.equalsIgnoreCase(str2);
    }
    
    /**
     * 
     * 判断字符串str1是否包含字符串str2
     * 
     * @param str1
     *            源字符串
     * @param str2
     *            指定字符串
     * @return true源字符串包含指定字符串，false源字符串不包含指定字符串
     */
    public static boolean contains(String str1, String str2)
    {
        return str1 != null && str1.contains(str2);
    }
    
    /**
     * 
     * 判断字符串是否为空，为空则返回一个空值，不为空则返回原字符串
     * 
     * @param str
     *            待判断字符串
     * @return 判断后的字符串
     */
    public static String getString(String str)
    {
        return str == null ? "" : str;
    }
    
    /**
     * 过滤HTML标签，取出文本内容
     * 
     * @param inputString
     *            HTML字符串
     * @return 过滤了HTML标签的字符串
     */
    public static String filterHtmlTag(String inputString)
    {
        String htmlStr = inputString;
        String textStr = "";
        Pattern pScript;
        Matcher mScript;
        Pattern pStyle;
        Matcher mStyle;
        Pattern pHtml;
        Matcher mHtml;
        
        try
        {
            // 定义script的正则表达式
            String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?script[\\s]*?>";
            // 定义style的正则表达式
            String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?style[\\s]*?>";
            // 定义HTML标签的正则表达式
            String regExHtml = "<[^>\"]+>";
            
            pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
            mScript = pScript.matcher(htmlStr);
            // 过滤script标签
            htmlStr = mScript.replaceAll("");
            
            pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
            mStyle = pStyle.matcher(htmlStr);
            // 过滤style标签
            htmlStr = mStyle.replaceAll("");
            
            pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
            mHtml = pHtml.matcher(htmlStr);
            // 过滤html标签
            htmlStr = mHtml.replaceAll("");
            
            textStr = htmlStr;
            
        }
        catch (Exception e)
        {
            
            Log.e(TAG, "filterHtmlTag:" + e.toString(), e);
        }
        
        return textStr;
    }
    
    /**
     * 将字符串数组转化为字符串，默认以","分隔
     * 
     * @param values
     *            字符串数组
     * @param split
     *            分隔符，默认为","
     * @return 有字符串数组转化后的字符串
     */
    public static String arrayToString(String[] values, String split)
    {
        StringBuffer buffer = new StringBuffer();
        if (values != null)
        {
            if (split == null)
            {
                split = ",";
            }
            int len = values.length;
            for (int i = 0; i < len; i++)
            {
                buffer.append(values[i]);
                if (i != len - 1)
                {
                    buffer.append(split);
                }
            }
        }
        return buffer.toString();
    }
    
    /**
     * 
     * 将字符串list转化为字符串，默认以","分隔<BR>
     * 
     * @param strList
     *            字符串list
     * @param split
     *            分隔符，默认为","
     * @return 组装后的字符串对象
     */
    public static String listToString(Collection<String> strList, String split)
    {
        String[] values = null;
        if (strList != null)
        {
            values = new String[strList.size()];
            strList.toArray(values);
        }
        return arrayToString(values, split);
    }
    
    /**
     * 验证字符串是否符合email格式
     * 
     * @param email
     *            需要验证的字符串
     * @return 验证其是否符合email格式，符合则返回true,不符合则返回false
     */
    public static boolean isEmail(String email)
    {
        // 通过正则表达式验证email是否合法
        if (email == null)
        {
            return false;
        }
        if (email.toLowerCase()
                .matches("^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$"))
        {
            return true;
        }
        return false;
    }
    
    /**
     * 验证字符串是否为数字
     * 
     * @param str
     *            需要验证的字符串
     * @return 不是数字返回false，是数字就返回true
     */
    public static boolean isNumeric(String str)
    {
        return str != null && str.matches("[0-9]*");
    }
    
    /**
     * 替换字符串中特殊字符
     * 
     * @param strData
     *            源字符串
     * @return 替换了特殊字符后的字符串，如果strData为NULL，则返回空字符串
     */
    public static String encodeString(String strData)
    {
        if (strData == null)
        {
            return "";
        }
        return strData.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("'", "&apos;")
                .replaceAll("\"", "&quot;");
    }
    
    /**
     * 还原字符串中特殊字符
     * 
     * @param strData
     *            strData
     * @return 还原后的字符串
     */
    public static String decodeString(String strData)
    {
        if (strData == null)
        {
            return "";
        }
        return strData.replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&apos;", "'")
                .replaceAll("&quot;", "\"")
                .replaceAll("&amp;", "&");
    }
    
    /**
     * 
     * 组装XML字符串<BR>
     * [功能详细描述]
     * 
     * @param map
     *            键值Map
     * @return XML字符串
     */
    public static String generateXml(Map<String, Object> map)
    {
        
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<root>");
        if (map != null)
        {
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                xml.append("<");
                xml.append(key);
                xml.append(">");
                xml.append(entry.getValue());
                xml.append("</");
                xml.append(key);
                xml.append(">");
            }
        }
        xml.append("</root>");
        return xml.toString();
    }
    
    /**
     * 
     * 组装XML字符串<BR>
     * [功能详细描述]
     * 
     * @param values
     *            key、value依次排列
     * @return XML字符串
     */
    public static String generateXml(String... values)
    {
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<root>");
        if (values != null)
        {
            int size = values.length >> 1;
            for (int i = 0; i < size; i++)
            {
                xml.append("<");
                xml.append(values[i << 1]);
                xml.append(">");
                xml.append(values[(i << 1) + 1]);
                xml.append("</");
                xml.append(values[i << 1]);
                xml.append(">");
            }
        }
        xml.append("</root>");
        return xml.toString();
    }
    
    /**
     * 将srcString按split拆分，并组装成List。默认以','拆分。<BR>
     * 
     * @param srcString
     *            源字符串
     * @param split
     *            分隔符
     * @return 返回list
     */
    public static List<String> parseStringToList(String srcString, String split)
    {
        List<String> list = null;
        if (!StringUtils.isNullOrEmpty(srcString))
        {
            if (split == null)
            {
                split = ",";
            }
            String[] strArr = srcString.split(split);
            if (strArr != null && strArr.length > 0)
            {
                list = new ArrayList<String>(strArr.length);
                for (String str : strArr)
                {
                    list.add(str);
                }
            }
        }
        return list;
    }
    
    /**
     * 
     * 按照一个汉字两个字节的方法计算字数
     * 
     * @param string
     *            String
     * @return 返回字符串's count
     */
    public static int count2BytesChar(String string)
    {
        int count = 0;
        if (string != null)
        {
            for (char c : string.toCharArray())
            {
                count++;
                if (isChinese(c))
                {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * 判断字符串中是否包含中文 <BR>
     * [功能详细描述] [added by 杨凡]
     * 
     * @param str
     *            检索的字符串
     * @return 是否包含中文
     */
    public static boolean hasChinese(String str)
    {
        boolean hasChinese = false;
        if (str != null)
        {
            for (char c : str.toCharArray())
            {
                if (isChinese(c))
                {
                    hasChinese = true;
                    break;
                }
            }
        }
        return hasChinese;
    }
    
    /**
     * 
     * 截取字符串，一个汉字按两个字符来截取<BR>
     * [功能详细描述] [added by 杨凡]
     * 
     * @param src
     *            源字符串
     * @param charLength
     *            字符长度
     * @return 截取后符合长度的字符串
     */
    public static String subString(String src, int charLength)
    {
        if (src != null)
        {
            int i = 0;
            for (char c : src.toCharArray())
            {
                i++;
                charLength--;
                if (isChinese(c))
                {
                    charLength--;
                }
                if (charLength <= 0)
                {
                    if (charLength < 0)
                    {
                        i--;
                    }
                    break;
                }
            }
            return src.substring(0, i);
        }
        return src;
    }
    
    /**
     * 对字符串进行截取, 超过则以...结束
     * 
     * @param originStr
     *            原字符串
     * @param maxCharLength
     *            最大字符数
     * @return 截取后的字符串
     */
    public static String trim(String originStr, int maxCharLength)
    {
        if (TextUtils.isEmpty(originStr))
        {
            return "";
        }
        int count = 0;
        int index = 0;
        int originLen = originStr.length();
        for (index = 0; index < originLen; index++)
        {
            char c = originStr.charAt(index);
            int len = 1;
            if (isChinese(c))
            {
                len++;
            }
            if (count + len <= maxCharLength)
            {
                count += len;
            }
            else
            {
                break;
            }
        }
        
        if (index < originLen)
        {
            return originStr.substring(0, index) + "...";
        }
        else
        {
            return originStr;
        }
    }
    
    /**
     * 
     * 判断参数c是否为中文<BR>
     * [功能详细描述] [added by 杨凡]
     * 
     * @param c
     *            char
     * @return 是中文字符返回true，反之false
     */
    public static boolean isChinese(char c)
    {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
        
    }
    
    /**
     * 
     * 检测密码强度
     * 
     * @param password
     *            密码
     * @return 密码强度（1：低 2：中 3：高）
     */
    public static int checkStrong(String password)
    {
        boolean num = false;
        boolean lowerCase = false;
        boolean upperCase = false;
        boolean other = false;
        
        int threeMode = 0;
        int fourMode = 0;
        
        for (int i = 0; i < password.length(); i++)
        {
            // 单个字符是数字
            if (password.codePointAt(i) >= 48 && password.codePointAt(i) <= 57)
            {
                num = true;
            }
            // 单个字符是小写字母
            else if (password.codePointAt(i) >= 97
                    && password.codePointAt(i) <= 122)
            {
                lowerCase = true;
            }
            // 单个字符是大写字母
            else if (password.codePointAt(i) >= 65
                    && password.codePointAt(i) <= 90)
            {
                upperCase = true;
            }
            // 特殊字符
            else
            {
                other = true;
            }
        }
        
        if (num)
        {
            threeMode++;
            fourMode++;
        }
        
        if (lowerCase)
        {
            threeMode++;
            fourMode++;
        }
        
        if (upperCase)
        {
            threeMode++;
            fourMode++;
        }
        
        if (other)
        {
            fourMode = fourMode + 1;
        }
        
        // 数字、大写字母、小写字母只有一个，密码强度低（只有一种特殊字符也是密码强度低）
        if (threeMode == 1 && !other || fourMode == 1)
        {
            return 1;
        }
        // 四种格式有其中两个，密码强度中
        else if (fourMode == 2)
        {
            return 2;
        }
        // 四种格式有三个或者四个，密码强度高
        else if (fourMode >= 3)
        {
            return 3;
        }
        // 正常情况下不会出现该判断
        else
        {
            return 3;
        }
    }
    
    /**
     * 
     * 返回一个制定长度范围内的随机字符串
     * 
     * @param min
     *            范围下限
     * @param max
     *            范围上限
     * @return 字符串
     */
    public static String createRandomString(int min, int max)
    {
        StringBuffer strB = new StringBuffer();
        SecureRandom random = new SecureRandom();
        int lenght = min;
        if (max > min)
        {
            lenght += random.nextInt(max - min + 1);
        }
        for (int i = 0; i < lenght; i++)
        {
            strB.append((char) (97 + random.nextInt(26)));
        }
        return strB.toString();
    }
    
    /**
     * 
     * [用于获取字符串中字符的个数]<BR>
     * [功能详细描述]
     * 
     * @param content
     *            文本内容
     * @return 返回字符的个数
     */
    public static int getStringLeng(String content)
    {
        return (int) Math.ceil(count2BytesChar(content) / 2.0);
    }
    
    /**
     * 
     * 根据参数tag（XML标签）解析该标签对应的值<BR>
     * 本方法针对简单的XML文件，仅通过字符串截取的方式获取标签值
     * 
     * @param xml
     *            XML文件字符串
     * @param tag
     *            XML标签名，说明：标签名不需加“<>”，方法中已做处理
     * @return 标签对应的值
     */
    public static String getXmlValue(String xml, String tag)
    {
        if (xml == null || tag == null)
        {
            Log.w(TAG, "XML OR TAG is null!");
            return null;
        }
        
        // 如果标签中包含了"<"或">"，先去掉
        tag = tag.replace("<", "").replace(">", "");
        
        // 截取值
        int index = xml.indexOf(tag);
        if (index != -1)
        {
            int endIndex = xml.indexOf('<', index);
            if (endIndex != -1)
            {
                return xml.substring(index + tag.length() + 1, endIndex);
            }
        }
        
        Log.e(TAG, "No such tag : " + tag + " was found!");
        return null;
    }
    
    /**
     * 去掉字符串前后空格
     * 
     * @param string
     *            要去掉前后空格的字符串
     * @return 去掉前后空格的字符串
     */
    public static String stringTrimAllSpace(String string)
    {
        if (TextUtils.isEmpty(string))
        {
            return "";
        }
        string = string.trim();
        while (string.startsWith(" "))
        {
            string = string.substring(1, string.length());
        }
        return string;
    }
    
    /**
     * 
     * 生成唯一的字符串对象<BR>
     * 
     * @return 唯一的字符串
     */
    public static String generateUniqueID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * 
     * [一句话功能简述]<BR>
     * [功能详细描述]
     * 
     * @param jid
     *            jid
     * @return String
     */
    public static String[] splitJidAndRealmName(String jid)
    {
        if (null != jid)
        {
            return jid.split("@");
        }
        return null;
    }
    
    /**
     * Finds the index of the first word that starts with the given prefix.
     * <p>
     * 
     * @param text
     *            the text in which to search for the prefix
     * @param prefix
     *            the text to find, in upper case letters
     * @return index If not found,returns -1.
     */
    public static int indexOfWordPrefix(CharSequence text, char[] prefix)
    {
        if (prefix == null || text == null)
        {
            return -1;
        }
        
        int textLength = text.length();
        int prefixLength = prefix.length;
        
        if (prefixLength == 0 || textLength < prefixLength)
        {
            return -1;
        }
        
        int i = 0;
        while (i < textLength)
        {
            // Skip non-word characters
            while (i < textLength && !Character.isLetterOrDigit(text.charAt(i)))
            {
                i++;
            }
            
            if (i + prefixLength > textLength)
            {
                return -1;
            }
            
            // Compare the prefixes
            int j;
            for (j = 0; j < prefixLength; j++)
            {
                if (Character.toUpperCase(text.charAt(i + j)) != prefix[j])
                {
                    break;
                }
            }
            if (j == prefixLength)
            {
                return i;
            }
            
            // Skip this word
            while (i < textLength && Character.isLetterOrDigit(text.charAt(i)))
            {
                i++;
            }
        }
        
        return -1;
    }
    
    /**
     * 進一步过滤HTML标签，取出文本内容
     * 
     * @param inputString
     *            HTML字符串
     * @return 过滤了HTML标签的字符串
     */
    public static String filterHtmlTagDeeply(String inputString)
    {
        return htmlToText(inputString);
    }
    
    /**
     * 
     * 获取一个操作序列号<BR>
     * 
     * @return 序列号 消息格式hhmissSSS，如果首位为0则去掉首位 如果为 000000000 则返回0
     */
    public static String getSeq()
    {
        String result = DateUtils.getMillisecond();
        while (result.startsWith("0"))
        {
            result = result.substring(1, result.length());
        }
        if (result.length() == 0)
        {
            result = "0";
        }
        return result;
    }
    
    /**
     * 
     * 获取一个操作序列号<BR>
     * 
     * @return 序列号 消息格式hhmissSSS，如果首位为0则去掉首位 如果为 000000000 则返回0
     */
    /**
     * 获取一个操作序列号
     * 
     * @param num
     *            num
     * @param internationalPrefix
     *            internationalPrefix
     * @param specialPrefix
     *            specialPrefix
     * @return 序列号 消息格式hhmissSSS，如果首位为0则去掉首位 如果为 000000000 则返回0
     */
    public static String formatCTDNum(String num, String internationalPrefix,
            String specialPrefix)
    {
        if (null == num)
        {
            return null;
        }
        
        StringBuffer sb = new StringBuffer();
        // 添加前缀
        sb.append(specialPrefix);
        
        // 替换"+"
        sb.append(num.replace("+", internationalPrefix));
        
        return sb.toString();
    }
    
    /**
     * 判断字符串中是否包含中文<BR>
     * 
     * @param string
     *            传入的参数
     * @return 是否包含中文
     */
    public static boolean containChinese(String string)
    {
        if (StringUtils.isNullOrEmpty(string))
        {
            return false;
        }
        else
        {
            for (char ch : string.toCharArray())
            {
                if (isChinese(ch))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 去除html标签
     * 
     * @param inputString
     *            输入带html的string
     * @return 去除html标签的string
     */
    public static String htmlToText(String inputString)
    {
        // 含html标签的字符串
        String htmlStr = inputString;
        String textStr = "";
        Pattern pScript;
        Matcher mScript;
        Pattern pStyle;
        Matcher mStyle;
        Pattern pHtml;
        Matcher mHtml;
        Pattern pHouhtml;
        Matcher mHouhtml;
        Pattern pSpe;
        Matcher mSpe;
        Pattern pBlank;
        Matcher mBlank;
        Pattern pTable;
        Matcher mTable;
        Pattern pEnter;
        Matcher mEnter;
        try
        {
            // 定义script的正则表达式.
            String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            
            // 定义style的正则表达式.
            String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            
            // 定义HTML标签的正则表达式
            String regExHtml = "<[^>]+>";
            
            // 定义HTML标签的正则表达式
            String regExHouhtml = "/[^>]+>";
            
            // 定义特殊符号的正则表达式
            String regExSpe = "\\&[^;]+;";
            
            // 定义多个空格的正则表达式
            String regExBlank = " +";
            
            // 定义多个制表符的正则表达式
            String regExTable = "\t+";
            
            // 定义多个回车的正则表达式
            String regExEnter = "\n+";
            
            // 过滤script标签
            pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
            mScript = pScript.matcher(htmlStr);
            htmlStr = mScript.replaceAll("");
            
            // 过滤style标签
            pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
            mStyle = pStyle.matcher(htmlStr);
            htmlStr = mStyle.replaceAll("");
            
            // 过滤html标签
            pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
            mHtml = pHtml.matcher(htmlStr);
            htmlStr = mHtml.replaceAll("");
            
            // 过滤html标签
            pHouhtml = Pattern.compile(regExHouhtml, Pattern.CASE_INSENSITIVE);
            mHouhtml = pHouhtml.matcher(htmlStr);
            htmlStr = mHouhtml.replaceAll("");
            
            // 过滤特殊符号
            pSpe = Pattern.compile(regExSpe, Pattern.CASE_INSENSITIVE);
            mSpe = pSpe.matcher(htmlStr);
            htmlStr = mSpe.replaceAll("");
            
            // 过滤过多的空格
            pBlank = Pattern.compile(regExBlank, Pattern.CASE_INSENSITIVE);
            mBlank = pBlank.matcher(htmlStr);
            htmlStr = mBlank.replaceAll(" ");
            
            // 过滤过多的制表符
            pTable = Pattern.compile(regExTable, Pattern.CASE_INSENSITIVE);
            mTable = pTable.matcher(htmlStr);
            htmlStr = mTable.replaceAll(" ");
            
            // 过滤过多的制表符
            pEnter = Pattern.compile(regExEnter, Pattern.CASE_INSENSITIVE);
            mEnter = pEnter.matcher(htmlStr);
            htmlStr = mEnter.replaceAll(" ");
            
            if (!TextUtils.isEmpty(htmlStr) && htmlStr.indexOf("<") != -1)
            {
                htmlStr = htmlStr.substring(0, htmlStr.lastIndexOf("<"));
            }
            textStr = htmlStr;
        }
        catch (Exception e)
        {
            Log.e(TAG, "HtmlToText error:" + e.toString());
        }
        
        return textStr;
    }
    
    /**
     * 生成首字母大写，其他字母小写的字符串
     * 
     * @param str
     *            初始字符串
     * @return 生成首字母大写，其他字母小写的字符串
     */
    public static String getRuleString(String str)
    {
        // 空字符串
        if (isNullOrEmpty(str))
        {
            return str;
        }
        
        char ch = Character.toUpperCase(str.charAt(0));
        String subStr = str.substring(1);
        StringBuffer newStr = new StringBuffer();
        // 只有1个字母
        if (isNullOrEmpty(subStr))
        {
            newStr.append(ch);
            return newStr.toString();
        }
        
        subStr = subStr.toLowerCase();
        newStr.append(ch).append(subStr);
        return newStr.toString();
    }
    
    /**
     * 生成oid的最后随机数的长度
     */
    private static final int OID_RANDOM_LENGTH = 5;
    
    /**
     * 是否为null或空字符串
     * 
     * @param str
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(String str)
    {
        if (str == null || "".equals(str.trim()))
        {
            return true;
        }
        return false;
    }
    
    /**
     * 非null或非空
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }
    
    /**
     * <判断是否为手机号码>
     * 
     * @param str
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isPhoneNumber(String phoneNumber)
    {
        String reg = "1[3,4,5,8]{1}\\d{9}";
        return phoneNumber.matches(reg);
    }
    
    /**
     * <判断是否是数字>
     * 
     * @param str
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNumber(String str)
    {
        String reg = "[0-9]+";
        return str.matches(reg);
    }
    
    /**
     * 字符串转为整数(如果转换失败,则返回 -1)
     * 
     * @param str
     * @return [参数说明]
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static int stringToInt(String str)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        try
        {
            return Integer.parseInt(str.trim());
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }
    
    /**
     * 字体串转为boolean (如果转换失败,则返回false)
     * 
     * @param str
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean stringToBoolean(String str)
    {
        if (isEmpty(str))
        {
            return false;
        }
        try
        {
            return Boolean.parseBoolean(str.trim());
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /**
     * boolean转为字体串
     * 
     * @param str
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String booleanToString(Boolean bool)
    {
        String booleanString = "false";
        if (bool)
        {
            booleanString = "true";
        }
        return booleanString;
    }
    
    /**
     * <从异常中获取调用栈>
     * 
     * @param ex
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getExceptionStackTrace(Throwable ex)
    {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null)
        {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        return writer.toString();
    }
    
    /**
     * <Unicode转化为中文>
     * 
     * @param dataStr
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String decodeUnicode(String dataStr)
    {
        final StringBuffer buffer = new StringBuffer();
        String tempStr = "";
        String operStr = dataStr;
        if (operStr != null && operStr.indexOf("\\u") == -1)
        {
            return buffer.append(operStr).toString();
        }
        if (operStr != null && !operStr.equals("")
                && !operStr.startsWith("\\u"))
        {
            tempStr = StringUtils.substring(operStr, 0, operStr.indexOf("\\u"));
            operStr = StringUtils.substring(operStr,
                    operStr.indexOf("\\u"),
                    operStr.length());
        }
        buffer.append(tempStr);
        // 循环处理,处理对象一定是以unicode编码字符打头的字符串
        while (operStr != null && !operStr.equals("")
                && operStr.startsWith("\\u"))
        {
            tempStr = StringUtils.substring(operStr, 0, 6);
            operStr = StringUtils.substring(operStr, 6, operStr.length());
            String charStr = "";
            charStr = StringUtils.substring(tempStr, 2, tempStr.length());
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串
            buffer.append(letter);
            if (operStr.indexOf("\\u") == -1)
            {
                buffer.append(operStr);
            }
            else
            { // 处理operStr使其打头字符为unicode字符
                tempStr = StringUtils.substring(operStr,
                        0,
                        operStr.indexOf("\\u"));
                operStr = StringUtils.substring(operStr,
                        operStr.indexOf("\\u"),
                        operStr.length());
                buffer.append(tempStr);
            }
        }
        return buffer.toString();
    }
    
    /**
     * 字条串截取
     * 
     * @param str
     *            源字符串
     * @param start
     *            开始位置
     * @param end
     *            结束位置
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String substring(String str, int start, int end)
    {
        if (isEmpty(str))
        {
            return "";
        }
        int len = str.length();
        if (start > end)
        {
            return "";
        }
        if (start > len)
        {
            return "";
        }
        if (end > len)
        {
            return str.substring(start, len);
        }
        return str.substring(start, end);
    }
    
    /**
     * 字条串截取
     * 
     * @param str
     *            源字符串
     * @param start
     *            开始位置
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String substring(String str, int start)
    {
        if (isEmpty(str))
        {
            return "";
        }
        int len = str.length();
        if (start > len)
        {
            return "";
        }
        return str.substring(start);
    }
    
    /**
     * <将字符串截取为较短的字符串>
     * 
     * @param content
     * @return CharSequence
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String cutString(String content, int length)
    {
        if (StringUtils.isEmpty(content))
        {
            return "";
        }
        if (content.length() <= length)
        {
            return content;
        }
        return content.substring(0, length);
    }
    
    /**
     * <将字符串多空格，多换行替换成一个空格> <功能详细描述>
     * 
     * @param content
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String tirmString(String content)
    {
        if (StringUtils.isEmpty(content))
        {
            return "";
        }
        
        return content.replaceAll("[ \n\r\t]+", " ");
    }
    
    /**
     * 判断字符是否数字
     * 
     * @param str
     * @return
     */
    public static boolean isDigital(String str)
    {
        return str.matches("(-)?\\d+");
    }
    
    /**
     * 判断字符是否带小数
     * 
     * @param str
     * @return
     */
    public static boolean isDouble(String str)
    {
        if (isDigital(str))
        {
            return true;
        }
        return str.matches("(-)?\\d+\\.\\d+");
    }
    
    /**
     * 返回x小数,如果小数部分不够两位则自动填充小数部分
     * 
     * @param process
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getProcess(String process)
    {
        // 空字符
        if (null == process || "".equals(process.trim()))
        {
            return "";
        }
        
        // 非整数或小数
        if (!(isDigital(process) || isDouble(process)))
        {
            return process;
        }
        
        int index = process.indexOf('.');
        
        // 无小数部分
        if (-1 == index)
        {
            return process + ".00";
        }
        
        // 整数部分
        String prefix = process.substring(0, index);
        
        // 小数部分
        String postfix = process.substring(index + 1);
        
        StringBuilder result = new StringBuilder();
        
        // 小数部分长度
        switch (postfix.length())
        {
        // 无小数部分
            case 0:
                result.append(prefix).append(".00");
                break;
            // 只有一位小数
            case 1:
                result.append(prefix).append('.').append(postfix).append('0');
                break;
            // 两位小数
            case 2:
                result.append(prefix).append('.').append(postfix);
                break;
            // 三位或以上小数,需要进行四舍五入
            default:
                result.append(String.valueOf(Math.round(Double.parseDouble(prefix
                        + postfix.substring(0, 3)) / 10)))
                        .insert(result.length() - 2, '.');
                break;
        }
        return result.toString();
    }
    
    /**
     * 字符串转数字
     * 
     * @param str
     *            字符串
     * @param defualtValue
     *            自定义整型
     * @return 整型
     */
    public static int getInt(String str, int defualtValue)
    {
        return isDigital(str) ? Integer.parseInt(str) : defualtValue;
    }
    
    /**
     * 去掉url中多余的斜杠
     * 
     * @param url
     *            字符串
     * @return 去掉多余斜杠的字符串
     */
    public static String fixUrl(String url)
    {
        if (null == url)
        {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(url);
        for (int i = stringBuffer.indexOf("//", stringBuffer.indexOf("//") + 2); i != -1; i = stringBuffer.indexOf("//",
                i + 1))
        {
            stringBuffer.deleteCharAt(i);
        }
        return stringBuffer.toString();
    }
    
    /**
     * 获取记录唯一ID
     * 
     * @return
     */
    public static String getOID()
    {
        return System.currentTimeMillis() + getRadomNum(OID_RANDOM_LENGTH);
    }
    
    /**
     * 返回特定长度的随机数
     * 
     * @param length
     *            返回长度
     * @return
     */
    private static String getRadomNum(int length)
    {
        length = (length > 0) ? length : 10;
        StringBuffer sRand = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            sRand.append((int) (Math.random() * 10));
        }
        return sRand.toString();
    }
    
    /**
     * <字符串转为long(如果转换失败,则返回 -1)> <功能详细描述>
     * 
     * @param str
     * @return [参数说明]
     * @return long [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static long stringToLong(String str)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        try
        {
            return Long.parseLong(str.trim());
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }
    
    /**
     * 删除弹出对话框中文件名 [一句话功能简述]<BR>
     * [功能详细描述]
     * 
     * @param nameString
     * @return
     */
    public static String getName(String nameString)
    {
        String resultString;
        if (nameString.length() >= 16)
        {
            if (nameString.contains("."))
            {
                if ((nameString.indexOf(".") - 3) == 9)
                {
                    nameString = nameString.substring(0, 10)
                            + nameString.substring(nameString.indexOf(".") - 2,
                                    nameString.length());
                }
                else
                {
                    nameString = nameString.substring(0, 10)
                            + "..."
                            + nameString.substring(nameString.indexOf(".") - 3,
                                    nameString.length());
                }
                
            }
            else
            {
                if ((nameString.indexOf(".") - 3) == 9)
                {
                    nameString = nameString.substring(0, 10)
                            + nameString.substring(nameString.length() - 2,
                                    nameString.length());
                }
                else
                {
                    nameString = nameString.substring(0, 10)
                            + "..."
                            + nameString.substring(nameString.length() - 3,
                                    nameString.length());
                }
                
            }
            
        }
        resultString = nameString;
        return resultString;
    }
    
    /**
     * 下载弹出对话框中文件名 [一句话功能简述]<BR>
     * [功能详细描述]
     * 
     * @param nameString
     * @return
     */
    public static String getDownloadName(String nameString)
    {
        String resultString;
        if (nameString.length() >= 14)
        {
            if (nameString.contains("."))
            {
                if ((nameString.indexOf(".") - 3) == 7)
                {
                    nameString = nameString.substring(0, 8)
                            + nameString.substring(nameString.indexOf(".") - 2,
                                    nameString.length());
                }
                else
                {
                    nameString = nameString.substring(0, 8)
                            + "..."
                            + nameString.substring(nameString.indexOf(".") - 3,
                                    nameString.length());
                }
                
            }
            else
            {
                if ((nameString.indexOf(".") - 3) == 7)
                {
                    nameString = nameString.substring(0, 8)
                            + nameString.substring(nameString.length() - 2,
                                    nameString.length());
                }
                else
                {
                    nameString = nameString.substring(0, 8)
                            + "..."
                            + nameString.substring(nameString.length() - 3,
                                    nameString.length());
                }
                
            }
            
        }
        resultString = nameString;
        return resultString;
    }
    
    /**
     * 字符串翻转
     * 
     * @param s
     * @return
     */
    public static String reverse(String s)
    {
        return new StringBuffer(s).reverse().toString();
    }
    
    /**
     * 字符串根据分割符转化为数组
     * 
     * @param str
     *            待转化数组的字符串
     * @param separateSign
     *            分隔符
     * @return 如果字符串为空或NULL则返回null，否则返回转换之后的数组
     */
    public static String[] stringToArray(String str, String separateSign)
    {
        if (isNullOrEmpty(str))
        {
            return null;
        }
        return str.split(separateSign);
    }
    
    /**
     * 
     * 截取指定字节长度的字符串，不能返回半个汉字
     * 
     * @param str
     * @param length
     * @return
     */
    public static String getSubString(String str, int length)
    {
        int count = 0;
        int offset = 0;
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (c[i] > 256)
            {
                offset = 2;
                count += 2;
            }
            else
            {
                offset = 1;
                count++;
            }
            
            if (count == length)
            {
                return str.substring(0, i + 1);
            }
            if ((count == length + 1 && offset == 2))
            {
                return str.substring(0, i);
            }
        }
        return "";
    }
    
    /**
     * 
     * 处理账号
     * 
     * @param mAccount
     *            账号
     */
    public static String dealAccount(String account)
    {
        if (isNullOrEmpty(account))
        {
            return account.trim();
        }
        String newAccount = account;
        newAccount = newAccount.replaceAll(" ", "").replaceAll("-", "");
        
        return newAccount.trim();
    }
    
    /**
     * 提取英文的首字母，非英文字母用#代替。
     * 
     * @param str
     * @return
     */
    public static String getAlpha(String str)
    {
        if (str == null)
        {
            return "#";
        }
        
        if (str.trim().length() == 0)
        {
            return "#";
        }
        
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches())
        {
            return (c + "").toUpperCase(); // 大写输出
        }
        else
        {
            return "#";
        }
    }
    
    /**
     * 字符串解析为字符串数组
     * 
     * @param str
     * @return
     */
    public static String[] getStrings(String str)
    {
        return str.split(" ");
    }
    
    /***
     * 是否为字母
     * 
     * @param str
     * @return
     */
    public static boolean getLetter(String str)
    {
        return str.matches("^[A-Za-z]+$");
    }
    
    /**
     * 得到全拼或简拼
     * 
     * @param strs
     *            字符串数组
     * @param type
     *            全拼还是简拼 0---简拼 1--全拼
     * @return
     */
    public static String getString(String[] strs, int type)
    {
        String[] newStrs = new String[strs.length];
        int j = 0;
        for (int i = 0; i < strs.length; i++)
        {
            String firstLetter = strs[i].substring(0, 1);
            if (getLetter(firstLetter))
            {
                // type=0 out 简拼
                if (type == 0)
                {
                    newStrs[j] = firstLetter;
                }
                // type=1 out 全拼
                else
                {
                    newStrs[j] = strs[i];
                }
                j++;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < newStrs.length; k++)
        {
            if (newStrs[k] != null)
            {
                sb.append(newStrs[k]);
            }
        }
        return sb.toString();
    }
    
    /**
     * 将右斜杠替换为左斜杠
     * 
     * @param strData
     *            strData
     * @return 
     */
    public static String replaceRightSlash(String strData)
    {
        if (strData == null || strData.equals(""))
        {
            return "";
        }
        return strData.replace('\\', '/');
    }
    
    /**
    * JAVA判断字符串数组中是否包含某字符串元素
    *
    * @param substring 某字符串
    * @param source 源字符串数组
    * @return 包含则返回true，否则返回false
    */
    public static boolean isIn(String substring, String[] source)
    {
        if (source == null || source.length == 0)
        {
            return false;
        }
        for (int i = 0; i < source.length; i++)
        {
            String aSource = source[i];
            if (aSource.equals(substring))
            {
                return true;
            }
        }
        return false;
    }
}
