
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;

public class MyTool {

    public static final Scanner sc = new Scanner(System.in);
    
    public static List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeFile(String filename, List<String> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String item : list) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static String readPattern(String message, String pattern) {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean isValid;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            isValid = input.matches(pattern) && !input.equals("ID0000");
            if (!isValid) {
                System.out.println(" [ Invalid input format. Please try again. ] ");
            }
        } while (!isValid);
        return input;
    }

    public static String readOptionalBlank(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print(" -> This file cannot be empty, try again. ");
            }
        } while (input.isEmpty());
        return input;
    }

    public static String readValidEventName(String message) {
        String pattern = "^[^\\s]{5,}$"; // At least 5 characters, no spaces
        return readPattern(message, pattern);
    }

    public static int readInt(String message) {
        int value;
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.print(" -> The quantity must greater than 0. Please enter again: ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print(" [ Invalid input ] ");
            }
        }
        return value;
    }

    public static Integer readOptionalInt(String message) {
        Integer value = null;
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }
            try {
                value = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.print(" ->Invalid input. Please enter a valid integer or leave it blank to skip: ");
            }
        }
        return value;
    }

    public static String realAlphaString(String message) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            valid = input.matches("[a-zA-Z ]+");
            if (!valid) {
                System.out.println(" -> Invalid input. Please enter again: ");
            }
        } while (!valid);
        return input;
    }

    public static String readOptionalAlphaString(String message) {
        String input;
        boolean valid;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return input;
            }
            valid = input.matches("[a-zA-Z]{5,}$");
            if (!valid) {
                System.out.println(" -> Invalid input. Please enter a string with alphabetic characters only, no spaces, and at least 5 characters long:  ");
            }
        } while (!valid);
        return input;
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1) {
            return false;
        }
        switch (month) {
            case 2:
                if (isLeapYear(year)) {
                    return day <= 29;
                } else {
                    return day <= 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return day <= 30;
            default:
                return day <= 31;
        }
    }

    public static LocalDate readValidDate(String message, String dateFormat, boolean optional) {
        LocalDate date = null;
        boolean isValid = false;
        do {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty() && optional) {
                isValid = true;
            } else {
                try {
                    date = LocalDate.parse(input, DateTimeFormatter.ofPattern(dateFormat));
                    if (isValidDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear())) {
                        isValid = true;
                    } else {
                        System.out.println(" [ Invalid date. Please enter a valid date. ] ");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println(" [ Invalid format. Enter again or leave it empty ] ");
                }
            }
        } while (!isValid);
        return date;
    }
    
    

    public static LocalDate readValidDateNotEmpty(String message, String dateFormat) {
        LocalDate date = null;
        boolean isValid = false;
        do {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print("Date cannot be empty. ");
            } else {
                try {
                    date = LocalDate.parse(input, DateTimeFormatter.ofPattern(dateFormat));
                    if (isValidDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear())) {
                        isValid = true;
                    } else {
                        System.out.print(" [ Invalid date. Please enter a valid date. ] ");
                    }
                } catch (DateTimeParseException e) {
                    System.out.print(" [Invalid date format] ");
                }
            }
        } while (!isValid);
        return date;
    }

    public static <T> void updateIfNotNullOrEmpty(T value, Consumer<T> updater) {
        if (value != null && !value.toString().trim().isEmpty()) {
            updater.accept(value);
        }
    }
    
    public static Status readStatus(String message) {
        while (true) {
            String input = MyTool.readPattern(message, "[01]");
            try {
                int statusCode = Integer.parseInt(input);
                switch (statusCode) {
                    case 1:
                        return Status.AVAILABLE;
                    case 0:
                        return Status.NOT_AVAILABLE;
                    default:
                        System.out.println("Invalid status code. Please enter '1' for Available or '0' for Not Available.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" [Invalid input] ");
            }
        }
    }

    public static Status readOptionlStatus(String message) {
        while (true) {
            String input = MyTool.readPattern(message, "^[01 ]*$"); 
            if (input.isEmpty()) {
                return null;
            }
            try {
                int statusCode = Integer.parseInt(input);
                switch (statusCode) {
                    case 1:
                        return Status.AVAILABLE;
                    case 0:
                        return Status.NOT_AVAILABLE;
                    default:
                        System.out.println("Invalid status code. Please enter '1' for Available or '0' for Not Available.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" [Invalid input] ");
            }
        }
    }

}
