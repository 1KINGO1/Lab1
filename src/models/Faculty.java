package models;

public class Faculty {
    private String name = "Unknown";

    public Faculty(String name) {

        if (name.length() < 3) {
            throw new IllegalArgumentException("Name is too short (min 3 symbols)");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Faculty: " + name;
    }
}
