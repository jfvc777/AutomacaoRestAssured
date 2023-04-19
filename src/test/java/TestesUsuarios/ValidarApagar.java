package TestesUsuarios;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidarApagar {

    com.usuarios.classUsuarios usuarios = new com.usuarios.classUsuarios();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @Order(1)
    public void apagando_usuario() throws IOException {
        usuarios.apagando_usuario("2");
    }


}
