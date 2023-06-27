import java.util.*;
import java.io.*;


public class Main {
    public static void option1ListAllBarsFromClosestToFurthest(int userLocation, String[][] barsInfo) {
        for (int i = 0; i < barsInfo.length; i++) {
            int temp = Integer.parseInt(barsInfo[i][4]) - userLocation;
            if (temp < 0) {
                temp = Math.abs(temp);
            }
            barsInfo[i][4] = Integer.toString(temp);
        }
        Arrays.sort(barsInfo, (a, b) -> Integer.compare(Integer.parseInt(a[4]), Integer.parseInt(b[4]))); //Sorts the array by the 3rd index(distance)
        for (int i = 0; i < barsInfo.length; i++) {
            System.out.println(String.format("%d. %s (%s - %s) - %sм",
                    i + 1, barsInfo[i][0], barsInfo[i][1], barsInfo[i][2], barsInfo[i][3]));
        }
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

        String[][] barsInfo = {{"Famous", "21.00", "03.00", "500", "500"}, {"Enjoy", "08.00", "00.00", "250", "250"},
                {"Soho", "08.00", "00.00", "400", "400"}, {"Италианския", "08.00", "00.00", "800", "800"},
                {"Приста", "10.00", "23.30", "850", "850"}, {"Милениум", "21.00", "03.00", "1300", "1300"},
                {"Капитан Блъд", "07.00", "01.00", "150", "150"}, {"Майстор Манол", "08.30", "23.45", "600", "600"},
                {"Българе", "16.00", "23.42", "950", "950"}, {"STOP Mozzarella", "10.00", "00.00", "1050", "1050"},
                {"Pizza Home", "08.00", "20.00", "1150", "1150"}, {"Бялата Къща", "07.00", "21.00", "750", "750"}};
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