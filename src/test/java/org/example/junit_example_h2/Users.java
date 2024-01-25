package org.example.junit_example_h2;


import org.example.junit_example_h2.entity.User;

import java.util.List;

public class Users {
    public static final List<User> users = List.of(
            new User(1L, "hello", "38000000000"),
            new User(2L, "hello@", "+3800000000"),
            new User(3L, "@lalala@", "+380000s0000"),
            new User(4L, "hello@lalala", "+3800000000")
    );
}
