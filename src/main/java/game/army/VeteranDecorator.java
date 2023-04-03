package game.army;

import game.factory.ArmyFactory;
import org.checkerframework.checker.units.qual.A;

import java.util.List;
import java.util.Random;

public class VeteranDecorator implements Army{
    private final Army army;
    private static final int VETERAN_STRENGTH_MULTIPLIER = 10;
    private static final double PROBABILITY_OF_SPAWN = 0.05;
    public static final Random random = new Random();

    public VeteranDecorator(Army army) {
        this.army = army;
    }

    public static Army decorate(Army army) {
        if (army == null){
            return null;
        }
        if (army instanceof VeteranDecorator) {
            return army;
        }
        return new VeteranDecorator(army);
    }

    @Override
    public Player getPlayer() {
        return army.getPlayer();
    }

    @Override
    public int getStrength() {
        return (army.getStrength() * VETERAN_STRENGTH_MULTIPLIER);
    }

    @Override
    public ArmyFactory getArmyFactory() {
        return army.getArmyFactory();
    }

    @Override
    public Army join(Army other) {
        return new Team (army.getPlayer(), List.of(this, other), army.getArmyFactory());
    }

    @Override
    public Army takeDamage(int damage) {
        return army.takeDamage(damage/VETERAN_STRENGTH_MULTIPLIER);
    }

    @Override
    public Army tick() {
        Army result = decorate(army.tick());
        if (random.nextDouble() < PROBABILITY_OF_SPAWN) {
            Army newArmy = army.getArmyFactory().createInfantry();
            result = result.join(newArmy);
        }
        return result;
    }

    @Override
    public Description description() {
        Description description = army.description();
        int normalInfantry = description.getCountNormalInfantry();
        return new Description(0, normalInfantry);
    }

    @Override
    public SplitArmy splitHalf() {
        return army.splitHalf().decorate(VeteranDecorator::decorate);
    }

    @Override
    public SplitArmy splitOne() {
        return army.splitOne().decorate(VeteranDecorator::decorate);
    }
}
