package it.unicam.cs.mpgc.rpg126012.Model;
import com.google.gson.*;
import java.util.*;
/*si occupa di costruire i nodi dal json*/
public class NodeFactory {
    public final Gson gson = new Gson();
    //metodo per costruire un array di nemici dal file di nemici json
    public Map<String, Enemy> buildEnemyArray(JsonArray enemyArray){
        Map<String, Enemy> enemyMap = new HashMap<>();
        //per ogni elemento nel json array creo una classe enemy e la metto nel map
        for (JsonElement el: enemyArray){
            Enemy e = gson.fromJson(el, Enemy.class);
            enemyMap.put(e.getId(), e);
        }
        return enemyMap;
    }
    //metodo per costruire i nodi del file json
    public StoryNode buildNode(JsonObject obj, Map<String, Enemy> enemyMap) throws Exception{
        //converto le info tipo e id json in stringhe
        String type= obj.get("type").getAsString();
        String id= obj.get("id").getAsString();
        //in base al tipo di nodo creo il nodo corrispondente
        return switch (type){
            case "combat"->buildCombat(obj, id, enemyMap);
            case "dialogue"->gson.fromJson(obj, DialogueNode.class);
            case "riddle"->gson.fromJson(obj, RiddleNode.class);
            default->throw new Exception("Tipo di nodo non valido"+type);
        };
    }
    //metodo specifico per costruire nodi combattimento
    private CombatNode buildCombat(JsonObject obj, String id, Map<String, Enemy> enemyMap)
        throws Exception{
        CombatNode combatNode= gson.fromJson(obj, CombatNode.class);
        String enemyId= obj.get("nemico").getAsString();
        Enemy enemy= enemyMap.get(enemyId);
        if(enemy==null){throw new Exception("Nemico non trovato");}
        combatNode.setEnemy(enemy);
        return combatNode;
    }
}
