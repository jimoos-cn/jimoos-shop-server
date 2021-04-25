package cn.jimoos.utils;

import cn.jimoos.utils.random.CodeUtil;

/**
 * 内部对外编码 编解码
 *
 * @author :keepcleargas
 * @date :2020-12-17 14:59.
 */
public class OutTradeNoEncoder {
    private OutTradeNoEncoder() {
    }

    public static String encodeOutTradeNo(String outTradeNo) {
        return String.format("%s%s", outTradeNo, CodeUtil.genRamNumber(10));
    }

    public static String decodeOutTradeNo(String outTradeNoAfter) {
        return outTradeNoAfter.substring(0, outTradeNoAfter.length() - 10);
    }
}
