package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.Profile;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 11/17/2019.
 */
@Controller
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/profile")
    public ModelAndView getProfile(ModelAndView modelAndView, @AuthenticationPrincipal User currentUser) {
        modelAndView.addObject("userObj", currentUser);
        modelAndView.setViewName("commonPage/profile");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/profileData")
    public Profile saveProfile(HttpServletRequest request, HttpSession session, @AuthenticationPrincipal User currentUser) {
        String fName = request.getParameter("fatherName");
        String mName = request.getParameter("motherName");
        String cAddress = request.getParameter("currAddress");
        String pAdd = request.getParameter("presAddress");
        String photo = request.getParameter("profile");
        String sing = request.getParameter("sing");
        Profile pro = (Profile) session.getAttribute("profile");
        Profile profile = new Profile();
        if (pro.getProfileId()!=null) {
            profile = pro;
        }
        profile.setFatherName(fName);
        profile.setMotherName(mName);
        profile.setPermanentAddress(cAddress);
        profile.setPresentAddress(pAdd);
        profile.setProfilePhoto(photo);
        profile.setSing(sing);
        profile.setUser(currentUser);
        session.setAttribute("profile", profileService.save(profile));
        return profile ;
    }
}
