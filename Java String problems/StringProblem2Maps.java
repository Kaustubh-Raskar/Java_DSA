import java.util.*;
import java.util.stream.*;

public class StringProblem2Maps {

        private static List<String> words = Arrays.asList("apple","banana","grape","kiwi","orange","apple","education","banana","umbrella");
    
        public static void main(String[] args){
           
            // Group strings by length
            Map<Integer, List<String>> groupingByLength =
            words.stream().collect(Collectors.groupingBy(String::length));
            System.out.println(groupingByLength);

            // Count occurrences of Each Character in a List of Strings
            Map<Character, Long> charCount = words.stream().flatMapToInt(String::chars).mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
            System.out.println(charCount);

            //Find the First String That Contains All Vowels
            Optional<String> res1 = words.stream().filter(word -> "aeiou".chars().allMatch(c -> word.indexOf(c) >= 0)).findFirst();
            System.out.println(res1.orElse("No Strings found"));

            // Find the most frequent character in a List of Strings
            Optional<Map.Entry<Character, Long>> mostFreqChar = words.stream().flatMapToInt(String::chars)
                                                                              .mapToObj(c -> (char) c)
                                                                              .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                                                                              .entrySet().stream()
                                                                              .max(Map.Entry.comparingByValue());
            mostFreqChar.ifPresent(entry -> 
                                            System.out.println("Most frequent character is '"+ entry.getKey() + "' with frequency: "+ entry.getValue())
                                   );

            //    Create a Map of String Lengths with Their Corresponding Words
            // Approach 1
            Map<Integer, List<String>> lengthMap1 = words.stream().collect(Collectors.groupingBy(String::length));
            System.out.println("With grouping by : "+ lengthMap1);

            // Approach 2
            Map<Integer, List<String>> lenghtMap2 = words.stream().collect(Collectors.toMap(String::length, word -> new ArrayList<>(Arrays.asList(word)),
                                                                                                            (existing,replacement) -> {
                                                                                                                existing.addAll(replacement);
                                                                                                                return existing;
                                                                                                            }));
            System.out.println(lenghtMap2);


            // Sort words by their frequency of Occurrence 
            List<String> sortedList = words.stream()
                                           .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                                           .entrySet().stream()
                                           .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                                           .map(Map.Entry::getKey)
                                           .collect(Collectors.toList());

            System.out.println(sortedList);

            // find the string with max vowl count
            Optional<String> result = words.stream().max(Comparator.comparingInt(word -> (int)word.chars().filter(c -> "aeiou".indexOf(c) >= 0).count()));
            System.out.println(result.orElse("No Stings found."));

            //  Create a Map of Initial Letter to List of Words
            Map<Character, List<String>> groupedByFirstLetter = words.stream().collect(Collectors.groupingBy(word -> word.charAt(0)));
            System.out.println(groupedByFirstLetter);

            for(Map.Entry<Character, List<String>> entry : groupedByFirstLetter.entrySet()){
                System.out.println("Letter: " + entry.getKey() + " -> words: "+ entry.getValue());
            }

            // Most frequent character in string
            String mostFreqstr = words.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                                               .entrySet().stream()
                                               .max(Map.Entry.comparingByValue())
                                               .map(Map.Entry::getKey).toString();
            System.out.println(mostFreqstr);
        }
}
