import java.util.ArrayList;

import Includes.*;

public class FrequencyAnalysis {
    //sizes of hash-tables are updated
    static final int[] hashTableSizes = {173, 6733, 100003};
    COL106Dictionary<String, Integer> dict1 = new COL106Dictionary<String, Integer>(hashTableSizes[0]);
    COL106Dictionary<String, Integer> dict2 = new COL106Dictionary<String, Integer>(hashTableSizes[1]);
    COL106Dictionary<String, Integer> dict3 = new COL106Dictionary<String, Integer>(hashTableSizes[2]);

    void fillDictionaries(String inputString) throws NullKeyException, KeyAlreadyExistException, KeyNotFoundException {
        /*
         * To be filled in by the student
         */
    }
    
    long[] profile() {
        /*
         * To be filled in by the student
         */
        return new long[4];
    }

    int[] noOfCollisions() {
        /*
         * To be filled in by the student
         */
        return new int[3];
    }

    String[] hashTableHexaDecimalSignature() {
        /*
         * To be filled in by the student
         */
        return new String[3];
    }
    
    String[] distinctWords() {
        /*
         * To be filled in by the student
         */
        return new String[0];
    }

    Integer[] distinctWordsFrequencies() {
        /*
         * To be filled in by the student
         */
        return new Integer[0];
    }
}
