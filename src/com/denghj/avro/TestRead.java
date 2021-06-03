package com.denghj.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TestRead
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/6/2 18:23
 * @Description 读
 */
public class TestRead {
    public static void main(String[] args) throws IOException {
//        DatumReader<User> reader = new SpecificDatumReader<User>(User.class);
//        DataFileReader<User> dataFileReader = new DataFileReader<User>(new File("D:\\project\\packeteye_V3\\packeteye-service\\20210603\\AVRO\\pe_security_event_20210603_092609-102609.avro"), reader);
//        User user = null;
//        while (dataFileReader.hasNext()) {
//            user = dataFileReader.next();
//            System.out.println(user);
//        }
        DatumReader<PeSecurityEvent> reader = new SpecificDatumReader<PeSecurityEvent>(PeSecurityEvent.class);
        DataFileReader<PeSecurityEvent> dataFileReader = new DataFileReader<PeSecurityEvent>(new File("D:\\project\\packeteye_V3\\packeteye-service\\20210603\\AVRO\\pe_security_event_20210603_092609-102609.avro"), reader);
        PeSecurityEvent PeSecurityEvent = null;
        List<PeSecurityEvent> list=new ArrayList<>();
        while (dataFileReader.hasNext()) {
            PeSecurityEvent = dataFileReader.next();
            list.add(PeSecurityEvent);
        }
        System.out.println(list);
    }
}
