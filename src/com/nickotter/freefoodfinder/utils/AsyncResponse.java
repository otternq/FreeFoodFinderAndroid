package com.nickotter.freefoodfinder.utils;

public interface AsyncResponse<T> {
    void processFinish(T output);
}