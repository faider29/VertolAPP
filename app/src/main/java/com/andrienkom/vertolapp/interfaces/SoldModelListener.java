package com.andrienkom.vertolapp.interfaces;

import com.andrienkom.vertolapp.entities.Sold;

import java.util.List;

public interface SoldModelListener {
    void soldListLoad(List<Sold> entitySold);

    void error(String errorMessage);
}
