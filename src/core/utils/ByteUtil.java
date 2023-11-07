package core.utils;

public class ByteUtil {
	
	public static String byteArrayToHex(byte[] bytes) {
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
    
    /**
     * byte[]为大端字节序（Big-Endian），其中最高有效字节存储在数组的第一个元素中
     * @param value
     * @return
     */
    public static byte[] intToByteArray(int value) {
    	byte[] result = new byte[4]; // 一个 int 值占 4 个字节

        for (int i = 0; i < 4; i++) {
            result[i] = (byte) (value >> ((3-i) * 8));
        }

        return result;
    }

    public static int byteArrayToInt(byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException
            ("Input byte array must be 4 bytes in length");
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            result |= bytes[i] << ((3-i) * 8);
        }

        return result;
    }


    /**
	 * 添加长度字段(int存储MAC、K长度，先在首部存储MAC长度，再存储加密后的K长度）
	 * @param infoLength 
	 */
	public static byte[] addLengthField(int infoLength, byte[] originBytes) {
		byte[] bytesToAdd = intToByteArray(infoLength);
		
		return mergeByteArrays(bytesToAdd, originBytes);
	}
	
	/**
	 * 获取长度字段保存的int值
	 * @return
	 */
	public static int getLengthField(int byteLength, byte[] bytes) {
		
		byte[] lengthField = new byte[byteLength];
		System.arraycopy(bytes, 0, lengthField, 0, byteLength);
		return byteArrayToInt(lengthField);
		
	}
	
}
