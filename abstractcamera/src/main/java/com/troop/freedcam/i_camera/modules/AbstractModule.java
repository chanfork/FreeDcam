package com.troop.freedcam.i_camera.modules;


import com.troop.freedcam.i_camera.AbstractCameraHolder;
import com.troop.freedcam.i_camera.interfaces.I_Module;
import com.troop.freedcam.i_camera.parameters.AbstractParameterHandler;
import com.troop.freedcam.ui.AppSettingsManager;

/**
 * Created by troop on 15.08.2014.
 */
public abstract class AbstractModule implements I_Module
{
    protected AbstractCameraHolder baseCameraHolder;
    protected AppSettingsManager Settings;
    protected AbstractParameterHandler ParameterHandler;

    protected boolean isWorking = false;
    public String name;

    protected ModuleEventHandler eventHandler;
    protected AbstractModuleHandler.I_worker workerListner;

    public AbstractModule(){};

    public AbstractModule(AbstractCameraHolder cameraHandler, AppSettingsManager Settings, ModuleEventHandler eventHandler)
    {
        this.baseCameraHolder = cameraHandler;
        this.Settings = Settings;
        this.eventHandler = eventHandler;
        this.ParameterHandler = baseCameraHolder.ParameterHandler;
    }

    public void SetWorkerListner(AbstractModuleHandler.I_worker workerListner)
    {
        this.workerListner = workerListner;
    }

    protected void workstarted()
    {
        if (this.workerListner != null)
            workerListner.onWorkStarted();
    }

    protected void workfinished(final boolean finish)
    {
        if (workerListner != null)
            workerListner.onWorkFinished(finish);
    }

    @Override
    public String ModuleName() {
        return name;
    }

    @Override
    public void DoWork()
    {

    }

    @Override
    public boolean IsWorking() {
        return isWorking;
    }

    @Override
    public void LoadNeededParameters() {

    }

    @Override
    public void UnloadNeededParameters() {

    }

    @Override
    public String LongName() {
        return null;
    }

    @Override
    public String ShortName() {
        return null;
    }
}
