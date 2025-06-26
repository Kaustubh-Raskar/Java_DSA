import java.util.*;
import java.util.stream.*;

public class StringPractice {

        private static List<String> words = Arrays.asList("apple","banana","grape","kiwi","orange","antina","education","banana","umbrella");
        private static List<String> mixWordNums = Arrays.asList("app367le","ba123nana","gr5a6pe","k8iw9i","ora3n2ge","a1ntina","education","ban24ana","um5brella");
    
        public static void main(String[] args){
           
            Map<Character, List<String>> res1 = words.stream().collect(Collectors.groupingBy(word -> word.charAt(0)));
            System.out.println(res1);

            String longestWord = words.stream().max(Comparator.comparingInt(String::length)).orElse("No Strings found");
            System.out.println(longestWord);

            Map<Integer, List<String>> res2 = words.stream().collect(Collectors.groupingBy(String::length));
            System.out.println(res2);

            Map<String, Integer> res3 = words.stream()
            .collect(Collectors.toMap(
                word -> word,  // key: the word itself
                word -> (int) word.chars().filter(c -> "aeiouAEIOU".indexOf(c) >= 0).count(), // value: count vowels
                (existing, replacement) -> existing 
            ));
            System.out.println(res3);


            // Find the Most Frequent Word in a List
            String mostFreqString = words.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                                                  .entrySet().stream()
                                                  .max(Map.Entry.comparingByValue())
                                                  .map(Map.Entry::getKey)
                                                  .orElse(null);
            System.out.println(mostFreqString);
        

        // Create a Map of Word Frequency by Length
        Map<Integer, Map<String, Long>> resFreq = words.stream().collect(Collectors.groupingBy(
                String::length,
                Collectors.groupingBy(
                    word -> word,
                    Collectors.counting()
                )
        ));
        System.out.println(resFreq);
    
    // Find words containing a specific substring
    List<String> containsSubstr = words.stream()
                                       .filter(word -> word.contains("ple"))
                                       .collect(Collectors.toList());
    System.out.println(containsSubstr);

    // Reverse words in a sentence
    String sentence = "Hello World! How are you? ";
    String reverse = Arrays.stream(sentence.split(" "))
                           .reduce((word1, word2) -> word2 + " " + word1)
                           .orElse("");
    System.out.println(reverse);

    // Check if any word starts with vowel
    boolean resVowel = words.stream().anyMatch(word -> "aeiou".indexOf(word.charAt(0)) >= 0);
    Long wordsStartsWithVowel = words.stream().filter(word -> "aeiou".indexOf(word.charAt(0)) >= 0).count();

    System.out.println(resVowel + " count: " + wordsStartsWithVowel); 

    // Concatenate Words that End with a Specific Character
    char specChar = 'e';
    String resConcat = words.stream().filter(word -> word.endsWith(String.valueOf(specChar))).collect(Collectors.joining(" "));
    System.out.println(resConcat);

    //  Find the Word with the Most Distinct Characters
    String res5 = words.stream()
                       .max(Comparator.comparingInt(word -> (int)word.chars().distinct().count()))
                       .orElse(null);

    System.out.println(res5);

            // Filter Words with an Even Number of Characters
        List<String> evenStr = words.stream()
                                    .filter(word -> word.length() % 2 == 0)
                                    .collect(Collectors.toList());
        System.out.println(evenStr);

        // Create a Map of Character to All Words that Contain It
        Map<Character, List<String>> result = words.stream()
        .flatMap(word -> word.chars().mapToObj(c -> (char) c)) // Convert each word to a stream of characters
        .distinct()  // Ensure that each character is processed only once
        .collect(Collectors.toMap(
                c -> c, // key: the character
                c -> words.stream() // value: words that contain the character
                    .filter(word -> word.indexOf(c) >= 0)
                    .collect(Collectors.toList())
        ));
        System.out.println(result);

        // Replace All Digits with a Specific Character
        List<String> replaceDigits = mixWordNums.stream()
                                    .map(word -> word.replaceAll("[0-9]", "#"))
                                    .collect(Collectors.toList());
        System.out.println(replaceDigits);


        // Find the Common Characters Between Two Strings
        String str1 = "apple";
        String str2 = "grape";

        String commonChars = str1.chars()
                                 .filter(c -> str2.indexOf(c) >= 0)
                                 .mapToObj(c -> String.valueOf((char)c))
                                 .distinct()
                                 .collect(Collectors.joining());
        System.out.println(commonChars);

        // Map Each Word to Its Character Count 
        Map<String, Integer> result1 = words.stream().distinct().collect(Collectors.toMap(word -> word, String::length));
        System.out.println(result1);


        // Check If All Strings in a List Are Palindromes
        List<String> strList = Arrays.asList("madam","naman","apple","racecar","banana");
        boolean containsPalindromes = strList.stream().anyMatch(word -> word.equals(new StringBuilder(word).reverse().toString()));
        System.out.println(containsPalindromes);

        List<String> palindromes = strList.stream().filter(word -> word.equals(new StringBuilder(word).reverse().toString())).collect(Collectors.toList());
        System.out.println(palindromes);
    }
}
