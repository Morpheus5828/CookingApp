package app.foodapp.model.node;

public class Welcome extends Node{

    public Welcome() {
        super();
        addNodes();
        display();
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.add(NodeName.FAVORITE);
        this.neighborsList.add(NodeName.MEASURE_SYSTEM);
        this.neighborsList.add(NodeName.GET_RECIPE_BY_INGREDIENT);
    }

    public void display() {
        System.out.println(
                "\t 1. Get recipe by ingredients" + "\n" +
                "\t 2. Favorite list" + "\n" +
                "\t 3. System Measure" + "\n\n" +
                "\t\t--> Tap number: "

        );
    }

}
