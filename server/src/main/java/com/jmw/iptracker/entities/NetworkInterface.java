package com.jmw.iptracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class NetworkInterface
implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Machine machine;

    @OneToMany(mappedBy = "id")
    private List<InterfaceAddress> interfaceAddresses;

    private String name;

    private String description;

    private Boolean loopback;

    private Boolean primaryInterface;
}
