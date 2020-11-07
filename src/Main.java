import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class XTool {
    static int randomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}

abstract class Robot {
    public int weight; // M

    public int distance; // S

    public int consumedEnergy;

    public Robot(int distance) {
        this.distance = distance;
    }

    public abstract void calculateConsumedEnergy();

    public abstract void print();
}

class Pedion extends Robot {
    public int flexibility; // F

    public Pedion(int distance) {
        super(distance);
        this.weight = 20;
        this.flexibility = XTool.randomNumber(1, 5); // [1, 5]
    }

    public void calculateConsumedEnergy() {
        this.consumedEnergy = this.weight * this.distance + (this.flexibility + 1) * this.distance / 2;
    }

    public void print() {
        System.out.println(
                this.getClass().getSimpleName().toUpperCase() + "\n" +
                "+ weight: " + this.weight + "\n" +
                "+ flexibility: " + this.flexibility + "\n" +
                "+ consumedEnergy: " + this.consumedEnergy + "\n"
        );
    }
}

class Zattacker extends Robot {
    public int power; // P

    public Zattacker(int distance) {
        super(distance);
        this.weight = 50;
        this.power = XTool.randomNumber(20, 30); // [20, 30]
    }

    public void calculateConsumedEnergy() {
        this.consumedEnergy = this.weight * this.distance + this.power * this.power * this.distance;
    }

    public void print() {
        System.out.println(
                this.getClass().getSimpleName().toUpperCase() + "\n" +
                        "+ weight: " + this.weight + "\n" +
                        "+ power: " + this.power + "\n" +
                        "+ consumedEnergy: " + this.consumedEnergy + "\n"
        );
    }
}

class Carrier extends Robot {
    public int energy; // P

    public Carrier(int distance) {
        super(distance);
        this.weight = 30;
        this.energy = XTool.randomNumber(50, 100); // [50, 100]
    }

    public void calculateConsumedEnergy() {
        this.consumedEnergy = this.weight * this.distance + 4 * this.energy * this.distance;
    }

    public void print() {
        System.out.println(
                this.getClass().getSimpleName().toUpperCase() + "\n" +
                        "+ weight: " + this.weight + "\n" +
                        "+ energy: " + this.energy + "\n" +
                        "+ consumedEnergy: " + this.consumedEnergy + "\n"
        );
    }
}

class Planet {
    public List<Pedion> pedionList;

    public List<Zattacker> zattackerList;

    public List<Carrier> carrierList;

    public int pedionTotalConsumedEnergy;
    public int zattackerTotalConsumedEnergy;
    public int carrierTotalConsumedEnergy;

    public Planet() {
        pedionList = new ArrayList<>();
        zattackerList = new ArrayList<>();
        carrierList = new ArrayList<>();
    }

    public void createInitialList() {
        int DEFAULT_DISTANCE = 10;
        for (int i = 0; i < XTool.randomNumber(1, 10); i++) {
            this.pedionList.add(new Pedion(DEFAULT_DISTANCE));
        }
        for (int i = 0; i < XTool.randomNumber(3, 7); i++) {
            this.zattackerList.add(new Zattacker(DEFAULT_DISTANCE));
        }
        for (int i = 0; i < XTool.randomNumber(2, 8); i++) {
            this.carrierList.add(new Carrier(DEFAULT_DISTANCE));
        }
    }

    public void calculateConsumedEnergyList() {
        for (int i = 0; i < pedionList.size(); i++) {
            pedionList.get(i).calculateConsumedEnergy();
        }
        for (int i = 0; i < zattackerList.size(); i++) {
            zattackerList.get(i).calculateConsumedEnergy();
        }
        for (int i = 0; i < carrierList.size(); i++) {
            carrierList.get(i).calculateConsumedEnergy();
        }
    }

    public void printList() {
        for (int i = 0; i < pedionList.size(); i++) {
            pedionList.get(i).print();
        }
        for (int i = 0; i < zattackerList.size(); i++) {
            zattackerList.get(i).print();
        }
        for (int i = 0; i < carrierList.size(); i++) {
            carrierList.get(i).print();
        }
    }

    public void calculateTotalConsumedEnergy() {
        for (int i = 0; i < pedionList.size(); i++) {
            this.pedionTotalConsumedEnergy += pedionList.get(i).consumedEnergy;
        }
        for (int i = 0; i < zattackerList.size(); i++) {
            this.zattackerTotalConsumedEnergy += zattackerList.get(i).consumedEnergy;
        }
        for (int i = 0; i < carrierList.size(); i++) {
            this.carrierTotalConsumedEnergy += carrierList.get(i).consumedEnergy;
        }
    }

    public void maxTotalConsumedEnergy() {
        String robotWithMaxConsumedEnergy = "";
        if (this.pedionTotalConsumedEnergy == this.zattackerTotalConsumedEnergy && this.zattackerTotalConsumedEnergy == this.carrierTotalConsumedEnergy) {
            robotWithMaxConsumedEnergy = "Pedion, " + "Zattacker, " + "Carrier";
        } else if (this.pedionTotalConsumedEnergy == this.zattackerTotalConsumedEnergy) {
            robotWithMaxConsumedEnergy = "Pedion, " + "Zattacker";
        } else if (this.zattackerTotalConsumedEnergy == this.carrierTotalConsumedEnergy) {
            robotWithMaxConsumedEnergy = "Zattacker, " + "Carrier";
        } else if (this.pedionTotalConsumedEnergy == this.carrierTotalConsumedEnergy) {
            robotWithMaxConsumedEnergy = "Pedion, " + "Carrier";
        } else {
            robotWithMaxConsumedEnergy = this.pedionTotalConsumedEnergy > this.zattackerTotalConsumedEnergy ?
                    this.pedionTotalConsumedEnergy > this.carrierTotalConsumedEnergy ? "Pedion" : "Carrier"
                    : "Zattacker";
        }
        System.out.println("The most consumed energy by " + robotWithMaxConsumedEnergy);
    }

    public void getTotalPlanConsumedEnergy() {
        System.out.println("Total Plan Consumed Energy: " + (this.pedionTotalConsumedEnergy + this.zattackerTotalConsumedEnergy + this.carrierTotalConsumedEnergy));
    }
}

class Main {
    public static void main(String[] args) {
        Planet babilon = new Planet();
        babilon.createInitialList();
        babilon.calculateConsumedEnergyList();
        babilon.printList();
        babilon.calculateTotalConsumedEnergy();
        babilon.maxTotalConsumedEnergy();
        babilon.getTotalPlanConsumedEnergy();
    }
}
