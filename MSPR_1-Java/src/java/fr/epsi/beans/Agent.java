package fr.epsi.beans;

import java.util.Collections;
import java.util.List;

public class Agent {
    private String name;
    private String username;

    public Agent(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
