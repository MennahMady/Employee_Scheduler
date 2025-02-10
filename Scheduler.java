package Employee_Scheduler;
import java.util.*;

public class Scheduler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        Map<String, List<String>> shifts = new LinkedHashMap<>(); // Stores shifts per day

        // Get the number of employees
        System.out.print("Enter the number of employees: ");
        int numEmployees = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Collect employee details
        for (int i = 0; i < numEmployees; i++) {
            System.out.print("Enter name for employee " + (i + 1) + ": ");
            String name = scanner.nextLine();
            
            System.out.print("Enter rating for " + name + " (higher is better): ");
            int rating = scanner.nextInt();
            scanner.nextLine(); // Consume newline after nextInt()
            
            System.out.print("Enter max weekly hours for " + name + ": ");
            int maxHours = scanner.nextInt();
            scanner.nextLine(); // Consume newline after nextInt()
            
            Map<String, List<String>> availability = new HashMap<>();
            System.out.println("Enter availability for each day (e.g., Monday: 9AM-12PM,2PM-5PM)");
            for (String day : Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")) {
                System.out.print(day + " availability (comma-separated time slots, or 'none' if unavailable): ");
                String input = scanner.nextLine();
                if (!input.equalsIgnoreCase("none")) {
                    availability.put(day, Arrays.asList(input.split(",")));
                }
            }
            
            // Add employee to the list
            employees.add(new Employee(name, rating, maxHours, availability));
        }

        // Get required shifts for each day
        System.out.println("Enter required shifts for each day (e.g., Monday: 9AM-12PM,2PM-5PM)");
        for (String day : Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")) {
            System.out.print(day + " required shifts (comma-separated time slots, or 'none' if no shifts needed): ");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("none")) {
                shifts.put(day, Arrays.asList(input.split(",")));
            }
        }

        // Assign shifts to employees
        Map<String, Map<String, String>> schedule = new LinkedHashMap<>();
        
        for (String day : shifts.keySet()) {
            for (String shift : shifts.get(day)) {
                for (Employee emp : employees) {
                    if (emp.availability.containsKey(day) && emp.availability.get(day).contains(shift) && emp.assignedHours < emp.maxHours) {
                        schedule.computeIfAbsent(day, k -> new LinkedHashMap<>()).put(shift, emp.name);
                        emp.assignedHours += 1; // Increment assigned hours (assumes 1-hour shift)
                        break; // Assign only one employee per shift
                    }
                }



            }
        }

        // Print the final generated schedule
        System.out.println("\nGenerated Weekly Schedule:");
        for (String day : schedule.keySet()) {
            System.out.println(day + ":");
            for (Map.Entry<String, String> entry : schedule.get(day).entrySet()) {
                System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
