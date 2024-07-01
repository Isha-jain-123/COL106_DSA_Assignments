package com.gradescope.assignment1;

import com.gradescope.assignment1.AbstractFreqOfLetters;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
public class FreqOfLetters extends AbstractFreqOfLetters {
    public Integer[] count_letters(String fname) throws FileNotFoundException, IOException {
        Integer[] result = new Integer[26];

        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }

        FileReader fr = new FileReader(fname);
        BufferedReader br = new BufferedReader(fr);   // reading the file and converting to a string
        String line;
        while((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {  // running a loop over the length of the string and reading each character
                char ch = line.charAt(i);
                if (ch <= 'z' && ch >= 'a') {
                    result[ch - 'a']++;   // comparing two letters (throuh ASCII values) to check position in the array.
                }
            }
        }
        return result;

    }
}

