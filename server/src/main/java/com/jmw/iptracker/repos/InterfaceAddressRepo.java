package com.jmw.iptracker.repos;

import com.jmw.iptracker.entities.InterfaceAddress;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceAddressRepo
extends CrudRepository<InterfaceAddress, Long> {
}
