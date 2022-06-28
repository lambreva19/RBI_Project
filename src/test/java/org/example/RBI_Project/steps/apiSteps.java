package org.example.RBI_Project.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class apiSteps {

    public Response response;

    @Step("Send valid request for changed API")
    public void sendRequest(String URL) {
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().get(URL);

    }

    @Step("Verify the status code {0}")
    public void verifyStatusCode(int expectedStatusCode) {
        SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
    }
    @Step("Verify data is returned")
    public void verifyDataIsReturned() {
        JsonPath j = new JsonPath(response.asString());
        int s = j.getInt("data.size()");
        assertThat(s).isGreaterThan(0);
    }

    @Step("Verify the id {0}")
        public void verifyId(int expectedId) {
        SerenityRest.restAssuredThat(response -> response.body("id", equalTo(expectedId)));
    }

    @Step("Verify the user id {0}")
    public void verifyUserId(int expectedUserId) {

        SerenityRest.restAssuredThat(response -> response.body("userId", equalTo(expectedUserId)));
    }

    @Step("Verify the user salary {0}")
    public void verifyTitle(String expectedTitle) {
        JSONObject ja = new JSONObject();
        System.out.println( ja);
        SerenityRest.restAssuredThat(response -> response.body("title", equalTo(expectedTitle)));
    }
}
