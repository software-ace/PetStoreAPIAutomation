package api.endpoints;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//this class created to do CRUD operations to Store API
public class StoreEndPoints {
    public static Response createStore(Store payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_user_url);

    }

    public static Response readUser(String userName) {
        return (Response) given().pathParam("username", userName)
                .accept(ContentType.JSON)
                .when()
                .get(Routes.get_user_url)
                .then();
    }

    public static Response updateUser(String userName, Store payload) {
        return (Response) given()
                .pathParam("username", userName)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.update_user_url)
                .then();
    }

    public static Response deleteUser(String userName) {
        return (Response) given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.delete_user_url)
                .then();
    }
}
