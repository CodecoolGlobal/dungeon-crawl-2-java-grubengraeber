package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("sword", new Tile(1, 30));
        tileMap.put("door closed", new Tile(11, 11));
        tileMap.put("enemy robot", new Tile(31, 6));
        tileMap.put("door open", new Tile(12, 11));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("fat dude", new Tile(30, 6));
        tileMap.put("goblin", new Tile(5, 6));
    }

    public static Image getTileset() {
        return tileset;
    }

    /**
     * Enter null to get an empty tile
     *
     * @param asset could be null or a real asset
     * @return Tile
     */
    public static Tile getTile(Asset asset) {
        Tile tile = asset != null ? tileMap.get(asset.getTileName()) : tileMap.get("empty");
        return tile;
    }

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }
}
