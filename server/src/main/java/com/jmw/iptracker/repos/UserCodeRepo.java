package com.jmw.iptracker.repos;

import com.jmw.iptracker.entities.UserCode;
import org.springframework.data.repository.CrudRepository;

public interface UserCodeRepo
        extends CrudRepository<UserCode, Long> {

    UserCode findOneByCode(String code);

}
