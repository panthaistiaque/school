package com.istiaque.EVM.service.imp;

import com.istiaque.EVM.model.Browser;
import com.istiaque.EVM.repository.BrowserRepository;
import com.istiaque.EVM.repository.UserRequestRepository;
import com.istiaque.EVM.service.BrowserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
@Service
public class BrowserServiceImp implements BrowserService {
    @Autowired
    BrowserRepository browserRepository;
    @Autowired
    UserRequestRepository userRequestRepository;

    @Override
    public boolean isNotRegister(HttpServletRequest request) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList != null) {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals("_bid")) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Cookie newBrowserId(String name) {
        Cookie bid = new Cookie(name, System.currentTimeMillis() + "::" + UUID.randomUUID().toString());
        bid.setMaxAge(60 * 60 * 24 * 365);
        bid.setHttpOnly(true);
        bid.setComment("confidential");
        bid.setVersion(1);
        return bid;
    }

    @Override
    public Browser saveBrowser(HttpServletRequest request, String bid) {
        Browser browser = new Browser();
        browser.setRegistrationNo(bid);
        browser.setClientIpAddress(getClientIpAddress(request));
        browser.setClientBrowser(getClientBrowser(request));
        browser.setClientOS(getClientOS(request));
        browser.setUserAgent(request.getHeader("User-Agent"));
        System.out.println(browser.getRegistrationNo() + " :: " + browser.getClientIpAddress() + " :: " + browser.getClientBrowser() + "  :: " + browser.getClientOS());
        return browserRepository.save(browser);
    }

    @Override
    public String cookieValue(HttpServletRequest request, String name) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList != null) {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return "--";
    }

    @Override
    public Browser saveBrowserUserName(HttpServletRequest request, String userName) {
        Browser browser = browserRepository.findByRegistrationNo(cookieValue(request, "_bid"));
        if (browser != null) {
            boolean doSave= true;
            for (String s : browser.getUserName()) {
                if(s.equalsIgnoreCase(userName)){
                    doSave = false;
                    break;
                }
            }
           if(doSave) {
               List list = browser.getUserName();
               list.add(userName);
               browser.setUserName(list);
               browser =  browserRepository.save(browser);
           }
           return browser;
        } else {
            return new Browser();
        }
    }

    @Override
    public Browser findByRegistrationNo(String registrationNo) {
        return browserRepository.findByRegistrationNo(registrationNo);
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private String getClientOS(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");

        //=================OS=======================
        final String lowerCaseBrowser = browserDetails.toLowerCase();
        if (lowerCaseBrowser.contains("windows")) {
            return "Windows -" + browserDetails.substring(browserDetails.indexOf("Windows NT ") + 11, browserDetails.indexOf(";"));
        } else if (lowerCaseBrowser.contains("mac")) {
            return "Mac";
        } else if (lowerCaseBrowser.contains("x11")) {
            return "Unix";
        } else if (lowerCaseBrowser.contains("android")) {
            return browserDetails.substring(browserDetails.indexOf("(")+1, browserDetails.indexOf(";"));
        } else if (lowerCaseBrowser.contains("iphone")) {
            return "IPhone";
        } else {
            return "UnKnown, More-Info: " + browserDetails;
        }
    }

    private String getClientBrowser(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");
        final String user = browserDetails.toLowerCase();

        String browser = "";

        //===============Browser===========================
        if (user.contains("msie")) {
            String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split(
                    "/")[0] + "-" + (browserDetails.substring(
                    browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera"))
                browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split(
                        "/")[0] + "-" + (browserDetails.substring(
                        browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if (user.contains("opr"))
                browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/",
                        "-")).replace(
                        "OPR", "Opera");
        } else if (user.contains("chrome")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf(
                "mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf(
                "mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            browser = "IE";
        } else {
            browser = "UnKnown, More-Info: " + browserDetails;
        }

        return browser;
    }

}
