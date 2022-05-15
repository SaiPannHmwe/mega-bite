package mm.com.mtp.utils;

public final class ControllerLogUtil {
	
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
