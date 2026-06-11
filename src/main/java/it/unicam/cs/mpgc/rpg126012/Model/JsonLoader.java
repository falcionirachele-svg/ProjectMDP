package it.unicam.cs.mpgc.rpg126012.Model;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
/*classe che mi permettere di leggere il file json,
* e mi restituisce un JsonObject*/
public class JsonLoader {
    //metodo per convertire file in jsonobject passandogli il percorso del mio file Json
    public JsonObject loadFile(String jsonPath) throws Exception{
        return new Gson().fromJson(new FileReader(jsonPath), JsonObject.class);
    }
}
