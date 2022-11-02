package com.cleancode.gildedrose;

public class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String COURSED = "Coursed";
    Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            UpdateQualityItem(item);
        }
    }

    private void UpdateQualityItem(Item  item) {
        boolean isExpired = item.sellIn < 1;
        int degradeRate= determineDegrateRate(item,isExpired);
        boolean doesDegrade = !item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES) && !item.name.equals(SULFURAS);
        boolean hasSellByDate = !item.name.equals(SULFURAS);

        if(doesDegrade){
            addjustQuality(item,degradeRate);
        }

        if(item.name.equals(AGED_BRIE)){
            int adjustment = isExpired?2:1;
            addjustQuality(item,adjustment);
        }

        if (item.name.equals(BACKSTAGE_PASSES)) {
            updateBackStagePassQuality(item, isExpired);

        }

        if(hasSellByDate){
            item.sellIn = item.sellIn -1;
        }
    }

    private void updateBackStagePassQuality(Item item, boolean isExpired) {
        addjustQuality(item,1);
        if (item.sellIn<11){
            addjustQuality(item,1);
        }
        if (item.sellIn<6){
            addjustQuality(item,1);
        }
        if (isExpired) {
            item.quality = item.quality - item.quality;
        }
    }

    private int determineDegrateRate(Item item, boolean isExpired) {
        int baseDegrade= item.name.equals(COURSED) ?-2:-1;
        return isExpired?baseDegrade*2:baseDegrade;
    }

    public static void addjustQuality(Item item, int adjustment) {
        int newQuality=  item.quality + adjustment;
        boolean isInValidRange = newQuality<=50 && newQuality>=0;
        if (isInValidRange){
             item.quality = item.quality + adjustment;
        }

    }

    public static void main(String[] args) {
		System.out.println("OMGHAI!");

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item(AGED_BRIE, 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item(SULFURAS, 0, 80), //
                new Item(SULFURAS, -1, 80),
                new Item(BACKSTAGE_PASSES, 15, 20),
                new Item(BACKSTAGE_PASSES, 10, 49),
                new Item(BACKSTAGE_PASSES, 5, 49),
                new Item(COURSED, 3, 6),
                // this conjured item does not work properly yet
                //new Item("Conjured Mana Cake", 3, 6)
        };

        GildedRose app = new GildedRose(items);

        int days = 31;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
	}
}
