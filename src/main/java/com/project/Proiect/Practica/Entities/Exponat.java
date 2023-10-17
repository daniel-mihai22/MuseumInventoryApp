package com.project.Proiect.Practica.Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;


@Entity
@Table(name = "exponate")
public class Exponat {
    @Id
    @GeneratedValue
    private Long id;

    private UUID cod;

    @Column(length = 64)
    private String codInventar;

    @Column(length = 256)
    private String nume;

    @Column(length = 4000)
    private String descriereScurta;

    @Lob
    private byte[] continut;

    @Column()
    private Timestamp dataInregistrarii;

    @Column(length = 256)
    private String locatie;
    @Column(length = 256)
    private String proprietar;
    @Column(length = 64)
    private String stare;

    public Exponat(String cod) {
        this.cod = UUID.fromString(cod);
    }

    public Exponat() {
        this.cod = UUID.randomUUID();
    }
    public Exponat(String codInventar, String nume, String descriereScurta,
                   byte[] continut, Timestamp dataInregistrarii, String locatie, String proprietar, String stare) {

        this.codInventar = codInventar;
        this.nume = nume;
        this.descriereScurta = descriereScurta;
        this.continut = continut;
        this.dataInregistrarii = dataInregistrarii;
        this.locatie = locatie;
        this.proprietar = proprietar;
        this.stare = stare;
    }



    public Long getId() {
        return id;
    }

    public UUID getCod() {
        return cod;
    }

    public String getCodInventar() {
        return codInventar;
    }

    public void setCodInventar(String codInventar) {
        this.codInventar = codInventar;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriereScurta() {
        return descriereScurta;
    }

    public void setDescriereScurta(String descriereScurta) {
        this.descriereScurta = descriereScurta;
    }

    public byte[] getContinut() {
        return continut;
    }

    public void setContinut(byte[] continut) {
        this.continut = continut;
    }

    public Timestamp getDataInregistrarii() {
        return dataInregistrarii;
    }

    public void setDataInregistrarii(Timestamp dataInregistrarii) {
        this.dataInregistrarii = dataInregistrarii;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getProprietar() {
        return proprietar;
    }

    public void setProprietar(String proprietar) {
        this.proprietar = proprietar;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }
}