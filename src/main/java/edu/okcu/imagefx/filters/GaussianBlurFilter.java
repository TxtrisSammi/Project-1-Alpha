package edu.okcu.imagefx.filters;

public class GaussianBlurFilter extends Convolution {

    public GaussianBlurFilter() {
        super(5, new float[] {1/273f, 4/273f, 7/273f, 4/273f, 1/273f,
                4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
                7/273f, 26/273f, 41/273f, 26/273f, 7/273f,
                4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
                1/273f, 4/273f, 7/273f, 4/273f, 1/273f});
    }
}
