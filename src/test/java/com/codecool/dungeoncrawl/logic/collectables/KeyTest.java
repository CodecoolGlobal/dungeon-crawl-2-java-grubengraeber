package com.codecool.dungeoncrawl.logic.collectables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {

    @Test
    void isPickUpPossible() {
        Key key = new Key("key", 0, 0);
        key.setPickUpPossible(true);
        assertTrue(key.isPickUpPossible());
    }
}