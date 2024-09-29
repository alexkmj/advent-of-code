import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DayOne {
  public static void main(String[] args) throws IOException {

    Function<String, String> parseStringToNumber = x -> {
      BiFunction<BiFunction, String, String> parseStringToNumberHelper = (f, y) -> {
        List<String> numbers = Arrays.asList(";", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

        return y.length() < 1
            ? ""
            : numbers.stream()
                .anyMatch(val -> y.startsWith(val))
                    ? numbers.indexOf(numbers.stream()
                        .filter(val -> y.startsWith(val))
                        .findFirst()
                        .orElse(""))
                        + f.apply(f, y.substring(1))
                        .toString()
                    : y.charAt(0)
                        + f.apply(f, y.substring(1)).toString();
      };

      return parseStringToNumberHelper.apply(parseStringToNumberHelper, x);
    };

    int partA = Files.lines(Paths.get("day1.txt"))
        .map(line -> line.chars()
            .mapToObj(c -> (char) c)
            .filter(c -> c >= '0' && c <= '9')
            .map(Object::toString)
            .collect(Collectors.joining()))
        .map(l -> new StringBuilder()
            .append(l.charAt(0))
            .append(l.charAt(l.length() - 1))
            .toString())
        .mapToInt(Integer::parseInt)
        .sum();

    int partB = Files.lines(Paths.get("day1.txt"))
        .map(parseStringToNumber)
        .map(line -> line.chars()
            .mapToObj(c -> (char) c)
            .filter(c -> c >= '0' && c <= '9')
            .map(Object::toString)
            .collect(Collectors.joining()))
        .map(l -> new StringBuilder()
            .append(l.charAt(0))
            .append(l.charAt(l.length() - 1))
            .toString())
        .mapToInt(Integer::parseInt)
        .sum();

    System.out.println(partA + "\n" + partB);
  }
}
