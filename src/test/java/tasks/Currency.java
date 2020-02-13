package tasks;

public enum Currency {
    BOLIVIAN_BOLIVIANO("Bolivian Boliviano", "BOB", "2832");

    private final String featureDescription;

    private final String currencyCode;

    private final String coinMarketCapId;

    Currency(String featureDescription, String currencyCode, String coinMarketCapId) {
        this.featureDescription = featureDescription;
        this.currencyCode = currencyCode;
        this.coinMarketCapId = coinMarketCapId;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCoinMarketCapId() {
        return coinMarketCapId;
    }

    public static Currency getByFeatureDescription(String featureDescription) {
        for (Currency currency : Currency.values()) {
            if(currency.featureDescription.equals(featureDescription)){
                return currency;
            }
        }
        return null;
    }
}
