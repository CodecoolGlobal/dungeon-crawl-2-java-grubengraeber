package com.codecool.dungeoncrawl.persistance;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.persistance.Data.GameState;
import com.codecool.dungeoncrawl.persistance.Data.GameStateDaoJdbc;
import com.codecool.dungeoncrawl.util.GameInformation;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CrawlerDataBaseManager {
    private final GameInformation gameInformation;
    private GameStateDaoJdbc gameStateJdbc;
    private AssetDaoJdbc assetDaoJdbc;
    private final Map<String, String> env;

    public CrawlerDataBaseManager(GameInformation gameInformation) {
        this.gameInformation = gameInformation;
        env = System.getenv();
        //TODO for working dataconnection uncomment the setUp
        //setUp();
    }

    private DataSource connect() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(env.get("database"));
        dataSource.setUser(env.get("user"));
        dataSource.setPassword(env.get("password"));
        System.out.println("Trying to connect...");
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Connection Problems!");
        }
        System.out.println("Connection OK");
        return dataSource;
    }

    private void setUp() {
        DataSource dataSource = connect();
        assetDaoJdbc = new AssetDaoJdbc(dataSource);
        gameStateJdbc = new GameStateDaoJdbc(dataSource);
    }

    public List<GameState> getGameStates() {
        return gameStateJdbc.getAll();
    }

    public boolean isNameProvided(String name) {
        return false;
    }

    public void saveGameData(String name) {
        setUp();
        int gameStateId = gameStateJdbc.safe(name);
        List<Asset> assets = gameInformation.getAssetCollection().getAssets();
        assets.forEach(asset -> assetDaoJdbc.safe(asset, gameStateId));

    }

    public void loadGameData() {


    }

}
