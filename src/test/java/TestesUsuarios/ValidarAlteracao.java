package TestesUsuarios;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidarAlteracao {

    com.usuarios.classUsuarios usuarios = new com.usuarios.classUsuarios();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @Order(1)
    public void alterando_usuario() throws IOException {
        usuarios.alterando_usuario("2","morpheus","Pets");
    }


}
