package stepdefinitions;

import cucumber.api.java.Before;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;

public class Hooks {

    @Before("@web")
    public void set_the_stage_for_a_web_test() {
        setTheStage(new OnlineCast());
    }

    @Before("@api")
    public void set_the_stage_for_an_api_test() {
        setTheStage(Cast.whereEveryoneCan(CallAnApi.at("https://pro-api.coinmarketcap.com")));
    }
}
