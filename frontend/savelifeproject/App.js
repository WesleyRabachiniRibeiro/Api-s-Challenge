import React from 'react';
import RootNavigation from './navigation/RootNavigation';
import { NavigationContainer } from '@react-navigation/native';
import { AppRegistry } from 'react-native';
import { GlobalStorage } from './components/GlobalContext';

AppRegistry.registerComponent('main', () => App);

export default function App() {
    return (
      <GlobalStorage>
        <NavigationContainer>
          <RootNavigation/>
        </NavigationContainer>
      </GlobalStorage>
    );
}
