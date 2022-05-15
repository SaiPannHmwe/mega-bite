package mm.com.mtp.service;

import mm.com.mtp.model.RefreshToken;
import mm.com.mtp.payload.StaffRequest;
import mm.com.mtp.payload.StaffResponse;
import mm.com.mtp.type.StaffRole;

import java.util.List;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 1/9/2021.
 */
public interface StaffService {

    StaffResponse registerStaff(StaffRequest staffRequest);

    List<StaffResponse> getStaff();

    StaffResponse getStaffDetail(Long staffId);

    List<StaffResponse> getStaffByRole(StaffRole role);

    StaffResponse updateStaff(Long staffId, StaffRequest staffRequest);

    void deleteStaff(Long staffId);

    StaffResponse login(String name, String password);

    void changePassword(Long id, String newPassword);

    void changeDeliveryManPassword(Long staffId, String oldPassword, String newPassword);

    void changeActiveStatus(Long staffId);

    void resetPassword(Long staffId);

    List<StaffResponse> getOfficeStaff();

    void changeInActiveStatus(Long staffId);

    Map<String, String> validateStaffRequest(StaffRequest staffRequest);

    Map<String, String> validateStaffEditRequest(StaffRequest staffRequest);

    RefreshToken createFreshToken(Long id);

}
