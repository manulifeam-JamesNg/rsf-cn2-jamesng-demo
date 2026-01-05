package com.manulife.ap.rsf_connect_mongodb.swagger.api;

import com.manulife.ap.rsf_connect_mongodb.swagger.model.HomeResponse;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeApiController implements HomeApi {

    private final HomeApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public HomeApiController(HomeApiDelegate delegate) {
        this.delegate = delegate;
    }
    public ResponseEntity<HomeResponse> homeGet() {
        return delegate.homeGet();
    }

}
