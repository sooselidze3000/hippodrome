import java.util.ArrayList;

public abstract class Animal {
                                                  //0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    private static int[] animalsCounter = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int count = 0;
    private Types type;
    private final int maxPopulation = -1;
    private final double weight = -1;
    private final int speed = -1;
    protected final double maxSaturation = -1;
    protected double actSaturation = -1;
    public static int getAnimalCount (int a) {
        return animalsCounter[a];
    }

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

    public static synchronized String getMoveDirection() {
        switch ((int) (Math.random() * 3) + 1) {
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

    public static synchronized <T extends Animal> T getAnimal(int type) {
        switch (type) {
            case 0 -> {
                animalsCounter[Types.WOLF.ordinal()]++;
                return (T) new Wolf();
            }
            case 1 -> {
                animalsCounter[Types.BOA.ordinal()]++;
                return (T) new Boa();
            }
            case 2 -> {
                animalsCounter[Types.FOX.ordinal()]++;
                return (T) new Fox();
            }
            case 3 -> {
                animalsCounter[Types.BEAR.ordinal()]++;
                return (T) new Bear();
            }
            case 4 -> {
                animalsCounter[Types.EAGLE.ordinal()]++;
                return (T) new Eagle();
            }
            case 5 -> {
                animalsCounter[Types.HORSE.ordinal()]++;
                return (T) new Horse();
            }
            case 6 -> {
                animalsCounter[Types.DEER.ordinal()]++;
                return (T) new Deer();
            }
            case 7 -> {
                animalsCounter[Types.RABBIT.ordinal()]++;
                return (T) new Rabbit();
            }
            case 8 -> {
                animalsCounter[Types.MOUSE.ordinal()]++;
                return (T) new Mouse();
            }
            case 9 -> {
                animalsCounter[Types.GOAT.ordinal()]++;
                return (T) new Goat();
            }
            case 10 -> {
                animalsCounter[Types.SHEEP.ordinal()]++;
                return (T) new Sheep();
            }
            case 11 -> {
                animalsCounter[Types.BOAR.ordinal()]++;
                return (T) new Boar();
            }
            case 12 -> {
                animalsCounter[Types.BUFFALO.ordinal()]++;
                return (T) new Buffalo();
            }
            case 13 -> {
                animalsCounter[Types.DUCK.ordinal()]++;
                return (T) new Duck();
            }
            case 14 -> {
                animalsCounter[Types.CATERPILLAR.ordinal()]++;
                return (T) new Caterpillar();
            }
            default -> {
                animalsCounter[Types.PLANT.ordinal()]++;
                return (T) new Plant();
            }
        }
    }

    public static synchronized <T extends Animal> T converter(Animal animal) {
        return (T) animal;
    }

    public static synchronized void printAnimals(ArrayList<ArrayList<ArrayList<? extends Animal>>> fieldArray) {
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() != Types.PLANT) {
                count++;
            }
        })));
        System.out.println("Total number of animals is " + count);
        System.out.println("Total number of plants is " + count / 10);
        // wolves count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.WOLF) {
                count++;
            }
        })));
        System.out.println("Wolfs " + count);
        // boas count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.BOA) {
                count++;
            }
        })));
        System.out.println("Boas " + count);
        // foxes count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.FOX) {
                count++;
            }
        })));
        System.out.println("Foxes " + count);
        // bears count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.BEAR) {
                count++;
            }
        })));
        System.out.println("Bears " + count);
        // eagles count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.EAGLE) {
                count++;
            }
        })));
        System.out.println("Eagles " + count);
        // horses count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.HORSE) {
                count++;
            }
        })));
        System.out.println("Horses " + count);
        // deers count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.DEER) {
                count++;
            }
        })));
        System.out.println("Deers " + count);
        // rabbits count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.RABBIT) {
                count++;
            }
        })));
        System.out.println("Rabbits " + count);
        // mouses count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.MOUSE) {
                count++;
            }
        })));
        System.out.println("Mouses " + count);
        // goats count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.GOAT) {
                count++;
            }
        })));
        System.out.println("Goats " + count);
        // sheeps count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.SHEEP) {
                count++;
            }
        })));
        System.out.println("Sheeps " + count);
        // boar count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.BOAR) {
                count++;
            }
        })));
        System.out.println("Boars " + count);
        // buffalo count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.BUFFALO) {
                count++;
            }
        })));
        System.out.println("Buffalo " + count);
        // ducks count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.DUCK) {
                count++;
            }
        })));
        System.out.println("Ducks " + count);
        // caterpillars count
        count = 0;
        fieldArray.forEach(m -> m.forEach(n -> n.forEach(k -> {
            if (k.getType() == Types.CATERPILLAR) {
                count++;
            }
        })));
        System.out.println("Caterpillars " + count);
    }
}
