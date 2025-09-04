package co.edu.uniquindio.proyecto.demo.services.implementaciones;

import co.edu.uniquindio.proyecto.demo.config.JWTUtils;
import co.edu.uniquindio.proyecto.demo.model.documentos.Cuenta;
import co.edu.uniquindio.proyecto.demo.model.dto.cuenta.CambiarPasswordDTO;
import co.edu.uniquindio.proyecto.demo.model.dto.email.EmailDTO;
import co.edu.uniquindio.proyecto.demo.model.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.demo.model.vo.CodigoValidacion;
import co.edu.uniquindio.proyecto.demo.repository.CuentaRepo;
import co.edu.uniquindio.proyecto.demo.services.interfaces.CuentaServicio;
import co.edu.uniquindio.proyecto.demo.services.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepo cuentaRepo;
    private final EmailServicio emailServicio;
    private final JWTUtils jwtUtils;

    @Override
    // Envía un código al correo para recuperar la contraseña
    public String enviarCodigoRecuperacionPassword(String correo) throws Exception {
        Cuenta cuenta = obtenerEmail(correo);
        String codigoValidacion = generarCodigo();

        cuenta.setCodigoValidacionPassword(new CodigoValidacion(
                LocalDateTime.now(),
                codigoValidacion
        ));

        cuentaRepo.save(cuenta);
        emailServicio.enviarCorreo(new EmailDTO("CODIGO DE RECUPERACION DE CONTRASEÑA", codigoValidacion, correo));

        return "Se ha enviado un correo con el código de recuperación de contraseña";
    }

    @Override
    // Cambia la contraseña si el código es válido y no ha expirado
    public String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        Cuenta cuenta = obtenerEmail(cambiarPasswordDTO.correo());
        CodigoValidacion codigoValidacion = cuenta.getCodigoValidacionPassword();

        if (codigoValidacion.getCodigo().equals(cambiarPasswordDTO.codigoVerificacion())) {
            if (codigoValidacion.getFechaCreacion().plusMinutes(15).isAfter(LocalDateTime.now())) {
                cuenta.setPassword(encriptarPassword(cambiarPasswordDTO.passwordNueva()));
                cuentaRepo.save(cuenta);
            } else {
                throw new Exception("El código ya expiró.");
            }
        } else {
            throw new Exception("El código ingresado no coincide con el enviado al correo.");
        }

        return "Su contraseña ha sido cambiada.";
    }

    // Genera un código aleatorio de 6 caracteres
    private String generarCodigo() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int indice = (int) (caracteres.length() * Math.random());
            codigo.append(caracteres.charAt(indice));
        }
        return codigo.toString();
    }

    // Encripta la contraseña antes de guardarla
    private String encriptarPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    // Busca una cuenta por correo y valida que exista y no esté eliminada
    private Cuenta obtenerEmail(String correo) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepo.buscaremail(correo);

        if (cuentaOptional.isEmpty()) {
            throw new Exception("El correo dado no está registrado.");
        }

        Cuenta cuenta = cuentaOptional.get();

        if (cuenta.getEstado().equals(EstadoCuenta.ELIMINADO)) {
            throw new Exception("La cuenta registrada con el correo " + correo + " está ELIMINADA.");
        }

        return cuenta;
    }

    // Crea un mapa con la información que irá en el token
    private Map<String, Object> construirClaims(Cuenta cuenta) {
        return Map.of(
                "rol", cuenta.getRol(),
                "nombre", cuenta.getUsuario().getNombre(),
                "id", cuenta.getId()
        );
    }
}
