import java.util.Scanner;

public class Main {
    public static void option1ListAllBarsFromClosetsToFurthest(int location) {
    }

    public static void option2ListOpenedBars(String time) {
    }

    public static void option3ShowMap(int location) {
    }

    public static boolean isTimeInCorrectFormat(String time) {
        if (time.matches("^\\d{2}\\.\\d{2}$")) { //check if the format is hh.mm
            String[] parts = time.split("\\.");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);

            if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Въведете локацията си в метри: ");
        int location = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String userLocation = sc.nextLine();
                char lastChar = userLocation.charAt(userLocation.length() - 1);
                while (true) {
                    if (!Character.isDigit(lastChar)) {
                        break;
                    } else {
                        System.out.print("Въведете дължината и съкращението слято (пример: 240m): ");
                        userLocation = sc.nextLine();
                        lastChar = userLocation.charAt(userLocation.length() - 1);
                    }
                }
                location = Integer.parseInt(userLocation.substring(0, userLocation.length() - 1));
                break;
            } catch (Exception e) {
                System.out.print("Въведете дължината и съкращението слято (пример: 240m): ");
            }
        }

        System.out.println("Изберете опция: СПИСЪК ВСИЧКИ (1), СПИСЪК ОТВОРЕНИ (2), КАРТА (3)");
        String option;
        while (true) {
            option = sc.next();
            while (true) {
                if (option.equals("1") || option.equals("2") || option.equals("3")) {
                    break;
                } else {
                    System.out.println("Въведете валидна опция (1, 2 или 3): ");
                    option = sc.next();
                }
            }
            break;
        }

        if (option.equals("1")) {
            option1ListAllBarsFromClosetsToFurthest(location);
        } else if (option.equals("2")) {
            System.out.println("Въведете желанто време: ");
            String time = sc.next();
            while (true) {
                if (isTimeInCorrectFormat(time)) {
                    break;
                }
                System.out.println("Въведете часа във валиден формат (чч.мм):");
                time = sc.next();
            }
            option2ListOpenedBars(time);
        } else {
            option3ShowMap(location);
        }
    }
}