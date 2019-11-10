package com.istiaque.EVM.controllar;

import com.istiaque.EVM.model.RequestRejectionMesg;
import com.istiaque.EVM.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 08/Nov/2019.
 */
@RestController
@RequestMapping(value = "/v1")
public class ApiController {
    @Autowired
    UserRequestService userRequestService;

    @PostMapping("/isMailUsed")
    public boolean isUserAvailable(HttpServletRequest request) {
        return userRequestService.isUserExist(request.getParameter("email"));
    }
    @GetMapping("/rejectionMessage/{requestid}")
    public RequestRejectionMesg mesg(@PathVariable("requestid") Long requestid){
        return userRequestService.getRejectionMessage(requestid);
    }
}
