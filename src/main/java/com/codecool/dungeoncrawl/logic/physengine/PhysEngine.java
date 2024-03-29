package com.codecool.dungeoncrawl.logic.physengine;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.data.WorldInformation;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.collectables.Key;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventAssetCollision;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventNextLevel;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventStandingOn;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;
import com.codecool.dungeoncrawl.logic.scenery.DoorClosed;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;
import com.codecool.dungeoncrawl.util.DoorOpener;
import com.codecool.dungeoncrawl.util.GameInformation;

import java.util.List;


public class PhysEngine {
    private final int boundaryMaxX;
    private final int boundaryMaxY;
    private final int boundaryMinX;
    private final int boundaryMinY;
    AssetCollection assetCollection;
    private static PhysEngine physEngineInstance = null;


    private PhysEngine(GameInformation gameInformation, WorldInformation worldInformation) {
        boundaryMinX = worldInformation.levelBoundaryMinX();
        boundaryMinY = worldInformation.levelBoundaryMinY();
        boundaryMaxX = worldInformation.levelBoundaryMaxX();
        boundaryMaxY = worldInformation.levelBoundaryMaxY();
        assetCollection = gameInformation.getAssetCollection();
    }

    public static void setPhysEngine(GameInformation gameInformation, WorldInformation worldInformation) {
        physEngineInstance = new PhysEngine(gameInformation, worldInformation);
    }

    public static PhysEngine getEngine() {
        return physEngineInstance;
    }

    public boolean tryToMove(Asset movingAsset, int x, int y) {
        return isInBoundry(x, y) && !isCollision(movingAsset, x, y);
    }


    //Maybe move because handles collision logic
    public boolean isCollision(Asset movingAsset, int x, int y) {
        List<Asset> getsCollidedAssets = assetCollection.getAssetByCoordinates(x, y);
        if (getsCollidedAssets.isEmpty()) {
            return false;
        }
        for (Asset asset : getsCollidedAssets) {
            if (movingAsset.equals(asset)) {
                return false;
            }
            if (checkForKey(getPlayer()) && asset instanceof DoorClosed) {
                Scenery door = (Scenery) asset;
                DoorOpener.openDoor((DoorClosed) door, assetCollection.getAssets());
                EventEngine.getInstance().addEvent(new EventNextLevel());
                return true;
            } else if (asset instanceof IsSolid) {
                EventEngine.getInstance().addEvent(new EventAssetCollision(movingAsset, asset));
                return true;
            } else if (asset instanceof Collectable && movingAsset instanceof Player) {
                EventEngine.getInstance().addEvent(new EventStandingOn(asset));
            }
        }
        return false;

    }

    private boolean checkForKey(Player player) {
        return player
                .getInventory()
                .getItems()
                .stream()
                .anyMatch(item -> item instanceof Key);
    }

    private Player getPlayer() {
        return (Player) assetCollection
                .getAssets()
                .stream()
                .filter(asset -> asset instanceof Player)
                .findFirst()
                .get();
    }

    public boolean isCollideable(Asset asset) {
        return true;
    }


    private boolean isInBoundry(int x, int y) {
        return x >= boundaryMinX &&
                y >= boundaryMinY &&
                x <= boundaryMaxX &&
                y <= boundaryMaxY;
    }

}
