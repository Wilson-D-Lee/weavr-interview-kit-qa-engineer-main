package services;

import io.restassured.response.Response;
import models.CreateUserModel;

public class GoRestService extends BaseService {

    public static Response createUser(final CreateUserModel createUserModel) {
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post("/public/v1/users");
    }

    public static Response updateUser(final int userId, final CreateUserModel updateUserModel) {
        return defaultRequestSpecification()
                .body(updateUserModel)
                .when()
                .put("/public/v1/users/" + userId);
    }
}