package com.troop.freecamv2.ui.menu;

import android.content.Context;
import android.view.SurfaceView;

import com.troop.freecam.R;
import com.troop.freecamv2.camera.BaseCameraHolder;
import com.troop.freecamv2.camera.CameraUiWrapper;
import com.troop.freecamv2.camera.modules.ModuleHandler;
import com.troop.freecamv2.camera.parameters.modes.I_ModeParameter;
import com.troop.freecamv2.ui.AppSettingsManager;
import com.troop.freecamv2.ui.TextureView.ExtendedSurfaceView;

import java.util.ArrayList;

/**
 * Created by troop on 19.08.2014.
 */
public class MenuCreator
{
    Context context;
    CameraUiWrapper cameraUiWrapper;
    AppSettingsManager appSettingsManager;
    public MenuCreator(Context context, CameraUiWrapper cameraUiWrapper, AppSettingsManager appSettingsManager)
    {
        this.cameraUiWrapper = cameraUiWrapper;
        this.context = context;
        this.appSettingsManager = appSettingsManager;
    }

    public ExpandableGroup CreatePictureSettings(ExtendedSurfaceView surfaceView)
    {
        ExpandableGroup picGroup = new ExpandableGroup(context);
        picGroup.setName(context.getString(R.string.picture_settings));

        createPictureSettingsChilds(picGroup, surfaceView);
        return picGroup;
    }

    private void createPictureSettingsChilds(ExpandableGroup group, ExtendedSurfaceView surfaceView)
    {
        ArrayList<ExpandableChild> piclist = new ArrayList<ExpandableChild>();
        PreviewExpandableChild picSize = new PreviewExpandableChild(context, surfaceView);
        picSize.setName(context.getString(R.string.picture_size));
        cameraUiWrapper.moduleHandler.moduleEventHandler.addListner(picSize);
        picSize.setParameterHolder(cameraUiWrapper.camParametersHandler.PictureSize, appSettingsManager, AppSettingsManager.SETTING_PICTURESIZE, cameraUiWrapper.moduleHandler.PictureModules);
        piclist.add(picSize);

        ExpandableChild picformat = getNewChild(cameraUiWrapper.camParametersHandler.PictureFormat,
                AppSettingsManager.SETTING_PICTUREFORMAT,
                context.getString(R.string.picture_format),
                cameraUiWrapper.moduleHandler.PictureModules);


        piclist.add(picformat);

        ExpandableChild jpegquality = getNewChild(cameraUiWrapper.camParametersHandler.JpegQuality,
                AppSettingsManager.SETTING_JPEGQUALITY, context.getString(R.string.jpeg_quality), cameraUiWrapper.moduleHandler.PictureModules);
        piclist.add(jpegquality);

        if (cameraUiWrapper.camParametersHandler.RedEye.IsSupported())
        {
            ExpandableChild redeye = getNewChild(cameraUiWrapper.camParametersHandler.RedEye, AppSettingsManager.SETTING_REDEYE_MODE, "RedEye Reduction", cameraUiWrapper.moduleHandler.PictureModules);
            piclist.add(redeye);
        }

        group.setItems(piclist);
    }

    public ExpandableGroup CreateModeSettings()
    {
        ExpandableGroup modesGroup = new ExpandableGroup(context);
        modesGroup.setName(context.getString(R.string.mode_settings));
        createModesSettingsChilds(modesGroup);
        return modesGroup;
    }

    private void createModesSettingsChilds(ExpandableGroup group)
    {
        ArrayList<ExpandableChild> childlist = new ArrayList<ExpandableChild>();

        if (cameraUiWrapper.camParametersHandler.ColorMode.IsSupported()) {
            ExpandableChild color = new ExpandableChild(context);
            color.setName(context.getString(R.string.mode_color));
            cameraUiWrapper.moduleHandler.moduleEventHandler.addListner(color);
            color.setParameterHolder(cameraUiWrapper.camParametersHandler.ColorMode, appSettingsManager, AppSettingsManager.SETTING_COLORMODE, cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(color);
        }

        if (cameraUiWrapper.camParametersHandler.IsoMode.IsSupported()) {
            ExpandableChild iso = new ExpandableChild(context);
            iso.setName(context.getString(R.string.mode_iso));
            cameraUiWrapper.moduleHandler.moduleEventHandler.addListner(iso);
            iso.setParameterHolder(cameraUiWrapper.camParametersHandler.IsoMode, appSettingsManager, AppSettingsManager.SETTING_ISOMODE, cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(iso);
        }

        if (cameraUiWrapper.camParametersHandler.ExposureMode.IsSupported())
        {
            ExpandableChild exposure = new ExpandableChild(context);
            exposure.setName(context.getString(R.string.mode_exposure));
            cameraUiWrapper.moduleHandler.moduleEventHandler.addListner(exposure);
            exposure.setParameterHolder(cameraUiWrapper.camParametersHandler.ExposureMode,
                    appSettingsManager,
                    AppSettingsManager.SETTING_EXPOSUREMODE,
                    cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(exposure);
        }

        if (cameraUiWrapper.camParametersHandler.WhiteBalanceMode.IsSupported())
        {
            ExpandableChild wb = getNewChild(cameraUiWrapper.camParametersHandler.WhiteBalanceMode,
                    context.getString(R.string.mode_whitebalance),
                    AppSettingsManager.SETTING_WHITEBALANCEMODE, cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(wb);
        }
        if (cameraUiWrapper.camParametersHandler.SceneMode.IsSupported())
        {
            ExpandableChild scen = getNewChild(cameraUiWrapper.camParametersHandler.SceneMode, "Scene", AppSettingsManager.SETTING_SCENEMODE, cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(scen);
        }
        ExpandableChild focus = getNewChild(cameraUiWrapper.camParametersHandler.FocusMode, "Focus", AppSettingsManager.SETTING_FOCUSMODE, cameraUiWrapper.moduleHandler.AllModules);
        childlist.add(focus);

        group.setItems(childlist);
    }

    public ExpandableGroup CreateQualitySettings()
    {
        ExpandableGroup qualityGroup = new ExpandableGroup(context);
        qualityGroup.setName(context.getString(R.string.quality_settings));
        createQualitySettingsChilds(qualityGroup);
        return qualityGroup;
    }

    private void  createQualitySettingsChilds(ExpandableGroup group)
    {
        ArrayList<ExpandableChild> childlist = new ArrayList<ExpandableChild>();

        if (cameraUiWrapper.camParametersHandler.AntiBandingMode.IsSupported())
        {
            ExpandableChild antibanding = new ExpandableChild(context);
            antibanding.setName(context.getString(R.string.antibanding));
            cameraUiWrapper.moduleHandler.moduleEventHandler.addListner(antibanding);
            antibanding.setParameterHolder(cameraUiWrapper.camParametersHandler.AntiBandingMode, appSettingsManager, AppSettingsManager.SETTING_ANTIBANDINGMODE, cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(antibanding);
        }
        if (cameraUiWrapper.camParametersHandler.ImagePostProcessing.IsSupported())
        {
            ExpandableChild ipp = new ExpandableChild(context);
            ipp.setName(context.getString(R.string.image_post_processing));
            cameraUiWrapper.moduleHandler.moduleEventHandler.addListner(ipp);
            ipp.setParameterHolder(cameraUiWrapper.camParametersHandler.ImagePostProcessing, appSettingsManager, AppSettingsManager.SETTING_IMAGEPOSTPROCESSINGMODE, cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(ipp);
        }
        if (cameraUiWrapper.camParametersHandler.LensShade.IsSupported())
        {
            ExpandableChild lens = getNewChild(cameraUiWrapper.camParametersHandler.LensShade, AppSettingsManager.SETTING_LENSSHADE_MODE, "Lens Shade", cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(lens);
        }
        if (cameraUiWrapper.camParametersHandler.ZSL.IsSupported())
        {
            ExpandableChild zsl = getNewChild(cameraUiWrapper.camParametersHandler.LensShade, AppSettingsManager.SETTING_ZEROSHUTTERLAG_MODE, "ZeroShutterLag", cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(zsl);
        }
        if (cameraUiWrapper.camParametersHandler.SceneDetect.IsSupported())
        {
            ExpandableChild sd = getNewChild(cameraUiWrapper.camParametersHandler.SceneDetect, AppSettingsManager.SETTING_SCENEDETECT_MODE, "Scene Detect", cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(sd);
        }
        if (cameraUiWrapper.camParametersHandler.Denoise.IsSupported())
        {
            ExpandableChild sd = getNewChild(cameraUiWrapper.camParametersHandler.Denoise, AppSettingsManager.SETTING_DENOISE_MODE, "Denoise", cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(sd);
        }
        if (cameraUiWrapper.camParametersHandler.DigitalImageStabilization.IsSupported())
        {
            ExpandableChild sd = getNewChild(cameraUiWrapper.camParametersHandler.DigitalImageStabilization, AppSettingsManager.SETTING_DIS_MODE, "DigitalImageStabilization", cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(sd);
        }
        if (cameraUiWrapper.camParametersHandler.MemoryColorEnhancement.IsSupported())
        {
            ExpandableChild sd = getNewChild(cameraUiWrapper.camParametersHandler.MemoryColorEnhancement, AppSettingsManager.SETTING_MCE_MODE, "Memory Color Enhancement", cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(sd);
        }

        if (cameraUiWrapper.camParametersHandler.SkinToneEnhancment.IsSupported())
        {
            ExpandableChild sd = getNewChild(cameraUiWrapper.camParametersHandler.SkinToneEnhancment, AppSettingsManager.SETTING_SKINTONE_MODE, "SkinTone", cameraUiWrapper.moduleHandler.AllModules);
            childlist.add(sd);
        }

        group.setItems(childlist);
    }

    public ExpandableGroup CreatePreviewSettings(ExtendedSurfaceView surfaceView)
    {
        ExpandableGroup preview = getNewGroup(context.getString(R.string.preview_settings));
        createPreviewSettingsChilds(preview, surfaceView);
        return preview;
    }

    private void createPreviewSettingsChilds(ExpandableGroup preview, ExtendedSurfaceView surfaceView)
    {
        ArrayList<ExpandableChild> childlist = new ArrayList<ExpandableChild>();
        PreviewExpandableChild size = new PreviewExpandableChild(context, surfaceView);
        size.setName("Preview Size");
        cameraUiWrapper.moduleHandler.moduleEventHandler.addListner(size);
        size.setParameterHolder(cameraUiWrapper.camParametersHandler.PreviewSize,appSettingsManager,AppSettingsManager.SETTING_PREVIEWSIZE, cameraUiWrapper.moduleHandler.AllModules);

        //ExpandableChild size = getNewChild(cameraUiWrapper.camParametersHandler.PreviewSize, AppSettingsManager.SETTING_PREVIEWSIZE, context.getString(R.string.preview_size), cameraUiWrapper.moduleHandler.AllModules);
        childlist.add(size);

        ExpandableChild fps = getNewChild(cameraUiWrapper.camParametersHandler.PreviewFPS, AppSettingsManager.SETTING_PREVIEWFPS, context.getString(R.string.preview_fps), cameraUiWrapper.moduleHandler.AllModules);
        childlist.add(fps);

        ExpandableChild format = getNewChild(cameraUiWrapper.camParametersHandler.PreviewFormat, AppSettingsManager.SETTING_PREVIEWFORMAT, context.getString(R.string.preview_fromat), cameraUiWrapper.moduleHandler.AllModules);
        childlist.add(format);

        preview.setItems(childlist);
    }

    private ExpandableGroup getNewGroup(String name)
    {
        ExpandableGroup group = new ExpandableGroup(context);
        group.setName(name);
        return group;
    }

    /**
     *
     * @param mode The camera parameter wich handels the input
     * @param appsettingName THe name of the appsetting to get stored
     * @param settingName the name wich gets displayed in menu
     * @param modules on wich module event the child is shown.
     * @return
     */
    private ExpandableChild getNewChild(I_ModeParameter mode, String appsettingName, String settingName, ArrayList<String> modules)
    {
        ExpandableChild child = new ExpandableChild(context);
        child.setName(settingName);
        cameraUiWrapper.moduleHandler.moduleEventHandler.addListner(child);
        child.setParameterHolder(mode,appSettingsManager,appsettingName, modules);
        return child;
    }

    public ExpandableGroup CreateVideoSettings(ExtendedSurfaceView surfaceView)
    {
        ExpandableGroup preview = getNewGroup("Video Settings");
        createVideoSettingsChilds(preview, surfaceView);
        return preview;
    }

    private void createVideoSettingsChilds(ExpandableGroup preview, ExtendedSurfaceView surfaceView)
    {
        ArrayList<ExpandableChild> childlist = new ArrayList<ExpandableChild>();
    }
}
