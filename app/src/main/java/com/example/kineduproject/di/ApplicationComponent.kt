package com.example.kineduproject.di

import android.content.Context
import com.example.kineduproject.MainActivity
import com.example.kineduproject.activities.ActivitiesFragment
import com.example.kineduproject.activities.ActivityDetailFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Subcomponent
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Singleton
@Component(modules = [SubcomponentsModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun activityComponent(): ActivityComponent.Factory
}

@ActivityScope
@Subcomponent
interface ActivityComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: ActivitiesFragment)
    fun inject(fragment: ActivityDetailFragment)
}

@Module(subcomponents = [ActivityComponent::class])
class SubcomponentsModule {}