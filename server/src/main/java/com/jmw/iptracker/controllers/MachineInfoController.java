package com.jmw.iptracker.controllers;

import com.jmw.iptracker.requests.MachineInfo;
import com.jmw.iptracker.services.MachineInfoService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MachineInfoController {

    public MachineInfoController(MachineInfoService machineInfoService) {
        this.machineInfoService = machineInfoService;
    }

    private MachineInfoService machineInfoService;

    @RequestMapping(value="/machineInfo", method = RequestMethod.POST)
    public void updateMachineInfo(@RequestBody MachineInfo machineInfo) {
        machineInfoService.updateInfo(machineInfo);
    }
}
