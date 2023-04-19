package com.usuarios;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.*;

public class classUsuarios {

    public void consulta_usuario_global(String numPagina) throws IOException {
        usuario_consulta_global(numPagina);
    }

    public void consulta_por_usuario(String user) throws IOException {
        consultaUsuario(user);
    }

    public void consulta_geral_resource() throws IOException {
        consulta_resource_geral();
    }

    public void consulta_resource_usuario(String user) throws IOException {
        consulta_usuario_resource(user);
    }

    public void cadastrando_usuario(String name, String job) throws IOException {
        registrar_usuario(name, job);
    }

    public void cadastrando_acesso(String email, String pass) throws IOException {
        registrar_acesso(email, pass);
    }

    public void delay_usuario(String time) throws IOException {
        time_usuario(time);
    }

    public void confirmando_usuario(String email, String pass) throws IOException {
        confirm_usuario(email, pass);
    }

    public void alterando_usuario(String id, String user, String job) throws IOException {
        altera_usuario(id, user, job);
    }

    public void apagando_usuario(String id) throws IOException {
        apaga_usuario(id);
    }

    private void apaga_usuario(String id) throws IOException {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/" + id)
                .then()
                .statusCode(204)
                .extract().response();
        System.out.println("Foi apagado com sucesso");

    }

    private void altera_usuario(String id, String user, String job) throws IOException {
        String[] listaParametros = {user, job};
        String body = bodyParseUser(listaParametros);

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .put("/users/" + id)
                .then()
                .statusCode(200)
                .extract().response();
        String constante = response.getBody().asString();
        System.out.println("------------------------------------------------");
        System.out.println("Name : " + parsearJsonGeral(constante, "name").replace("\"", ""));
        System.out.println("Job : " + parsearJsonGeral(constante, "job").replace("\"", ""));
        System.out.println("updateAt : " + parsearJsonGeral(constante, "updatedAt").replace("\"", ""));
    }

    private void confirm_usuario(String email, String pass) throws IOException {
        String[] listaParametros = {email, pass};
        String body = bodyParseAcesso(listaParametros);

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().response();
        String constante = response.getBody().asString();
        System.out.println("------------------------------------------------");
        System.out.println("Token : " + parsearJsonGeral(constante, "token").replace("\"", ""));
    }

    private void time_usuario(String time) throws IOException {
        Response response = given()
                .contentType(ContentType.JSON)
                .param("delay", time)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().response();

        String constante = response.getBody().asString();
        List<JsonNode> stringConstante = parsearJsonData1(constante);
        int pagesize = Integer.parseInt(parsearJsonGeral(constante, "per_page"));

        for (int i = 0; i < pagesize; i++) {
            System.out.println("ID : " + String.valueOf(stringConstante.get(0).get(i).get("id")));
            System.out.println("Email : " + String.valueOf(stringConstante.get(0).get(i).get("email")));
            System.out.println("First Name : " + String.valueOf(stringConstante.get(0).get(i).get("first_name")));
            System.out.println("Last Name : " + String.valueOf(stringConstante.get(0).get(i).get("last_name")));
            System.out.println("Avatar : " + String.valueOf(stringConstante.get(0).get(i).get("avatar")));
            System.out.println("---------------------------------------------");
        }
    }

    private void registrar_acesso(String email, String pass) throws IOException {
        String[] listaParametros = {email, pass};
        String body = bodyParseAcesso(listaParametros);

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .extract().response();
        String constante = response.getBody().asString();
        System.out.println("------------------------------------------------");
        System.out.println("ID : " + parsearJsonGeral(constante, "id").replace("\"", ""));
        System.out.println("Token : " + parsearJsonGeral(constante, "token").replace("\"", ""));
    }

    private void registrar_usuario(String name, String job) throws IOException {
        String[] listaParametros = {name, job};
        String body = bodyParseUser(listaParametros);

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().response();

        String constante = response.getBody().asString();
        System.out.println("Name : " + parsearJsonGeral(constante, "name").replace("\"", ""));
        System.out.println("Job : " + parsearJsonGeral(constante, "job").replace("\"", ""));
        System.out.println("ID : " + parsearJsonGeral(constante, "id").replace("\"", ""));
        System.out.println("Created At : " + parsearJsonGeral(constante, "createdAt").replace("\"", ""));
    }

    private void consulta_resource_geral() throws IOException {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .extract().response();

        String constante = response.getBody().asString();
        List<JsonNode> stringConstante = parsearJsonData1(constante);
        int pagesize = Integer.parseInt(parsearJsonGeral(constante, "per_page"));

        for (int i = 0; i < pagesize; i++) {
            System.out.println("ID : " + String.valueOf(stringConstante.get(0).get(i).get("id")));
            System.out.println("Name : " + String.valueOf(stringConstante.get(0).get(i).get("name")));
            System.out.println("Year : " + String.valueOf(stringConstante.get(0).get(i).get("year")));
            System.out.println("Color : " + String.valueOf(stringConstante.get(0).get(i).get("color")));
            System.out.println("Pantone Value : " + String.valueOf(stringConstante.get(0).get(i).get("pantone_value")));
            System.out.println("---------------------------------------------");
        }
    }

    private void consulta_usuario_resource(String user) throws IOException {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/unknown/" + user)
                .then()
                .statusCode(200)
                .extract().response();

        String constante = response.getBody().asString();
        List<JsonNode> stringConstante = parsearJsonData1(constante);
        System.out.println("ID : " + String.valueOf(stringConstante.get(0).get("id")));
        System.out.println("Name : " + String.valueOf(stringConstante.get(0).get("name")));
        System.out.println("Year : " + String.valueOf(stringConstante.get(0).get("year")));
        System.out.println("Color : " + String.valueOf(stringConstante.get(0).get("color")));
        System.out.println("Pantone Value : " + String.valueOf(stringConstante.get(0).get("pantone_value")));
        System.out.println("---------------------------------------------");
    }

    private void usuario_consulta_global(String numPagina) throws IOException {

        Response response = given()
                .contentType(ContentType.JSON)
                .param("page", numPagina)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().response();

        String constante = response.getBody().asString();
        List<JsonNode> stringConstante = parsearJsonData1(constante);
        int pagesize = Integer.parseInt(parsearJsonGeral(constante, "per_page"));

        for (int i = 0; i < pagesize; i++) {
            System.out.println("ID : " + String.valueOf(stringConstante.get(0).get(i).get("id")));
            System.out.println("Email : " + String.valueOf(stringConstante.get(0).get(i).get("email")));
            System.out.println("First Name : " + String.valueOf(stringConstante.get(0).get(i).get("first_name")));
            System.out.println("Last Name : " + String.valueOf(stringConstante.get(0).get(i).get("last_name")));
            System.out.println("Avatar : " + String.valueOf(stringConstante.get(0).get(i).get("avatar")));
            System.out.println("---------------------------------------------");
        }
    }

    private void consultaUsuario(String user) throws IOException {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/" + user)
                .then()
                .statusCode(200)
                .extract().response();

        String constante = response.getBody().asString();
        List<JsonNode> stringConstante = parsearJsonData1(constante);
        System.out.println("ID : " + String.valueOf(stringConstante.get(0).get("id")));
        System.out.println("Email : " + String.valueOf(stringConstante.get(0).get("email")));
        System.out.println("First Name : " + String.valueOf(stringConstante.get(0).get("first_name")));
        System.out.println("Last Name : " + String.valueOf(stringConstante.get(0).get("last_name")));
        System.out.println("Avatar : " + String.valueOf(stringConstante.get(0).get("avatar")));

    }

    private List<JsonNode> parsearJsonData1(String parametro) throws IOException {
        List<JsonNode> stringConstante = nodeParam(parametro).findValues("data");
        return stringConstante;
    }

    private String parsearJsonGeral(String parametro, String cabecalho) throws IOException {
        String nodeParam = String.valueOf(nodeParam(parametro).get(cabecalho));
        return nodeParam;
    }

    private JsonNode nodeParam(String parametro) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser jsonParser = factory.createParser(parametro);
        JsonNode node = mapper.readTree(jsonParser);
        return node;
    }

    private String bodyParseUser(String[] paramentros) {
        String body = "{\n"
                + "\"name\":" + "\"" + paramentros[0].toString() + "\"" + ","
                + "\"job\":" + "\"" + paramentros[1].toString() + "\""
                + "\n}";

        return body;
    }

    private String bodyParseAcesso(String[] paramentros) {
        String body = "{\n"
                + "\"email\":" + "\"" + paramentros[0].toString() + "\"" + ","
                + "\"password\":" + "\"" + paramentros[1].toString() + "\""
                + "\n}";

        return body;
    }


}
