package game.army;

import java.util.function.Function;

public class SplitArmy{
    private final Army result;
    private final Army rest;

    public SplitArmy(Army result, Army rest) {
        this.result = result;
        this.rest = rest;
    }

    public Army getResult() {
        return result;
    }

    public Army getRest() {
        return rest;
    }

    public SplitArmy decorate(Function<Army, Army> decorator) {
        return new SplitArmy(decorator.apply((result)), decorator.apply((rest)));
    }


}
