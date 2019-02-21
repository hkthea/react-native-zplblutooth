
import { NativeModules, DeviceEventEmitter } from 'react-native';

const { RNZplblutooth } = NativeModules;

RNZplblutooth.on = (eventName, handler)=>{
    DeviceEventEmitter.addListener(eventName, handler)
};

RNZplblutooth.off = (eventName, handler)=>{
    DeviceEventEmitter.addListener(eventName, handler)
};

export default RNZplblutooth;
