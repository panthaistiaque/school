package com.istiaque.EVM.service;

import com.istiaque.EVM.model.Browser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
public interface BrowserService {
    boolean isNotRegister(HttpServletRequest request);

    Cookie newBrowserId(String name);

    Browser saveBrowser(HttpServletRequest request, String bid);

   String cookieValue(HttpServletRequest request, String name);

    Browser saveBrowserUserName(HttpServletRequest request, String userName);
    Browser findByRegistrationNo(String registrationNo);
}
