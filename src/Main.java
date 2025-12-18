import java.lang.reflect.Member;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        GymMember[] Member = new GymMember[1];
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
                case "6":
                    fuzzySearchByName(Member, count);
                    break;
                case "7":
                    System.out.println("Log out");
                    break Loop;
                default:
                    System.out.println("This option is not available!");
            }
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

