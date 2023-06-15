import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Island {
    public static void main(String[] args) {
        int a = (int) (Math.random() * 20 + 20);// рандомное число стороны поля а
        int b = (int) (Math.random() * 20 + 20);// рандомное число стороны поля в
        int c = (int) (Math.random() * 130 + 20);//рандомное число для заполнения поля животными/растениями
        int[][][] animalsCount = new int[a][b][16];
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
                        fieldArray.get(i).get(j).add(Animal.getAnimal(15));
                        animalsCount[i][j][15]++;//заполнение травой каждую ячейку на 10%
                    } else {
                        fieldArray.get(i).get(j).add(Animal.getAnimal(type));
                        animalsCount[i][j][type]++;
                    }
                }
            }
        }
        System.out.println("Island created size is " + a + "*" + b + "*" + c);
//        System.out.println(fieldArray.get(0).get(0).get(16));
//        fieldArray.get(0).get(0).remove(5);
//        System.out.println(fieldArray.get(0).get(0).get(16));
        class RandomEating implements Runnable {
            int getA1 = 0;
            int getA2 = 0;
            int count = 0;

            @Override
            public void run() {
                try {
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
                } catch (Exception e) {
                    System.out.println("Exception in eating ");
                    e.printStackTrace();
                }

                count = 0;
            }
        }
        class AllAnimalsSlowlyDying implements Runnable {
            int count = 0;

            @Override
            public void run() {
                try {
                    fieldArray
                            .forEach(n -> n
                                    .forEach(m -> {
                                        for (int i = m.size() - 1; i >= 0; i--) {
                                            m.get(i).decreaseSaturation();
                                        }
                                    })); // slowly animals dying
                } catch (Exception e) {
                    System.out.println("Exception in health decrease ");
                    e.printStackTrace();
                }

                try {
                    fieldArray
                            .forEach(n -> n
                                    .forEach(m -> {
                                        for (int i = m.size() - 1; i >= 0; i--) {
                                            if (m.get(i).getActSaturation() <= 0.0000001 && m.get(i).getType() != Types.PLANT) {
                                                animalsCount[fieldArray.indexOf(n)][n.indexOf(m)][m.remove(i).getType().ordinal()]--;
                                                count++;
                                            }
                                        }
                                    })); //Check if dead
                } catch (Exception e) {
                    System.out.println("Exception in killing animals ");
                    e.printStackTrace();
                }
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
                        fieldArray.get(a).get(b).add(0, Animal.getAnimal(15)); // planting plant
                        count++;
                    } else {
                        i--;
                    }
                }
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
                try {
                    fieldArray
                            .forEach(n -> n
                                    .forEach(m -> {
                                        while (getA1 != getA2 || m.get(getA1).getType() == Types.PLANT && m.get(getA2).getType() == Types.PLANT) {
                                            getA1 = ThreadLocalRandom.current().nextInt(0, m.size());
                                            getA2 = ThreadLocalRandom.current().nextInt(0, m.size());
                                        } // trying until both not plants and same animal
                                        if (m.get(getA1).getType() == m.get(getA2).getType()) {
                                            m.add(Animal.getAnimal(m.get(getA2).getType().ordinal()));
                                            count++;
                                        }
                                        getA1 = 0; // reset for next field
                                        getA2 = 0; // reset for next field
                                    }));
//                System.out.println(count + " babies was born");
                } catch (Exception e) {
                    System.out.println("Exception in making babies ");
                    e.printStackTrace();
                }
                count = 0;
            }
        }
        class PrintStatus implements Runnable {
            int iterations = 0;
            int rand1 = 0;
            int rand2 = 0;
            Animal animal;

            @Override
            public void run() {
                System.out.println("Iteration " + ++iterations);
//                rand1 = ThreadLocalRandom.current().nextInt(0,animalsCount.length);
//                rand2 = ThreadLocalRandom.current().nextInt(0,animalsCount[rand1].length);
//                System.out.println("Animals count on field x" + rand1 + " y" + rand2 + " is :");
//                System.out.println("Wolves count " + animalsCount[rand1][rand2][0]);
//                System.out.println("Boas count " + animalsCount[rand1][rand2][1]);
//                System.out.println("Foxes count " + animalsCount[rand1][rand2][2]);
//                System.out.println("Bears count " + animalsCount[rand1][rand2][3]);
//                System.out.println("Eagles count " + animalsCount[rand1][rand2][4]);
//                System.out.println("Horses count " + animalsCount[rand1][rand2][5]);
//                System.out.println("Deer count " + animalsCount[rand1][rand2][6]);
//                System.out.println("Rabbits count " + animalsCount[rand1][rand2][7]);
//                System.out.println("Mouses count " + animalsCount[rand1][rand2][8]);
//                System.out.println("Goats count " + animalsCount[rand1][rand2][9]);
//                System.out.println("Sheep count " + animalsCount[rand1][rand2][10]);
//                System.out.println("Boars count " + animalsCount[rand1][rand2][11]);
//                System.out.println("Buffalo count " + animalsCount[rand1][rand2][12]);
//                System.out.println("Ducks count " + animalsCount[rand1][rand2][13]);
//                System.out.println("Caterpillars count " + animalsCount[rand1][rand2][14]);
//                System.out.println("Plants count " + animalsCount[rand1][rand2][15]);
            }
        }
        class CheckEnd implements Runnable {
            int totalAnimalsCount = 0;

            @Override
            public void run() {
                for (int i = 0; i < animalsCount.length; i++) {
                    for (int j = 0; j < animalsCount[i].length; j++) {
                        for (int k = 0; k < (animalsCount[i][j].length - 1); k++) {
                            totalAnimalsCount += animalsCount[i][j][k];
                        }
                    }
                }
                if (totalAnimalsCount <= 0) {
                    System.out.println("SIMULATION IS FINISHED ALL ANIMALS DEAD");
                    totalAnimalsCount = 0;
                } else {
                    System.out.println("Total animals count is " + totalAnimalsCount);
                    totalAnimalsCount = 0;
                }
            }
        }
        final int msTime = 100;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        ScheduledExecutorService executorService1 = Executors.newScheduledThreadPool(1);
//        executorService1.scheduleAtFixedRate(new RandomEating(), 10, msTime, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new AllAnimalsSlowlyDying(), 10, msTime, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new GrowingPlants(), 10, msTime, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new RandomAnimalMove(), 10, msTime, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new MakingBabies(), 10, msTime, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new PrintStatus(), 10, msTime, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new CheckEnd(), 10, msTime, TimeUnit.MILLISECONDS);
    }
}
