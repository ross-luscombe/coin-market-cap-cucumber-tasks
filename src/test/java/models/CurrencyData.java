package models;

public class CurrencyData {

    private String id;
    private String name;
    private String symbol;
    private String slug;
    private int is_active;
    private int rank;
    private String first_historical_data;
    private String last_historical_data;
    private Platform platform;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSlug() {
        return slug;
    }

    public int getIs_active() {
        return is_active;
    }

    public int getRank() {
        return rank;
    }

    public String getFirst_historical_data() {
        return first_historical_data;
    }

    public String getLast_historical_data() {
        return last_historical_data;
    }

    public Platform getPlatform() {
        return platform;
    }

    @Override
    public String toString() {
        return "CurrencyData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", slug='" + slug + '\'' +
                ", is_active=" + is_active +
                ", rank=" + rank +
                ", first_historical_data='" + first_historical_data + '\'' +
                ", last_historical_data='" + last_historical_data + '\'' +
                ", platform=" + platform +
                '}';
    }
}
