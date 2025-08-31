package co.edu.uniquindio.proyecto.demo.services.interfaces;


import co.edu.uniquindio.proyecto.demo.model.dto.email.EmailDTO;

public interface EmailServicio {
    void enviarCorreo(EmailDTO emailDTO) throws Exception;
    void enviarCorreoHtml(EmailDTO emailDTO) throws Exception;
    
    

}
