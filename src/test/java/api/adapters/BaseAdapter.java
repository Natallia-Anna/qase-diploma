package api.adapters;

import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseAdapter {
    private static final String TOKEN_VALUE = "a888efe63e483d852360370015875c4eadb8f23077c47802cab98dd9a1b00234";
    private static final String BASE_URL = "https://api.qase.io/v1";
    private static final String TOKEN_NAME = "Token";
    private static final String CONTENT_TYPE = "content-type";
    private static final String JSON = "application/json";

    protected Gson converter = new Gson();

    public Response get(String url) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .when()
                .get(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    public Response post(String url, String body) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .header(CONTENT_TYPE, JSON)
                .body(body)
                .when()
                .post(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    public Response delete(String url) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .when()
                .delete(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }
}
