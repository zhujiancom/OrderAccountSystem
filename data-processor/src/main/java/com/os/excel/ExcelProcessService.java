package com.os.excel;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Jian Zhu on 12/26/2016.
 */
public interface ExcelProcessService {
    void processXlsx(InputStream inputStream) throws Exception;
}
