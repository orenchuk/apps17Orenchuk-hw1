package json;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private List<JsonPair> jsons;

    public JsonObject(JsonPair... jsonPairs) {
        jsons = new LinkedList<>();
        for (JsonPair pair : jsonPairs) {
            add(pair);
        }
    }

    @Override
    public String toJson() {
        return "{\n" + getJsonArrBody() + "\n}";
    }

    private String getJsonArrBody() {
        String jsonStr = "";
        Iterator<JsonPair> jsonIterator = jsons.iterator();
        while (jsonIterator.hasNext()) {
            JsonPair jsonPair = jsonIterator.next();
            jsonStr += jsonPair.key + ": " + jsonPair.value.toJson();
            if (jsonIterator.hasNext())
                jsonStr += ",\n";
        }
        return jsonStr;
    }

    public void add(JsonPair jsonPair) {
        for (JsonPair pair : jsons) {
            if (pair.key.equals(jsonPair.key)) {
                int index = jsons.indexOf(pair);
                jsons.set(index, jsonPair);
                return;
            }
        }
        jsons.add(jsonPair);
        System.out.println(jsonPair.value.toJson());
    }

    public Json find(String name) {
        for (JsonPair pair : jsons) {
            if (pair.key.equals(name)) {
                return pair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();

        for (String name : names) {
            Json result = find(name);
            if (result != null) {
                jsonObject.add(new JsonPair(name, result));
            }
        }

        return jsonObject;
    }
}
