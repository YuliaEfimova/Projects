package game.factory;

import game.cells.*;
import game.controller.CellCoordinates;

public class CellsFactory {
    public Cell createCellSwamp(int x, int y) {
        return new Swamp(new CellCoordinates(x, y));
    }
    public Cell createCellMountain(int x, int y) {
        return new Mountain(new CellCoordinates(x, y));
    }
    public Cell createCellGeneral(ArmyFactory armyFactory, int x, int y) {
        return new General(armyFactory, new CellCoordinates(x, y));
    }
    public Cell createCellEmpty(int x, int y) {
        return new Empty(new CellCoordinates(x, y));
    }
    public Cell createCellCity(int x, int y) {
        return new City(new CellCoordinates(x, y));
    }
}
