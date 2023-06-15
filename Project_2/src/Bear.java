public class Bear extends Predator {
    private final Types type = Types.BEAR;
    private final int maxPopulation = 5;
    private final double weight = 500.0;
    private final int speed = 2;
    private final double maxSaturation = 80;
    private double actSaturation = 80;
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
        if (actSaturation < 0.0001) {
            actSaturation = 0;
        }
    }
    @Override
    public synchronized <T extends Animal> void eat(T animal) {
        actSaturation += animal.getWeight();
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
