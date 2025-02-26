package edu.okcu.imagefx.filters;

public class SharpenFilter extends Convolution {
    public SharpenFilter() {
        super(3, new float[]{
                0, -1, 0,
                -1, 5, -1,
                0, -1, 0});
    }
}
