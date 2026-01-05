package com.manulife.ap.rsf_connect_mongodb.swagger.api;

import com.manulife.ap.rsf_connect_mongodb.swagger.model.HomeResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * A delegate to be called by the {@link HomeApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface HomeApiDelegate {

    /**
     * @see HomeApi#homeGet
     */
    ResponseEntity<HomeResponse> homeGet();

}
