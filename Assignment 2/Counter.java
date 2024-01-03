public class Counter {
    private PowerSet<Card> cardps;
//    making powerSet of type Card called cardps
    private Card starter;
// creating our starter which indicates starter card of type Card
    public Counter(Card[] hand, Card starter) {
        cardps = new PowerSet<Card>(hand);
        this.starter = starter;
//    constructor takes array of card objects and the starter card

//  initializing instance variables
    }
    public int countPoints() {
        return countPairs() + countLongestRun() + countFlush() + countFifteen() + countHisKnobs();
//  adds all the methods that counts up each kind of points and adds them together then returns them in this method
    }
    public int countLongestRun() {
        int pointsLongestRun = 0;
        int longestRunLength = 0;
//  int to count points and run length
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> setCards = cardps.getSet(i);
//  makes a set of types card at each position in the set of set cards (checks each set of cards)
            if (isRun(setCards)) {
                if (setCards.getLength() > longestRunLength) {
                    longestRunLength = setCards.getLength();
                    pointsLongestRun = longestRunLength;
//  if the set is a run, return the set of cards length and keep it as the current run until the longest one is found
//  when checking each set.
                } else if (setCards.getLength() == longestRunLength) {
                    pointsLongestRun += setCards.getLength();
//  if the longest run is found we just return points longestRun with the length of that set.
                }
            }
        }
        return pointsLongestRun;
//  return the points of our longestRun
    }
    private boolean isRun(Set<Card> set) {
        int n = set.getLength();
        if (n <= 2) return false; // Run must be at least 3 in length.
        int[] rankArr = new int[13];
        for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.
        for (int i = 0; i < n; i++) {
            rankArr[set.getElement(i).getRunRank() - 1] += 1;
        }
        // Now search in the array for a sequence of n consecutive 1's.
        int streak = 0;
        int maxStreak = 0;
        for (int i = 0; i < 13; i++) {
            if (rankArr[i] == 1) {
                streak++;
                if (streak > maxStreak)
                    maxStreak = streak;
            } else
                streak = 0;
        }
        if (maxStreak == n) // Check if this is the maximum streak.
            return true;
        else
            return false;
    }
    public int countPairs() {
        int pointsPairs = 0;
//  int to count pairs
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> cards1 = cardps.getSet(i);
//  makes a set of types card at each position in the set of set cards (checks each set of cards)
            if (cards1.getLength() == 2) {
                if (cards1.getElement(0).getLabel().equals(cards1.getElement(1).getLabel())) {
                    pointsPairs += 2;
//  if the length of the set is two we check to see if they have the same label, if so we add two to point pairs
                } else {
                    pointsPairs += 0;
//  if the set of two cards is not a pair we add nothing and check the next set
                }
            }
        }
        return pointsPairs;
//  return point retrieved from pairs
    }
    public int countFifteen() {
        int pointsFifteen = 0;
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> cards = cardps.getSet(i);
//  makes a set of types card at each position in the set of set cards (checks each set of cards)
            int sum = 0;
            for (int j = 0; j < cards.getLength(); j++) {
                sum += cards.getElement(j).getFifteenRank();
//  returns the element fifteen rank for each card in the set.
            }
            if (sum == 15) {
                pointsFifteen += 2;
//  if the total of the cards equals 15 we add two points to our point fifteen tally.
            }
        }
        return pointsFifteen;
//  return the total points retrieved from any combination of sets of cards that equal 15
    }
    public int countFlush() {
        int flushPoints = 0;
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> cards = cardps.getSet(i);
//  makes a set of types card at each position in the set of set cards (checks each set of cards)
            if (cards.getLength() == 5) {
                boolean sameSuit = true;
//  we use a boolean called sameSuit set to true at the start of each for loop iteration and if the set is 5 cards.
                String flushSuit = cards.getElement(0).getSuit();
//  making a string flushSuit that gets the element suit at position 0 which is the starter suit.
                for (int j = 1; j < 5; j++) {
                    if (cards.getElement(j).getSuit() != flushSuit) {
                        sameSuit = false;
                        break;
//  if the suit is not equal to the suit of the rest of the four cards we break to go to the else statement.
                    }
                }
                if (sameSuit) {
                    flushPoints += 5;
//  on the other hand if the suit is the same we add 5 points to flushPoints since all 5 cards carry the same suit.
                }
            } else if (cards.getLength() == 4 && !cardps.getSet(i).getElement(0).getSuit().equals(starter.getSuit())) {
                String suit = cards.getElement(0).getSuit();
                boolean sameSuit = true;
//  opposing this, if the card is length four it equals the starter card suit then sameSuit is set to true
                for (int k = 0; k < 4; k++) {
                    if (cards.getElement(0).getSuit() != cards.getElement(k).getSuit()) {
                        sameSuit = false;
                        break;
//  If it goes through the for loop and the four cards do not match suits it breaks and no points are given
                    }
                }
                if (sameSuit) {
                    flushPoints += 4;
//  but if the suit is the same we add 4 points to the flushPoints tally
                }
            }
        }
        return flushPoints;
//  return flushPoints after all flushPoints are gathered
    }
    public int countHisKnobs() {
        int pointsHisKnobs = 0;
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> cards = cardps.getSet(i);
//  makes a set of types card at each position in the set of set cards (checks each set of cards)
            if (cards.getLength() == 5) {
                for (int j = 0; j < cards.getLength(); j++) {
//  if the set of cards has a length of 5
                    String suit = starter.getSuit();
//  putting the starter suit type in a string variable
                    if (cards.getElement(j).getSuit().equals(suit) && cards.getElement(j).getLabel() == "J") {
                        pointsHisKnobs += 1;
//  if the card element suit is the same as the starter suit and the element has a jack label this is hisKnobs and
//  returns 1
                    }
                }
            }
        }
        return pointsHisKnobs;
//  at the end return hisKnobs
    }
}