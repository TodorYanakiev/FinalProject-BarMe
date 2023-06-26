import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void option1ListAllBarsFromClosestToFurthest(int location, String[][] barsInfo) {

    }

    public static void option2ListOpenedBars(String time, String[][] barsInfo) {
    }

    public static void option3ShowMap(int location, String[][] barsInfo) {
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

        String[][] barsInfo = {{"Famous", "21.00", "03.00", "150"}, {"Enjoy", "08.00", "00.00", "250"}, {"Soho", "08.00", "00.00", "400"},
                {"Италианския", "08.00", "00.00", "500"}, {"Приста", "10.00", "23.30", "600"}, {"Милениум", "21.00", "03.00", "750"},
                {"Капитан Блъд", "07.00", "01.00", "800"}, {"Майстор Манол", "08.30", "23.45", "850"}, {"Българе", "16.00", "23.42", "950"},
                {"STOP Mozzarella", "10.00", "00.00", "1050"}, {"Pizza Home", "08.00", "20.00", "1150"}, {"Бялата Къща", "07.00", "21.00", "1300"}};
        if (option.equals("1")) {
            option1ListAllBarsFromClosestToFurthest(location, barsInfo);
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
            option2ListOpenedBars(time, barsInfo);
        } else {
            option3ShowMap(location, barsInfo);
        }
    }
}