package game.cells;

import game.controller.CellCoordinates;

public class Swamp extends CellBase{
    public Swamp(CellCoordinates cellCoordinates) {
        super(cellCoordinates);
    }

    @Override
    public void tick() {
        if (getArmy() != null) {
            setArmy(getArmy().takeDamage(1));
            if (getArmy() != null) {
                setArmy(getArmy().tick());
            }
        }
    }

    @Override
    public CellsType getType() {
        return CellsType.SWAMP;
    }
}
