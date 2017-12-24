package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private List<JsonObject> examsJsonObject = new ArrayList<>();

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);

        for (Tuple<String, Integer> exam : exams) {
            examsJsonObject.add(new JsonObject(
               new JsonPair("course", new JsonString(exam.key)),
                    new JsonPair("mark", new JsonNumber(exam.value)),
                    new JsonPair("passed", isPassed(exam.value))
            ));
        }
    }

    public JsonObject toJsonObject() {
        JsonObject student = new JsonObject(
                new JsonPair("name", new JsonString(super.name)),
                new JsonPair("surname", new JsonString(super.surname)),
                new JsonPair("year", new JsonNumber(super.year)),
                new JsonPair("exams", new JsonArray(examsJsonObject.toArray(new Json[examsJsonObject.size()])))
        );
        return student;
    }

    private JsonBoolean isPassed(int mark) {
        return new JsonBoolean(mark > 2);
    }
}
