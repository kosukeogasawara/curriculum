package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Main {
    
    public static void main(String[] args) {
        {
            List<String> array = new ArrayList<>();
            array.add("hoge");
            array.add("pos");
            array.add("foo");
                
            array.set(1, "bar");
                
            System.out.println("fooのインデックス：" + 2);
        }
        
        {
            Map<String, Object> map = new HashMap<>();
            map.put("adress", "////");
            map.put("name", "kosuke");
            map.put("age", 29);
            
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("key:" + entry.getKey());
                System.out.println("value:" + entry.getValue());
            }
        }
        
        {
            Calendar cal = Calendar.getInstance();
                System.out.println(cal.get(Calendar.YEAR));
                System.out.println(cal.get(Calendar.MONTH));
                System.out.println(cal.get(Calendar.DATE));
                
                int[] arrayDate = {cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)};
                List<int[]> Date = Arrays.asList(arrayDate);
                
                for(int[] d : Date) {
                    System.out.println(Arrays.toString(d));
                }
                }
                
        }
}
