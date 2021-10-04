## This project performs web and native Android and IOS tests

###### _* The app is included_

**To run tests on Android devices using AVD manager you need to satisfy conditions below:**
- run AVD emulator
- run Appium server
- run maven project build with below xml profiles

All parameters for Appium driver set in two xml files in test/resources package:
- webLocalAndroid.xml
- nativeLocalAndroid.xml

Also, there are 4 profiles to run tests using EPAM mobile cloud, in this project:
- nativeCloudAndroid.xml
- nativeCloudIOS.xml
- webCloudAndroid.xml
- webCloudIOS.xml 

Note that:
- you should also specify you token for EPAM mobile cloud in pom file (there is a place to put value for system variable "token")
- for test run using EPAM mobile cloud, you should specify serial number of device in .xml file ("udid" parameter)
- for native test run using EPAM mobile cloud, you should install included app on device before test run


