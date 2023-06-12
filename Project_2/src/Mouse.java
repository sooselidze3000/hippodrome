public class Mouse extends Herbivore {
    public final Types type = Types.MOUSE;
    private String moveDirection;
    public final int maxPopulation = 500;
    public final double weight = 0.05;
    public final int speed = 1;
    public final double maxSaturation = 0.01;
    private double actSaturation = 0.01;

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
    void eat(Caterpillar caterpillar) {
        actSaturation += caterpillar.getWeight();
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
