import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.*;

public class DayFour {

  public static void main(String[] args) throws IOException {
    long partA = Files.lines(Paths.get("day4.txt"))
        .map(line -> {
          String[] types = line.split("[:|]");

          String[] winningNumbers = types[1].split("\\s");
          String[] chosenNumbers = types[2].split("\\s");

          long y = Arrays.stream(chosenNumbers)
            .filter(chosenNumber -> chosenNumber.trim().length() > 0)
            .mapToLong(chosenNumber -> Integer.parseInt(chosenNumber))
            .filter(chosenNumber -> {
              return Arrays.stream(winningNumbers)
                .filter(x -> x.trim().length() > 0)
                .mapToLong(x -> Integer.parseInt(x))
                .anyMatch(x -> x == chosenNumber);
            })
            .count();
          
          y = (long) Math.pow(2, y - 1);

          return y;
        })
        .reduce(0L, Long::sum);
    
      long[] xyz = Files.lines(Paths.get("day4.txt"))
        .map(line -> {
          String[] types = line.split("[:|]");

          String[] winning = types[1].split("\\s");
          String[] chosenNumbers = types[2].split("\\s");

          return Arrays.stream(chosenNumbers)
            .filter(chosenNumber -> chosenNumber.trim().length() > 0)
            .mapToLong(chosenNumber -> Integer.parseInt(chosenNumber))
            .filter(chosenNumber -> {
              return Arrays.stream(winning)
                .filter(x -> x.trim().length() > 0)
                .mapToLong(x -> Integer.parseInt(x))
                .anyMatch(x -> x == chosenNumber);
            })
            .count();
          
        })
        .mapToLong(i -> i)
        .toArray();

      Map<Integer, Integer> abc = new HashMap<Integer, Integer>();

      for (int i = 0; i < xyz.length; i++) {
        abc.put(i, 1);
      }

      for (int i = 0; i < xyz.length; i++) {
        long aaa = xyz[i];
        for (int j = 0; j < aaa; j++) {
          abc.put(i + j + 1, abc.getOrDefault(i + j + 1, 0) + abc.getOrDefault(i, 1));
        }
      }

      int total = 0;

      for (int i = 0; i < xyz.length; i++) {
        
        total += abc.get(i);
      }

    System.out.println(partA);
    System.out.println(total);
  }
}
