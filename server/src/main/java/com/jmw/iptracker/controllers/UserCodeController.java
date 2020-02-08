package com.jmw.iptracker.controllers;

import com.jmw.iptracker.entities.Machine;
import com.jmw.iptracker.entities.UserCode;
import com.jmw.iptracker.services.MachineInfoService;
import com.jmw.iptracker.services.UserCodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserCodeController {

    public UserCodeController(UserCodeService userCodeService,
                              MachineInfoService machineInfoService) {
        this.userCodeService = userCodeService;
        this.machineInfoService = machineInfoService;
    }

    private UserCodeService userCodeService;

    private MachineInfoService machineInfoService;

    @RequestMapping(value = "/userCodes", method = RequestMethod.GET)
    @ResponseBody
    public UserCode generateCode() {
        return userCodeService.generateCode();
    }

    @RequestMapping(value="/userCodes/{code}", method = RequestMethod.GET)
    public List<Machine> getMachines(@PathVariable("code") String code) {
        return machineInfoService.getMachines(code);
    }
}
