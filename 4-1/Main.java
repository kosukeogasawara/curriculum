package check;

import constants.Constants;

public class Main {

    public static void main(String[] args) {
        String firstName = "七海";
        String lastName = "真弥";
        
        System.out.println("printNameメソッド→"+printName(firstName, lastName));
    
    
        Pet neko = new Pet(Constants.CHECK_CLASS_JAVA, Constants.CHECK_CLASS_HOGE);
        neko.introduce();
    
        RobotPet rb = new RobotPet(Constants.CHECK_CLASS_R2D2, Constants.CHECK_CLASS_LUKE);
        rb.introduce();
    }
        
        private static String printName(String firstName, String lastName) {
            return firstName + lastName;
        }
}