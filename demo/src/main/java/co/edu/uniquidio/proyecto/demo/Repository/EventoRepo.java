package co.edu.uniquindio.proyecto.demo.repository;


import co.edu.uniquindio.proyecto.demo.model.documentos.Cuenta;
import co.edu.uniquindio.proyecto.demo.model.documentos.Evento;
import co.edu.uniquindio.proyecto.demo.model.enums.TipoEvento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepo extends MongoRepository<Evento, String> {

    @Query("{nombre : ?0, fechaEvento: ?1, ciudad :  ?2}")
    Optional<Evento> buscarEvento(String nombreEvento, LocalDateTime fechaEvento, String ciudad);

    @Query("{nombre : ?0, tipo: ?1, ciudad :  ?2}")
    List<Evento> filtrarEventos(String nombreEvento, TipoEvento tipo, String ciudad);
}