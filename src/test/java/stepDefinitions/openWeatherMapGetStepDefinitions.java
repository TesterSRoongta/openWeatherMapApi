package stepDefinitions;

import Utils.HelperMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class openWeatherMapGetStepDefinitions {


    @When("I search for a station with a value of {string}")
    public void iSearchForAStationWithAValueOf(String stationID) {
        String stationName = HelperMethods.fetchGetResponse(stationID).get("id");
        assertThat(stationName).as("No station found").isNotEmpty();
    }


    //FIXME parameterization can be done to compare with the file instead of verifying with actual values,can be extended with more time
    @Then("the requests response with a value of {string} will contain the correct json data")
    public void theRequestsResponseWithAValueOfFbcCBbFWillContainTheCorrectJsonData(String station) {
        Map<String, String> response = HelperMethods.fetchStationDetails(station);

        assertThat(response).as("external_id is not as expected").containsEntry("external_id", "DEMO_TEST001");
        assertThat(response).as("name is not as expected").containsEntry("name", "Team Demo Test Station 001");
        assertThat(response).as("latitude is not as expected").extracting("latitude").contains(33.33);
        assertThat(response).as("longitude is not as expected").extracting("longitude").contains(-122.43);
        assertThat(response).as("altitude is not as expected").extracting("altitude").contains(222);
        assertThat(response).as("rank is not as expected").extracting("rank").contains(10);
    }
}
