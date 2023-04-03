package game.ui;

import game.controller.Controller;
import game.controller.state.Map;

public interface UI {
    void init(Controller controller);

    void updateState(Map newMap);
}
