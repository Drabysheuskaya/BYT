public enum Level {
    INFO(1), WARNING(2), ERROR(3);

    private int index;

    Level(int level) {
        this.index = level;
    }

    public int getIndex() {
        return index;
    }
}
