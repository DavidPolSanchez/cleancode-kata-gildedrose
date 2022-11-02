package com.cleancode.gildedrose;

import org.junit.Test;

import static com.cleancode.gildedrose.ItemTestHelper.assertItemEquals;

public class ConjuredItemTest{


    @Test
    public void item_conjured_decreasesInQuality_twiceTheSpeed() {
        GildedRose app = new GildedRose(new Item("Coursed", 3, 6));

        app.updateQuality();

        assertItemEquals(app.items[0], new Item("Coursed", 2, 4));
    }

    @Test
    public void item_conjured_decreasesInQuality_twiceTheSpeed_alsoWhenSellInExpired() {
        GildedRose app = new GildedRose(new Item("Coursed", 0, 6));

        app.updateQuality();

        assertItemEquals(app.items[0], new Item("Coursed", -1, 2));
    }
}
