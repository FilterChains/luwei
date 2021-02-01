package com.user.util.pushmessage;

import org.springframework.stereotype.Component;

@Component
public abstract class AndroidPushExpandService extends PushConfig implements PushBaseService {
    @Override
    public void pushSingleMessageOrNotice(PushParams pushParams) {

    }

    @Override
    public void pushNotice(PushParams pushParams) {

    }
}
