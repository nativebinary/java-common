package models;


import siena.NotNull;

public class Notice extends ModelAutoIncrement {

    @NotNull
    public String ticker;

    @NotNull
    public String contentTitle;

    @NotNull
    public String contentText;

    @NotNull
    public String url;
}
