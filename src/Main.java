import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        HashSet<String> hashSet = new HashSet<>();

        hashSet.add("Alish");

        hashSet.add(null);

        MyHashSet<String> myHashSet = new MyHashSet<>();
        myHashSet.add("Alish");
        myHashSet.add("Alish");
        myHashSet.add("Kalish");
        myHashSet.add(null);
        System.out.println("myHashSet.remove(\"Alish\") = " + myHashSet.remove("Alish"));
        System.out.println("myHashSet.toArray() = " + Arrays.toString(myHashSet.toArray()));
        System.out.println(myHashSet);

    }
}
