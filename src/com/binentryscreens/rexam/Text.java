/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binentryscreens.rexam;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 *
 * @author Chris
 */
public class Text {
    
    public static void main(String args[]){
        
            HSSFRow[] rows = new HSSFRow[155];
    
    
//            HSSFCell cellB3AA = rows[3].getCell(0);
//            String value = cellB3AA.getRichStringCellValue().getString();
            
            String[] names = new String[155];
            HSSFCell [] hssCells = new HSSFCell[155];
            
            for (int i = 1; i < rows.length+1; i++) {
                
           System.out.println("hssCells["+i+"] = rows["+i+"].getCell(0);");
           System.out.println("names["+(i-1)+"] = hssCells["+i+"].getRichStringCellValue().getString();");
           System.out.println(" ");
        }
    
    
    }
    
}
