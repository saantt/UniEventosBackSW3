package co.edu.uniquindio.proyecto.demo.controllers;

import co.edu.uniquindio.proyecto.demo.model.dto.autenticacion.MensajeDTO;
import co.edu.uniquindio.proyecto.demo.model.dto.cuenta.*;
import co.edu.uniquindio.proyecto.demo.service.interfaces.CuentaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/general")
public class GeneralControlador {

    private final CuentaServicio cuentaServicio;

    // Edita los datos de una cuenta
    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> editarCuenta(@Valid @RequestBody EditarCuentaDTO cuenta) throws Exception{
        cuentaServicio.editarCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }

    // Marca una cuenta como eliminada
    @PutMapping("/eliminar-cuenta/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String id) throws Exception{
        cuentaServicio.eliminarCuenta(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    // Obtiene la información detallada de una cuenta
    @GetMapping("/obtener-info-cuenta/{id}")
    public ResponseEntity<MensajeDTO<InformacionCuentaDTO>> obtenerInformacionCuenta(@PathVariable String id) throws Exception{
        InformacionCuentaDTO info = cuentaServicio.obtenerInformacionCuenta(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    // Lista todas las cuentas registradas
    @GetMapping("/listar-todo-cuentas")
    public ResponseEntity<MensajeDTO<List<ItemCuentaDTO>>> listarCuentas() throws Exception {
        List<ItemCuentaDTO> lista = cuentaServicio.listarCuentas();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    // Envía un código para recuperar la contraseña
    @GetMapping("/enviar-codigo-password/{correo}")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoRecuperacionPassword(@PathVariable String correo) throws Exception {
        cuentaServicio.enviarCodigoRecuperacionPassword(correo);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Se ha enviado un correo con el código de recuperación de contraseña"));
    }

    // Envía un código para activar la cuenta
    @GetMapping("/enviar-codigo-auth/{correo}")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoActivacionCuenta(@PathVariable String correo) throws Exception {
        cuentaServicio.enviarCodigoActivacionCuenta(correo);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Se ha enviado un correo con el código de activación de su cuenta"));
    }

    // Cambia la contraseña de la cuenta
    @PutMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        cuentaServicio.cambiarPassword(cambiarPasswordDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su contraseña ha sido cambiada."));
    }

    // Activa la cuenta con un código de verificación
    @PutMapping("/activar-cuenta")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@RequestBody ActivarCuentaDTO activarCuentaDTO) throws Exception {
        cuentaServicio.activarCuenta(activarCuentaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta activada exitosamente."));
    }
}
