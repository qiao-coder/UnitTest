package com.tufei.unittest.di;

import com.tufei.unittest.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author TuFei
 * @date 19-4-8.
 */
@ActivityScoped
@Component(dependencies = AppComponent.class,modules = MainModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
