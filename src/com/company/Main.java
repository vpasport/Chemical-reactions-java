package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    private static HashSet<Integer> reag;
    private static ArrayList<HashSet<Integer>> lefts, rights;

    public static void main(String[] args) throws FileNotFoundException {
	    boolean fl;

	    for( int i = 1; i < 15; i++ ){
	        fl = startTest( i );

	        if( fl ) System.out.printf( "Test %d passed successfully\n", i );
	        else System.out.printf( "Test %d failed\n", i );
        }
    }

    private static boolean startTest(int i) throws FileNotFoundException {
        Chem test = new Chem( Integer.toString( i ) );
        boolean fl = true;
        HashSet<Integer> answer = test.getAnswer();
        reag = test.getReagents();
        lefts = test.getLefts();
        rights = test.getRights();

        while (fl){
            fl = false;
            for ( int j = 0; j < lefts.size(); j++ ){
                if( sucReaction( j ) ){
                    fl = addReagents( j ) || fl;
                    lefts.remove( j );
                    rights.remove( j );
                    j--;
                }
            }
        }

        fl = true;

        for( Integer el : answer ){
            fl = reag.contains( el ) && fl;
        }

        return  fl;
    }

    private static boolean addReagents(int i) {
        boolean fl = false;

        for ( Integer el : rights.get( i ) ){
            fl = reag.add( el ) || fl;
        }

        return fl;
    }

    private static boolean sucReaction(int i) {
        for( Integer el : lefts.get( i ) ){
            if( !reag.contains( el ) ) return false;
        }

        return true;
    }
}
