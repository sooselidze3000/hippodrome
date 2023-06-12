public class Plant extends Animal{
    public final Types type = Types.PLANT;
    private final double weight = 1;
    private final int maxPopulation = 200;

    public double getWeight() {
        return weight;
    }
    public int getMaxPopulation() {
        return maxPopulation;
    }
    @Override
    public Types getType() {
        return type;
    }
}