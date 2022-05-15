package exceptions;

import javafx.beans.property.StringProperty;

public class WrongLoginException extends Exception{
	/**
	 * Constructor of an exception which just specifies the problem
	 * @param stringProperty
	 */
    public WrongLoginException(StringProperty stringProperty) {
        super("Wrong credentials provided");
        stringProperty.setValue("This is bad");
    }
}
