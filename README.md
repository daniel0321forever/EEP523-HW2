# EEP523 HW2: Sensors, Drawing, and Camera App

## App Overview
This Android application is a multi-functional utility app designed with a modern navigation interface. It features three main components accessible via a bottom navigation bar:

1.  **Sensors Tab**: Displays real-time data from three hardware sensors: **Light**, **Proximity**, and **Magnetic Field**. It handles missing sensors gracefully by notifying the user via Toast messages.
2.  **Draw Tab**: Allows users to import an image from their photo gallery and draw over it. Users can switch between different brush colors (Black, Red, Green, Blue), reset the canvas to the original photo, and save their edited masterpiece back to the gallery.
3.  **Camera Tab**: A full camera implementation using CameraX. It supports switching between front and back cameras, capturing high-resolution photos, and automatically saving them to the device's image gallery. It also features an ImageView to preview the last captured shot.

## Time Spent
It took approximately **12 hours** to complete this project. This includes:
*   2 hours for UI/UX design and Navigation Component setup.
*   3 hours for implementing and testing the Sensor logic.
*   4 hours for the Drawing engine and MediaStore integration (saving/loading).
*   3 hours for CameraX integration and permission handling.

## Most Challenging Parts
*   **CameraX Lifecycle**: Managing the camera lifecycle within a Fragment was tricky, specifically ensuring the camera unbinds correctly when switching tabs to prevent crashes or resource leaks.
*   **MediaStore API**: Since the app targets SDK 36, I had to use the modern `ContentResolver` and `ContentValues` approach to save images. Navigating the differences between older storage permissions and the newer scoped storage model took significant research.
*   **Custom Drawing View**: Creating the `DrawingView` to scale a background image from the gallery while maintaining the coordinate system for touch events was a complex math challenge.

## Resources & Citations
*   [Android Developers: CameraX Overview](https://developer.android.com/training/camerax)
*   [Android Developers: Sensors Overview](https://developer.android.com/guide/topics/sensors/sensors_overview)
*   [Android Developers: MediaStore](https://developer.android.com/training/data-storage/shared/media)
*   [Material Design: Bottom Navigation](https://m3.material.io/components/bottom-navigation/overview)
*   [StackOverflow: Drawing on Canvas over an Image](https://stackoverflow.com/questions/12266899/drawing-on-canvas-over-an-image)
*   [StackOverflow: Save Bitmap to Gallery Android Q+](https://stackoverflow.com/questions/56904485/how-to-save-an-image-in-android-q-using-mediastore)
