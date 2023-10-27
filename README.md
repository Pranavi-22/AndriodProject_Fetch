# Fetch Rewards Coding Exercise - Mobile App

## Objective

The objective of this mobile application is to retrieve data from a specific URL and present it to the user based on certain criteria.

## Display Conditions

1. **Grouping by "listId"**:
   - All items will be grouped based on their "listId".

2. **Sorting**:
   - The grouped results will be sorted in ascending order first by "listId", and then by "name".

3. **Filtering**:
   - Any items with a blank or null "name" will be excluded from the display.

4. **User-Friendly List Format**:
   - The final result will be presented to the user in a user-friendly list format.


## Implementation Steps

1. **Clone the Repository**:
   - Download the code from the provided repository and open it in Android Studio.

2. **Retrieve Data**:
   - The application fetches data from a specific URL: [https://fetch-hiring.s3.amazonaws.com/hiring.json](https://fetch-hiring.s3.amazonaws.com/hiring.json).

3. **Grouping and Sorting**:
   - The fetched data is grouped by the "listId" and sorted first by "listId", then by "name".

4. **Filtering**:
   - Items with blank or null "name" values are removed from the display.

5. **User Interface**:
   - The final results are presented to the user in a well-organized list format.


## Development Environment

This application is designed to run on Android devices and has been developed using Android Studio, the official IDE for Android development. It leverages the Android SDK to interact with device hardware and software. An emulator or an Android mobile device is required for testing.

Please note that you'll need to set up your Android development environment and ensure that it's properly configured to run the application. Additionally, if you plan to deploy this app on a physical Android device, make sure you have enabled developer mode and USB debugging on the device.

This project provides a foundation for data retrieval, processing, and presentation in an Android application, adhering to specific grouping, sorting, and filtering criteria.

