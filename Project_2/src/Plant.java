public class Plant extends Animal{
    private final Types type = Types.PLANT;
    private final double weight = 1;
    private final int maxPopulation = 200;

    public synchronized double getWeight() {
        return weight;
    }
    public synchronized int getMaxPopulation() {
        return maxPopulation;
    }
    @Override
    public synchronized Types getType() {
        return type;
    }
    @Override
    public synchronized void decreaseSaturation() {
        //actSaturation = actSaturation - (maxSaturation/10.0);
    }

}