package ru.xlower.marketaction.model;

/**
 * Created by Dmitry.Shishlo on 21.03.2018.
 */


public class Good extends BaseEntity
{
    public String name;
    public String description;
    public String weight;
    public String cost;
    public String newStr;

    public String markets;
    public String actions;
    public String catalogues;

    public String getId() {
        return this.id;
    }
    public String getContent() {
        return this.id;
    }
}
