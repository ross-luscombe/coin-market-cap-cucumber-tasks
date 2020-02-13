package tasks;

import models.CurrencyData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static tasks.ActorMemory.CURRENCY_DETAIL;
import static tasks.Currency.getByFeatureDescription;
import static tasks.Endpoints.*;


public class ConvertCurrency implements Task {

    private final String startingCurrency;

    private final String targetCurrency;

    public ConvertCurrency(String startingCurrency, String targetCurrency){
        this.startingCurrency = startingCurrency;
        this.targetCurrency = targetCurrency;
    }

    public static ConvertCurrency fromAndTo(String startingCurrency, String targetCurrency) {
        return instrumented(ConvertCurrency.class, startingCurrency, targetCurrency);
    }

    @Override
    @Step("{0} converts #startingCurrency to #targetCurrency")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(GetCurrencyDetails.forTheCurrencyNamed(startingCurrency));
        CurrencyData startingCurrency = theActor.recall(CURRENCY_DETAIL.name());
        String targetCurrencyId = getByFeatureDescription(targetCurrency).getCoinMarketCapId();

        theActor.attemptsTo(Get.resource(CONVERSION_TOOL)
                .with(request -> request
                        .header(ACCEPT_JSON)
                        .header(X_CMC_PRO_API_KEY)
                        .queryParam("id", startingCurrency.getId())
                        .queryParam("amount", 1)
                        .queryParam("convert_id", targetCurrencyId)));
    }
}
