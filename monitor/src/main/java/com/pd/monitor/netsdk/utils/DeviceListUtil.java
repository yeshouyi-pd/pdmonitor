package com.pd.monitor.netsdk.utils;

import com.pd.monitor.netsdk.module.DevicesModule;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class DeviceListUtil {
    private static List<DevicesModule> deviceList = new ArrayList<>();

    public static List<DevicesModule> getDeviceList() {
        return deviceList;
    }

    public static DevicesModule getDeviceModuleByDeviceId(String deviceId) {
        for (DevicesModule deviceModule : deviceList) {
            if (deviceModule.getDeviceInfo().getDid().equals(deviceId)) {
                return deviceModule;
            }
        }
        return null;
    }
}
