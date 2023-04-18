package TestesUsuarios;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidarConsulta {

    com.usuarios.classUsuarios usuarios = new com.usuarios.classUsuarios();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @Order(1)
    public void consultando_listado_geral_usuarios() throws IOException {
            usuarios.consulta_usuario_global("1");
    }

    @Test
    @Order(2)
    public void consultando_por_usuario() throws IOException {
        usuarios.consulta_por_usuario("2");
    }

    @Test
    @Order(3)
    public void consultando_por_usuario_inexistente() throws IOException {
        usuarios.consulta_por_usuario("222");
    }

    @Test
    @Order(4)
    public void consultando_listado_geral_resource() throws IOException {
        usuarios.consulta_geral_resource();
    }

    @Test
    @Order(5)
    public void consultando_resource_por_usuario() throws IOException {
       usuarios.consulta_resource_usuario("1");
    }

    @Test
    @Order(6)
    public void consultando_resource_por_usuario_inexistente() throws IOException {
        usuarios.consulta_resource_usuario("20");
    }


}

