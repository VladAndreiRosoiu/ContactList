package ro.jademy.contactlist.customexceptions;

import java.util.InputMismatchException;

public class ValidateInput extends InputMismatchException {

    public ValidateInput(String message) {
        super(message);
    }
}
