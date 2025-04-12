package com.study.demo.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class CodeRunner {

    public static String execute(String code, String language) throws IOException, InterruptedException {
        String fileID = UUID.randomUUID().toString();
        Path tempDir = Files.createTempDirectory(fileID);
        Path scriptFile = tempDir.resolve("script.java");
        Files.write(scriptFile, code.getBytes());
        //File scriptFile = File.createTempFile("script", getExtension(language));

        ProcessBuilder processBuilder = new ProcessBuilder(
                "docker", "run", "--rm", "-v",
                tempDir.toAbsolutePath() + ":/app",
                "openjdk:21", "sh", "-c",
                "javac /app/script.java && java -cp /app script"
        );

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitCode = process.waitFor();

//        Files.deleteIfExists(scriptFile);
//        Files.deleteIfExists(tempDir);

        return exitCode == 0 ? output.toString() : "Error ejecutando código";

//        try(BufferedWriter writer = new BufferedWriter(new FileWriter(scriptFile))) {
//            writer.write(code);
//        }

//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "docker", "run", "--rm", "code-executor", getRunCommand(language, scriptFile.getAbsolutePath()));
//
//        Process process = processBuilder.start();
//        process.waitFor();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        StringBuilder output = new StringBuilder();
//        String line;
//
//        while ((line = reader.readLine()) != null) {
//            output.append(line).append("\n");
//        }
//
//        //scriptFile.delete();
//
//        return output.toString();
    }

    private static String getRunCommand(String language, String path) {
        return switch (language) {
            case "python" -> "python " + path;
            case "javascript" -> "node " + path;
            default -> "echo 'Unsupported language'";
        };
    }

    private static String getExtension(String language) {
        return switch (language) {
            //case "python" -> ".py";
            case "java" -> ".java";
            case "javascript" -> ".js";
            default -> ".txt";
        };
    }
}
