package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Getter
@AllArgsConstructor
public class Lotto implements Playable {
    private List<Toy> toys;
    private int numberOfGames;

    @Override
    public void run(String src, String dst) {
        for (int i = 0; i < this.numberOfGames; i++) {
            lotto(src, dst);
        }
    }

    private void lotto(String src, String dst) {
        if (this.toys.isEmpty()) return;

        List<Toy> lottoList = new LinkedList<>();
        for (Toy toy : this.toys) {
            for (int i = 0; i < toy.getDropFrequency() + 1; i++) {
                lottoList.add(toy);
            }
        }

        findPrize(src, dst, lottoList);
        if (this.toys.isEmpty()) return;

        this.toys.forEach(toy -> toy.writeInFile(src, StandardOpenOption.APPEND));
    }

    private void findPrize(String src, String dst, List<Toy> lottoList) {
        int size = lottoList.size();
        int index = new Random().nextInt(size);

        Toy prize = lottoList.get(index);
        prize.writeInFile(dst, StandardOpenOption.APPEND);
        this.toys.remove(prize);
        clearFile(src);
    }

    private void clearFile(String src) {
        try {
            Files.write(Paths.get(src), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
