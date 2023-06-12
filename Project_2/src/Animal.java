public abstract class Animal{
    private Types type;
    private final int maxPopulation = -1;
    private final double weight = -1;
    private final int speed = -1;
    protected final double maxSaturation = -1;
    protected double actSaturation = -1;

    public int getSpeed() {
        return speed;
    }
    public Types getType() {
        return type;
    }

    public void setActSaturation(double actSaturation) {
        this.actSaturation = actSaturation;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public double getMaxSaturation() {
        return maxSaturation;
    }

    public double getActSaturation() {
        return actSaturation;
    }
    public void decreaseSaturation() {

    }

    public double getWeight() {
        return weight;
    }
    public <T extends Animal> void eat(T animal) {
//        actSaturation += animal.getWeight();
//        if (actSaturation > maxSaturation) {
//            actSaturation = maxSaturation;
//        }
    }

    public static String getMoveDirection() {
        switch ((int)(Math.random() * 3) + 1) {
            case 0 -> {
                return "down";
            }
            case 1 -> {
                return "up";
            }
            case 2 -> {
                return "left";
            }
            default -> {
                return "right";
            }
        }
    }
    public static <T extends Animal> T getAnimal(int type){
        switch (type) {
            case 1 -> {
                return (T) new Wolf();
            }
            case 2 -> {
                return (T) new Boa();
            }
            case 3 -> {
                return (T) new Fox();
            }
            case 4 -> {
                return (T) new Bear();
            }
            case 5 -> {
                return (T) new Eagle();
            }
            case 6 -> {
                return (T) new Horse();
            }
            case 7 -> {
                return (T) new Deer();
            }
            case 8 -> {
                return (T) new Rabbit();
            }
            case 9 -> {
                return (T) new Mouse();
            }
            case 10 -> {
                return (T) new Goat();
            }
            case 11 -> {
                return (T) new Sheep();
            }
            case 12 -> {
                return (T) new Boar();
            }
            case 13 -> {
                return (T) new Buffalo();
            }
            case 14 -> {
                return (T) new Duck();
            }
            case 15 -> {
                return (T) new Caterpillar();
            }
            default -> {
                return (T) new Plant();
            }
        }
    }
    public static <T extends Animal> T converter (Animal animal) {
        return (T) animal;
    }

}
