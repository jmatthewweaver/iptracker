package com.jmw.iptracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Machine
implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserCode userCode;

    @OneToMany(mappedBy = "id")
    private List<NetworkInterface> networkInterfaces;
}
