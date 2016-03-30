package com.boomstack.pyutil;

/**
 * Created by wangkangfei on 2016/3/30.
 */
public final class PyUtil {

    /**
     * transform a string into pinyin string, and the chinese part will be transformed into uppercase,
     * and the characters don't belong to Chinese will not change.
     * for example, "我是中国人" --> "WOSHIZHOGNGUOREN";
     * "我ye是中国人" -->"WOyeSHIZHOGGUOREN"
     */
    public static String strToPinyinUpper(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return strToPinyinUpper(str.toCharArray());
    }

    public static String strToPinyinUpper(char[] chars) {
        if (chars == null || chars.length == 0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for (char c : chars) {
                if (isChinese(c)) {
                    builder.append(toPinyin(c));
                } else {
                    builder.append(String.valueOf(c));
                }
            }
            return builder.toString();
        }
    }

    /**
     * transform a string into pinyin string, and the chinese part will be transformed into lowercase,
     * and the characters don't belong to Chinese will not change.
     * for example, "我是中国人" --> "woshizhongguoren";
     * "我ye是中国人" -->"woyeshizhogguoren"
     */
    public static String strToPinyinLower(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return strToPinyinLower(str.toCharArray());
    }

    public static String strToPinyinLower(char[] chars) {
        if (chars == null || chars.length == 0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for (char c : chars) {
                if (isChinese(c)) {
                    builder.append(toPinyin(c).toLowerCase());
                } else {
                    builder.append(String.valueOf(c));
                }
            }
            return builder.toString();
        }
    }

    /**
     * transform a string into pinyin string, and the chinese part will be transformed into uppercase,
     * as well as the non Chinese part.
     * for example, "我是中国人" --> "WOSHIZHOGNGUOREN";
     * "我ye是中国人" -->"WOYESHIZHOGGUOREN"
     */
    public static String strToPinyinUpperAll(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return strToPinyinUpperAll(str.toCharArray());
    }

    public static String strToPinyinUpperAll(char[] chars) {
        if (chars == null || chars.length == 0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for (char c : chars) {
                if (isChinese(c)) {
                    builder.append(toPinyin(c).toUpperCase());
                } else {
                    builder.append(String.valueOf(c).toUpperCase());
                }
            }
            return builder.toString();
        }
    }

    /**
     * transform a string into pinyin string, and the chinese part will be transformed into lowercase,
     * as well as the non Chinese part.
     * for example, "我是中国人" --> "woshizhongguoren";
     * "我YE是中国人" -->"woyeshizhognguoren"
     */
    public static String strToPinyinLowerAll(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return strToPinyinLowerAll(str.toCharArray());
    }

    public static String strToPinyinLowerAll(char[] chars) {
        if (chars == null || chars.length == 0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for (char c : chars) {
                if (isChinese(c)) {
                    builder.append(toPinyin(c).toLowerCase());
                } else {
                    builder.append(String.valueOf(c).toLowerCase());
                }
            }
            return builder.toString();
        }
    }

    /**
     * return pinyin if c is chinese in uppercase, String.valueOf(c) otherwise.
     */
    public static String toPinyin(char c) {
        if (c == PinyinData.CHAR_12295) {
            return PinyinData.PINYIN_12295;
        } else {
            return PinyinData.PINYIN_TABLE[getPinyinCode(c)];
        }

    }

    /**
     * return whether c is chinese
     */
    public static boolean isChinese(char c) {
        return (PinyinData.MIN_VALUE <= c && c <= PinyinData.MAX_VALUE
                && getPinyinCode(c) > 0)
                || PinyinData.CHAR_12295 == c;
    }

    private static int getPinyinCode(char c) {
        int offset = c - PinyinData.MIN_VALUE;
        if (0 <= offset && offset < PinyinData.PINYIN_CODE_1_OFFSET) {
            return decodeIndex(PinyinCodeOne.PINYIN_CODE_PADDING, PinyinCodeOne.PINYIN_CODE, offset);
        } else if (PinyinData.PINYIN_CODE_1_OFFSET <= offset
                && offset < PinyinData.PINYIN_CODE_2_OFFSET) {
            return decodeIndex(PinyinCodeTwo.PINYIN_CODE_PADDING, PinyinCodeTwo.PINYIN_CODE,
                    offset - PinyinData.PINYIN_CODE_1_OFFSET);
        } else {
            return decodeIndex(PinyinCodeThree.PINYIN_CODE_PADDING, PinyinCodeThree.PINYIN_CODE,
                    offset - PinyinData.PINYIN_CODE_2_OFFSET);
        }
    }

    private static short decodeIndex(byte[] paddings, byte[] indexes, int offset) {
        //CHECKSTYLE:OFF
        int index1 = offset / 8;
        int index2 = offset % 8;
        short realIndex;
        realIndex = (short) (indexes[offset] & 0xff);
        //CHECKSTYLE:ON
        if ((paddings[index1] & PinyinData.BIT_MASKS[index2]) != 0) {
            realIndex = (short) (realIndex | PinyinData.PADDING_MASK);
        }
        return realIndex;
    }
}
