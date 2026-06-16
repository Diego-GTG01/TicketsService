package com.risosuit.DGomezTagle.TicketsService.DTO;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Result <T> {
    public boolean correct;
    public String message;
    public Object object;
    public ArrayList<T> objects;
    @JsonIgnore
    public Exception ex;


}
