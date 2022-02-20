package app.enums;

public enum MaterialEnum {
    SILVER("Silver"),
    IRON("Iron"),
    GOLD("Gold");

    private final String material;

    MaterialEnum(String label) {
        this.material = label;
    }

    public static boolean materialExists(String material) {
        for (MaterialEnum e : values()) {
            if (e.material.equals(material)) {
                return true;
            }
        }
        return false;
    }
}
