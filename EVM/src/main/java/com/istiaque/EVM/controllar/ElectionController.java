package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.Association;
import com.istiaque.EVM.model.Election;
import com.istiaque.EVM.model.User;
import com.istiaque.EVM.service.ElectionService;
import com.istiaque.EVM.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 11/20/2019.
 */
@Controller
public class ElectionController {
    @Autowired
    ElectionService electionService;

    @GetMapping("/newelection")
    public ModelAndView newElection(ModelAndView modelAndView) {
        modelAndView.addObject("association", electionService.getAssociation());
        modelAndView.addObject("electionList", electionService.getAllOpenElection());
        modelAndView.setViewName("election/newElection");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/newAssociation")
    public Association addAssociation(HttpServletRequest request, @AuthenticationPrincipal User user) {
        String name = request.getParameter("name");
        String address = request.getParameter("add");
        Association association = new Association();
        association.setAssociationName(name);
        association.setAssociationAddress(address);
        association.setCreatedBy(user.getId());
        association.setCreationDate(DateUtil.currentDateTime());
        return electionService.saveAssociation(association);
    }

    @PostMapping("/saveElection")
    public ModelAndView saveElection(ModelAndView modelAndView, @ModelAttribute Election election,@AuthenticationPrincipal User user) {
        election.setCreatedBy(user.getId());
        electionService.save(election);
        modelAndView.setViewName("redirect:/newelection");
        return modelAndView;
    }
}
