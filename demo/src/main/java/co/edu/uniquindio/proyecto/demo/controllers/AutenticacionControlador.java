package co.edu.uniquindio.proyecto.demo.controllers;

import co.edu.uniquindio.proyecto.demo.model.dto.autenticacion.MensajeDTO;
import co.edu.uniquindio.proyecto.demo.model.dto.autenticacion.TokenDTO;
import co.edu.uniquindio.proyecto.demo.model.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.proyecto.demo.model.dto.cuenta.LoginDTO;
import co.edu.uniquindio.proyecto.demo.service.interfaces.CuentaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteControlador {

    private final CuentaServicio cuentaServicio;

    // Activa una cuenta usando el código de validación
    @PutMapping("/activar-cuenta")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@RequestBody ActivarCuentaDTO activarCuentaDTO) throws Exception {
        cuentaServicio.activarCuenta(activarCuentaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta activada exitosamente."));
    }
}