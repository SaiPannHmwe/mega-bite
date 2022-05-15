package mm.com.mtp.controller;

import mm.com.mtp.model.RefreshToken;
import mm.com.mtp.model.Staff;
import mm.com.mtp.payload.StaffRequest;
import mm.com.mtp.payload.StaffResponse;
import mm.com.mtp.response.ApiResponse;
import mm.com.mtp.response.ApiStatus;
import mm.com.mtp.service.RefreshTokenService;
import mm.com.mtp.service.StaffService;
import mm.com.mtp.type.StaffRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static mm.com.mtp.response.ApiMessage.*;

/**
 * Created by Sai Pann Hmwe on 1/9/2021.
 */
@RestController
@RequestMapping("/api/v1")
public class StaffController extends BaseController<StaffResponse> {

    @Autowired
    StaffService staffService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> staffLogin(@RequestParam Map<String, String> loginRequest) {
        String name = loginRequest.get("name").trim();
        String password = loginRequest.get("password").trim();

        logRequestData("Receive POST Staff login with name <" + name + ">");
        StaffResponse staffResponse = staffService.login(name, password);
        RefreshToken refreshToken = staffService.createFreshToken(staffResponse.getId());

        return staffLoginResponse(staffResponse, password, refreshToken, STAFF_LOGIN_SUCCESSFUL);
    }

    /*
     * Refresh Access Token
     */
    @GetMapping("/refresh-access-token")
    public ResponseEntity<?> refreshToken(@RequestParam String token) {
        logRequestData("Refresh access token with token<" + token + ">");

        RefreshToken refreshToken = refreshTokenService.getRefreshToken(token);

        if (refreshToken == null) {
            return okResponse(ApiStatus.FAIL, ("Token invalid."));
        }

        if (!refreshToken.getExpiryDateTime().isAfter(LocalDateTime.now())) {
            return okResponse(ApiStatus.FAIL, ("Token expire."));
        }

        Staff staff = refreshToken.getStaff();

        return refreshTokenResponse(staff, ("Token refresh success."));
    }

    /**
     * Staff Registration
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<StaffResponse>> registerStaff(@RequestBody @Valid StaffRequest staffRequest) {
        logRequestData("Register staff with < " + staffRequest + " > .");

        StaffResponse staffResponse = staffService.registerStaff(staffRequest);

        if (staffResponse == null) {
            return internalServerErrorResponse(STAFF_REGISTER_ERROR);
        }
        return createdResponse(STAFF_REGISTER_SUCCESSFUL, staffResponse);
    }

    /**
     * Get Staffs
     */
    @GetMapping("/staffs")
    public ResponseEntity<ApiResponse<List<StaffResponse>>> getStaff(@RequestParam(required = false) StaffRole role) {
        logRequestData("Get staff");
        if (role != null) {
            return okResponse(STAFF_RETRIEVE_SUCCESSFUL, staffService.getStaffByRole(role));
        }
        return okResponse(STAFF_RETRIEVE_SUCCESSFUL, staffService.getStaff());
    }

    /**
     * Get Staff Detail
     */
    @GetMapping("/staffs/{staffId}")
    public ResponseEntity<ApiResponse<StaffResponse>> getStaffDetail(@PathVariable Long staffId) {
        logRequestData("Get staff detail for id < " + staffId + " >.");
        return okResponse(STAFF_DETAIL_RETRIEVE_SUCCESSFUL, staffService.getStaffDetail(staffId));
    }

    /**
     * Update Staff
     */
    @PutMapping("/staffs/{staffId}")
    public ResponseEntity<ApiResponse<StaffResponse>> updateStaff(@PathVariable Long staffId,
                                                                  @RequestBody StaffRequest staffRequest) {
        logRequestData("Update staff with id < " + staffId + " >.");
        return okResponse(STAFF_UPDATE_SUCCESSFUL, staffService.updateStaff(staffId, staffRequest));
    }

    /**
     * Delete Staff
     */
    @DeleteMapping("/staffs/{staffId}")
    public ResponseEntity<ApiResponse<StaffResponse>> deleteStaff(@PathVariable Long staffId) {
        logRequestData("Delete staff with id < " + staffId + " >.");
        staffService.deleteStaff(staffId);
        return okResponse(ApiStatus.SUCCESS, STAFF_DELETE_SUCCESSFUL);
    }

    /**
     * Delete Staff
     */
    @PutMapping("/staffs/{staffId}/changePassword")
    public ResponseEntity<ApiResponse<StaffResponse>> changePassword(@PathVariable Long staffId,
                                                                     @RequestParam String oldPassword,
                                                                     @RequestParam String newPassword) {
        logRequestData("Change password for staff id < " + staffId + " >.");
        staffService.changeDeliveryManPassword(staffId, oldPassword, newPassword);
        return okResponse(ApiStatus.SUCCESS, STAFF_PASSWORD_CHANGE_SUCCESSFUL);
    }


}
