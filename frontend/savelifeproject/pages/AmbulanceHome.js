import React from 'react'
import {View, Text, TouchableOpacity, StyleSheet} from 'react-native'
import { StatusBar } from 'expo-status-bar';
import { GlobalContext } from '../components/GlobalContext';
import { Ionicons } from "@expo/vector-icons";

export default function AmbulanceHome(props) {
    return (
      <View style={styles.container}>
        <View style={styles.textContainer}>
          <Text style={styles.initialText}>“O melhor médico é aquele que mais esperança inspira” <Text style={styles.initialTextSmall}>(Samuel Taylor Coleridge)</Text></Text>
        </View>
        <View style={styles.buttonsContainer}>
          <TouchableOpacity style={styles.button} onPress={() => props.navigation.navigate("Paciente")} >
            <Ionicons style={styles.buttonIcon} name="person" size={40}/>
            <Text style={styles.buttonText}>Paciente</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.button} onPress={() => props.navigation.navigate("MapaAmbulancia")}>
          <Ionicons style={styles.buttonIcon} name="map" size={40}/>
            <Text style={styles.buttonText}>Mapa</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.button} onPress={() => console.log("Ocorrencias Passadas")}>
            <Ionicons style={styles.buttonIcon} name="arrow-back-outline"/>
            <Text style={styles.buttonText}>Ocorrencias Passadas</Text>
          </TouchableOpacity>
        </View>
      </View>
    );
  }

  const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff'
    },
    textContainer: {
      paddingTop: 50,
      paddingHorizontal: 20,
      alignItems: 'center'
    },
    initialText: {
      fontSize: 20
    },
    initialTextSmall: {
      fontSize: 15
    },
    buttonsContainer: {
      alignItems: 'center',
      marginTop: 60
    },
    button: {
      backgroundColor: '#D9D9D9',
      padding: 10,
      width: '50%',
      alignItems: 'center',
      marginBottom: 30,
      borderRadius: 15
    },
    buttonIcon: {
      marginTop: 10
    },
    buttonText: {
      fontSize: 17,
      textAlign: 'center'
    }
  });