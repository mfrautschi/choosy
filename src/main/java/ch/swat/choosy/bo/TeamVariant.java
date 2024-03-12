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

    /**
     *
     * @param size TeamVariant size
     * @return Teamvariant if size exists ; null if no TeamVariant with the size is found
     */
    public static TeamVariant valueOf(int size){
        for(TeamVariant en : TeamVariant.values()){
            if(en.size==size){
                return en;
            }
        }
        return null;
    }
}
