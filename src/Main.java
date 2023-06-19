import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Въведете локацията си в метри: ");
        int location = 0;
        Scanner sc = new Scanner(System.in);
        try{
            String userLocation = sc.nextLine();
            location = Integer.parseInt(userLocation.substring(0,userLocation.length()-1));
        }catch(Exception e){
            System.out.println("Въведете дължината и съкращението слято(пример: 240m)!");
            System. exit(0);
        }
    }
}