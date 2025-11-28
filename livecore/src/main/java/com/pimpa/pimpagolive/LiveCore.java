package com.pimpa.pimpagolive;

import android.content.Context;
import android.util.Log;

import com.pedro.rtplibrary.rtmp.RtmpCamera1;
import net.ossrs.rtmp.ConnectCheckerRtmp;

public class LiveCore {

    private RtmpCamera1 rtmpCamera1;
    private ConnectCheckerRtmp checker;
    private Context context;

    // Constructor
    public LiveCore(Context context, ConnectCheckerRtmp checker) {
        this.context = context;
        this.checker = checker;
        rtmpCamera1 = new RtmpCamera1(context, checker);
    }

    // Start streaming
    public void startStream(String rtmpUrl) {
        if (!rtmpCamera1.isStreaming()) {
            if (rtmpCamera1.prepareAudio() && rtmpCamera1.prepareVideo()) {
                rtmpCamera1.startStream(rtmpUrl);
                Log.d("LiveCore", "Streaming started: " + rtmpUrl);
            } else {
                Log.e("LiveCore", "Error preparing stream");
            }
        }
    }

    // Stop streaming
    public void stopStream() {
        if (rtmpCamera1.isStreaming()) {
            rtmpCamera1.stopStream();
            Log.d("LiveCore", "Streaming stopped");
        }
    }

    // Switch camera
    public void switchCamera() {
        rtmpCamera1.switchCamera();
    }

    // Check if streaming
    public boolean isStreaming() {
        return rtmpCamera1.isStreaming();
    }

    // Toggle audio
    public void toggleAudio() {
        rtmpCamera1.enableAudio(!rtmpCamera1.isAudioMuted());
    }
}
