// RnskiaModule.java

package com.shopify.reactnative.skia;

import android.util.Log;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.lang.ref.WeakReference;

@ReactModule(name="RNSkiaModule")
public class RNSkiaModule extends NativeSkiaModuleSpec {
    public static final String NAME = "RNSkiaModule";

    private final WeakReference<ReactApplicationContext> weakReactContext;
    private SkiaManager skiaManager;

    public RNSkiaModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.weakReactContext = new WeakReference<>(reactContext);
    }

    @Override
    public void invalidate() {
        super.invalidate();

        if (this.skiaManager != null) {
            this.skiaManager.invalidate();
            this.skiaManager.destroy();
            this.skiaManager = null;
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SkiaManager getSkiaManager() {
        return skiaManager;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean install() {
        if (skiaManager != null) {
            // Already initialized, ignore call.
            return true;
        }

        try {
            System.loadLibrary("rnskia");
            ReactApplicationContext context = weakReactContext.get();
            if (context == null) {
                Log.e(NAME, "React Application Context was null!");
                return false;
            }
            skiaManager = new SkiaManager(context);
            return true;
        } catch (Exception exception) {
            Log.e(NAME, "Failed to initialize Skia Manager!", exception);
            return false;
        }
    }
}
