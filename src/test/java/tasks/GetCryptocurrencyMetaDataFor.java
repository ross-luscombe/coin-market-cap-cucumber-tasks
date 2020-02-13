package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static tasks.Endpoints.*;

public class GetCryptocurrencyMetaDataFor implements Task {

    private final String ids;

    public GetCryptocurrencyMetaDataFor(String ids) {
        this.ids = ids;
    }

    public static GetCryptocurrencyMetaDataFor cryptoCurrencyId(int id) {
        return instrumented(GetCryptocurrencyMetaDataFor.class, String.valueOf(id));
    }

    public static GetCryptocurrencyMetaDataFor cryptoCurrencyIds(String ids) {
        return instrumented(GetCryptocurrencyMetaDataFor.class, ids);
    }

    @Override
    @Step("{0} gets the metadata for the currency #currencyDescription")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Get.resource(CRYPTOCURRENCY_INFO)
                .with(request -> request
                        .header(ACCEPT_JSON)
                        .header(X_CMC_PRO_API_KEY)
                        .queryParam("id", ids)));
    }
}
