package com.codecool.dungeoncrawl.logic.collectables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {

    private Sword sword = new Sword("sword", 0, 0);

    @Test
    void isPickUpPossible() {
        sword.setPickUpPossible(true);
        assertTrue(sword.isPickUpPossible());
    }
}