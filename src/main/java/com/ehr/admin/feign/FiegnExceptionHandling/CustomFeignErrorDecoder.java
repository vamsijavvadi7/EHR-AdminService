package com.ehr.admin.feign.FiegnExceptionHandling;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CustomFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        String message = "Unexpected error occurred during Feign client call";

        if (status == HttpStatus.CONFLICT) {
            message = "Ambiguity Error";
            return new ResponseStatusException(HttpStatus.CONFLICT, message);
        } else if (status == HttpStatus.NOT_FOUND) {
            message = "Resource not found.";
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        } else if (status.is5xxServerError()) {
            message = "Server error occurred.";
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
        }

        return new ResponseStatusException(status, message);
    }
}
