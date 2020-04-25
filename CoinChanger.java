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
    
            var result = Integer.MAX_VALUE;
            for (var d : denominations) {
                if (amount < d) {
                    continue;
                } else if (amount == d) {
                    result = 1;
                } else  {
                    var updatedResult = minCoins(amount - d, denominations);
                    if (updatedResult < result) {
                        result = 1 + updatedResult;
                    }
                }
            }
            memo.put(memoKey, result);
            return result;
        }
    }

    public static class BottomUp extends CoinChanger {
        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);
            
            var table = new int[amount + 1];
            table[0] = 0;

            for (var i = 0; i <= amount; i++) {
                var newResult = Integer.MAX_VALUE;
                for (var d : denominations) {
                    if (i < d) {
                        continue;
                    } if (table[i - d] >= 0 && table[i - d] + 1 < newResult) {
                        newResult = table[i - d] + 1;
                    } if (newResult == Integer.MAX_VALUE) {
                        table[i] = -1;
                    } else {
                        table[i] = newResult;
                    }
                }
            }
            return table[amount];
        }
    }
}
