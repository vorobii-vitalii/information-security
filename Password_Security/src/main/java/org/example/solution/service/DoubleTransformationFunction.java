package org.example.solution.service;

public class DoubleTransformationFunction implements TransformationFunction<Integer> {
    @Override
    public Integer compute(Integer number) {
        return number * 2;
    }
}
