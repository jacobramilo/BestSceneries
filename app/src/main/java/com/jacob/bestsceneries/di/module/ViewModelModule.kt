package com.jacob.bestsceneries.di.module

import com.jacob.bestsceneries.repository.SceneriesRepository
import com.jacob.bestsceneries.viewmodel.SceneryViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class ViewModelModule {

    @Provides @Singleton fun provideSceneryViewModel(sceneryRepository: SceneriesRepository): SceneryViewModel {
        return SceneryViewModel(sceneryRepository)
    }

}