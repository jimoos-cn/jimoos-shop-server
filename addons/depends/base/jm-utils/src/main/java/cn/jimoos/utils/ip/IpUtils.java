package cn.jimoos.utils.ip;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * @author SiletFlower
 * @date 2021/7/22 18:50:19
 * @description
 */
public class IpUtils {

    private IpUtils(){}

    /**
     * 通过HttpServletRequest返回IP地址`
     * @param request HttpServletRequest
     * @return ip String
     * @throws Exception
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || IpConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || IpConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || IpConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || IpConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || IpConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            //代理IP
            ip = request.getRemoteAddr();
        }
        String[] ips = ip.split(",");
        return ips[0];
    }

    /**
     * 获取设备类型
     * @return
     */
    public static String getDeviceType(HttpServletRequest request) {
        String agentString = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agentString);
        // 操作系统信息
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        // 设备类型
        eu.bitwalker.useragentutils.DeviceType deviceType = operatingSystem.getDeviceType();
        switch (deviceType) {
            case COMPUTER:
                return "PC";
            case TABLET: {
                if (agentString.contains(IpConstant.ANDROID)) {
                    return "Android Pad";
                }
                if (agentString.contains(IpConstant.IOS)) {
                    return "iPad";
                }
                return IpConstant.UNKNOWNDE;
            }
            case MOBILE: {
                if (agentString.contains(IpConstant.ANDROID)) {
                    return "Android";
                }
                if (agentString.contains(IpConstant.IOS)) {
                    return "IOS";
                }
                return IpConstant.UNKNOWNDE;
            }
            default:
                return IpConstant.UNKNOWNDE;
        }
    }


}

