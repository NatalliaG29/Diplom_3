package User;

import PageObject.RegisterPage;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserClient extends RestClient{

    private static final String USER_CREATE = "auth/register";
    private static final String USER_DELETE = "auth/user";

    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_CREATE)
                .then();
    }
    public ValidatableResponse deleteUser(String authToken) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", authToken)
                .when()
                .delete(USER_DELETE)
                .then();
    }

}
