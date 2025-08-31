package co.edu.uniquindio.proyecto.demo.services.implementaciones;
import co.edu.uniquindio.proyecto.demo.model.dto.email.EmailDTO;
import co.edu.uniquindio.proyecto.demo.services.interfaces.EmailServicio;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServicioImpl implements EmailServicio {

    @Override
    @Async
    public void enviarCorreo(EmailDTO emailDTO) throws Exception {

    }

    @Override
    public void enviarCorreoHtml(EmailDTO emailDTO) throws Exception {
        // Implementación pendiente
    }

    
}