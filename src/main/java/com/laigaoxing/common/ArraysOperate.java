package com.laigaoxing.common;

/**
 * 数组操作类
 * 
 * @author jeff
 * 
 */
public class ArraysOperate {

	private static String lineSeparator = java.security.AccessController
			.doPrivileged(new sun.security.action.GetPropertyAction(
					"line.separator"));

	private static String defaultSeparate = " ";

	public static int output(Object[] args) {
		return output(args, defaultSeparate, 0);
	}

	public static int output(Object[] args, String separate) {
		return output(args, separate, 0);
	}

	public static int output(Object[] args, String separate, int lineNo) {
		int rst = 0;
		if (args == null) {
			return 0;
		}
		System.out.print(outputStr(args, separate, lineNo));
		rst = args.length;
		return rst;
	}

	public static String outputStr(Object[] args) {
		return outputStr(args, defaultSeparate, 0);
	}

	public static String outputStr(Object[] args, String separate) {
		return outputStr(args, separate, 0);
	}

	public static String outputStr(Object[] args, String separate, int lineNo) {
		if (args == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (Object c : args) {
			sb.append(c);
			i++;
			if (args.length != i
					&& ((lineNo != 0 && i % lineNo != 0) || lineNo == 0)) {
				sb.append(separate);
			}
			if (lineNo != 0 && i % lineNo == 0 && args.length != i) {
				sb.append(outputLineStr());
			}
		}
		sb.append(outputLineStr());
		return sb.toString();
	}

	public static int output(char[] args) {
		return output(args, defaultSeparate, 0);
	}

	public static int output(char[] args, String separate) {
		return output(args, separate, 0);
	}

	public static int output(char[] args, String separate, int lineNo) {
		int rst = 0;
		if (args == null) {
			return 0;
		}
		System.out.print(outputStr(args, separate, lineNo));
		rst = args.length;
		return rst;
	}

	public static String outputStr(char[] args) {
		return outputStr(args, defaultSeparate, 0);
	}

	public static String outputStr(char[] args, String separate) {
		return outputStr(args, separate, 0);
	}

	public static String outputStr(char[] args, String separate, int lineNo) {
		if (args == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (char c : args) {
			sb.append(c);
			i++;
			if (args.length != i
					&& ((lineNo != 0 && i % lineNo != 0) || lineNo == 0)) {
				sb.append(separate);
			}
			if (lineNo != 0 && i % lineNo == 0 && args.length != i) {
				sb.append(outputLineStr());
			}
		}
		sb.append(outputLineStr());
		return sb.toString();
	}

	public static int output(byte[] args) {
		return output(args, defaultSeparate, 0);
	}

	public static int output(byte[] args, String separate) {
		return output(args, separate, 0);
	}

	public static int output(byte[] args, String separate, int lineNo) {
		int rst = 0;
		if (args == null) {
			return 0;
		}
		System.out.print(outputStr(args, separate, lineNo));
		rst = args.length;
		return rst;
	}

	public static String outputStr(byte[] args) {
		return outputStr(args, defaultSeparate, 0);
	}

	public static String outputStr(byte[] args, String separate) {
		return outputStr(args, separate, 0);
	}

	public static String outputStr(byte[] args, String separate, int lineNo) {
		if (args == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (byte c : args) {
			sb.append(c);
			i++;
			if (args.length != i
					&& ((lineNo != 0 && i % lineNo != 0) || lineNo == 0)) {
				sb.append(separate);
			}
			if (lineNo != 0 && i % lineNo == 0 && args.length != i) {
				sb.append(outputLineStr());
			}
		}
		sb.append(outputLineStr());
		return sb.toString();
	}

	public static int output(int[] args) {
		return output(args, defaultSeparate, 0);
	}

	public static int output(int[] args, String separate) {
		return output(args, separate, 0);
	}

	public static int output(int[] args, String separate, int lineNo) {
		int rst = 0;
		if (args == null) {
			return 0;
		}
		System.out.print(outputStr(args, separate, lineNo));
		rst = args.length;
		return rst;
	}

	public static String outputStr(int[] args) {
		return outputStr(args, defaultSeparate, 0);
	}

	public static String outputStr(int[] args, String separate) {
		return outputStr(args, separate, 0);
	}

	public static String outputStr(int[] args, String separate, int lineNo) {
		if (args == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (int c : args) {
			sb.append(c);
			i++;
			if (args.length != i
					&& ((lineNo != 0 && i % lineNo != 0) || lineNo == 0)) {
				sb.append(separate);
			}
			if (lineNo != 0 && i % lineNo == 0 && args.length != i) {
				sb.append(outputLineStr());
			}
		}
		sb.append(outputLineStr());
		return sb.toString();
	}

	public static int output(double[] args) {
		return output(args, defaultSeparate, 0);
	}

	public static int output(double[] args, String separate) {
		return output(args, separate, 0);
	}

	public static int output(double[] args, String separate, int lineNo) {
		int rst = 0;
		if (args == null) {
			return 0;
		}
		int i = 0;
		for (double c : args) {
			System.out.print(c);
			i++;
			if (args.length != i
					&& ((lineNo != 0 && i % lineNo != 0) || lineNo == 0)) {
				System.out.print(separate);
			}
			if (lineNo != 0 && i % lineNo == 0 && args.length != i) {
				System.out.println();
			}
		}
		rst = args.length;
		System.out.println();
		return rst;
	}
	
	public static byte[] reverse(byte[] bs){
		byte[] rst = new byte[bs.length]; 
		for(int i=0;i<bs.length;i++){
			rst[i] = bs[bs.length-1-i];
		}
		return rst;
	}
	
	public static int[] reverse(int[] bs){
		int[] rst = new int[bs.length]; 
		for(int i=0;i<bs.length;i++){
			rst[i] = bs[bs.length-1-i];
		}
		return rst;
	}

	public static int output(float[] args) {
		return output(args, defaultSeparate, 0);
	}

	public static int output(float[] args, String separate) {
		return output(args, separate, 0);
	}

	public static int output(float[] args, String separate, int lineNo) {
		int rst = 0;
		if (args == null) {
			return 0;
		}
		System.out.print(outputStr(args, separate, lineNo));
		return rst;
	}

	public static String outputStr(float[] args) {
		return outputStr(args, defaultSeparate, 0);
	}

	public static String outputStr(float[] args, String separate) {
		return outputStr(args, separate, 0);
	}

	public static String outputStr(float[] args, String separate, int lineNo) {
		if (args == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (float c : args) {
			sb.append(c);
			i++;
			if (args.length != i
					&& ((lineNo != 0 && i % lineNo != 0) || lineNo == 0)) {
				sb.append(separate);
			}
			if (lineNo != 0 && i % lineNo == 0 && args.length != i) {
				sb.append(outputLineStr());
			}
		}
		sb.append(outputLineStr());
		return sb.toString();
	}

	private static String outputLineStr() {
		return lineSeparator;
	}
}
