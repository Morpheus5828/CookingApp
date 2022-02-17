package app.foodapp.model.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Node {
    protected final Map<Integer, NodeName> neighborsList;

    public Node() {
        this.neighborsList = new HashMap<>();
    }



    //Setter
    protected void setNeighbors(NodeName neighbors, int key) {
        this.neighborsList.put(key, neighbors);
    }

    //Getter
    public NodeName getNeighborsList(int key) {
        return this.neighborsList.get(key);
    }
}
