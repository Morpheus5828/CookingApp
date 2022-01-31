package app.foodapp.model;

public enum MeasureSystem {
    US(0),
    METRIC(1);

    private final int index;

    private MeasureSystem (int index) {
        this.index = index;
    }
}
