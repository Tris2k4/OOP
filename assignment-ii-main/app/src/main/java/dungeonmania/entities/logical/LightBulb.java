package dungeonmania.entities.logical;

import java.util.List;

import dungeonmania.entities.Entity;
import dungeonmania.entities.conductor.Conductor;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class LightBulb extends Entity implements LogicalEntity {
    private boolean activated;
    private String logic;

    public LightBulb(Position position, String logic) {
        super(position);
        this.logic = logic;
        this.activated = false;
    }

    @Override
    public void updateState(GameMap map) {
        List<Position> adjPosList = getPosition().getCardinallyAdjacentPositions();
        int activeConductorCount = 0;
        boolean allAdjacentConductorsActive = true;
        boolean areAdjacentConductorsActivatedSameTick = true;
        int activationTick = -1;

        for (Position pos : adjPosList) {
            List<Entity> entities = map.getEntities(pos);
            boolean hasActiveConductor = true;
            for (Entity entity : entities) {
                if (entity instanceof Conductor && ((Conductor) entity).isActivated()) {
                    activeConductorCount++;
                    int conductorActivationTick = ((Conductor) entity).getLastActivationTick();
                    if (activationTick == -1) {
                        activationTick = conductorActivationTick;
                    } else if (activationTick != conductorActivationTick) {
                        areAdjacentConductorsActivatedSameTick = false;
                    }
                }
                if (entity instanceof Conductor && !((Conductor) entity).isActivated()) {
                    hasActiveConductor = false;
                }
            }
            if (!hasActiveConductor) {
                allAdjacentConductorsActive = false;
            }
        }

        switch (logic) {
        case "and":
            activated = activeConductorCount >= 2 && allAdjacentConductorsActive;
            break;
        case "or":
            activated = activeConductorCount >= 1;
            break;
        case "xor":
            activated = activeConductorCount == 1;
            break;
        case "co_and":
            activated = activeConductorCount >= 2 && allAdjacentConductorsActive
                    && areAdjacentConductorsActivatedSameTick;
            break;
        default:
            activated = false;
        }
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public String getLogic() {
        return logic;
    }
}
