package co.edu.uniquidio.proyecto.demo.modelo.dto.cuenta;

import co.edu.uniquindio.proyecto.modelo.vo.Boleta;

import java.util.List;

public record InformacionCuentaDTO(
        String id,
        String cedula,
        String nombre,
        String telefono,
        String direccion,
        String correo,
        List<Boleta> boletas
) {
}
