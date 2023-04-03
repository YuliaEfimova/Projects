package game.army;

import game.factory.ArmyFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Team implements Army {
    private final Player player;
    private final List<Army> parts;
    private final ArmyFactory armyFactory;

    public Team(Player player, List<Army> parts, ArmyFactory armyFactory) {
        this.player = player;
        this.parts = flatten(parts);
        this.armyFactory = armyFactory;
    }

    private static List<Army> flatten(List<Army> parts) {
        ArrayList<Army> result = new ArrayList<>();
        parts.forEach(part->{
            if (part instanceof Team) {
                result.addAll(((Team) part).parts);
            } else {
                result.add(part);
            }
        });
        return result.stream()
                .sorted(Comparator.comparing(Army::getStrength))
                .collect(Collectors.toList());
    }


    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int getStrength() {
        int strenght = 0;
        for (Army part : parts) {
            strenght += part.getStrength();
        }
        return strenght;
    }

    @Override
    public ArmyFactory getArmyFactory() {
        return armyFactory;
    }

    @Override
    public Army join(Army other) {
        ArrayList<Army> newParts = new ArrayList<>(parts);
        if (other instanceof Team) {
            newParts.addAll(((Team) other).parts);
        } else {
            newParts.add(other);
        }
        return new Team(player, newParts, armyFactory);
    }

    @Override
    public Army takeDamage(int damage) {
        ArrayList<Army> newParts = new ArrayList<>();
        for (Army part : parts) {
            int strength = part.getStrength();
            if (damage == 0) {
                newParts.add(part);
            } else if (strength <= damage) {
                damage -= strength;
            } else {
                Army rest = part.takeDamage(damage);
                newParts.add(rest);
                damage = 0;
            }
        }
        if (newParts.isEmpty()) {
            return null;
        } else if (newParts.size() == 1) {
            return newParts.get(0);
        } else {
            return new Team(player, newParts, armyFactory);
        }
    }

    @Override
    public Army tick() {
        List<Army> newParts = parts.stream().map(Army::tick).collect(Collectors.toList());
        return new Team(player, newParts, armyFactory);
    }

    @Override
    public Description description() {
        return parts.stream()
                .map(Army::description)
                .reduce(Description::add)
                .orElse(new Description(0, 0));
    }

    @Override
    public SplitArmy splitHalf() {
        if (parts.size() < 2) {
            return new SplitArmy(null, this);
        }
        List<Army> rest = new ArrayList<>();
        List<Army> result = new ArrayList<>();
        for (int i = 0; i < parts.size(); i += 1) {
            if (i % 2 == 0) {
                rest.add(parts.get(i));
            } else {
                result.add(parts.get(i));
            }
        }
        return new SplitArmy(
                new Team(player, result, armyFactory),
                new Team(player, rest, armyFactory)
        );
    }

    @Override
    public SplitArmy splitOne() {
        if (parts.size() < 2) {
            return new SplitArmy(null, this);
        }
        Army rest = parts.get(0);
        List<Army> result = new ArrayList<>();
        for (int i = 1; i < parts.size(); i+=1) {
            result.add(parts.get(i));
        }
        return new SplitArmy(
                new Team (player, result, armyFactory),
                rest);
    }
}
