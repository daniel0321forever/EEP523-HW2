# LLM Prompts Used

The following prompts were used with Gemini to assist in the development of this application. They were designed to ensure high-quality code and proper integration of Android components.

### Prompt 1: Project Architecture and Navigation
> "I am building an Android app in Kotlin that requires a Bottom Navigation Bar with three tabs: Sensors, Draw, and Camera. I want to use the Jetpack Navigation Component. Can you provide the XML layout for `activity_main` including the `BottomNavigationView` and the `NavHostFragment`, and show me how to link them in `MainActivity.kt`? Please ensure it follows modern Material 3 design guidelines and handles edge-to-edge window insets properly."

### Prompt 2: Custom Drawing Canvas with Image Overlay
> "I need to implement a 'Draw' feature where a user picks an image from their gallery and draws over it using different colors. I've created a custom `View` called `DrawingView`. Can you help me write the logic for `onDraw` and `onTouchEvent` so that I can draw paths on top of a background bitmap? Also, please provide the logic to save the final composited bitmap to the public DCIM gallery using the `MediaStore` API, ensuring compatibility with Android 11 (API 30) and above without requesting legacy storage permissions."

### Prompt 3: CameraX Integration and Permission Handling
> "I am using the CameraX library for a Camera tab. I need to implement a preview, a button to capture a photo, and a button to switch between the front and back cameras. Can you provide a robust implementation for a `CameraFragment` that handles the `ProcessCameraProvider` lifecycle correctly? Additionally, please show me how to use the modern `ActivityResultLauncher` to request `CAMERA` permissions and handle the scenario where the user denies access with a helpful UI message."
