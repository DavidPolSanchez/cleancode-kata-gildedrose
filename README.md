# Clean Code Kata - GildedRose

Code used to demonstrate in a workshop how clean code principles can be
applied to existing legacy code.

Observation

- GildedRose class receiving Items list
- It operates on the fields of the Item objects
- The class shall be better named
- updateQuality method not only updates quality
- it also updates sellIn field

Activity

- Rename the class GildedRose to InventoryUpdater
- Rename the method updateQuality to update
- Shift the main method to own class named GildedRose
- Change the call to InventoryUpdator.main in Test class
- make it GildedRose.main
- Rename InventoryUpdatorTest class back to GildedRoseTest

Activity

- Select items[i] and refactor to extract variable, all 34 occurences
- Extract all code in for loop to method named updateItem
- pass items[i] to the method and remove variable item

Observation

- The logic in updateItem
- based on what name of the item is
- based on ruled mainly on the item name
- The logic is name based strategy

Activity

- Select all code from method updateItem
- extract method object and name the class UpdateStrategy
- Rename the invoke method to update
- Refactor move UpdateStrategy class to top level
- Pass the item object directly to update method of strategy class
- remove the extra constructor and field from strategy class

Observation

- the update method in strategy class
- logic is divided into three parts
- change of quality ignoring sellIn date
- decrement in sellIn days
- adjustment of quality for sellIn days less than zero

Activity

- Select first if and extract into method updateQualityIgnoringSellIn
- Select second if and extract into method decrementSellIn
- Select third if and extract into method adjustQualityForNegativeSellIn

Activity

- Invert if conditions from method adjustQualityForNegativeSellIn

Observation

- logic is based on the name of he item
- different logic for different name
- strategy for each name is different

Activity

- Add StrategyFactory for different names
- Change method update in InventoryUpdator

```java
private void updateItem(Item item) {
    UpdateStrategy updateStrategy = UpdateStrategyFactory.create(item.name);
    updateStrategy.update(item);
}
```

- Create the UpdateStrategyFactory class through IDE
- create method

```java
public class UpdateStrategyFactory {
    public static UpdateStrategy create(String name) {
        return new UpdateStrategy();
    }
}
```

- Add line to UpdateStrategyFactory

```java
private static final Map<String, UpdateStrategy> strategyMap = createStrategyMap();
```

- Create the method to introduce multiple strategy classes
- change logic to fetch strategy from map
- if no strategy found use UpdateStrategy

Activity

- Add AgedBrieStrategy
- Add SulfuralStrategy
- Add BackstageStrategy
- Add NormalStrategy
- Push members down from UpdateStrategy class
- Keep update method abstract

Activity

- Update NormalStrategy to keep required logic only

Activity

- Update Sulfuras class to keep only required logic

Activity

- Update AgedBrie class to keep only required logic

Activity

- Update BackstageStrategy class to keep only required logic

Activity

- Add ConjuredItem class to factory
- Add ConjuredItem logic to map
- Implement the ConjuredItem class starting from NormalStrategy
- Create ConjuredTest class to test logic