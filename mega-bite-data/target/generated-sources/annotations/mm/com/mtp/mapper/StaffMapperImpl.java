package mm.com.mtp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import mm.com.mtp.model.Staff;
import mm.com.mtp.payload.StaffRequest;
import mm.com.mtp.payload.StaffResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-11T20:44:14+0630",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public Staff toStaff(StaffRequest staffRequest) {
        if ( staffRequest == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setName( staffRequest.getName() );
        staff.setUserName( staffRequest.getUserName() );
        staff.setPhoneNumber( staffRequest.getPhoneNumber() );
        staff.setRole( staffRequest.getRole() );

        return staff;
    }

    @Override
    public Staff toStaff(Staff staff, StaffRequest staffRequest) {
        if ( staff == null && staffRequest == null ) {
            return null;
        }

        Staff staff1 = new Staff();

        if ( staff != null ) {
            staff1.setPassword( staff.getPassword() );
            staff1.setCreatedDate( staff.getCreatedDate() );
            staff1.setId( staff.getId() );
        }
        if ( staffRequest != null ) {
            staff1.setPhoneNumber( staffRequest.getPhoneNumber() );
            staff1.setRole( staffRequest.getRole() );
            staff1.setName( staffRequest.getName() );
            staff1.setUserName( staffRequest.getUserName() );
        }

        return staff1;
    }

    @Override
    public StaffResponse toStaffResponse(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        StaffResponse staffResponse = new StaffResponse();

        staffResponse.setId( staff.getId() );
        staffResponse.setName( staff.getName() );
        staffResponse.setUserName( staff.getUserName() );
        staffResponse.setPhoneNumber( staff.getPhoneNumber() );
        staffResponse.setRole( staff.getRole() );

        return staffResponse;
    }

    @Override
    public List<StaffResponse> toStaffResponses(List<Staff> staff) {
        if ( staff == null ) {
            return null;
        }

        List<StaffResponse> list = new ArrayList<StaffResponse>( staff.size() );
        for ( Staff staff1 : staff ) {
            list.add( toStaffResponse( staff1 ) );
        }

        return list;
    }
}
