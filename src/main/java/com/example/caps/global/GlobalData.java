package com.example.caps.global;

import java.util.ArrayList;
import java.util.List;

import com.example.caps.model.Product;

public class GlobalData {
	public static List<Product> cart;
	static {
		cart = new ArrayList<Product>();
		
	}

}
