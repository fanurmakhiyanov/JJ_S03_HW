package ru.gb.JJ_S03_HW;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

public class Homework04Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Employee employee = new Employee("Igor",28, 3000);

        EmployeeSerializer<Employee> serializer = new EmployeeSerializerImpl();
        Path nameFile = serializer.serialize(employee);
        Employee employeeRes = serializer.deserialize(nameFile);
        System.out.println(employeeRes);


        serializer = new JSONEmployeeSerializerImpl();
        Path jsonFile = serializer.serialize(employee);
        Employee employee1 = serializer.deserialize(jsonFile);
        System.out.println(employee1);
    }
}