import java.lang.reflect.Member;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        GymMember[] member = new GymMember[0];
        Loop:
        while (true) {
            System.out.println("------------------Welcome to deBugYourBody Gym!-----------------");
            System.out.println("1.Add a member");
            System.out.println("2.Delete a member");
            System.out.println("3.Update a member's details");
            System.out.println("4.Query member information");
            System.out.println("5.List all members");
            System.out.println("6.Fuzzy search members by name");
            System.out.println("7.Log out");
            System.out.println("Please enter your choice:");

            Scanner sc = new Scanner(System.in);
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    int originalLength1 = member.length;
                    member = addMember(member);
                    if (originalLength1 != member.length)
                        count++;
                    break;
                case "2":
                    int originalLength2 = member.length;
                    member = deleteMember(member);
                    if (originalLength2 != member.length)
                        count--;
                    break;
                case "3":
                    updateMember(member);
                    break;
                case "4":
                    queryMember(member);
                    break;
                case "5":
                    listAll(member, count);
                    break;
                case "6":
                    fuzzySearchByName(member, count);
                    break;
                case "7":
                    System.out.println("Log out");
                    break Loop;
                default:
                    System.out.println("This option is not available!");
            }
        }

    }
    //Add a member
    public static GymMember[] addMember(GymMember[] Member) {
        System.out.println("Please enter the ID:");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        if (findById(id, Member) != -1) {
            System.out.println("This ID is already in use. Cannot add.");
            return Member;
        }
        System.out.println("Please enter the name");
        String name = sc.nextLine();
        System.out.println("Please enter the age");
        int age = Integer.parseInt(sc.nextLine());
        System.out.println("Please enter the height");
        double height = Double.parseDouble(sc.nextLine());
        System.out.println("Please enter the weight");
        double weight = Double.parseDouble(sc.nextLine());
        Member = expandArr(Member);
        Member[Member.length - 1] = new GymMember(id, name, age, height, weight);
        System.out.println("Member added successfully");
        return Member;
    }

    //Delete a member
    public static GymMember[] deleteMember(GymMember[] Member) {
        System.out.println("Please enter the ID to delete:");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        int index = findById(id, Member);
        if (index == -1) {
            System.out.println("Member not found");
            return Member;
        }
        for (int i = index; i < Member.length-1; i++) {
            Member[i] = Member[i + 1];
        }
        Member = shortenArr(Member);
        System.out.println("Member deleted successfully");
        return Member;
    }

    //Update a member's details
    public static void updateMember(GymMember[] Member) {
        System.out.println("Please enter the ID to update:");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        int index = findById(id, Member);
        if (index == -1) {
            System.out.println("Member not found");
            return;
        }
        GymMember mem = Member[index];
        System.out.println("Please enter the name to update:");
        String newName = sc.nextLine();
        mem.setName(newName);
        System.out.println("Please enter the age to update:");
        int newAge = Integer.parseInt(sc.nextLine());
        mem.setAge(newAge);
        System.out.println("Please enter the height to update:");
        double newHeight = Double.parseDouble(sc.nextLine());
        mem.setHeight(newHeight);
        System.out.println("Please enter the weight to update:");
        double newWeight = Double.parseDouble(sc.nextLine());
        mem.setWeight(newWeight);
    }


    //Query member information
    public static void queryMember(GymMember[] Member) {
        System.out.println("Please enter the ID to query:");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        int index = findById(id,Member);
        if(index == -1){
            System.out.println("Member not found");
            return;
        }
        else{
            GymMember mem = Member[index];
            System.out.println("Member queried successfully");
            System.out.println("id\tname\tage\theight\tweight");
            System.out.println(mem.getId() + "\t" + mem.getName() + "\t\t" + mem.getAge() + "\t" + mem.getHeight() + "\t" + mem.getWeight());
        }
    }

    //List all members
    public static void listAll(GymMember[] Member, int count) {
        if (count != 0) {
            System.out.println("=========All the Members=======");
            System.out.println("id\tname\tage\theight\tweight");
            for (int i = 0; i < Member.length; i++) {
                if (Member[i] != null) {
                    GymMember mem = Member[i];
                    System.out.println(mem.getId() + "\t" + mem.getName() + "\t\t" + mem.getAge() + "\t" + mem.getHeight() + "\t" + mem.getWeight());
                }
            }
        } else
            System.out.print("Please add a member first");
    }

    //Find id
    public static int findById(String id, GymMember[] Member) {
        for (int i = 0; i < Member.length; i++) {
            if (Member[i] != null && Member[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static GymMember[] expandArr(GymMember[] arr) {
        GymMember[] newArr = new GymMember[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static GymMember[] shortenArr(GymMember[] arr) {
        GymMember[] newArr = new GymMember[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    //fuzzySearch
    public static void fuzzySearchByName(GymMember[] members, int count) {
        if (count == 0) {
            System.out.println("There are currently no members. Please add a member first!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the name keyword to search for:");
        String keyword = sc.nextLine();

        if (keyword.isEmpty()) {
            System.out.println("Keyword cannot be empty!");
            return;
        }

        System.out.println("id\tname\t\tage\theight\tweight");

        boolean found = false;
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null && members[i].getName().contains(keyword)) {
                System.out.println(members[i].getId() + "\t" + members[i].getName() + "\t\t" + members[i].getAge() + "\t" + members[i].getHeight() + "\t" + members[i].getWeight());
                found = true;
            }
        }

        if (!found) {
            System.out.println(" No name containing" + keyword);
        }
    }
}


