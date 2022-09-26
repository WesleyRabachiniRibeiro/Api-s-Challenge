import React from 'react';
import RootNavigation from './navigation/RootNavigation';
import { NavigationContainer } from '@react-navigation/native';
import { AppRegistry } from 'react-native';

AppRegistry.registerComponent('main', () => App);

export default function App() {
    return (
    <NavigationContainer>
      <RootNavigation/>
    </NavigationContainer>
    );
}

