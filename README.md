# Project Title

About Canada

## Getting Started

These instructions says about the POC and requirements for the project development.

### Prerequisites

To develop an app or POC in Android, we have to install Android Studio which is available at Android developers site.

```
https://developer.android.com/studio/
```

### Installing

A step by step installation details wiil be available for development environment running

```
https://developer.android.com/studio/install
```

## Description

Basic flow of this application is to fetch data from json and display it in listview.
When user open the view, will call repository for fetching data, repository can return cached data by the policy that will be defined and/or make network call, after the data is ready it will be transferred to model and passed to the presenter, presenter will update the view and user will see the updated data.
  
## Features used:

Swipelayout for the swiping list which has swipelistener to refresh the list
Used a retrofit library to convert json data into java. 
Load the data which is need to display in listview.
used An adapter to show the data in a perticular farmat using view holder.
Http Network call for downloading the image for list item.
 
## Folder structure

* `app` - All files related to application development.
* `build` - Contains build outputs.
* `gradle` - Contains build configurations

* `app\src\main\AndroidManifest` - Manifest file contains all the packages, components of the applications, permissions, declares the api.
* `app\src\main\java` - Java source files associated with this project. This includes Activity files. In the package name of com.example.testapp.mytestapp
* `app\src\main\res` - Resource files associated with this project. Strings, layouts in xml form ,images,colors. 

## Build and Run

* `Build` - Build has options like Make Module, Make Project, Clean Project, ReBuild Project, Build APK.
* `Run` - To Run the project you have select Run in the toolbar. If it is first time running the app the android studio will ask to create emulator to run app in to device. If you connected any device, then studio will display the connected devices.Select the device in which you want to deploy the app.
