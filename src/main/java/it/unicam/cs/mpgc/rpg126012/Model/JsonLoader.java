package it.unicam.cs.mpgc.rpg126012.Model;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
/*classe che mi permettere di leggere il file json,
* e mi restituisce un JsonObject*/
public class JsonLoader {
    public JsonObject loadFile(String jsonPath) throws Exception {
        try (FileReader reader = new FileReader(jsonPath)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
    }
}
