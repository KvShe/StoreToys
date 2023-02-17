package org.example;

import java.io.IOException;

public interface Playable {
    void run(String src, String dst) throws IOException;
}
