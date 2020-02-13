package tasks;

import models.Platform;

public enum Cryptocurrency {
    ETHEREUM("Ethereum", 1027, "ETH",
            "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png", "2015-08-07T00:00:00.000Z",
            null, "https://github.com/ethereum/wiki/wiki/White-Paper");

    private final String featureDescription;
    private final int id;
    private final String symbol;
    private final String logo;
    private final String dateAdded;
    private final Platform platform;
    private final String technicalDoc;

    Cryptocurrency(String featureDescription, int id, String symbol, String logo, String dateAdded, Platform platform, String technicalDoc) {
        this.featureDescription = featureDescription;
        this.id = id;
        this.symbol = symbol;
        this.logo = logo;
        this.dateAdded = dateAdded;
        this.platform = platform;
        this.technicalDoc = technicalDoc;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getLogo() {
        return logo;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public Platform getPlatform() {
        return platform;
    }

    public String getTechnicalDoc() {
        return technicalDoc;
    }

    public static Cryptocurrency getByFeatureDescription(String featureDescription) {
        for (Cryptocurrency currency : Cryptocurrency.values()) {
            if (currency.featureDescription.equals(featureDescription)) {
                return currency;
            }
        }
        return null;
    }
}
