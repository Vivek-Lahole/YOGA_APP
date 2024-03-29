# See the new YOGA pose estimation Android sample, which demonstrates both Posenet and Movenet models. 

<br/> <br/> 

## Demo

https://github.com/Vivek-Lahole/YOGA_APP/assets/82323706/9733f285-1fb0-4ed7-9675-0c19bab7ac64

## Yoga Posture detection App (using tenserflowlite posenet library)

### Overview
 This is an Yoga app that continuously detects the body parts in the frames seen by
 your device's camera. The yoga app compares the posture of the user with inbuilt 
 yoga poses and allows user to check if he is performing the yoga pose correctly
 . Camera captures are discarded immediately after
 use, nothing is stored or saved.
 
 

![Demo Image](posenetimage.png)


## Features Of App
 Detects the user's posture in real-time using the device camera and compares it to pre-programmed yoga postures.

 *Steps and instructions for Yoga aasan are provided, as well as a timer to calculate the time spent in correct posture.
  
 *When the user's posture matches up to 75%, the Timer starts, and when the posture is distorted, the Timer resets.
  
 *Using the custom yoga position function, users may build their own yoga stance by uploading an image of the posture they wish to compare it to.
 
 *With the inbuilt media player and songs given with the app, users may listen to music while doing yoga.

## Build the demo using Android Studio

### Prerequisites

* If you don't have it already, install **[Android Studio](
 https://developer.android.com/studio/index.html)** 3.2 or
 later, following the instructions on the website.

* Android device and Android development environment with minimum API 21.

### Building
* Open Android Studio, and from the `Welcome` screen, select
`Open an existing Android Studio project`.

* From the `Open File or Project` window that appears, navigate to and select
 the `tensorflow-lite/examples/posenet/android` directory from wherever you
 cloned the TensorFlow Lite sample GitHub repo. Click `OK`.

* If it asks you to do a `Gradle Sync`, click `OK`.

* You may also need to install various platforms and tools, if you get errors
 like `Failed to find target with hash string 'android-21'` and similar. Click
 the `Run` button (the green arrow) or select `Run` > `Run 'android'` from the
 top menu. You may need to rebuild the project using `Build` > `Rebuild Project`.

* If it asks you to use `Instant Run`, click `Proceed Without Instant Run`.

* Also, you need to have an Android device plugged in with developer options
 enabled at this point. See **[here](
 https://developer.android.com/studio/run/device)** for more details
 on setting up developer devices.


### Model used
Downloading, extraction and placement in assets folder has been managed
 automatically by `download.gradle`.

If you explicitly want to download the model, you can download it from
 **[here](
 https://storage.googleapis.com/download.tensorflow.org/models/tflite/posenet_mobilenet_v1_100_257x257_multi_kpt_stripped.tflite)**.

### Additional Note
_Please do not delete the assets folder content_. If you explicitly deleted the
 files, then please choose `Build` > `Rebuild` from menu to re-download the
 deleted model files into assets folder.

