package com.haly.coupon.service;

import com.haly.coupon.model.Coupon;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
@Service
public class CouponService {

    private final Map<String, List<Coupon>> coupons = new ConcurrentHashMap<>();

    public void readCouponsFromGoogleDrive() throws Exception {
        String fileUrl = "https://docs.google.com/spreadsheets/d/1_uT7E2tg-umkr2pybxX1rfAqoenff2Vt/export?format=xlsx";
        
        URL url = new URL(fileUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setInstanceFollowRedirects(true);  // Allow redirects
        conn.connect();

        try (InputStream inputStream = url.openStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                
                String productId = row.getCell(0).getStringCellValue();
                String code = row.getCell(1).getStringCellValue();
                String description = row.getCell(2).getStringCellValue(); 
                double percentage = row.getCell(3).getNumericCellValue(); 

                Coupon coupon = new Coupon(productId, code, description, percentage);
                coupons.computeIfAbsent(productId, k -> new ArrayList<>()).add(coupon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public CouponService() {
    	try {
			readCouponsFromGoogleDrive();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//loadCouponsFromExcel("H:\\affiliate\\full1\\haly-coupon-backend\\src\\main\\resources\\static\\files\\coupons.xlsx");
    }

    private void loadCouponsFromExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // skip header row

                String productId = row.getCell(0).getStringCellValue();
                String code = row.getCell(1).getStringCellValue();
                String description = row.getCell(2).getStringCellValue(); 
                double percentage = row.getCell(3).getNumericCellValue(); 

                Coupon coupon = new Coupon(productId, code, description, percentage);
                coupons.computeIfAbsent(productId, k -> new ArrayList<>()).add(coupon);
            	System.out.println(coupon);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get coupons by product ID
    public List<Coupon> findById(String productId) {
        return coupons.getOrDefault(productId, Collections.emptyList());
    }

    // Get all coupons
    public Map<String, List<Coupon>> findAll() {
    	System.out.println(coupons);
        return coupons;
    }
}
