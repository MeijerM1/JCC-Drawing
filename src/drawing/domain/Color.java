package drawing.domain;

/**
 * Created by max1_ on 14/02/2017.
 * A custom color enum to be easily expanded in the future.
 */
public enum Color {
    BLACK,
    WHITE,
    RED,
    BLUE,
    GREEN;

    public static String[] names() {
        Color[] colors = values();
        String[] names = new String[colors.length];

        for (int i = 0; i < colors.length; i++) {
            names[i] = colors[i].name();
        }

        return names;
    }
}
