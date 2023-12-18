package ru.gb.JJ_S03_HW;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class EmployeeSerializerImpl implements EmployeeSerializer {

    @Override
    public Path serialize(Employee obj) {
        Path path = Path.of(obj.getClass().getName() + "_" + UUID.randomUUID());

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(obj);
            oos.flush();
            return path;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee deserialize(Path path) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))){
            Object object = ois.readObject();
            Files.delete(path);
            return (Employee) object;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}