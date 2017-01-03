package com.os.excel;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Jian Zhu on 12/26/2016.
 */
public class ExcelProcessServiceImpl {

    public void processXlsx(InputStream inputStream) throws Exception {
        OPCPackage p = OPCPackage.open(inputStream);
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(p);
        XSSFReader xssfReader = new XSSFReader(p);
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();

        p.close();
    }
}
