package de.uniwue.jpp.testing.interfaces;

import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.DayManager;
import de.uniwue.jpp.testing.interfaces.Guest;
import de.uniwue.jpp.testing.interfaces.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CreateSimpleDayManager implements DayManager {

    public final LocalDate date;
    private final int numberOfTimeSlots, capacityPerSlot;
    List <Order> orders;
    Map <Integer, List <Order>> orderIntegerMap;
    List <Guest> guestList;

    public CreateSimpleDayManager(LocalDate date, int numberOfTimeSlots, int capacityPerSlot) {

        if (date == null) {
            throw new IllegalArgumentException("Date must not be null!");
        }

        if (numberOfTimeSlots <= 0) {
            throw new IllegalArgumentException("NumberOfTimeSlots must not be zero or negative!");
        }

        if (capacityPerSlot <= 0) {
            throw new IllegalArgumentException("CapacityPerSlot must not be zero or negative!");
        }

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date must not be before current date!");
        }

        this.date = date;
        this.numberOfTimeSlots = numberOfTimeSlots;
        this.capacityPerSlot = capacityPerSlot;
        this.orderIntegerMap = new HashMap <>();
        for (int i = 0; i < numberOfTimeSlots; i++) {
            orderIntegerMap.put(i, new ArrayList <>());
        }
        this.orders = new ArrayList <>();
        this.guestList = new ArrayList <>();

    }


    @Override
    public Optional <Integer> addOrder(Order order, int preferredSlot) {

        if (order == null) {
            throw new IllegalArgumentException("Order must not be null!");
        }

        if (preferredSlot > numberOfTimeSlots - 1 || preferredSlot < 0) {

            throw new IllegalArgumentException("Illegal slot index!");
        }

        if (preferredSlot == numberOfTimeSlots - 1 && orderIntegerMap.get(preferredSlot).size() >= capacityPerSlot) {
            return Optional.empty();
        }

        if (guestList.contains(order.getGuest())) {
            System.out.println("Already");
            throw new IllegalArgumentException("Illegal order: This guest has already ordered!");
        }

        if (orderIntegerMap.get(preferredSlot).size() < capacityPerSlot) {
            orderIntegerMap.get(preferredSlot).add(order);
            orders.add(order);
            guestList.add(order.getGuest());
            return Optional.of(preferredSlot);
        }

        for (int i = 0; i < numberOfTimeSlots; i++) {
            if (preferredSlot + i >= numberOfTimeSlots && preferredSlot - i < 0) {
                break;
            }

            if (preferredSlot - i >= 0 && orderIntegerMap.get(preferredSlot - i).size() < capacityPerSlot) {
                orderIntegerMap.get(preferredSlot - i).add(order);
                orders.add(order);
                guestList.add(order.getGuest());
                return Optional.of(preferredSlot - i);
            } else if (preferredSlot + i < numberOfTimeSlots && orderIntegerMap.get(preferredSlot + i).size() < capacityPerSlot) {
                orderIntegerMap.get(preferredSlot + i).add(order);
                orders.add(order);
                guestList.add(order.getGuest());
                return Optional.of(preferredSlot + i);
            }
        }
        return Optional.empty();

    }

    @Override
    public Optional <Integer> addOrder(Order order) {
        return addOrder(order, 0);
    }


    @Override
    public Collection <Order> getOrdersForSlot(int slot) {

        if (slot > numberOfTimeSlots - 1 || slot < 0) {
            throw new IllegalArgumentException("Illegal slot index!");
        }
        return Collections.unmodifiableCollection(orderIntegerMap.get(slot));
    }

    @Override
    public Collection <Order> getAllOrders() {

        return Collections.unmodifiableCollection(orders);
    }

    @Override
    public double calculateEarnings() {
        double value = 0.0;
        for (Order order : orders) {
            value += order.calculatePrice();
        }
        value = Math.round(100.0 * value) / 100.0;
        System.out.println(value);
        return value;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        String formattedString = date.format(formatter);
        return "SimpleDayManager (" + formattedString + ")";
    }

    public static void main(String[] args) {

        List<Dish> dishList = new LinkedList <>();
        Guest guest = Guest.createGuest("paul", GuestType.GUEST);
        dishList.add(Dish.createDish("test", 0.5, DishType.MAIN_DISH));
        dishList.add(Dish.createDish("test", 1.0, DishType.DESSERT));
        dishList.add(Dish.createDish("test", 1.5, DishType.DESSERT));
        dishList.add(Dish.createDish("test", 2.0, DishType.OTHER));
        dishList.add(Dish.createDish("test", 2.5, DishType.OTHER));


        System.out.println();
        for (Dish dish : dishList) {
            System.out.println(dish);
        }

        Order order = Order.createOrder(guest,dishList);
        Order order1 = Order.createOrder(guest,dishList);

        DayManager dayManager = DayManager.createSimpleDayManager(LocalDate.now(), 1, 1);

        System.out.println(dayManager.toString());


        System.out.println(dayManager.addOrder(order));
        System.out.println(dayManager.addOrder(order1));

        System.out.println(dayManager.calculateEarnings());

        System.out.println(LocalDate.now());
    }
}