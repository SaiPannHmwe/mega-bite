package mm.com.mtp.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Created by Sai Pann Hmwe on 1/31/2021.
 */
@AllArgsConstructor
public enum IssueType {

    NO_ANS("Don't answer the phone call"),

    OTHERS("Others"),

    FULL_RETURN("Fully Return"),

    PARTIAL_RETURN("Partial Return"),

    WRONG_ADDRESS("Wrong Order Address"),

    CHANGE_ADDRESS("Change Order Address"),

    DATE_CHANGE("Date Change"),

    WRONG_VALUE("Wrong Value"),

    WRONG_ORDER("Wrong Order");

    @Getter
    @JsonValue
    private String type;

    @JsonCreator
    public static IssueType fromString(String source) {
        if (source == null) return null;

        return Stream.of(IssueType.values()).filter(e -> e.getType().equalsIgnoreCase(source)).findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return this.type;
    }

}
