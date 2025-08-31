package co.edu.uniquindio.proyecto.demo.model.dto.cuenta;

public record CambiarPasswordDTO(

        String correo,
        String codigoVerificacion,
        String passwordNueva
) {
}
