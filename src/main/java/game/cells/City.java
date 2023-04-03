package game.cells;

import game.controller.CellCoordinates;

public class City extends CellBase {

    public City(CellCoordinates cellCoordinates) {
        super(cellCoordinates);
    }

    @Override
    public void tick() {
        if (getArmy() != null) {
            setArmy(getArmy().tick());
            if (getArmy() != null) {
                setArmy(getArmy().join((getArmy().getArmyFactory()).createInfantry()));
            }
        }
    }

    @Override
    public CellsType getType() {
        return CellsType.CITY;
    }
}
