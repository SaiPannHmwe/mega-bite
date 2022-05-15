package mm.com.mtp.utils;

public final class LoggingFormatUtil {

    public static String strControllerPattern = new StringBuilder(System.lineSeparator())
            .append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            .append(System.lineSeparator()).append(">{}.")
            .append(System.lineSeparator())
            .append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            .toString();

    public static String strControllerPatternWithBody = new StringBuilder(System.lineSeparator())
            .append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            .append(System.lineSeparator()).append(">{}.")
            .append(System.lineSeparator()).append(">Request Body : {}")
            .append(System.lineSeparator())
            .append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            .toString();

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

    public static String strResponsePattern = new StringBuilder(System.lineSeparator())
            .append("*********************************************************************************************************")
            .append(System.lineSeparator()).append("*code :{}")
            .append(System.lineSeparator()).append("*status :{}")
            .append(System.lineSeparator()).append("*message :{}")
            .append(System.lineSeparator()).append("*data : {}")
            .append(System.lineSeparator())
            .append("*********************************************************************************************************")
            .toString();

}
