package mm.com.mtp.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import mm.com.mtp.response.ApiResponse;
import mm.com.mtp.response.ApiStatus;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // 400 Bad Request
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(String.join(",", errors))
                .build();

        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(String.join(",", errors))
                .build();

        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(error)
                .build();

        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final String error = ex.getRequestPartName() + " part is missing";

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(error)
                .build();

        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final String error = ex.getParameterName() + " parameter is missing";

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(error)
                .build();

        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
        logger.info(ex.getClass().getName());

        final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(error)
                .build();

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final List<String> errors = new ArrayList<String>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(String.join(",", errors))
                .build();

        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<Object> handleInvalid(final InvalidFormatException ex) {
        logger.info(ex.getClass().getName());

        ApiResponse body = ApiResponse.builder()
                .status(ApiStatus.FAIL.toString())
                .message(ex.getValue() + " is not valid value for field " + ex.getPath().get(0).getFieldName() + ".")
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info(ex.getClass().getName());

        String error;
        if (ex.getRootCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getRootCause();
            error = invalidFormatException.getValue() + " is not valid value for field " + invalidFormatException.getPath().get(0).getFieldName() + ".";
        } else {
            error = ex.getMessage();
        }

        ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.FAIL.toString())
                .message(error)
                .build();

        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    // 404 Not Found
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(error)
                .build();

        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFound(final ResourceNotFoundException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.FAIL.toString())
                .message(ex.getLocalizedMessage())
                .build();

        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(final BadRequestException ex) {
        logger.info(ex.getClass().getName());

        ApiResponse body = ApiResponse.builder()
                .status(ApiStatus.FAIL.toString())
                .message(ex.getLocalizedMessage())
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    // 405 Method Not Allowed
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(builder.toString())
                .build();

        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    // 409 Conflict
    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Object> handleConflict(final ConflictException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(ex.getLocalizedMessage())
                .build();

        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    // 415 Unsupported Media Type
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(builder.substring(0, builder.length() - 2))
                .build();

        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // 500 Internal Server Error
    @ExceptionHandler({AppException.class, Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final ApiResponse response = ApiResponse.builder()
                .status(ApiStatus.ERROR.toString())
                .message(ex.getLocalizedMessage())
                .build();

        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}