package edu.okcu.imagefx.filters;

public class SobelOperatorFilter extends Convolution {

    public SobelOperatorFilter() {
        super(3, new float[]{-1, -2, -1,
                0, 0, 0,
                1, 2, 1});
    }
}
