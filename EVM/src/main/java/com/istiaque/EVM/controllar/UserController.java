package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.Browser;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.model.UserRequest;
import com.istiaque.EVM.model.enam.Status;
import com.istiaque.EVM.service.BrowserService;
import com.istiaque.EVM.service.UserRequestService;
import com.istiaque.EVM.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 29/Oct/2019.
 */
@Slf4j
@Controller
public class UserController {
    @Autowired
    UserRequestService userRequestService;
    @Autowired
    BrowserService browserService;
    @Autowired
    UserService userService;

    @GetMapping("/allNewUserRequest")
    public ModelAndView allNewUserRequest(ModelAndView modelAndView, @ModelAttribute("mess") String s) {
        List list = userRequestService.findAllUnaproveUser();
        modelAndView.addObject("requestedUserList", list);
        if (s != null) {
            if (s.contains("Rejected")) {
                modelAndView.addObject("err", "error");
            }
        }
        modelAndView.addObject("mess", s);
        modelAndView.setViewName("requestedUser");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/getOneNewRequestUser/{id}")
    public UserRequest getOneRequest(@PathVariable Integer id) {
        return userRequestService.findById(id);
    }

    @ResponseBody
    @GetMapping("/getBrowserForUser/{bid}")
    public Browser getBrowserForUser(@PathVariable String bid) {
        System.out.println(bid);
        return browserService.findByRegistrationNo(bid);
    }

    @PostMapping("/userRequestAccept")
    public ModelAndView userRequestAccept(ModelAndView modelAndView, HttpServletRequest request, RedirectAttributes redirect) {
        Integer id = Integer.valueOf(request.getParameter("requId"));
        UserRequest userRequest = userRequestService.findById(id);
        String status = request.getParameter("requStatus");
        userRequest.setStatus(Status.valueOf(status));
        String mess = userRequestService.userRequestApproved(userRequest);
        System.out.println(mess);
        redirect.addAttribute("mess", mess);
        modelAndView.setViewName("redirect:/allNewUserRequest");
        return modelAndView;
    }

    @GetMapping("/setpassword")
    public ModelAndView setPassword(ModelAndView modelAndView, HttpSession session, HttpServletRequest request) {
        String token = request.getParameter("tk");
        String validity = request.getParameter("v");
        if (token != "" && validity != "") {
//            token = AesEncryption.decrypt(token);
            Long time = Long.valueOf(validity);//AesEncryption.decrypt(validity));
            System.out.println(token + "~" + time);
            User user = userService.findByToken(token);
//            if (System.currentTimeMillis() - time <= 60000 * 5) {//check 5 min. validity
            if (System.currentTimeMillis() - time <= 86400000 * 7 && user != null) {//check 7 day validity
                session.setAttribute("username", user.getUserName());
                session.setAttribute("id", user.getId());
                modelAndView.setViewName("unauthenticatedPage/resetPassword");
            } else {
                log.warn("token validity expire" + TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - (time + 3000000))) / 60);
                modelAndView.setViewName("redirect:/singup");
            }
        } else {
            log.warn("invalid url");
            modelAndView.setViewName("redirect:/singup");
        }
        return modelAndView;
    }

    @PostMapping("/savePassword")
    public ModelAndView savePassword(ModelAndView modelAndView, HttpSession session, HttpServletRequest request,RedirectAttributes redirect) {
        String pass = request.getParameter("pass");
        String recheckPass = request.getParameter("pass2");
        String username = session.getAttribute("username").toString();
        String userid = session.getAttribute("id").toString();
        if (username != "" && pass.equals(recheckPass)) {
            userService.passwordUpdate(username, pass);
            redirect.addFlashAttribute("mess","Please login by your new password.");
            modelAndView.setViewName("redirect:/login");
        }else {
            modelAndView.setViewName("redirect:/singup");
        }
        return modelAndView;
    }
}
