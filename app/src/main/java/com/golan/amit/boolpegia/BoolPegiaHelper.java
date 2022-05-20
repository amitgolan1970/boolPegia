package com.golan.amit.boolpegia;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoolPegiaHelper {

    /**
     * Constants
     */
    public static final int MODE_ADVANCED = 0;
    public static final int MODE_BEGINNER = 1;
    public static final int NUM_LENGTH = 4;
    public static final int COLORS_LENGTH = 5;
    public static final String[] COLOR_STR = {
            "#40C4FF", "#00FF00", "#FF0000", "#0000FF", "#FFFF00"
    };
    public static final String[] REFLECTION_COLOR_STR = {
            "#FFFFFF", "#000000"
    };

    private int[] computerNumbers;
    private int[] userGuesses;

    private int out_index, in_index;
    private int[] evalRes ;
    private int mode;

    /**
     * Constructor
     */
    public BoolPegiaHelper() {
        List<Integer> computerNumbersList = new ArrayList<>();
        for(int i = 0; i <= NUM_LENGTH; i++ ) {
            computerNumbersList.add(i);
        }
        Collections.shuffle(computerNumbersList);
        int tmpLastElem = computerNumbersList.get(computerNumbersList.size() -1);
        computerNumbersList.remove(tmpLastElem);
        computerNumbers = new int[computerNumbersList.size()];
        for(int i = 0; i < computerNumbersList.size(); i++) {
            computerNumbers[i] = computerNumbersList.get(i);
        }

        userGuesses = new int[NUM_LENGTH];
        out_index = in_index = 0;

        evalRes = new int[NUM_LENGTH];
        resetEvalParameters(evalRes.length);
        mode = MODE_ADVANCED;   //  advanced
    }

    /**
     * helper methods
     * @return
     */

    public boolean user_win() {
        boolean isWon = true;
        for(int i = 0; i < userGuesses.length; i++) {
            if(userGuesses[i] != computerNumbers[i]) {
                isWon = false;
                break;
            }
        }
        return isWon;
    }

    public void evaluate() {

        //  0 = bool
        //  1 = pegia
        resetEvalParameters(NUM_LENGTH);
        if (userGuesses.length != computerNumbers.length)
            return;
        for (int i = 0; i < userGuesses.length; i++) {
            if (userGuesses[i] == computerNumbers[i]) {
                evalRes[i] = 0;         //  bool
            } else {
                for (int j = 0; j < computerNumbers.length; j++) {
                    if (userGuesses[i] == computerNumbers[j]) {
                        evalRes[i] = 1; //  pegia
                        break;
                    }
                }
            }
        }
    }

    public String evaluation_representation() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < evalRes.length; i++) {
            sb.append(evalRes[i]);
            if(i < evalRes.length)
                sb.append(" ");
        }
        return sb.toString();
    }

    public int getEvalByIndex(int ind) {
        if(ind >= evalRes.length)
            return -1;
        else
            return evalRes[ind];
    }

    private void resetEvalParameters(int length) {
        for (int i = 0; i < length; i++)
            evalRes[i] = -1;
    }


    /**
     * Getters & Setters
     */

    public String computer_representation() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < computerNumbers.length; i++) {
            sb.append(computerNumbers[i]);
            if(i < computerNumbers.length)
                sb.append(" ");
        }
        return sb.toString();
    }

    public String user_guesses_representation() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < userGuesses.length; i++) {
            sb.append(userGuesses[i]);
            if(i < (userGuesses.length - 1))
                sb.append(" ");
        }
        return sb.toString();
    }

    public int getComputerNumByIndex(int ind) {
        if(ind > computerNumbers.length)
            return computerNumbers[ind];
        else
            return -1;
    }

    public int getUserGuessByIndex(int ind) {
        if(ind < userGuesses.length)
            return userGuesses[ind];
        else
            return -1;
    }

    public void setUserGuessByIndex(int val, int ind) {
        if(ind < userGuesses.length)
            userGuesses[ind] = val;
        else
            Log.d(MainActivity.DEBUGTAG, "ind " + ind + " is not valid. can't set user guess");
    }

    public int getOut_index() {
        return out_index;
    }

    public void setOut_index(int out_index) {
        this.out_index = out_index;
    }

    public void increaseOut_index() {
        out_index++;
    }

    public int getIn_index() {
        return in_index;
    }

    public void setIn_index(int in_index) {
        this.in_index = in_index;
    }

    public void increaseIn_index() {
        in_index++;
    }

    public void decreaseIn_index() {
        in_index--;
    }

    public void resetIn_index() {
        in_index = 0;
    }

    public int[] getEvalRes() {
        return evalRes;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
