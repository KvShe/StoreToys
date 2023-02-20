package org.example;

import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    static final String SRC = "src/main/resources/src.txt";
    static final String DST = "src/main/resources/dst.txt";

    public static void main(String[] args) {
        List<Toy> toys = createToys(20);
        new Lotto(toys, 10).run(SRC, DST);
    }

    static private List<Toy> createToys(int amount) {
        int maxChance = 9;
        List<Toy> toys = new LinkedList<>();

        for (int i = 0; i < amount; i++) {
            Toy toy = new Toy(i, "Name" + i, new Random().nextInt(maxChance));
            toy.writeInFile(SRC, StandardOpenOption.APPEND);
            toys.add(toy);
        }
        return toys;
    }
}
