public class Bear extends Predator {
    public final Types type = Types.BEAR;
    private String moveDirection;
    public final int maxPopulation = 5;
    public final double weight = 500.0;
    public final int speed = 2;
    public final double maxSaturation = 80;
    private double actSaturation = 80;

    @Override
    public double getActSaturation() {
        return actSaturation;
    }

    @Override
    public void setActSaturation(double actSaturation) {
        this.actSaturation = actSaturation;
    }
    @Override
    public void decreaseSaturation() {
        actSaturation = actSaturation - (maxSaturation/10.0);
    }
    @Override
    public <T extends Animal> void eat(T animal) {
        actSaturation += animal.getWeight();
        if (actSaturation > maxSaturation) {
            actSaturation = maxSaturation;
        }
    }
    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public Types getType() {
        return type;
    }
    @Override
    public int getSpeed() {
        return speed;
    }
}
