package com.codecool.dungeoncrawl.logic.movementengine.behaviour;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;

public interface MovementBehaviour {

    public <T extends Asset & Moveable> void move(T moveableAsset, PhysEngine physEngine, GameData gameData);
}
