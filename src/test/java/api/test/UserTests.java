package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    Faker faker;
    User userPayload;
    String old_email;
    public Logger logger;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();
        // hasCode() is used here so faker can generate different id everytime its called
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 11));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // to initiate logs logger for this class
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testUserPostRequest() {
        logger.info("@@@@@@@@@@@ Creating a user @@@@@@@@@@@@");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("@@@@@@@@@@@ User is Created @@@@@@@@@@@@");
    }

    @Test(priority = 2, dependsOnMethods = "testUserPostRequest")
    public void testUserGetRequest() {
        logger.info("@@@@@@@@@@@ Getting User info @@@@@@@@@@@@");
        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("@@@@@@@@@@@ User info is displayed @@@@@@@@@@@@");
    }

    @Test(priority = 3)
    public void testUserPutRequest() {
        logger.info("@@@@@@@@@@@ Editing User info @@@@@@@@@@@@");
        // initializing the old_email variable to check it later when doing the put request
        old_email = userPayload.getEmail();

        // update user data
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotEquals(old_email, userPayload.getEmail());
        System.out.println("Old Email: " + old_email);
        System.out.println("New Email: " + userPayload.getEmail());
        logger.info("@@@@@@@@@@@ User info is edited @@@@@@@@@@@@");
    }

    @Test(priority = 4, dependsOnMethods = "testUserGetRequest")
    public void testUserDeleteRequest() {
        logger.info("@@@@@@@@@@@ Deleting a User @@@@@@@@@@@@");
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(userPayload.getUsername(), response.jsonPath().getString("message"));
        response.jsonPath().getString("message");
        logger.info("@@@@@@@@@@@ The user associated with the username: " + response.jsonPath().getString("message") + " is deleted successfully @@@@@@@@@@@");
    }
}
