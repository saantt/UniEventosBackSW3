package co.edu.uniquindio.proyecto.demo.model.dto.cuenta;

import co.edu.uniquindio.proyecto.demo.model.vo.Boleta;

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
