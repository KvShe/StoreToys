package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Getter
@AllArgsConstructor
public class Toy {
    private int id;
    private String name;
    @Setter
    private int dropFrequency;

    public void writeInFile(String path, StandardOpenOption option) {
        try {
            Files.write(Paths.get(path), this.toString().getBytes(), option);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return id + " " + name + " " + dropFrequency + "\n";
    }
}
