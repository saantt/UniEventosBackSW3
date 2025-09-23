package co.edu.uniquindio.proyecto.demo.model.dto.evento;

import co.edu.uniquindio.proyecto.demo.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.demo.model.enums.TipoEvento;
import co.edu.uniquindio.proyecto.demo.model.vo.Localidad;
import lombok.Builder;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public record InformacionEventoDTO(

        String id,
        EstadoEvento estado,
        String nombre,
        String descripcion,
        TipoEvento tipo,
        LocalDateTime fechaEvento,
        String ciudad,
        String imagenPortada,
        String imagenLocalidades,
        List<Localidad> localidades
) {

}