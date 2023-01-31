package check;

public class Main {

    public static void main(String[] args) {
        String firstName = "七海";
        String lastName = "真弥";
        
        System.out.println(printName(firstName, lastName));
        System.out.println();
    
    
        Pet neko = new Pet("Contents.CHECK_CLASS_JAVA", "Contents.CHECK_CLASS_HOGE");
        neko.introduce();
    
        RobotPet rb = new RobotPet("Contents.CHECK_CLASS_R2D2", "Contents.CHECK_CLASS_LUKE");
        rb.introduce();
    }
        
        private static String printName(String firstName, String lastName) {
            return firstName + lastName;
        }
}