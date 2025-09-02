import models.CreateUserModel;
import org.junit.jupiter.api.Test;
import services.GoRestService;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateUserTests {

    @Test
    public void Users_CreateUser_Success() {
        CreateUserModel createUserModel = new CreateUserModel(
                "QA Create Success",
                "Male",
                "qatest_" + System.currentTimeMillis() + "@test.com",
                "Active"
        );

        GoRestService.createUser(createUserModel)
                .then()
                .statusCode(SC_CREATED)
                .body("data.id", notNullValue())
                .body("data.name", equalTo(createUserModel.getName()))
                .body("data.email", equalTo(createUserModel.getEmail()))
                .body("data.status", equalTo(createUserModel.getStatus().toLowerCase()));
    }

    @Test
    public void Users_CreateUser_MissingEmail_Fails() {
        CreateUserModel createUserModel = new CreateUserModel(
                "QA Missing Email",
                "Male",
                "",
                "Active"
        );

        GoRestService.createUser(createUserModel)
                .then()
                .statusCode(422);
    }

    @Test
    public void Users_CreateUser_InvalidEmail_Fails() {
        CreateUserModel createUserModel = new CreateUserModel(
                "QA Invalid Email",
                "Male",
                "invalidEmail",
                "Active"
        );

        GoRestService.createUser(createUserModel)
                .then()
                .statusCode(422);
    }
}