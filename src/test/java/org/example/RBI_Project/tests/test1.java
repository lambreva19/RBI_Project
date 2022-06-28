package org.example.RBI_Project.tests;
import org.example.RBI_Project.steps.apiSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)

public class test1 {

    int randomNumber = (int)Math.floor(Math.random()*(50000-100000+1)+50000);
    String query = "SELECT * FROM posts";

    @Steps
    apiSteps apiSteps;

    @Test
    @Title("Т1_PostsApiPathChange_ValidRequestForApiPathChange_CorrectDataIsReturned")
    public void ValidRequestForApiPathChange() {
        apiSteps.sendRequest("https://jsonplaceholder.typicode.com/posts");
        apiSteps.verifyStatusCode(200);
        apiSteps.verifyDataIsReturned();
    }

    @Test
    @Title("T1_PostsApiPathChange_ValidRequestForOldApiBeforeChange_StatusCodeNotFoundIsReturned")
    public void ValidRequestForOldApiBeforeChange() {
        apiSteps.sendRequest("https://jsonplaceholder.typicode.com/post");
        apiSteps.verifyStatusCode(404);
    }

    @Test
    @Title("T1_PostsApiPathChange_ValidRequestForApiPathChangeForIdRequest_CorrectDataIsReturnedForOnlyOneObject")
    public void ValidRequestForApiPathChangeForIdRequest() {
        apiSteps.sendRequest("https://jsonplaceholder.typicode.com/posts/1");
        apiSteps.verifyStatusCode(200);
        apiSteps.verifyId(1);
        apiSteps.verifyUserId(1);
        apiSteps.verifyTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");

    }

    @Test
    @Title("T3_PostsApiPathChange_ValidRequestForChangedApiWithSpacialCharacters_AvailableDataIsReturned")
    public void ValidRequestForChangedApiWithSpacialCharacters() {
        apiSteps.sendRequest("https://jsonplaceholder.typicode.com/posts/#");
        apiSteps.verifyStatusCode(200);
        apiSteps.verifyDataIsReturned();
    }

    @Test
    @Title("T4_PostsApiPathChange_ValidRequestForChangedApiWithNonExistingId_StatusCodeNotFoundIsReturned")
    public void ValidRequestForChangedApiWithNonExistingId() {
        apiSteps.sendRequest("https://jsonplaceholder.typicode.com/posts/" + randomNumber);
        apiSteps.verifyStatusCode(404);
    }



    @Test
    @Title("T5_PostsApiPathChange_ValidRequestForChangedApiWithQueryAsParameter_StatusCodeNotFoundIsReturned")
    public void ValidRequestForChangedApiWithQueryAsParameter() {
        apiSteps.sendRequest("https://jsonplaceholder.typicode.com/posts/" + query);
        apiSteps.verifyStatusCode(404);

    }

}
