package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Chem {
    HashSet<Integer> reag, ans;
    ArrayList<HashSet<Integer>> lefts, rights;

    Chem( String file ) throws FileNotFoundException {
        this.reag = new HashSet<Integer>();
        this.ans = new HashSet<Integer>();

        this.lefts = new ArrayList<HashSet<Integer>>();
        this.rights = new ArrayList<HashSet<Integer>>();

        this.read( file );
    }

    private void read(String fileName) throws FileNotFoundException {
        File file = new File( "C:\\Users\\paspo\\Desktop\\Chem\\" + fileName );
        Scanner sc = new Scanner( file );

        boolean fl = false;
        String str;
        String[] splStr, left, right;

        while( sc.hasNextLine() ){
            str = sc.nextLine();

            if( fl ){
                splStr = str.split( "->" );
                left = splStr[0].split( "\\+" );
                right = splStr[1].split( "\\+" );
                lefts.add( arrayToHashSet( left ) );
                rights.add( arrayToHashSet( right ) );
            } else {
                splStr = str.split( " " );
                reag = arrayToHashSet( splStr );
                ans = arrayToHashSet( splStr );
                fl = true;
            }
        }
    }

    private HashSet<Integer> arrayToHashSet(String[] array) {
        HashSet<Integer> res = new HashSet<Integer>();

        for( int i = 0; i < array.length; i++ ){
            res.add( Integer.parseInt( array[i] ) );
        }

        return  res;
    }

    public HashSet<Integer> getReagents(){
        return this.reag;
    }

    public HashSet<Integer> getAnswer(){
        return this.ans;
    }

    public ArrayList< HashSet< Integer > > getLefts(){
        return this.lefts;
    }

    public ArrayList< HashSet< Integer > > getRights() {
        return this.rights;
    }
}
