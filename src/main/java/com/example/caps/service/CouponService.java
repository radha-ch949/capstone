package com.example.caps.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.caps.model.Coupon;
import com.example.caps.repository.CouponRepo;


@Service
@Transactional
public class CouponService {
	static CouponRepo couponRepo;
	
	@Autowired
	public CouponService(CouponRepo couponRepo) {
		this.couponRepo = couponRepo;

	}
	public static List<Coupon> getAllCoupon() {
		List<Coupon> coupons = new ArrayList<Coupon>();
		try {
			couponRepo.findAll().forEach(coupon1 -> {
				coupons.add(coupon1);
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coupons;
	}
	public void saveOrUpdate(Coupon coupons) {
		couponRepo.save(coupons);
	}
	public void delete(int coupon_id) {
		couponRepo.deleteById(coupon_id);
	}

	

}
