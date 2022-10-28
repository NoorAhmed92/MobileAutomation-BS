package com.kinship.automation.utils.allure;

import com.kinship.automation.constants.Constants;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AllureUtils {

//    allure generate -c target/allure-results -o reports/allure-results-html

    public static void copyAllureHistory() throws IOException {
        if(new File("TestReport/history").exists()){
            if( !new File("target/allure-results/history/"+ Constants.platform.toUpperCase()).exists())
                new File("target/allure-results/history/"+Constants.platform.toUpperCase()).mkdir();
            System.out.println("copying history");
            FileUtils.copyDirectory(new File("TestReport/history"), new File("target/allure-results/history/"+Constants.platform.toUpperCase()));
            Arrays.stream(new File("target/allure-results/").listFiles()).forEach(File::delete);
        }
    }

    public static void generateReport() throws Exception {
        System.out.println("Generating report");
        String cmd = "allure generate target/allure-results --clean -o TestReport";
        Runtime run = Runtime.getRuntime();
        try {
            Process pr = run.exec(cmd);
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
            }
//            pr.destroy();
        }
        catch(Exception e){
            System.out.println("Allure command failed, need to be sure if we have allure CLI installed");
        }
    }
}
