package ca.cmpt213.ui;

/**
 * Superhuman - class for putting information from server about each superhuman
 */
public class Superhuman {

    static private long total = 0;
    private long sid;
    private String name;
    private double weight;
    private int height;
    private String picture;
    private String category;
    private String ability;

    // Constructor
    public Superhuman(String name, double weight, int height, String picture, String category,
            String ability) {

        this.sid = total++;
        if (name == null || name == "")
            this.name = "N/A";
        else
            this.name = name;
        this.weight = weight;
        this.height = height;
        if (picture == null || picture == "")
            this.picture = "N/A";
        else
            this.picture = picture;
        if (category == null || category == "")
            this.category = "N/A";
        else
            this.category = category;
        if (ability == null || ability == "")
            this.ability = "N/A";
        else
            this.ability = ability;

    }

    public static long totalSuperhumans() {

        return total;

    }

    // Getters & Setters
    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

}
