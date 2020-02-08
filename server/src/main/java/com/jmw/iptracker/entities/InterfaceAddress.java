package com.jmw.iptracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class InterfaceAddress
implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NetworkInterface networkInterface;

    private String hostname;

    private String address;
}
