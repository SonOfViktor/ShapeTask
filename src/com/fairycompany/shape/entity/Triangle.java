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
    private long triangleID;
    private Point A;
    private Point B;
    private Point C;
    private Observer observer = new TriangleObserver();

    public Triangle(Point A, Point B, Point C) throws TriangleException {
        if (!TriangleValidator.isTrianglePossible(A, B, C)) {
            throw new TriangleException("Points can't be on one line");
        }

        this.triangleID = IDGenerator.generateID();
        this.A = A;
        this.B = B;
        this.C = C;
        notifyObservers();
    }

    public long getTriangleID() {
        return triangleID;
    }

    public Point getA() {
        return A;
    }

    public void setA(Point A) {
        this.A = A;
        notifyObservers();
    }

    public Point getB() {
        return B;
    }

    public void setB(Point B) {
        this.B = B;
        notifyObservers();
    }

    public Point getC() {
        return C;
    }

    public void setC(Point C) {
        this.C = C;
        notifyObservers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;

        Triangle triangle = (Triangle) o;

        if (A != null ? !A.equals(triangle.A) : triangle.A != null) return false;
        if (B != null ? !B.equals(triangle.B) : triangle.B != null) return false;
        return C != null ? C.equals(triangle.C) : triangle.C == null;
    }

    @Override
    public int hashCode() {
        int result = A != null ? A.hashCode() : 0;
        result = 31 * result + (B != null ? B.hashCode() : 0);
        result = 31 * result + (C != null ? C.hashCode() : 0);
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

        TriangleEvent triangleEvent = new TriangleEvent(this);
        observer.parameterChange(triangleEvent);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Triangle %05d", triangleID))
                .append("\nPoint A: ").append(A)
                .append("\nPoint B: ").append(B)
                .append("\nPoint C: ").append(C);
        return stringBuilder.toString();
    }
}
