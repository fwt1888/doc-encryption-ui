package core.utils;

public class ByteUtil {
	
	public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
	
    public static byte[] mergeByteArrays(byte[] array1, byte[] array2) {

        int length1 = array1.length;
        int length2 = array2.length;

        byte[] mergedArray = new byte[length1 + length2];

        System.arraycopy(array1, 0, mergedArray, 0, length1);
        System.arraycopy(array2, 0, mergedArray, length1, length2);

        return mergedArray;
    }


}
