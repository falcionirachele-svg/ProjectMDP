package it.unicam.cs.mpgc.rpg126012.Model;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.util.*;
import com.google.gson.Gson;


/*classe che si occupa di collegare i nodi creati dal NodeFactory,
* e recupera il nodo di partenza*/
public class StoryLoader {
    //classe per salvare solo id del nodo e destinazioni
    private static class RawNode{
        String id, nodoA, nodoB;
    }
    private final JsonLoader jsonLoader= new JsonLoader();
    private final NodeFactory nodeFactory= new NodeFactory();

    public StoryData load(String jsonPath) throws Exception{
        //carica il file json nella variabile root
        JsonObject root= jsonLoader.loadFile(jsonPath);
        //uso il metodo di nodefactory che mi restituisce la mappa di nemici
        Map<String, Enemy> enemyMap=nodeFactory.buildEnemyArray(root.getAsJsonArray("enemy"));
        //richiamo un metodo che mi crea una mappa di nodi
        Map<String, StoryNode> nodeMap= buildNodeMap(root, enemyMap);
        //chiamo un metodo che mi crea i collegamenti tra i vari nodi
        linkPointers(nodeMap, root);
        //per trovare il nodo di inizio:
        StoryNode startNode= getStartNode(nodeMap, root);
        //con queste informazioni genero lo StoryData
        return new StoryData(nodeMap, startNode);
    }

    //metodo per creare la mappa di nodi
    private Map<String, StoryNode> buildNodeMap(JsonObject root, Map<String, Enemy> enemyMap)
        throws Exception{
        Map<String, StoryNode> nodeMap=new HashMap<>();
        //prendo tutti gli elementi nodi
        for(JsonElement el: root.getAsJsonArray("nodes")){
            StoryNode node= nodeFactory.buildNode(el.getAsJsonObject(), enemyMap);
            nodeMap.put(node.getId(), node);
        }
        return nodeMap;
    }

    //metodo per puntatori dei nodi
    private void linkPointers(Map<String, StoryNode> nodeMap, JsonObject root) throws Exception{
        Gson gson= new Gson();
        //per tutti i nodi
        for (JsonElement el: root.getAsJsonArray("nodes")){
            RawNode raw= gson.fromJson(el, RawNode.class);
            //recupero nodo in memoria
            StoryNode node=nodeMap.get(raw.id);
            node.setNodoA(resolve(nodeMap, raw.nodoA, raw.id, "nodoA"));
            node.setNodoB(resolve(nodeMap, raw.nodoB, raw.id, "nodoB"));
        }
    }
    //metodo per controlli, vede se l'id è nullo (es. file finale) e resistuisce null
    //dice se c'è un errore nel file json
    private StoryNode resolve(Map<String, StoryNode> nodeMap, String targetId, String sourceId, String campo) throws Exception{
        if(targetId==null){return null;}//id prossimo nodo nullo
        StoryNode target= nodeMap.get(targetId);
        if(target==null){
            throw new Exception(campo+" non trovato: "+targetId);
        }
        return target;
    }
    //meotodo per recuperare il nodo iniziale
    private StoryNode getStartNode(Map<String, StoryNode> nodeMap, JsonObject root) throws Exception{
        //verifico che la chiave di inizio ci sia nel json
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
