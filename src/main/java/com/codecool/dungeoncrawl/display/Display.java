package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.collections.Inventory;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;
import com.codecool.dungeoncrawl.util.AssetUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Optional;

import static com.codecool.dungeoncrawl.display.Tiles.getTile;

/**
 * Only exit point to display everything
 */
public class Display {

    private StringBuilder stringBuilder;

    private final GraphicsData graphicsData;

    public Display(GraphicsData graphicsData) {
        this.graphicsData = graphicsData;
        stringBuilder = new StringBuilder();
    }

    public void drawHealth(Label healthLabel, String labelText, GraphicsContext context) {
        healthLabel.setText(labelText);
    }

    public void drawMainGame() {
        GraphicsContext context = graphicsData.context();
        List<Scenery> scenery = graphicsData.scenery();
        List<Collectable> collectables = graphicsData.collectables();
        List<Moveable> moveables = graphicsData.moveables();
        List<Asset> assets = graphicsData.assets();
        Canvas canvas = graphicsData.canvas();
        GameMap map = graphicsData.map();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawFirstLayer(map, context);
        iterateAndDraw(scenery, map, context);
        iterateAndDraw(collectables, map, context);
        iterateAndDraw(moveables, map, context);
    }

    private <T> void iterateAndDraw(List<T> assets, GameMap map, GraphicsContext context) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Asset actual = AssetUtil.getAssetByCoordinates((List<Asset>) assets, x, y).size() > 0 ?
                        AssetUtil.getAssetByCoordinates((List<Asset>) assets, x, y).get(0) : null;
                if (actual != null && AssetUtil.getAssetByCoordinates((List<Asset>) assets, x, y).size() == 1) {
                    drawTile(context, getTile(actual), x, y);
                }            }
        }
    }

    public void drawFirstLayer(GameMap map, GraphicsContext context) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                drawTile(context, getTile(null), x, y);
            }
        }
    }

    public void drawTile(GraphicsContext context, Tiles.Tile tile, int x, int y) {
        context.drawImage(Tiles.getTileset(), tile.x, tile.y, tile.w, tile.h,
                x * Tiles.TILE_WIDTH, y * Tiles.TILE_WIDTH, Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
    }

    public void showGameHint(String hint) {
        graphicsData.ui().add(new Label("GAME HINTS: \n" + hint), 0, graphicsData.map().getHeight() - 10);
    }

    public void showSpacesBetweenInfoboxContent(int emptyLines, int startRow) {
        stringBuilder = new StringBuilder();
        IntStream
                .range(0, emptyLines)
                .forEach(line -> stringBuilder.append("\n"));
        graphicsData.ui().add(new Label(stringBuilder.toString()), 0, startRow);
    }

    public  void  showNewLabelAlignedLeft(String labelText, int rowOfLabel) {
        graphicsData.ui().add(new Label(labelText), 0, rowOfLabel);
    }

    public void drawInventory(Label inventorySection) {
        Player player = (Player) graphicsData.moveables()
                .stream()
                .filter(gamer -> gamer instanceof Player)
                .findAny().get();

        Inventory inventory = player.getInventory();
        inventorySection.setText(inventory.toString());

    }
}