package dataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import util.ExcelUtils;

public class ExcelDataProvider {

	@DataProvider(name = "TestData")
    public Object[][] getData() throws IOException {
        String path = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
        ExcelUtils.LoadExcel(path, "Sheet1");
        return ExcelUtils.getExcelData();
    }

}
