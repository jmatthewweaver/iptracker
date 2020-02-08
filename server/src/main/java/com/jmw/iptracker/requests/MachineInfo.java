package com.jmw.iptracker.requests;

import com.jmw.iptracker.entities.NetworkInterface;
import lombok.Data;

import java.util.List;

@Data
public class MachineInfo {

    private String code;

    private List<NetworkInterfaceInfo> networkInterfaces;

    public String getHostname() {
        if(networkInterfaces != null) {
            for(NetworkInterfaceInfo networkInterface: networkInterfaces) {
                if(networkInterface.getPrimaryInterface() != null && networkInterface.getPrimaryInterface() &&
                        networkInterface.getInterfaceAddresses() != null) {
                    return networkInterface.getInterfaceAddresses().get(0).getHostname();
                }
            }
        }

        return null;
    }

}
