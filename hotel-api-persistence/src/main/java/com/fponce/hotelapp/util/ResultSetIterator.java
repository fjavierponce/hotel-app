package com.fponce.hotelapp.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResultSetIterator implements Iterator {

    ResultSet resultSet;
    ResultSet next;

    public ResultSetIterator(ResultSet rs) {
        resultSet = rs;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        try {
            updateNext();
            return next != null;
        } catch (SQLException ex) {
            throw new RuntimeException("At hasNext.", ex);
        }
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() {
        try {
            updateNext();
            if (next == null) {
                throw new NoSuchElementException();
            }
            return next;
        } catch (SQLException ex) {
            throw new RuntimeException("At nex element.", ex);
        } finally {
            next = null;
        }
    }

    private void updateNext() throws SQLException {
        if (next == null) {
            next = resultSet.next() ? resultSet : null;
        }
    }
}
