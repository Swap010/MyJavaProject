package ArrayListExample;

import java.util.ArrayList;
import java.util.Collections;

public class SortColors {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Violet");
        colors.add("Indigo");
        colors.add("Blue");
        colors.add("Green");

        Collections.sort(colors);

        System.out.println("Sorted color list: " + colors);
    }
}

