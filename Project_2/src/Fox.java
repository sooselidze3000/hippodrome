public class Fox extends Predator{
    private final Types type = Types.FOX;
    private final int maxPopulation = 30;
    private final double weight = 8.0;
    private final int speed = 2;
    private final double maxSaturation = 2;
    private double actSaturation = 2;
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
        if (actSaturation < 0.0001) {
            actSaturation = 0;
        }
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
