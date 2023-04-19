package TestesUsuarios;


import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidarCadastro {
    com.usuarios.classUsuarios usuarios = new com.usuarios.classUsuarios();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @Order(1)
    public void cadastrando_usuario() throws IOException {
        usuarios.cadastrando_usuario("Conejone","Pets");
    }

    @Test
    @Order(2)
    public void cadastrando_acesso() throws IOException {
        usuarios.cadastrando_acesso("tracey.ramos@reqres.in","pistolo");
    }

    @Test
    @Order(3)
    public void cadastrando_acesso_user_nao_existente() throws IOException {
        usuarios.cadastrando_acesso("tracey.ramos123@reqres.in","pistola");
    }

    @Test
    @Order(4)
    public void confirmando_acesso_user() throws IOException {
        usuarios.confirmando_usuario("tracey.ramos@reqres.in","pistolo");
    }

    @Test
    @Order(5)
    public void confirmando_acesso_nao_user() throws IOException {
        usuarios.confirmando_usuario("tracey.ramoTs@reqres.in","pistolos");
    }

}
