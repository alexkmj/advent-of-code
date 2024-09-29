import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayFive {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("day5.txt"));
    StringBuilder stringBuilder = new StringBuilder();
    String line = null;
    String ls = System.getProperty("line.separator");
    while ((line = reader.readLine()) != null) {
      stringBuilder.append(line);
      stringBuilder.append(ls);
    }
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    reader.close();

    String all = stringBuilder.toString();

    long[] seeds = Arrays
        .stream(all.split("\\n\\n")[0]
            .split(": ")[1]
            .split("\\s"))
        .mapToLong(Long::parseLong)
        .toArray();

    String[] blocks = Arrays.stream(all.split("\\n\\n"))
        .skip(1)
        .toArray(size -> new String[size]);

    for (String block: blocks) {
      List<long[]> ranges = Arrays.stream(block.split("\n"))
        .skip(1)
        .map(x -> {
          return Arrays.stream(x.split("\\s"))
              .mapToLong(Long::parseLong)
              .toArray();
        })
        .collect(Collectors.toList());

      List<Long> newSeeds = new ArrayList<>();
      for (long seed: seeds) {
        boolean found = false;
        for (long[] range: ranges) {
          if (seed >= range[1] && seed < range[1] + range[2]) {
            found = true;
            newSeeds.add(seed - range[1] + range[0]);
          }
        }

        if (!found) {
          newSeeds.add(seed);
        }
      }

      for (int i = 0; i < seeds.length; i++) {
        seeds[i] = newSeeds.get(i);
      }
    }

    long partA = Arrays.stream(seeds).min().orElse(Long.MAX_VALUE);

    System.out.println(partA);
  }
}
