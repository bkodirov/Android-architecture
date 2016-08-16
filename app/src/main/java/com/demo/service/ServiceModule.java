package com.demo.service;

import com.demo.service.firebase.CloudMessageManager;
import com.demo.service.firebase.CloudMessageManagerImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Beka on 8/9/16.
 */
@Module(library = true)
public class ServiceModule {


    @Provides
    CloudMessageManager provideCloudMessageManager(CloudMessageManagerImpl cloudMessageManager) {
        return cloudMessageManager;
    }

}
