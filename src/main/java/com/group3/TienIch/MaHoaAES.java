package com.group3.TienIch;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MaHoaAES {
	
	private static String MAT_KHAU = "ZzZzZddllhhZzZzZ";
	private static SecretKeySpec skeySpec;

	public static String maHoa(String vanBan) {
		String matKhauMaHoa = null;
		try {
			skeySpec = new SecretKeySpec(MAT_KHAU.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			matKhauMaHoa = Base64.getEncoder().encodeToString(cipher.doFinal(vanBan.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matKhauMaHoa;
	}

}