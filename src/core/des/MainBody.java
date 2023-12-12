package core.des;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 算法主体部分
 * 
 */

public class MainBody {

	private int[][] groupText;// 分组后的明文，每组64bit
	private int[][] subkeys;// 16个子密钥，每组48bit
	private int flag;// 0表示加密，1表示解密

	public MainBody(byte[] text, String key, int flag) throws UnsupportedEncodingException {
		if (flag == 1) {
			int text_length = text.length;
			byte[] text_new = (text.length + "@").getBytes();
			int text_new_length = text_new.length;
			text_new = Arrays.copyOf(text_new, text_new_length + text_length);
			System.arraycopy(text, 0, text_new, text_new_length, text_length);
			this.groupText = GroupText.groupText(text_new);
		} else {
			this.groupText = GroupText.groupText(text);
		}

		this.subkeys = GenerateSubkeys.generateSubkeys(key);
		this.flag = flag;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		final String TEXT = "hello, world";
		final String KEY = "fwt1888";
		System.out.println("加密前: " + TEXT + "\n");

		long before = System.currentTimeMillis();
		byte[] c = new MainBody(TEXT.getBytes(), KEY, 1).mainBody();
		long after = System.currentTimeMillis();
		System.out.println("加密后: " + new String(c));
		System.out.println("加密时长: " + (after - before) + "ms\n");

		before = System.currentTimeMillis();
		byte[] p = new MainBody(c, KEY, 0).mainBody();
		after = System.currentTimeMillis();
		System.out.println("解密后: " + new String(p));
		System.out.println("解密时长: " + (after - before) + "ms");
	}


	public byte[] mainBody() throws UnsupportedEncodingException {
		byte[] result0 = new byte[8 * groupText.length];
		for (int g = 0; g < this.groupText.length; g++) {
			int[] IP_result = new int[64];
			for (int i = 0; i < 64; i++) {
				IP_result[i] = groupText[g][Table.IP[i] - 1];
			}

			if (flag == 1) { 
				for (int i = 0; i < 16; i++) {
					eachRound(IP_result, i);
				}
			} else { 
				for (int i = 15; i > -1; i--) {
					eachRound(IP_result, i);
				}
			}

			int[] IP_1_result = new int[64];
			for (int i = 0; i < 64; i++) {
				IP_1_result[i] = IP_result[Table.IP_1[i] - 1];
			}

			byte[] tmp = new byte[8];
			for (int i = 0; i < 8; i++) {
				tmp[i] = (byte) ((IP_1_result[8 * i] << 7) + (IP_1_result[8 * i + 1] << 6)
						+ (IP_1_result[8 * i + 2] << 5) + (IP_1_result[8 * i + 3] << 4) + (IP_1_result[8 * i + 4] << 3)
						+ (IP_1_result[8 * i + 5] << 2) + (IP_1_result[8 * i + 6] << 1) + (IP_1_result[8 * i + 7]));
			}

			System.arraycopy(tmp, 0, result0, g * 8, 8);
		}

		if (flag == 0) {
			for (int i = 0; i < result0.length; i++) {
				if (result0[i] == (byte) 64) {
					byte[] tmp0 = new byte[i];
					System.arraycopy(result0, 0, tmp0, 0, i);
					int j = Integer.parseInt(new String(tmp0));
					byte[] result1 = new byte[j];
					System.arraycopy(result0, i + 1, result1, 0, j);
					return result1;
				}
			}
		}
		return result0;
	}


	public void eachRound(int[] IP_result, int roundNum) {
		int[] L0 = new int[32];
		int[] R0 = new int[32];
		int[] L1 = new int[32];
		int[] R1 = new int[32];
		int[] f_result = new int[32];
		System.arraycopy(IP_result, 0, L0, 0, 32);
		System.arraycopy(IP_result, 32, R0, 0, 32);
		L1 = R0;

		f_result = FFunction.fFuction(R0, subkeys[roundNum]);

		for (int j = 0; j < 32; j++) {
			R1[j] = L0[j] ^ f_result[j];
			if (((flag == 1) && (roundNum == 15)) || ((flag == 0) && (roundNum == 0))) {
				IP_result[j] = R1[j];
				IP_result[j + 32] = L1[j];
			} else {
				IP_result[j] = L1[j];
				IP_result[j + 32] = R1[j];
			}
		}
	}

}