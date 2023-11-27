package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserDataDrivenTests {
    public Logger logger;

    @BeforeClass
    public void setup() {
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1, dataProvider = "createUserTestData", dataProviderClass = DataProviders.class)
    public void testUserPostRequest(String id, String username, String firstname, String lastname, String email, String password, String phone) {
        logger.info("*********** Creating User from Data Provider **********");
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(id));
        userPayload.setUsername(username);
        userPayload.setFirstname(firstname);
        userPayload.setLastname(lastname);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("*********** User from Data Provider is created **********");
    }

    @Test(priority = 2, dataProvider = "deleteUserTestData", dataProviderClass = DataProviders.class)
    public void testUserDeleteRequest(String username) {
        logger.info("*********** Deleting User from Data Provider **********");
        User userPayload = new User();
        userPayload.setUsername(username);
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(userPayload.getUsername(), response.jsonPath().getString("message"));
        response.jsonPath().getString("message");
        System.out.println("the user associated with the username: " + response.jsonPath().getString("message") + " is deleted successfully");
        logger.info("*********** User from Data Provider is deleted **********");
    }
}
