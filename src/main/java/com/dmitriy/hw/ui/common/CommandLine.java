package com.dmitriy.hw.ui.common;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CommandLine implements Closeable {
    private final Scanner scanner;

    public CommandLine() {
        scanner = new Scanner(System.in);
    }

    public <T> T choose(List<T> items) {
        IntStream.range(0, items.size()).forEach(idx -> System.out.printf("%d: %s\n", idx, items.get(idx)));
        int index = getValidNumber();
        try {
            return items.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect index. Please try again.");
            return choose(items);
        }
    }

    private int getValidNumber() {
        try {
            String value = scanner.nextLine();
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect number. Please try again.");
            return getValidNumber();
        }
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }
}
