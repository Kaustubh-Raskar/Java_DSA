import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamPractice3FlatMap {

    public static void main(String[] args) {
        // Flatten a List of Lists
        List<List<Integer>> listOfList = List.of(
            List.of(1, 2, 3),
            List.of(4, 5),
            List.of(6, 7, 8)
        );

        List<Integer> flattened = listOfList.stream().flatMap(List::stream).collect(Collectors.toList());

        // Split sentences into words
        List<String> sentences = List.of("hello world", "java stream flatMap", "practice questions");

        List<String> words = sentences.stream().flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                                               .collect(Collectors.toList());
        

        // Get all unique characters from List of words
        List<String> words1 = List.of("apple", "banana", "grape");

        Set<Character> uniqueChars = words1.stream()
                                          .flatMap(word -> word.chars().mapToObj(c -> (char)c))
                                          .collect(Collectors.toSet());
        
        // Generate all pairs from 2 lists
        List<String> letters = List.of("a", "b");
        List<Integer> numbers = List.of(1, 2);

        List<String> pairs = letters.stream().flatMap(letter -> numbers.stream().map(num -> letter + num))
                                             .collect(Collectors.toList());

        // Count total vowels in all words
        long vowelCount = words.stream().flatMapToInt(String::chars).mapToObj(c -> (char)c).filter(ch -> "aeiouAEIOU".indexOf(ch) >= 0)
                                                                    .count();

        // Extract all Email Domains from user records
        class User {
            String name;
            List<String> emails;
            User(String name, List<String> emails) {
                this.name = name;
                this.emails = emails;
            }
        }

        List<User> users = List.of(
        new User("Alice", List.of("alice@gmail.com", "alice@work.com")),
        new User("Bob", List.of("bob@yahoo.com"))
        );

        Set<String> domains = users.stream().flatMap(user -> user.emails.stream())
                                            .map(email -> email.substring(email.indexOf("@")+1))
                                            .collect(Collectors.toSet());

        // Remove Duplicate Words from Nested Paragraphs
        List<String> paragraphs = List.of(
            "Java is powerful",
            "Java stream API is powerful and clean"
        );

        Set<String> uniqueWords = paragraphs.stream()
            .flatMap(p -> Arrays.stream(p.split(" ")))
            .map(String::toLowerCase)
            .collect(Collectors.toSet());


        // Generate all combinations of characters between 2 strings
        String s1 = "abc";
        String s2 = "xy";
        
        List<String> combinations = s1.chars().mapToObj(c1 -> (char) c1)
            .flatMap(c1 -> s2.chars().mapToObj(c2 -> "" + c1 + (char) c2))
            .collect(Collectors.toList());
    }
}
