import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayThreeTwo {
    public static void main(String[] args) throws IOException {
        int rowLength = 140;

        List<String> a = Files.lines(Paths.get("day3.txt"))
            .map(line -> line.chars()
                    .mapToObj(c -> (char) c)
                    .map(c -> c == '*' || (c >= '0' && c <= '9')
                            ? c
                            : '.'))
            .map(c -> c.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())
            .toList();
        
        /*
         * qwe
         * r_t
         * yuo
         * 
         * if w = false, check 
         */

        int product = 0;
        for (int i = 0; i < a.size(); i++) {
            String sentence = a.get(i);

            if (sentence.indexOf('*') < 0) {
                continue;
            }

            for (int j = 0; j < sentence.length(); j++) {

                char target = sentence.charAt(j);

                if (target != '*') {
                    continue;
                }

                List<Integer> numbers = new ArrayList<>();

                // find top
                if (i > 0) {
                    String topSentence = a.get(i - 1);
                    if (topSentence.charAt(j) < '0' || topSentence.charAt(j) > '9') {
                        if (j > 0 && topSentence.charAt(j - 1) >= '0' && topSentence.charAt(j - 1) <= '9') {
                            // left
                            int start = j - 1;
                            int end = j - 1;

                            while (start - 1 >= 0 && topSentence.charAt(start - 1) >= '0' && topSentence.charAt(start - 1) <= '9') {
                                start = start - 1;
                            }

                            StringBuilder sb = new StringBuilder();
                            while (start <= end) {
                                sb.append(topSentence.charAt(start));
                                start++;
                            }

                            numbers.add(Integer.parseInt(sb.toString()));
                        }
                        
                        if (j + 1 < rowLength && topSentence.charAt(j + 1) >= '0' && topSentence.charAt(j + 1) <= '9') {
                            // right
                            // left
                            int start = j + 1;
                            int end = j + 1;

                            while (end + 1 < rowLength && topSentence.charAt(end + 1) >= '0' && topSentence.charAt(end + 1) <= '9') {
                                end = end + 1;
                            }

                            StringBuilder sb = new StringBuilder();
                            while (start <= end) {
                                sb.append(topSentence.charAt(start));
                                start++;
                            }

                            numbers.add(Integer.parseInt(sb.toString()));
                        }
                    } else {
                        // left and right
                        // left
                        int start = j;
                        int end = j;

                        while (start - 1 >= 0 && topSentence.charAt(start - 1) >= '0' && topSentence.charAt(start - 1) <= '9') {
                            start = start - 1;
                        }

                        while (end + 1 < rowLength && topSentence.charAt(end + 1) >= '0' && topSentence.charAt(end + 1) <= '9') {
                            end = end + 1;
                        }

                        StringBuilder sb = new StringBuilder();
                        while (start <= end) {
                            sb.append(topSentence.charAt(start));
                            start++;
                        }

                        numbers.add(Integer.parseInt(sb.toString()));
                    }
                }

                if (i + 1 < rowLength) {
                    String topSentence = a.get(i + 1);
                    if (topSentence.charAt(j) < '0' || topSentence.charAt(j) > '9') {
                        if (j > 0 && topSentence.charAt(j - 1) >= '0' && topSentence.charAt(j - 1) <= '9') {
                            // left
                            int start = j - 1;
                            int end = j - 1;

                            while (start - 1 >= 0 && topSentence.charAt(start - 1) >= '0' && topSentence.charAt(start - 1) <= '9') {
                                start = start - 1;
                            }

                            StringBuilder sb = new StringBuilder();
                            while (start <= end) {
                                sb.append(topSentence.charAt(start));
                                start++;
                            }

                            numbers.add(Integer.parseInt(sb.toString()));
                        }
                        
                        if (j + 1 < rowLength && topSentence.charAt(j + 1) >= '0' && topSentence.charAt(j + 1) <= '9') {
                            // right
                            // left
                            int start = j + 1;
                            int end = j + 1;

                            while (end + 1 < rowLength && topSentence.charAt(end + 1) >= '0' && topSentence.charAt(end + 1) <= '9') {
                                end = end + 1;
                            }

                            StringBuilder sb = new StringBuilder();
                            while (start <= end) {
                                sb.append(topSentence.charAt(start));
                                start++;
                            }

                            numbers.add(Integer.parseInt(sb.toString()));
                        }
                    } else {
                        // left and right
                        // left
                        int start = j;
                        int end = j;

                        while (start - 1 >= 0 && topSentence.charAt(start - 1) >= '0' && topSentence.charAt(start - 1) <= '9') {
                            start = start - 1;
                        }

                        while (end + 1 < rowLength && topSentence.charAt(end + 1) >= '0' && topSentence.charAt(end + 1) <= '9') {
                            end = end + 1;
                        }

                        StringBuilder sb = new StringBuilder();
                        while (start <= end) {
                            sb.append(topSentence.charAt(start));
                            start++;
                        }

                        numbers.add(Integer.parseInt(sb.toString()));
                    }
                }


                if (j > 0 && sentence.charAt(j - 1) >= '0' && sentence.charAt(j - 1) <= '9') {
                    int start = j - 1;
                    int end = j - 1;

                    while (start - 1 >= 0 && sentence.charAt(start - 1) >= '0' && sentence.charAt(start - 1) <= '9') {
                        start = start - 1;
                    }

                    StringBuilder sb = new StringBuilder();
                    while (start <= end) {
                        sb.append(sentence.charAt(start));
                        start++;
                    }

                    numbers.add(Integer.parseInt(sb.toString()));
                }

                if (j + 1 < rowLength && sentence.charAt(j + 1) >= '0' && sentence.charAt(j + 1) <= '9') {
                    // right
                    // left
                    int start = j + 1;
                    int end = j + 1;

                    while (end + 1 < rowLength && sentence.charAt(end + 1) >= '0' && sentence.charAt(end + 1) <= '9') {
                        end = end + 1;
                    }

                    StringBuilder sb = new StringBuilder();
                    while (start <= end) {
                        sb.append(sentence.charAt(start));
                        start++;
                    }

                    numbers.add(Integer.parseInt(sb.toString()));
                }
                
                for (Integer abc : numbers) {
                    System.out.println(abc);
                }

                if (numbers.size() == 2) {
                    product = product + (numbers.get(0) * numbers.get(1));
                }
            }
        }

        System.out.println(product);
    }
}
