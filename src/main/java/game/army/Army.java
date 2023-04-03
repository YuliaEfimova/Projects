package game.army;

import game.factory.ArmyFactory;

public interface Army {
    Player getPlayer();
    int getStrength();
    ArmyFactory getArmyFactory();
    Army join(Army other);
    Army takeDamage(int damage);
    Army tick();
    Description description();

    SplitArmy splitHalf();
    SplitArmy splitOne();
}
