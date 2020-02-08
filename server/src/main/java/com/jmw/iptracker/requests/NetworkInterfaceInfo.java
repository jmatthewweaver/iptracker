package com.jmw.iptracker.requests;

import lombok.Data;

import java.util.List;

@Data
public class NetworkInterfaceInfo {

    private String name;

    private String description;

    private Boolean loopback;

    private Boolean primaryInterface;

    private List<InterfaceAddressInfo> interfaceAddresses;
}
