package tasks;

import io.restassured.http.Header;

import static org.apache.http.HttpHeaders.ACCEPT;

public class Endpoints {

    public static final String API_KEY = System.getenv("COINMARKETPCAP_API_KEY");

    //Headers
    public static final Header ACCEPT_JSON = new Header(ACCEPT, "application/json");

    public static final Header X_CMC_PRO_API_KEY = new Header("X-CMC_PRO_API_KEY", API_KEY);

    //Paths
    public static final String CRYPTOCURRENCY_MAP = "/v1/cryptocurrency/map";

    public static final String CRYPTOCURRENCY_INFO = "/v1/cryptocurrency/info";

    public static final String CONVERSION_TOOL = "/v1/tools/price-conversion";
}
