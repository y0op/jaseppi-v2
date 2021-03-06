package me.sagan.jaseppi;

/**
 * @author cam (sagan/y0op)
 */
public enum Tokens {

    DISCORD_MAIN_TOKEN("DISCORD_MAIN_TOKEN"),
    WEATHER_TOKEN("WEATHER_TOKEN"),
    NASA_TOKEN("NASA_TOKEN"),
    REDDIT("REDDIT_CLIENT_SECRET", "REDDIT_CLIENT_ID", "REDDIT_USERNAME", "REDDIT_PASSWORD")
    ;

    private String[] tokens;

    Tokens(String... tokens) {
        this.tokens = tokens;
    }

    public String[] getTokens() {
        return tokens;
    }

    public String getToken() {
        return System.getenv(this.tokens[0]);
    }

    public String getToken(String environmentVarName) {
        return System.getenv(environmentVarName);
    }
}