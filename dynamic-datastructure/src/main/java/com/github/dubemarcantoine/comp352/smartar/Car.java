package com.github.dubemarcantoine.comp352.smartar;

import java.util.Date;

public class Car {

    private String immatriculation;
    private Date immatriculationDate;

    public Car(String immatriculation) {
        this.immatriculation = immatriculation;
        this.immatriculationDate = new Date();
    }
}
