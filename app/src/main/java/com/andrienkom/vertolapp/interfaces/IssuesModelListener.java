package com.andrienkom.vertolapp.interfaces;

import com.andrienkom.vertolapp.entities.Issues;

import java.util.List;

public interface IssuesModelListener {

    void issuesListLoad (List<Issues> entityIssues);

    void  error(String errorMessage);
}
