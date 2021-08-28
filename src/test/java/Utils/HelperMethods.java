package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import Utils.LoadDataMethods;

import java.util.Map;

public class HelperMethods {

    public static final int GET_REQUEST = 0;
    public static final int POST_REQUEST = 1;
    public static final int PUT_REQUEST = 3;


    private static LoadDataMethods loadDataMethods = new LoadDataMethods();
    public static final String url =loadDataMethods.getBaseUrl();
    public static final String APP_ID = loadDataMethods.getAppId();

    public static Response sendRequest(RequestSpecification request, int requestType, String url) {
        Response response;

        switch (requestType) {
            case HelperMethods.GET_REQUEST:
                if (null == request) {
                    response = RestAssured.given()
                            .params("appid",APP_ID)
                            .get(url);
                } else {
                    response = request.get(url);
                }
                break;
            case HelperMethods.POST_REQUEST:
                if (null == request) {
                    response = RestAssured.given()
                            .params("appid",  APP_ID)
                            .post(url);
                } else {
                    response = request.post(url);
                }
                break;
            case HelperMethods.PUT_REQUEST:
                if (null == request) {
                    response = RestAssured.when().put(url);
                } else {
                    response = request.put(url);
                }
                break;
            default:
                if (null == request) {
                    response = RestAssured.when().post(url);
                } else {
                    response = request.post(url);
                }
                assert request != null;
                response = request.post(url);
                break;
        }
        return response;
    }

    public static Response postResponse(String ExternalId, String name, Double latitude, Double longitude, Integer altitude, String appId) {
        Response res = null;
        String postBody= "{\"external_id\": \""+ExternalId+"\", \"name\": \""+name+"\", \"latitude\": "+latitude+" , \"longitude\": "+longitude+", \"altitude\": "+altitude+"}\"}";

        try {
            res = RestAssured.given()
                    .queryParam("appid", appId)
                    .header("Content-Type", "application/json")
                    .body(postBody)
                    .when()
                    .post(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static JsonPath fetchGetResponse(String station) {
        Response res;
        try {
            res =
                    RestAssured.given()
                            .params("appid",  APP_ID)
                            .when()
                            .get(url + "/" + station);
            JsonPath jp = new JsonPath(res.asString());
            if (jp.get("Errors") != null) {
                throw new RuntimeException("Error response" + jp.prettyPrint());
            } else {
                return jp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public static Map fetchStationDetails(String station) {
        Response res;
        res =
                RestAssured.given()
                        .params("appid", APP_ID)
                        .when()
                        .get(url + "/" + station);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> stationRes = null;
        try {
            stationRes = mapper.readValue(res.asString(), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stationRes;
    }
}


