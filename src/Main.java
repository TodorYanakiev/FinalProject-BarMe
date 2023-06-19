import java.util.Scanner;

public class Main {
    public static void option1ListAllBarsFromClosetsToFurthest(int location) {

    }

    public static void option2ListOpenedBars(String time) {

    }

    public static void option3ShowMap(int location) {

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
        int option;
        while (true) {
            try {
                option = sc.nextInt();
                while (true) {
                    if (option == 1 || option == 2 || option == 3) {
                        break;
                    } else {
                        System.out.println("Въведете валидна опция (1, 2 или 3): ");
                        option = sc.nextInt();
                    }
                }
                break;
            } catch (Exception e) {
                System.out.print("Въведете валидна опция (1, 2 или 3): ");
            }
        }

        if (option == 1) {
            option1ListAllBarsFromClosetsToFurthest(location);
        } else if (option == 2) {
            System.out.println("Въведете желанто време: ");
            String time = sc.next();
            //TODO make all inputs working
            option2ListOpenedBars(time);
        } else {
            option3ShowMap(location);
        }
    }
}