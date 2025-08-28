package co.edu.uniquidio.proyecto.demo.modelo.dto.cuenta;

public record CambiarPasswordDTO(

        String correo,
        String codigoVerificacion,
        String passwordNueva
) {
}
