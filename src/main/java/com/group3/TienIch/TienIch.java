package com.group3.TienIch;

public class TienIch {

	public static String xuLiTuKhoaTimKiem(String tuKhoa) {
		String res = tuKhoa.trim().toLowerCase();
		//Loại tất cả các kí tự không thuộc a-z A-Z 0-9 ngoài trừ các nguyên âm có dấu =))
		res = res.replaceAll("[\\W&&[^áàảãạăắẳằẵặâấầẩẫậóòỏõọôốồổỗộơớởờỡợúùủũụưứừửữựíìỉĩịéèẻẽẹêếểềễệ]]", " ");
		//Loại trừ khoảng trắng cạnh nhau
		res = res.replaceAll("\\s+", " ");
		return res;
	}
	
}
