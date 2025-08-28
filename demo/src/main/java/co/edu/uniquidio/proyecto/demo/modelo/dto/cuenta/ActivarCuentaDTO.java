package co.edu.uniquidio.proyecto.demo.modelo.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActivarCuentaDTO(
        @NotBlank(message = "El token de activación es obligatorio.")
        String token,

        @NotBlank(message = "El correo electrónico es obligatorio.")
        @Email(message = "El correo electrónico debe tener un formato válido.")
        String email

) {
}
