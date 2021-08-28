package stepDefinitions;

import Utils.HelperMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class openWeatherMapPostStepDefinitions {
    Response response = null;

    @Given("the OpenWeatherMapPost API is available")
    public void theOpenWeatherMapPostAPIIsAvailable() {
        String url = "http://api.openweathermap.org/data/3.0/stations";
        Response res = HelperMethods.sendRequest(null, HelperMethods.POST_REQUEST, url);
        assertThat(res.getStatusCode()).as("API unavailable").isNotEqualTo(500);
    }

    @When("I attempt to register a station with a value of api key as null")
    public void iAttemptToRegisterAStationWithAValueOfApiKeyAsNull() {
        response = HelperMethods.postResponse("DEMO_TEST001", "Team Demo Test Station 00", 33.33, -122.43, 222, null);
    }

    @Then("the request response has error response")
    public void theRequestResponseHasErrorResponse() {
        assertThat(response.getStatusCode()).as("Incorrect status code").isEqualTo(401);
        assertThat(response.getBody().asString()).as("Incorrect status message").contains("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.");
    }

    //FIXME data parameterization is a better approach instead of passing the arguments,can be extended with time
    @When("I attempt to register a station with a value of {string}, {string}, {double}, {double}, {int}")
    public void iAttemptToRegisterAStationWithAValueOfDEMO_TESTTeamDemoTestStation(String arg0, String arg1, double arg2, double arg3, int arg4) {
        response = HelperMethods.postResponse(arg0, arg1, arg2, arg3, arg4, "c17f86683d2ecfa5e91115142909dead");
    }

    @Then("the request response has success response")
    public void theRequestResponseHasSuccessResponse() {
        assertThat(response.getStatusCode()).as("Incorrect status code").isEqualTo(201);
    }


}
