import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinsOnAClock {

    private static int coinsLeft(Map<Character, Integer> numCoins) {
        // Returns number of coins remaining to be placed
        int coins = 0;
        for (int v : numCoins.values()) {
            coins = coins + v;
        }
        return coins;
    }

    private static void coinsOnAClockHelper(char[] clock, List<char[]> output, int i, Map<Character, Integer> numCoins) {
        // If there are no coins left => valid solution! Add to output
        if (coinsLeft(numCoins) == 0) {
            char[] solution = new char[clock.length];
            System.arraycopy(clock, 0, solution, 0, clock.length);
            output.add(solution);
            return;
        }
        // If there is already a coin in the location to be placed, stop going down this branch
        if (clock[i] != ' ') {
            return;
        }

        for (char coinType : numCoins.keySet()) {
            int remaining = numCoins.get(coinType);
            int value = 0;

            if (coinType == 'p') value = 1;
            if (coinType == 'n') value = 5;
            if (coinType == 'd') value = 10;

            if (remaining > 0) {
                clock[i] = coinType;
                int next_idx = (i + value) % clock.length;
                numCoins.replace(coinType, remaining-1);
                coinsOnAClockHelper(clock, output, next_idx, numCoins);
                // After the backtracking step, try a new coin type in that location
                clock[i] = ' ';
                numCoins.replace(coinType, remaining);
            }

        }
    }

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        Map<Character, Integer> numcoins = new HashMap<>();
        numcoins.put('p', pennies);
        numcoins.put('n', nickels);
        numcoins.put('d', dimes);

//        System.out.println(coinsLeft(numCoins));

        char[] clock = new char[hoursInDay];
        for (int i = 0; i < clock.length; i++)
            clock[i] = ' ';
        List<char[]> output = new ArrayList<>();
        coinsOnAClockHelper(clock, output, 0, numcoins);
        return output;
    }
}
