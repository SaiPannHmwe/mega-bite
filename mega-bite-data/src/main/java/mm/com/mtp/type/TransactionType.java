package mm.com.mtp.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Created by Sai Pann Hmwe on 12/15/2020.
 */
@AllArgsConstructor
public enum TransactionType {

    PENDING("Pending"),

    COMPLETED("Completed"),

    CREDIT("Credit"),

    DEBIT("Debit");

    @Getter
    @JsonValue
    private String type;

    @JsonCreator
    public static TransactionType fromString(String source) {
        if (source == null) return null;

        return Stream.of(TransactionType.values()).filter(e -> e.getType().equalsIgnoreCase(source)).findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return this.type;
    }

}
