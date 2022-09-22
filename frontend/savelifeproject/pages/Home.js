import React from 'react';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';

export default function Home() {
    return (
      <View style={styles.container}>
        <View style={styles.menuContainer}>
            {/* Menu */}
        </View>
        <Text style={styles.title}>Bem-vindo(a)</Text>
        <Text style={styles.subtitle}>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore.</Text>
        <View style={styles.mapaContainer}>
            <Text style={styles.subtitle}>Hospitais perto de você:</Text>
            {/* Mapa */}
        </View>
        <StatusBar style="auto" />
      </View>
    );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    height: '100%',
    paddingTop: 25,
    backgroundColor: '#FCFCFC',
  },
  menuContainer: {
    flexDirection: 'row'
  },
  title: {
    color: '#000',
    paddingHorizontal: 25,
    fontSize: 23,
    lineHeight: 40
  },
  subtitle: {
    color: '#000',
    paddingHorizontal: 25,
    fontSize: 15
  },
  mapaContainer: {
    paddingTop: 40
  },
  menuInferiorContainer: {
    flex: 1,
    justifyContent: 'flex-end',
  },
  menuInferior: {
    alignItems: 'center',
    borderTopLeftRadius: 30,
    borderTopRightRadius: 30,
    backgroundColor: '#fff',
    width: '100%',
    height: 50,
    shadowColor: "#000",
    shadowOffset: {
	  width: 0,
	  height: 5,
    },
    shadowOpacity: 0.05,
    shadowRadius: 10,
    elevation: 50
  },
  menuInferiorButton: {
    backgroundColor: '#FA533E',
    width: 60,
    height: 60,
    marginTop: -30,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 50
  },
  menuInferiorButtonText: {
    color: '#fff',
    fontSize: 60,
    marginTop: -15,
  }
});
