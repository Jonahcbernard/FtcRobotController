package org.firstinspires.ftc.internal;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class OpenCVLoader {
    private OpenCvCamera phoneCam;
    public OptimizedOpenCVPipeline pipeline;
    private final HardwareMap map;
    private final Point translation = null;
    private final boolean RUN_ON_APP;

    public OpenCVLoader(HardwareMap map, boolean RUN_ON_APP, OptimizedOpenCVPipeline pipeline) {
        this.map = map;
        this.RUN_ON_APP = RUN_ON_APP;
        this.pipeline = pipeline;
        init();
    }

    private void init() {

        if (RUN_ON_APP) {
            int cameraMonitorViewId = map.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", map.appContext.getPackageName());
            phoneCam = OpenCvCameraFactory.getInstance().createWebcam(map.get(WebcamName.class, RobotConfig.WEBCAM_NAME), cameraMonitorViewId);
        } else {
            phoneCam = OpenCvCameraFactory.getInstance().createWebcam(map.get(WebcamName.class, RobotConfig.WEBCAM_NAME));
        }


        phoneCam.setPipeline(pipeline);

        phoneCam.openCameraDeviceAsync(() -> phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT));
    }
}