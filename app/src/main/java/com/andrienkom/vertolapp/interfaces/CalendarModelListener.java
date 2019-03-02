package com.andrienkom.vertolapp.interfaces;

import com.andrienkom.vertolapp.entities.Events;

import java.util.List;

public interface CalendarModelListener {

        void eventsListLoad(List<Events> entityEvents);

        void error(String errorMessage);
    }

