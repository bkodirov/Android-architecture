/*
 * Copyright 2016 Víctor Albertos
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

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class RxActivityResult {
    private static ActivitiesLifecycleCallbacks activitiesLifecycle;

    public static void register(final Application application) {
        activitiesLifecycle = new ActivitiesLifecycleCallbacks(application);
    }

    public static <T extends Activity> Builder<T> on(T activity) {
        return new Builder<T>(activity);
    }

    public static <T extends Fragment> Builder<T> on(T fragment) {
        return new Builder<T>(fragment);
    }

    public static class Builder<T> {
        private final Class clazz;
        PublishSubject<Result<T>> subject = PublishSubject.create();
        private final boolean uiTargetActivity;

        public Builder(T t) {
            if (activitiesLifecycle == null) {
                throw new IllegalStateException(Locale.RX_ACTIVITY_RESULT_NOT_REGISTER);
            }

            this.clazz = t.getClass();
            this.uiTargetActivity = t instanceof Activity;
        }

        public Observable<Result<T>> startIntentSender(IntentSender intentSender, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) {
            return startIntentSender(intentSender, fillInIntent, flagsMask, flagsValues, extraFlags, null);
        }

        public Observable<Result<T>> startIntentSender(IntentSender intentSender, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) {
            RequestIntentSender requestIntentSender = new RequestIntentSender(intentSender, fillInIntent, flagsMask, flagsValues, extraFlags, options);
            return startHolderActivity(requestIntentSender);
        }

        public Observable<Result<T>> startIntent(final Intent intent) {
            return startHolderActivity(new Request(intent));
        }

        private Observable<Result<T>> startHolderActivity(Request request) {

            OnResult onResult = uiTargetActivity ? onResultActivity() : onResultFragment();
            request.setOnResult(onResult);

            HolderActivity.setRequest(request);

            activitiesLifecycle.getOLiveActivity().subscribe(new Action1<Activity>() {
                @Override public void call(Activity activity) {
                    activity.startActivity(new Intent(activity, HolderActivity.class));
                }
            });

            return subject.asObservable();
        }

        private OnResult onResultActivity() {
            return new OnResult() {
                @Override public void response(int resultCode, Intent data) {
                    if (activitiesLifecycle.getLiveActivity() == null) return;

                    //If true it means some other activity has been stacked as a secondary process.
                    //Wait until the current activity be the target activity
                    if (activitiesLifecycle.getLiveActivity().getClass() != clazz) {
                        return;
                    }

                    T activity = (T) activitiesLifecycle.getLiveActivity();
                    subject.onNext(new Result<T>((T) activity, resultCode, data));
                    subject.onCompleted();
                }
            };
        }

        private OnResult onResultFragment() {
            return new OnResult() {
                @Override public void response(int resultCode, Intent data) {
                    if (activitiesLifecycle.getLiveActivity() == null) return;

                    Activity activity = activitiesLifecycle.getLiveActivity();

                    FragmentActivity fragmentActivity = (FragmentActivity) activity;
                    FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

                    Fragment targetFragment = getTargetFragment(fragmentManager.getFragments());

                    if(targetFragment != null) {
                        subject.onNext(new Result<T>((T) targetFragment, resultCode, data));
                        subject.onCompleted();
                    }

                    //If code reaches this point it means some other activity has been stacked as a secondary process.
                    //Do nothing until the current activity be the target activity to get the associated fragment
                }
            };
        }

        @Nullable private Fragment getTargetFragment(List<Fragment> fragments) {
            if (fragments == null) return null;

            for (Fragment fragment : fragments) {
                if(fragment != null && fragment.isVisible() && fragment.getClass() == clazz) {
                    return fragment;
                } else if (fragment != null && fragment.getChildFragmentManager() != null) {
                    List<Fragment> childFragments = fragment.getChildFragmentManager().getFragments();
                    Fragment candidate = getTargetFragment(childFragments);
                    if (candidate != null) return candidate;
                }
            }

            return null;
        }
    }
}
