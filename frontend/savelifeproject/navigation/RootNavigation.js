import 'react-native-gesture-handler';
import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';

import Cadastro from '../pages/Cadastro'
import Login from '../pages/Login'
import HomeNavigation from './HomeNavigation';
import AmbulanceNavigation from './AmbulanceNavigation';

const Stack = createStackNavigator();

export default function RootNavigation() {
    return (
        <Stack.Navigator screenOptions={{
          headerShown: false
        }}>
          <Stack.Screen name='Login' component={Login}/>
          <Stack.Screen name='Cadastro' component={Cadastro}/>
          <Stack.Screen name='HomeNavigation' component={HomeNavigation}/>
          <Stack.Screen name='AmbulanceHome' component={AmbulanceNavigation}/>
        </Stack.Navigator>
    );
}

