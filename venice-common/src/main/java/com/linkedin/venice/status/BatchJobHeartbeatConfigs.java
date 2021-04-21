package com.linkedin.venice.status;

import com.linkedin.venice.utils.Utils;
import java.time.Duration;


public class BatchJobHeartbeatConfigs {

    public static final Utils.ConfigEntity<Boolean> HEARTBEAT_ENABLED_CONFIG = new Utils.ConfigEntity<>(
            "batch.job.heartbeat.enabled",
            false,
            "If the heartbeat feature is enabled"
    );
    public static final Utils.ConfigEntity<Long> HEARTBEAT_INTERVAL_CONFIG = new Utils.ConfigEntity<>(
            "batch.job.heartbeat.interval.ms",
            Duration.ofMinutes(1).toMillis(),
            "Time interval between sending two consecutive heartbeats"
    );
    public static final Utils.ConfigEntity<Long> HEARTBEAT_CONTROLLER_TIMEOUT_CONFIG = new Utils.ConfigEntity<>(
        "controller.batch.job.heartbeat.timeout.ms",
        HEARTBEAT_INTERVAL_CONFIG.getDefaultValue() == null ? Duration.ofMinutes(3).toMillis() : 3 * HEARTBEAT_INTERVAL_CONFIG.getDefaultValue(),
        "The max amount of time the controller waits for a heartbeat to show up before it claims a timeout."
    );
    public static final Utils.ConfigEntity<Long> HEARTBEAT_CONTROLLER_INITIAL_DELAY_CONFIG = new Utils.ConfigEntity<>(
        "controller.batch.job.heartbeat.initial.delay.ms",
        Duration.ofMinutes(15).toMillis(),
        "The amount of time the controller waits after a store creation before it enables the heartbeat-based lingering push job checking feature."
    );
    public static final Utils.ConfigEntity<Long> HEARTBEAT_INITIAL_DELAY_CONFIG = new Utils.ConfigEntity<>(
            "batch.job.heartbeat.initial.delay.ms",
            0L,
            "Delay before sending the first heartbeat"
    );
    public static final Utils.ConfigEntity<String> HEARTBEAT_VENICE_D2_ZK_HOST_CONFIG = new Utils.ConfigEntity<>(
            "heartbeat.venice.d2.zk.host",
            null,
            "D2 Zookeeper host used to discover the Venice cluster with the heartbeat store"
    );
    public static final Utils.ConfigEntity<String> HEARTBEAT_VENICE_D2_SERVICE_NAME_CONFIG = new Utils.ConfigEntity<>(
            "heartbeat.venice.d2.service.name",
            null,
            "D2 service name used to construct a Venice controller client"
    );
    public static final Utils.ConfigEntity<String> HEARTBEAT_STORE_NAME_CONFIG = new Utils.ConfigEntity<>(
            "heartbeat.store.name",
            null,
            "Heartbeat store name"
    );
    private BatchJobHeartbeatConfigs() {
        // Util class
    }
}