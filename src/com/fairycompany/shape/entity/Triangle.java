package com.fairycompany.shape.entity;

import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.observer.Observable;
import com.fairycompany.shape.observer.Observer;
import com.fairycompany.shape.observer.TriangleEvent;
import com.fairycompany.shape.observer.impl.TriangleObserver;
import com.fairycompany.shape.util.IDGenerator;
import com.fairycompany.shape.validator.TriangleValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Triangle implements Observable {
    private static Logger logger = LogManager.getLogger();
    private final long triangleID;
    private Point pointA;
    private Point pointB;
    private Point pointC;
    private Observer observer = new TriangleObserver();

    public Triangle(Point pointA, Point pointB, Point pointC) throws TriangleException {
        if (!TriangleValidator.isTrianglePossible(pointA, pointB, pointC)) {
            throw new TriangleException("Points can't be on one line");
        }

        this.triangleID = IDGenerator.generateID();
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        notifyObservers();
    }

    public long getTriangleID() {
        return triangleID;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
        notifyObservers();
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
        notifyObservers();
    }

    public Point getPointC() {
        return pointC;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
        notifyObservers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;

        Triangle triangle = (Triangle) o;

        if (pointA != null ? !pointA.equals(triangle.pointA) : triangle.pointA != null) return false;
        if (pointB != null ? !pointB.equals(triangle.pointB) : triangle.pointB != null) return false;
        return pointC != null ? pointC.equals(triangle.pointC) : triangle.pointC == null;
    }

    @Override
    public int hashCode() {
        int result = pointA != null ? pointA.hashCode() : 0;
        result = 31 * result + (pointB != null ? pointB.hashCode() : 0);
        result = 31 * result + (pointC != null ? pointC.hashCode() : 0);
        return result;
    }

    @Override
    public void attach(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void detach() {
        this.observer = null;
    }

    @Override
    public void notifyObservers() {
        if (observer == null) {
            logger.log(Level.INFO, "Observer is null");
            return;
        }

        var triangleEvent = new TriangleEvent(this);
        observer.parameterChange(triangleEvent);
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Triangle %05d", triangleID))
                .append("\nPoint A: ").append(pointA)
                .append("\nPoint B: ").append(pointB)
                .append("\nPoint C: ").append(pointC);
        return stringBuilder.toString();
    }
}
