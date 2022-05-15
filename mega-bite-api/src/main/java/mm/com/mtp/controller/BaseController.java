package mm.com.mtp.controller;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.model.RefreshToken;
import mm.com.mtp.model.Staff;
import mm.com.mtp.payload.JwtTokenResponse;
import mm.com.mtp.payload.StaffLoginResponse;
import mm.com.mtp.payload.StaffResponse;
import mm.com.mtp.response.ApiResponse;
import mm.com.mtp.response.ApiStatus;
import mm.com.mtp.security.JwtTokenProvider;
import mm.com.mtp.utils.ControllerLogUtil;
import mm.com.mtp.response.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sai Pann Hmwe on 1/6/2021.
 */
@RestController
@Slf4j
public class BaseController<T> {

    @Autowired
    protected JwtTokenProvider tokenProvider;

    @Autowired
    protected AuthenticationManager authenticationManager;

    // -----------------------------------------------------------------------

    /**
     * Return 200 OK response with status and message only.</br>
     * {@link ApiStatus} can be FAIL, SUCESS.
     *
     * @param status  can be fail or success.
     * @param message to give information about response.
     * @return {@link ApiResponse}
     */
    protected ResponseEntity<ApiResponse<T>> okResponse(ApiStatus status, String message) {

        logResponseData(HttpStatus.OK.toString(), status.toString(), message, "No Data");

        ApiResponse<T> response = ApiResponse.<T>builder()
                .status(status.toString())
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }

    // -----------------------------------------------------------------------

    /**
     * Return 200 OK response with status, message and data.</br>
     * <p>
     * {@link ApiStatus} is always success by default.
     *
     * @param message to give information about response.
     * @param data    the generic response data.
     * @return
     * @see ApiResponse
     */
    protected ResponseEntity<ApiResponse<T>> okResponse(String message, T data) {

        logResponseData(HttpStatus.OK.toString(), ApiStatus.SUCCESS.toString(), message, data);

        ApiResponse<T> response = ApiResponse.<T>builder()
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.ok(response);
    }

    // -----------------------------------------------------------------------

    /**
     * Return 200 OK response with status, message and data.</br>
     * <p>
     * {@link ApiStatus} is always success by default.
     *
     * @param message to give information about response.
     * @param data    the list of generic response data.
     * @return {@link ApiResponse}
     * @see ApiResponse
     */
    protected ResponseEntity<ApiResponse<List<T>>> okResponse(String message, List<T> data) {

        logResponseData(HttpStatus.OK.toString(), ApiStatus.SUCCESS.toString(), message, data);

        ApiResponse<List<T>> responses = ApiResponse.<List<T>>builder()
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.ok(responses);
    }

    // -----------------------------------------------------------------------

    /**
     * Return 200 OK response with status, message and data.</br>
     * <p>
     * {@link ApiStatus} is always success by default.
     *
     * @param message to give information about response.
     * @param data    the list of generic response data.
     * @return {@link ApiResponse}
     * @see ApiResponse
     */
    protected ResponseEntity<ApiResponse<Page<T>>> okResponse(String message, Page<T> data) {

        logResponseData(HttpStatus.OK.toString(), ApiStatus.SUCCESS.toString(), message, data);

        ApiResponse<Page<T>> responses = ApiResponse.<Page<T>>builder()
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.ok(responses);
    }

    // -----------------------------------------------------------------------

    /**
     * Return 201 Created response with status, message and data.</br>
     * <p>
     * {@link ApiStatus} is always success by default.
     *
     * @param message to give information about response.
     * @param data    the generic response data.
     * @return
     * @see ApiResponse
     */
    protected ResponseEntity<ApiResponse<T>> createdResponse(String message, T data) {

        logResponseData(HttpStatus.CREATED.toString(), ApiStatus.SUCCESS.toString(), message, data);

        ApiResponse<T> response = ApiResponse.<T>builder()
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // -----------------------------------------------------------------------

    /**
     * Return 400 Bad Request with status<fail> and message.
     *
     * @param message to give information about response.
     * @return {@link ApiResponse}
     */
    protected ResponseEntity<ApiResponse<T>> badRequestResponse(String message) {

        logResponseData(HttpStatus.BAD_REQUEST.toString(), ApiStatus.FAIL.toString(), message, "No data");

        ApiResponse<T> response = ApiResponse.<T>builder()
                .status(ApiStatus.FAIL.toString())
                .message(message)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // -----------------------------------------------------------------------

    /**
     * Return 409 Conflict with status<fail> and message.
     *
     * @param message to give information about response.
     * @return
     */
    protected ResponseEntity<ApiResponse<T>> conflictResponse(String message) {

        logResponseData(HttpStatus.CONFLICT.toString(), ApiStatus.FAIL.toString(), message, "No data");

        ApiResponse<T> response = ApiResponse.<T>builder()
                .status(ApiStatus.FAIL.toString())
                .message(message)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // -----------------------------------------------------------------------

    /**
     * Return 404 Not Found with status<fail> and message.
     *
     * @param message to give information about response.
     * @return
     */
    protected ResponseEntity<ApiResponse<T>> notFoundResponse(String message) {

        logResponseData(HttpStatus.NOT_FOUND.toString(), ApiStatus.FAIL.toString(), message, "No data");

        ApiResponse<T> response = ApiResponse.<T>builder()
                .status(ApiStatus.FAIL.toString())
                .message(message)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    // -----------------------------------------------------------------------

    /**
     * Return 500 Internal Server Error with status<error> and message.
     *
     * @param message to give information about response.
     * @return {@link ApiResponse}
     */
    protected ResponseEntity<ApiResponse<T>> internalServerErrorResponse(String message) {

        logResponseData(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ApiStatus.ERROR.toString(), message,
                "No data");

        ApiResponse<T> response = ApiResponse.<T>builder()
                .status(ApiStatus.ERROR.toString())
                .message(message)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // -----------------------------------------------------------------------

    protected ResponseEntity<Resource> resourceResponse(HttpServletRequest request, Resource resource) {
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // -----------------------------------------------------------------------

    protected ResponseEntity<?> staffLoginResponse(StaffResponse staffResponse, String password, RefreshToken refreshToken, String message) {
        JwtTokenResponse jwtToken = generateToken(staffResponse.getName(), password);
        jwtToken.setRefreshToken(refreshToken.getToken());

        StaffLoginResponse agentLoginResponse = new StaffLoginResponse(jwtToken, staffResponse);
        logResponseData(HttpStatus.OK.toString(), ApiStatus.SUCCESS.toString(), message,
                agentLoginResponse);

        ApiResponse<StaffLoginResponse> response = ApiResponse.<StaffLoginResponse>builder()
                .message(ApiMessage.STAFF_LOGIN_SUCCESSFUL)
                .data(agentLoginResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    // -----------------------------------------------------------------------

    private JwtTokenResponse generateToken(String name, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(name, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return new JwtTokenResponse(jwt);
    }

    // -----------------------------------------------------------------------

    /**
     * Create refresh token response with access token for staff.
     */
    protected ResponseEntity<ApiResponse<JwtTokenResponse>> refreshTokenResponse(Staff staff, String message) {
        JwtTokenResponse jwtToken = refreshToken(staff);

        ApiResponse<JwtTokenResponse> response = ApiResponse.<JwtTokenResponse>builder()
                .message(message)
                .data(jwtToken)
                .build();

        return ResponseEntity.ok(response);
    }


    /**
     * Generate JWT token using refresh token for staff.
     */
    protected JwtTokenResponse refreshToken(Staff staff) {
        String jwt = tokenProvider.refreshToken(staff);
        return new JwtTokenResponse(jwt);
    }

    // -----------------------------------------------------------------------

    /**
     * Log request method and request parameters.
     */
    protected void logRequestData(String requestData) {
        log.info(ControllerLogUtil.strControllerPattern, requestData);
    }

    /**
     * Log request method, parameters and request body.
     */
    protected void logRequestData(String requestData, Object requestBody) {
        log.info(ControllerLogUtil.strControllerPatternWithBody, requestData, requestBody);
    }

    // -----------------------------------------------------------------------

    /**
     * Log response status and its information.
     */
    private void logResponseData(String httpStatus, String responseStatus, String message, Object data) {
        log.info(ControllerLogUtil.strResponsePattern, httpStatus, responseStatus, message, data);
    }

}
