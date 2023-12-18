package ru.gb.JJ_S03_HW;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Collectors;

public class JSONEmployeeSerializerImpl implements EmployeeSerializer {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Path serialize(Employee obj) {
        Path path = Path.of(obj.getClass().getName() + "_" + UUID.randomUUID());

        try (OutputStreamWriter osw = new OutputStreamWriter(Files.newOutputStream(path))) {
            osw.write(GSON.toJson(obj));
            osw.flush();
            return path;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Employee deserialize(Path path) {
        try (BufferedReader osr = new BufferedReader(Files.newBufferedReader(path))){
            String jsonStr = osr.lines().collect(Collectors.joining());
            Files.delete(path);
            return GSON.fromJson(jsonStr,Employee.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}