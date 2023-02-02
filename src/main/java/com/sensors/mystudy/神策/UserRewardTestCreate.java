package com.sensors.mystudy.神策;



import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.bean.FailedData;
import com.sensorsdata.analytics.javasdk.bean.UserRecord;
import com.sensorsdata.analytics.javasdk.consumer.DebugConsumer;
import com.sensorsdata.analytics.javasdk.consumer.FastBatchConsumer;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;

import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenyi@sensorsdata.cn
 * @date 2022/7/5 14:34
 */
public class UserRewardTestCreate {
    // 二期测试环境
    //private final String url = "http://10.120.107.223:8106/sa?project=default";
    // 三期测试环境
    private static final String url = "http://10.162.3.139:8106/sa?project=production";

    public void report() throws Exception {

        //发送失败的数据，收集容器。（仅测试使用，避免网络异常时，大批量数据发送失败同时保存内存中，导致 OOM ）
        final List<FailedData> failedDataList = new ArrayList<>();

        //参数含义：数据接收地址，是否定时上报，非定时上报阈值触发条数，内部缓存最大容量，定时刷新时间间隔，网络请求超时时间，请求失败之后异常数据回调
        //提供多重构造函数，可以根据实际去调用不同的构造函数
        //收集发送失败的数据
        final FastBatchConsumer fastBatchConsumer =
                new FastBatchConsumer(this.url, false, 50, 6000, 2, 10, failedDataList::add);
        //构建 sa 实例对象
        final SensorsAnalytics sa = new SensorsAnalytics(fastBatchConsumer);

        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setMinimumIntegerDigits(5);
        numberFormat.setGroupingUsed(false);

        for (int i = 0; i < 10; i++) {
            UserRecord build = UserRecord.builder()
                    .isLoginId(true)
                    .setDistinctId(String.format("%s-%s", date, numberFormat.format(i + 1)))
                    .addProperty("user_name", String.format("%s-%s-%s", "autoCreate", date, numberFormat.format(i + 1)))
                    .addProperty("phone", String.format("13%s", Math.abs(UUID.randomUUID().hashCode()) + "0000000000").substring(0, 10))
                    .build();

            sa.profileSet(build);
        }

        //异常数据的重发送设置(重发送尽量由另外一个单独的线程来处理完成，避免影响主线程处理逻辑)
        if (!failedDataList.isEmpty()) {
            for (FailedData failedData : failedDataList) {
                try {
                    //返回重发送接口发送成功与否，true:发送成功；false:发送失败
                    boolean b = fastBatchConsumer.resendFailedData(failedData);
                    System.out.println("================resend result: " + b);
                } catch (Exception e) {
                    //处理重发送数据校验异常的情况
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        new UserRewardTestCreate().report();
//        createUser();
        List<String> list = parseData();
//        List<String> list = new ArrayList<>();
//        list.add("aaa,bbb,ccc");
        createUser(list);
    }

    public static void createUser(List<String> list) throws InvalidArgumentException {

        final DebugConsumer fastBatchConsumer =
                new DebugConsumer(url, true);
        //构建 sa 实例对象
        final SensorsAnalytics sa = new SensorsAnalytics(fastBatchConsumer);
        for (String s: list){
            String[] data = s.split(",");
            String distinctId = UUID.randomUUID().toString();
            UserRecord userRecord = UserRecord.builder()
                    .isLoginId(true)
                    .setDistinctId(distinctId)
                    .addProperty("phone_reward",data[0])
                    .addProperty("identity_app_cust_id", Arrays.asList( data[1]))
                    .addProperty("$identity_login_id", Arrays.asList( data[2]))
                    .build();
            sa.profileSet(userRecord);
        }
        sa.flush();


    }

    public static List<String> parseData() throws IOException {
        File file = new File("D:\\Project\\mystudy\\src\\main\\java\\com\\sensors\\mystudy\\神策\\MMP_EDW_USERS_20230115_001.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempStr = "";
        List<String> list = new ArrayList<>();
        while ((tempStr = reader.readLine()) != null) {
            list.add(tempStr);
        }
        return list;
    }
}

