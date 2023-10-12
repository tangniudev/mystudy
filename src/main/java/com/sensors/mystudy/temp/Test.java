package com.sensors.mystudy.temp;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author tyw
 * @since 2022/7/26
 */
public class Test {
    public static void main(String[] args) throws IOException {

        String md5 = DigestUtils.md5Hex(new FileInputStream("D:\\Project\\sns_user_group_upload\\nastemp\\三方名称-分群一-20221011.txt"));
        System.out.println(md5);

        String md51 = DigestUtils.md5Hex(new FileInputStream("D:\\Project\\mystudy\\测试数据文件2.txt"));
        System.out.println(md51);

        File file = new File("D:\\Project\\mystudy\\测试数据文件.txt");
        System.out.println(file.length());


        ArrayList<String> list = new ArrayList<>();
         list.add("aaa,ccc");
         list.add("bbb,ccc");
        Map<String, String> collect = list.stream().collect(Collectors.toMap(s -> "mmm"+ s.split(",")[0], s -> s,(o, n) -> n));
        for (String s : list){
            System.out.println(s);
        }
        System.out.println(collect);
         Set<String> strings = collect.keySet();
        System.out.println(strings);
         List<String> mmm = strings.stream().map(s -> s.replace("mmm", "")).collect(Collectors.toList());
        System.out.println(mmm);

        Map<String, String> getenv = System.getenv();
        for (String s:getenv.keySet()){
            System.out.println("key:{}"+s+"  value:{}"+getenv.get(s));
        }

        String ssss ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKD0tIxFDHV1h8cY24rj3Jxy4HdkwXMl3I1GMaVom+xK4gjpbVJQI8EKT4j2UUQnQqvlg2Xnh/lEMrhZOBAST1b/b/2NfaNcKzhHhwkTN+WKD19zk0YK76h7tGU19VDmlxuTc/eCY5Q7Y3lurfnmTrGKNonOfiavN4obtalPYThjAgMBAAECgYBgqLPobOr/evZwRWhKr25j8cUFTleyS/F4Ucgsunaw34OAsJX7Ua83FXcQHERPdnhefLV9NB7/Pcpr1QCK/kPwWglIkDLRgrsGl4CfYFBY8vBPm02pDcB66kgjJUfEa8wnUqqoCWD3KYfF+wxev6wWF51HRFq/PHHEtqZogsEQGQJBANE0hAQY54h5qxtnR+5FL6njoEcLrS4Lnhoaek0Rs2YR6sfrSs9RaRupWkiMRZr6/FvxwWxqY8Y1oj8lzhaKAbUCQQDE9Vhd5smr69LfLiFd4LeVoiikMufJWPz7wKLwx5vJpAwmR3Y5O+lEvrKTc5dtPiqJuGvt/yhXT89PhdNEEgC3AkEAjNKP5v4opJpZzol4RvB1hCOAWTKH8Y3KcAHQv54hp5x58Pb2lZVUblVSO7GIjSlTxER5EhSPcvXZcC5vioZ9XQJBAKXXbGvM230umaWgwym4AP02yH/R+UeZ7ZLR1qFlgfDcd/p3a72vi7B93gG4cAXlNY43QnHoyoASTJ5KMeT8P6sCQAcbJ50dZovJNol24eMiYXJIQwCJ/9Y/Sx3qPVDeoTqV954vspjdJVdvlcfao3WSaDmPKwcbwrdIMPKEWJqlafk=";
        byte[] s = Base64.getDecoder().decode(ssss);
        System.out.println("323");

        String temp = "asd/nasdad";
        String[] split = temp.split(" ");
        for (String s1 :split){
            System.out.println(s1);
        }

    }
}
