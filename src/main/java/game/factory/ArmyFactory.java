package game.factory;

import game.army.Army;
import game.army.Infantry;
import game.army.Player;

public class ArmyFactory {
    private final Player player;

    public ArmyFactory(Player player) {
        this.player = player;
    }

    public Army createInfantry () {
        return new Infantry(player, 0, this);
    }
}
