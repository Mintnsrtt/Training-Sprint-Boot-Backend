package com.iamnsrt.training.backend.api;

import com.iamnsrt.training.backend.business.TestBusiness;
import com.iamnsrt.training.backend.exception.BaseException;
import com.iamnsrt.training.backend.model.RegisterRequest;
import com.iamnsrt.training.backend.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestApi {
    private final TestBusiness business;

    public TestApi(TestBusiness business) {
        this.business = business;
    }

    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setName("Mumu");
        response.setFood("Walnut");
        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws BaseException {
        String response = business.register(request);
        return ResponseEntity.ok(response);
    }
}

