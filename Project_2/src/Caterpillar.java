public class Caterpillar extends Herbivore{
    public final Types type = Types.CATERPILLAR;
    private String moveDirection;
    public final int maxPopulation = 1000;
    public final double weight = 0.01;
    public final int speed = 4;
    public final double maxSaturation = 0;
    private double actSaturation = 0;

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
    void eat(Plant plant) {
        actSaturation += plant.getWeight();
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
