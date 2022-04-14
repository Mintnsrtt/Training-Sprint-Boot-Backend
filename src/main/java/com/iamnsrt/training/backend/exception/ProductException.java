package com.iamnsrt.training.backend.exception;

public class ProductException extends BaseException {
    public ProductException(String code) {
        super(code);
    }

    public static ProductException notFound() {
        return new ProductException("not.found");
    }
}
