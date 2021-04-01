package de.uniwue.jpp.testing;

import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.interfaces.Dish;
import de.uniwue.jpp.testing.util.AbstractTestClass;
import de.uniwue.jpp.testing.util.DataGenerator;
import de.uniwue.jpp.testing.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDish extends AbstractTestClass <Dish> {

    @Test
    public void testConstructorValidArguments() {
        List <String> dishList = DataGenerator.createDishNames(9);
        List <Double> doubleList = DataGenerator.createBasePrices(9);
        List <DishType> dishTypes = DataGenerator.createDishTypes(3, 2, 2, 2);
        int count = 0;
        for (String value : dishList) {
            try {
                construct(value, doubleList.get(count), dishTypes.get(count));
                count++;
            } catch (Exception e) {
                Assertions.fail("Testing valid arguments for Dish constructor");
            }
        }
    }

    @Test
    public void testConstructorInvalidArguments() {

        List <String> value = DataGenerator.createDishNames(9);

        System.out.println(value.toString());

        for (String s : value) {
            TestUtils.assertThrowsWithMessage(IllegalArgumentException.class, () -> construct(null, 0.5, DishType.MAIN_DISH), "Name must not be null!", "Testing invalid name argument for Dish constructor");
            TestUtils.assertThrowsWithMessage(IllegalArgumentException.class, () -> construct("", 1.5, DishType.MAIN_DISH), "Name must not be empty!", "Testing invalid name argument for Dish constructor");
            TestUtils.assertThrowsWithMessage(IllegalArgumentException.class, () -> construct(s, 1.0, null), "DishType must not be null!", "Testing invalid dishType argument for Dish constructor");
            TestUtils.assertThrowsWithMessage(IllegalArgumentException.class, () -> construct(s, -1.0, DishType.DESSERT), "BasePrice must not be zero or negative!", "Testing invalid basePrice argument for Dish constructor");
            TestUtils.assertThrowsWithMessage(IllegalArgumentException.class, () -> construct(s, 0.0, DishType.DESSERT), "BasePrice must not be zero or negative!", "Testing invalid basePrice argument for Dish constructor");
        }

    }

    @Test
    public void testGetName() {
        List <String> listDish = DataGenerator.createDishNames(9);
        List <Double> doubleList = DataGenerator.createBasePrices(9);
        List <DishType> dishTypes = DataGenerator.createDishTypes(3, 2, 2, 2);
        List <Dish> list = new ArrayList <>();
        int count = 0;

        System.out.println(listDish.toString());
        System.out.println();
        for (int i = 0; i < 9; i++) {
            Dish dish = construct(listDish.get(i), doubleList.get(i), dishTypes.get(i));
            list.add(dish);
        }
        System.out.println(list.toString());

        for (Dish value : list) {
            try {
                assertEquals(value.getName(), listDish.get(count));
                count++;
            } catch (AssertionError error) {
                Assertions.fail("Incorrect name returned by getName()");
            }
        }
    }

    @Test
    public void testGetBasePrice() {
        List <String> listDish = DataGenerator.createDishNames(9);
        List <Double> doubleList = DataGenerator.createBasePrices(9);
        List <DishType> dishTypes = DataGenerator.createDishTypes(3, 2, 2, 2);
        List <Dish> list = new ArrayList <>();
        int count = 0;

        System.out.println(listDish.toString());
        System.out.println();
        for (int i = 0; i < 9; i++) {
            Dish dish = construct(listDish.get(i), doubleList.get(i), dishTypes.get(i));
            list.add(dish);
        }
        System.out.println(list.toString());

        for (Dish value : list) {
            try {
                assertEquals(value.getBasePrice(), doubleList.get(count));
                count++;
            } catch (AssertionError error) {
                Assertions.fail("Incorrect basePrice returned by getBasePrice()");
            }
        }
    }

    @Test
    public void testGetDishType() {
        List <String> listDish = DataGenerator.createDishNames(9);
        List <Double> doubleList = DataGenerator.createBasePrices(9);
        List <DishType> dishTypes = DataGenerator.createDishTypes(3, 2, 2, 2);
        List <Dish> list = new ArrayList <>();
        int count = 0;

        System.out.println(listDish.toString());
        System.out.println();
        for (int i = 0; i < 9; i++) {
            Dish dish = construct(listDish.get(i), doubleList.get(i), dishTypes.get(i));
            list.add(dish);
        }
        System.out.println(list.toString());

        for (Dish value : list) {
            try {
                assertEquals(value.getDishType(), dishTypes.get(count));
                count++;
            } catch (AssertionError error) {
                Assertions.fail("Incorrect dishType returned by getDishType()");
            }
        }
    }

    @Test
    public void testEquals() {
        List <String> listDish = DataGenerator.createDishNames(5);
        List <Double> doubleList = DataGenerator.createBasePrices(5);
        List <DishType> dishTypes = DataGenerator.createDishTypes(2, 1, 1, 1);
        int count = 0;
        int k = 4;
        for (int i = 0; i < 5; i++) {
            try {
                assertEquals(construct(listDish.get(i),doubleList.get(i), dishTypes.get(i) ), construct(listDish.get(i), doubleList.get(k), dishTypes.get(k)));
                k--;
            } catch (AssertionError error) {
                Assertions.fail("Two dishes with identical names should be equal");
            }
        }


        try {
            Assertions.assertNotEquals(construct("dish", doubleList.get(count), dishTypes.get(count)), construct("test", doubleList.get(count), dishTypes.get(count)));
        } catch (AssertionError error) {

            Assertions.fail("Two dishes with different names should not be equal");
        }

    }

    @Test
    public void testHashCode() {
        List <String> listDish = DataGenerator.createDishNames(9);
        List <Double> doubleList = DataGenerator.createBasePrices(9);
        List <DishType> dishTypes = DataGenerator.createDishTypes(2, 2, 2, 3);
        List <Dish> dishList1 = new ArrayList <>();
        List <Dish> dishList2 = new ArrayList <>();


        for (int i = 0; i < 9; i++) {
            dishList1.add(construct(listDish.get(i), doubleList.get(i),dishTypes.get(i)));
        }

        for (int i = 0; i < 9; i++) {
            dishList2.add(construct(listDish.get(i),doubleList.get(((doubleList.size() - 1) - i)), dishTypes.get(((dishTypes.size() - 1) - i))));
        }

        for (int i = 0; i < 9; i++) {
            if (!(dishList1.get(i).hashCode() == dishList2.get(i).hashCode())) {
                throw new AssertionError("Two dishes with identical names should have the same hash code");
            }
        }
    }

    @Test
    public void testToString() {
        List <String> listDish = DataGenerator.createDishNames(9);
        List <Double> doubleList = DataGenerator.createBasePrices(9);
        List <DishType> dishTypes = DataGenerator.createDishTypes(3, 2, 2, 2);
        List <Dish> list = new ArrayList <>();


        System.out.println(listDish.toString());
        System.out.println();
        for (int i = 0; i < 9; i++) {
            Dish dish = construct(listDish.get(i), doubleList.get(i), dishTypes.get(i));
            list.add(dish);
        }
        System.out.println(list.toString());

        for (Dish value : list) {
            if (!value.toString().equals(value.getName() + " | Baseprice: " + value.getBasePrice() + "€ | Type: " + value.getDishType())){
                throw new AssertionError("Incorrect string representation returned by toString()");
            }
        }
    }

}