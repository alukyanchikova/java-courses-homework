package ru.stqa.jchw.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collection {
    public static void main (String[] args) {
        String [] langs = {"Java", "C#", "Pytion", "PHP"};

        List<String> languages = Arrays.asList("Java", "C#", "Pytion", "PHP");

        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}

