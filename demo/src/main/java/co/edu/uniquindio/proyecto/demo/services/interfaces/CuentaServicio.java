package co.edu.uniquindio.proyecto.demo.services.interfaces;

import co.edu.uniquindio.proyecto.demo.model.dto.cuenta.CambiarPasswordDTO;
import co.edu.uniquindio.proyecto.demo.model.dto.cuenta.*;


public interface CuentaServicio {

    String enviarCodigoRecuperacionPassword(String correo) throws Exception;

    String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO ) throws Exception;

}