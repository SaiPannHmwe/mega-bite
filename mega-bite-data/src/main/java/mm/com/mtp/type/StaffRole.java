package mm.com.mtp.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Sai Pann Hmwe on 1/9/2021.
 */
@AllArgsConstructor
public enum StaffRole {

    ADMIN("Admin"),

    STAFF("Staff"),

    DELIVERY_MAN("Delivery Man");

    @Getter
    private String role;

    @JsonValue
    public String getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return this.role;
    }

}
