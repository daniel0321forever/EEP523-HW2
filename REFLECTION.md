# Reflection: AI in Software Development

### What features or sensors did your app use?
*   **Sensors**: Light Sensor, Proximity Sensor, and Magnetic Field (Magnetometer) Sensor.
*   **Features**: 
    *   Bottom Navigation using Jetpack Navigation Component.
    *   Custom Drawing Engine with multi-color support and image scaling.
    *   CameraX integration for front/back camera toggling and photo capture.
    *   Modern Scoped Storage (MediaStore) for saving and loading gallery images.

### How did Gemini help you?
Gemini was instrumental in providing the boilerplate code for the CameraX lifecycle binding, which is traditionally verbose and difficult to get right on the first try. It also helped speed up the creation of the XML layouts and provided a solid mathematical foundation for scaling bitmaps within the custom `DrawingView`.

### What errors, weaknesses, or missing pieces did you find in Gemini’s output?
*   **API Misuse**: It initially suggested `Log.error()`, which is not a standard method in Android's `Log` class (should be `Log.e()`).
*   **Redundancy**: It generated several version checks for Android Q (API 29) even though my project's `minSdk` was 30, making the code more cluttered than necessary.
*   **Accessibility/Lint**: The generated custom view didn't initially override `performClick()`, which triggers a lint warning when overriding `onTouchEvent`. It also used hardcoded strings instead of referencing `strings.xml`.

### What did you change or fix?
I manually corrected the `Log` calls and removed the redundant SDK version checks to keep the codebase clean. I implemented the `performClick()` method in `DrawingView` to handle accessibility properly. Additionally, I fine-tuned the `WindowInsets` logic in `MainActivity` to ensure the UI didn't overlap with the status bar while keeping the bottom navigation properly positioned.

### What did you learn about using AI in software development?
I learned that AI is an incredible "accelerator" for scaffolding complex features like CameraX or Navigation, but it is not a replacement for fundamental Android knowledge. You must be able to read and debug the output, as AI often ignores lint warnings, accessibility standards, and specific project configurations (like `minSdk` levels). The best way to use AI is to treat it as a highly efficient pair programmer that still needs a "senior" developer to review and refine its work.
