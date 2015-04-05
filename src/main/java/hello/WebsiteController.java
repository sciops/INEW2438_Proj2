/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.util.locale.StringTokenIterator;

/**
 *
 * @author Stephen R. Williams
 */
@RestController
public class WebsiteController {

    private final String filepath = "E:\\Users\\home\\Documents\\NetBeansProjects\\" + "INEW2438_Proj2.csv";
    private final AtomicLong counter = new AtomicLong();

    //handles all RequestMethod types
    @RequestMapping(value = "/website/test")//, method = RequestMethod.GET
    public Website website(
            @RequestParam(value = "cat", defaultValue = "defaultCategory") String category,
            @RequestParam(value = "url", defaultValue = "http://www.example.com") String url
    ) {
        return new Website(counter.incrementAndGet(), category, url);
    }

    @RequestMapping(value = "/store")//, method = RequestMethod.GET
    public Website store(
            @RequestParam(value = "cat", defaultValue = "defaultCategory") String category,
            @RequestParam(value = "url", defaultValue = "http://www.example.com") String url
    ) throws IOException {
        //no control for unique id 
        Website website = new Website(counter.incrementAndGet(), category, url);
        storeWebsite(website);
        return website;
    }

    private void storeWebsite(Website website) throws IOException {
        String record = website.toString();
        File file = new File(filepath);
        boolean newFile = false;
        if (!(file.exists())) {
            file.createNewFile();
            newFile = true;
        }
        FileWriter fw = fw = new FileWriter(file, true);//true for append mode
        BufferedWriter bw = new BufferedWriter(fw);
        if (!(newFile)) {
            bw.newLine();
        }
        bw.append(record);
        bw.close();
    }

    @RequestMapping(value = "/grab")//, method = RequestMethod.GET
    public Website grab(
            @RequestParam(value = "id", defaultValue = "1") String id_s
    ) throws IOException {
        long id = Long.parseLong(id_s);
        Website website = grabWebsite(id);
        if (website == null) {
            return new Website(-404, "NOT FOUND", "NOT FOUND");
        }
        return website;
    }

    private Website grabWebsite(long id) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        String thisLine = "";
        while ((thisLine = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(thisLine, ",");
            long id_comp = Long.parseLong(st.nextToken());
            String cat = st.nextToken();
            String url = st.nextToken();
            if (id_comp == id) {
                return new Website(id_comp, cat, url);
            }
        }
        return null;
    }

    @RequestMapping(value = "/list")//, method = RequestMethod.GET
    public String list() throws IOException {
        String output = "";
        output = grabList();
        return output;
    }

    private String grabList() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        String thisLine = "", output = "";
        while ((thisLine = br.readLine()) != null) {
            output += thisLine + "\n";
        }
        return output;
    }

}
