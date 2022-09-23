import 'react-native-gesture-handler';
import React from 'react';
import {StyleSheet} from 'react-native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Ionicons } from '@expo/vector-icons';
import Home from '../pages/Home';
import Chatbot from '../pages/Chatbot'
import Settings from '../pages/Settings'
import Mapa from '../components/Mapa';

const Tab = createBottomTabNavigator()

export default function HomeNavigation(props) {
    return (
        <Tab.Navigator
        screenOptions={{
          headerShown: false,
          tabBarLabelStyle: {
            fontSize: 14,
            color: "#f0f0f0"
          },
          tabBarStyle: { 
            position: 'relative',
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
           component={Home}
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
          <Tab.Screen name='Chatbot' component={Chatbot}
           options={{
            headerShown: false,
            tabBarLabelStyle: {
              display: "none",
            },
            tabBarIconStyle:{
              bottom: 20,
              color: "#000"
            },
            tabBarIcon: ({color, size, focused}) => {
              if(focused){
                return <Ionicons name="chatbox" size={size} color={"#fff"} style={styles.tab}/>
              }
              return <Ionicons name="chatbox-outline" size={size} color={"#fff"} style={styles.tab}/>
            }
            }}/>
          <Tab.Screen
            name='Mapa' component={Mapa}
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