package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider
    public Object[][] createUserTestData() {
        return new Object[][]{
                // id, username, firstname, lastname, email, password, phone
                {"1010", "testUser1", "Penny", "Angelina", "gva@gmail.com", "Password1", "1234567890"},
                {"1020", "testUser2", "Zoey", "Stone", "asf@gmail.com", "Password2", "1234567890"},
                {"1030", "testUser3", "Sammy", "Morty", "agtn@gmail.com", "Password3", "1234567890"}
        };
    }

    @DataProvider
    public Object[][] deleteUserTestData() {
        return new Object[][]{
                // username
                {"testUser1"}, {"testUser2"}, {"testUser3"}
        };
    }
}
