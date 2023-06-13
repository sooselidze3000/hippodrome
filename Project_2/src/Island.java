import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Island {
    public static void main(String[] args) {
        int a = (int) (Math.random() * 80 + 20);// рандомное число стороны поля а
        int b = (int) (Math.random() * 80 + 20);// рандомное число стороны поля в
        int c = (int) (Math.random() * 80 + 20);//рандомное число для заполнения поля животными/растениями
        int animalsCount = a * b * c;
        ArrayList<ArrayList<ArrayList<? extends Animal>>> fieldArray = new ArrayList<>(); //размер одной стороны поля
        int type;
        // INITIALIZATION
        for (int i = 0; i < a; i++) {
            fieldArray.add(new ArrayList<>()); // размер второй стороны поля и заполнение первой стороны
            for (int j = 0; j < b; j++) {
                fieldArray.get(i).add(new ArrayList<>()); //размер поля и заполнение полей
                for (int k = 0; k < c; k++) {
                    type = ThreadLocalRandom.current().nextInt(0, 15);
                    if (k < (c / 10)) {
                        fieldArray.get(i).get(j).add(Animal.getAnimal(20)); //заполнение травой каждую ячейку на 10%
                    } else {
                        fieldArray.get(i).get(j).add(Animal.getAnimal(type));
                        if (Animal.getAnimalCount(type) > fieldArray.get(i).get(j).get(k).getMaxPopulation()) {
                            fieldArray.get(i).get(j).remove(fieldArray.get(i).get(j).get(k));
                        }
                    }
                }
            }
        }
        System.out.println("Island created size is " + a + "*" + b + "*" + c);
//        Animal.printAnimals(fieldArray);
        class RandomEating implements Runnable {
            int getA1 = 0;
            int getA2 = 0;
            int count = 0;
            @Override
            public void run() {
                fieldArray
                        .forEach(n -> n
                                .forEach(m -> {
                                    while (getA1 == getA2 || m.get(getA1).getType() == Types.PLANT && m.get(getA2).getType() == Types.PLANT) {
                                        getA1 = ThreadLocalRandom.current().nextInt(0, m.size());
                                        getA2 = ThreadLocalRandom.current().nextInt(0, m.size());
                                    } // trying until both not plants and not same animal
                                    if (ChanceToEat.getChance(m.get(getA1).getType().ordinal(), m.get(getA2).getType().ordinal()) >
                                            ChanceToEat.getChance(m.get(getA2).getType().ordinal(), m.get(getA1).getType().ordinal())) {
                                        if (ThreadLocalRandom.current().nextInt(0, 100) <=
                                                ChanceToEat.getChance(m.get(getA1).getType().ordinal(), m.get(getA2).getType().ordinal())) {
                                            m.get(getA1).eat(m.remove(getA2));
                                            count++;
                                        }
                                    } else {
                                        if (ThreadLocalRandom.current().nextInt(0, 100) <=
                                                ChanceToEat.getChance(m.get(getA2).getType().ordinal(), m.get(getA1).getType().ordinal())) {
                                            m.get(getA2).eat(m.remove(getA1));
                                            count++;
                                        }
                                    }
                                    getA1 = 0; // reset for next field
                                    getA2 = 0; // reset for next field
                                }));
//                System.out.println(count + " animals ate other animals");
//                System.out.printf("%.3f", (double) count / (double) animalsCount * 100);
//                System.out.println("%");
                count = 0;
            }
        }
        class AllAnimalsSlowlyDying implements Runnable {
            int count = 0;
            @Override
            public void run() {
                fieldArray
                        .forEach(n -> n
                                .forEach(m -> m
                                        .forEach(k -> {
                                            if (k.getType() != Types.PLANT) {
                                                k.decreaseSaturation();
                                            }
                                        }))); // slowly animals dying
                fieldArray
                        .forEach(n -> n
                                .forEach(m -> {
                                    for (int i = m.size() - 1; i >= 0; i--) {
                                        if (m.get(i).getActSaturation() <= 0 && m.get(i).getType() != Types.PLANT) {
                                            m.remove(i);
                                            count++;
                                        }
                                    }
                                })); //Check if dead
//                System.out.println(count + " animals died");
//                System.out.print("");
//                System.out.printf("%.3f", (double) count / (double) animalsCount * 100);
//                System.out.println("%");
                count = 0;
            }
        }
        class GrowingPlants implements Runnable {
            int a;
            int b;
            final int randFieldsCount = (int) ((fieldArray.size() * fieldArray.get(0).size()) * 0.15);
            Set set = new HashSet<String>();
            int count = 0;

            @Override
            public void run() {
                for (int i = 0; i < randFieldsCount; i++) {
                    a = ThreadLocalRandom.current().nextInt(0, fieldArray.size());
                    b = ThreadLocalRandom.current().nextInt(0, fieldArray.get(a).size());
                    if (!set.contains(a + "" + b)) {
                        set.add(a + "" + b); // to not repeat field
                        fieldArray.get(a).get(b).add(Animal.getAnimal(20)); // planting plant
                        count++;
                    } else {
                        i--;
                    }
                }
//                System.out.println(count + " plants was planted");
//                System.out.printf("%.3f", (double) count / (double) animalsCount * 100);
//                System.out.println("%");
                set.clear(); // clear set for next iteration
                count = 0;
            }
        }
        class RandomAnimalMove implements Runnable {
            int pos = 0;
            int step = 0;
            String direction = "";
            int count = 0;

            @Override
            public void run() {
                fieldArray
                        .forEach(n -> n
                                .forEach(m -> {
                                    for (int i = m.size() - 1; i >= 0; i--) {
                                        if (i < ThreadLocalRandom.current().nextInt(0, (m.size() - 1))) {
                                            if (m.get(i).getType() != Types.PLANT) {
                                                step = ThreadLocalRandom.current().nextInt(0, (m.get(i).getSpeed() + 1));
                                            }
                                            direction = Animal.getMoveDirection();
                                            if (step > 0) {
                                                count++;
                                                if (direction.equalsIgnoreCase("down")) {
                                                    pos = fieldArray.indexOf(n) + step;
                                                    if (pos >= fieldArray.size())
                                                        pos = fieldArray.size() - 1;
                                                    fieldArray.get(pos).get(n.indexOf(m)).add(Animal.converter(m.remove(i)));
                                                } else if (direction.equalsIgnoreCase("up")) {
                                                    pos = fieldArray.indexOf(n) - step;
                                                    if (pos < 0)
                                                        pos = 0;
                                                    fieldArray.get(pos).get(n.indexOf(m)).add(Animal.converter(m.remove(i)));
                                                } else if (direction.equalsIgnoreCase("left")) {
                                                    pos = n.indexOf(m) - step;
                                                    if (pos < 0)
                                                        pos = 0;
                                                    n.get(pos).add(Animal.converter(m.remove(i)));
                                                } else if (direction.equalsIgnoreCase("right")) {
                                                    pos = n.indexOf(m) + step;
                                                    if (pos >= n.size())
                                                        pos = n.size() - 1;
                                                    n.get(pos).add(Animal.converter(m.remove(i)));
                                                }
                                            }
                                        }
                                        step = 0;
                                    }
                                }));
//                System.out.println(count + " animals moved");
//                System.out.printf("%.3f", (double) count / (double) animalsCount * 100);
//                System.out.println("%");
                pos = 0;
                count = 0;
            }
        }
        class MakingBabies implements Runnable {
            int getA1 = 0;
            int getA2 = 0;
            int count = 0;

            @Override
            public void run() {
                fieldArray
                        .forEach(n -> n
                                .forEach(m -> {
                                    while (getA1 != getA2 || m.get(getA1).getType() == Types.PLANT && m.get(getA2).getType() == Types.PLANT) {
                                        getA1 = ThreadLocalRandom.current().nextInt(0, m.size());
                                        getA2 = ThreadLocalRandom.current().nextInt(0, m.size());
                                    } // trying until both not plants and same animal
                                    if (m.get(getA1).getType() == m.get(getA2).getType()) {
                                        m.add(Animal.getAnimal(m.get(getA2).getType().ordinal() + 1));
                                        count++;
                                    }
                                    getA1 = 0; // reset for next field
                                    getA2 = 0; // reset for next field
                                }));
//                System.out.println(count + " babies was born");
//                System.out.printf("%.3f", (double) count / (double) animalsCount * 100);
//                System.out.println("%");
                count = 0;
            }
        }
        class PrintStatus implements Runnable {
            int iterations = 0;
            @Override
            public void run() {
                try {
                    System.out.println("Iteration " + ++iterations);
                    Animal.printAnimals(fieldArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(7);
        executorService.scheduleAtFixedRate(new RandomEating(), 0, 5000, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new AllAnimalsSlowlyDying(), 10, 5000, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new GrowingPlants(), 10, 5000, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new RandomAnimalMove(), 10, 5000, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new MakingBabies(), 10, 5000, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new PrintStatus(), 10, 5000, TimeUnit.MILLISECONDS);
    }
}
