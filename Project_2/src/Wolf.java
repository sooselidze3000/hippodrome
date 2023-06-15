public class Wolf extends Predator {
    private final Types type = Types.WOLF;
    private final int maxPopulation = 30;
    private final double weight = 50.0;
    private final int speed = 3;
    private final double maxSaturation = 8;
    private double actSaturation = 8;
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
        actSaturation = actSaturation - (maxSaturation/10);
    }
    @Override
    public synchronized <T extends Animal> void eat(T animal) {
        actSaturation += animal.getWeight();
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
