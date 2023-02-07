package functionalProgramming;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class NumberOfWords {

    public static void main(String[] args) {

        Map<String, Integer> wordsMap = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текст : ");
        String str = scanner.nextLine();
        String[] words = str.split(" ");

        for (String word : words) {
//            System.out.println(word);
            if(!wordsMap.containsKey(word)) {
                wordsMap.put(word,1);
            } else {
                wordsMap.put(word,wordsMap.get(word) + 1);
            }
        }
        for (Map.Entry<String, Integer> item: wordsMap.entrySet()) {
                System.out.printf("%d --  %s\n", item.getValue(), item.getKey());
        }
        System.out.println();
        System.out.println("ТОП10:");

        wordsMap.entrySet().stream().limit(10).sorted(Map.Entry.<String, Integer>comparingByValue()
                .reversed().thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry -> System.out.println(entry.getValue() + " --- " + entry.getKey()));
    }


}
