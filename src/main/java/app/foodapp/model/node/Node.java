package app.foodapp.model.node;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    protected final List<NodeName> neighborsList;

    public Node() {
        this.neighborsList = new ArrayList<>();
    }



    //Setter
    protected void setNeighbors(NodeName neighbors) {
        this.neighborsList.add(neighbors);
    }

    //Getter
    public List<NodeName> getNeighborsList() {
        return this.neighborsList;
    }
}
