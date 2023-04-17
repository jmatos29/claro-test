package com.claro;

import com.claro.core.ClassDescription;
import com.claro.core.ClassDescriptor;
import com.claro.core.DescriptionUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new IllegalArgumentException("No file name provided");
            }
            String fileName = args[0];
            ClassDescriptor classDescriptor = new ClassDescriptor();
            ClassDescription description = classDescriptor.describe(new String(Files.readAllBytes(Paths.get(fileName))));
            DescriptionUtil.print(description);

        } catch (IOException | RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}
