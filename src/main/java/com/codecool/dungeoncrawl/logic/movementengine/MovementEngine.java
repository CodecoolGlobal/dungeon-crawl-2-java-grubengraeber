package com.codecool.dungeoncrawl.logic.movementengine;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;

import java.util.List;

public class MovementEngine {
    final GameData gameData;
    final PhysEngine physEngine;
    final EventEngine eventEngine;



    private MovementEngine(GameData gameData, PhysEngine physEngine, EventEngine eventEngine) {
        this.gameData = gameData;
        this.physEngine = physEngine;
        this.eventEngine = eventEngine;
    }


    public void moveAssets() {
        List<Asset> assetsToMove = gameData.assetCollection().getMovableAssets();

    }


}
