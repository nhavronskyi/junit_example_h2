package org.example.junit_example_h2.user;

import org.example.junit_example_h2.Users;
import org.example.junit_example_h2.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserPhoneNumberTest {
    private static List<String> getPhoneNumbersFromUsers() {
        return Users.users.stream()
                .map(User::getPhoneNumber)
                .toList();
    }

    @ParameterizedTest
    @MethodSource("getPhoneNumbersFromUsers")
    @DisplayName("Phone number starts with '+'")
    @Tag("group")
    public void phoneNumberTest1(String phoneNumber) {
        Assertions.assertTrue(phoneNumber.startsWith("+"));
    }

    @ParameterizedTest
    @MethodSource("getPhoneNumbersFromUsers")
    @DisplayName("Phone number should have 11 numbers")
    @Tag("group")
    public void phoneNumberTest3(String phoneNumber) {
        Assertions.assertEquals(11, phoneNumber.replace("+", "").length());
    }

    @ParameterizedTest
    @MethodSource("getPhoneNumbersFromUsers")
    @DisplayName("Phone number should contains only numbers and one +")
    public void phoneNumberTest4(String phoneNumber) {
        Assertions.assertTrue(phoneNumber.contains("+"));

        var temp = phoneNumber.replace("+", "");
        Assertions.assertDoesNotThrow(() -> Long.parseLong(temp));
    }
}
