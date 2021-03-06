package com.checkers.server.services.referee.graph.coords;

import com.checkers.server.exceptions.CheckersException;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public class RussianCoords implements Coords{
    private Integer x = 0;
    private Integer y = 0;

    private Map<Character, Integer> resolver;
    private Map<Integer, Character> reverseResolver;

    public RussianCoords(){
        resolver = new HashMap();
        resolver.put('a', 1);
        resolver.put('b', 2);
        resolver.put('c', 3);
        resolver.put('d', 4);
        resolver.put('e', 5);
        resolver.put('f', 6);
        resolver.put('g', 7);
        resolver.put('h', 8);

        reverseResolver = new HashMap();
        reverseResolver.put(1, 'a');
        reverseResolver.put(2, 'b');
        reverseResolver.put(3, 'c');
        reverseResolver.put(4, 'd');
        reverseResolver.put(5, 'e');
        reverseResolver.put(6, 'f');
        reverseResolver.put(7, 'g');
        reverseResolver.put(8, 'h');
    }

    public RussianCoords(String checkersNotation) throws CheckersException {
        this();
        this.setCheckersNotation(checkersNotation);
    }

    public RussianCoords(Integer x, Integer y) throws CheckersException {
        this();

        if(x < 1 || x > 8 || y < 1 || y > 8){
            throw new CheckersException(4L, "Incorrect X or Y out of range (Russian checkers)");
        }

        if((x + y) % 2 != 0){
            throw new CheckersException(5L, "Incorrect step - only black cells right (Russian checkers)");
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public String getCheckersNotation(){
        StringBuilder checkersNotation = new StringBuilder();

        if(this.x == 0 || this.y == 0){
            return null;
        }

        checkersNotation.append(reverseResolver.get(this.y));
        checkersNotation.append(this.x);

        return checkersNotation.toString();
    }

    @Override
    public void setCheckersNotation(String checkersNotation) throws CheckersException {
        this.y = resolver.get(checkersNotation.charAt(0));
        if(this.y == null){
            throw new CheckersException(1L, "Only symbols from 'a' to 'h' allowed (Russian checkers)");
        }
        try{
            this.x = Integer.parseInt(new Character(checkersNotation.charAt(1)).toString());
            if(this.x > 8 || this.x < 1){
                throw new CheckersException(2L, "Only numbers from '1' to '8' allowed (Russian checkers)");
            }
        } catch(Exception e){
            throw new CheckersException(3L, "Only numbers allowed (Russian checkers)");
        }

        if((this.x + this.y) % 2 != 0){
            throw new CheckersException(5L, "Incorrect step - only black cells right (Russian checkers)");
        }
    }

    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object c){
        return (this.x.equals(((Coords)c).getX()) && this.y.equals(((Coords)c).getY()));
    }

    @Override
    public int hashCode(){
        return this.x + (this.y * 10);
    }
}
