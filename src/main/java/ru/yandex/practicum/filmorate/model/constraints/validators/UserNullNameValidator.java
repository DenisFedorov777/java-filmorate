package ru.yandex.practicum.filmorate.model.constraints.validators;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.constraints.UserNullNameConstrain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class UserNullNameValidator implements ConstraintValidator<UserNullNameConstrain, User> {


    @Override
    public boolean isValid(User obj, ConstraintValidatorContext context) {
        Field nameField = null;
        Field loginField = null;

        try {
            nameField = obj.getClass().getDeclaredField("name");
            nameField.setAccessible(true);
            String nameValue = (String) nameField.get(obj);

            if (nameValue == null || nameValue.isEmpty()) {
                loginField = obj.getClass().getDeclaredField("login");
                loginField.setAccessible(true);
                String loginValue = (String) loginField.get(obj);

                if (!loginValue.isBlank()) {
                    nameField.set(obj, loginValue);
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            if (nameField != null) {
                nameField.setAccessible(false);
            }
            if (loginField != null) {
                loginField.setAccessible(false);
            }
        }
        return true;
    }
}