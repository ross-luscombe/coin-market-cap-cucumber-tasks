package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import pageobjects.CoinMarketHomePage;

public class RetrieveAllCurrencies implements Task {

    @Step("{0} retrieve all cryptocurrencies")
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Click.on(CoinMarketHomePage.viewAll));
    }
}
