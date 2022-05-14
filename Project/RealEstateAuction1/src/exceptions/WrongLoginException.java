package exceptions;

import javafx.beans.property.StringProperty;

public class WrongLoginException extends Exception{
    public WrongLoginException(StringProperty stringProperty) {
        super("Wrong credentials provided");
        stringProperty.setValue("This is bad");
    }
}
