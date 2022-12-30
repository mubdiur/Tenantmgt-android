package io.github.tenantmgt.Models;

import java.util.HashMap;
import java.util.Map;

public class Bills {
    private static Map<String, Double> bills = null;

    private Bills() {};

    public static synchronized void clear() {
        bills =  null;
    }

    public static synchronized Map<String, Double> getMap() {
        if (bills == null) {
            bills = new HashMap<>();
        }

        return bills;
    }
}
