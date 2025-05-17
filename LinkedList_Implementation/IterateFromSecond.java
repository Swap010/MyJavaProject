package LL;

import java.util.LinkedList;
import java.util.ListIterator;

public class IterateFromSecond {
    public static void main(String[] args) {
        LinkedList<String> colors = new LinkedList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");

        ListIterator<String> iterator = colors.listIterator(1); // Starts from index 1 (2nd element)

        System.out.println("Iterating from 2nd element:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
