package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.Candidate;
import com.istiaque.EVM.model.Profile;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.model.VoterList;
import com.istiaque.EVM.repository.CandidateRepository;
import com.istiaque.EVM.service.CandidateService;
import com.istiaque.EVM.service.ElectionService;
import com.istiaque.EVM.service.NominationService;
import com.istiaque.EVM.service.VoterListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.Ansi8BitColor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Autowired
    NominationService nominationService;

    @GetMapping("voterrequest")
    public ModelAndView index(ModelAndView modelAndView, @AuthenticationPrincipal User user, @ModelAttribute("mess") String s) {
        modelAndView.addObject("list", voterListService.findAllByUserId(user.getId()));
        if (s != null) {
            modelAndView.addObject("message", s);
        }
        modelAndView.setViewName("vote/voterRequest");
        return modelAndView;
    }

    @PostMapping("voterrequest")
    public ModelAndView saveVoter(ModelAndView modelAndView, HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
        String s = request.getParameter("electionCode");
        Profile profile = (Profile) session.getAttribute("profile");
       if(profile.getUser()!=null){
           voterListService.save(s, profile.getUser().getId());
           redirect.addFlashAttribute("mess", "Your request accepted, you will be notify shortly.");
       }else {
           redirect.addFlashAttribute("mess", "Plz set your profile first");
       }
        modelAndView.setViewName("redirect:/voterrequest");
        return modelAndView;
    }

    @GetMapping("/nomination")
    public ModelAndView nominationPaper(ModelAndView modelAndView, @AuthenticationPrincipal User user, @ModelAttribute("mess") String s) {
        modelAndView.addObject("list", voterListService.nominationAbleElectionList(user.getId()));
        if (s != null) {
            modelAndView.addObject("message", s);
        }
        modelAndView.setViewName("vote/nomination");
        return modelAndView;
    }

    @PostMapping("/save_nomination")
    public ModelAndView nominationSave(ModelAndView modelAndView, @AuthenticationPrincipal User user, HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
        try {
            long candidatesId = Long.parseLong(request.getParameter("candidateVoterNo"));
            long supporterId = Long.parseLong(request.getParameter("supporterVoterNo"));
            candidateService.saveCandidate(candidatesId, supporterId, session);
        } catch (NumberFormatException nfe) {
            log.error("you enter wrong supporter Id");
        }
        redirect.addFlashAttribute("mess", "Your request accepted, you will be notify shortly.");
        modelAndView.setViewName("redirect:/nomination");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/cast")
    public ModelAndView getAllVoteAbleElection(ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        modelAndView.addObject("list", voterListService.findAllByUserId(user.getId()));
        modelAndView.setViewName("vote/vote");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/forSupporterInfo")
    public Candidate getCandidateInformation(ModelAndView modelAndView, @AuthenticationPrincipal User user, HttpServletRequest request) {
        String[] stringArray = request.getParameter("id").split("-");
        Candidate candidate = candidateService.findByElectionElectionIdAndVoterListVoterNo(Integer.valueOf(stringArray[1]), Long.valueOf(stringArray[0]));
        return candidate;
    }

    @PostMapping("/nominationAccept")
    public ModelAndView nominationAccept(ModelAndView modelAndView, @AuthenticationPrincipal User user, HttpServletRequest request) {
        try {
            Integer sl = Integer.valueOf(request.getParameter("CandidateElectionId"));
            Long candidateVoterId = Long.valueOf(request.getParameter("candidateVoterId"));
            Long supporterVoterId = Long.valueOf(request.getParameter("nominationSupporterId"));
            String nominationStatus = request.getParameter("userStatus");
            nominationService.acceptNomination(candidateVoterId, supporterVoterId, sl, nominationStatus);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
        }
        modelAndView.setViewName("redirect:/nomination");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/getAllCandidate")
    public List getAllCandidate(ModelAndView modelAndView, @AuthenticationPrincipal User user, HttpServletRequest request) {
        try {
            Integer sl = Integer.valueOf(request.getParameter("electionId"));
            return candidateService.getAllCandidate(sl);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
