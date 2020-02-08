package com.jmw.iptracker.services;

import com.jmw.iptracker.entities.InterfaceAddress;
import com.jmw.iptracker.entities.Machine;
import com.jmw.iptracker.entities.NetworkInterface;
import com.jmw.iptracker.entities.UserCode;
import com.jmw.iptracker.repos.InterfaceAddressRepo;
import com.jmw.iptracker.repos.MachineRepo;
import com.jmw.iptracker.repos.NetworkInterfaceRepo;
import com.jmw.iptracker.repos.UserCodeRepo;
import com.jmw.iptracker.requests.InterfaceAddressInfo;
import com.jmw.iptracker.requests.MachineInfo;
import com.jmw.iptracker.requests.NetworkInterfaceInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineInfoService {

    public MachineInfoService(UserCodeRepo userCodeRepo,
                              MachineRepo machineRepo,
                              NetworkInterfaceRepo networkInterfaceRepo,
                              InterfaceAddressRepo interfaceAddressRepo) {
        this.userCodeRepo = userCodeRepo;
        this.machineRepo = machineRepo;
        this.networkInterfaceRepo = networkInterfaceRepo;
        this.interfaceAddressRepo = interfaceAddressRepo;
    }

    private UserCodeRepo userCodeRepo;

    private MachineRepo machineRepo;

    private NetworkInterfaceRepo networkInterfaceRepo;

    private InterfaceAddressRepo interfaceAddressRepo;

    public List<Machine> getMachines(String code) {
        return machineRepo.findAllByUserCode_Code(code);
    }

    public void updateInfo(MachineInfo machineInfo) {
        UserCode userCode = userCodeRepo.findOneByCode(machineInfo.getCode());

        List<Machine> machineList = machineRepo.findAllByUserCode_Code(machineInfo.getCode());
        for(Machine machine: machineList) {
            boolean match = false;

            for(NetworkInterface networkInterface: machine.getNetworkInterfaces()) {
                if(networkInterface.getPrimaryInterface() != null &&
                        networkInterface.getPrimaryInterface()) {
                    for(InterfaceAddress address: networkInterface.getInterfaceAddresses()) {
                        if(machineInfo.getHostname().equals(address.getHostname())) {
                            // This is the machine we want to update

                            match = true;
                            break;
                        }
                    }
                }
            }

            if(match) {
                updateMachineInfo(machine, machineInfo);
                machineRepo.save(machine);
                return;
            }
        }

        Machine machine = new Machine();
        machine.setUserCode(userCode);
        machineRepo.save(machine);
        updateMachineInfo(machine, machineInfo);
        machineRepo.save(machine);
    }

    private void updateMachineInfo(Machine machine, MachineInfo machineInfo) {
        if(machine.getNetworkInterfaces() == null) {
            machine.setNetworkInterfaces(new ArrayList<>());
        }
        machine.getNetworkInterfaces().clear();
        for(NetworkInterfaceInfo info: machineInfo.getNetworkInterfaces()) {
            NetworkInterface itf = new NetworkInterface();
            itf.setInterfaceAddresses(new ArrayList<>());
            itf.setName(info.getName());
            itf.setDescription(info.getDescription());
            itf.setLoopback(info.getLoopback());
            itf.setPrimaryInterface(info.getPrimaryInterface());
            itf.setMachine(machine);
            machine.getNetworkInterfaces().add(itf);
            networkInterfaceRepo.save(itf);

            for(InterfaceAddressInfo addressInfo: info.getInterfaceAddresses()) {
                InterfaceAddress address = new InterfaceAddress();
                address.setAddress(addressInfo.getAddress());
                address.setHostname(addressInfo.getHostname());
                address.setNetworkInterface(itf);
                itf.getInterfaceAddresses().add(address);
                interfaceAddressRepo.save(address);
            }

        }

    }
}
