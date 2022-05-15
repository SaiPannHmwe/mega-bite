package mm.com.mtp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nay Myo Htet on 11/7/2019.
 */
@Component
public class AppConstant {

    public static String API_ENDPOINT;

    public static String ROOT_FILE_STORAGE_PATH;

    public static String DB_BACKUP_PATH;

    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd' 'HH:mm:ss";

    public static final String DEFAULT_PASSWORD = "123456";

    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 16;

    public static final int MIN_PHONE_NUMBER_LENGTH = 7;
    public static final int MAX_PHONE_NUMBER_LENGTH = 15;
    public static final String PHONE_NUMBER_LENGTH_INVALID = "Phone number length must be between " + MIN_PHONE_NUMBER_LENGTH + " and " + MAX_PHONE_NUMBER_LENGTH + ".";
    public static final String PHONE_NUMBER_EMPTY = "Phone number should not be empty.";
    public static final String PHONE_NUMBER_ALREADY_EXIST = "Phone number already in used.";

    public static final String STAFF_IN_ACTIVE = "Staff not active.";
    public static final String STAFF_INVALID = "Staff invalid.";
    public static final String STAFF_NOT_FOUND = "Staff not found.";

    public static final int LIMITED_AGE = 13;

    public static final String CASH = "Cash";

    public static List<Locale> LOCALES = Arrays.asList(new Locale("en"),
            new Locale("mm"));

    @Value("${api-endpoint}")
    public void setApiEndpoint(String apiEndpoint) {
        API_ENDPOINT = apiEndpoint;
    }

    @Value("${file.upload-dir}")
    public void setRootFileStoragePath(String rootFileStoragePath) {
        ROOT_FILE_STORAGE_PATH = rootFileStoragePath;
    }

    @Value("${db-backup-path}")
    public void setDbBackupPath(String dbBackupPath) {
        DB_BACKUP_PATH = dbBackupPath;
    }

}
