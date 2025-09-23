package co.edu.uniquindio.proyecto.demo.model.dto.evento;

import co.edu.uniquindio.proyecto.demo.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.demo.model.enums.TipoEvento;
import co.edu.uniquindio.proyecto.demo.model.vo.Localidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditarEventoDTO(

        @NotBlank String imagenPortada,
        @NotNull EstadoEvento estado,
        @NotBlank String descripcion,
        @NotBlank String imagenLocalidades,
        @NotNull LocalDateTime fechaEvento,
        @NotBlank String id

) {
}