package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.UserRequest;
import com.istiaque.EVM.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 29/Oct/2019.
 */
@Controller
public class UserController {
    @Autowired
    UserRequestService userRequestService;

    @GetMapping("/allNewUserRequest")
    public ModelAndView allNewUserRequest(ModelAndView modelAndView) {
        List list = userRequestService.findAllUnaproveUser();
        modelAndView.addObject("requestedUserList",list);
        modelAndView.setViewName("requestedUser");
        return modelAndView;
    }
    @ResponseBody
    @GetMapping("/getOneNewRequestUser/{id}")
    public UserRequest getOneRequest(@PathVariable Integer id){
        return userRequestService.findById(id);
    }
}
