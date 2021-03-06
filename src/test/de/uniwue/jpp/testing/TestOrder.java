package de.uniwue.jpp.testing;

import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.interfaces.Dish;
import de.uniwue.jpp.testing.interfaces.Guest;
import de.uniwue.jpp.testing.interfaces.Order;
import de.uniwue.jpp.testing.interfaces.createOrder;
import de.uniwue.jpp.testing.util.AbstractTestClass;
import de.uniwue.jpp.testing.util.DataGenerator;
import de.uniwue.jpp.testing.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestOrder extends AbstractTestClass <Order> {

    @Test
    public void testConstructorValidArguments() {

        List <Order> orderList = new ArrayList <>();

        for (int i = 1; i < 6; i++) {
            orderList.add(DataGenerator.createOrderMock(i));
        }
        int[][] array = new int[5][4];
        array[0] = new int[]{0, 1, 2, 2};
        array[1] = new int[]{2, 0, 1, 2};
        array[2] = new int[]{2, 1, 0, 2};
        array[3] = new int[]{2, 2, 1, 0};
        array[4] = new int[]{1, 1, 1, 2};

        List <List <Dish>> listList = DataGenerator.createDishSets(array);


        for (int i = 0; i < orderList.size(); i++) {
            try {
                construct(orderList.get(i).getGuest(), listList.get(i));
            } catch (Exception error) {
                Assertions.fail("Testing valid arguments for Order constructor");
            }
        }


    }

    @Test
    public void testConstructorInvalidArguments() {

        List <Guest> list = DataGenerator.createGuestMocks(2, 3, 2);
        List <Dish> dishList = DataGenerator.createDishMocks(3, 2, 2, 2);


        for (Guest test : list) {
            TestUtils.assertThrowsWithMessage(IllegalArgumentException.class, () -> construct(null, dishList), "Guest must not be null!", "Testing invalid guest argument for Order constructor");
            TestUtils.assertThrowsWithMessage(IllegalArgumentException.class, () -> construct(test, null), "Dish collection must not be null!", "Testing invalid dishes argument for Order constructor");
            TestUtils.assertThrowsWithMessage(IllegalArgumentException.class, () -> construct(test, List.of()), "Dish collection must not be empty!", "Testing invalid dishes argument for Order constructor");
        }

    }

    @Test
    public void testGetDishes() {
        List <Guest> list = DataGenerator.createGuestMocks(2, 1, 2);
        List <Dish> dishList = DataGenerator.createDishMocks(0, 1, 1, 1);
        List <Dish> list1 = DataGenerator.createDishMocks(1, 0, 1, 1);
        List <Dish> list2 = DataGenerator.createDishMocks(1, 1, 0, 1);
        List <Dish> list3 = DataGenerator.createDishMocks(1, 1, 1, 0);
        List <Dish> list4 = DataGenerator.createDishMocks(1, 1, 1, 1);

        List <List <Dish>> listList;

        listList = List.of(list1, list2, list3, list4, dishList);


        List <Order> listOrder = new ArrayList <>();

        for (int i = 0; i < 5; i++) {
            listOrder.add(construct(list.get(i), listList.get(i)));
        }
        int k = 0;
        for (Order order : listOrder) {
            TestUtils.assertCollectionsEquals(order.getDishes(), listList.get(k), "Incorrect collection returned by getDishes()");
            k++;

            TestUtils.assertCollectionIsUnmodifiable(order.getDishes(), "Collection returned by getDishes() is modifiable");
        }

    }

    @Test
    public void testGetGuest() {
        List <Guest> list = DataGenerator.createGuestMocks(2, 1, 2);
        List <Dish> dishList = DataGenerator.createDishMocks(1, 2, 1, 1);
        List <Order> listOrder = new ArrayList <>();


        for (int i = 0; i < 5; i++) {
            listOrder.add(construct(list.get(i), dishList));
        }
        int k = 0;
        for (Order order : listOrder) {
            try {
                Assertions.assertEquals(order.getGuest(), list.get(k));
                k++;
            } catch (AssertionError error) {
                Assertions.fail("Incorrect guest returned by getGuest()");

            }
        }

    }

    @Test
    public void testCalculatePrice() {
        List <Guest> list = DataGenerator.createGuestMocks(2, 1, 2);
        List<Guest> guestList = DataGenerator.createGuestMocks(1,1,1);

        int[][] array = new int[5][4];
        array[0] = new int[]{1, 0, 2, 2};
        array[1] = new int[]{2, 0, 1, 2};
        array[2] = new int[]{2, 1, 0, 2};
        array[3] = new int[]{2, 2, 1, 0};
        array[4] = new int[]{1, 1, 1, 2};

        List <List <Dish>> listList = DataGenerator.createDishSets(array);

        List<Dish> dishes = DataGenerator.createDishMocks(1,1,1,0);

        StringBuilder str2 = new StringBuilder();
        str2.append("Created order containing these dishes:").append("\n\n");
        for (int i = 0; i < 3; i++) {
            str2.append(dishes.get(i).getDishType()).append(", ").append(dishes.get(i).getBasePrice()).append("???").append("\n");
        }

        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(calculatePrice(guestList.get(i), dishes), construct(guestList.get(i),dishes).calculatePrice(), str2 + "\n" + "GuestType was " + list.get(i).getGuestType() + "\n" + "Incorrect price returned by calculatePrice()");
        }
        StringBuilder str = new StringBuilder();
        str.append("Created order containing these dishes:").append("\n\n");
        for (int i = 0; i < 5; i++) {

            str.append(listList.get(0).get(i).getDishType()).append(", ").append(listList.get(0).get(i).getBasePrice()).append("???").append("\n");
        }
        for (int i = 0; i < 5; i++) {

            Assertions.assertEquals(calculatePrice(list.get(i), listList.get(0)), construct(list.get(i), listList.get(0)).calculatePrice(), str.toString() + "\n" + "GuestType was " + list.get(i).getGuestType() + "\n" + "Incorrect price returned by calculatePrice()");
        }
        str = new StringBuilder();
        str.append("Created order containing these dishes:").append("\n\n");
        for (int i = 0; i < 5; i++) {

            str.append(listList.get(1).get(i).getDishType()).append(", ").append(listList.get(0).get(i).getBasePrice()).append("???").append("\n");
        }
        for (int i = 0; i < 5; i++) {

            Assertions.assertEquals(calculatePrice(list.get(i), listList.get(1)), construct(list.get(i), listList.get(1)).calculatePrice(), str.toString() + "\n" + "GuestType was " + list.get(i).getGuestType() + "\n" + "Incorrect price returned by calculatePrice()");
        }
        str = new StringBuilder();
        str.append("Created order containing these dishes:").append("\n\n");
        for (int i = 0; i < 5; i++) {

            str.append(listList.get(2).get(i).getDishType()).append(", ").append(listList.get(2).get(i).getBasePrice()).append("???").append("\n");
        }
        for (int i = 0; i < 5; i++) {

            Assertions.assertEquals(calculatePrice(list.get(i), listList.get(2)), construct(list.get(i), listList.get(2)).calculatePrice(), str.toString() + "\n" + "GuestType was " + list.get(i).getGuestType() + "\n" + "Incorrect price returned by calculatePrice()");
        }

        str = new StringBuilder();
        str.append("Created order containing these dishes:").append("\n\n");
        for (int i = 0; i < 5; i++) {

            str.append(listList.get(3).get(i).getDishType()).append(", ").append(listList.get(3).get(i).getBasePrice()).append("???").append("\n");
        }
        for (int i = 0; i < 5; i++) {

            Assertions.assertEquals(calculatePrice(list.get(i), listList.get(3)), construct(list.get(i), listList.get(3)).calculatePrice(), str.toString() + "\n" + "GuestType was " + list.get(i).getGuestType() + "\n" + "Incorrect price returned by calculatePrice()");
        }

        str = new StringBuilder();
        str.append("Created order containing these dishes:").append("\n\n");
        for (int i = 0; i < 5; i++) {

            str.append(listList.get(4).get(i).getDishType()).append(", ").append(listList.get(4).get(i).getBasePrice()).append("???").append("\n");
        }
        for (int i = 0; i < 5; i++) {

            Assertions.assertEquals(calculatePrice(list.get(i), listList.get(4)), construct(list.get(i), listList.get(4)).calculatePrice(), str.toString() + "\n" + "GuestType was " + list.get(i).getGuestType() + "\n" + "Incorrect price returned by calculatePrice()");
        }


    }

    public double calculatePrice(Guest guest, List <Dish> dishes) {


        double value = 0.0;
        double price = 0.0;

        for (Dish dish : dishes) {
            price += dish.getBasePrice();
            switch (guest.getGuestType()){
                case STUDENT -> {
                    value = 0.6;
                }
                case STAFF -> {
                    value = 0.8;
                }
                case GUEST -> {
                    value = 1.0;
                }
            }
            value = price * value;
        }
        boolean Starter = false;
        boolean Main_dish = false;
        boolean Dessert = false;

        for (Dish dish : dishes) {
            if (dish.getDishType().equals(DishType.STARTER)) {
                Starter = true;
            } else if (dish.getDishType().equals(DishType.DESSERT)) {
                Dessert = true;
            } else if (dish.getDishType().equals(DishType.MAIN_DISH)) {
                Main_dish = true;
            }

        }
        if (Starter & Main_dish & Dessert) {
            value = value * 0.8;
        }

        value = Math.round(100.0 * value) / 100.0;
        System.out.println("valueEnde :" + value);
        return value;
    }

    @Test
    public void testEquals() {


        List <Guest> list = DataGenerator.createGuestMocks(2, 1, 2);
        List <Guest> guestList2 = DataGenerator.createGuestMocks(3, 1, 1);

        List <Dish> list5 = DataGenerator.createDishMocks(1, 2, 1, 1);
        List <Dish> list1 = DataGenerator.createDishMocks(2, 1, 1, 1);
        List <Dish> list2 = DataGenerator.createDishMocks(1, 1, 1, 2);
        List <Dish> list3 = new ArrayList <>(DataGenerator.createDishMocks(1, 2, 1, 1));
        List <Dish> list4 = DataGenerator.createDishMocks(1, 1, 2, 1);


        List <List <Dish>> listList = List.of(list1, list2, list3, list4, list5);

        List <List <Dish>> reverseList = new ArrayList <>();

        for (int i = 0; i < listList.size(); i++) {
            List<Dish> listDish = new ArrayList <>(listList.get(i));
            Collections.reverse(listDish);
            reverseList.add(listDish);
        }
        List <List <Dish>> reverse = Collections.unmodifiableList(reverseList);

        System.out.println(reverse.toString());


        for (int i = 0; i < 5; i++) {


            try {
                Assertions.assertEquals(construct(list.get(i), listList.get(i)), construct(list.get(i), listList.get(i)));
            } catch (AssertionError e) {
                Assertions.fail("Two orders with identical guests and dishes should be equal");
            }

            try {
                Assertions.assertEquals(construct(list.get(i), listList.get(i)), construct(list.get(i), reverse.get(i)));
            } catch (AssertionError e) {
                Assertions.fail("Two orders with identical dishes (which may be out of order...) should be equal");
            }

            try {
                Assertions.assertNotEquals(construct(list.get(i), list5), construct(guestList2.get(i), list5));
            } catch (AssertionError e) {
                Assertions.fail("Two orders with different guests should not be equal");
            }
            try {
                Assertions.assertNotEquals(construct(list.get(i), list1), construct(list.get(i), list2));
            } catch (AssertionError e) {
                Assertions.fail("Two orders containing different dishes should not be equal");
            }
            try {
                Assertions.assertNotEquals(construct(list.get(i), list1), construct(guestList2.get(i), list2));
            } catch (AssertionError e) {
                Assertions.fail("Two orders with different guests and dishes should not be equal");
            }

        }


    }

    @Test
    public void testHashCode() {
        List <Guest> list = DataGenerator.createGuestMocks(2, 1, 2);
        int[][] array = new int[5][4];
        array[0] = new int[]{0, 1, 2, 2};
        array[1] = new int[]{2, 0, 1, 2};
        array[2] = new int[]{2, 1, 0, 2};
        array[3] = new int[]{2, 2, 1, 0};
        array[4] = new int[]{1, 1, 1, 2};

        List <List <Dish>> listList = DataGenerator.createDishSets(array);

        List <List <Dish>> reverseList = new ArrayList <>();
        List <List <Dish>> unmodifiableList = Collections.unmodifiableList(listList);
        for (int i = unmodifiableList.size(); i > 0; i--) {
            reverseList.add(unmodifiableList.get(i - 1));
        }

        List <List <Dish>> reverse = Collections.unmodifiableList(reverseList);

        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(construct(list.get(i), listList.get(i)).hashCode(), construct(list.get(i), listList.get(i)).hashCode(), "Two Orders with identical guests and dishes should have the same hash code");
            Assertions.assertEquals(construct(list.get(i), listList.get(i)).hashCode(), construct(list.get(i), reverse).hashCode(), "Two Orders with identical dishes (which may be out of order...) should have the same hash code");
        }


    }

    @Test
    public void testToString() {
        List <Guest> list = DataGenerator.createGuestMocks(2, 1, 2);
        List <Dish> dishList = DataGenerator.createDishMocks(0, 1, 1, 1);
        List <Dish> list1 = DataGenerator.createDishMocks(1, 0, 1, 1);
        List <Dish> list2 = DataGenerator.createDishMocks(1, 1, 0, 1);
        List <Dish> list3 = DataGenerator.createDishMocks(1, 1, 1, 0);
        List <Dish> list4 = DataGenerator.createDishMocks(1, 1, 1, 1);

        List <List <Dish>> listList;

        listList = List.of(list1, list2, list3, list4, dishList);
        List <Order> orderList = new ArrayList <>();
        List <String> value = new ArrayList <>();

        for (int i = 0; i < 5; i++) {
            orderList.add(construct(list.get(i), listList.get(i)));
        }

        createOrder createOrderImplements;


        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {

            createOrderImplements = new createOrder(list.get(i), listList.get(i));


            stringBuilder.append("Order:").append("\n");

            for (Dish dish : dishList) {
                stringBuilder.append("\t").append("- ").append(dish.getName()).append("\n");
            }

            stringBuilder.append("\n");

            stringBuilder.append("\t").append("Name: ").append(orderList.get(i).getGuest().getName()).append(" | Total price: ").append(orderList.get(i).calculatePrice()).append("???");

            Assertions.assertEquals(orderList.get(i).toString(), createOrderImplements.toString(), "Incorrect string representation returned by toString()");
        }

        value.add(stringBuilder.toString());



    }

}
