/*
 * Copyright 2016 Copyright 2016 Víctor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.ui.rx_activity_result;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class RequestIntentSender extends Request {
    private final IntentSender intentSender;
    private final Intent fillInIntent;
    private final int flagsMask, flagsValues, extraFlags;
    private final Bundle options;

    public RequestIntentSender(IntentSender intentSender, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) {
        super(null);
        this.intentSender = intentSender;
        this.fillInIntent = fillInIntent;
        this.flagsMask = flagsMask;
        this.flagsValues = flagsValues;
        this.extraFlags = extraFlags;
        this.options = options;
    }

    public IntentSender getIntentSender() {
        return intentSender;
    }

    public Intent getFillInIntent() {
        return fillInIntent;
    }

    public int getFlagsMask() {
        return flagsMask;
    }

    public int getFlagsValues() {
        return flagsValues;
    }

    public int getExtraFlags() {
        return extraFlags;
    }

    public Bundle getOptions() {
        return options;
    }
}
