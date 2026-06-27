package it.unicam.cs.mpgc.rpg126012.Model.Story;

import java.util.Map;
/*classe che contiene la mappa degli story node e il nodo iniziale */
public class StoryData {
    private final Map<String, StoryNode> nodeMap;
    private final StoryNode startNode;
    public StoryData(Map<String, StoryNode> nodeMap, StoryNode startNode){
        this.nodeMap=nodeMap;
        this.startNode=startNode;
    }
    public StoryNode getNodoIniziale(){
        return startNode;
    }
    public Map<String, StoryNode> getNodeMap() {
        return nodeMap;
    }
    public StoryNode getNode(String id){
        return nodeMap.get(id);
    }
}
