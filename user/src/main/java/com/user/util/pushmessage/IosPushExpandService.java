package com.user.util.pushmessage;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public abstract class IosPushExpandService extends PushConfig implements PushBaseService {
    @Override
    public void pushSingleMessageOrNotice(PushParams pushParams) {
        if (!PushParams.PushDeviceType.ANDROID.equals(pushParams.getDeviceType())) {
            this.pushMessage(pushParams);
        }
    }

    @Override
    public void pushNotice(PushParams pushParams) {

    }

    public void pushMessage(PushParams pushParams) {

    }
}
