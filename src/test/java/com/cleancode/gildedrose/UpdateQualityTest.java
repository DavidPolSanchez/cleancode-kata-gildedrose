package com.cleancode.gildedrose;

import org.junit.Test;

import static com.cleancode.gildedrose.ItemTestHelper.assertItemEquals;

public class UpdateQualityTest {


    @Test
    public void sellInDateDecreases_butQualityCannotBeNegative(){
        GildedRose app = new GildedRose(new Item("foo",0,0));
        app.updateQuality();
        assertItemEquals(app.items[0],new Item("foo",-1,0));
    }


    @Test
    public void qualityDecreases(){
        GildedRose app = new GildedRose(new Item("foo",10,10));
        app.updateQuality();
        assertItemEquals(app.items[0],new Item("foo",9,9));
    }

    @Test
    public void qualityDecreases2afterSellInDateExpired(){
        GildedRose app = new GildedRose(new Item("foo",0,10));
        app.updateQuality();
        assertItemEquals(app.items[0],new Item("foo",-1,8));
    }
}
