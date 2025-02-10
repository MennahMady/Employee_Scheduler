package Employee_Scheduler;
import java.util.*;

// Class representing an employee
class Employee {
    String name; // Employee name
    int rating; // Employee rating (higher rating means higher priority)
    int maxHours; // Maximum hours an employee can work per week
    int assignedHours = 0; // Hours assigned to the employee
    Map<String, List<String>> availability; // Availability per day with time slots

    // Constructor to initialize an employee
    public Employee(String name, int rating, int maxHours, Map<String, List<String>> availability) {
        this.name = name;
        this.rating = rating;
        this.maxHours = maxHours;
        this.availability = availability;
    }
}