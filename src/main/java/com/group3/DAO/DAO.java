package com.group3.DAO;

import java.util.List;

public interface DAO<T> {
	List<T> layTatCa();
	
	T layQuaMa(int ma);
	
	int taoMoi(T t);
	
	void sua(T t);
	
	void xoa(T t);
	
}
