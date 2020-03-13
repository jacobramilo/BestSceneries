package com.jacob.bestsceneries.di.module

import com.jacob.bestsceneries.repository.SceneriesRepository
import com.jacob.bestsceneries.viewmodel.SceneryViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideSceneryViewModel(sceneryRepository: SceneriesRepository): SceneryViewModel {
        return SceneryViewModel(sceneryRepository)
    }

}