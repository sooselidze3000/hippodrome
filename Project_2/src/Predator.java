public abstract class Predator extends Animal{
    public <T extends Animal> void eat(T animal) {
        actSaturation += animal.getWeight();
        if (actSaturation > maxSaturation) {
            actSaturation = maxSaturation;
        }
    }
}
