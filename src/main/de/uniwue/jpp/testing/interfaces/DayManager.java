package de.uniwue.jpp.testing.interfaces;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface DayManager {

    public Optional<Integer> addOrder(Order order, int preferredSlot);

    public Optional<Integer> addOrder(Order order);

    public Collection<Order> getOrdersForSlot(int slot);

    public Collection<Order> getAllOrders();

    public double calculateEarnings();

    public static DayManager createSimpleDayManager(LocalDate date, int numberOfTimeSlots, int capacityPerSlot){
        return new CreateSimpleDayManager(date, numberOfTimeSlots, capacityPerSlot);
    }
}
