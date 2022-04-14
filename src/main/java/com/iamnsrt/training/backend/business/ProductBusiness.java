package com.iamnsrt.training.backend.business;

import com.iamnsrt.training.backend.exception.BaseException;
import com.iamnsrt.training.backend.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductBusiness {
    public String getProductById(String id) throws BaseException {
        // TODO: get data from database
        if (Objects.equals("1234", id)) {
            throw ProductException.notFound();
        }

        return id;
    }
}
