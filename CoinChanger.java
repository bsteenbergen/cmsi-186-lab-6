import java.util.Set;
import java.util.HashMap;

public abstract class CoinChanger {
    abstract public int minCoins(int amount, Set<Integer> denominations);

    private static void checkArguments(int amount, Set<Integer> denominations) {
        if (amount < 1) {
            throw new IllegalArgumentException("Amount must be at least 1");
        }
        if (denominations.isEmpty()) {
            throw new IllegalArgumentException("At least one denomination is required");
        }
        for (var d : denominations) {
            if (d < 0) {
                throw new IllegalArgumentException("Denominations must all be positive");
            }
        }
        if (denominations.contains(1) == false) {
            throw new IllegalArgumentException("Denominations must have a 1-unit coin");
        }
    }

    private static HashMap<String, Integer> memo = new HashMap<>();

    public static class TopDown extends CoinChanger {
        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);

            var memoKey = amount + "" + denominations;
            if (memo.containsKey(memoKey)) {
                return memo.get(memoKey);
            }
    
            var result = 0;
            //recursive equation here
    
            memo.put(memoKey, result);
            return result;
        }
    }

    public static class BottomUp extends CoinChanger {
        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);

            // TODO: Implement this method using the bottom-up approach with
            // a table.
            return 0; // TODO change this line, of course
        }
    }
}
