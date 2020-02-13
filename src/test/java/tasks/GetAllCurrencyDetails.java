package tasks;

import models.CurrencyData;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static tasks.ActorMemory.ALL_CURRENCY_DETAILS;
import static tasks.Endpoints.*;

public class GetAllCurrencyDetails implements Task {

    public GetAllCurrencyDetails() {
    }

    public static GetAllCurrencyDetails now() {
        return new GetAllCurrencyDetails();
    }

    @Override
    @Step("{0} gets the details for all currencies")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Get.resource(CRYPTOCURRENCY_MAP)
                .with(request -> request
                        .header(ACCEPT_JSON)
                        .header(X_CMC_PRO_API_KEY)));

        if (SerenityRest.lastResponse().statusCode() == SC_OK) {
            List<CurrencyData> currencies = SerenityRest.lastResponse()
                    .jsonPath()
                    .getList("data", CurrencyData.class);
            theActor.remember(ALL_CURRENCY_DETAILS.name(), currencies);
        }
    }
}