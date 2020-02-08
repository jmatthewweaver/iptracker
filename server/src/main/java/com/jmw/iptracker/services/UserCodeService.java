package com.jmw.iptracker.services;

import com.jmw.iptracker.entities.UserCode;
import com.jmw.iptracker.repos.UserCodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class UserCodeService {

    public UserCodeService(UserCodeRepo userCodeRepo) {
        this.userCodeRepo = userCodeRepo;
    }

    private UserCodeRepo userCodeRepo;

    public UserCode generateCode() {
        Random r = new Random();
        UserCode userCode = new UserCode();

        StringBuilder codeBuilder = new StringBuilder();

        String[] options = new String[] {
                "0","1","2","3","4","5","6","7","8","9",
                "A","C","D","E","F","H","J","K","M","N",
                "P","Q","R","S","T","U","W","X","Y","Z"
        };

        for(int i = 0; i < 6; i++) {
            codeBuilder.append(options[r.nextInt(options.length)]);
        }

//        userCode.setCode(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));
        userCode.setCode(codeBuilder.toString());
        userCodeRepo.save(userCode);
        return userCode;
    }
}
