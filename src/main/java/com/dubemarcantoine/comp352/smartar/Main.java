package com.dubemarcantoine.comp352.smartar;

import com.dubemarcantoine.comp352.smartar.datastructure.implementation.SmartAR;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        new Main().execute();
    }

    public void execute() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        CustomTimer customTimer = new CustomTimer();
        // Loop on the test files
        List<String> fileNames = new ArrayList<>(Arrays.asList("ar_test_file0.txt", "ar_test_file1.txt", "ar_test_file2.txt", "ar_test_file3.txt", "ar_test_file4.txt"));
        fileNames.forEach(fileName -> {
            try {
                // Instantiate new SmartAR
                SmartAR<String, Car> smartAR = new SmartAR<>();
                // Warm up!

                // Load all the file lines
                System.out.println("Parsing file: " + fileName);
                customTimer.start();
                File file = new File(classLoader.getResource(fileName).toURI());
                if (file.exists()) {
                    Stream<String> fileLines = Files.lines(Paths.get(file.getPath()));
                    List<String> keys = fileLines.collect(Collectors.toList());
                    fileLines.close();
                    System.out.println("Finished parsing file in " + customTimer.stop() + "ms");
                    // Add the keys to SmartAR
                    customTimer.start();
                    keys.forEach(key -> {
                        smartAR.add(key, new Car(key));
                    });
                    System.out.println("Finished adding " + keys.size() + " data in " + customTimer.stop() + "ms");
                } else {
                    System.out.println("The file does not exist!");
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
