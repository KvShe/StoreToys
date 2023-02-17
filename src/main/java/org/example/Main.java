package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    static final String SRC = "src/main/resources/src.txt";
    static final String DST = "src/main/resources/dst.txt";

    public static void main(String[] args) {
        try {
            List<Toy> toys = createToys(20);
            new Lotto(toys, 10).run(SRC, DST);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    static private List<Toy> createToys(int amount) throws IOException {
        int maxChance = 9;
        for (int i = 0; i < amount; i++) {
            new Toy(i, "Name" + i, new Random().nextInt(maxChance)).writeInFile(SRC, StandardOpenOption.APPEND);
        }

        List<String> reader = Files.readAllLines(Path.of(SRC));
        List<Toy> toys = new LinkedList<>();
        for (String s : reader) {
            int id = Integer.parseInt(s.split(" ")[0]);
            String name = s.split(" ")[1];
            int lossRate = Integer.parseInt(s.split(" ")[2]);
            toys.add(new Toy(id, name, lossRate));
        }
        return toys;
    }
}
