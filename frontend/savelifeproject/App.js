import React from 'react';
import RootNavigation from './navigation/RootNavigation';
import { NavigationContainer } from '@react-navigation/native';

export default function App() {
    return (
    <NavigationContainer>
      <RootNavigation/>
    </NavigationContainer>
    );
}

