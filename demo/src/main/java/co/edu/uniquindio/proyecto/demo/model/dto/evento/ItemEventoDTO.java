package co.edu.uniquindio.proyecto.demo.model.dto.evento;

import java.time.LocalDateTime;

public record ItemEventoDTO(
        String urlImagenPoster,
        String nombre,
        LocalDateTime fecha,
        String ciudad
) {
}
