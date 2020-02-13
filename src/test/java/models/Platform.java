package models;

public class Platform {

    private int id;
    private String name;
    private String symbol;
    private String slug;
    private String token_address;

    public int getId() {
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

    public String getToken_address() {
        return token_address;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", slug='" + slug + '\'' +
                ", token_address='" + token_address + '\'' +
                '}';
    }
}
