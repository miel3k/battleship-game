package lab.cleancode.engine.ships;

import lab.cleancode.engine.BoardConstraints;
import lab.cleancode.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship implements Cloneable {
    final private String name;
    final private int length;
    private ArrayList<Coordinate> coordinates;
    private int hitCount = 0;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate startCoordinate, Boolean isPlacedHorizontal) {
        this.coordinates = generateCoordinates(startCoordinate, isPlacedHorizontal);
    }

    public boolean canSetCoordinates(Coordinate startCoordinate, boolean isPlacedHorizontal, BoardConstraints boardConstraints) {
        if (startCoordinate.x > boardConstraints.getSizeX()
                || startCoordinate.y > boardConstraints.getSizeY()) {
            return false;
        }
        if (isPlacedHorizontal) {
            return startCoordinate.y <= boardConstraints.getSizeY() - getLength();
        } else {
            return startCoordinate.x <= boardConstraints.getSizeX() - getLength();
        }
    }

    public int getLength() {
        return length;
    }

    public void hit(Coordinate coordinate) {
        this.hitCount++;
    }

    public boolean isSunk() {
        return hitCount == length;
    }

    private ArrayList<Coordinate> generateCoordinates(
            Coordinate startCoordinate,
            Boolean isHorizontal
    ) {
        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.add(startCoordinate);
        for (int i = 1; i <= getLength() - 1; i++) {
            if (isHorizontal) {
                int xCoordinate = startCoordinate.x;
                int yCoordinate = startCoordinate.y + i;
                Coordinate nextCoordinate = new Coordinate(xCoordinate, yCoordinate);
                newCoordinates.add(nextCoordinate);
            } else {
                int xCoordinate = startCoordinate.x + i;
                int yCoordinate = startCoordinate.y;
                Coordinate nextCoordinate = new Coordinate(xCoordinate, yCoordinate);
                newCoordinates.add(nextCoordinate);
            }
        }
        return newCoordinates;
    }

    @Override
    public Ship clone() throws CloneNotSupportedException {
        return (Ship) super.clone();
    }
}