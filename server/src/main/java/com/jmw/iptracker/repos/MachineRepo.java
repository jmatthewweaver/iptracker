package com.jmw.iptracker.repos;

import com.jmw.iptracker.entities.Machine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MachineRepo
extends CrudRepository<Machine, Long> {

    public List<Machine> findAllByUserCode_Code(String code);
}
