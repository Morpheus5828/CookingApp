package app.foodapp.model.node;

public enum NodeName {
    MAIN_MENU(0),
    GET_RECIPE_BY_INGREDIENT(1),
    FAVORITE(2),
    MEASURE_SYSTEM(3),
    RECIPE_DETAILS(4),
    CLOSE_APP(5);
    NodeName(final int index) {}

    public static String getNode(int index) {
        return NodeName.values()[index].name();
    }

    public static NodeName getNodeName(int index) {
        return NodeName.values()[index];
    }

}