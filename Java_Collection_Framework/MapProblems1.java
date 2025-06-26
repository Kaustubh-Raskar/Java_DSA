import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.*;

public class MapProblems1 {

    static List<String> wordlist = Arrays.asList("Superman", "Batman", "Spiderman", "Ironman","Wonderwoman", "Hulk", "Robin","Robin","Robin","Batman", "Superman","Antman","Hulk");
        

    public static <K, V> Map<K, V> mergeMaps(Map<K, V> map1, Map<K, V> map2, BiFunction<V, V, V> mergeFunction) {
        Map<K, V> result = new HashMap<>(map1);
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            result.merge(entry.getKey(), entry.getValue(), mergeFunction);
        }
        return result;
    }

    public static <K, V> void removeNullValues(Map<K, V> map) {
        map.values().removeAll(Collections.singleton(null));
    }
    
    // Check if a Key exists
    public static <K,V> boolean isKeyExists(Map<K, V> map, K key){
        return map.containsKey(key);
    }

    // Get Key set
    public static <K,V> Set<K> getKeySet(Map<K,V> map){
        return map.keySet();
    }

    // Iterate over Map Entries
    public static <K,V> void iterateMap(Map<K,V> map){
        for(Map.Entry<K,V> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Replace values conditionally
    public static <K,V> void replaceValues(Map<K,V> map, V oldValue, V newValue){
        map.replaceAll((key,value) -> value.equals(oldValue)? newValue : value);
    }

    // Compute if absent
    public static <K,V> V computeIfAbsent(Map<K, V>  map, K key, Supplier<V> supplier){
        return map.computeIfAbsent(key, k -> supplier.get());
    }

    // Sort map by key
    public static <K extends Comparable<? super K>, V> Map<K,V> sortMapByKey(Map<K, V> map){
        Map<K,V> sortedMap = new TreeMap<>(map);
        return sortedMap;
    }
    
    // Merge map of lists
    private static Map<String, List<String>> mapc1 = new HashMap<>();
    private static Map<String, List<String>> mapc2 = new HashMap<>();

    public static void main(String[] args) {
            
        // 1. Count the frequency of each word
        Map<String, Long> wordFreq = wordlist.stream().collect(Collectors.groupingBy(word -> word,
                                                                                Collectors.counting()));
        System.out.println(wordFreq);

        // 2. most frequent word
        String res1 = wordlist.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                                       .entrySet().stream()
                                       .max(Map.Entry.comparingByValue())
                                       .map(Map.Entry::getKey)
                                       .orElse(null);
                                       
        System.out.println(res1);

        // Make functions which uses Map methods:
        // Merge 2 Maps
        Map<String, Integer> map1 = Map.of("a", 1, "b", 2);
        Map<String, Integer> map2 = Map.of("b", 3, "c", 4);

        Map<String, Integer> merged = mergeMaps(map1, map2, Integer::sum);
        System.out.println("Merged 2 Maps: " + merged); 

        // count frequency of chars in string
        String input = "Hello world";
        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : input.toCharArray()){
            freq.put(ch, freq.getOrDefault(ch, 0)+1);
        }

        // Find first non-repeating character
        for(Map.Entry<Character, Integer> entry : freq.entrySet()){
            if(entry.getValue() == 1){
                System.out.println("First non-repeating character.");
                break;
            }
        }

        // Map of students and marks - remove students with marks < 35
        Map<String, Integer> studentScores = new HashMap<>(Map.of("A", 90, "B", 25, "C", 84, "D", 32, "E", 60));
        studentScores.entrySet().removeIf(entry -> entry.getValue() < 35);
        System.out.println(studentScores);

        // Find duplicates in sentence
        String sentence = "hi hello hi world hello";
        String[] words = sentence.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        map.entrySet().removeIf(e -> e.getValue() < 2);
        System.out.println(map);

        // Group names by their first character
        List<String> names = List.of("Alice", "Aman", "Bob", "Brian");
        Map<Character, List<String>> grouped = new HashMap<>();

        for(String name : names){
            grouped.computeIfAbsent(name.charAt(0), k -> new ArrayList<>()).add(name);
        }
        System.out.println(grouped);

        // Compute employee bonuses if not set
        Map<String, Integer> bonusMap = new HashMap<>();
        bonusMap.putIfAbsent("John", 1200);
        bonusMap.putIfAbsent("Rocky", 1500);
        bonusMap.putIfAbsent("John", 1800);
        System.out.println(bonusMap);


        // Mutating operations
        // Convert all values to uppercase
        Map<String, String> ucmap = new HashMap<>(Map.of("k1", "hello", "k2", "world"));
        ucmap.replaceAll((k,v) -> v.toUpperCase());
        System.out.println(ucmap);

        // Incrrease each integer by 10
        Map<String, Integer> points = new HashMap<>(Map.of("x", 5, "y", 1, "z", 3));
        // points.replaceAll((k,v) -> v + 10);
        points.compute("x", (k,v) -> v + 10);
        points.compute("y", (k,v) -> v + 10);
        points.compute("z", (k,v) -> v + 10);
        System.out.println(points);

        // Merge two maps by summing values
        Map<String, Integer> mapr1 = new HashMap<>(Map.of("a", 10, "b", 20));
        Map<String, Integer> mapr2 = new HashMap<>(Map.of("a", 5, "c", 15));

        for(Map.Entry<String, Integer> e : mapr2.entrySet()){
            mapr1.merge(e.getKey(), e.getValue(), Integer::sum);
        }
        System.out.println(mapr1);

        for (Map.Entry<String, Integer> e : mapr2.entrySet()) {
            mapr1.merge(e.getKey(), e.getValue(), Integer::max);
        }
        System.out.println(mapr1); // Output: {a=10, b=25, c=5}


        mapc1.put("fruit", new ArrayList<>(List.of("apple")));
        mapc1.put("veg", new ArrayList<>(List.of("carrot")));
        mapc2.put("fruit", new ArrayList<>(List.of("banana")));
        mapc2.put("dairy", new ArrayList<>(List.of("milk")));
        for (Map.Entry<String, List<String>> e : mapc2.entrySet()) {
            mapc1.merge(e.getKey(), e.getValue(), (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            });
        }
        System.out.println(mapc1);

        // Group names by first letter
        List<String> names1 = List.of("Alice", "Adam", "Bob", "Bella", "Charlie");
        Map<Character, List<String>> grouped1 = new HashMap<>();

        for(String name : names1){
            grouped1.computeIfAbsent(name.charAt(0), k -> new ArrayList<>()).add(name);
        }
        System.out.println(grouped1);

        // Count frequency of words using merge()
        String text = "apple banana apple orange banana apple";
        String[] words1 = text.split(" ");
        Map<String, Integer> freq1 = new HashMap<>();

        for(String word : words1){
            freq1.merge(word, 1, Integer::sum);
        }
        System.out.println(freq1);

        // Replace Null Values Using computeIfAbsent
        // Initialize a value for absent keys. 
        Map<String, List<String>> courses = new HashMap<>(); 
        courses.computeIfAbsent("Math", k -> new ArrayList<>()).add("Alice");
        courses.computeIfAbsent("Math", k -> new ArrayList<>()).add("Bob");
        courses.computeIfAbsent("Physics", k -> new ArrayList<>()).add("Charlie");

        // Update scores only if present
        Map<String, Integer> scores = new HashMap<>(Map.of("John", 85, "Mary", 90));
        scores.computeIfPresent("John", (k,v) -> v + 5);
        scores.computeIfPresent("Alex", (k,v) -> v + 10); // ignored
        System.out.println(scores);

        // Truncate Long strings using replaceAll()
        Map<String,String> data = new HashMap<>(Map.of("id1", "Alexander", "id2", "Beatrice", "id3", "Max"));
        data.replaceAll((k,v) -> v.length() > 3 ? v.substring(0,3):v);

        System.out.println(data);

        //Append log entries using merge()
        Map<String, String> logs = new HashMap<>();
        logs.merge("system", "Started", (oldVal, newVal) -> oldVal + "; " + newVal);
        logs.merge("system", "Connected", (oldVal, newVal) -> oldVal + "; " + newVal);
        logs.merge("error", "NullPointer", (oldVal, newVal) -> oldVal + "; " + newVal);

        System.out.println(logs); 




    }
    
}
