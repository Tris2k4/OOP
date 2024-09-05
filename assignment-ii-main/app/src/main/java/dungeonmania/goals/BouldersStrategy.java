package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.entities.Switch;


public class BouldersStrategy implements Goal {
    @Override
    public boolean achieved(Game game) {
        return game.getMap().getEntities(Switch.class).stream().allMatch(s -> s.isActivated());
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game)) {
            return "";
        }
        return ":boulders";
    }
}
