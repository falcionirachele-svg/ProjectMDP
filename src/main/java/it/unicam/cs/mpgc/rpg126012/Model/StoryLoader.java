package it.unicam.cs.mpgc.rpg126012.Model;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.*;
import com.google.gson.Gson;

/*classe che si occupa di collegare i nodi creati dal NodeFactory,
* recupera il nodo di partenza e collega i nodi a quelli successivi*/
public class StoryLoader {
    private static class RawNode{
        String id, nodoA, nodoB;
    }
    private final JsonLoader jsonLoader= new JsonLoader();
    private final NodeFactory nodeFactory= new NodeFactory();
    /*Metodo per caricare il file json e creare lo StoryData
    * @param jsonPath percorso del file json
    * @return lo StoryData creato*/
    public StoryData load(String jsonPath) throws Exception{
        JsonObject root= jsonLoader.loadFile(jsonPath);
        Map<String, Enemy> enemyMap=nodeFactory.buildEnemyArray(root.getAsJsonArray("enemy"));
        Map<String, Player> playerMap= nodeFactory.buildPlayerArray(root.getAsJsonArray("player"));
        Map<String, StoryNode> nodeMap= buildNodeMap(root, enemyMap);
        linkPointers(nodeMap, root);
        StoryNode startNode= getStartNode(nodeMap, root);
        return new StoryData(nodeMap, startNode);
    }
    //metodo per ottenere la mappa dei player
    /*per implementazioni future: mi permette di ottenere dei personaggi standard nel json,
    * che possono essere selezionati in futuro dalla view */
    public Map<String, Player> getAlPlayerMap(Map<String, Player>playerMap){
        return playerMap;

    }
    /*Metodo per creare la mappa di nodi
    * @param root oggetto json contenente i nodi
    * @param enemyMap mappa dei nemici
    * @return mappa dei nodi*/
    private Map<String, StoryNode> buildNodeMap(JsonObject root, Map<String, Enemy> enemyMap)
        throws Exception{
        Map<String, StoryNode> nodeMap=new HashMap<>();
        for(JsonElement el: root.getAsJsonArray("nodes")){
            StoryNode node= nodeFactory.buildNode(el.getAsJsonObject(), enemyMap);
            nodeMap.put(node.getId(), node);
        }
        return nodeMap;
    }

    /*Metodo per gestire i puntatori dei nodi
    * @param nodeMap mappa dei nodi
    * @param root oggetto json contenente i nodi*/
    private void linkPointers(Map<String, StoryNode> nodeMap, JsonObject root) throws Exception{
        Gson gson= new Gson();
        for (JsonElement el: root.getAsJsonArray("nodes")){
            RawNode raw= gson.fromJson(el, RawNode.class);
            StoryNode node=nodeMap.get(raw.id);
            node.setNodoA(resolve(nodeMap, raw.nodoA, raw.id, "nodoA"));
            node.setNodoB(resolve(nodeMap, raw.nodoB, raw.id, "nodoB"));
        }
    }
    /*metodo per controlli
    * @param nodeMap mappa dei nodi
    * @param targetId id del prossimo nodo
    * @param sourceId id del nodo corrente
    * @param campo nome del campo del json
    * @return il prossimo nodo, null se non esiste*/
    private StoryNode resolve(Map<String, StoryNode> nodeMap, String targetId, String sourceId, String campo) throws Exception{
        if(targetId==null){return null;}
        StoryNode target= nodeMap.get(targetId);
        if(target==null){
            throw new Exception(campo+" non trovato: "+targetId);
        }
        return target;
    }
    /*Metodo per recuperare il nodo iniziale
    * @param nodeMap mappa dei nodi
    * @param root oggetto json contenente i nodi
    * @return il nodo iniziale*/
    private StoryNode getStartNode(Map<String, StoryNode> nodeMap, JsonObject root) throws Exception{
        if (!root.has("start") || root.get("start").isJsonNull()) {
            throw new Exception("Errore nel JSON: manca la chiave 'start'!");
        }
        String startId = root.get("start").getAsString();
        StoryNode startNode= nodeMap.get(startId);
        if(startNode==null){
            throw new Exception("Nodo di partenza non trovato "+ startId);
        }
        return startNode;
    }
}
