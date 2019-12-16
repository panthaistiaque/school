package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.Profile;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.model.VoterList;
import com.istiaque.EVM.service.CandidateService;
import com.istiaque.EVM.service.ElectionService;
import com.istiaque.EVM.service.VoterListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 12/8/2019.
 */
@Slf4j
@Controller
public class VoteController {
    @Autowired
    VoterListService voterListService;
    @Autowired
    ElectionService electionService;
    @Autowired
    CandidateService candidateService;

    @GetMapping("voterrequest")
    public ModelAndView index(ModelAndView modelAndView, @AuthenticationPrincipal User user,@ModelAttribute("mess") String s) {
        modelAndView.addObject("list", voterListService.findAllByUserId(user.getId()));
        if(s!=null){
            modelAndView.addObject("message", s);
        }
        modelAndView.setViewName("vote/voterRequest");
        return modelAndView;
    }

    @PostMapping("voterrequest")
    public ModelAndView saveVoter(ModelAndView modelAndView, HttpServletRequest request, HttpSession session,RedirectAttributes redirect) {
        String s = request.getParameter("electionCode");
        Profile profile = (Profile) session.getAttribute("profile");
        voterListService.save(s, profile.getUser().getId());
        redirect.addFlashAttribute("mess","Your request accepted, you will be notify shortly.");
        modelAndView.setViewName("redirect:/voterrequest");
        return modelAndView;
    }
    @GetMapping("/nomination")
    public ModelAndView nominationPaper(ModelAndView modelAndView,@AuthenticationPrincipal User user,@ModelAttribute("mess") String s){
        modelAndView.addObject("list", voterListService.findAllByUserId(user.getId()));
        if(s!=null){
            modelAndView.addObject("message", s);
        }
        modelAndView.setViewName("vote/nomination");
        return modelAndView;
    }
    @PostMapping("/save_nomination")
    public ModelAndView nominationSave(ModelAndView modelAndView,@AuthenticationPrincipal User user, HttpServletRequest request, HttpSession session,RedirectAttributes redirect) {
        try {
            long candidatesId = Long.parseLong(request.getParameter("candidateVoterNo"));
            long supporterId = Long.parseLong(request.getParameter("supporterVoterNo"));
            candidateService.saveCandidate(candidatesId, supporterId, session);
        }catch (NumberFormatException nfe){
            log.error("you enter wrong supporter Id");
        }
        redirect.addFlashAttribute("mess","Your request accepted, you will be notify shortly.");
        modelAndView.setViewName("redirect:/nomination");
        return modelAndView;
    }
}
