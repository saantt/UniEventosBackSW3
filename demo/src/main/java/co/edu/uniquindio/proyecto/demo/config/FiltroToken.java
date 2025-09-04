    package co.edu.uniquindio.proyecto.demo.config;


    import co.edu.uniquindio.proyecto.demo.model.dto.autenticacion.MensajeDTO;

    import co.edu.uniquindio.proyecto.demo.model.enums.Rol;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.ExpiredJwtException;
    import io.jsonwebtoken.Jws;
    import io.jsonwebtoken.MalformedJwtException;
    import io.jsonwebtoken.security.SignatureException;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import lombok.RequiredArgsConstructor;

    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;




    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;
    import java.io.IOException;


   @Component
@RequiredArgsConstructor
public class FiltroToken extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;

    // Se ejecuta en cada petición para validar CORS y el token
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Configura cabeceras para permitir CORS
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            String requestURI = request.getRequestURI();
            String token = getToken(request);
            boolean error = true;

            try {
                // Valida el token según el rol y la ruta
                if (requestURI.startsWith("/api/cliente")) {
                    error = validarToken(token, Rol.CLIENTE);
                } else if (requestURI.startsWith("/api/admin")) {
                    error = validarToken(token, Rol.ADMINISTRADOR);
                } else {
                    error = false;
                }

                // Si el token no es válido se responde con error
                if (error) {
                    crearRespuestaError("No tiene permisos para acceder a este recurso", HttpServletResponse.SC_FORBIDDEN, response);
                }

            } catch (MalformedJwtException | SignatureException e) {
                crearRespuestaError("El token es incorrecto", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);
            } catch (ExpiredJwtException e) {
                crearRespuestaError("El token está vencido", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);
            } catch (Exception e) {
                crearRespuestaError(e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);
            }

            // Si todo está bien, continúa con la petición
            if (!error) {
                filterChain.doFilter(request, response);
            }
        }
    }

    // Extrae el token del encabezado Authorization
    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        return header != null && header.startsWith("Bearer ") ? header.replace("Bearer ", "") : null;
    }

    // Crea y envía una respuesta JSON con un mensaje de error
    private void crearRespuestaError(String mensaje, int codigoError, HttpServletResponse response) throws IOException {
        MensajeDTO<String> dto = new MensajeDTO<>(true, mensaje);
        response.setContentType("application/json");
        response.setStatus(codigoError);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();
    }

    // Verifica que el token sea válido y que tenga el rol correcto
    private boolean validarToken(String token, Rol rol){
        boolean error = true;
        if (token != null) {
            Jws<Claims> jws = jwtUtils.parseJwt(token);
            if (Rol.valueOf(jws.getPayload().get("rol").toString()) == rol) {
                error = false;
            }
        }
        return error;
    }
}

