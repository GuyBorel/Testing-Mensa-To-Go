package de.uniwue.jpp.testing.enums;

public enum GuestType implements DiscountFactorProvider{


    STUDENT("STUDENT"), STAFF("STAFF"), GUEST("GUEST");

    private final String guestTypeValue;

    GuestType(String guestTypeValue) {
        this.guestTypeValue = guestTypeValue;
    }

    @Override
    public double getDiscountFactor(){

        double value = 0.0;
        switch (guestTypeValue){
            case "STUDENT" -> {
                value = 0.6;
            }
            case "STAFF" -> {
                value = 0.8;
            }
            case "GUEST" -> {
                value = 1.0;
            }
        }
        return value;
    }
}
