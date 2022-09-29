import React, { useEffect } from 'react';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TouchableOpacity, Image } from 'react-native';
import Mapa from '../components/Mapa';

export default function Home(props) {

  useEffect(() => {
    
  }, [])

    return (
      <View style={styles.container}>
        <View style={styles.menuContainer}>
          <TouchableOpacity onPress={() => {props.navigation.navigate("Settings")}}>
            <Image
              style={styles.img}
              source={require('../assets/person.png')}
              />
          </TouchableOpacity>
        </View>
        <Text style={styles.title}>Bem-vindo(a)</Text>
        <Text style={styles.subtitle}>Savelife é o aplicativo onde você consegue salvar a sua vida ou a vida de outras pessoas mais rápido</Text>
        <Text style={[styles.subtitle, {marginTop: 10}]}>Hospitais perto de você:</Text>
        <Mapa style={styles.map} flex={0.8} />
        <StatusBar style="auto" />
      </View>
    );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    height: '100%',
    paddingTop: 50,
    paddingHorizontal: 25,
    backgroundColor: '#FCFCFC',
  },
  menuContainer: {
    flexDirection: 'row-reverse'
  },
  img: {
    width: 80,
    height: 80,
    borderRadius: 50,
    resizeMode: "cover"
  },
  title: {
    color: '#000',
    fontSize: 23,
    lineHeight: 40,
    fontWeight: "bold"
  },
  subtitle: {
    color: '#000',
    fontSize: 16
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
    marginTop: 15,
  },
});
