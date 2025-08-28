package co.edu.uniquidio.proyecto.demo.modelo.dto.autenticacion;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}

