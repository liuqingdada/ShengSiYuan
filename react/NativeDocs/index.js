/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './src/js/App';
import {name as appName} from './app.json';

AppRegistry.registerComponent(
  // app name
  appName,
  // app component
  () => App,
);
