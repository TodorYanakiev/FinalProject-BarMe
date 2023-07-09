import java.time.LocalTime;
import java.util.*;

import static java.lang.CharSequence.compare;

public class Main {
    public static void option1ListAllBarsFromClosestToFurthest(int userLocation, String[][] barsInfo) {
        //Finds the distance between the user and every bar
        for (int i = 0; i < barsInfo.length; i++) {
            int distance = Integer.parseInt(barsInfo[i][3]) - userLocation;
            if (distance < 0) {
                distance = Math.abs(distance);
            }
            barsInfo[i][3] = Integer.toString(distance);
        }

        Arrays.sort(barsInfo, (a, b) -> Integer.compare(Integer.parseInt(a[3]), Integer.parseInt(b[3]))); //index 3 is for distance
        for (int i = 0; i < barsInfo.length; i++) {
            System.out.println(String.format("%d. %s (%s - %s) - %sм",
                    i + 1, barsInfo[i][0], barsInfo[i][1], barsInfo[i][2], barsInfo[i][3]));
        }
    }

    public static void option2ListOpenBars(String time, String[][] barsInfo) {
        barsInfo = sortBars(barsInfo, time);
        LocalTime userTime = LocalTime.parse(time);
        int barNum = 1;
        for (int i = 0; i < barsInfo.length; i++) {
            LocalTime openingTime = LocalTime.parse(barsInfo[i][1]);
            LocalTime closingTime = LocalTime.parse(barsInfo[i][2]);
            if (isBarOpen(userTime, openingTime, closingTime)) {
                System.out.println(String.format("%d. %s (%s - %s)", barNum, barsInfo[i][0], barsInfo[i][1], barsInfo[i][2]));
                barNum++;
            }
        }
    }

    public static String[][] sortBars(String[][] barsInfo, String time) {
        LocalTime userTime = LocalTime.parse(time);
        for (int i = 0; i < barsInfo.length; i++) {
            LocalTime closingTime = LocalTime.parse(barsInfo[i][2]);
            int closingHours = Integer.parseInt(barsInfo[i][2].substring(0, 2));
            int closingMinutes = Integer.parseInt(barsInfo[i][2].substring(3));
            int userHours = Integer.parseInt(time.substring(0, 2));
            int userMinutes = Integer.parseInt(time.substring(3));
            int timeDifference = 0;
            if (closingTime.isBefore(userTime)) {
                if (userMinutes == 0) {
                    timeDifference += (24 - userHours) * 60 + closingHours * 60 + closingMinutes;
                } else {
                    timeDifference += (24 - userHours) * 60 - (60 - userMinutes) + closingHours * 60 + closingMinutes;
                }
            } else {
                timeDifference += (closingHours - userHours) * 60 + closingMinutes - userMinutes;
            }
            barsInfo[i][5] = Integer.toString(timeDifference);
        }
        Arrays.sort(barsInfo, (a, b) -> Integer.compare(Integer.parseInt(a[5]), Integer.parseInt(b[5])));
        return barsInfo;
    }

    public static boolean isBarOpen(LocalTime userTime, LocalTime openingTime, LocalTime closingTime) {
        if (closingTime.isBefore(openingTime)) {
            if (userTime.isAfter(openingTime) || userTime.isBefore(closingTime) ||
                    userTime.equals(closingTime) || userTime.equals(openingTime)) return true;

        } else {
            if (userTime.isAfter(openingTime) && userTime.isBefore(closingTime) ||
                    userTime.equals(closingTime) || userTime.equals(openingTime)) return true;
        }
        return false;
    }

    public static void option3ShowMap(int userLocation, String[][] barsInfo) {
        Arrays.sort(barsInfo, (a, b) -> Integer.compare(Integer.parseInt(a[4]), Integer.parseInt(b[4])));
        int distance;
        if (userLocation > Integer.parseInt(barsInfo[barsInfo.length - 1][4])) {
            distance = userLocation / 50;
        } else {
            distance = Integer.parseInt(barsInfo[barsInfo.length - 1][4]) / 50;
        }

        ArrayList<String> barsMap = new ArrayList<>();
        for (int i = 0; i < distance; i++) {
            barsMap.add("_");
        }

        barsMap.add(userLocation / 50, "X");

        for (int i = 0; i < barsInfo.length; i++) {
            if (userLocation / 50 > Integer.parseInt(barsInfo[i][4]) / 50) {
                barsMap.add(Integer.parseInt(barsInfo[i][4]) / 50 + i, Integer.toString(i + 1));
            } else {
                barsMap.add(Integer.parseInt(barsInfo[i][4]) / 50 + i + 1, Integer.toString(i + 1));
            }
        }

        System.out.println("Всяко \'_\' отговаря на 50 метра.");
        int size = barsMap.size();
        for (int i = 0; i < size; i++) {
            System.out.print(barsMap.get(i));
        }
        System.out.println();
        for (int i = 0; i < barsInfo.length; i++) {
            System.out.println(i + 1 + ". " + barsInfo[i][0]);
        }
    }

    public static boolean isTimeInCorrectFormat(String time) {
        if (time.matches("^\\d{2}\\:\\d{2}$")) { //check if the format is hh:mm
            String[] parts = time.split("\\:");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);

            if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59) {
                return true;
            }
        }
        return false;
    }

    public static int getUserLocation() {
        System.out.println("Въведете локацията си в метри: ");
        int location = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String userLocation = sc.nextLine();
                char lastChar = userLocation.charAt(userLocation.length() - 1);
                while (true) {
                    location = Integer.parseInt(userLocation.substring(0, userLocation.length() - 1));
                    if (location < 0) {
                        System.out.println("Локацията трябва да е положително число! Въведете наново:");
                        userLocation = sc.nextLine();
                        lastChar = userLocation.charAt(userLocation.length() - 1);
                        continue;
                    }
                    if (!Character.isDigit(lastChar)) {
                        break;
                    } else {
                        System.out.print("Въведете дължината и съкращението слято (пример: 240m): ");
                        userLocation = sc.nextLine();
                        lastChar = userLocation.charAt(userLocation.length() - 1);
                    }
                }
                break;
            } catch (Exception e) {
                System.out.print("Въведете дължината и съкращението слято (пример: 240m): ");
            }
        }
        return location;
    }

    public static String getUserOption() {
        System.out.println("Изберете опция: СПИСЪК ВСИЧКИ (1), СПИСЪК ОТВОРЕНИ (2), КАРТА (3), ЗАТВОРИ (4)");
        String option;
        Scanner sc = new Scanner(System.in);
        while (true) {
            option = sc.next();
            while (true) {
                if (option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")) {
                    break;
                } else {
                    System.out.println("Въведете валидна опция (1, 2 или 3): ");
                    option = sc.next();
                }
            }
            break;
        }
        return option;
    }

    public static void executeOptions(String[][] barsInfo, String option, int location) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (option.equals("4")) {
                System.out.println("Затворихте Bar me!");
                break;
            }
            if (option.equals("1")) {
                option1ListAllBarsFromClosestToFurthest(location, barsInfo);
            } else if (option.equals("2")) {
                System.out.println("Въведете желанто време: ");
                String time = sc.next();
                while (true) {
                    if (isTimeInCorrectFormat(time)) {
                        break;
                    }
                    System.out.println("Въведете часа във валиден формат (чч:мм):");
                    time = sc.next();
                }
                option2ListOpenBars(time, barsInfo);
            } else {
                option3ShowMap(location, barsInfo);
            }
            System.out.println();
            location = getUserLocation();
            option = getUserOption();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int location = getUserLocation();
        String option = getUserOption();

        String[][] barsInfo = {{"Famous", "21:00", "03:00", "500", "500", "0"}, {"Enjoy", "08:00", "00:00", "250", "250", "0"},
                {"Soho", "08:00", "00:00", "400", "400", "0"}, {"Италианския", "08:00", "00:00", "800", "800", "0"},
                {"Приста", "10:00", "23:30", "850", "850", "0"}, {"Милениум", "21:00", "03:00", "1300", "1300", "0"},
                {"Капитан Блъд", "07:00", "01:00", "150", "150", "0"}, {"Майстор Манол", "08:30", "23:45", "600", "600", "0"},
                {"Българе", "16:00", "23:42", "950", "950", "0"}, {"STOP Mozzarella", "10:00", "00:00", "1050", "1050", "0"},
                {"Pizza Home", "08:00", "20:00", "1150", "1150", "0"}, {"Бялата Къща", "07:00", "21:00", "750", "750", "0"}};

        executeOptions(barsInfo, option, location);
    }
}
