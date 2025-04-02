** Android Developer ** 

Overview

This Android application is developed as part of the Android Developer Internship Selection Process for InfyU Labs. The app demonstrates core Android functionalities, including:

User Authentication UI (Login & Registration)

Image Selection from Camera & Gallery

QR Code Scanning

Features

1. User Authentication (UI Only)

Login and Registration screens with email & password fields.

Form validation (email format, password length).

Navigation from Login to Home Screen (no backend integration).

2. Camera & Gallery Image Selection

Capture an image using the device camera.

Select an image from the gallery.

Display the selected/captured image in an ImageView.

3. QR Code Scanner

Integrated QR code scanner.

Extracts and displays text content from a scanned QR code.

Tech Stack & Requirements

Language: Kotlin or Java

Camera & Gallery Handling: CameraX / Intent

QR Code Scanning: ML Kit / ZXing

Android SDK Version: 23+

Setup Instructions

Prerequisites

Install Android Studio (latest version).

Clone the repository:

git clone https://github.com/yourusername/AndroidDeveloperTask.git

Open the project in Android Studio.

Sync dependencies using Gradle Sync.

Running the App

Connect an Android device or use an emulator.

Grant required permissions (Camera & Storage) when prompted.

Run the application from Android Studio.

Project Structure

AndroidDeveloperTask/
│── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/app/
│   │   │   │   ├── ui/   (Login, Registration, Home Screens)
│   │   │   │   ├── camera/ (Handles Camera & Gallery Operations)
│   │   │   │   ├── qrscanner/ (QR Code Scanner Implementation)
│   │   │   ├── res/ (Layouts, Drawables, and Values)
│   ├── build.gradle
│── README.md
│── .gitignore
│── app-release.apk (for testing)

Implementation Overview

User Authentication (UI Only)

Used EditText for email & password input.

Used Button for login & register actions.

Implemented TextInputLayout for better user input validation.

Used Intent for screen navigation.

Camera & Gallery Image Selection

Used Intent(MediaStore.ACTION_IMAGE_CAPTURE) for capturing images.

Used Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI) for selecting images from the gallery.

Displayed the selected image in an ImageView.

Handled runtime permissions for Camera & Storage.

QR Code Scanner

Integrated ML Kit / ZXing for QR code scanning.

Extracted text from scanned QR codes and displayed it in a TextView.

Ensured smooth user experience using CameraX.

