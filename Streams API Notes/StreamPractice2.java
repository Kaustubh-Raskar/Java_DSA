import java.util.*;
import java.util.stream.*;

// problems using reduce 
public class StreamPractice2 {

    static List<String> words = List.of("apple", "banana", "kiwi", "orange");
    public static void main(String[] args) {
        
        // Use of Reduce() in Stream API

        // 1. Sum of all string length
        int totalLength = words.stream().map(String::length).reduce(0, Integer::sum);
        System.out.println(totalLength);

        // longest word in list
        String longest = words.stream().reduce((w1,w2) -> w1.length() >= w2.length() ? w1 : w2).orElse(null);
        System.out.println(longest);

        // concatenate all strings
        String result = words.stream()
                                .reduce((a, b) -> a + "-" + b)
                                .orElse("");

        
    }
}
