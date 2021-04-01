package de.uniwue.jpp.testing.util;

import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.Dish;
import de.uniwue.jpp.testing.interfaces.Guest;
import de.uniwue.jpp.testing.interfaces.Order;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
public class DataGenerator {

    public static List <String> createGuestNames(int amount) {
        List <String> stringList = new ArrayList <>();

        for (int i = 1; i <= amount; i++) {
            stringList.add("Guest_" + i);
        }
        return Collections.unmodifiableList(stringList);
    }

    public static List <GuestType> createGuestTypes(int staff, int student, int guest) {
        List <GuestType> guestTypeList = new ArrayList <>();
        if (staff >= 0 && student >= 0 && guest >= 0) {
            for (int i = 1; i <= staff; i++) {
                guestTypeList.add(GuestType.STAFF);
            }
            for (int i = 1; i <= student; i++) {
                guestTypeList.add(GuestType.STUDENT);
            }
            for (int i = 1; i <= guest; i++) {
                guestTypeList.add(GuestType.GUEST);
            }
        }
        return Collections.unmodifiableList(guestTypeList);
    }

    public static List <String> createDishNames(int amount) {
        List <String> stringList = new ArrayList <>();
        for (int i = 1; i <= amount; i++) {
            stringList.add("Dish_" + i);
        }
        return Collections.unmodifiableList(stringList);
    }

    public static List <Double> createBasePrices(int amount) {
        List <Double> listPrice = new ArrayList <>();

        for (double i = 0.0; i < (double) amount; i++) {
            listPrice.add(0.5 + i / 2);
        }

        return Collections.unmodifiableList(listPrice);
    }

    public static List <DishType> createDishTypes(int main, int starter, int dessert, int other) {
        List <DishType> dishTypeList = new ArrayList <>();

        if (main >= 0 && starter >= 0 && dessert >= 0 && other >= 0) {
            for (int i = 1; i <= main; i++) {
                dishTypeList.add(DishType.MAIN_DISH);
            }
            for (int j = 1; j <= starter; j++) {
                dishTypeList.add(DishType.STARTER);
            }
            for (int k = 1; k <= dessert; k++) {
                dishTypeList.add(DishType.DESSERT);
            }
            for (int l = 1; l <= other; l++) {
                dishTypeList.add(DishType.OTHER);
            }
        }
        return Collections.unmodifiableList(dishTypeList);
    }

    public static List <Guest> createGuestMocks(int staff, int student, int guest) {
        List <Guest> guestList = new ArrayList <>();

        int count = 1;
        if (staff >= 0 && student >= 0 && guest >= 0) {
            for (int i = 1; i <= staff; i++) {
                Guest mock = Mockito.mock(Guest.class);
                Mockito.when(mock.getName()).thenReturn("Guest_" + count);
                Mockito.when(mock.getGuestType()).thenReturn(GuestType.STAFF);
                count++;
                guestList.add(mock);
            }
            for (int k = 1; k <= student; k++) {
                Guest mock = Mockito.mock(Guest.class);
                Mockito.when(mock.getName()).thenReturn("Guest_" + count);
                Mockito.when(mock.getGuestType()).thenReturn(GuestType.STUDENT);
                count++;
                guestList.add(mock);
            }
            for (int j = 1; j <= guest; j++) {
                Guest mock = Mockito.mock(Guest.class);
                Mockito.when(mock.getName()).thenReturn("Guest_" + count);
                Mockito.when(mock.getGuestType()).thenReturn(GuestType.GUEST);
                count++;
                guestList.add(mock);
            }
        }

        return Collections.unmodifiableList(guestList);
    }

    public static List <Dish> createDishMocks(int main, int starter, int dessert, int other) {
        List <Dish> dishList = new ArrayList <>();
        int count = 1;
        if (main >= 0 && starter >= 0 && dessert >= 0 && other >= 0) {
            if (main > 0) {
                for (int i = 1; i <= main; i++) {
                    Dish mock = Mockito.mock(Dish.class);
                    Mockito.when(mock.getName()).thenReturn("Dish_" + count);
                    Mockito.when(mock.getDishType()).thenReturn(DishType.MAIN_DISH);
                    count++;
                    dishList.add(mock);
                }
            }

            if (starter > 0) {
                for (int i = 1; i <= starter; i++) {
                    Dish mock = Mockito.mock(Dish.class);
                    Mockito.when(mock.getName()).thenReturn("Dish_" + count);
                    Mockito.when(mock.getDishType()).thenReturn(DishType.STARTER);
                    count++;
                    dishList.add(mock);
                }
            }

            if (dessert > 0) {
                for (int i = 1; i <= dessert; i++) {
                    Dish mock = Mockito.mock(Dish.class);
                    Mockito.when(mock.getName()).thenReturn("Dish_" + count);
                    Mockito.when(mock.getDishType()).thenReturn(DishType.DESSERT);
                    count++;
                    dishList.add(mock);
                }
            }

            if (other > 0) {
                for (int i = 1; i <= other; i++) {
                    Dish mock = Mockito.mock(Dish.class);
                    Mockito.when(mock.getName()).thenReturn("Dish_" + count);
                    Mockito.when(mock.getDishType()).thenReturn(DishType.OTHER);
                    count++;
                    dishList.add(mock);
                }
            }
        }

        List <Double> doubleList = createBasePrices(dishList.size());
        int k = 0;
        for (Dish dish:dishList) {
            Mockito.when(dish.getBasePrice()).thenReturn(doubleList.get(k++));
        }

        return Collections.unmodifiableList(dishList);
    }

    public static List <List <Dish>> createDishSets(int[][] data) {
        List <List <Dish>> listList = new ArrayList <>();
        if (data.length > 0) {
            for (int[] value : data) {
                if (value.length < 4) {
                    throw new IllegalArgumentException("Inner array to small!");
                } else {
                    List <Dish> dishList = createDishMocks(value[0], value[1], value[2], value[3]);
                    listList.add(dishList);
                }
            }
        }
        return listList;
    }

    public static Order createOrderMock(int index) {
        if (index <= 0) {
            return null;
        }
        double count = 0.0;

        Order mock = Mockito.mock(Order.class);
        List <Guest> guestList = createGuestMocks(0,0,index);
        Mockito.when(mock.getGuest()).thenReturn(guestList.get(guestList.size()-1));
        List<Dish> dishList = createDishMocks(index,0,0,0);
        Mockito.when(mock.getDishes()).thenReturn(dishList);

        for (Dish dish:mock.getDishes()) {
            count += dish.getBasePrice();
        }
        Mockito.when(mock.calculatePrice()).thenReturn(count);

        return mock;
    }

    public static void main(String[] args) {
        System.out.println(DataGenerator.createDishNames(10));
        System.out.println();
        System.out.println(DataGenerator.createGuestNames(10));
        System.out.println();
        System.out.println(Objects.requireNonNull(DataGenerator.createOrderMock(10)).getDishes().get(3));
    }
}
