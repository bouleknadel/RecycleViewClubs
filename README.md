# Premier League Clubs App

## Description
This Android application displays a gallery of Premier League football clubs. It features an animated splash screen and list of clubs with a search function. The app demonstrates various Android development concepts, such as animations, RecyclerView, image handling, and menu creation.
## Features
- Animated splash screen with smooth logo transitions
- Display of Premier League clubs using RecyclerView
- Circular club logos using CircleImageView
- Efficient image loading and caching with Glide
- Search bar for filtering clubs in real-time
- Share option for the application

## Prerequisites
- Android Studio version 2023.3.1
- JDK version 22.0.1

## Dependencies
This project uses the following key dependencies to improve functionality and performance:

1. RecyclerView:
   - Implementation: `androidx.recyclerview:recyclerview:1.3.2`
   - Purpose: Efficient display of scrollable lists
   - Advantages: 
     - Optimized for long lists with smooth scrolling
     - Flexible layout management
     - Built-in ViewHolder pattern for better memory usage

2. Glide:
   - Implementation: `com.github.bumptech.glide:glide:4.8.0`
   - Purpose: Efficient image loading and caching
   - Advantages:
     - Smooth image loading for better scrolling performance
     - Automatic memory and disk caching
     - Easy to apply image transformations

3. CircleImageView:
   - Implementation: `de.hdodenhof:circleimageview:3.0.1`
   - Purpose: Display circular images
   - Advantages:
     - Simplifies circular image creation
     - Customizable border width and color options

## Project Structure
- `adapter`: Contains custom adapters for RecyclerView
- `beans`: Data model classes
- `dao`: Data Access Object interfaces
- `service`: Business logic and data handling services

## Setup and Installation
1. Clone the repository:
https://github.com/bouleknadel/RecycleViewClubs.git
2. Open the project in Android Studio.
3. Sync the Gradle files to install dependencies.
4. Run the application on an emulator or physical device.

## Implementation Details

### Splash Screen (SplashActivity)
The splash screen includes multiple animations applied to the app logo to create a visually engaging transition:

```java
logo = findViewById(R.id.logo);
logo.animate().rotation(360f).setDuration(2000);
logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);
logo.animate().translationYBy(1000f).setDuration(2000);
logo.animate().alpha(0f).setDuration(6000);
```
rotation(360f): Rotates the logo 360 degrees
scaleX(0.5f).scaleY(0.5f): Scales the logo to half its size
translationYBy(1000f): Moves the logo 1000 pixels down
alpha(0f): Fades out the logo
### Adapter Implementation
The custom adapter extends RecyclerView.Adapter and supports filtering:
```java
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> implements Filterable {
    // Implementation details
}
```
# Video  Demonstration


https://github.com/user-attachments/assets/f3274e37-dfec-4af3-a3db-ea0c81a639e8



## Contributors
- BOULEKNADEL Abderrahmane https://github.com/bouleknadel
