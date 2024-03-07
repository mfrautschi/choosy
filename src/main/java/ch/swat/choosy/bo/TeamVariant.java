package ch.swat.choosy.bo;

public enum TeamVariant {
    T2ER(2), T3ER(3), T4ER(4), T5ER(5), T6ER(6), T7ER(7), T8ER(8), T9ER(9), T10ER(10);

    private final int size;

    TeamVariant(int size) {
        this.size = size;
    }

    public int size() {
        return size;
    }
}
