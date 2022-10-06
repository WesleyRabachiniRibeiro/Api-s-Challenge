import 'react-native-gesture-handler';
import React from 'react';
import {StyleSheet, TouchableOpacity} from 'react-native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Ionicons } from '@expo/vector-icons';
import AmbulanceHome from '../pages/AmbulanceHome';
import MapaAmbulancia from '../pages/MapaAmbulancia';
import Paciente from '../pages/Paciente';

const Tab = createBottomTabNavigator()

export default function AmbulanceNavigation(props) {
    return (
        <Tab.Navigator
        screenOptions={{
          headerShown: false,
          tabBarLabelStyle: {
            fontSize: 14,
            color: "#f0f0f0"
          },
          tabBarStyle: { 
            position: 'absolute',
            bottom: 20,
            marginHorizontal: 20,
            height: 60,
            borderRadius: 10,
            paddingHorizontal: 20,
            backgroundColor: "#6914FF"
          }
        }}>
          <Tab.Screen
           name="Home"
           component={AmbulanceHome}
           options={{
            headerShown: false,
            tabBarIcon: ({color, size, focused}) => {
              if(focused){
                return <Ionicons name="home" size={size} color={"#fff"}/>
              }
              return <Ionicons name="home-outline" size={size} color={"#fff"}/>
            }
           }}
           />
          <Tab.Screen
            name='Mapa' component={MapaAmbulancia}
            options={{
            headerShown: false,
            tabBarIcon: ({color, size, focused}) => {
              if(focused){
                return <Ionicons name="map" size={size} color={"#fff"}/>
              }
              return <Ionicons name="map-outline" size={size} color={"#fff"}/>
            }
           }}/>

          <Tab.Screen
            name='Paciente' component={Paciente}
            options={{
            headerShown: false,
            tabBarIcon: ({color, size, focused}) => {
              if(focused){
                return <Ionicons name="map" size={size} color={"#fff"}/>
              }
              return <Ionicons name="map-outline" size={size} color={"#fff"}/>
            }
           }}/>
        </Tab.Navigator>
    );

}

const styles = StyleSheet.create({
  tab : {
    backgroundColor: "#6914FF",
    borderColor: "#fff",
    borderRadius: 150,
    borderWidth: 5,
    padding: 10,
    textAlign: "center",
    textAlignVertical: "center"
  }
})