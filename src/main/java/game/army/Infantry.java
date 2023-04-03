package game.army;

import game.factory.ArmyFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Infantry implements Army {
    private static final int AGE_TO_VETERAN = 60;
    private final Player player;
    private final int age;
    private final ArmyFactory armyFactory;



    public Infantry(Player player, int age, ArmyFactory armyFactory) {
        this.player = player;
        this.age = age;
        this.armyFactory = armyFactory;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public int getStrength() {
        return 1;
    }

    @Override
    public ArmyFactory getArmyFactory() {
        return armyFactory;
    }


    @Override
    public Army join(Army other) {
        if (other instanceof Team) {
            return other.join(this);
        }
        List<Army> newParts = Arrays.asList(this, other);
        return new Team(player, newParts, armyFactory);
    }

    @Override
    public Army takeDamage(int damage) {
        if (damage >= this.getStrength()) {
            return null;
        } else {
            return this;
        }
    }

    @Override
    public Army tick() {
        Infantry newInfantry = new Infantry(player, age + 1, armyFactory);
        if (age + 1 == AGE_TO_VETERAN) {
            return new VeteranDecorator(newInfantry);
        } else {
            return newInfantry;
        }
    }

    @Override
    public Description description() {
        return new Description(1, 0);
    }

    @Override
    public SplitArmy splitHalf() {
        return new SplitArmy(null, this);
    }

    @Override
    public SplitArmy splitOne() {
        return new SplitArmy(null, this);
    }
}
