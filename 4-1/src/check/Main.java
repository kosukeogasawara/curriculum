package check;

public class Main {

    public static void main(String[] args) {
        String firstName = "七海";
        String lastName = "真弥";
        
        System.out.println("printNameメソッド→"+printName(firstName, lastName));
    
    
        Pet neko = new Pet("java吉", "hoge");
        neko.introduce();
    
        RobotPet rb = new RobotPet("R2D2", "ルーク");
        rb.introduce();
    }
        
        private static String printName(String firstName, String lastName) {
            return firstName + lastName;
        }
}