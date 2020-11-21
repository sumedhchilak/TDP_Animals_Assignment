import java.util.Scanner;
import java.util.*;
class Animal{
    String name;
    String bday;
    String color;
    String species;
    // Constructor initializes animal object with the required fields
    Animal(String name, String bday, String color, String species){
        this.name = name;
        this.bday = bday;
        this.color = color;
        this.species = species;
    }
}
public class Info {
    // keeps track of count/index
    static int animalInd = 0;
    static Map<String, String> sounds = new HashMap<>();
    static {
        sounds.put("dog", "bark");
        sounds.put("cat", "meow");
        sounds.put("sheep", "baa");
    }
    public static void main(String[] args){
        // Create and initialize Scanner
        Scanner sc = new Scanner(System.in);
        //Array Data Structure of Animal object to store 100 required input
        final int CAPACITY = 100;
        Animal[] mals = new Animal[CAPACITY];
        boolean cont = true;
        while(cont){
            String response = sc.nextLine();
            // Makes sure to exit loop if no repsonse
            if(response.equals("")){
                cont = false;
            } else{
                String[] values = response.split(",");
                mals[animalInd] = new Animal(values[0], values[1], values[2], values[3]);
                animalInd++;
            }
        }
        // Gets the most common Animal
        String mCAnimal = mostCommonAnimal(mals);
        // Gets the relative sound from hashmap
        String mCSound = sounds.get(mCAnimal);
        // Gets the oldest animal from the group of most common animal
        int oldIndex = oldestCommonAnimal(mals, mCAnimal);
        //Print resulting output
        System.out.println(mals[oldIndex].name + " the "  + mals[oldIndex].color + " " + mCAnimal + " says " + mCSound);
    }
    /*
        Helper Method
        Args: mals array of Animal objects
        Purpose: To get the most common Animal species from the input
    */
    public static String mostCommonAnimal(Animal[] mals){
        int dogCount = 0;
        int catCount = 0;
        int sheepCount = 0;
        // Keeps track of the count for each animal species type
        for(int i = 0; i < animalInd; i++){
            if(mals[i].species.equals("dog")){
                dogCount++;
            } else if(mals[i].species.equals("cat")){
                catCount++;
            } else if(mals[i].species.equals("sheep")){
                sheepCount++;
            }
        }
        // retrieves the max and returns corresponding match
        int max = Math.max(dogCount, Math.max(catCount, sheepCount));
        if(max == dogCount){
            return "dog";
        } else if(max == catCount){
            return "cat";
        } else if(max == sheepCount){
            return "sheep";
        }
        return "";
    }
    /*
        Helper Method
        Args: mals array of Animal objects, most common animal string
        Purpose: To retrieve the oldest animal from the group of the most common animals
    */
    public static int oldestCommonAnimal(Animal[] mals, String common){
        String[] start = mals[0].bday.split("/");
        int oldIndex = 0;
        int oldDay = Integer.parseInt(start[0]);
        int oldMonth = Integer.parseInt(start[1]);
        int oldYear = Integer.parseInt(start[2]);
        // loop through to find oldest from group
        for(int i = 1; i < animalInd; i++){
            if(mals[i].species.equals(common)){
                String[] date = mals[i].bday.split("/");
                int currDay = Integer.parseInt(date[0]);
                int currMonth = Integer.parseInt(date[1]);
                int currYear = Integer.parseInt(date[2]);
                // Checks and finds the oldest date
                if(currYear < oldYear){
                    oldYear = currYear;
                    oldMonth = currMonth;
                    oldDay = currDay;
                    oldIndex = i;
                } else if (currYear == oldYear){
                    if(currMonth < oldMonth){
                        oldYear = currYear;
                        oldMonth = currMonth;
                        oldDay = currDay;
                        oldIndex = i;
                    } else if(currMonth == oldMonth){
                        if(currDay < oldDay){
                            oldYear = currYear;
                            oldMonth = currMonth;
                            oldDay = currDay;
                            oldIndex = i;
                        }
                    }
                } 
            }
        }
        return oldIndex;
    }
}
