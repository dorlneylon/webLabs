package com.nylon.third.lab;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

public class Entry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean hitResult;
    private Double xValue;
    private Double yValue;
    private Double rValue;
    private String executionTime;
    private Double responseTime;

    public Entry() {
        xValue = 0.0;
        yValue = 0.0;
        rValue = 0.0;
        executionTime = "";
        responseTime = 0.0;
        hitResult = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean validateHit() {
        return xValue >= -5 && xValue <= 5 && yValue >= -5 && yValue <= 5 && rValue >= 1 && rValue <= 5;
    }

    private boolean checkTriangle() {
        return xValue <=0 && yValue >=0 && yValue <= xValue + rValue/2;
    }

    private boolean checkRectangle() {
        return xValue >= 0 && yValue >= 0 && xValue <= rValue && yValue <= rValue;
    }

    private boolean checkCircle() {
        return xValue <= 0 && yValue <= 0 && xValue*xValue + yValue*yValue <= rValue*rValue;
    }

    public void setxValue(Double x) {
        xValue = x;
    }

    public Double getxValue() {
        return xValue;
    }

    public void setyValue(Double y) {
        yValue = y;
    }

    public Double getyValue() {
        return yValue;
    }

    public void setrValue(Double r) {
        System.out.println("Toggled," + r);
        rValue = r;
    }

    public Double getrValue() {
        return rValue;
    }

    public void setexecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getexecutionTime() {
        return executionTime;
    }

    public void setresponseTime(Double responseTime) {
        this.responseTime = responseTime;
    }

    public Double getresponseTime() {
        return responseTime;
    }

    public void sethitResult(Boolean hitResult) {
        this.hitResult = hitResult;
    }

    public Boolean gethitResult() {
        return hitResult;
    }

    public void checkHit() {
        hitResult = checkTriangle() || checkRectangle() || checkCircle();
    }
}