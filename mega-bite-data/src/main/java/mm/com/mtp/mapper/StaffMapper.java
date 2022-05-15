package mm.com.mtp.mapper;

import mm.com.mtp.model.Staff;
import mm.com.mtp.payload.ResponsibilityResponse;
import mm.com.mtp.payload.StaffRequest;
import mm.com.mtp.payload.StaffResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by Sai Pann Hmwe on 1/9/2021.
 */
@Mapper(componentModel = "spring")
public interface StaffMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "lastModifiedDate", ignore = true),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "name", source = "staffRequest.name"),
    })
    Staff toStaff(StaffRequest staffRequest);

    @Mappings({
            @Mapping(target = "createdDate", source = "staff.createdDate"),
            @Mapping(target = "lastModifiedDate", ignore = true),
            @Mapping(target = "id", source = "staff.id"),
            @Mapping(target = "password", source = "staff.password"),
            @Mapping(target = "name", source = "staffRequest.name"),
            @Mapping(target = "userName", source = "staffRequest.userName"),
            @Mapping(target = "phoneNumber", source = "staffRequest.phoneNumber"),
            @Mapping(target = "role", source = "staffRequest.role")
    })
    Staff toStaff(Staff staff, StaffRequest staffRequest);

    StaffResponse toStaffResponse(Staff staff);

    List<StaffResponse> toStaffResponses(List<Staff> staff);

}
