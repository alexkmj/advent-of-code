import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class DayThree {
    public static void main(String[] args) throws IOException {
        int rowLength = 140;

        String input = Files.lines(Paths.get("day3.txt"))
            .map(line -> line.chars()
                    .mapToObj(c -> (char) c)
                    .map(c -> c == '.' || (c >= '0' && c <= '9')
                            ? c
                            : '*'))
            .flatMap(c -> c)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        String same = IntStream.range(0, rowLength + 1)
                .mapToObj(x -> '.')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String x = same + input;
        String y = input + same;

        String leftTop = x.substring(0, x.length() - rowLength);
        String top = x.substring(1, x.length() - rowLength) + '.';
        String rightTop = x.substring(2, x.length() - rowLength) + "..";

        String left = '.' + input.substring(0, input.length() - 1);
        String right = input.substring(1) + '.';

        String leftDown = y.substring(rowLength - 1);
        String down = y.substring(rowLength) + '.';
        String rightDown = y.substring(rowLength + 1) + "..";

        String result = IntStream.range(0, input.length())
            .mapToObj(i -> {
                return input.charAt(i) == '.' || input.charAt(i) == '%'
                    ? 'F'
                    : leftTop.charAt(i) == '*'
                        || top.charAt(i) == '*'
                        || rightTop.charAt(i) == '*'
                        || left.charAt(i) == '*'
                        || right.charAt(i) == '*'
                        || leftDown.charAt(i) == '*'
                        || down.charAt(i) == '*'
                        || rightDown.charAt(i) == '*'
                            ? 'T'
                            : 'F';
            })
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
        
        int total = 0;
        int currentNumber = 0;
        boolean flag = false;
        for (int i = 0; i < result.length(); i++) {
            if (input.charAt(i) == '.' || input.charAt(i) == '*') {
                if (flag) {
                    total = total + currentNumber;
                    flag = false;
                }
                currentNumber = 0;
                continue;
            }

            flag = flag || result.charAt(i) == 'T';
            currentNumber = (currentNumber * 10) + (input.charAt(i) - '0');
        }

        total = total + currentNumber;

        System.out.println(total);
    }
}
