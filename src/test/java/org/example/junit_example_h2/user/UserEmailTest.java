package org.example.junit_example_h2.user;

import org.example.junit_example_h2.Users;
import org.example.junit_example_h2.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class UserEmailTest {

    private static List<String> getEmails() {
        return Users.users
                .stream()
                .map(User::getEmail)
                .toList();
    }

    @ParameterizedTest
    @MethodSource("getEmails")
    @DisplayName("email should contain only one @")
    public void emailTest1(String email) {
        Assertions.assertTrue(email.contains("@"));
        Assertions.assertEquals(1, Arrays.stream(email.split("")).filter(x -> x.equals("@")).count());
    }


    @ParameterizedTest
    @MethodSource("getEmails")
    @DisplayName("left and right part should not be empty")
    public void emailTest2(String email) {
        var arr = email.split("@");

        Assertions.assertNotNull(arr[0]);
        Assertions.assertNotNull(arr[1]);

        Assertions.assertFalse(arr[0].isEmpty());
        Assertions.assertFalse(arr[1].isEmpty());
    }
}
