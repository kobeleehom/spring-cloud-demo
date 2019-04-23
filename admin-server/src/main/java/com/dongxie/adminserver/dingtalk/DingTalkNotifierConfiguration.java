package com.dongxie.adminserver.dingtalk;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.server.config.AdminServerNotifierAutoConfiguration.CompositeNotifierConfiguration;
import de.codecentric.boot.admin.server.config.AdminServerNotifierAutoConfiguration.NotifierTriggerConfiguration;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;


/**
 * ${钉钉发送服务启停消息配置} <br>
 *
 * @author guan.xb <br>
 * @version 1.0 <br>
 * @taskId <br>
 * @CreateDate 2019年04月23日 <br>
 * @see com.dongxie.adminserver.dingtalk <br>
 * @since V7.3 <br>
 */
@Configuration
@ConditionalOnProperty(
    prefix = "spring.boot.admin.notify.dingtalk",
    name = {"webhook-token"}
)
@AutoConfigureBefore({NotifierTriggerConfiguration.class, CompositeNotifierConfiguration.class})
public class DingTalkNotifierConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "spring.boot.admin.notify.dingtalk")
    public DingTalkStatusChangeNotifier dingTalkNotifier(InstanceRepository repository) {
        return new DingTalkStatusChangeNotifier(repository);
    }
}
