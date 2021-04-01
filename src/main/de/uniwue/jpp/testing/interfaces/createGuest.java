package de.uniwue.jpp.testing.interfaces;

import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.Guest;

public class createGuest implements Guest{


    private final String name;
    private final GuestType type;
    public createGuest(String name, GuestType type) {
        if (name == null){
            throw new IllegalArgumentException("Name must not be null!");
        }
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name must not be empty!");
        }

        if (type == null){
            throw new IllegalArgumentException("GuestType must not be null!");
        }

        this.name = name;
        this.type = type;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public GuestType getGuestType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof createGuest)) return false;

        createGuest that = (createGuest) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return  name + " (" + type + ")";
    }
}
