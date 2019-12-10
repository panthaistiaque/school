package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.Profile;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.model.VoterList;
import com.istiaque.EVM.service.ElectionService;
import com.istiaque.EVM.service.VoterListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 12/8/2019.
 */
@Controller
public class VoteController {
    @Autowired
    VoterListService voterListService;
    @Autowired
    ElectionService electionService;

    @GetMapping("voterrequest")
    public ModelAndView index(ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        modelAndView.addObject("list", voterListService.findAllByUserId(user.getId()));
        modelAndView.setViewName("vote/voterRequest");
        return modelAndView;
    }

    @PostMapping("voterrequest")
    public ModelAndView saveVoter(ModelAndView modelAndView, HttpServletRequest request, HttpSession session) {
        String s = request.getParameter("electionCode");
        Profile profile = (Profile) session.getAttribute("profile");
        voterListService.save(s, profile.getUser().getId());
        modelAndView.setViewName("redirect:/voterrequest");
        return modelAndView;
    }
    @GetMapping("/nomination")
    public ModelAndView nominationPaper(ModelAndView modelAndView,@AuthenticationPrincipal User user){
        modelAndView.addObject("list", voterListService.findAllByUserId(user.getId()));
        modelAndView.setViewName("vote/nomination");
        return modelAndView;
    }
}
