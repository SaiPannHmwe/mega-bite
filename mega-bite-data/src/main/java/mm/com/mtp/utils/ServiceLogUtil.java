package mm.com.mtp.utils;

public final class ServiceLogUtil {

    public static String strServicePattern = new StringBuilder(System.lineSeparator())
            .append("=========================================================================================================")
            .append(System.lineSeparator()).append("={}.")
            .append(System.lineSeparator())
            .append("=========================================================================================================")
            .toString();

    public static String strServiceOpenPattern = new StringBuilder(System.lineSeparator())
            .append("=========================================================================================================")
            .append(System.lineSeparator()).append("={} {}.")
            .append(System.lineSeparator())
            .toString();

    public static String strServiceClosePattern = new StringBuilder(System.lineSeparator())
            .append(">>>>>Total {} record(s) \t >>>>>Inserted {} row(s) \t >>>>>Updated {} row(s) \t >>>>>Skipped {} records(s)")
            .append(System.lineSeparator())
            .append("=========================================================================================================")
            .toString();

}
