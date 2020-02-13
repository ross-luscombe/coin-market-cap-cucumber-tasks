package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.ensure.Ensure;
import tasks.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static pageobjects.CoinMarketHomePage.currencyList;

public class StepDefinitions {

    @Given("^that (.*) is viewing the top 100 cyrptocurrencies by market capitalisation$")
    public void that_the_actor_is_viewing_the_top_cyrptocurrencies_by_market_capitalisation(String actorName) {
        theActorCalled(actorName).wasAbleTo(View.theTop100CryptocurrenciesByMarketCapitalisation());
    }

    @When("^(.*) requests to view all cryptocurrencies$")
    public void the_actor_requests_to_view_all_cryptocurrencies(String actorName) {
        theActorCalled(actorName).attemptsTo(new RetrieveAllCurrencies());
    }

    @When("^(.*) converts (.*) into (.*)$")
    public void the_actor_tries_to_convert_a_cryptocurrency_into_a_currency(String actorName, String cryptoCurrency,
                                                                            String currency) {
        theActorCalled(actorName).attemptsTo(ConvertCurrency.fromAndTo(cryptoCurrency, currency));
    }

    @When("^(.*) retrieves the metadata for (.*)$")
    public void the_actor_tries_to_retrieve_the_metadata_for_cryptocurrency(String actorName, String cryptocurrency) {

        theActorCalled(actorName).attemptsTo(GetCryptocurrencyMetaDataFor.cryptoCurrencyId(
                Cryptocurrency.getByFeatureDescription(cryptocurrency).getId()));
    }

    @When("^(.*) requests cryptocurrencies the first (\\d+) cryptocurrencies$")
    public void the_actor_requests_mineable_cryptocurrencies_with_an_id_less_than(String actorName, int idCount) {
        List<Integer> ids = IntStream.rangeClosed(1, idCount)
                .boxed().collect(Collectors.toList());
        theActorCalled(actorName).attemptsTo(GetCryptocurrencyMetaDataFor.cryptoCurrencyIds(
                ids.stream().map(String::valueOf).collect(Collectors.joining(","))));
    }

    @Then("^at least (\\d+) cryptocurrencies will be returned$")
    public void at_least_x_cryptocurrencies_will_be_returned(int expectedCurrencies) {
        theActorInTheSpotlight().attemptsTo(Ensure.thatTheSetOf(currencyList).hasSizeGreaterThanOrEqualTo(expectedCurrencies));
    }

    @Then("^the (?:.*) will be converted into (?:.*)$")
    public void the_cryptocurrency_will_be_successfully_converted_into_currency() {
        theActorInTheSpotlight().should(seeThatResponse(response -> response.statusCode(SC_OK)));
    }

    @Then("^the metadata for (.*) will be successfully returned$")
    public void the_metadata_for_cryptocurrency_will_be_successfully_returned(String cryptocurrencyDescription) {

        Cryptocurrency cryptocurrency = Cryptocurrency.getByFeatureDescription(cryptocurrencyDescription);
        String id = String.valueOf(cryptocurrency.getId());

        theActorInTheSpotlight().should(seeThatResponse(response -> response
                .statusCode(SC_OK)
                .body(String.format("data.%s.logo", id), equalTo(cryptocurrency.getLogo()))
                .body(String.format("data.%s.urls.technical_doc", id), hasItem(cryptocurrency.getTechnicalDoc()))
                .body(String.format("data.%s.symbol", id), equalTo(cryptocurrency.getSymbol()))
                .body(String.format("data.%s.date_added", id), equalTo(cryptocurrency.getDateAdded()))
                .body(String.format("data.%s.platform", id), equalTo(cryptocurrency.getPlatform()))
                .body(String.format("data.%s.tags", id), hasItem("mineable"))));
    }

    @Then("^(.*) will see (\\d+) are mineable$")
    public void he_will_see_are_mineable(String actorName, int expectedMineableCryptocurrencies) {
        theActorCalled(actorName).should(seeThatResponse(response ->
                response.statusCode(SC_OK)
        ));
        int hasMineableTagCount = 0;
        //TODO: Update step def to take in number of currencies to check rather than hard code it e.g. 10
        //For each currency
        for (int i = 1; i <= 10; ++i) {
            //Extract tags
            List<String> tags = SerenityRest.lastResponse().jsonPath()
                    .getList("data." + i + ".tags", String.class);
            //Check if tags includes mineable
            if (tags.contains("mineable")) {
                hasMineableTagCount = hasMineableTagCount + 1;
            }
        }
        assertThat(hasMineableTagCount, equalTo(expectedMineableCryptocurrencies));
    }
}