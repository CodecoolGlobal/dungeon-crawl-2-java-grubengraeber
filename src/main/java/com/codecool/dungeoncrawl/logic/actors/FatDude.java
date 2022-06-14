package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.eventengine.combat.CombatStats;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.MovementBehaviour;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

public class FatDude extends Asset implements Moveable, Fighter, IsSolid {


    public FatDude(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
    }

    @Override
    public CombatStats getCombatStats() {
        return null;
    }

    @Override
    public void setCombatStats(CombatStats combatStats) {

    }

    @Override
    public void startCombatMovement() {

    }

    @Override
    public void stopCombatMovement() {

    }

    @Override
    public MovementBehaviour getMovementBehaviour() {
        return null;
    }

    @Override
    public void setMovementBehaviour(MovementBehaviour movementBehaviour) {

    }

    @Override
    public void setCollisionMode(boolean isCollision) {

    }

    @Override
    public boolean getCollisionMode() {
        return false;
    }

    @Override
    public void setMovementStop(boolean movementStop) {

    }

    @Override
    public boolean getMovementStop() {
        return false;
    }
}
