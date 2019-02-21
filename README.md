
# react-native-react-native-zplblutooth

## Getting started

`$ npm install react-native-react-native-zplblutooth --save`

### Mostly automatic installation

`$ react-native link react-native-react-native-zplblutooth`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.haka.zplblutooth.RNReactNativeZplblutoothPackage;` to the imports at the top of the file
  - Add `new RNReactNativeZplblutoothPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-native-zplblutooth'
  	project(':react-native-react-native-zplblutooth').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-zplblutooth/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-native-zplblutooth')
  	```


## Usage
```javascript
import RNReactNativeZplblutooth from 'react-native-react-native-zplblutooth';

// TODO: What to do with the module?
RNReactNativeZplblutooth;
```
  