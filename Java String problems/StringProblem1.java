import java.util.*;
import java.util.stream.*;
public class StringProblem1 {

    private static List<String> words = Arrays.asList("apple","banana","grape","kiwi","orange","apple","grape","banana");

    private static void removeVowels(){
        List<String> result = words.stream().map(word -> word.replaceAll("[aeiou]",""))
        .collect(Collectors.toList());
        System.out.println(result);
    }

    private static void toUppercase(){
        List<String> result = words.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(result);
    }

    private static void longestString(){
        String longestString = words.stream().max(Comparator.comparingInt(String::length)).orElse("No Strings found");
        System.out.println(longestString);
    }

    private static void startsWith(String letter){
        long count = words.stream().filter(word -> word.startsWith(letter)).count();
        System.out.printf("Number of Strings startsWith '%s' : %d%n", letter, count);
    }


    public static void main(String[] args){
        removeVowels();
        toUppercase();
        longestString();
        startsWith("a");

        // check if all strings have 3 characters:
        boolean allLongStrs = words.stream().allMatch(word -> word.length() >= 3);
        System.out.println("All strings length is more than 3 : "+ allLongStrs);

        // find first string that starts with 'g'
        Optional<String> firstG = words.stream().filter(word -> word.startsWith("g"))
                                                .findFirst();
        System.out.println(firstG.orElse("No String found"));

        // Concatenate all strings with comma delimiter
        String concatenatedString = words.stream().collect(Collectors.joining(", "));
        System.out.println(concatenatedString);
                                   
        // Remove duplicates from list of String:
        Set<String> distinctSet =words.stream().collect(Collectors.toSet());
        System.out.println(distinctSet);

        // Convert a string to list of characters
        String fruit = "apple";
        List<Character> charList = fruit.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        System.out.println(charList);
    }
}
