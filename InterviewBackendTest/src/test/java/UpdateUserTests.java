import models.CreateUserModel;
import org.junit.jupiter.api.Test;
import services.GoRestService;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UpdateUserTests {

    @Test
    public void Users_UpdateUser_Success() {
        // create user first
        CreateUserModel createUserModel = new CreateUserModel(
                "QA Update Success",
                "Male",
                "update_" + System.currentTimeMillis() + "@test.com",
                "Active"
        );

        int userId = GoRestService.createUser(createUserModel)
                .then().statusCode(SC_CREATED)
                .extract().path("data.id");

        // update user
        CreateUserModel updateUserModel = new CreateUserModel(
                "Updated QA User",
                "Male",
                "updated_" + System.currentTimeMillis() + "@test.com",
                "Inactive"
        );

        GoRestService.updateUser(userId, updateUserModel)
                .then()
                .statusCode(SC_OK)
                .body("data.id", notNullValue())
                .body("data.name", equalTo(updateUserModel.getName()))
                .body("data.email", equalTo(updateUserModel.getEmail()))
                .body("data.status", equalTo(updateUserModel.getStatus().toLowerCase())); // lowercase fix
    }

    @Test
    public void Users_UpdateUser_InvalidEmail_Fails() {
        // create user first
        CreateUserModel createUserModel = new CreateUserModel(
                "QA Update Invalid Email",
                "Male",
                "valid_" + System.currentTimeMillis() + "@test.com",
                "Active"
        );

        int userId = GoRestService.createUser(createUserModel)
                .then().statusCode(SC_CREATED)
                .extract().path("data.id");

        // update with invalid email
        CreateUserModel invalidUpdate = new CreateUserModel(
                "QA Invalid Update",
                "Male",
                "invalidEmail",
                "Active"
        );

        GoRestService.updateUser(userId, invalidUpdate)
                .then()
                .statusCode(422);
    }

    @Test
    public void Users_UpdateUser_NonExistentUser_Fails() {
        CreateUserModel updateUser = new CreateUserModel(
                "QA NonExistent User",
                "Female",
                "qa" + System.currentTimeMillis() + "@mail.com",
                "Active"
        );

        GoRestService.updateUser(9999999, updateUser)
                .then()
                .statusCode(SC_NOT_FOUND);
    }
}