package com.risosuit.DGomezTagle.TicketsService.DTO;

import java.util.ArrayList;

public class Result <T> {
    public boolean correct;
    public String message;
    public Object object;
    public ArrayList<T> objects;
    public Exception ex;


}
