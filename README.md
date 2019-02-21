
# react-native-zplblutooth
This library support for Zebra Printer.
## Getting started

`$ npm install react-native-zplblutooth --save`

### Mostly automatic installation

`$ react-native link react-native-zplblutooth`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.haka.zplblutooth.RNReactNativeZplblutoothPackage;` to the imports at the top of the file
  - Add `new RNReactNativeZplblutoothPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-zplblutooth'
  	project(':react-native-zplblutooth').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-zplblutooth/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-zplblutooth')
  	```


## Usage
```javascript
import RNReactNativeZplblutooth from 'react-native-zplblutooth';

// TODO: What to do with the module?
RNReactNativeZplblutooth;

OnException=(exception)=>console.log(exception)
OnLog=(exception)=>console.log(exception)

on Mount

RNZplblutooth.on('LIB_EXCEPTION', OnException)
RNZplblutooth.on('LIB_LOG', OnLog)

on Unmount

RNZplblutooth.off('LIB_EXCEPTION', OnException)
RNZplblutooth.off('LIB_LOG', OnLog)

Check Connection

Bluetooth Mac Address From Device
const btmac='XX:XX:XX:XX:XX:XX';
try {
	let ok = await RNZplblutooth.checkConnection(btmac);
	console.log(ok);      
} catch (error) {
	console.log(error);
}

Send Command to Printer

try {
	let ok=await RNZplblutooth.sendZplCommand("^XA^FO20,20^A0N,25,25^FDTest ZPL!!^FS^XZ")
	console.log(ok);
} catch (error) {
	console.log(error);
}
```
  