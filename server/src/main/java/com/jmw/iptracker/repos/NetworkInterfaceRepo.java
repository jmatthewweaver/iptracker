package com.jmw.iptracker.repos;

import com.jmw.iptracker.entities.NetworkInterface;
import org.springframework.data.repository.CrudRepository;

public interface NetworkInterfaceRepo
extends CrudRepository<NetworkInterface, Long> {

}
