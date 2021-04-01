package de.uniwue.jpp.testing.interfaces;

import de.uniwue.jpp.testing.enums.DishType;

public class createDish implements Dish{

    private final String name;
    private final double basePrice;
    private final DishType type;
    public createDish(String name, double basePrice, DishType type){
        if (name == null){
            throw new IllegalArgumentException("Name must not be null!");
        }

        if (name.isEmpty()){
            throw new IllegalArgumentException("Name must not be empty!");
        }

        if (basePrice <= 0.0){
            throw new IllegalArgumentException("BasePrice must not be zero or negative!");
        }

        if (type == null){
            throw new IllegalArgumentException("DishType must not be null!");
        }

        this.name = name;
        this.basePrice = basePrice;
        this.type = type;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public DishType getDishType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof createDish)) return false;

        createDish that = (createDish) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return  name + " | Baseprice: " + basePrice + "€ | Type: " + type;
    }
}
