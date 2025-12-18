
import java.lang.reflect.Member;
import java.util.Scanner;


public class GymMember {
    private String id;
    private String name;
    private int age;
    private double height;
    private double weight;

    public GymMember(String id,String name,int age,double height,double weight){
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public GymMember() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if(weight <= 0)
            System.out.println("Weight not valid");
        else
            this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if(height <= 0)
            System.out.println("Height not valid");
        else
            this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age <= 0)
            System.out.println("Age not valid");
        else
            this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}



