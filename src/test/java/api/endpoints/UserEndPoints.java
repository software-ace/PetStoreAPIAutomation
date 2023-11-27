package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//this class created to do CRUD operations to the User API
public class UserEndPoints {
    public static Response createUser(User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_user_url);

    }

    public static Response readUser(String userName) {
        return given().pathParam("username", userName)
                .accept(ContentType.JSON)
                .when()
                .get(Routes.get_user_url);
    }

    public static Response updateUser(String userName, User payload) {
        return given()
                .pathParam("username", userName)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.update_user_url);
    }

    public static Response deleteUser(String userName) {
        return given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.delete_user_url);
    }
}
