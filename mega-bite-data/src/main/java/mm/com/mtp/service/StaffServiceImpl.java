package mm.com.mtp.service;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.exception.BadRequestException;
import mm.com.mtp.exception.ConflictException;
import mm.com.mtp.exception.ResourceNotFoundException;
import mm.com.mtp.mapper.StaffMapper;
import mm.com.mtp.model.RefreshToken;
import mm.com.mtp.model.Staff;
import mm.com.mtp.payload.StaffRequest;
import mm.com.mtp.payload.StaffResponse;
import mm.com.mtp.repository.RefreshTokenRepository;
import mm.com.mtp.repository.StaffRepository;
import mm.com.mtp.type.StaffRole;
import mm.com.mtp.utils.AppConstant;
import mm.com.mtp.utils.LoggingFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static mm.com.mtp.utils.AppConstant.*;

/**
 * Created by Sai Pann Hmwe on 1/9/2021.
 */
@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    StaffMapper staffMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Override
    public StaffResponse registerStaff(StaffRequest staffRequest) {
        logServiceData("Register staff with < " + staffRequest + " >.");

        // Confirm the phone number is valid and not is used
        validatePhoneNumber(staffRequest.getPhoneNumber());

        Staff staff = staffMapper.toStaff(staffRequest);
        staff.setPassword(passwordEncoder.encode("123456"));

        return staffMapper.toStaffResponse(staffRepository.save(staff));
    }

    @Override
    public List<StaffResponse> getStaff() {
        logServiceData("Get all staff");
        return staffMapper.toStaffResponses(staffRepository.findAll());
    }

    @Override
    public StaffResponse getStaffDetail(Long staffId) {
        logServiceData("Get staff detail");
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "Id", staffId));
        return staffMapper.toStaffResponse(staff);
    }

    @Override
    public List<StaffResponse> getStaffByRole(StaffRole role) {
        logServiceData("Get all staff by role.");

        return staffMapper.toStaffResponses(staffRepository.findAllByRole(role));
    }

    @Override
    public StaffResponse updateStaff(Long staffId, StaffRequest staffRequest) {
        logServiceData("Update staff with id < " + staffId + " >.");

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "Id", staffId));

        staff = staffMapper.toStaff(staff, staffRequest);

        return staffMapper.toStaffResponse(staffRepository.save(staff));
    }

    @Override
    public void deleteStaff(Long staffId) {
        logServiceData("Delete staff with id < " + staffId + " >.");
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "Id", staffId));
        staffRepository.delete(staff);
    }

    @Override
    public StaffResponse login(String name, String password) {
        logServiceData("Staff login with name <" + name + ">");

        Staff staff = staffRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(STAFF_NOT_FOUND));

        if (!passwordEncoder.matches(password, staff.getPassword())) {
            throw new BadRequestException(STAFF_INVALID);
        }

        return staffMapper.toStaffResponse(staff);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "id", id));
        staff.setPassword(passwordEncoder.encode(newPassword));
        staffRepository.save(staff);
    }

    @Override
    public void changeDeliveryManPassword(Long staffId, String oldPassword, String newPassword) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "id", staffId));

        // Check old password is valid or not
        if (!passwordEncoder.matches(oldPassword, staff.getPassword())) {
            throw new BadRequestException("The old password is invalid.");
        }

        // Check whether new password is same as password
        if (passwordEncoder.matches(newPassword, staff.getPassword())) {
            throw new BadRequestException("The new password is same with your old password.");
        }

        // Check the new password length is valid or not
        if (!(newPassword.length() >= MIN_PASSWORD_LENGTH && newPassword.length() <= MAX_PASSWORD_LENGTH)) {
            throw new BadRequestException("The password length must be between 6 and 16.");
        }

        staff.setPassword(passwordEncoder.encode(newPassword));
        staffRepository.save(staff);
    }

    @Override
    public void changeActiveStatus(Long staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "Id", staffId));
        staffRepository.save(staff);
    }

    @Override
    public void resetPassword(Long staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "Id", staffId));
        staff.setPassword(passwordEncoder.encode(AppConstant.DEFAULT_PASSWORD));
        staffRepository.save(staff);
    }

    @Override
    public List<StaffResponse> getOfficeStaff() {
        List<StaffRole> roles = new ArrayList<>();
        roles.add(StaffRole.ADMIN);
        roles.add(StaffRole.STAFF);

        return staffMapper.toStaffResponses(staffRepository.findAllByRoleIn(roles));
    }

    @Override
    public void changeInActiveStatus(Long staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "Id", staffId));
        staffRepository.save(staff);
    }

    @Override
    public Map<String, String> validateStaffRequest(StaffRequest staffRequest) {
        Map<String, String> fieldErrors = new HashMap<>();
        String phoneNumber = staffRequest.getPhoneNumber();
        String userName = staffRequest.getUserName();

        if (StringUtils.hasText(userName) && staffRepository.existsByUserNameIgnoreCase(userName)) {
            fieldErrors.put("userName", "user name already exist");
        }

        if (StringUtils.hasText(phoneNumber) && staffRepository.existsByPhoneNumberIgnoreCase(phoneNumber)) {
            fieldErrors.put("phoneNumber", "User phone number already exist");
        }

        return fieldErrors;
    }

    @Override
    public Map<String, String> validateStaffEditRequest(StaffRequest staffRequest) {
        Map<String, String> fieldErrors = new HashMap<>();
        String phoneNumber = staffRequest.getPhoneNumber();
        String userName = staffRequest.getUserName();

        if (StringUtils.hasText(userName) && staffRepository.existsByUserNameAndIdNot(userName, staffRequest.getId())) {
            fieldErrors.put("userName", "user name already exist");
        }

        if (StringUtils.hasText(phoneNumber) && staffRepository.existsByPhoneNumberAndIdNot(phoneNumber, staffRequest.getId())) {
            fieldErrors.put("phoneNumber", "User phone number already exist");
        }

        return fieldErrors;
    }

    @Override
    public RefreshToken createFreshToken(Long staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));

        RefreshToken newToken = new RefreshToken(staff);
        RefreshToken oldToken = refreshTokenRepository.findByStaff(staff).orElse(null);
        if (oldToken == null) {
            return refreshTokenRepository.save(newToken);
        }
        oldToken.setExpiryDateTime(newToken.getExpiryDateTime());
        oldToken.setToken(newToken.getToken());
        return refreshTokenRepository.save(oldToken);
    }

    private void validatePhoneNumber(String phoneNumber) {

        // Phone number is empty or not
        if (phoneNumber.isEmpty()) {
            throw new BadRequestException(PHONE_NUMBER_EMPTY);
        }

        // Phone number length exceeds or not
        if (phoneNumber.length() < MIN_PHONE_NUMBER_LENGTH || phoneNumber.length() > MAX_PHONE_NUMBER_LENGTH) {
            throw new BadRequestException(PHONE_NUMBER_LENGTH_INVALID);
        }

        // Phone number already exists or not
        if (existsByPhoneNumber(phoneNumber)) {
            throw new ConflictException(PHONE_NUMBER_ALREADY_EXIST);
        }
    }

    private boolean existsByPhoneNumber(String phoneNumber) {
        return staffRepository.existsByPhoneNumber(phoneNumber);
    }

    /**
     * Log service level information for {@link StaffServiceImpl}.
     */
    private void logServiceData(String serviceData) {
        log.info(LoggingFormatUtil.strServicePattern, serviceData);
    }

}
