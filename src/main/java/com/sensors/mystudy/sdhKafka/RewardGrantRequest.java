package com.sensors.mystudy.sdhKafka;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 *
 * @author chenyi@sensorsdata.cn
 * @date 2022/6/7 17:27
 */
@NoArgsConstructor
@Data
public class RewardGrantRequest {
    private Integer projectId;

    private String sfVersion;

    private String channelCategory;

    private Integer channelId;

    private Integer channelInstanceId;

    private Boolean needReceipt;

    private Boolean isTestSend;

    private List<MessagesDTO> messages;

    @Data
    public static class MessagesDTO {
        private String sendId;

        private ReceiptPropertiesDTO receiptProperties;

        private RewardProfileDTO rewardProfile;

        private UserProfileDTO userProfile;
        /**额外用户属性参数**/
        private Map<String, Object> params;

        @Data
        public static class RewardProfileDTO {
            private String rewardId;

            private String typeId;
        }

        @Data
        public static class ReceiptPropertiesDTO {
            private String sfMsgId;

            private Integer sfPlanId;

            private String sfPlanName;

            private Long sfEnterPlanTime;

            private Integer sfPlanStrategyId;

            private Integer sfStrategyUnitId;

            private String sfPlanType;
        }

        @Data
        public static class UserProfileDTO {
            private Long userId;

            private String firstId;

            private String secondId;
        }
    }
}
