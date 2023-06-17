public class Deer extends Herbivore{
    private final Types type = Types.DEER;
    private final int maxPopulation = 20;
    private final double weight = 300.0;
    private final int speed = 4;
    private final double maxSaturation = 50;
    private volatile double actSaturation = 50;
    @Override
    public synchronized int getMaxPopulation() {
        return maxPopulation;
    }
    @Override
    public synchronized double getActSaturation() {
        return actSaturation;
    }

    @Override
    public synchronized void setActSaturation(double actSaturation) {
        this.actSaturation = actSaturation;
    }

    @Override
    public synchronized void decreaseSaturation() {
        actSaturation = actSaturation - (maxSaturation/10.0);
    }
    @Override
    public synchronized void eat(Plant plant) {
        actSaturation += plant.getWeight();
        if (actSaturation > maxSaturation) {
            actSaturation = maxSaturation;
        }
    }
    @Override
    public synchronized double getWeight() {
        return weight;
    }
    @Override
    public synchronized Types getType() {
        return type;
    }
    @Override
    public synchronized int getSpeed() {
        return speed;
    }
}
