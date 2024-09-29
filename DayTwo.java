import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.regex.Pattern;

public class DayTwo {
    public static void main(String[] args) throws IOException {
        Function<String, Function<String, Integer>> maxCount = color -> {
            return input -> Pattern.compile("([0-9]+) " + color)
                .matcher(input)
                .results()
                .map(l -> l.group(1))
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0);
        };

        Function<String, Integer> redMaxCount = maxCount.apply("red");
        Function<String, Integer> blueMaxCount = maxCount.apply("blue");
        Function<String, Integer> greenMaxCount = maxCount.apply("green");
        
        Function<String, String> getGameNumber = (x) -> Pattern
                .compile("Game ([0-9]*): ")
                .matcher(x)
                .results()
                .map(l -> l.group(1))
                .findFirst()
                .orElse("0");

        int partA = Files.lines(Paths.get("day2.txt"))
                .filter(l -> redMaxCount.apply(l) <= 12)
                .filter(l -> blueMaxCount.apply(l) <= 14)
                .filter(l -> greenMaxCount.apply(l) <= 13)
                .map(getGameNumber)
                .mapToInt(Integer::parseInt)
                .sum();

        int partB = Files.lines(Paths.get("day2.txt"))
                .map(l -> redMaxCount.apply(l) * blueMaxCount.apply(l) * greenMaxCount.apply(l))
                .mapToInt(l -> l)
                .sum();

        System.out.println(partA + "\n" + partB);
    }
}
