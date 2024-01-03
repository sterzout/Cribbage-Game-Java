public class PowerSet<T> {
    private Set<T>[] set;

    public PowerSet(T[] elements) {
        int largestBinary = 0;
        int powerSetConvert = (int) Math.pow(2, elements.length);
//  like in the slides we set int powerSetConvert to 2^ of our elements array length. This is the number of all sets
        set = new Set[powerSetConvert];
//  setting set equal to a new set with number of combinations as powerSetConvert

        for (int i = 0; i < powerSetConvert; i++) {
            if (Integer.toBinaryString(i).length() > largestBinary) {
                largestBinary = Integer.toBinaryString(i).length();
//  for loop of less than powerSetConvert, we use largestBinary and set it to the largestBinary converted number in
//  values from 0 to 1 less than powerSetConvert.
            }
        }

        for (int i = 0; i < powerSetConvert; i++) {
            String binaryString = String.format("%0" + largestBinary + "d", Integer.parseInt(Integer.toBinaryString(i)));
//  formatted string for strings that do not reach length of the largestBinary string
            set[i] = new Set<>();
//  a set for each number until less than powerSetConvert
            for (int j = 0; j < binaryString.length(); j++) {
                if (binaryString.charAt(j) == '1') {
                    set[i].add(elements[j]);
//  if the binary string contains 1 in the string of characters then we add it to set i
                }
            }
        }
    }

    public int getLength() {
        return set.length;
    }
//    returns set length

    public Set<T> getSet(int i) {
        return set[i];
    }
}
// returns set index i
