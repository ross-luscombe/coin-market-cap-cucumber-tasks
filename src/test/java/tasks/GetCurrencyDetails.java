package tasks;

import models.CurrencyData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.apache.http.HttpStatus.SC_OK;
import static tasks.ActorMemory.ALL_CURRENCY_DETAILS;
import static tasks.ActorMemory.CURRENCY_DETAIL;

public class GetCurrencyDetails implements Task {

    private final String currencyDescription;

    public GetCurrencyDetails(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }

    public static GetCurrencyDetails forTheCurrencyNamed(String currencyDescription) {
        return instrumented(GetCurrencyDetails.class, currencyDescription);
    }

    @Override
    @Step("{0} gets the details for the currency #currencyDescription")
    public <T extends Actor> void performAs(T theActor) {

        List<CurrencyData> currencies = theActor.recall("currencyDetails");

        if (currencies == null) {
            theActor.attemptsTo(GetAllCurrencyDetails.now());
            if(lastResponse().statusCode() == SC_OK) {
                currencies = theActor.recall(ALL_CURRENCY_DETAILS.name());
            } else {
                Assert.fail("Unable to retrieve currency details, due to HTTPError : " + lastResponse().getStatusCode());
            }
        }
        for (CurrencyData currency : currencies) {
            if (currency.getName().equals(currencyDescription)) {
                theActor.remember(CURRENCY_DETAIL.name(), currency);
                break;
            }

        }
    }
}
