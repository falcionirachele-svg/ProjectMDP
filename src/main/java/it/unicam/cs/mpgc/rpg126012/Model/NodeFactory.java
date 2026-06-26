package it.unicam.cs.mpgc.rpg126012.Model;
import com.google.gson.*;
import java.util.*;
/*si occupa di costruire i nodi dal json*/
public class NodeFactory {
    public final Gson gson = new Gson();
    /*metodo per costruire un array di nemici dal file di nemici json
    * @param enemyArray array di nemici in formato json
    * @return mappa di nemici con id come chiave*/
    public Map<String, Enemy> buildEnemyArray(JsonArray enemyArray){
        Map<String, Enemy> enemyMap = new HashMap<>();
        for (JsonElement el: enemyArray){
            Enemy e = gson.fromJson(el, Enemy.class);
            if(e==null){throw new NullPointerException("Nemico non trovato");}
            if(enemyMap.containsKey(e.getId())) throw new IllegalArgumentException("Id duplicato");
            enemyMap.put(e.getId(), e);
        }
        return enemyMap;
    }
    /*metodo per costruire un array di player dal file dei player json
    * @param playerArray array di player in formato json
    * @return mappa di player con id come chiave*/
    public Map<String, Player> buildPlayerArray(JsonArray playerArray){
        Map<String, Player> playerMap= new HashMap<>();
        for(JsonElement el: playerArray){
            Player p= gson.fromJson(el, Player.class);
            if(p==null){throw new NullPointerException("Player non trovato");}
            if(playerMap.containsKey(p.getId())) throw new IllegalArgumentException("Id duplicato");
            playerMap.put(p.getId(), p);
        }
        return playerMap;
    }
    /*metodo per costruire i nodi del file json
    * @param obj oggetto json in formato json
    * @param enemyMap mappa di nemici con id come chiave
    * @return nodo costruito
    * @throws Exception se il tipo di nodo non e' valido*/
    public StoryNode buildNode(JsonObject obj, Map<String, Enemy> enemyMap) throws Exception{
        String type= obj.get("type").getAsString();
        String id= obj.get("id").getAsString();
        return switch (type){
            case "combat"->buildCombat(obj, id, enemyMap);
            case "dialogue"->gson.fromJson(obj, DialogueNode.class);
            case "riddle"->gson.fromJson(obj, RiddleNode.class);
            default->throw new Exception("Tipo di nodo non valido"+type);
        };
    }
    /*metodo per costruire un nodo di combattimento
    * @param obj oggetto json in formato json
    * @param id id del nodo
    * @param enemyMap mappa di nemici con id come chiave
    * @return nodo di combattimento costruito
    * @throws Exception se il nemico non e' trovato*/
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
