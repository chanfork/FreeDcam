package com.troop.freedcam.sonyapi.parameters.modes;

import com.troop.freedcam.sonyapi.sonystuff.SimpleRemoteApi;

import java.util.Set;

/**
 * Created by troop on 17.12.2014.
 */
public class ExposureModeSony extends BaseModeParameterSony {
    public ExposureModeSony(String VALUE_TO_GET, String VALUE_TO_SET, String VALUES_TO_GET, SimpleRemoteApi mRemoteApi) {
        super(VALUE_TO_GET, VALUE_TO_SET, VALUES_TO_GET, mRemoteApi);
    }

    @Override
    protected void processValuesToSet(String valueToSet) {
        super.processValuesToSet(valueToSet);
    }

    @Override
    public void SonyApiChanged(Set<String> mAvailableCameraApiSet) {
        super.SonyApiChanged(mAvailableCameraApiSet);

    }


}
