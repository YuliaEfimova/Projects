package game.controller.state;

import game.controller.CellCoordinates;
import game.controller.commands.Command;
import game.factory.ArmyFactory;
import game.factory.CellsFactory;
import game.army.Player;
import game.cells.Cell;

import java.util.Random;

public class Map {
    private final int sizeX;
    private final int sizeY;
    private final Cell[][] map;
    private int time;

    private CellCoordinates selectedCellCoordinates;

    public Map(int sizeX, int sizeY, Cell[][] map) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.map = map;
    }
    public CellCoordinates getSelectedCellCoordinates() {
        return selectedCellCoordinates;
    }
    public void setSelectedCellCoordinates(CellCoordinates selectedCellCoordinates) {
        this.selectedCellCoordinates = selectedCellCoordinates;
    }
    public static Map create(int sizeX, int sizeY) {
        Random rnd = new Random();
        Cell[][] map = new Cell[sizeX][sizeY];
        CellsFactory factory = new CellsFactory();
        for (int i = 0; i < sizeX; i += 1) {
            for (int j = 0; j < sizeY; j += 1) {
                if ((i == 0) || (i == sizeX-1) || (j == 0) || (j == sizeY-1)) {
                    Cell mountain = factory.createCellMountain(i,j);
                    map[i][j] = mountain;
                } else {
                    int x = rnd.nextInt(10);
                    if (x < 1) {
                        Cell mountain = factory.createCellMountain(i,j);
                        map[i][j] = mountain;
                    } else if (x < 2) {
                        Cell swamp = factory.createCellSwamp(i,j);
                        map[i][j] = swamp;
                    } else {
                        Cell empty = factory.createCellEmpty(i,j);
                        map[i][j] = empty;
                    }
                }
            }
        }
        ArmyFactory armyFactoryRed = new ArmyFactory(Player.RED);
        int xRed = rnd.nextInt(sizeX - 2) + 1;
        int yRed = rnd.nextInt(sizeY/2) + 1;
        Cell generalRed = factory.createCellGeneral(armyFactoryRed, xRed, yRed);
        map[xRed][yRed] = generalRed;

        ArmyFactory armyFactoryBlue = new ArmyFactory(Player.BLUE);
        int xBlue = rnd.nextInt(sizeX - 2) + 1;
        int yBlue = rnd.nextInt(sizeY/2) + sizeY/2 - 1;
        Cell generalBlue = factory.createCellGeneral(armyFactoryBlue, xBlue, yBlue);
        map[xBlue][yBlue] = generalBlue;

        int xCityRed = rnd.nextInt(sizeX - 2) + 1;
        int yCityRed = rnd.nextInt(sizeY/2) + 1;
        Cell cityRed = factory.createCellCity(xCityRed, yCityRed);
        map[xCityRed][yCityRed] = cityRed;

        int xCityBlue = rnd.nextInt(sizeX - 2) + 1;
        int yCityBlue = rnd.nextInt(sizeY/2) + sizeY/2 - 1;
        Cell cityBlue = factory.createCellCity(xCityBlue, yCityBlue);
        map[xCityBlue][yCityBlue] = cityBlue;

        return new Map(sizeX, sizeY, map);
    }


    public void sendCommandToAllCells(Command command) {
        for (int i = 0; i < sizeX; i += 1) {
            for (int j = 0; j < sizeY; j += 1) {
                map[i][j].acceptCommand(command);
            }
        }
    }


    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Cell getCell(int x, int y) {
        return map[x][y];
    }

    public Cell getCell(CellCoordinates cellCoordinates) {
        return map[cellCoordinates.x][cellCoordinates.y];
    }

    public void incrementTimer() {
        time++;
    }

    public Cell getSelectedCell() {
        return getCell(getSelectedCellCoordinates());
    }
}
