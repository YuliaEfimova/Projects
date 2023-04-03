package game.controller.commands;

import game.army.Army;
import game.army.SplitArmy;
import game.cells.Cell;
import game.cells.CellsType;
import game.controller.state.Map;

public class CommandGo implements Command{
    private final Cell destination;
    private final boolean alt;
    private final Map map;

    public CommandGo(Cell destination, boolean alt, Map map) {
        this.destination = destination;
        this.alt = alt;
        this.map = map;
    }

    @Override
    public void executeOnCell(Cell cell) {
        Army our = cell.getArmy();
        if (our == null) {
            return;
        }
        if (destination.getType() == CellsType.MOUNTAIN) {
            return;
        }
        SplitArmy armies = alt ? our.splitHalf() : our.splitOne();
        cell.setArmy(armies.getRest());
        our = armies.getResult();
        if (our == null) {
            destination.select(map);
            return;
        }
        Army other = destination.getArmy();
        if (other == null) {
            destination.setArmy(our);
            destination.select(map);
        } else if (other.getPlayer() == our.getPlayer()){
            destination.setArmy(other.join(our));
            destination.select(map);
        } else {
            int enemyStrength = other.getStrength();
            int ourStrength = our.getStrength();
            other = other.takeDamage(ourStrength);
            our = our.takeDamage(enemyStrength);
            if (other == null) {
                destination.setArmy(our);
                destination.select(map);
            } else {
                destination.setArmy(other);
            }
        }
    }

    @Override
    public void executeOnMap(Map map) {
        //Do nothing
    }

    @Override
    public void executeOnArmy(Army army) {
        //Do nothing
    }
}
