package com.project.Proiect.Practica.Repository;

import com.project.Proiect.Practica.Entities.Exponat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface IRepositoryExponat extends JpaRepository<Exponat, Long> {

    @Query("SELECT new map(e.cod as cod, e.codInventar as codInventar, e.nume as nume, " +
            "e.descriereScurta as descriereScurta, e.locatie as locatie, e.dataInregistrarii as dataInregistrarii) " +
            "FROM Exponat e")
    List<Map<String, Exponat>> getAll();

    @Query("SELECT new map(e.cod as cod, e.codInventar as codInventar, e.nume as nume, " +
            "e.descriereScurta as descriereScurta, e.locatie as locatie, e.dataInregistrarii as dataInregistrarii) " +
            "FROM Exponat e WHERE e.nume = :nume")
    List<Map<String, Exponat>> getDescrByName(@Param("nume") String nume);

    @Query("SELECT new map(e.cod as cod, e.codInventar as codInventar, e.nume as nume, " +
            "e.descriereScurta as descriereScurta, e.locatie as locatie, e.dataInregistrarii as dataInregistrarii) " +
            "FROM Exponat e WHERE e.locatie = :locatie")
    List<Map<String, Exponat>> getDescrByLocation(@Param("locatie") String locatie);

    @Query("SELECT e FROM Exponat e WHERE e.cod = :cod")
    Exponat getByCod(@Param("cod") UUID cod);

    @Query("SELECT e FROM Exponat e WHERE e.codInventar = :codInventar")
    Exponat getByInventory(@Param("codInventar") String codInventar);
}

