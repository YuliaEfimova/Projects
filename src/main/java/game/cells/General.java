package game.cells;

import game.controller.CellCoordinates;
import game.factory.ArmyFactory;

public class General extends CellBase{
    private final ArmyFactory armyFactory;

    @Override
    public void tick() {
        if (getArmy() != null) {
            setArmy(getArmy().tick());
            setArmy(getArmy().join(armyFactory.createInfantry()));
        }
    }

    public General(ArmyFactory armyFactory, CellCoordinates cellCoordinates) {
        super(cellCoordinates);
        this.armyFactory = armyFactory;
        this.setArmy(armyFactory.createInfantry());
    }

    @Override
    public CellsType getType() {
        return CellsType.GENERAL;
    }

}
