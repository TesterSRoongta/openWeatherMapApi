package stepDefinitions;


import Utils.HelperMethods;

public class commonStepDefinitions {

    @io.cucumber.java.en.Given("^the OpenWeatherMap API is available$")
    public void theOpenWeatherMapAPIIsAvailable() {
        String url = "http://api.openweathermap.org/data/3.0/stations";
        HelperMethods.sendRequest(null,HelperMethods.GET_REQUEST, url).then().statusCode(200);
    }
}
