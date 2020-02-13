package pageobjects;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import net.thucydides.core.pages.PageObject;

import java.util.concurrent.TimeUnit;

@DefaultUrl("https://coinmarketcap.com/")
public class CoinMarketHomePage extends PageObject {

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div[1]/div[3]/nav/nav/a/div")
    WebElementFacade logo;

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[2]/a[2]")
    public static WebElementFacade viewAll;

    public static Target currencyList = Target.the("List of displayed currencies")
            .locatedBy("//*[@id=\"__next\"]/div/div[2]/div[1]/div[2]/div/div[2]/div[3]/div/table/tbody/tr");

    @WhenPageOpens
    public void waitUntilLogoAppears() {
        element(logo).waitUntilPresent().withTimeoutOf(10, TimeUnit.SECONDS);
    }
}
