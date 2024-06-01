package Main;

import io.restassured.*;

public class Api {
    private static final String BASE_URI = "https://api-homologacao.getnet.com.br/";
    private static final String CLIENT_ID = "67823c6d-58de-494f-96d9-86a4c22682cb";
    private static final String CLIENT_SECRET = "c2d6a06f-5f31-448b-9079-7e170e8536e4";

    public static void main(String[] args) {
        String accessToken = getAccessToken();

        if (accessToken != null) {
            testTokenization(accessToken);
        } else {
            System.out.println("Failed to get access token.");
        }
    }

    private static String getAccessToken() {
        Response response = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(CLIENT_ID, CLIENT_SECRET)
                .contentType(ContentType.URLENC)
                .formParam("grant_type", "client_credentials")
                .post(BASE_URI + "auth/oauth/v2/token");

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("access_token");
        } else {
            System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody().asString());
            return null;
        }
    }

    private static void testTokenization(String accessToken) {
        class TokenizationRequest {
            public String card_number;
            public String customer_id;

            public TokenizationRequest(String card_number, String customer_id) {
                this.card_number = card_number;
                this.customer_id = customer_id;
            }
        }

        TokenizationRequest requestBody = new TokenizationRequest("1234123412341234", "customer_123");

        Response response = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(BASE_URI + "v1/tokens/card");

        if (response.getStatusCode() == 201) {
            System.out.println("Tokenization successful!");
        } else {
            System.out.println(
                    "Tokenization failed: " + response.getStatusCode() + " - " + response.getBody().asString());
        }

        String token = response.jsonPath().getString("number_token");
        if (token != null) {
            System.out.println("Token received: " + token);
        } else {
            System.out.println("Token field missing in response.");
        }
    }
}
