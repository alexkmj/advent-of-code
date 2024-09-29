import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class DayFiveTwo {
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

    long[] seedsTemp = Arrays
        .stream(all.split("\\n\\n")[0]
            .split(": ")[1]
            .split("\\s"))
        .mapToLong(Long::parseLong)
        .toArray();
    
    Queue<long[]> seeds = new LinkedList<long[]>();
    for (int i = 0; i < seedsTemp.length; i = i + 2) {
      long[] seedRange = new long[2];
      seedRange[0] = seedsTemp[i];
      seedRange[1] = seedsTemp[i] + seedsTemp[i + 1];
      seeds.add(seedRange);
    }

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

      Queue<long[]> newSeeds = new LinkedList<long[]>();
      while (!seeds.isEmpty()) {
        long[] seedRange = seeds.poll();
        boolean found = false;

        for (long[] range: ranges) {
          
          if (seedRange[0] >= range[1] && seedRange[1] < range[1] + range[2]) {
            found = true;
            long[] newSeedRange = new long[2];
            newSeedRange[0] = seedRange[0] - range[1] + range[0];
            newSeedRange[1] = seedRange[1] - range[1] + range[0];
            newSeeds.add(newSeedRange);
          } else if (seedRange[0] >= range[1] && seedRange[0] < range[1] + range[2]) {
            found = true;
            long[] newSeedRange = new long[2];
            newSeedRange[0] = seedRange[0] - range[1] + range[0];
            newSeedRange[1] = range[0] + range[2];
            newSeeds.add(newSeedRange);

            long[] oldSeedRange = new long[2];
            oldSeedRange[0] = range[1] + range[2];
            oldSeedRange[1] = seedRange[1];
            seeds.add(oldSeedRange);
          } else if (seedRange[1] >= range[1] && seedRange[1] < range[1] + range[2]) {
            found = true;
            long[] newSeedRange = new long[2];
            newSeedRange[0] = range[0];
            newSeedRange[1] = seedRange[1] - range[1] + range[0];
            newSeeds.add(newSeedRange);

            long[] oldSeedRange = new long[2];
            oldSeedRange[0] = seedRange[0];
            oldSeedRange[1] = range[1] - 1;
            seeds.add(oldSeedRange);
          }
        }

        if (!found) {
          newSeeds.add(seedRange);
        }
      }

      seeds = newSeeds;
    }

    long min = Long.MAX_VALUE;
    for (long[] seed: seeds) {
      if (min > seed[0]) {
        min = seed[0];
      }
    }

    System.out.println(min);
  }
}
