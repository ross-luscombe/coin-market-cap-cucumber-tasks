package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;
import pageobjects.CoinMarketHomePage;

public class View implements Performable {

    public static Performable theTop100CryptocurrenciesByMarketCapitalisation() {
        return new View();
    }

    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(Open.browserOn().the(CoinMarketHomePage.class));
    }
}
