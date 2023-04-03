package game.army;

public class Description {
    private final int countNormalInfantry;
    private final int countVeteranInfantry;

    public Description(int countNormalInfantry, int countVeteranInfantry) {
        this.countNormalInfantry = countNormalInfantry;
        this.countVeteranInfantry = countVeteranInfantry;
    }

    public int getCountNormalInfantry() {
        return countNormalInfantry;
    }

    public int getCountVeteranInfantry() {
        return countVeteranInfantry;
    }

    public Description add(Description other) {
        return new Description(
                countNormalInfantry + other.countNormalInfantry,
                countVeteranInfantry + other.countVeteranInfantry);
    }
}
