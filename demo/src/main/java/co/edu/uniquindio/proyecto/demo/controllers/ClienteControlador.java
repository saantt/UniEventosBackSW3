package co.edu.uniquindio.proyecto.demo.controllers;

import co.edu.uniquindio.proyecto.demo.model.dto.autenticacion.MensajeDTO;
import co.edu.uniquindio.proyecto.demo.model.dto.cuenta.ActivarCuentaDTO;
import co.edu.uniquindio.proyecto.demo.service.interfaces.CuentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteControlador {

    private final CuentaServicio cuentaServicio;

    @PutMapping("/activar-cuenta")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@RequestBody ActivarCuentaDTO activarCuentaDTO) throws Exception {
        cuentaServicio.activarCuenta(activarCuentaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta activada exitosamente."));
    }
}