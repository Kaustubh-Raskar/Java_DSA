import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice1 {

        private static List<String> words = Arrays.asList("dog","cat","grape","race","racecar","banana","apple","education",
                                                            "naman","umbrella","cat","lion","light","night","day","car","grape","cat","good"
                                                            ,"listen","silent","tea","ate","bad","god","eat");
    
        public static void main(String[] args){

            // Most frequent word -
            String mostFreqStr = words.stream() .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                                                                        .entrySet().stream()
                                                                        .max(Map.Entry.comparingByValue())
                                                                        .map(Map.Entry::getKey)
                                                                        .orElse(null);
            System.out.println(mostFreqStr);

            // Word length frequency
            Map<Integer, Long> lengthFrequencyMap = words.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
            System.out.println(lengthFrequencyMap);
            
            // Anagram Grouping
            Map<String, List<String>> anagrams = words.stream()
                                                      .collect(Collectors.groupingBy(w -> w.chars().sorted()
                                                                                                   .collect(StringBuilder::new, 
                                                                                                            StringBuilder::appendCodePoint, 
                                                                                                            StringBuilder::append).toString()));
            System.out.println(anagrams);    

            // Words that share the first character
            Map<Character, List<String>> firstCharMap = words.stream().collect(Collectors.groupingBy(w -> w.charAt(0)));
            System.out.println(firstCharMap);

            // Filter and Group by length - Group words by length that contain a vowel
            Map<Integer, List<String>> vowelGrouped = words.stream().filter(w -> w.matches(".*[aeiouAEIOU].*"))
                                                                    .collect(Collectors.groupingBy(String::length));
            System.out.println(vowelGrouped);

            // Palindromic Words group
            Map<Integer, List<String>> palindromes = words.stream()
                                                          .filter(w -> new StringBuilder(w).reverse().toString().equals(w))
                                                          .collect(Collectors.groupingBy(String::length));
            System.out.println(palindromes);

            // Longest word per Starting character
            Map<Character, Optional<String>> longest = words.stream().collect(Collectors.groupingBy(
                                                                              w -> w.charAt(0),
                                                                              Collectors.maxBy(Comparator.comparingInt(String::length))
                                                                            ));

            longest.forEach((ch, optWord) -> {
                optWord.ifPresent(word -> System.out.println(ch + " -> " + word));
            });

            // Partition palindromes
            Map<Boolean, List<String>> partition = words.stream().collect(Collectors.partitioningBy(w -> new StringBuilder(w).reverse().toString().equals(w)));
            System.out.println(partition);


            // Average word length
            double avgWordLength = words.stream().collect(Collectors.averagingInt(String::length));
            System.out.println(avgWordLength);

            // Sort by Frequency then Alphabetically
            List<String> sorted = words.stream()
                                        .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                                        .entrySet().stream()
                                        .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed()
                                                .thenComparing(Map.Entry::getKey))
                                        .map(Map.Entry::getKey)
                                        .collect(Collectors.toList());
            System.out.println(sorted);

            // Top N Frequent words
            List<String> freqWords = words.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                                                   .entrySet().stream()
                                                   .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                                                   .limit(3)
                                                   .map(Map.Entry::getKey)
                                                   .collect(Collectors.toList());
            System.out.println(freqWords);
            
            // Find Duplicates
            List<String> duplicates = words.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                                                    .entrySet().stream()
                                                    .filter(entry -> entry.getValue() > 1)
                                                    .map(Map.Entry::getKey)
                                                    .collect(Collectors.toList());
            System.out.println(duplicates);
        
            // Map word to set of Unique characters
            Map<String, Set<Character>> charMap = words.stream().distinct().collect(Collectors.toMap(w -> w, w -> w.chars().mapToObj(c -> (char)c).collect(Collectors.toSet())));
            charMap.forEach((key, val) -> System.out.println(key + " : " + val));
        
            // Remove duplicates while preserving order
            List<String> unique = words.stream().distinct().collect(Collectors.toList());
            System.out.println(unique);
            
            // Group words by sorted anagrams and sort each group by length
            List<String> Words2 = List.of("listen", "silent", "enlist", "inlets", "rat", "tar", "art");

            Map<String, List<String>> anagramsSorted = Words2.stream().collect(Collectors.groupingBy(
                                                                                        word -> word.chars().sorted()
                                                                                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                                                                        .toString(),
                                                                            Collectors.collectingAndThen(
                                                                                Collectors.toList(),
                                                                                list -> list.stream()
                                                                                            .sorted(Comparator.comparingInt(String::length))
                                                                                            .collect(Collectors.toList())
                                                                            )
                                                                        ));
            System.out.println(anagramsSorted);

            // Examples of using CollectingAndThen
            //  Wrap Result in a Custom Wrapper
            class ResultWrapper<T> {
                        T data;
                        ResultWrapper(T data) { this.data = data; }
                    }

            ResultWrapper<List<String>> result5 = Stream.of("a", "b", "c")
                .collect(Collectors.collectingAndThen(
                    Collectors.toList(),
                    ResultWrapper::new
                ));

            // Make Unmodifiable List
            List<String> unmodifiableList = Stream.of("apple", "banana", "cherry")
                                                .collect(Collectors.collectingAndThen(
                                                    Collectors.toList(),
                                                    Collections::unmodifiableList
                                                ));

            // Get Max element directly
            String longestWord = Stream.of("cat", "elephant", "dog")
                                    .collect(Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(String::length)),
                                        Optional::get
                                    ));
        }

                             
}
